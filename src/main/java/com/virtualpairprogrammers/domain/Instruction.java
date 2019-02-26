package com.virtualpairprogrammers.domain;

public class Instruction {
	
	private String nomeIstruzione;
	private int durata;
	private int idTask;
	private int id;
	
	public Instruction(String nome, int durata, int idTask, int id) {
		this.nomeIstruzione = nome;
		this.durata = durata;
		this.idTask = idTask;
		this.id = id;
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
}
