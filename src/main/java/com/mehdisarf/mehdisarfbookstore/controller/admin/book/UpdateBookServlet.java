package com.mehdisarf.mehdisarfbookstore.controller.admin.book;

import com.mehdisarf.mehdisarfbookstore.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateBookServlet", urlPatterns = "/admin/update_book")
@MultipartConfig(
        maxRequestSize = 1024 * 30,
        maxFileSize = 1024 * 30,
        fileSizeThreshold = 1024 * 30
)
public class UpdateBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService service = new BookService(request, response);
        service.update();
    }
}
