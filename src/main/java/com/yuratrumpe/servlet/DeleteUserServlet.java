package com.yuratrumpe.servlet;


import com.yuratrumpe.services.UserService;
import com.yuratrumpe.services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteuser")
public class DeleteUserServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final Long userId = Long.valueOf(req.getParameter("id"));

        userService.deleteUser(userId);
        resp.sendRedirect(req.getContextPath() + "/users");

    }

}

