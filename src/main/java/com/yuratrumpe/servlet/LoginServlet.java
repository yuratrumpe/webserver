package com.yuratrumpe.servlet;


import com.yuratrumpe.model.User;
import com.yuratrumpe.services.UserService;
import com.yuratrumpe.util.ApplicationContextHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet{

    private UserService userService = ApplicationContextHelper.context.getBean("userService", UserService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        if (login == null || password == null) {
            resp.sendRedirect("/error_page.html");
            return;
        }

        User user = userService.getUserByName(login);

        if (user == null || !password.equals(user.getPassword())) {

            resp.sendRedirect("/error_page.html");
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);

        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
