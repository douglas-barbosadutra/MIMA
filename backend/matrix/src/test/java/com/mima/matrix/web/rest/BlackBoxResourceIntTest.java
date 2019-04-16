package com.mima.matrix.web.rest;

import com.mima.matrix.MatrixApp;

import com.mima.matrix.domain.BlackBox;
import com.mima.matrix.repository.BlackBoxRepository;
import com.mima.matrix.service.BlackBoxService;
import com.mima.matrix.web.rest.errors.ExceptionTranslator;

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


import static com.mima.matrix.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BlackBoxResource REST controller.
 *
 * @see BlackBoxResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MatrixApp.class)
public class BlackBoxResourceIntTest {

    private static final Integer DEFAULT_COLUMN = 1;
    private static final Integer UPDATED_COLUMN = 2;

    private static final Integer DEFAULT_ROW = 1;
    private static final Integer UPDATED_ROW = 2;

    @Autowired
    private BlackBoxRepository blackBoxRepository;

    @Autowired
    private BlackBoxService blackBoxService;

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

    private MockMvc restBlackBoxMockMvc;

    private BlackBox blackBox;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlackBoxResource blackBoxResource = new BlackBoxResource(blackBoxService);
        this.restBlackBoxMockMvc = MockMvcBuilders.standaloneSetup(blackBoxResource)
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
    public static BlackBox createEntity(EntityManager em) {
        BlackBox blackBox = new BlackBox()
            .column(DEFAULT_COLUMN)
            .row(DEFAULT_ROW);
        return blackBox;
    }

    @Before
    public void initTest() {
        blackBox = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlackBox() throws Exception {
        int databaseSizeBeforeCreate = blackBoxRepository.findAll().size();

        // Create the BlackBox
        restBlackBoxMockMvc.perform(post("/api/black-boxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blackBox)))
            .andExpect(status().isCreated());

        // Validate the BlackBox in the database
        List<BlackBox> blackBoxList = blackBoxRepository.findAll();
        assertThat(blackBoxList).hasSize(databaseSizeBeforeCreate + 1);
        BlackBox testBlackBox = blackBoxList.get(blackBoxList.size() - 1);
        assertThat(testBlackBox.getColumn()).isEqualTo(DEFAULT_COLUMN);
        assertThat(testBlackBox.getRow()).isEqualTo(DEFAULT_ROW);
    }

    @Test
    @Transactional
    public void createBlackBoxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blackBoxRepository.findAll().size();

        // Create the BlackBox with an existing ID
        blackBox.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlackBoxMockMvc.perform(post("/api/black-boxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blackBox)))
            .andExpect(status().isBadRequest());

        // Validate the BlackBox in the database
        List<BlackBox> blackBoxList = blackBoxRepository.findAll();
        assertThat(blackBoxList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBlackBoxes() throws Exception {
        // Initialize the database
        blackBoxRepository.saveAndFlush(blackBox);

        // Get all the blackBoxList
        restBlackBoxMockMvc.perform(get("/api/black-boxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blackBox.getId().intValue())))
            .andExpect(jsonPath("$.[*].column").value(hasItem(DEFAULT_COLUMN)))
            .andExpect(jsonPath("$.[*].row").value(hasItem(DEFAULT_ROW)));
    }
    
    @Test
    @Transactional
    public void getBlackBox() throws Exception {
        // Initialize the database
        blackBoxRepository.saveAndFlush(blackBox);

        // Get the blackBox
        restBlackBoxMockMvc.perform(get("/api/black-boxes/{id}", blackBox.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blackBox.getId().intValue()))
            .andExpect(jsonPath("$.column").value(DEFAULT_COLUMN))
            .andExpect(jsonPath("$.row").value(DEFAULT_ROW));
    }

    @Test
    @Transactional
    public void getNonExistingBlackBox() throws Exception {
        // Get the blackBox
        restBlackBoxMockMvc.perform(get("/api/black-boxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlackBox() throws Exception {
        // Initialize the database
        blackBoxService.save(blackBox);

        int databaseSizeBeforeUpdate = blackBoxRepository.findAll().size();

        // Update the blackBox
        BlackBox updatedBlackBox = blackBoxRepository.findById(blackBox.getId()).get();
        // Disconnect from session so that the updates on updatedBlackBox are not directly saved in db
        em.detach(updatedBlackBox);
        updatedBlackBox
            .column(UPDATED_COLUMN)
            .row(UPDATED_ROW);

        restBlackBoxMockMvc.perform(put("/api/black-boxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlackBox)))
            .andExpect(status().isOk());

        // Validate the BlackBox in the database
        List<BlackBox> blackBoxList = blackBoxRepository.findAll();
        assertThat(blackBoxList).hasSize(databaseSizeBeforeUpdate);
        BlackBox testBlackBox = blackBoxList.get(blackBoxList.size() - 1);
        assertThat(testBlackBox.getColumn()).isEqualTo(UPDATED_COLUMN);
        assertThat(testBlackBox.getRow()).isEqualTo(UPDATED_ROW);
    }

    @Test
    @Transactional
    public void updateNonExistingBlackBox() throws Exception {
        int databaseSizeBeforeUpdate = blackBoxRepository.findAll().size();

        // Create the BlackBox

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlackBoxMockMvc.perform(put("/api/black-boxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blackBox)))
            .andExpect(status().isBadRequest());

        // Validate the BlackBox in the database
        List<BlackBox> blackBoxList = blackBoxRepository.findAll();
        assertThat(blackBoxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBlackBox() throws Exception {
        // Initialize the database
        blackBoxService.save(blackBox);

        int databaseSizeBeforeDelete = blackBoxRepository.findAll().size();

        // Delete the blackBox
        restBlackBoxMockMvc.perform(delete("/api/black-boxes/{id}", blackBox.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BlackBox> blackBoxList = blackBoxRepository.findAll();
        assertThat(blackBoxList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlackBox.class);
        BlackBox blackBox1 = new BlackBox();
        blackBox1.setId(1L);
        BlackBox blackBox2 = new BlackBox();
        blackBox2.setId(blackBox1.getId());
        assertThat(blackBox1).isEqualTo(blackBox2);
        blackBox2.setId(2L);
        assertThat(blackBox1).isNotEqualTo(blackBox2);
        blackBox1.setId(null);
        assertThat(blackBox1).isNotEqualTo(blackBox2);
    }
}
