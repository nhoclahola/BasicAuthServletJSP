package org.example.study_03_servlet_jdbc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.study_03_servlet_jdbc.models.UserModel;

import java.io.IOException;

@WebServlet("/waiting")
public class WaitingController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if (session != null && session.getAttribute("account") != null)
        {
            UserModel u = (UserModel) session.getAttribute("account");
            req.setAttribute("username", u.getUsername());
            if (u.getRoleId() == 1)
            {
                resp.sendRedirect(req.getContextPath() + "/admin/home");
            }
            else if (u.getRoleId() == 2)
            {
                resp.sendRedirect(req.getContextPath() + "/manager/home");
            }
            else
            {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        }
        else
        {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}

