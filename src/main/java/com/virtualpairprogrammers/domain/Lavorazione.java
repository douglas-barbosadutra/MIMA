package com.virtualpairprogrammers.domain;

public class Lavorazione {

	private int id;
	private int item;
	private int istruzione;
	private int durata;
	
	public Lavorazione() {
		
	}
	
	public Lavorazione(int id, int item, int istruzione, int durata) {
		this.id = id;
		this.item = item;
		this.istruzione = istruzione;
		this.durata = durata;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setItem(int item) {
		this.item = item;
	}
	
	public int getItem() {
		return this.item;
	}
	
	public void setIstruzione(int istruzione) {
		this.istruzione = istruzione;
	}
	
	public int getIstruzione() {
		return this.istruzione;
	}
	
	public void setDurata(int durata) {
		this.durata = durata;
	}
	
	public int getDurata() {
		return this.durata;
	}
}
