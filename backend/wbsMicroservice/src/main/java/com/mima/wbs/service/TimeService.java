package com.mima.wbs.service;

import java.util.List;

import com.mima.wbs.service.dto.TimeDTO;

public interface TimeService {

	List<TimeDTO> showTimeByIdTask(Integer idTask);
}
