package com.virtualpairprogrammers.servlets;

import com.virtualpairprogrammers.domain.Gomma;
import com.virtualpairprogrammers.services.GommaService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class GommaServlet  extends HttpServlet
{
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);
        Integer choice = (Integer) session.getAttribute("choice");
        String role = (String) session.getAttribute("role");

        GommaService gommaService=GommaService.getService();

        switch (role)
        {
            case "user":
            {
                switch (choice)
                {
                    case 1:
                    {
                        session.setAttribute("view", "GommaView.jsp");
                        session.setAttribute("mode", "allBrandForVehicle");
                        MainDispatcherServlet.getInstance(request).callView(request, response);
                    }
                    break;
                    case 2:
                    {
                        session.setAttribute("view", "GommaView.jsp");
                        session.setAttribute("mode", "allGommeForSize");
                        MainDispatcherServlet.getInstance(request).callView(request, response);
                    }
                    break;
                    case 3:
                    {
                        session.setAttribute("view", "GommaView.jsp");
                        session.setAttribute("mode", "allGommeForVehicle");
                        MainDispatcherServlet.getInstance(request).callView(request, response);
                    }
                }
            }
            break;
            case "admin":
            {
                switch (choice)
                {
                    case 1:
                    {
                        session.setAttribute("view", "GommaView.jsp");
                        session.setAttribute("mode", "insert");
                        MainDispatcherServlet.getInstance(request).callView(request, response);
                    }
                    break;
                    case 2:
                    {
                        List<Gomma> gomme = gommaService.getAllGomme();
                        session.setAttribute("view", "GommaView.jsp");
                        session.setAttribute("mode", "all");
                        session.setAttribute("gomme",gomme);
                        MainDispatcherServlet.getInstance(request).callView(request, response);
                    }
                    break;
                    case 3:
                    {
                         String model=request.getParameter("model");
                         String manufacturer=request.getParameter("manufacturer");
                         double price=Double.parseDouble(request.getParameter("price"));
                         double width=Double.parseDouble(request.getParameter("width"));
                         double height=Double.parseDouble(request.getParameter("height"));
                         double diameter=Double.parseDouble(request.getParameter("diameter"));
                         double weight=Double.parseDouble(request.getParameter("weight"));
                         String speed=request.getParameter("speed");
                         String typeVehicle=request.getParameter("typeVehicle");
                         String season=request.getParameter("season");
                         int quantity=Integer.parseInt(request.getParameter("quantity"));
                         gommaService.insertGomma(new Gomma(null, model, manufacturer, price, width, height, diameter, weight, speed, season, typeVehicle,quantity));
                         session.setAttribute("view","homeMenu.jsp");
                         MainDispatcherServlet.getInstance(request).callView(request, response);
                    }
                }
            }
        }
    }
}
