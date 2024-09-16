package org.example.study_03_servlet_jdbc.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.study_03_servlet_jdbc.models.UserModel;
import org.example.study_03_servlet_jdbc.services.IUserService;
import org.example.study_03_servlet_jdbc.services.implement.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

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
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/waiting");
                    return;
                }
            }
        }
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
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
        {
            isRememberMe = true;
        }
        String alertMsg =
                "";
        if (username.isEmpty() || password.isEmpty())
        {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        IUserService service = new UserServiceImpl();

        UserModel user = service.login(username, password);
        boolean check = false;
        if (user != null)
        {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
//            if (isRememberMe)
//            {
//                saveRemeberMe(resp, username);
//            }

            resp.sendRedirect(req.getContextPath() + "/waiting");
        }
        else
        {
            alertMsg =
                    "Tài khoản hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
//        req.setAttribute("msg", check);
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
//        requestDispatcher.forward(req, resp);
    }

//    private void saveRemeberMe(HttpServletResponse response, String username)
//    {
//        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER,
//                username);
//        cookie.setMaxAge(30*60);
//        response.addCookie(cookie);
//    }
}
