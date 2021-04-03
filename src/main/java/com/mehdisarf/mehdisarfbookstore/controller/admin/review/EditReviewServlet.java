package com.mehdisarf.mehdisarfbookstore.controller.admin.review;

import com.mehdisarf.mehdisarfbookstore.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditReviewServlet", urlPatterns = "/admin/edit_review")
public class EditReviewServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ReviewService service = new ReviewService(request, response);
        service.edit();
    }
}
