package org.example.study_03_servlet_jdbc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.study_03_servlet_jdbc.constants.Constant;
import org.example.study_03_servlet_jdbc.models.UserModel;
import org.example.study_03_servlet_jdbc.services.IUserService;
import org.example.study_03_servlet_jdbc.services.implement.UserServiceImpl;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null)
        {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }
        // Check cookie
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
                        session = req.getSession(true);
                        session.setAttribute("account", user);
                        resp.sendRedirect(req.getContextPath() + "/waiting");
                    }
                    else
                    {
                        resp.sendRedirect(req.getContextPath() + "/login");
                    }
                    return;
                }
            }
        }
        req.getRequestDispatcher(Constant.Path.LOGIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isRememberMe = false;
        String remember = req.getParameter("remember");

        if ("on".equals(remember))
            isRememberMe = true;
        String alertMsg = "";
        if (username.isEmpty() || password.isEmpty())
        {
            alertMsg = "Username or password should not be null";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.Path.LOGIN).forward(req, resp);
            return;
        }

        IUserService service = UserServiceImpl.getInstance();

        UserModel user = service.login(username, password);
        if (user != null)
        {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            if (isRememberMe)
            {
                saveRememberMe(resp, username);
            }

            resp.sendRedirect(req.getContextPath() + "/waiting");
        }
        else
        {
            alertMsg =
                    "Username or password is incorrect!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.Path.LOGIN).forward(req, resp);
        }
    }

    private void saveRememberMe(HttpServletResponse response, String username)
    {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER,
                username);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");    // Root for entire web
        response.addCookie(cookie);
    }
}
