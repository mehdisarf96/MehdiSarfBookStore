package com.mehdisarf.mehdisarfbookstore.controller.frontend.customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowEditProfileFormServlet", urlPatterns = "/edit_profile")
public class ShowEditProfileFormServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/edit_profile.jsp");
        dispatcher.forward(request,response);
    }

}
