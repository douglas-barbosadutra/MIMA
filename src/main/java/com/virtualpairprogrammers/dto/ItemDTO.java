package com.virtualpairprogrammers.dto;


public class ItemDTO {
	
	private int id;
	private String descrizione;
	
	public ItemDTO() {
		
	}
	
	public ItemDTO(int id, String descrizione) {
		this.id = id;
		this.descrizione = descrizione;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getDescrizione() {
		return this.descrizione;
	}
	

}
