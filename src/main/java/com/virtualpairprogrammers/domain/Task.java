package com.virtualpairprogrammers.domain;


public class Task {

	private int id;
	private String descrizione;
	private int macchinario;	
	
	public Task(int id, String descrizione,int macchinario) {
		
		this.id=id;
		this.descrizione=descrizione;
		this.macchinario=macchinario;
	}
	public int getID() {
		return id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public int getMacchinario() {
		return macchinario;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione=descrizione;
	}
	public void setMacchinario(int macchinario) {
		this.macchinario=macchinario;
		
	}

	@Override
	public String toString() {
		return "Descrizione: "+descrizione+ "\n Macchinario: "+macchinario;
	}


}



