package com.virtualpairprogrammers.domain;

import java.sql.Timestamp;

public class Scheduling {
	private int id;
	private String nome;
	private Timestamp inizio;
	private Timestamp fine;
	private int idMacchinario;
	
	public Scheduling(int id, String nome, Timestamp inizio, Timestamp fine, int idMacchinario) {
		this.id = id;
		this.nome = nome;
		this.inizio = inizio;
		this.fine = fine;
		this.idMacchinario = idMacchinario;
	}
	
	public Scheduling(String nome, Timestamp inizio, Timestamp fine, int idMacchinario) {
		this.nome = nome;
		this.inizio = inizio;
		this.fine = fine;
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
		return inizio;
	}
	
	public Timestamp getFine() {
		return fine;
	}
	
	public int getIdMacchinario() {
		return idMacchinario;
	}
	
	public String toString() {
		return "nome: " + nome + "\ndata inizio: " + inizio + "\ndata fine: " + fine + "\nId Macchinario: " + idMacchinario + "\n";
	}
}
