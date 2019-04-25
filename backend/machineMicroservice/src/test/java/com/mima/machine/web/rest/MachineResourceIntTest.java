package com.mima.machine.web.rest;

import com.mima.machine.MachineMicroserviceApp;

import com.mima.machine.domain.Machine;
import com.mima.machine.repository.MachineRepository;
import com.mima.machine.service.MachineService;
import com.mima.machine.service.dto.MachineDTO;
import com.mima.machine.service.mapper.MachineMapper;
import com.mima.machine.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.mima.machine.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MachineResource REST controller.
 *
 * @see MachineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MachineMicroserviceApp.class)
public class MachineResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_USER = 1;
    private static final Integer UPDATED_ID_USER = 2;

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private MachineMapper machineMapper;

    @Autowired
    private MachineService machineService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMachineMockMvc;

    private Machine machine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MachineResource machineResource = new MachineResource(machineService);
        this.restMachineMockMvc = MockMvcBuilders.standaloneSetup(machineResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Machine createEntity(EntityManager em) {
        Machine machine = new Machine()
            .name(DEFAULT_NAME)
            .model(DEFAULT_MODEL)
            .idUser(DEFAULT_ID_USER);
        return machine;
    }

    @Before
    public void initTest() {
        machine = createEntity(em);
    }

    @Test
    @Transactional
    public void createMachine() throws Exception {
        int databaseSizeBeforeCreate = machineRepository.findAll().size();

        // Create the Machine
        MachineDTO machineDTO = machineMapper.toDto(machine);
        restMachineMockMvc.perform(post("/api/machines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(machineDTO)))
            .andExpect(status().isCreated());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeCreate + 1);
        Machine testMachine = machineList.get(machineList.size() - 1);
        assertThat(testMachine.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMachine.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testMachine.getIdUser()).isEqualTo(DEFAULT_ID_USER);
    }

    @Test
    @Transactional
    public void createMachineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = machineRepository.findAll().size();

        // Create the Machine with an existing ID
        machine.setId(1L);
        MachineDTO machineDTO = machineMapper.toDto(machine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMachineMockMvc.perform(post("/api/machines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(machineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMachines() throws Exception {
        // Initialize the database
        machineRepository.saveAndFlush(machine);

        // Get all the machineList
        restMachineMockMvc.perform(get("/api/machines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(machine.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].idUser").value(hasItem(DEFAULT_ID_USER)));
    }
    
    @Test
    @Transactional
    public void getMachine() throws Exception {
        // Initialize the database
        machineRepository.saveAndFlush(machine);

        // Get the machine
        restMachineMockMvc.perform(get("/api/machines/{id}", machine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(machine.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.idUser").value(DEFAULT_ID_USER));
    }

    @Test
    @Transactional
    public void getNonExistingMachine() throws Exception {
        // Get the machine
        restMachineMockMvc.perform(get("/api/machines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMachine() throws Exception {
        // Initialize the database
        machineRepository.saveAndFlush(machine);

        int databaseSizeBeforeUpdate = machineRepository.findAll().size();

        // Update the machine
        Machine updatedMachine = machineRepository.findById(machine.getId()).get();
        // Disconnect from session so that the updates on updatedMachine are not directly saved in db
        em.detach(updatedMachine);
        updatedMachine
            .name(UPDATED_NAME)
            .model(UPDATED_MODEL)
            .idUser(UPDATED_ID_USER);
        MachineDTO machineDTO = machineMapper.toDto(updatedMachine);

        restMachineMockMvc.perform(put("/api/machines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(machineDTO)))
            .andExpect(status().isOk());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeUpdate);
        Machine testMachine = machineList.get(machineList.size() - 1);
        assertThat(testMachine.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMachine.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testMachine.getIdUser()).isEqualTo(UPDATED_ID_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingMachine() throws Exception {
        int databaseSizeBeforeUpdate = machineRepository.findAll().size();

        // Create the Machine
        MachineDTO machineDTO = machineMapper.toDto(machine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMachineMockMvc.perform(put("/api/machines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(machineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMachine() throws Exception {
        // Initialize the database
        machineRepository.saveAndFlush(machine);

        int databaseSizeBeforeDelete = machineRepository.findAll().size();

        // Delete the machine
        restMachineMockMvc.perform(delete("/api/machines/{id}", machine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Machine.class);
        Machine machine1 = new Machine();
        machine1.setId(1L);
        Machine machine2 = new Machine();
        machine2.setId(machine1.getId());
        assertThat(machine1).isEqualTo(machine2);
        machine2.setId(2L);
        assertThat(machine1).isNotEqualTo(machine2);
        machine1.setId(null);
        assertThat(machine1).isNotEqualTo(machine2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MachineDTO.class);
        MachineDTO machineDTO1 = new MachineDTO();
        machineDTO1.setId(1L);
        MachineDTO machineDTO2 = new MachineDTO();
        assertThat(machineDTO1).isNotEqualTo(machineDTO2);
        machineDTO2.setId(machineDTO1.getId());
        assertThat(machineDTO1).isEqualTo(machineDTO2);
        machineDTO2.setId(2L);
        assertThat(machineDTO1).isNotEqualTo(machineDTO2);
        machineDTO1.setId(null);
        assertThat(machineDTO1).isNotEqualTo(machineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(machineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(machineMapper.fromId(null)).isNull();
    }
}
