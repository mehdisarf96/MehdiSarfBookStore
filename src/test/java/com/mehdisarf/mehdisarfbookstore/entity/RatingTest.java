package com.mehdisarf.mehdisarfbookstore.entity;


import java.util.HashSet;
import java.util.Set;

import org.junit.*;

public class RatingTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetAverageRating() {

        Book book = new Book();

        Set<Review> reviews = new HashSet<>();

        Review review1 = new Review();
        review1.setRating(5);
        Review review2 = new Review();
        review2.setRating(2);
        Review review3 = new Review();
        review3.setRating(1);
        Review review4 = new Review();
        review4.setRating(8);
        Review review5 = new Review();
        review5.setRating(6);

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
        reviews.add(review5);

        book.setReviews(reviews);

        System.out.println(book.getAverageRating());
        Assert.assertEquals(4.4, book.getAverageRating(), 1.0);
    }

    @Test
    public void testConvertAverageRatingToString() {

        Book book = new Book();

        Set<Review> reviews = new HashSet<>();

        Review review1 = new Review();
        review1.setRating(5);
        Review review2 = new Review();
        review2.setRating(5);
        Review review3 = new Review();
        review3.setRating(5);
        Review review4 = new Review();
        review4.setRating(5);
        Review review5 = new Review();
        review5.setRating(5);

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
        reviews.add(review5);

        book.setReviews(reviews);

        String ratingToString = book.convertAverageRatingToString(3.6f);

        System.out.println(book.getAverageRating());
        System.out.println(ratingToString);
    }

}
