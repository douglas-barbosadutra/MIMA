package com.mima.matrix.web.rest;
import com.mima.matrix.domain.Matrix;
import com.mima.matrix.service.MatrixService;
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
 * REST controller for managing Matrix.
 */
@RestController
@RequestMapping("/api")
public class MatrixResource {

    private final Logger log = LoggerFactory.getLogger(MatrixResource.class);

    private static final String ENTITY_NAME = "matrixMatrix";

    private final MatrixService matrixService;

    public MatrixResource(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    /**
     * POST  /matrices : Create a new matrix.
     *
     * @param matrix the matrix to create
     * @return the ResponseEntity with status 201 (Created) and with body the new matrix, or with status 400 (Bad Request) if the matrix has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/matrices")
    public ResponseEntity<Matrix> createMatrix(@RequestBody Matrix matrix) throws URISyntaxException {
        log.debug("REST request to save Matrix : {}", matrix);
        if (matrix.getId() != null) {
            throw new BadRequestAlertException("A new matrix cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Matrix result = matrixService.save(matrix);
        return ResponseEntity.created(new URI("/api/matrices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /matrices : Updates an existing matrix.
     *
     * @param matrix the matrix to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated matrix,
     * or with status 400 (Bad Request) if the matrix is not valid,
     * or with status 500 (Internal Server Error) if the matrix couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/matrices")
    public ResponseEntity<Matrix> updateMatrix(@RequestBody Matrix matrix) throws URISyntaxException {
        log.debug("REST request to update Matrix : {}", matrix);
        if (matrix.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Matrix result = matrixService.save(matrix);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, matrix.getId().toString()))
            .body(result);
    }

    /**
     * GET  /matrices : get all the matrices.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of matrices in body
     */
    @GetMapping("/matrices")
    public List<Matrix> getAllMatrices() {
        log.debug("REST request to get all Matrices");
        return matrixService.findAll();
    }

    /**
     * GET  /matrices/:id : get the "id" matrix.
     *
     * @param id the id of the matrix to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the matrix, or with status 404 (Not Found)
     */
    @GetMapping("/matrices/{id}")
    public ResponseEntity<Matrix> getMatrix(@PathVariable Long id) {
        log.debug("REST request to get Matrix : {}", id);
        Optional<Matrix> matrix = matrixService.findOne(id);
        return ResponseUtil.wrapOrNotFound(matrix);
    }

    /**
     * DELETE  /matrices/:id : delete the "id" matrix.
     *
     * @param id the id of the matrix to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/matrices/{id}")
    public ResponseEntity<Void> deleteMatrix(@PathVariable Long id) {
        log.debug("REST request to delete Matrix : {}", id);
        matrixService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
