package org.example.study_03_servlet_jdbc;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.study_03_servlet_jdbc.dao.IUserDao;
import org.example.study_03_servlet_jdbc.dao.implement.UserDaoImpl;
import org.example.study_03_servlet_jdbc.models.UserModel;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet
{
    private String message;

    public void init()
    {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        IUserDao userDao = new UserDaoImpl();
        UserModel user = userDao.findByUsername("nlg.bao1340");
        System.out.println(user.getEmail());
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy()
    {
    }
}