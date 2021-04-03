package com.mehdisarf.mehdisarfbookstore.controller.admin;

import com.mehdisarf.mehdisarfbookstore.dao.*;
import com.mehdisarf.mehdisarfbookstore.entity.BookOrder;
import com.mehdisarf.mehdisarfbookstore.entity.Review;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminHomeServlet", urlPatterns = "/admin/")
public class AdminHomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderDAO orderDAO = new OrderDAO();
        List<BookOrder> mostRecentOrder = orderDAO.mostRecentOrder();
        request.setAttribute("mostRecentOrder", mostRecentOrder);

        ReviewDAO reviewDAO = new ReviewDAO();
        List<Review> mostRecentReviews = reviewDAO.mostRecentReviews();
        request.setAttribute("mostRecentReviews", mostRecentReviews);

        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        long numOfBooks = bookDAO.count();
        long numOfUsers = userDAO.count();
        long numOfCustomers = customerDAO.count();
        long numOfOrders = orderDAO.count();
        long numOfReviews = reviewDAO.count();

        request.setAttribute("numOfBooks", numOfBooks);
        request.setAttribute("numOfUsers", numOfUsers);
        request.setAttribute("numOfCustomers", numOfCustomers);
        request.setAttribute("numOfOrders", numOfOrders);
        request.setAttribute("numOfReviews", numOfReviews);

        String adminHomePage = "index.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(adminHomePage);
        dispatcher.forward(request, response);
    }
}
