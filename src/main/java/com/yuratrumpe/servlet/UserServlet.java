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


@WebServlet(value = "/user/user")
public class UserServlet extends HttpServlet {

    private UserService userService = ApplicationContextHelper.context.getBean("userService", UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/pages/user/user.jsp");
        requestDispatcher.forward(req, resp);
    }

}
