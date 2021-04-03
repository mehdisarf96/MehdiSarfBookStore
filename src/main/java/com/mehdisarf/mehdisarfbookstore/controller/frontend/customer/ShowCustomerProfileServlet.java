package com.mehdisarf.mehdisarfbookstore.controller.frontend.customer;

import com.mehdisarf.mehdisarfbookstore.service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowCustomerProfileServlet",urlPatterns = "/view_profile")
public class ShowCustomerProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/customer_profile.jsp");
        dispatcher.forward(request,response);
    }
}
