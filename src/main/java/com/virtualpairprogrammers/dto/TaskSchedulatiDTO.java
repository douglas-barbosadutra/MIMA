package com.virtualpairprogrammers.dto;

public class TaskSchedulatiDTO {
	private int id;
	private String descrizione;
	private int ordine;
	private int idOperazioneSchedulazione;
	
	public TaskSchedulatiDTO(int id, String descrizione, int ordine, int idOperazioneSchedulazione) {
		this.id=id;
		this.descrizione=descrizione;
		this.ordine = ordine;
		this.idOperazioneSchedulazione = idOperazioneSchedulazione;
	}
	public int getID() {
		return id;
	}
	public String getDescrizione() {
		return descrizione;
	}

	public int getOrdine() {
		return ordine;
	}
	
	public int getIdOperazioneSchedulazione() {
		return idOperazioneSchedulazione;
	}
	
	public void setOrdine(int nuovoOrdine) {
		ordine = nuovoOrdine;
	}
	
	@Override
	public String toString() {
		return "ID: "+id+"\nDescrizione: "+descrizione + "\nordine: "+ ordine;
	}


}



