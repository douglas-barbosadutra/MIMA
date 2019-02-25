package com.virtualpairprogrammers.dto;

public class UserDTO {

	private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private int rank;

    public UserDTO(int id, String name, String surname, String email, String phone, int rank) {
    	this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.rank = rank;
    }
    
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
    	return this.name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getSurname() {
    	return this.surname;
    }
    
    public void setSurname(String surname) {
    	this.surname = surname;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getPhone() {
    	return this.phone;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isAdmin() {
    	return (getRank() == 1 ? true : false);
    }
    @Override
    public String toString() {
        return "ID: " + id +"\nNome: "+name+"\nCognome: "+surname+"\nEmail: "+email+"\nTelefono: "+phone+ "\nRank: "+rank+"\n";

    }
}
