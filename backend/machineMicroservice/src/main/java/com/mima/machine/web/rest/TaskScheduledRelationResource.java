package com.mima.machine.web.rest;
import com.mima.machine.service.TaskScheduledRelationService;
import com.mima.machine.web.rest.errors.BadRequestAlertException;
import com.mima.machine.web.rest.util.HeaderUtil;
import com.mima.machine.service.dto.TaskScheduledRelationDTO;
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
 * REST controller for managing TaskScheduledRelation.
 */
@RestController
@RequestMapping("/api")
public class TaskScheduledRelationResource {

    private final Logger log = LoggerFactory.getLogger(TaskScheduledRelationResource.class);

    private static final String ENTITY_NAME = "machineMicroserviceTaskScheduledRelation";

    private final TaskScheduledRelationService taskScheduledRelationService;

    public TaskScheduledRelationResource(TaskScheduledRelationService taskScheduledRelationService) {
        this.taskScheduledRelationService = taskScheduledRelationService;
    }

    /**
     * POST  /task-scheduled-relations : Create a new taskScheduledRelation.
     *
     * @param taskScheduledRelationDTO the taskScheduledRelationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new taskScheduledRelationDTO, or with status 400 (Bad Request) if the taskScheduledRelation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/task-scheduled-relations")
    public ResponseEntity<TaskScheduledRelationDTO> createTaskScheduledRelation(@RequestBody TaskScheduledRelationDTO taskScheduledRelationDTO) throws URISyntaxException {
        log.debug("REST request to save TaskScheduledRelation : {}", taskScheduledRelationDTO);
        if (taskScheduledRelationDTO.getId() != null) {
            throw new BadRequestAlertException("A new taskScheduledRelation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskScheduledRelationDTO result = taskScheduledRelationService.save(taskScheduledRelationDTO);
        return ResponseEntity.created(new URI("/api/task-scheduled-relations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /task-scheduled-relations : Updates an existing taskScheduledRelation.
     *
     * @param taskScheduledRelationDTO the taskScheduledRelationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated taskScheduledRelationDTO,
     * or with status 400 (Bad Request) if the taskScheduledRelationDTO is not valid,
     * or with status 500 (Internal Server Error) if the taskScheduledRelationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/task-scheduled-relations")
    public ResponseEntity<TaskScheduledRelationDTO> updateTaskScheduledRelation(@RequestBody TaskScheduledRelationDTO taskScheduledRelationDTO) throws URISyntaxException {
        log.debug("REST request to update TaskScheduledRelation : {}", taskScheduledRelationDTO);
        if (taskScheduledRelationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TaskScheduledRelationDTO result = taskScheduledRelationService.save(taskScheduledRelationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, taskScheduledRelationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /task-scheduled-relations : get all the taskScheduledRelations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of taskScheduledRelations in body
     */
    @GetMapping("/task-scheduled-relations")
    public List<TaskScheduledRelationDTO> getAllTaskScheduledRelations() {
        log.debug("REST request to get all TaskScheduledRelations");
        return taskScheduledRelationService.findAll();
    }

    /**
     * GET  /task-scheduled-relations/:id : get the "id" taskScheduledRelation.
     *
     * @param id the id of the taskScheduledRelationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the taskScheduledRelationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/task-scheduled-relations/{id}")
    public ResponseEntity<TaskScheduledRelationDTO> getTaskScheduledRelation(@PathVariable Long id) {
        log.debug("REST request to get TaskScheduledRelation : {}", id);
        Optional<TaskScheduledRelationDTO> taskScheduledRelationDTO = taskScheduledRelationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskScheduledRelationDTO);
    }

    /**
     * DELETE  /task-scheduled-relations/:id : delete the "id" taskScheduledRelation.
     *
     * @param id the id of the taskScheduledRelationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/task-scheduled-relations/{id}")
    public ResponseEntity<Void> deleteTaskScheduledRelation(@PathVariable Long id) {
        log.debug("REST request to delete TaskScheduledRelation : {}", id);
        taskScheduledRelationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/task-scheduled-relations-by-task-scheduled/{id}")
    public List<TaskScheduledRelationDTO> getAllTaskScheduledRelationsByTaskScheduled(@PathVariable Long id){
    	log.debug("REST request to get TaskScheduledRelation By TaskScheduled : {}", id);
    	return taskScheduledRelationService.findAllByTaskScheduled(id);
    }
}
