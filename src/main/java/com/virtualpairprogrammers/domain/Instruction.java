package com.virtualpairprogrammers.domain;

public class Instruction {
	
	private String nomeIstruzione;
	private int durata;
	private int idTask;
	private int id;
	private String codice;
	
	public Instruction(String nome, int durata, int idTask, int id, String codice) {
		this.nomeIstruzione = nome;
		this.durata = durata;
		this.idTask = idTask;
		this.id = id;
		this.codice = codice;
	}
	
	public String getNome() {
		return nomeIstruzione;
	}
	
	public int getDurata() {
		return durata;
	}
	
	public int getIdTask() {
		return idTask;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCodice() {
		return codice;
	}
}
