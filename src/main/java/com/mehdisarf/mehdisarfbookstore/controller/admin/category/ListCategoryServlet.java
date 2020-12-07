package com.mehdisarf.mehdisarfbookstore.controller.admin.category;

import com.mehdisarf.mehdisarfbookstore.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ListCategoryServlet", urlPatterns = "/admin/list_category")
public class ListCategoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryService categoryService = new CategoryService(request, response);
        categoryService.list();
    }
}
