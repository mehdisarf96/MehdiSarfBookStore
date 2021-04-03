package com.mehdisarf.mehdisarfbookstore.controller.frontend.customer;

import com.mehdisarf.mehdisarfbookstore.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterCustomerServlet", urlPatterns = "/register_customer")
public class RegisterCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CustomerService service = new CustomerService(request, response);
        service.register();
    }

}
