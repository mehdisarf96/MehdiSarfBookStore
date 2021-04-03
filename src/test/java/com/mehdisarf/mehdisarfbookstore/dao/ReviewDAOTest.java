package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.entity.Book;
import com.mehdisarf.mehdisarfbookstore.entity.Customer;
import com.mehdisarf.mehdisarfbookstore.entity.Review;
import org.junit.*;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ReviewDAOTest {

    private static ReviewDAO reviewDAO;
    private static BookDAO bookDAO;
    private static CustomerDAO customerDAO;

    @BeforeClass
    public static void setUp() throws Exception {
        reviewDAO = new ReviewDAO();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        reviewDAO.closeFactory();
    }

    @Test
    public void create() {

        Review review = new Review();

        Book theBook = new Book();
        theBook.setBookId(48);
        review.setBook(theBook);

        Customer theCustomer = new Customer();
        theCustomer.setCustomerId(14);
        review.setCustomer(theCustomer);

        review.setHeadline("Not Bad");
        review.setComment("That was not a great book but it was just fine.");
        review.setRating(2);
        review.setReviewTime(new Date());

        Review newAddedReview = reviewDAO.create(review);

        assertTrue(newAddedReview.getReviewId() > 0);
    }

    @Test
    public void get() {

        Review review = reviewDAO.get(16);
        assertNotNull(review);
    }

    @Test
    public void update() {

        Review review = reviewDAO.get(15);

        review.setHeadline("WooooW");

        Review updatedReview = reviewDAO.update(review);

        assertEquals("WooooW", updatedReview.getHeadline());
    }

    @Test
    public void delete() {

        int reviewId = 17;
        reviewDAO.delete(reviewId);

        assertNull(reviewDAO.get(reviewId));
    }

    @Test
    public void listAll() {

        List<Review> reviews = reviewDAO.listAll();

        for (Review review : reviews) {
            System.out.println(review.getReviewId() + ". " + review.getBook().getTitle() + " " + review.getComment());
        }

        assertTrue(reviews.size() > 0);
    }

    @Test
    public void count() {

        long count = reviewDAO.count();

        assertEquals(2, count);
    }

    @Test
    public void findByCustomerAndBook() {

        Review reviewFoundByCustomerAndBook = reviewDAO.findByCustomerAndBook(14, 48);

        System.out.println(reviewFoundByCustomerAndBook.getHeadline() + " " + reviewFoundByCustomerAndBook.getComment());

        assertEquals("Not Bad!",reviewFoundByCustomerAndBook.getHeadline());
    }

    @Test
    public void countByCustomer() {

        int customerId = 12;
        long count = reviewDAO.countByCustomer(customerId);
        assertEquals(2, count);
    }

    @Test
    public void countByBook() {

        int bookId = 48;
        long count = reviewDAO.countByBook(bookId);
        assertEquals(3, count);
    }
}