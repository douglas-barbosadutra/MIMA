package com.virtualpairprogrammers.dto;

public class InstructionDTO {
	private String nomeIstruzione;
	private int durata;
	
	public InstructionDTO(String nomeIstruzione, int durata) {
		this.nomeIstruzione = nomeIstruzione;
		this.durata = durata;
	}
	
	public String getNomeIstruzione() {
		return nomeIstruzione;
	}
	
	public int getDurata() {
		return durata;
	}
	
	public String toString() {
		return "Nome Istruzione: " + nomeIstruzione + "\nDurata: " + durata + "\n";
	}
}
