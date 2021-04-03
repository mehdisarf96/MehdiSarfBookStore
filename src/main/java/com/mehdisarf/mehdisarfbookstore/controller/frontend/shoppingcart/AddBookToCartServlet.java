package com.mehdisarf.mehdisarfbookstore.controller.frontend.shoppingcart;

import com.mehdisarf.mehdisarfbookstore.dao.BookDAO;
import com.mehdisarf.mehdisarfbookstore.entity.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddBookToCartServlet", urlPatterns = "/add_book_to_cart")
public class AddBookToCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Object cart = session.getAttribute("cart"); // momkene qablan "cart" ro nazade bashe.
        ShoppingCart shoppingCart = null;

        if (cart != null && cart instanceof ShoppingCart) {

            shoppingCart = (ShoppingCart) cart;

        } else {

            shoppingCart = new ShoppingCart();

            session.setAttribute("cart", shoppingCart);
        }

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        BookDAO bookDAO = new BookDAO();
        Book theBook = bookDAO.get(bookId);

        shoppingCart.addItem(theBook);

        response.sendRedirect(request.getContextPath().concat("/view_cart"));
    }
}
