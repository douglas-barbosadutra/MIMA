package com.mima.wbs.web.rest;

import com.mima.wbs.WbsApp;

import com.mima.wbs.domain.Manufacturing;
import com.mima.wbs.repository.ManufacturingRepository;
import com.mima.wbs.service.ManufacturingService;
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
 * Test class for the ManufacturingResource REST controller.
 *
 * @see ManufacturingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WbsApp.class)
public class ManufacturingResourceIntTest {

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    @Autowired
    private ManufacturingRepository manufacturingRepository;

    @Autowired
    private ManufacturingService manufacturingService;

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

    private MockMvc restManufacturingMockMvc;

    private Manufacturing manufacturing;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ManufacturingResource manufacturingResource = new ManufacturingResource(manufacturingService);
        this.restManufacturingMockMvc = MockMvcBuilders.standaloneSetup(manufacturingResource)
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
    public static Manufacturing createEntity(EntityManager em) {
        Manufacturing manufacturing = new Manufacturing()
            .duration(DEFAULT_DURATION);
        return manufacturing;
    }

    @Before
    public void initTest() {
        manufacturing = createEntity(em);
    }

    @Test
    @Transactional
    public void createManufacturing() throws Exception {
        int databaseSizeBeforeCreate = manufacturingRepository.findAll().size();

        // Create the Manufacturing
        restManufacturingMockMvc.perform(post("/api/manufacturings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturing)))
            .andExpect(status().isCreated());

        // Validate the Manufacturing in the database
        List<Manufacturing> manufacturingList = manufacturingRepository.findAll();
        assertThat(manufacturingList).hasSize(databaseSizeBeforeCreate + 1);
        Manufacturing testManufacturing = manufacturingList.get(manufacturingList.size() - 1);
        assertThat(testManufacturing.getDuration()).isEqualTo(DEFAULT_DURATION);
    }

    @Test
    @Transactional
    public void createManufacturingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manufacturingRepository.findAll().size();

        // Create the Manufacturing with an existing ID
        manufacturing.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManufacturingMockMvc.perform(post("/api/manufacturings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturing)))
            .andExpect(status().isBadRequest());

        // Validate the Manufacturing in the database
        List<Manufacturing> manufacturingList = manufacturingRepository.findAll();
        assertThat(manufacturingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllManufacturings() throws Exception {
        // Initialize the database
        manufacturingRepository.saveAndFlush(manufacturing);

        // Get all the manufacturingList
        restManufacturingMockMvc.perform(get("/api/manufacturings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manufacturing.getId().intValue())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)));
    }
    
    @Test
    @Transactional
    public void getManufacturing() throws Exception {
        // Initialize the database
        manufacturingRepository.saveAndFlush(manufacturing);

        // Get the manufacturing
        restManufacturingMockMvc.perform(get("/api/manufacturings/{id}", manufacturing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(manufacturing.getId().intValue()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION));
    }

    @Test
    @Transactional
    public void getNonExistingManufacturing() throws Exception {
        // Get the manufacturing
        restManufacturingMockMvc.perform(get("/api/manufacturings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManufacturing() throws Exception {
        // Initialize the database
        manufacturingService.save(manufacturing);

        int databaseSizeBeforeUpdate = manufacturingRepository.findAll().size();

        // Update the manufacturing
        Manufacturing updatedManufacturing = manufacturingRepository.findById(manufacturing.getId()).get();
        // Disconnect from session so that the updates on updatedManufacturing are not directly saved in db
        em.detach(updatedManufacturing);
        updatedManufacturing
            .duration(UPDATED_DURATION);

        restManufacturingMockMvc.perform(put("/api/manufacturings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedManufacturing)))
            .andExpect(status().isOk());

        // Validate the Manufacturing in the database
        List<Manufacturing> manufacturingList = manufacturingRepository.findAll();
        assertThat(manufacturingList).hasSize(databaseSizeBeforeUpdate);
        Manufacturing testManufacturing = manufacturingList.get(manufacturingList.size() - 1);
        assertThat(testManufacturing.getDuration()).isEqualTo(UPDATED_DURATION);
    }

    @Test
    @Transactional
    public void updateNonExistingManufacturing() throws Exception {
        int databaseSizeBeforeUpdate = manufacturingRepository.findAll().size();

        // Create the Manufacturing

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManufacturingMockMvc.perform(put("/api/manufacturings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturing)))
            .andExpect(status().isBadRequest());

        // Validate the Manufacturing in the database
        List<Manufacturing> manufacturingList = manufacturingRepository.findAll();
        assertThat(manufacturingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteManufacturing() throws Exception {
        // Initialize the database
        manufacturingService.save(manufacturing);

        int databaseSizeBeforeDelete = manufacturingRepository.findAll().size();

        // Delete the manufacturing
        restManufacturingMockMvc.perform(delete("/api/manufacturings/{id}", manufacturing.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Manufacturing> manufacturingList = manufacturingRepository.findAll();
        assertThat(manufacturingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Manufacturing.class);
        Manufacturing manufacturing1 = new Manufacturing();
        manufacturing1.setId(1L);
        Manufacturing manufacturing2 = new Manufacturing();
        manufacturing2.setId(manufacturing1.getId());
        assertThat(manufacturing1).isEqualTo(manufacturing2);
        manufacturing2.setId(2L);
        assertThat(manufacturing1).isNotEqualTo(manufacturing2);
        manufacturing1.setId(null);
        assertThat(manufacturing1).isNotEqualTo(manufacturing2);
    }
}
