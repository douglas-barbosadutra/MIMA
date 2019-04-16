package com.mima.matrix.web.rest;
import com.mima.matrix.domain.BlackBox;
import com.mima.matrix.service.BlackBoxService;
import com.mima.matrix.web.rest.errors.BadRequestAlertException;
import com.mima.matrix.web.rest.util.HeaderUtil;
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
 * REST controller for managing BlackBox.
 */
@RestController
@RequestMapping("/api")
public class BlackBoxResource {

    private final Logger log = LoggerFactory.getLogger(BlackBoxResource.class);

    private static final String ENTITY_NAME = "matrixBlackBox";

    private final BlackBoxService blackBoxService;

    public BlackBoxResource(BlackBoxService blackBoxService) {
        this.blackBoxService = blackBoxService;
    }

    /**
     * POST  /black-boxes : Create a new blackBox.
     *
     * @param blackBox the blackBox to create
     * @return the ResponseEntity with status 201 (Created) and with body the new blackBox, or with status 400 (Bad Request) if the blackBox has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/black-boxes")
    public ResponseEntity<BlackBox> createBlackBox(@RequestBody BlackBox blackBox) throws URISyntaxException {
        log.debug("REST request to save BlackBox : {}", blackBox);
        if (blackBox.getId() != null) {
            throw new BadRequestAlertException("A new blackBox cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BlackBox result = blackBoxService.save(blackBox);
        return ResponseEntity.created(new URI("/api/black-boxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /black-boxes : Updates an existing blackBox.
     *
     * @param blackBox the blackBox to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated blackBox,
     * or with status 400 (Bad Request) if the blackBox is not valid,
     * or with status 500 (Internal Server Error) if the blackBox couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/black-boxes")
    public ResponseEntity<BlackBox> updateBlackBox(@RequestBody BlackBox blackBox) throws URISyntaxException {
        log.debug("REST request to update BlackBox : {}", blackBox);
        if (blackBox.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BlackBox result = blackBoxService.save(blackBox);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, blackBox.getId().toString()))
            .body(result);
    }

    /**
     * GET  /black-boxes : get all the blackBoxes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of blackBoxes in body
     */
    @GetMapping("/black-boxes")
    public List<BlackBox> getAllBlackBoxes() {
        log.debug("REST request to get all BlackBoxes");
        return blackBoxService.findAll();
    }

    /**
     * GET  /black-boxes/:id : get the "id" blackBox.
     *
     * @param id the id of the blackBox to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the blackBox, or with status 404 (Not Found)
     */
    @GetMapping("/black-boxes/{id}")
    public ResponseEntity<BlackBox> getBlackBox(@PathVariable Long id) {
        log.debug("REST request to get BlackBox : {}", id);
        Optional<BlackBox> blackBox = blackBoxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blackBox);
    }

    /**
     * DELETE  /black-boxes/:id : delete the "id" blackBox.
     *
     * @param id the id of the blackBox to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/black-boxes/{id}")
    public ResponseEntity<Void> deleteBlackBox(@PathVariable Long id) {
        log.debug("REST request to delete BlackBox : {}", id);
        blackBoxService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
