package com.mehdisarf.mehdisarfbookstore.controller.admin.customer;

import com.mehdisarf.mehdisarfbookstore.service.CategoryService;
import com.mehdisarf.mehdisarfbookstore.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ListCustomerServlet",urlPatterns = "/admin/list_customer")
public class ListCustomerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService service = new CustomerService(request, response);
        service.list();
    }
}
