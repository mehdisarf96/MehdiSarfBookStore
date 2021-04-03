package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.entity.Review;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

    @Override
    public Review create(Review review) {
        review.setReviewTime(new Date());
        return super.create(review);
    }

    @Override
    public Review get(Object id) {
        return super.find(Review.class, id);
    }

    @Override
    public Review update(Review review) {
        return super.update(review);
    }

    @Override
    public void delete(Object id) {
        super.delete(Review.class, id);
    }

    @Override
    public List<Review> listAll() {
        return super.findWithNamedQuery("Review.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Review.countAll");
    }

    public Review findByCustomerAndBook(int customerId, int bookId) {

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("customerId", customerId);
        parameters.put("bookId", bookId);

        List<Review> reviewsFoundByCustomerAndBook = super.findWithNamedQuery("Review.findByCustomerAndBook", parameters);

        if (reviewsFoundByCustomerAndBook != null && reviewsFoundByCustomerAndBook.size() == 1)
            return reviewsFoundByCustomerAndBook.get(0);

        return null;
    }

    public long countByCustomer(int customerId) {
        return super.countWithNamedQuery("Review.countByCustomer", "customerId", customerId);
    }

    public long countByBook(int bookId) {
        return super.countWithNamedQuery("Review.countByBook", "bookId", bookId);
    }

    public List<Review> mostRecentReviews() {
        return super.findWithNamedQuery("Review.mostRecentReview", 0, 3);
    }
}
