package com.virtualpairprogrammers.dto;

public class OperazioneSchedulazioneDTO {

	private int id;
	private int id_task;
	private int id_schedulazione;
	private int ordine;
	
	public OperazioneSchedulazioneDTO() {
		
	}
	
	public OperazioneSchedulazioneDTO(int id, int id_task, int id_schedulazione, int ordine) {
		this.id = id;
		this.id_task = id_task;
		this.id_schedulazione = id_schedulazione;
		this.ordine = ordine;
		
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setTask(int id_task) {
		this.id_task = id_task;
	}
	
	public int getTask() {
		return this.id_task;
	}
	
	public void setSchedulazione(int id_schedulazione) {
		this.id_schedulazione = id_schedulazione;
	}
	
	public int getSchedulazione() {
		return this.id_schedulazione;
	}
	
	public void setOrdine(int ordine) {
		this.ordine = ordine;
	}
	
	public int getOrdine() {
		return this.ordine;
	}
	
}
