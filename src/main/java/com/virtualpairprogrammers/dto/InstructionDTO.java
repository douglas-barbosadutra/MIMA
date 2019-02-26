package com.virtualpairprogrammers.dto;

public class InstructionDTO {
	private String nomeIstruzione;
	private int durata;
	private int id;
	
	public InstructionDTO(String nomeIstruzione, int durata, int id) {
		this.nomeIstruzione = nomeIstruzione;
		this.durata = durata;
		this.id = id;
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
	
	public String toString() {
		return "Nome Istruzione: " + nomeIstruzione + "\nDurata: " + durata + "\n";
	}
}
