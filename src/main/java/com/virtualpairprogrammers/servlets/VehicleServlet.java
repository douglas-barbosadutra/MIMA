package com.virtualpairprogrammers.servlets;

import com.virtualpairprogrammers.domain.Vehicle;
import com.virtualpairprogrammers.services.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class VehicleServlet extends HttpServlet {

   // protected void doPost(HttpServletRequest request, HttpServletResponse response)
     //   throws ServletException, IOException
    //{ }




    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int scelta = Integer.parseInt((String)request.getParameter("scelta"));
        VehicleService vehicleService = VehicleService.getService();

        switch (scelta)
        {
            case 1:
                {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("view", "VehicleView.jsp");
                    session.setAttribute("mode", "insert");
                    //session.setAttribute("view","insertVehicle.jsp");
                    MainDispatcherServlet.getInstance(request).callView(request,response);
            }
            case 2:
            {
                List<Vehicle> vehicles = vehicleService.getAllVehicle();
                HttpSession session = request.getSession(true);
                session.setAttribute("view", "VehicleView.jsp");
                session.setAttribute("mode", "all");
//                session.setAttribute("view","allVehicle.jsp");
                MainDispatcherServlet.getInstance(request).callView(request,response);
                break;
            }
        }
    }
}
