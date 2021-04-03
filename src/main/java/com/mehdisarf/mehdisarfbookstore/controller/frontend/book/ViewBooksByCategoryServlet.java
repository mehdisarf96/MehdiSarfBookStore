package com.mehdisarf.mehdisarfbookstore.controller.frontend.book;

import com.mehdisarf.mehdisarfbookstore.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewBooksByCategoryServlet",urlPatterns = "/view_category")
public class ViewBooksByCategoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService service = new BookService(request, response);
        service.listByCategory();
    }
}
