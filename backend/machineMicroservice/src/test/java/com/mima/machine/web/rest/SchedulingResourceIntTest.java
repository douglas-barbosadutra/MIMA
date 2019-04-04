package com.mima.machine.web.rest;

import com.mima.machine.MachineMicroserviceApp;

import com.mima.machine.domain.Scheduling;
import com.mima.machine.repository.SchedulingRepository;
import com.mima.machine.service.SchedulingService;
import com.mima.machine.service.dto.SchedulingDTO;
import com.mima.machine.service.mapper.SchedulingMapper;
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
 * Test class for the SchedulingResource REST controller.
 *
 * @see SchedulingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MachineMicroserviceApp.class)
public class SchedulingResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_START = "AAAAAAAAAA";
    private static final String UPDATED_START = "BBBBBBBBBB";

    private static final String DEFAULT_FINISH = "AAAAAAAAAA";
    private static final String UPDATED_FINISH = "BBBBBBBBBB";

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private SchedulingMapper schedulingMapper;

    @Autowired
    private SchedulingService schedulingService;

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

    private MockMvc restSchedulingMockMvc;

    private Scheduling scheduling;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SchedulingResource schedulingResource = new SchedulingResource(schedulingService);
        this.restSchedulingMockMvc = MockMvcBuilders.standaloneSetup(schedulingResource)
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
    public static Scheduling createEntity(EntityManager em) {
        Scheduling scheduling = new Scheduling()
            .name(DEFAULT_NAME)
            .start(DEFAULT_START)
            .finish(DEFAULT_FINISH);
        return scheduling;
    }

    @Before
    public void initTest() {
        scheduling = createEntity(em);
    }

    @Test
    @Transactional
    public void createScheduling() throws Exception {
        int databaseSizeBeforeCreate = schedulingRepository.findAll().size();

        // Create the Scheduling
        SchedulingDTO schedulingDTO = schedulingMapper.toDto(scheduling);
        restSchedulingMockMvc.perform(post("/api/schedulings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(schedulingDTO)))
            .andExpect(status().isCreated());

        // Validate the Scheduling in the database
        List<Scheduling> schedulingList = schedulingRepository.findAll();
        assertThat(schedulingList).hasSize(databaseSizeBeforeCreate + 1);
        Scheduling testScheduling = schedulingList.get(schedulingList.size() - 1);
        assertThat(testScheduling.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testScheduling.getStart()).isEqualTo(DEFAULT_START);
        assertThat(testScheduling.getFinish()).isEqualTo(DEFAULT_FINISH);
    }

    @Test
    @Transactional
    public void createSchedulingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = schedulingRepository.findAll().size();

        // Create the Scheduling with an existing ID
        scheduling.setId(1L);
        SchedulingDTO schedulingDTO = schedulingMapper.toDto(scheduling);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSchedulingMockMvc.perform(post("/api/schedulings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(schedulingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Scheduling in the database
        List<Scheduling> schedulingList = schedulingRepository.findAll();
        assertThat(schedulingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSchedulings() throws Exception {
        // Initialize the database
        schedulingRepository.saveAndFlush(scheduling);

        // Get all the schedulingList
        restSchedulingMockMvc.perform(get("/api/schedulings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scheduling.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].start").value(hasItem(DEFAULT_START.toString())))
            .andExpect(jsonPath("$.[*].finish").value(hasItem(DEFAULT_FINISH.toString())));
    }
    
    @Test
    @Transactional
    public void getScheduling() throws Exception {
        // Initialize the database
        schedulingRepository.saveAndFlush(scheduling);

        // Get the scheduling
        restSchedulingMockMvc.perform(get("/api/schedulings/{id}", scheduling.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(scheduling.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.start").value(DEFAULT_START.toString()))
            .andExpect(jsonPath("$.finish").value(DEFAULT_FINISH.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingScheduling() throws Exception {
        // Get the scheduling
        restSchedulingMockMvc.perform(get("/api/schedulings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateScheduling() throws Exception {
        // Initialize the database
        schedulingRepository.saveAndFlush(scheduling);

        int databaseSizeBeforeUpdate = schedulingRepository.findAll().size();

        // Update the scheduling
        Scheduling updatedScheduling = schedulingRepository.findById(scheduling.getId()).get();
        // Disconnect from session so that the updates on updatedScheduling are not directly saved in db
        em.detach(updatedScheduling);
        updatedScheduling
            .name(UPDATED_NAME)
            .start(UPDATED_START)
            .finish(UPDATED_FINISH);
        SchedulingDTO schedulingDTO = schedulingMapper.toDto(updatedScheduling);

        restSchedulingMockMvc.perform(put("/api/schedulings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(schedulingDTO)))
            .andExpect(status().isOk());

        // Validate the Scheduling in the database
        List<Scheduling> schedulingList = schedulingRepository.findAll();
        assertThat(schedulingList).hasSize(databaseSizeBeforeUpdate);
        Scheduling testScheduling = schedulingList.get(schedulingList.size() - 1);
        assertThat(testScheduling.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testScheduling.getStart()).isEqualTo(UPDATED_START);
        assertThat(testScheduling.getFinish()).isEqualTo(UPDATED_FINISH);
    }

    @Test
    @Transactional
    public void updateNonExistingScheduling() throws Exception {
        int databaseSizeBeforeUpdate = schedulingRepository.findAll().size();

        // Create the Scheduling
        SchedulingDTO schedulingDTO = schedulingMapper.toDto(scheduling);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSchedulingMockMvc.perform(put("/api/schedulings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(schedulingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Scheduling in the database
        List<Scheduling> schedulingList = schedulingRepository.findAll();
        assertThat(schedulingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteScheduling() throws Exception {
        // Initialize the database
        schedulingRepository.saveAndFlush(scheduling);

        int databaseSizeBeforeDelete = schedulingRepository.findAll().size();

        // Delete the scheduling
        restSchedulingMockMvc.perform(delete("/api/schedulings/{id}", scheduling.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Scheduling> schedulingList = schedulingRepository.findAll();
        assertThat(schedulingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Scheduling.class);
        Scheduling scheduling1 = new Scheduling();
        scheduling1.setId(1L);
        Scheduling scheduling2 = new Scheduling();
        scheduling2.setId(scheduling1.getId());
        assertThat(scheduling1).isEqualTo(scheduling2);
        scheduling2.setId(2L);
        assertThat(scheduling1).isNotEqualTo(scheduling2);
        scheduling1.setId(null);
        assertThat(scheduling1).isNotEqualTo(scheduling2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SchedulingDTO.class);
        SchedulingDTO schedulingDTO1 = new SchedulingDTO();
        schedulingDTO1.setId(1L);
        SchedulingDTO schedulingDTO2 = new SchedulingDTO();
        assertThat(schedulingDTO1).isNotEqualTo(schedulingDTO2);
        schedulingDTO2.setId(schedulingDTO1.getId());
        assertThat(schedulingDTO1).isEqualTo(schedulingDTO2);
        schedulingDTO2.setId(2L);
        assertThat(schedulingDTO1).isNotEqualTo(schedulingDTO2);
        schedulingDTO1.setId(null);
        assertThat(schedulingDTO1).isNotEqualTo(schedulingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(schedulingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(schedulingMapper.fromId(null)).isNull();
    }
}
