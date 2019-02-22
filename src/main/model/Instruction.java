package main.model;

public class Instruction {
	
	private String nome;
	private int durata;
	
	public Instruction(String nome, int durata) {
		this.nome = nome;
		this.durata = durata;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getDurata() {
		return durata;
	}
}
