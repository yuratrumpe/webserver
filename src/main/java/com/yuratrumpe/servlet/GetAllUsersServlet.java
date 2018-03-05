package com.yuratrumpe.servlet;

import com.yuratrumpe.services.UserService;
import com.yuratrumpe.services.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/users" , loadOnStartup = 1)
public class GetAllUsersServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("usersList", userService.getAllUsers());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/pages/users_view.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        userService.closeResource();
    }
}
