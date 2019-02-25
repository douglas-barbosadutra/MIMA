package com.virtualpairprogrammers.servlets;

import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserServlet extends HttpServlet {



    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Integer choice = (Integer) session.getAttribute("choice");
        String role = (String) session.getAttribute("role");
       // UserService userService = UserService.getService();
        UserService userService=new UserService();

        switch (role) {

            case "admin": {
                switch (choice) {
                    case 3: {
                        List<User> users = userService.getAllUser();
                        session.setAttribute("view", "UserView.jsp");
                        session.setAttribute("mode", "all");
                        session.setAttribute("users", users);
                        MainDispatcherServlet.getInstance(request).callView(request, response);
                        break;
                    }
                }
            }
            break;

            case "reg": {
                switch (choice) {
                    case 4:
                      /*  String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        String firstname = request.getParameter("firstname");
                        String lastname = request.getParameter("lastname");
                        String dateofbirth = request.getParameter("dateofbirth");
                        String fiscalcode = request.getParameter("fiscalcode");
                        String businessname = request.getParameter("businessname");
                        String vat = request.getParameter("vat");
                        String municipality = request.getParameter("municipality");
                        String post = request.getParameter("post");
                        String city = request.getParameter("city");
                        String address = request.getParameter("address");
                        String telephone = request.getParameter("telephone");

                        userService.insertUser(new User(null, username, password, firstname, lastname, dateofbirth, fiscalcode, businessname, vat, municipality, post, city, address, telephone,"user"));
                        session.setAttribute("view", "index.jsp");
                        MainDispatcherServlet.getInstance(request).callView(request, response);*/
                      session.setAttribute("mode","reg");
                      session.setAttribute("view","UserView");
                      MainDispatcherServlet.getInstance(request).callView(request, response);
                }
                break;
            }

        }


    }


}
