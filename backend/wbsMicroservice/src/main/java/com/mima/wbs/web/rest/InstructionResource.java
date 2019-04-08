package com.mima.wbs.web.rest;
import com.codahale.metrics.annotation.Timed;
import com.mima.wbs.service.InstructionService;
import com.mima.wbs.web.rest.errors.BadRequestAlertException;
import com.mima.wbs.web.rest.util.HeaderUtil;
import com.mima.wbs.service.dto.InstructionDTO;
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
 * REST controller for managing Instruction.
 */
@RestController
@RequestMapping("/api")
public class InstructionResource {

    private final Logger log = LoggerFactory.getLogger(InstructionResource.class);

    private static final String ENTITY_NAME = "wbsMicroserviceInstruction";

    private final InstructionService instructionService;

    public InstructionResource(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    /**
     * POST  /instructions : Create a new instruction.
     *
     * @param instructionDTO the instructionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new instructionDTO, or with status 400 (Bad Request) if the instruction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/instructions")
    public ResponseEntity<InstructionDTO> createInstruction(@RequestBody InstructionDTO instructionDTO) throws URISyntaxException {
        log.debug("REST request to save Instruction : {}", instructionDTO);
        if (instructionDTO.getId() != null) {
            throw new BadRequestAlertException("A new instruction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InstructionDTO result = instructionService.save(instructionDTO);
        return ResponseEntity.created(new URI("/api/instructions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /instructions : Updates an existing instruction.
     *
     * @param instructionDTO the instructionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated instructionDTO,
     * or with status 400 (Bad Request) if the instructionDTO is not valid,
     * or with status 500 (Internal Server Error) if the instructionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/instructions")
    public ResponseEntity<InstructionDTO> updateInstruction(@RequestBody InstructionDTO instructionDTO) throws URISyntaxException {
        log.debug("REST request to update Instruction : {}", instructionDTO);
        if (instructionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InstructionDTO result = instructionService.save(instructionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, instructionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /instructions : get all the instructions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of instructions in body
     */
    @GetMapping("/instructions")
    public List<InstructionDTO> getAllInstructions() {
        log.debug("REST request to get all Instructions");
        return instructionService.findAll();
    }

    /**
     * GET  /instructions/:id : get the "id" instruction.
     *
     * @param id the id of the instructionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the instructionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/instructions/{id}")
    public ResponseEntity<InstructionDTO> getInstruction(@PathVariable Long id) {
        log.debug("REST request to get Instruction : {}", id);
        Optional<InstructionDTO> instructionDTO = instructionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(instructionDTO);
    }

    /**
     * DELETE  /instructions/:id : delete the "id" instruction.
     *
     * @param id the id of the instructionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/instructions/{id}")
    public ResponseEntity<Void> deleteInstruction(@PathVariable Long id) {
        log.debug("REST request to delete Instruction : {}", id);
        instructionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/instructions/{idTask}")
    @Timed
    public List<InstructionDTO> showInstruction(@PathVariable Long idTask){
    	return instructionService.getAllInstructionByIdTask(idTask);
    }
}
