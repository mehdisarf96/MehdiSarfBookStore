package com.mehdisarf.mehdisarfbookstore.controller.admin.book;

import com.mehdisarf.mehdisarfbookstore.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditBookServlet", urlPatterns = "/admin/edit_book")
public class EditBookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService service = new BookService(request,response);
        service.edit();
    }
}
