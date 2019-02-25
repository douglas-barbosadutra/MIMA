package com.virtualpairprogrammers.servlets;

import com.virtualpairprogrammers.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class HomeServlet extends HttpServlet
{
    private LoginService loginService;
    private String result="";

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String firstName = (String) session.getAttribute("firstname");
        String role = (String) session.getAttribute("role");
        loginService =LoginService.getService();

        if ((request != null)&&(role== null)&&(firstName==null))
        {

            String userName = request.getParameter("user");
            String password = request.getParameter("pwd");

            result = this.loginService.login(userName, password);

            if (result != null)
            {
                String[] part = result.split(":");
                session.setAttribute("firstname", part[0]);
                session.setAttribute("role", part[1]);
                session.setAttribute("view", "homeMenu.jsp");
                MainDispatcherServlet.getInstance(request).callView(request, response);
            }

            else
                {
                    session.setAttribute("view", "index.jsp");
                    MainDispatcherServlet.getInstance(request).callView(request, response);
                }
        }
        else
            {
                session.setAttribute("view", "homeMenu.jsp");
                MainDispatcherServlet.getInstance(request).callView(request, response);
            }
    }


}
