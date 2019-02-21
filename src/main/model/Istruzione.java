package main.model;

public class Istruzione {
	
	private String nome;
	private int durata;
	
	public Istruzione(String nome, int durata) {
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
