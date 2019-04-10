package com.mima.machine.web.rest;
import com.mima.machine.service.MachineService;
import com.mima.machine.web.rest.errors.BadRequestAlertException;
import com.mima.machine.web.rest.util.HeaderUtil;
import com.mima.machine.service.dto.MachineDTO;
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
 * REST controller for managing Machine.
 */
@RestController
@RequestMapping("/api")
public class MachineResource {

    private final Logger log = LoggerFactory.getLogger(MachineResource.class);

    private static final String ENTITY_NAME = "machineMicroserviceMachine";

    private final MachineService machineService;

    public MachineResource(MachineService machineService) {
        this.machineService = machineService;
    }

    /**
     * POST  /machines : Create a new machine.
     *
     * @param machineDTO the machineDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new machineDTO, or with status 400 (Bad Request) if the machine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/machines")
    public ResponseEntity<MachineDTO> createMachine(@RequestBody MachineDTO machineDTO) throws URISyntaxException {
        log.debug("REST request to save Machine : {}", machineDTO);
        if (machineDTO.getId() != null) {
            throw new BadRequestAlertException("A new machine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MachineDTO result = machineService.save(machineDTO);
        return ResponseEntity.created(new URI("/api/machines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /machines : Updates an existing machine.
     *
     * @param machineDTO the machineDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated machineDTO,
     * or with status 400 (Bad Request) if the machineDTO is not valid,
     * or with status 500 (Internal Server Error) if the machineDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/machines")
    public ResponseEntity<MachineDTO> updateMachine(@RequestBody MachineDTO machineDTO) throws URISyntaxException {
        log.debug("REST request to update Machine : {}", machineDTO);
        if (machineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MachineDTO result = machineService.save(machineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, machineDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /machines : get all the machines.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of machines in body
     */
    @GetMapping("/machines")
    public List<MachineDTO> getAllMachines() {
        log.debug("REST request to get all Machines");
        return machineService.findAll();
    }

    /**
     * GET  /machines/:id : get the "id" machine.
     *
     * @param id the id of the machineDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the machineDTO, or with status 404 (Not Found)
     */
    @GetMapping("/machines/{id}")
    public ResponseEntity<MachineDTO> getMachine(@PathVariable Long id) {
        log.debug("REST request to get Machine : {}", id);
        Optional<MachineDTO> machineDTO = machineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(machineDTO);
    }

    /**
     * DELETE  /machines/:id : delete the "id" machine.
     *
     * @param id the id of the machineDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/machines/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable Long id) {
        log.debug("REST request to delete Machine : {}", id);
        machineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/allMachines/{id}")
    public List<MachineDTO> getAllMachineById(@PathVariable int id) {
        log.debug("REST request to get All Machine : {}", id);
        return machineService.findAllMachineByIdUser(id);
    }
    
    @GetMapping("/findAllMachineByUserId/{idUser}")
    public List<MachineDTO> findAllMachineByUserId(@PathVariable int idUser) {
		return machineService.TESTfindAllMachineByUserId(idUser); 
    }
}
