package com.mehdisarf.mehdisarfbookstore.controller.admin.category;

import com.mehdisarf.mehdisarfbookstore.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCategoryServlet", urlPatterns = "/admin/delete_category")
public class DeleteCategoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryService service = new CategoryService(request,response);
        service.delete();
    }
}
