package com.mima.machine.web.rest;
import com.mima.machine.service.SchedulingService;
import com.mima.machine.web.rest.errors.BadRequestAlertException;
import com.mima.machine.web.rest.util.HeaderUtil;
import com.mima.machine.service.dto.SchedulingDTO;
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
 * REST controller for managing Scheduling.
 */
@RestController
@RequestMapping("/api")
public class SchedulingResource {

    private final Logger log = LoggerFactory.getLogger(SchedulingResource.class);

    private static final String ENTITY_NAME = "machineMicroserviceScheduling";

    private final SchedulingService schedulingService;

    public SchedulingResource(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    /**
     * POST  /schedulings : Create a new scheduling.
     *
     * @param schedulingDTO the schedulingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new schedulingDTO, or with status 400 (Bad Request) if the scheduling has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/schedulings")
    public ResponseEntity<SchedulingDTO> createScheduling(@RequestBody SchedulingDTO schedulingDTO) throws URISyntaxException {
        log.debug("REST request to save Scheduling : {}", schedulingDTO);
        if (schedulingDTO.getId() != null) {
            throw new BadRequestAlertException("A new scheduling cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SchedulingDTO result = schedulingService.save(schedulingDTO);
        return ResponseEntity.created(new URI("/api/schedulings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /schedulings : Updates an existing scheduling.
     *
     * @param schedulingDTO the schedulingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated schedulingDTO,
     * or with status 400 (Bad Request) if the schedulingDTO is not valid,
     * or with status 500 (Internal Server Error) if the schedulingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/schedulings")
    public ResponseEntity<SchedulingDTO> updateScheduling(@RequestBody SchedulingDTO schedulingDTO) throws URISyntaxException {
        log.debug("REST request to update Scheduling : {}", schedulingDTO);
        if (schedulingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SchedulingDTO result = schedulingService.save(schedulingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, schedulingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /schedulings : get all the schedulings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of schedulings in body
     */
    @GetMapping("/schedulings")
    public List<SchedulingDTO> getAllSchedulings() {
        log.debug("REST request to get all Schedulings");
        return schedulingService.findAll();
    }

    /**
     * GET  /schedulings/:id : get the "id" scheduling.
     *
     * @param id the id of the schedulingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the schedulingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/schedulings/{id}")
    public ResponseEntity<SchedulingDTO> getScheduling(@PathVariable Long id) {
        log.debug("REST request to get Scheduling : {}", id);
        Optional<SchedulingDTO> schedulingDTO = schedulingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(schedulingDTO);
    }

    /**
     * DELETE  /schedulings/:id : delete the "id" scheduling.
     *
     * @param id the id of the schedulingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/schedulings/{id}")
    public ResponseEntity<Void> deleteScheduling(@PathVariable Long id) {
        log.debug("REST request to delete Scheduling : {}", id);
        schedulingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/schedulingsByIdMachine/{idMachine}")
    public List<SchedulingDTO> getAllSchedulingsByIdMachine(@PathVariable Long idMachine) {
        log.debug("REST request to get all Schedulings by Machine "+idMachine);
        return schedulingService.findAllByIdMachine(idMachine);
    }
}
