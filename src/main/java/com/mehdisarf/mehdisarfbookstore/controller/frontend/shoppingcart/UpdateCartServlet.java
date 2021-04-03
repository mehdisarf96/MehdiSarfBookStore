package com.mehdisarf.mehdisarfbookstore.controller.frontend.shoppingcart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@WebServlet(name = "UpdateCartServlet", urlPatterns = "/update_cart")
public class UpdateCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] bookIds = request.getParameterValues("bookId");
        String[] quantities = request.getParameterValues("quantity");

       /* String[] quantities = new String[bookIds.length];

        for (int i = 1; i <= quantities.length; i++) {

            quantities[i - 1] = request.getParameter("quantity" + i);
        }*/

        int[] intBookIds = Arrays.stream(bookIds).mapToInt(Integer::parseInt).toArray();
        int[] intQuantities = Arrays.stream(quantities).mapToInt(Integer::parseInt).toArray();

        HttpSession session = request.getSession(false);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        cart.updateCart(intBookIds, intQuantities);

        response.sendRedirect(request.getContextPath().concat("/view_cart"));
    }
}