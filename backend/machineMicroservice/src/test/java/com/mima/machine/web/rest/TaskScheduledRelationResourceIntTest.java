package com.mima.machine.web.rest;

import com.mima.machine.MachineMicroserviceApp;

import com.mima.machine.domain.TaskScheduledRelation;
import com.mima.machine.repository.TaskScheduledRelationRepository;
import com.mima.machine.service.TaskScheduledRelationService;
import com.mima.machine.service.dto.TaskScheduledRelationDTO;
import com.mima.machine.service.mapper.TaskScheduledRelationMapper;
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
 * Test class for the TaskScheduledRelationResource REST controller.
 *
 * @see TaskScheduledRelationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MachineMicroserviceApp.class)
public class TaskScheduledRelationResourceIntTest {

    @Autowired
    private TaskScheduledRelationRepository taskScheduledRelationRepository;

    @Autowired
    private TaskScheduledRelationMapper taskScheduledRelationMapper;

    @Autowired
    private TaskScheduledRelationService taskScheduledRelationService;

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

    private MockMvc restTaskScheduledRelationMockMvc;

    private TaskScheduledRelation taskScheduledRelation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaskScheduledRelationResource taskScheduledRelationResource = new TaskScheduledRelationResource(taskScheduledRelationService);
        this.restTaskScheduledRelationMockMvc = MockMvcBuilders.standaloneSetup(taskScheduledRelationResource)
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
    public static TaskScheduledRelation createEntity(EntityManager em) {
        TaskScheduledRelation taskScheduledRelation = new TaskScheduledRelation();
        return taskScheduledRelation;
    }

    @Before
    public void initTest() {
        taskScheduledRelation = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaskScheduledRelation() throws Exception {
        int databaseSizeBeforeCreate = taskScheduledRelationRepository.findAll().size();

        // Create the TaskScheduledRelation
        TaskScheduledRelationDTO taskScheduledRelationDTO = taskScheduledRelationMapper.toDto(taskScheduledRelation);
        restTaskScheduledRelationMockMvc.perform(post("/api/task-scheduled-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskScheduledRelationDTO)))
            .andExpect(status().isCreated());

        // Validate the TaskScheduledRelation in the database
        List<TaskScheduledRelation> taskScheduledRelationList = taskScheduledRelationRepository.findAll();
        assertThat(taskScheduledRelationList).hasSize(databaseSizeBeforeCreate + 1);
        TaskScheduledRelation testTaskScheduledRelation = taskScheduledRelationList.get(taskScheduledRelationList.size() - 1);
    }

    @Test
    @Transactional
    public void createTaskScheduledRelationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taskScheduledRelationRepository.findAll().size();

        // Create the TaskScheduledRelation with an existing ID
        taskScheduledRelation.setId(1L);
        TaskScheduledRelationDTO taskScheduledRelationDTO = taskScheduledRelationMapper.toDto(taskScheduledRelation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskScheduledRelationMockMvc.perform(post("/api/task-scheduled-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskScheduledRelationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaskScheduledRelation in the database
        List<TaskScheduledRelation> taskScheduledRelationList = taskScheduledRelationRepository.findAll();
        assertThat(taskScheduledRelationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTaskScheduledRelations() throws Exception {
        // Initialize the database
        taskScheduledRelationRepository.saveAndFlush(taskScheduledRelation);

        // Get all the taskScheduledRelationList
        restTaskScheduledRelationMockMvc.perform(get("/api/task-scheduled-relations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskScheduledRelation.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getTaskScheduledRelation() throws Exception {
        // Initialize the database
        taskScheduledRelationRepository.saveAndFlush(taskScheduledRelation);

        // Get the taskScheduledRelation
        restTaskScheduledRelationMockMvc.perform(get("/api/task-scheduled-relations/{id}", taskScheduledRelation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(taskScheduledRelation.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTaskScheduledRelation() throws Exception {
        // Get the taskScheduledRelation
        restTaskScheduledRelationMockMvc.perform(get("/api/task-scheduled-relations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaskScheduledRelation() throws Exception {
        // Initialize the database
        taskScheduledRelationRepository.saveAndFlush(taskScheduledRelation);

        int databaseSizeBeforeUpdate = taskScheduledRelationRepository.findAll().size();

        // Update the taskScheduledRelation
        TaskScheduledRelation updatedTaskScheduledRelation = taskScheduledRelationRepository.findById(taskScheduledRelation.getId()).get();
        // Disconnect from session so that the updates on updatedTaskScheduledRelation are not directly saved in db
        em.detach(updatedTaskScheduledRelation);
        TaskScheduledRelationDTO taskScheduledRelationDTO = taskScheduledRelationMapper.toDto(updatedTaskScheduledRelation);

        restTaskScheduledRelationMockMvc.perform(put("/api/task-scheduled-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskScheduledRelationDTO)))
            .andExpect(status().isOk());

        // Validate the TaskScheduledRelation in the database
        List<TaskScheduledRelation> taskScheduledRelationList = taskScheduledRelationRepository.findAll();
        assertThat(taskScheduledRelationList).hasSize(databaseSizeBeforeUpdate);
        TaskScheduledRelation testTaskScheduledRelation = taskScheduledRelationList.get(taskScheduledRelationList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTaskScheduledRelation() throws Exception {
        int databaseSizeBeforeUpdate = taskScheduledRelationRepository.findAll().size();

        // Create the TaskScheduledRelation
        TaskScheduledRelationDTO taskScheduledRelationDTO = taskScheduledRelationMapper.toDto(taskScheduledRelation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskScheduledRelationMockMvc.perform(put("/api/task-scheduled-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskScheduledRelationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaskScheduledRelation in the database
        List<TaskScheduledRelation> taskScheduledRelationList = taskScheduledRelationRepository.findAll();
        assertThat(taskScheduledRelationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTaskScheduledRelation() throws Exception {
        // Initialize the database
        taskScheduledRelationRepository.saveAndFlush(taskScheduledRelation);

        int databaseSizeBeforeDelete = taskScheduledRelationRepository.findAll().size();

        // Delete the taskScheduledRelation
        restTaskScheduledRelationMockMvc.perform(delete("/api/task-scheduled-relations/{id}", taskScheduledRelation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TaskScheduledRelation> taskScheduledRelationList = taskScheduledRelationRepository.findAll();
        assertThat(taskScheduledRelationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskScheduledRelation.class);
        TaskScheduledRelation taskScheduledRelation1 = new TaskScheduledRelation();
        taskScheduledRelation1.setId(1L);
        TaskScheduledRelation taskScheduledRelation2 = new TaskScheduledRelation();
        taskScheduledRelation2.setId(taskScheduledRelation1.getId());
        assertThat(taskScheduledRelation1).isEqualTo(taskScheduledRelation2);
        taskScheduledRelation2.setId(2L);
        assertThat(taskScheduledRelation1).isNotEqualTo(taskScheduledRelation2);
        taskScheduledRelation1.setId(null);
        assertThat(taskScheduledRelation1).isNotEqualTo(taskScheduledRelation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskScheduledRelationDTO.class);
        TaskScheduledRelationDTO taskScheduledRelationDTO1 = new TaskScheduledRelationDTO();
        taskScheduledRelationDTO1.setId(1L);
        TaskScheduledRelationDTO taskScheduledRelationDTO2 = new TaskScheduledRelationDTO();
        assertThat(taskScheduledRelationDTO1).isNotEqualTo(taskScheduledRelationDTO2);
        taskScheduledRelationDTO2.setId(taskScheduledRelationDTO1.getId());
        assertThat(taskScheduledRelationDTO1).isEqualTo(taskScheduledRelationDTO2);
        taskScheduledRelationDTO2.setId(2L);
        assertThat(taskScheduledRelationDTO1).isNotEqualTo(taskScheduledRelationDTO2);
        taskScheduledRelationDTO1.setId(null);
        assertThat(taskScheduledRelationDTO1).isNotEqualTo(taskScheduledRelationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(taskScheduledRelationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(taskScheduledRelationMapper.fromId(null)).isNull();
    }
}
