package com.yuratrumpe.servlet.filter;

import com.yuratrumpe.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/login"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {

            switch (user.getRole()) {
                case "admin":
                    resp.sendRedirect(req.getContextPath() + "/admin/showusers");
                    return;
                case "user":
                    resp.sendRedirect(req.getContextPath() + "/user/user");
                    return;
                default:
                    resp.sendRedirect("/index.jsp");
                    return;
            }
        }

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
