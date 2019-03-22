package com.mima.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mima.converter.SchedulingConverter;
import com.mima.converter.TaskScheduledConverter;
import com.mima.dao.TaskScheduledDAO;
import com.mima.dto.OperationSchedulingDTO;
import com.mima.dto.SchedulingDTO;
import com.mima.dto.TaskScheduledDTO;
import com.mima.model.TaskScheduled;

@Service
public class TaskScheduledService {

	@Autowired
	TaskScheduledDAO taskScheduledDAO;

	@Autowired
	TaskService taskService;

	TaskScheduledService() {
	}

	public void insertScheduledRelations(OperationSchedulingDTO osDTO) {
		TaskScheduledDTO father = TaskScheduledConverter
				.convertToDto(taskScheduledDAO.findById(osDTO.getIdFather()).get());
		TaskScheduledDTO child = TaskScheduledConverter
				.convertToDto(taskScheduledDAO.findById(osDTO.getIdChild()).get());

		if (osDTO.getIdTask() == 0) {

			if (searchLoop(child, osDTO.getIdFather())) {
				if (searchLoop(father, osDTO.getIdChild())) {
					return;
				}
				int id = osDTO.getIdChild();
				osDTO.setIdChild(osDTO.getIdFather());
				osDTO.setIdFather(id);
			}
		}
		int idChild = osDTO.getIdChild();
		/*if (osDTO.getIdTask() != 0) {
			TaskScheduledDTO taskScheduled = new TaskScheduledDTO();
			taskScheduled.setIdTask(osDTO.getIdTask());
			taskScheduled.setIdScheduling(osDTO.getIdScheduling());
			System.out.println(taskScheduled);
			taskScheduled = insertTaskScheduled(taskScheduled);
			idChild = taskScheduled.getId();
		}*/
		taskScheduledDAO.insertScheduledRelations(idChild, osDTO.getIdFather());
	}

	public TaskScheduledDTO insertTaskScheduled(TaskScheduledDTO taskScheduled) {
		TaskScheduled task = TaskScheduledConverter.convertToEntity(taskScheduled);
		task = taskScheduledDAO.saveAndFlush(task);
		return TaskScheduledConverter.convertToDto(task);
	}

	public boolean deleteTaskScheduled(int id) {
		taskScheduledDAO.deleteById(id);
		return true;
	}

	public List<TaskScheduledDTO> getTaskScheduling(int idScheduling) {
		SchedulingDTO schedulingDTO = new SchedulingDTO();
		schedulingDTO.setId(idScheduling);
		return TaskScheduledConverter
				.toListDTO(taskScheduledDAO.findAllByScheduling(SchedulingConverter.convertToEntity(schedulingDTO)));
	}

	public TaskScheduledDTO getTaskScheduledRoot(int idScheduling) {
		List<TaskScheduledDTO> list = this.getTaskScheduling(idScheduling);
		for (TaskScheduledDTO i : list) {
			if (!i.isHasFather())
				return i;
		}
		return null;
	}

	public List<OperationSchedulingDTO> getOperationScheduling(int idScheduling) {
		List<TaskScheduledDTO> list = this.getTaskScheduling(idScheduling);
		List<OperationSchedulingDTO> result = new ArrayList<>();
		for (TaskScheduledDTO task : list) {
			if (task.getTaskScheduledChildren() != null) {
				for (TaskScheduledDTO child : task.getTaskScheduledChildren()) {
					OperationSchedulingDTO temp = new OperationSchedulingDTO(task.getId(), child.getId(), 0, 0);
					result.add(temp);
				}
			}
		}
		return result;
	}

	public boolean insertOutput(int idItem, int idOperationScheduling) {
		taskScheduledDAO.insertOutput(idItem, idOperationScheduling);
		return true;
	}

	private boolean searchLoop(TaskScheduledDTO task, int id) {
		if (task.getId() == id)
			return true;
		boolean result = false;
		for (TaskScheduledDTO child : task.getTaskScheduledChildren()) {
			result = result | searchLoop(child, id);
		}
		return result;
	}
}
