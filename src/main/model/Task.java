package main.model;

import java.sql.Timestamp;

public class Task {

	private int id;
	private String descrizione;
	private int macchinario;
	private Timestamp data;
	
	public Task(int id, String descrizione,int macchinario,Timestamp data) {
		
		this.id=id;
		this.descrizione=descrizione;
		this.macchinario=macchinario;
		this.data=data;
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
	public Timestamp getData() {
		return data;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione=descrizione;
	}
	public void setMacchinario(int macchinario) {
		this.macchinario=macchinario;
		
	}
	public void setData(Timestamp data) {
		this.data=data;
	}
	
	@Override
	public String toString() {
		return "Descrizione: "+descrizione+ "\n Macchinario: "+macchinario+ "\n Data: "+data;
	}


}



