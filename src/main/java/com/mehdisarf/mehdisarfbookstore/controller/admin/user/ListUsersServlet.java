package com.mehdisarf.mehdisarfbookstore.controller.admin.user;

import com.mehdisarf.mehdisarfbookstore.entity.Users;
import com.mehdisarf.mehdisarfbookstore.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListUsersServlet", urlPatterns = "/admin/list_users")
public class ListUsersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserService(request,response);
        userService.listUser();
    }
}
