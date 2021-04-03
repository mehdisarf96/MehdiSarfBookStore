package com.mehdisarf.mehdisarfbookstore.controller.frontend.customer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginNeedCustomerFilter", urlPatterns = "/*")
public class LoginNeedCustomerFilter implements Filter {

    private FilterConfig filterConfig;
    private static final String[] needToLoginUrls = {"/view_profile", "/edit_profile", "/update_profile",
            "/write_review", "/checkout", "/place_order", "/view_orders", "/show_order_detail", "/add_book_to_cart"};


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession existedSession = request.getSession(false);

        String contextPath = filterConfig.getServletContext().getContextPath();
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith(contextPath + "/admin/")) {

            chain.doFilter(req, resp);
            return;
        }

        boolean isLoggedIn = (existedSession != null) && (existedSession.getAttribute("theCustomer") != null);

        if (!isLoggedIn) {

            for (String loginRequiredURL : needToLoginUrls) {
                if (requestURI.contains(loginRequiredURL)) {

                    String destination = "frontend/login.jsp";
                    RequestDispatcher dispatcher = req.getRequestDispatcher(destination);
                    dispatcher.forward(req, resp);
                    return;
                }
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }
}
