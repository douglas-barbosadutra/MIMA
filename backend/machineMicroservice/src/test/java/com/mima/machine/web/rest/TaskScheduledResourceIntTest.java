package com.mima.machine.web.rest;

import com.mima.machine.MachineMicroserviceApp;

import com.mima.machine.domain.TaskScheduled;
import com.mima.machine.repository.TaskScheduledRepository;
import com.mima.machine.service.TaskScheduledService;
import com.mima.machine.service.dto.TaskScheduledDTO;
import com.mima.machine.service.mapper.TaskScheduledMapper;
import com.mima.machine.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.mima.machine.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TaskScheduledResource REST controller.
 *
 * @see TaskScheduledResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MachineMicroserviceApp.class)
public class TaskScheduledResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_OUTPUT = 1;
    private static final Integer UPDATED_ID_OUTPUT = 2;

    @Autowired
    private TaskScheduledRepository taskScheduledRepository;

    @Mock
    private TaskScheduledRepository taskScheduledRepositoryMock;

    @Autowired
    private TaskScheduledMapper taskScheduledMapper;

    @Mock
    private TaskScheduledService taskScheduledServiceMock;

    @Autowired
    private TaskScheduledService taskScheduledService;

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

    private MockMvc restTaskScheduledMockMvc;

    private TaskScheduled taskScheduled;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaskScheduledResource taskScheduledResource = new TaskScheduledResource(taskScheduledService);
        this.restTaskScheduledMockMvc = MockMvcBuilders.standaloneSetup(taskScheduledResource)
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
    public static TaskScheduled createEntity(EntityManager em) {
        TaskScheduled taskScheduled = new TaskScheduled()
            .name(DEFAULT_NAME)
            .idOutput(DEFAULT_ID_OUTPUT);
        return taskScheduled;
    }

    @Before
    public void initTest() {
        taskScheduled = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaskScheduled() throws Exception {
        int databaseSizeBeforeCreate = taskScheduledRepository.findAll().size();

        // Create the TaskScheduled
        TaskScheduledDTO taskScheduledDTO = taskScheduledMapper.toDto(taskScheduled);
        restTaskScheduledMockMvc.perform(post("/api/task-scheduleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskScheduledDTO)))
            .andExpect(status().isCreated());

        // Validate the TaskScheduled in the database
        List<TaskScheduled> taskScheduledList = taskScheduledRepository.findAll();
        assertThat(taskScheduledList).hasSize(databaseSizeBeforeCreate + 1);
        TaskScheduled testTaskScheduled = taskScheduledList.get(taskScheduledList.size() - 1);
        assertThat(testTaskScheduled.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTaskScheduled.getIdOutput()).isEqualTo(DEFAULT_ID_OUTPUT);
    }

    @Test
    @Transactional
    public void createTaskScheduledWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taskScheduledRepository.findAll().size();

        // Create the TaskScheduled with an existing ID
        taskScheduled.setId(1L);
        TaskScheduledDTO taskScheduledDTO = taskScheduledMapper.toDto(taskScheduled);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskScheduledMockMvc.perform(post("/api/task-scheduleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskScheduledDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaskScheduled in the database
        List<TaskScheduled> taskScheduledList = taskScheduledRepository.findAll();
        assertThat(taskScheduledList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTaskScheduleds() throws Exception {
        // Initialize the database
        taskScheduledRepository.saveAndFlush(taskScheduled);

        // Get all the taskScheduledList
        restTaskScheduledMockMvc.perform(get("/api/task-scheduleds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskScheduled.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].idOutput").value(hasItem(DEFAULT_ID_OUTPUT)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllTaskScheduledsWithEagerRelationshipsIsEnabled() throws Exception {
        TaskScheduledResource taskScheduledResource = new TaskScheduledResource(taskScheduledServiceMock);
        when(taskScheduledServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restTaskScheduledMockMvc = MockMvcBuilders.standaloneSetup(taskScheduledResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restTaskScheduledMockMvc.perform(get("/api/task-scheduleds?eagerload=true"))
        .andExpect(status().isOk());

        verify(taskScheduledServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllTaskScheduledsWithEagerRelationshipsIsNotEnabled() throws Exception {
        TaskScheduledResource taskScheduledResource = new TaskScheduledResource(taskScheduledServiceMock);
            when(taskScheduledServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restTaskScheduledMockMvc = MockMvcBuilders.standaloneSetup(taskScheduledResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restTaskScheduledMockMvc.perform(get("/api/task-scheduleds?eagerload=true"))
        .andExpect(status().isOk());

            verify(taskScheduledServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getTaskScheduled() throws Exception {
        // Initialize the database
        taskScheduledRepository.saveAndFlush(taskScheduled);

        // Get the taskScheduled
        restTaskScheduledMockMvc.perform(get("/api/task-scheduleds/{id}", taskScheduled.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(taskScheduled.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.idOutput").value(DEFAULT_ID_OUTPUT));
    }

    @Test
    @Transactional
    public void getNonExistingTaskScheduled() throws Exception {
        // Get the taskScheduled
        restTaskScheduledMockMvc.perform(get("/api/task-scheduleds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaskScheduled() throws Exception {
        // Initialize the database
        taskScheduledRepository.saveAndFlush(taskScheduled);

        int databaseSizeBeforeUpdate = taskScheduledRepository.findAll().size();

        // Update the taskScheduled
        TaskScheduled updatedTaskScheduled = taskScheduledRepository.findById(taskScheduled.getId()).get();
        // Disconnect from session so that the updates on updatedTaskScheduled are not directly saved in db
        em.detach(updatedTaskScheduled);
        updatedTaskScheduled
            .name(UPDATED_NAME)
            .idOutput(UPDATED_ID_OUTPUT);
        TaskScheduledDTO taskScheduledDTO = taskScheduledMapper.toDto(updatedTaskScheduled);

        restTaskScheduledMockMvc.perform(put("/api/task-scheduleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskScheduledDTO)))
            .andExpect(status().isOk());

        // Validate the TaskScheduled in the database
        List<TaskScheduled> taskScheduledList = taskScheduledRepository.findAll();
        assertThat(taskScheduledList).hasSize(databaseSizeBeforeUpdate);
        TaskScheduled testTaskScheduled = taskScheduledList.get(taskScheduledList.size() - 1);
        assertThat(testTaskScheduled.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTaskScheduled.getIdOutput()).isEqualTo(UPDATED_ID_OUTPUT);
    }

    @Test
    @Transactional
    public void updateNonExistingTaskScheduled() throws Exception {
        int databaseSizeBeforeUpdate = taskScheduledRepository.findAll().size();

        // Create the TaskScheduled
        TaskScheduledDTO taskScheduledDTO = taskScheduledMapper.toDto(taskScheduled);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskScheduledMockMvc.perform(put("/api/task-scheduleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskScheduledDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaskScheduled in the database
        List<TaskScheduled> taskScheduledList = taskScheduledRepository.findAll();
        assertThat(taskScheduledList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTaskScheduled() throws Exception {
        // Initialize the database
        taskScheduledRepository.saveAndFlush(taskScheduled);

        int databaseSizeBeforeDelete = taskScheduledRepository.findAll().size();

        // Delete the taskScheduled
        restTaskScheduledMockMvc.perform(delete("/api/task-scheduleds/{id}", taskScheduled.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TaskScheduled> taskScheduledList = taskScheduledRepository.findAll();
        assertThat(taskScheduledList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskScheduled.class);
        TaskScheduled taskScheduled1 = new TaskScheduled();
        taskScheduled1.setId(1L);
        TaskScheduled taskScheduled2 = new TaskScheduled();
        taskScheduled2.setId(taskScheduled1.getId());
        assertThat(taskScheduled1).isEqualTo(taskScheduled2);
        taskScheduled2.setId(2L);
        assertThat(taskScheduled1).isNotEqualTo(taskScheduled2);
        taskScheduled1.setId(null);
        assertThat(taskScheduled1).isNotEqualTo(taskScheduled2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskScheduledDTO.class);
        TaskScheduledDTO taskScheduledDTO1 = new TaskScheduledDTO();
        taskScheduledDTO1.setId(1L);
        TaskScheduledDTO taskScheduledDTO2 = new TaskScheduledDTO();
        assertThat(taskScheduledDTO1).isNotEqualTo(taskScheduledDTO2);
        taskScheduledDTO2.setId(taskScheduledDTO1.getId());
        assertThat(taskScheduledDTO1).isEqualTo(taskScheduledDTO2);
        taskScheduledDTO2.setId(2L);
        assertThat(taskScheduledDTO1).isNotEqualTo(taskScheduledDTO2);
        taskScheduledDTO1.setId(null);
        assertThat(taskScheduledDTO1).isNotEqualTo(taskScheduledDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(taskScheduledMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(taskScheduledMapper.fromId(null)).isNull();
    }
}
