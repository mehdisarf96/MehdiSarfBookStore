package com.mehdisarf.mehdisarfbookstore.controller.admin.user;

import com.mehdisarf.mehdisarfbookstore.entity.Users;
import com.mehdisarf.mehdisarfbookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateUserServlet", urlPatterns = "/admin/create_user")
public class CreateUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserService(request, response);
        userService.create();
    }
}
