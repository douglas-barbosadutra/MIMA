package com.virtualpairprogrammers.utils;


import com.virtualpairprogrammers.dao.GommaDAO;
import com.virtualpairprogrammers.domain.Gomma;

import java.util.List;

public class Application {

    public static void main(String[] args)
    {
        GommaDAO gomma=new GommaDAO();


        List<Gomma> gommes=gomma.getAllGomme();
        System.out.println("Gomme compatibili");
        for(Gomma gom:gommes)
        {
            System.out.println(gom);
        }
    }

}
