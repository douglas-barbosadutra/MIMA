package main.model;

public class Machine {
	
	private int id;
	private String nome;
	private String modello;
	private int utente;
	
	public Machine(int id,String nome,String modello,int utente){
		this.id=id;
		this.nome=nome;
		this.modello=modello;
		this.utente=utente;
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
	public int getUtente() {
		return utente;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\nNome: "+nome+"\nModello: "+modello+"\nUtente proprietario: "+utente+"\n";
	}
	
	
	

	
}
	
