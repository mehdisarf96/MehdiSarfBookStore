package com.mehdisarf.mehdisarfbookstore.controller.frontend.book;

import com.mehdisarf.mehdisarfbookstore.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchBookServlet", urlPatterns = "/search")
public class SearchBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookService service = new BookService(request, response);
        service.search();
    }
}
