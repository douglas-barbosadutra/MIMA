package com.virtualpairprogrammers.dto;

public class TimeDTO {
	private String nomeIstruzione;
	private String item;
	private int durataEffettiva;
	private int durataPrevista;
	private String risultato;
	
	
	public TimeDTO(String nomeIstruzione, String item, int durataEffettiva, int durataPrevista) {
		this.nomeIstruzione = nomeIstruzione;
		this.item = item;
		this.durataEffettiva = durataEffettiva;
		this.durataPrevista = durataPrevista;
		this.risultato = (durataEffettiva <= durataPrevista) ? "GREEN" : "RED";
	}
	
	public String getNomeIstruzione() {
		return nomeIstruzione;
	}
	
	public String getItem() {
		return item;
	}
	
	public int getDurataEffettiva() {
		return durataEffettiva;
	}
	
	public int getDurataPrevista() {
		return durataPrevista;
	}
	
	public String getRisultato() {
		return risultato;
	}
	
	public String toString() {
		return "nome istruzione: " + nomeIstruzione + "\nitem: " + item + "\ndurataEffettiva: " + durataEffettiva + "\ndurataPrevista" + durataPrevista;
	}
}
