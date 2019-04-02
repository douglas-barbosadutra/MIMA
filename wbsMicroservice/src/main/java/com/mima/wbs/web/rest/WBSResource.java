package com.mima.wbs.web.rest;
import com.mima.wbs.domain.WBS;
import com.mima.wbs.service.WBSService;
import com.mima.wbs.web.rest.errors.BadRequestAlertException;
import com.mima.wbs.web.rest.util.HeaderUtil;
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

    private static final String ENTITY_NAME = "wbsWbs";

    private final WBSService wBSService;

    public WBSResource(WBSService wBSService) {
        this.wBSService = wBSService;
    }

    /**
     * POST  /wbs : Create a new wBS.
     *
     * @param wBS the wBS to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wBS, or with status 400 (Bad Request) if the wBS has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wbs")
    public ResponseEntity<WBS> createWBS(@RequestBody WBS wBS) throws URISyntaxException {
        log.debug("REST request to save WBS : {}", wBS);
        if (wBS.getId() != null) {
            throw new BadRequestAlertException("A new wBS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WBS result = wBSService.save(wBS);
        return ResponseEntity.created(new URI("/api/wbs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wbs : Updates an existing wBS.
     *
     * @param wBS the wBS to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wBS,
     * or with status 400 (Bad Request) if the wBS is not valid,
     * or with status 500 (Internal Server Error) if the wBS couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wbs")
    public ResponseEntity<WBS> updateWBS(@RequestBody WBS wBS) throws URISyntaxException {
        log.debug("REST request to update WBS : {}", wBS);
        if (wBS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WBS result = wBSService.save(wBS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wBS.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wbs : get all the wBS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of wBS in body
     */
    @GetMapping("/wbs")
    public List<WBS> getAllWBS() {
        log.debug("REST request to get all WBS");
        return wBSService.findAll();
    }

    /**
     * GET  /wbs/:id : get the "id" wBS.
     *
     * @param id the id of the wBS to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wBS, or with status 404 (Not Found)
     */
    @GetMapping("/wbs/{id}")
    public ResponseEntity<WBS> getWBS(@PathVariable Long id) {
        log.debug("REST request to get WBS : {}", id);
        Optional<WBS> wBS = wBSService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wBS);
    }

    /**
     * DELETE  /wbs/:id : delete the "id" wBS.
     *
     * @param id the id of the wBS to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wbs/{id}")
    public ResponseEntity<Void> deleteWBS(@PathVariable Long id) {
        log.debug("REST request to delete WBS : {}", id);
        wBSService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
