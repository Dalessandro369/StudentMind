/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ProjetJava
 */
public class LireMessageServlet extends HttpServlet {
    @Override
    public void init() throws ServletException{
    }

    public LireMessageServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        
        request.getRequestDispatcher("lireMessage.jsp").forward(request,response); 
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
    }
}
