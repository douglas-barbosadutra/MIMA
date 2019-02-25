package com.virtualpairprogrammers.domain;

public class Instruction {
	
	private String nomeIstruzione;
	private int durata;
	private int idTask;
	
	public Instruction(String nome, int durata, int idTask) {
		this.nomeIstruzione = nome;
		this.durata = durata;
		this.idTask = idTask;
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
}
