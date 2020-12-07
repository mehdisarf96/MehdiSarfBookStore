package com.mehdisarf.mehdisarfbookstore.controller.admin;

import com.mehdisarf.mehdisarfbookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminLogoutServlet", urlPatterns = "/admin/logout")
public class AdminLogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserService(request,response);
        service.logout();
    }
}
