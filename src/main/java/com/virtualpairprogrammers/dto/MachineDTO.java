package com.virtualpairprogrammers.dto;

public class MachineDTO {
	
	private int id;
	private String nome;
	private String modello;
	
	public MachineDTO(int id,String nome,String modello){
		this.id=id;
		this.nome=nome;
		this.modello=modello;
	}
		
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getModello() {
		return modello;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\nNome: "+nome+"\nModello: "+modello+"\n";
	}
	
	
	

	
}
	
