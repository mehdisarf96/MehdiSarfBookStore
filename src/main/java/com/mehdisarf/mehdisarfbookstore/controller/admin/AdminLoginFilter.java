package com.mehdisarf.mehdisarfbookstore.controller.admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminLoginFilter", urlPatterns = "/admin/*")
public class AdminLoginFilter implements Filter {

    FilterConfig filterConfig;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);

        boolean isLoggedIn = (session != null) && (session.getAttribute("email") != null);

        String loginServletURL = filterConfig.getServletContext().getContextPath() + "/admin/login";

        boolean isLoginServletRequest = request.getRequestURI().equals(loginServletURL);
        boolean isLoginPageRequest = request.getRequestURI().endsWith("login.jsp");

        if (isLoggedIn && (isLoginPageRequest || isLoginServletRequest)) {

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);

        } else if (isLoggedIn || isLoginServletRequest) {

            chain.doFilter(req, resp);

        } else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }
}
