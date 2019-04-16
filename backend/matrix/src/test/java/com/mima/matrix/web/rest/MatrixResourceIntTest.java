package com.mima.matrix.web.rest;

import com.mima.matrix.MatrixApp;

import com.mima.matrix.domain.Matrix;
import com.mima.matrix.repository.MatrixRepository;
import com.mima.matrix.service.MatrixService;
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
 * Test class for the MatrixResource REST controller.
 *
 * @see MatrixResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MatrixApp.class)
public class MatrixResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM_COLUMNS = 1;
    private static final Integer UPDATED_NUM_COLUMNS = 2;

    private static final Integer DEFAULT_NUM_ROWS = 1;
    private static final Integer UPDATED_NUM_ROWS = 2;

    @Autowired
    private MatrixRepository matrixRepository;

    @Autowired
    private MatrixService matrixService;

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

    private MockMvc restMatrixMockMvc;

    private Matrix matrix;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MatrixResource matrixResource = new MatrixResource(matrixService);
        this.restMatrixMockMvc = MockMvcBuilders.standaloneSetup(matrixResource)
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
    public static Matrix createEntity(EntityManager em) {
        Matrix matrix = new Matrix()
            .name(DEFAULT_NAME)
            .numColumns(DEFAULT_NUM_COLUMNS)
            .numRows(DEFAULT_NUM_ROWS);
        return matrix;
    }

    @Before
    public void initTest() {
        matrix = createEntity(em);
    }

    @Test
    @Transactional
    public void createMatrix() throws Exception {
        int databaseSizeBeforeCreate = matrixRepository.findAll().size();

        // Create the Matrix
        restMatrixMockMvc.perform(post("/api/matrices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matrix)))
            .andExpect(status().isCreated());

        // Validate the Matrix in the database
        List<Matrix> matrixList = matrixRepository.findAll();
        assertThat(matrixList).hasSize(databaseSizeBeforeCreate + 1);
        Matrix testMatrix = matrixList.get(matrixList.size() - 1);
        assertThat(testMatrix.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMatrix.getNumColumns()).isEqualTo(DEFAULT_NUM_COLUMNS);
        assertThat(testMatrix.getNumRows()).isEqualTo(DEFAULT_NUM_ROWS);
    }

    @Test
    @Transactional
    public void createMatrixWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = matrixRepository.findAll().size();

        // Create the Matrix with an existing ID
        matrix.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMatrixMockMvc.perform(post("/api/matrices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matrix)))
            .andExpect(status().isBadRequest());

        // Validate the Matrix in the database
        List<Matrix> matrixList = matrixRepository.findAll();
        assertThat(matrixList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMatrices() throws Exception {
        // Initialize the database
        matrixRepository.saveAndFlush(matrix);

        // Get all the matrixList
        restMatrixMockMvc.perform(get("/api/matrices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(matrix.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].numColumns").value(hasItem(DEFAULT_NUM_COLUMNS)))
            .andExpect(jsonPath("$.[*].numRows").value(hasItem(DEFAULT_NUM_ROWS)));
    }
    
    @Test
    @Transactional
    public void getMatrix() throws Exception {
        // Initialize the database
        matrixRepository.saveAndFlush(matrix);

        // Get the matrix
        restMatrixMockMvc.perform(get("/api/matrices/{id}", matrix.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(matrix.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.numColumns").value(DEFAULT_NUM_COLUMNS))
            .andExpect(jsonPath("$.numRows").value(DEFAULT_NUM_ROWS));
    }

    @Test
    @Transactional
    public void getNonExistingMatrix() throws Exception {
        // Get the matrix
        restMatrixMockMvc.perform(get("/api/matrices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMatrix() throws Exception {
        // Initialize the database
        matrixService.save(matrix);

        int databaseSizeBeforeUpdate = matrixRepository.findAll().size();

        // Update the matrix
        Matrix updatedMatrix = matrixRepository.findById(matrix.getId()).get();
        // Disconnect from session so that the updates on updatedMatrix are not directly saved in db
        em.detach(updatedMatrix);
        updatedMatrix
            .name(UPDATED_NAME)
            .numColumns(UPDATED_NUM_COLUMNS)
            .numRows(UPDATED_NUM_ROWS);

        restMatrixMockMvc.perform(put("/api/matrices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMatrix)))
            .andExpect(status().isOk());

        // Validate the Matrix in the database
        List<Matrix> matrixList = matrixRepository.findAll();
        assertThat(matrixList).hasSize(databaseSizeBeforeUpdate);
        Matrix testMatrix = matrixList.get(matrixList.size() - 1);
        assertThat(testMatrix.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMatrix.getNumColumns()).isEqualTo(UPDATED_NUM_COLUMNS);
        assertThat(testMatrix.getNumRows()).isEqualTo(UPDATED_NUM_ROWS);
    }

    @Test
    @Transactional
    public void updateNonExistingMatrix() throws Exception {
        int databaseSizeBeforeUpdate = matrixRepository.findAll().size();

        // Create the Matrix

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMatrixMockMvc.perform(put("/api/matrices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matrix)))
            .andExpect(status().isBadRequest());

        // Validate the Matrix in the database
        List<Matrix> matrixList = matrixRepository.findAll();
        assertThat(matrixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMatrix() throws Exception {
        // Initialize the database
        matrixService.save(matrix);

        int databaseSizeBeforeDelete = matrixRepository.findAll().size();

        // Delete the matrix
        restMatrixMockMvc.perform(delete("/api/matrices/{id}", matrix.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Matrix> matrixList = matrixRepository.findAll();
        assertThat(matrixList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Matrix.class);
        Matrix matrix1 = new Matrix();
        matrix1.setId(1L);
        Matrix matrix2 = new Matrix();
        matrix2.setId(matrix1.getId());
        assertThat(matrix1).isEqualTo(matrix2);
        matrix2.setId(2L);
        assertThat(matrix1).isNotEqualTo(matrix2);
        matrix1.setId(null);
        assertThat(matrix1).isNotEqualTo(matrix2);
    }
}
