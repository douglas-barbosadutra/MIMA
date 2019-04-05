package com.mima.wbs.web.rest;

import com.mima.wbs.WbsMicroserviceApp;

import com.mima.wbs.domain.Instruction;
import com.mima.wbs.repository.InstructionRepository;
import com.mima.wbs.service.InstructionService;
import com.mima.wbs.service.dto.InstructionDTO;
import com.mima.wbs.service.mapper.InstructionMapper;
import com.mima.wbs.web.rest.errors.ExceptionTranslator;

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


import static com.mima.wbs.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InstructionResource REST controller.
 *
 * @see InstructionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WbsMicroserviceApp.class)
public class InstructionResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    private static final String DEFAULT_GCODE_FILE = "AAAAAAAAAA";
    private static final String UPDATED_GCODE_FILE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_TASK = 1;
    private static final Integer UPDATED_ID_TASK = 2;

    @Autowired
    private InstructionRepository instructionRepository;

    @Autowired
    private InstructionMapper instructionMapper;

    @Autowired
    private InstructionService instructionService;

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

    private MockMvc restInstructionMockMvc;

    private Instruction instruction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InstructionResource instructionResource = new InstructionResource(instructionService);
        this.restInstructionMockMvc = MockMvcBuilders.standaloneSetup(instructionResource)
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
    public static Instruction createEntity(EntityManager em) {
        Instruction instruction = new Instruction()
            .name(DEFAULT_NAME)
            .duration(DEFAULT_DURATION)
            .gcodeFile(DEFAULT_GCODE_FILE)
            .idTask(DEFAULT_ID_TASK);
        return instruction;
    }

    @Before
    public void initTest() {
        instruction = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstruction() throws Exception {
        int databaseSizeBeforeCreate = instructionRepository.findAll().size();

        // Create the Instruction
        InstructionDTO instructionDTO = instructionMapper.toDto(instruction);
        restInstructionMockMvc.perform(post("/api/instructions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructionDTO)))
            .andExpect(status().isCreated());

        // Validate the Instruction in the database
        List<Instruction> instructionList = instructionRepository.findAll();
        assertThat(instructionList).hasSize(databaseSizeBeforeCreate + 1);
        Instruction testInstruction = instructionList.get(instructionList.size() - 1);
        assertThat(testInstruction.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testInstruction.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testInstruction.getGcodeFile()).isEqualTo(DEFAULT_GCODE_FILE);
        assertThat(testInstruction.getIdTask()).isEqualTo(DEFAULT_ID_TASK);
    }

    @Test
    @Transactional
    public void createInstructionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = instructionRepository.findAll().size();

        // Create the Instruction with an existing ID
        instruction.setId(1L);
        InstructionDTO instructionDTO = instructionMapper.toDto(instruction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstructionMockMvc.perform(post("/api/instructions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Instruction in the database
        List<Instruction> instructionList = instructionRepository.findAll();
        assertThat(instructionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInstructions() throws Exception {
        // Initialize the database
        instructionRepository.saveAndFlush(instruction);

        // Get all the instructionList
        restInstructionMockMvc.perform(get("/api/instructions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(instruction.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].gcodeFile").value(hasItem(DEFAULT_GCODE_FILE.toString())))
            .andExpect(jsonPath("$.[*].idTask").value(hasItem(DEFAULT_ID_TASK)));
    }
    
    @Test
    @Transactional
    public void getInstruction() throws Exception {
        // Initialize the database
        instructionRepository.saveAndFlush(instruction);

        // Get the instruction
        restInstructionMockMvc.perform(get("/api/instructions/{id}", instruction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(instruction.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.gcodeFile").value(DEFAULT_GCODE_FILE.toString()))
            .andExpect(jsonPath("$.idTask").value(DEFAULT_ID_TASK));
    }

    @Test
    @Transactional
    public void getNonExistingInstruction() throws Exception {
        // Get the instruction
        restInstructionMockMvc.perform(get("/api/instructions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstruction() throws Exception {
        // Initialize the database
        instructionRepository.saveAndFlush(instruction);

        int databaseSizeBeforeUpdate = instructionRepository.findAll().size();

        // Update the instruction
        Instruction updatedInstruction = instructionRepository.findById(instruction.getId()).get();
        // Disconnect from session so that the updates on updatedInstruction are not directly saved in db
        em.detach(updatedInstruction);
        updatedInstruction
            .name(UPDATED_NAME)
            .duration(UPDATED_DURATION)
            .gcodeFile(UPDATED_GCODE_FILE)
            .idTask(UPDATED_ID_TASK);
        InstructionDTO instructionDTO = instructionMapper.toDto(updatedInstruction);

        restInstructionMockMvc.perform(put("/api/instructions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructionDTO)))
            .andExpect(status().isOk());

        // Validate the Instruction in the database
        List<Instruction> instructionList = instructionRepository.findAll();
        assertThat(instructionList).hasSize(databaseSizeBeforeUpdate);
        Instruction testInstruction = instructionList.get(instructionList.size() - 1);
        assertThat(testInstruction.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testInstruction.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testInstruction.getGcodeFile()).isEqualTo(UPDATED_GCODE_FILE);
        assertThat(testInstruction.getIdTask()).isEqualTo(UPDATED_ID_TASK);
    }

    @Test
    @Transactional
    public void updateNonExistingInstruction() throws Exception {
        int databaseSizeBeforeUpdate = instructionRepository.findAll().size();

        // Create the Instruction
        InstructionDTO instructionDTO = instructionMapper.toDto(instruction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstructionMockMvc.perform(put("/api/instructions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instructionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Instruction in the database
        List<Instruction> instructionList = instructionRepository.findAll();
        assertThat(instructionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInstruction() throws Exception {
        // Initialize the database
        instructionRepository.saveAndFlush(instruction);

        int databaseSizeBeforeDelete = instructionRepository.findAll().size();

        // Delete the instruction
        restInstructionMockMvc.perform(delete("/api/instructions/{id}", instruction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Instruction> instructionList = instructionRepository.findAll();
        assertThat(instructionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Instruction.class);
        Instruction instruction1 = new Instruction();
        instruction1.setId(1L);
        Instruction instruction2 = new Instruction();
        instruction2.setId(instruction1.getId());
        assertThat(instruction1).isEqualTo(instruction2);
        instruction2.setId(2L);
        assertThat(instruction1).isNotEqualTo(instruction2);
        instruction1.setId(null);
        assertThat(instruction1).isNotEqualTo(instruction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstructionDTO.class);
        InstructionDTO instructionDTO1 = new InstructionDTO();
        instructionDTO1.setId(1L);
        InstructionDTO instructionDTO2 = new InstructionDTO();
        assertThat(instructionDTO1).isNotEqualTo(instructionDTO2);
        instructionDTO2.setId(instructionDTO1.getId());
        assertThat(instructionDTO1).isEqualTo(instructionDTO2);
        instructionDTO2.setId(2L);
        assertThat(instructionDTO1).isNotEqualTo(instructionDTO2);
        instructionDTO1.setId(null);
        assertThat(instructionDTO1).isNotEqualTo(instructionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(instructionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(instructionMapper.fromId(null)).isNull();
    }
}
