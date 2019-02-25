package com.virtualpairprogrammers.servlets;




import com.virtualpairprogrammers.utils.GestoreEccezioni;
import com.virtualpairprogrammers.utils.ReflectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class MainDispatcherServlet extends HttpServlet
{
    private static MainDispatcherServlet instance;

    public static MainDispatcherServlet getInstance(HttpServletRequest request)
    {
        HttpSession session = request.getSession(true);
        instance=(MainDispatcherServlet)session.getAttribute("instance");

        if (instance == null)
        {
            instance = new MainDispatcherServlet();
            session.setAttribute("instance",instance);
        }
        return instance;
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.getInstance(request);
        Method method;
        HttpSession session = request.getSession(true);
        String methodToCall = (String)session.getAttribute("method");
        try
        {
            method = this.getClass().getMethod(methodToCall, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, (HttpServletRequest)request, (HttpServletResponse)response);
        }
        catch (Throwable e) {GestoreEccezioni.getInstance().gestisciEccezione(e);}
    }

    public void callAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession(true);

        String bott=request.getParameter("bott");

        if (bott.equals("Logout"))
            session.setAttribute("servlet", "Logout");
        else
            if(bott.equals("Registrati"))
            {
                session.setAttribute("servlet", "User");
                session.setAttribute("choice",4);
            }
            else
                if((bott.equals("Login"))&&(session.getAttribute("servlet")==null))
                {
                    session.setAttribute("servlet", "Login");
                }
                else
                    {
                        Integer sel;
                        if(!session.getAttribute("servlet").equals("Home"))
                            sel = Integer.parseInt(request.getParameter("sel"));
                        else
                            sel=0;
                        switch (sel)
                        {
                            case 0: break;

                            case 1:
                                session.setAttribute("servlet", "Gomma");
                                session.setAttribute("choice",1);
                                break;

                            case 2:
                                session.setAttribute("servlet", "Vehicle");
                                break;

                            case 3 :
                                session.setAttribute("servlet", "Gomma");
                                session.setAttribute("choice",2);
                                break;

                            case 6:
                                String type = (String) request.getParameter("type");
                                session.setAttribute("type", type);
                                session.setAttribute("choice",1);
                                session.setAttribute("servlet", "Gomma");
                                break;

                            case 7:
                                type = (String) request.getParameter("type");
                                session.setAttribute("type", type);
                                session.setAttribute("choice",2);
                                session.setAttribute("servlet", "Gomma");
                                break;

                            case 8:
                                type = (String) request.getParameter("type");
                                session.setAttribute("type", type);
                                session.setAttribute("servlet", "Vehicle");
                                break;
                        }
                    }
        HttpServlet oggettoServlet = (HttpServlet) ReflectionUtils.instantiateClass("com.virtualpairprogrammers.servlets." + session.getAttribute("servlet") + "Servlet");

        try
        {

            Method metodo = oggettoServlet.getClass().getMethod("service", HttpServletRequest.class, HttpServletResponse.class);
            metodo.invoke( oggettoServlet, (HttpServletRequest) request, (HttpServletResponse)response);
        }
        catch (Throwable e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
        }
    }

    public void callView(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession(true);
        String view = (String)session.getAttribute("view");
        response.sendRedirect(view);
    }

}
