package com.virtualpairprogrammers.dto;

public class InstructionDTO {
	private String nomeIstruzione;
	private int durata;
	private int id;
	private String codice;
	
	public InstructionDTO(String nomeIstruzione, int durata, int id, String codice) {
		this.nomeIstruzione = nomeIstruzione;
		this.durata = durata;
		this.id = id;
		this.codice = codice;
	}
	
	public String getNomeIstruzione() {
		return nomeIstruzione;
	}
	
	public int getDurata() {
		return durata;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public String toString() {
		return "Nome Istruzione: " + nomeIstruzione + "\nDurata: " + durata + "\n";
	}
}
