package main.model;

public class User {

	private int id;
    private String username;
    private String password;
    private int rank;

    public User(int id, String username, String password, int rank) {
    	this.id = id;
        this.username = username;
        this.password = password;
        this.rank = rank;
    }
    
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "ID: " + id + "\nUsername: " + username + "\nPassword: " +password + "\nRank: "+rank+"\n";

    }
}
