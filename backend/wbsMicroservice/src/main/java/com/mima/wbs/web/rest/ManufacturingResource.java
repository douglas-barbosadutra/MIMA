package com.mima.wbs.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mima.wbs.service.ManufacturingService;
import com.mima.wbs.web.rest.errors.BadRequestAlertException;
import com.mima.wbs.web.rest.util.HeaderUtil;
import com.mima.wbs.service.dto.ManufacturingDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Manufacturing.
 */
@RestController
@RequestMapping("/api")
public class ManufacturingResource {

    private final Logger log = LoggerFactory.getLogger(ManufacturingResource.class);

    private static final String ENTITY_NAME = "manufacturing";

    private final ManufacturingService manufacturingService;

    public ManufacturingResource(ManufacturingService manufacturingService) {
        this.manufacturingService = manufacturingService;
    }

    /**
     * POST  /manufacturings : Create a new manufacturing.
     *
     * @param manufacturingDTO the manufacturingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new manufacturingDTO, or with status 400 (Bad Request) if the manufacturing has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/manufacturings")
    @Timed
    public ResponseEntity<ManufacturingDTO> createManufacturing(@RequestBody ManufacturingDTO manufacturingDTO) throws URISyntaxException {
        log.debug("REST request to save Manufacturing : {}", manufacturingDTO);
        if (manufacturingDTO.getId() != null) {
            throw new BadRequestAlertException("A new manufacturing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManufacturingDTO result = manufacturingService.save(manufacturingDTO);
        return ResponseEntity.created(new URI("/api/manufacturings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /manufacturings : Updates an existing manufacturing.
     *
     * @param manufacturingDTO the manufacturingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated manufacturingDTO,
     * or with status 400 (Bad Request) if the manufacturingDTO is not valid,
     * or with status 500 (Internal Server Error) if the manufacturingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/manufacturings")
    @Timed
    public ResponseEntity<ManufacturingDTO> updateManufacturing(@RequestBody ManufacturingDTO manufacturingDTO) throws URISyntaxException {
        log.debug("REST request to update Manufacturing : {}", manufacturingDTO);
        if (manufacturingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ManufacturingDTO result = manufacturingService.save(manufacturingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, manufacturingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /manufacturings : get all the manufacturings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of manufacturings in body
     */
    @GetMapping("/manufacturings")
    @Timed
    public List<ManufacturingDTO> getAllManufacturings() {
        log.debug("REST request to get all Manufacturings");
        return manufacturingService.findAll();
    }

    /**
     * GET  /manufacturings/:id : get the "id" manufacturing.
     *
     * @param id the id of the manufacturingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the manufacturingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/manufacturings/{id}")
    @Timed
    public ResponseEntity<ManufacturingDTO> getManufacturing(@PathVariable Long id) {
        log.debug("REST request to get Manufacturing : {}", id);
        Optional<ManufacturingDTO> manufacturingDTO = manufacturingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(manufacturingDTO);
    }

    /**
     * DELETE  /manufacturings/:id : delete the "id" manufacturing.
     *
     * @param id the id of the manufacturingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/manufacturings/{id}")
    @Timed
    public ResponseEntity<Void> deleteManufacturing(@PathVariable Long id) {
        log.debug("REST request to delete Manufacturing : {}", id);
        manufacturingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
