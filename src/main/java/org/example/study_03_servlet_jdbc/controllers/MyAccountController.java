package org.example.study_03_servlet_jdbc.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.study_03_servlet_jdbc.constants.Constant;
import org.example.study_03_servlet_jdbc.models.UserModel;
import org.example.study_03_servlet_jdbc.services.IUserService;
import org.example.study_03_servlet_jdbc.services.implement.UserServiceImpl;

import java.io.IOException;

@WebServlet("/member/my-account")
public class MyAccountController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
        {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals("username"))
                {
                    IUserService service = UserServiceImpl.getInstance();
                    UserModel user = service.get(cookie.getValue());
                    // If found user, create a session and save information to the session
                    if (user != null)
                    {
                        System.out.println("add a session");
                        HttpSession session = req.getSession(true);
                        session.setMaxInactiveInterval(30);
                        session.setAttribute("account", user);
                    }
                }
            }
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.Path.MY_ACCOUNT);
        requestDispatcher.forward(req, resp);
    }
}
