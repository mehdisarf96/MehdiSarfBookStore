package com.mehdisarf.mehdisarfbookstore.service;

import com.mehdisarf.mehdisarfbookstore.dao.BookDAO;
import com.mehdisarf.mehdisarfbookstore.dao.ReviewDAO;
import com.mehdisarf.mehdisarfbookstore.entity.Book;
import com.mehdisarf.mehdisarfbookstore.entity.Customer;
import com.mehdisarf.mehdisarfbookstore.entity.Review;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ReviewService {

    private ReviewDAO reviewDAO;

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ReviewService(HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
        this.reviewDAO = new ReviewDAO();
    }


    public void list(String msg) throws ServletException, IOException {

        List<Review> reviews = reviewDAO.listAll();
        request.setAttribute("reviews", reviews);

        if (msg != null)
            request.setAttribute("msg", msg);

        String path = "review_list.jsp";
        dispatch(path);
    }

    public void list() throws ServletException, IOException {
        list(null);
    }


    public void dispatch(String destination) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }

    public void edit() throws ServletException, IOException {

        int reviewId = Integer.parseInt(request.getParameter("reviewId"));

        Review review = reviewDAO.get(reviewId);
        request.setAttribute("review", review);

        dispatch("review_edit.jsp");
    }

    public void update() throws ServletException, IOException {

        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        Review theReview = reviewDAO.get(reviewId);

        String headline = request.getParameter("headline");
        theReview.setHeadline(headline);

        String comment = request.getParameter("comment");
        theReview.setComment(comment);

        reviewDAO.update(theReview);

        String message = "Review Has Been Updated Successfully!";
        list(message);
    }

    public void delete() throws ServletException, IOException {

        int reviewId = Integer.parseInt(request.getParameter("reviewId"));

        reviewDAO.delete(reviewId);

        list("The Review Has Been Deleted Successfully!");
    }

    public void showReviewForm() throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Customer theCustomer = (Customer) session.getAttribute("theCustomer");
        int customerId = theCustomer.getCustomerId();

        int bookId = Integer.parseInt(request.getParameter("bookId"));

        Review reviewFoundByCustomerAndBook = reviewDAO.findByCustomerAndBook(customerId, bookId);

        if (reviewFoundByCustomerAndBook == null) {
            request.setAttribute("canPostComment", true);
        } else {
            request.setAttribute("canPostComment", false);
            request.setAttribute("theReview", reviewFoundByCustomerAndBook);
        }

        BookDAO bookDAO = new BookDAO();
        Book theBook = bookDAO.get(bookId);
        request.setAttribute("book", theBook);

        dispatch("frontend/review_form.jsp");
    }

    public void submitReview() throws ServletException, IOException {

        String headline = request.getParameter("headline");
        String comment = request.getParameter("comment");
        int rating = Integer.parseInt(request.getParameter("rating"));

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.get(bookId);

        HttpSession session = request.getSession(false);
        Customer theCustomer = (Customer) session.getAttribute("theCustomer");

        Review review = new Review();

        review.setHeadline(headline);
        review.setComment(comment);
        review.setRating(rating);
        review.setBook(book);
        review.setCustomer(theCustomer);

        reviewDAO.create(review);

        showReviewFormAfterSubmittingReview(review, book);
    }

    private void showReviewFormAfterSubmittingReview(Review review, Book book) throws ServletException, IOException {

        request.setAttribute("canPostComment", false);
        request.setAttribute("theReview", review);
        request.setAttribute("book", book);

        request.setAttribute("msg", "Thank you! Your comment has been successfully submitted");

        dispatch("frontend/review_form.jsp");
    }
}
