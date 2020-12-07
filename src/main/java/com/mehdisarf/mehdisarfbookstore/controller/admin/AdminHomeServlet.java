package com.mehdisarf.mehdisarfbookstore.controller.admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminHomeServlet", urlPatterns = "/admin/")
public class AdminHomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String adminHomePage = "index.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(adminHomePage);
        dispatcher.forward(request, response);
    }
}
