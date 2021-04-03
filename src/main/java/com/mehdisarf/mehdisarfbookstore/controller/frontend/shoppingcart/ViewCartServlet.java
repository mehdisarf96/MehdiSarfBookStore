package com.mehdisarf.mehdisarfbookstore.controller.frontend.shoppingcart;

import com.mehdisarf.mehdisarfbookstore.entity.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ViewCartServlet", urlPatterns = "/view_cart")
public class ViewCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Object cart = session.getAttribute("cart");

        if (cart == null) {

            ShoppingCart shoppingCart = new ShoppingCart();
            session.setAttribute("cart", shoppingCart);
        }

        String cartPage = "frontend/shopping_cart.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(cartPage);
        dispatcher.forward(request, response);
    }
}
