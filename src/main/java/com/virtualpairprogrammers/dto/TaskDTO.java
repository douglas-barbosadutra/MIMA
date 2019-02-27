package com.virtualpairprogrammers.dto;


public class TaskDTO {

	private int id;
	private String descrizione;
	
	public TaskDTO(int id, String descrizione) {
		
		this.id=id;
		this.descrizione=descrizione;
	}
	public int getID() {
		return id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione=descrizione;
	}
	
	@Override
	public String toString() {
		return "ID: "+id+"\nDescrizione: "+descrizione;
	}


}



