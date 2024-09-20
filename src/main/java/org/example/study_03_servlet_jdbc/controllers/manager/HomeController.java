package org.example.study_03_servlet_jdbc.controllers.manager;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.study_03_servlet_jdbc.constants.Constant;
import org.example.study_03_servlet_jdbc.models.UserModel;
import org.example.study_03_servlet_jdbc.services.IUserService;
import org.example.study_03_servlet_jdbc.services.implement.UserServiceImpl;

import java.io.IOException;

@WebServlet("/manager/home")
public class HomeController extends HttpServlet
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
                        HttpSession session = req.getSession(true);
                        session.setAttribute("account", user);
                    }
                }
            }
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.Path.MANAGER_HOME);
        requestDispatcher.forward(req, resp);
    }
}
