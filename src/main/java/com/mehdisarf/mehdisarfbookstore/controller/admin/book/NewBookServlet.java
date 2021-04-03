package com.mehdisarf.mehdisarfbookstore.controller.admin.book;

import com.mehdisarf.mehdisarfbookstore.entity.Category;
import com.mehdisarf.mehdisarfbookstore.service.BookService;
import com.mehdisarf.mehdisarfbookstore.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NewBookServlet", urlPatterns = "/admin/new_book")
public class NewBookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookService service = new BookService(request,response);
        service.showBookForm();
    }
}
