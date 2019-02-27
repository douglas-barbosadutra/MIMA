package com.virtualpairprogrammers.utils;

import java.util.List;

import com.virtualpairprogrammers.dao.LavorazioneDAO;
import com.virtualpairprogrammers.domain.Lavorazione;

public class Application {

    public static void main(String[] args){
    	LavorazioneDAO a = new LavorazioneDAO();
    	List<Lavorazione> list = a.getAllLavorazioni(12);
    	
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getID() + " " + list.get(i).getDurata());
		}
    }

}
