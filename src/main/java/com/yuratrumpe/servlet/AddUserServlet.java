package com.yuratrumpe.servlet;


import com.yuratrumpe.services.UserService;
import com.yuratrumpe.services.UserServiceImpl;
import com.yuratrumpe.util.ApplicationContextHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(value = "/admin/adduser")
public class AddUserServlet extends HttpServlet{

    private UserService userService = ApplicationContextHelper.context.getBean("userService", UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/pages/admin/user_add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String userName = req.getParameter("name");
        final String userPassword = req.getParameter("password");
        final String userRole = req.getParameter("role");

        if (userName == null || userPassword == null || userName.isEmpty() || userPassword.isEmpty() || userRole.isEmpty()) {
            resp.sendRedirect("/error_page.html");
        } else {
            userService.addUser(userName, userPassword, userRole);
            resp.sendRedirect(req.getContextPath() + "/admin/showusers");
        }
    }
}
