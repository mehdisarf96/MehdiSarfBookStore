package com.mehdisarf.mehdisarfbookstore.controller.frontend;

import com.mehdisarf.mehdisarfbookstore.entity.Category;
import com.mehdisarf.mehdisarfbookstore.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomePageServlet", urlPatterns = "")
public class HomePageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryService service = new CategoryService(request, response);
        List<Category> categories = service.listAll();

        request.setAttribute("categories", categories);

        String homePage = "/frontend/index.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);
        dispatcher.forward(request, response);
    }
}
