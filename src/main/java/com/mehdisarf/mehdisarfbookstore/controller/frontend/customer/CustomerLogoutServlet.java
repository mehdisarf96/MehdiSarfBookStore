package com.mehdisarf.mehdisarfbookstore.controller.frontend.customer;

import com.mehdisarf.mehdisarfbookstore.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CustomerLogoutServlet",urlPatterns = "/logout")
public class CustomerLogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CustomerService service = new CustomerService(request, response);
        service.logout();
    }
}
