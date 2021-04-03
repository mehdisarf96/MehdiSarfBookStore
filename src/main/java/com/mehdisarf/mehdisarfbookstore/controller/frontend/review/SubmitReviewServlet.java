package com.mehdisarf.mehdisarfbookstore.controller.frontend.review;

import com.mehdisarf.mehdisarfbookstore.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitReviewServlet", urlPatterns = "/submit_review")
public class SubmitReviewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ReviewService service = new ReviewService(request,response);
        service.submitReview();
    }

}
