package com.mima.wbs.web.rest;

import com.mima.wbs.WbsMicroserviceApp;

import com.mima.wbs.domain.WBS;
import com.mima.wbs.repository.WBSRepository;
import com.mima.wbs.service.WBSService;
import com.mima.wbs.service.dto.WBSDTO;
import com.mima.wbs.service.mapper.WBSMapper;
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
 * Test class for the WBSResource REST controller.
 *
 * @see WBSResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WbsMicroserviceApp.class)
public class WBSResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_USER = 1;
    private static final Integer UPDATED_ID_USER = 2;

    @Autowired
    private WBSRepository wBSRepository;

    @Autowired
    private WBSMapper wBSMapper;

    @Autowired
    private WBSService wBSService;

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

    private MockMvc restWBSMockMvc;

    private WBS wBS;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WBSResource wBSResource = new WBSResource(wBSService);
        this.restWBSMockMvc = MockMvcBuilders.standaloneSetup(wBSResource)
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
    public static WBS createEntity(EntityManager em) {
        WBS wBS = new WBS()
            .name(DEFAULT_NAME)
            .idUser(DEFAULT_ID_USER);
        return wBS;
    }

    @Before
    public void initTest() {
        wBS = createEntity(em);
    }

    @Test
    @Transactional
    public void createWBS() throws Exception {
        int databaseSizeBeforeCreate = wBSRepository.findAll().size();

        // Create the WBS
        WBSDTO wBSDTO = wBSMapper.toDto(wBS);
        restWBSMockMvc.perform(post("/api/wbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wBSDTO)))
            .andExpect(status().isCreated());

        // Validate the WBS in the database
        List<WBS> wBSList = wBSRepository.findAll();
        assertThat(wBSList).hasSize(databaseSizeBeforeCreate + 1);
        WBS testWBS = wBSList.get(wBSList.size() - 1);
        assertThat(testWBS.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testWBS.getIdUser()).isEqualTo(DEFAULT_ID_USER);
    }

    @Test
    @Transactional
    public void createWBSWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wBSRepository.findAll().size();

        // Create the WBS with an existing ID
        wBS.setId(1L);
        WBSDTO wBSDTO = wBSMapper.toDto(wBS);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWBSMockMvc.perform(post("/api/wbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wBSDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WBS in the database
        List<WBS> wBSList = wBSRepository.findAll();
        assertThat(wBSList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllWBS() throws Exception {
        // Initialize the database
        wBSRepository.saveAndFlush(wBS);

        // Get all the wBSList
        restWBSMockMvc.perform(get("/api/wbs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wBS.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].idUser").value(hasItem(DEFAULT_ID_USER)));
    }
    
    @Test
    @Transactional
    public void getWBS() throws Exception {
        // Initialize the database
        wBSRepository.saveAndFlush(wBS);

        // Get the wBS
        restWBSMockMvc.perform(get("/api/wbs/{id}", wBS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(wBS.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.idUser").value(DEFAULT_ID_USER));
    }

    @Test
    @Transactional
    public void getNonExistingWBS() throws Exception {
        // Get the wBS
        restWBSMockMvc.perform(get("/api/wbs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWBS() throws Exception {
        // Initialize the database
        wBSRepository.saveAndFlush(wBS);

        int databaseSizeBeforeUpdate = wBSRepository.findAll().size();

        // Update the wBS
        WBS updatedWBS = wBSRepository.findById(wBS.getId()).get();
        // Disconnect from session so that the updates on updatedWBS are not directly saved in db
        em.detach(updatedWBS);
        updatedWBS
            .name(UPDATED_NAME)
            .idUser(UPDATED_ID_USER);
        WBSDTO wBSDTO = wBSMapper.toDto(updatedWBS);

        restWBSMockMvc.perform(put("/api/wbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wBSDTO)))
            .andExpect(status().isOk());

        // Validate the WBS in the database
        List<WBS> wBSList = wBSRepository.findAll();
        assertThat(wBSList).hasSize(databaseSizeBeforeUpdate);
        WBS testWBS = wBSList.get(wBSList.size() - 1);
        assertThat(testWBS.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWBS.getIdUser()).isEqualTo(UPDATED_ID_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingWBS() throws Exception {
        int databaseSizeBeforeUpdate = wBSRepository.findAll().size();

        // Create the WBS
        WBSDTO wBSDTO = wBSMapper.toDto(wBS);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWBSMockMvc.perform(put("/api/wbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wBSDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WBS in the database
        List<WBS> wBSList = wBSRepository.findAll();
        assertThat(wBSList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWBS() throws Exception {
        // Initialize the database
        wBSRepository.saveAndFlush(wBS);

        int databaseSizeBeforeDelete = wBSRepository.findAll().size();

        // Delete the wBS
        restWBSMockMvc.perform(delete("/api/wbs/{id}", wBS.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WBS> wBSList = wBSRepository.findAll();
        assertThat(wBSList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WBS.class);
        WBS wBS1 = new WBS();
        wBS1.setId(1L);
        WBS wBS2 = new WBS();
        wBS2.setId(wBS1.getId());
        assertThat(wBS1).isEqualTo(wBS2);
        wBS2.setId(2L);
        assertThat(wBS1).isNotEqualTo(wBS2);
        wBS1.setId(null);
        assertThat(wBS1).isNotEqualTo(wBS2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WBSDTO.class);
        WBSDTO wBSDTO1 = new WBSDTO();
        wBSDTO1.setId(1L);
        WBSDTO wBSDTO2 = new WBSDTO();
        assertThat(wBSDTO1).isNotEqualTo(wBSDTO2);
        wBSDTO2.setId(wBSDTO1.getId());
        assertThat(wBSDTO1).isEqualTo(wBSDTO2);
        wBSDTO2.setId(2L);
        assertThat(wBSDTO1).isNotEqualTo(wBSDTO2);
        wBSDTO1.setId(null);
        assertThat(wBSDTO1).isNotEqualTo(wBSDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(wBSMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(wBSMapper.fromId(null)).isNull();
    }
}
