package com.mima.matrix.service.impl;

import com.mima.matrix.service.MatrixService;
import com.mima.matrix.domain.Matrix;
import com.mima.matrix.repository.MatrixRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Matrix.
 */
@Service
@Transactional
public class MatrixServiceImpl implements MatrixService {

    private final Logger log = LoggerFactory.getLogger(MatrixServiceImpl.class);

    private final MatrixRepository matrixRepository;

    public MatrixServiceImpl(MatrixRepository matrixRepository) {
        this.matrixRepository = matrixRepository;
    }

    /**
     * Save a matrix.
     *
     * @param matrix the entity to save
     * @return the persisted entity
     */
    @Override
    public Matrix save(Matrix matrix) {
        log.debug("Request to save Matrix : {}", matrix);
        return matrixRepository.save(matrix);
    }

    /**
     * Get all the matrices.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Matrix> findAll() {
        log.debug("Request to get all Matrices");
        return matrixRepository.findAll();
    }


    /**
     * Get one matrix by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Matrix> findOne(Long id) {
        log.debug("Request to get Matrix : {}", id);
        return matrixRepository.findById(id);
    }

    /**
     * Delete the matrix by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Matrix : {}", id);
        matrixRepository.deleteById(id);
    }
}
