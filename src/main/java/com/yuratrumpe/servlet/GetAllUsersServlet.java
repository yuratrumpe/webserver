package com.yuratrumpe.servlet;

import com.yuratrumpe.services.UserService;
import com.yuratrumpe.util.ApplicationContextHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/showusers" , loadOnStartup = 1)
public class GetAllUsersServlet extends HttpServlet {

    private UserService userService = ApplicationContextHelper.context.getBean("userService", UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("usersList", userService.getAllUsers());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/pages/admin/users_view.jsp");
        requestDispatcher.forward(req, resp);
    }
}
