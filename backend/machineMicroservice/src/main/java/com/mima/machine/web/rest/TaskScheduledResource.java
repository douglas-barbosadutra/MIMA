package com.mima.machine.web.rest;
import com.mima.machine.service.TaskScheduledService;
import com.mima.machine.web.rest.errors.BadRequestAlertException;
import com.mima.machine.web.rest.util.HeaderUtil;
import com.mima.machine.service.dto.NodeDTO;
import com.mima.machine.service.dto.TaskScheduledDTO;
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
 * REST controller for managing TaskScheduled.
 */
@RestController
@RequestMapping("/api")
public class TaskScheduledResource {

    private final Logger log = LoggerFactory.getLogger(TaskScheduledResource.class);

    private static final String ENTITY_NAME = "machineMicroserviceTaskScheduled";

    private final TaskScheduledService taskScheduledService;

    public TaskScheduledResource(TaskScheduledService taskScheduledService) {
        this.taskScheduledService = taskScheduledService;
    }

    /**
     * POST  /task-scheduleds : Create a new taskScheduled.
     *
     * @param taskScheduledDTO the taskScheduledDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new taskScheduledDTO, or with status 400 (Bad Request) if the taskScheduled has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/task-scheduleds")
    public ResponseEntity<TaskScheduledDTO> createTaskScheduled(@RequestBody TaskScheduledDTO taskScheduledDTO) throws URISyntaxException {
        log.debug("REST request to save TaskScheduled : {}", taskScheduledDTO);
        if (taskScheduledDTO.getId() != null) {
            throw new BadRequestAlertException("A new taskScheduled cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskScheduledDTO result = taskScheduledService.save(taskScheduledDTO);
        return ResponseEntity.created(new URI("/api/task-scheduleds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /task-scheduleds : Updates an existing taskScheduled.
     *
     * @param taskScheduledDTO the taskScheduledDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated taskScheduledDTO,
     * or with status 400 (Bad Request) if the taskScheduledDTO is not valid,
     * or with status 500 (Internal Server Error) if the taskScheduledDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/task-scheduleds")
    public ResponseEntity<TaskScheduledDTO> updateTaskScheduled(@RequestBody TaskScheduledDTO taskScheduledDTO) throws URISyntaxException {
        log.debug("REST request to update TaskScheduled : {}", taskScheduledDTO);
        if (taskScheduledDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TaskScheduledDTO result = taskScheduledService.save(taskScheduledDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, taskScheduledDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /task-scheduleds : get all the taskScheduleds.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of taskScheduleds in body
     */
    @GetMapping("/task-scheduleds")
    public List<TaskScheduledDTO> getAllTaskScheduleds(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all TaskScheduleds");
        return taskScheduledService.findAll();
    }

    /**
     * GET  /task-scheduleds/:id : get the "id" taskScheduled.
     *
     * @param id the id of the taskScheduledDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the taskScheduledDTO, or with status 404 (Not Found)
     */
    @GetMapping("/task-scheduleds/{id}")
    public ResponseEntity<TaskScheduledDTO> getTaskScheduled(@PathVariable Long id) {
        log.debug("REST request to get TaskScheduled : {}", id);
        Optional<TaskScheduledDTO> taskScheduledDTO = taskScheduledService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskScheduledDTO);
    }

    /**
     * DELETE  /task-scheduleds/:id : delete the "id" taskScheduled.
     *
     * @param id the id of the taskScheduledDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/task-scheduleds/{id}")
    public ResponseEntity<Void> deleteTaskScheduled(@PathVariable Long id) {
        log.debug("REST request to delete TaskScheduled : {}", id);
        taskScheduledService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @PostMapping("/task-scheduleds/createNode")
    public ResponseEntity<NodeDTO> createNode(NodeDTO node) throws URISyntaxException{
    	 log.debug("REST request to save TaskScheduled : {}", node);
    	 return ResponseEntity.created(new URI("/api/task-scheduleds/" + node.getId()))
    	            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, node.getId().toString()))
    	            .body(node);
    }
    
}
