package com.virtualpairprogrammers.dto;

import java.sql.Timestamp;

public class SchedulingDTO {

	private int id;
	private String nome;
	private Timestamp dataInizio;
	private Timestamp dataFine;
	private int idMacchinario;
	
	public SchedulingDTO(int id, String nome, Timestamp dataInizio, Timestamp dataFine, int idMacchinario) {
		this.id = id;
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.idMacchinario = idMacchinario;
	}
	
	public SchedulingDTO(String nome, Timestamp dataInizio, Timestamp dataFine, int idMacchinario) {
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.idMacchinario = idMacchinario;
		id = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Timestamp getInizio() {
		return dataInizio;
	}
	
	public Timestamp getFine() {
		return dataFine;
	}
	
	public int getIdMacchinario() {
		return idMacchinario;
	}
	
	public String toString() {
		return "nome: " + nome + "\ndata inizio: " + dataInizio + "\ndata fine: " + dataFine + "\nId Macchinario: " + idMacchinario + "\n";
	}
}
