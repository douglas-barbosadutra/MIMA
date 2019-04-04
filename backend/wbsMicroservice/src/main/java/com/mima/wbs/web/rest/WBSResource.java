package com.mima.wbs.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mima.wbs.service.WBSService;
import com.mima.wbs.web.rest.errors.BadRequestAlertException;
import com.mima.wbs.web.rest.util.HeaderUtil;
import com.mima.wbs.service.dto.WBSDTO;
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
 * REST controller for managing WBS.
 */
@RestController
@RequestMapping("/api")
public class WBSResource {

    private final Logger log = LoggerFactory.getLogger(WBSResource.class);

    private static final String ENTITY_NAME = "wBS";

    private final WBSService wBSService;

    public WBSResource(WBSService wBSService) {
        this.wBSService = wBSService;
    }

    /**
     * POST  /wbs : Create a new wBS.
     *
     * @param wBSDTO the wBSDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wBSDTO, or with status 400 (Bad Request) if the wBS has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wbs")
    @Timed
    public ResponseEntity<WBSDTO> createWBS(@RequestBody WBSDTO wBSDTO) throws URISyntaxException {
        log.debug("REST request to save WBS : {}", wBSDTO);
        if (wBSDTO.getId() != null) {
            throw new BadRequestAlertException("A new wBS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WBSDTO result = wBSService.save(wBSDTO);
        return ResponseEntity.created(new URI("/api/wbs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wbs : Updates an existing wBS.
     *
     * @param wBSDTO the wBSDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wBSDTO,
     * or with status 400 (Bad Request) if the wBSDTO is not valid,
     * or with status 500 (Internal Server Error) if the wBSDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wbs")
    @Timed
    public ResponseEntity<WBSDTO> updateWBS(@RequestBody WBSDTO wBSDTO) throws URISyntaxException {
        log.debug("REST request to update WBS : {}", wBSDTO);
        if (wBSDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WBSDTO result = wBSService.save(wBSDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wBSDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wbs : get all the wBS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of wBS in body
     */
    @GetMapping("/wbs")
    @Timed
    public List<WBSDTO> getAllWBS() {
        log.debug("REST request to get all WBS");
        return wBSService.findAll();
    }

    /**
     * GET  /wbs/:id : get the "id" wBS.
     *
     * @param id the id of the wBSDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wBSDTO, or with status 404 (Not Found)
     */
    @GetMapping("/wbs/{id}")
    @Timed
    public ResponseEntity<WBSDTO> getWBS(@PathVariable Long id) {
        log.debug("REST request to get WBS : {}", id);
        Optional<WBSDTO> wBSDTO = wBSService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wBSDTO);
    }

    /**
     * DELETE  /wbs/:id : delete the "id" wBS.
     *
     * @param id the id of the wBSDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wbs/{id}")
    @Timed
    public ResponseEntity<Void> deleteWBS(@PathVariable Long id) {
        log.debug("REST request to delete WBS : {}", id);
        wBSService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/wbsByUser/{id}")
    @Timed
    public List<WBSDTO> getWBSByIdUser(@PathVariable int id) {
        log.debug("REST request to get WBS : {}", id);
       return wBSService.getWBSByIdUser(id);
    }
}
