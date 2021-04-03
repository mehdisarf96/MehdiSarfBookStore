package com.mehdisarf.mehdisarfbookstore.controller.admin.book;

import com.mehdisarf.mehdisarfbookstore.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateBookServlet", urlPatterns = "/admin/create_book")
@MultipartConfig(
        fileSizeThreshold = 1024 * 10,
        maxFileSize = 1024 * 300,
        maxRequestSize = 1024 * 1024
)
public class CreateBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookService service = new BookService(request, response);
        service.create();
    }
}
