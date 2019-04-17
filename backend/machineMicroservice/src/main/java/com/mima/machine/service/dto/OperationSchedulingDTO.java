package com.mima.machine.service.dto;

public class OperationSchedulingDTO {

	Long idFather;
	Long idChild;
	int idTask;	//questo campo, se diverso da zero, indicherà che bisogna inserire un nuovo task nello scheduling. Se invece è zero indica che è stata soltanto aggiunta una relazione tra due task già schedulati
	int idScheduling;
	
	

	public OperationSchedulingDTO(Long idFather, Long idChild, int idTask, int idScheduling) {
		super();
		this.idFather = idFather;
		this.idChild = idChild;
		this.idTask = idTask;
		this.idScheduling = idScheduling;
	}

	public OperationSchedulingDTO() {

	}

	public long getIdFather() {
		return idFather;
	}

	public void setIdFather(long idFather) {
		this.idFather = idFather;
	}

	public long getIdChild() {
		return idChild;
	}

	public void setIdChild(long idChild) {
		this.idChild = idChild;
	}

	public void setIdFather(Long idFather) {
		this.idFather = idFather;
	}

	public void setIdChild(Long idChild) {
		this.idChild = idChild;
	}

	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public int getIdScheduling() {
		return idScheduling;
	}

	public void setIdScheduling(int idScheduling) {
		this.idScheduling = idScheduling;
	}

	@Override
	public String toString() {
		return "OperationSchedulingDTO [idFather=" + idFather + ", idChild=" + idChild + ", idTask=" + idTask
				+ ", idScheduling=" + idScheduling + "]";
	}
	
	
}
