package com.mehdisarf.mehdisarfbookstore.controller.frontend.shoppingcart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RemoveBookFromCartServlet", urlPatterns = "/remove_from_cart")
public class RemoveBookFromCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("book_id"));

        HttpSession session = request.getSession(false);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        cart.removeItemById(bookId);

        response.sendRedirect(request.getContextPath().concat("/view_cart"));
    }
}
