package com.virtualpairprogrammers.dto;

import java.sql.Timestamp;

public class SchedulingDTO {

	private int id;
	private String nome;
	private String dataInizio;
	private String dataFine;
	private int idMacchinario;
	
	public SchedulingDTO(int id, String nome, String dataInizio, String dataFine, int idMacchinario) {
		this.id = id;
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.idMacchinario = idMacchinario;
	}
	
	public SchedulingDTO(String nome, String dataInizio, String dataFine, int idMacchinario) {
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.idMacchinario = idMacchinario;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getInizio() {
		return dataInizio;
	}
	
	public String getFine() {
		return dataInizio;
	}
	
	public int getIdMacchinario() {
		return idMacchinario;
	}
	
	public String toString() {
		return "nome: " + nome + "\ndata inizio: " + dataInizio + "\ndata fine: " + dataFine + "\nId Macchinario: " + idMacchinario + "\n";
	}
}
