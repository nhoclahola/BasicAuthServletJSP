package org.example.study_03_servlet_jdbc.controllers.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.study_03_servlet_jdbc.constants.Constant;

import java.io.IOException;

@WebServlet("/home")
public class HomeController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.Path.USER_HOME);
        requestDispatcher.forward(req, resp);
    }
}
