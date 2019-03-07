package com.pCarpet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.OperationSchedulingConverter;
import com.pCarpet.dao.OperationSchedulingDAO;
import com.pCarpet.dto.OperationSchedulingDTO;
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.dto.TaskScheduledDTO;
import com.pCarpet.model.Scheduling;

@Service
public class OperationSchedulingService {

	OperationSchedulingDAO operationSchedulingDAO;
	TaskService taskService;

	@Autowired
	OperationSchedulingService(OperationSchedulingDAO operationSchedulingDAO, TaskService taskService) {
		this.operationSchedulingDAO = operationSchedulingDAO;
		this.taskService = taskService;
	}

	public void deleteOperationScheduling(int id, int idSchedulazione, int idMacchinario) {
		List<TaskScheduledDTO> listaTaskSchedultatiDTO = getTaskSchedulati(idSchedulazione, idMacchinario);
		int i = 0;
		while (listaTaskSchedultatiDTO.get(i).getIdOperationScheduling() != id)
			i++;
		i++;
		for (; i < listaTaskSchedultatiDTO.size(); i++) {
			OperationSchedulingDTO osdto = new OperationSchedulingDTO(
					listaTaskSchedultatiDTO.get(i).getIdOperationScheduling(),
					listaTaskSchedultatiDTO.get(i).getIdTask(), idSchedulazione, i);
			updateOperazioneSchedulazione(osdto);
			listaTaskSchedultatiDTO.get(i).setOrder(i);
		}
		this.operationSchedulingDAO.deleteById(id);
	}

	public void insertOperazioneSchedulazione(int id_schedulazione, int id_task, int ordine) {
		OperationSchedulingDTO osdto = new OperationSchedulingDTO(0, id_task, id_schedulazione, ordine);
		this.operationSchedulingDAO.save(OperationSchedulingConverter.convertToEntity(osdto));
	}

	public void updateOperazioneSchedulazione(OperationSchedulingDTO osdto) {
		this.operationSchedulingDAO.save(OperationSchedulingConverter.convertToEntity(osdto));
	}

	public List<OperationSchedulingDTO> getOperazioniSchedulazioneByIdSchedulazione(int idSchedulazione) {
		Scheduling scheduling = new Scheduling();
		scheduling.setId(idSchedulazione);
		List<OperationSchedulingDTO> osdto = OperationSchedulingConverter
				.toListDTO(this.operationSchedulingDAO.findAllByScheduling(scheduling));
		return osdto;
	}

	public void insertOperationScheduling(OperationSchedulingDTO osDTO) {
		operationSchedulingDAO.save(OperationSchedulingConverter.convertToEntity(osDTO));
	}

	public List<TaskScheduledDTO> getTaskSchedulati(int idSchedulazione, int idMacchinario) {
		List<TaskDTO> listaTask = taskService.getAllTasks(idMacchinario);
		List<TaskScheduledDTO> listaTaskSchedulati = new ArrayList<>();
		List<OperationSchedulingDTO> oss = this.getOperazioniSchedulazioneByIdSchedulazione(idSchedulazione);
		for (OperationSchedulingDTO os : oss) {
			for (TaskDTO task : listaTask) {
				if (task.getId() == os.getIdTask()) {
					listaTaskSchedulati
							.add(new TaskScheduledDTO(task.getId(), task.getDescrizione(), os.getOrder(), os.getId()));
					break;
				}
			}
		}
		return listaTaskSchedulati;
	}

	public void modifyPosition(int idSchedulazione, int idMacchinario, int oldPosition, int newPosition) {
		List<TaskScheduledDTO> listaTaskSchedulati = getTaskSchedulati(idSchedulazione, idMacchinario);
		if (newPosition < 1 || (newPosition > listaTaskSchedulati.size()))
			return;
		OperationSchedulingDTO osdto = new OperationSchedulingDTO(
				listaTaskSchedulati.get(oldPosition - 1).getIdOperationScheduling(),
				listaTaskSchedulati.get(oldPosition - 1).getIdTask(), idSchedulazione, newPosition);// listaTaskSchedulati.get(oldPosition
		insertOperationScheduling(osdto);
		osdto = new OperationSchedulingDTO(listaTaskSchedulati.get(newPosition - 1).getIdOperationScheduling(),
				listaTaskSchedulati.get(newPosition - 1).getIdTask(), idSchedulazione, oldPosition);
		insertOperationScheduling(osdto);
		listaTaskSchedulati.get(oldPosition - 1).setOrder(newPosition);
		listaTaskSchedulati.get(newPosition - 1).setOrder(oldPosition);
	}
}
