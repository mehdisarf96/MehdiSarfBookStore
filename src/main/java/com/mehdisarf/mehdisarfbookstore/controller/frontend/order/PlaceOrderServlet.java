package com.mehdisarf.mehdisarfbookstore.controller.frontend.order;

import com.mehdisarf.mehdisarfbookstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PlaceOrderServlet", urlPatterns = "/place_order")
public class PlaceOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderService service = new OrderService(request, response);
        service.placeOrder();
    }
}
