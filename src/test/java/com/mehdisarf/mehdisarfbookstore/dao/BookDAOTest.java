package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.entity.Book;
import com.mehdisarf.mehdisarfbookstore.entity.Category;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BookDAOTest {

    private static BookDAO bookDAO;

    @BeforeClass
    public static void setUpClass() throws Exception {

        bookDAO = new BookDAO();
    }

    @Test
    public void testCreate() throws ParseException, IOException {

        Book book = new Book();

        Category category = new Category("Psychology");
        category.setCategoryId(12);

        book.setCategory(category);

        book.setTitle("The Lacanian Subject");
        book.setAuthor("Bruce Fink");
        book.setDescription("This book presents the radically new theory of subjectivity found in the work of Jacques Lacan. Against the tide of post-structuralist thinkers who announce \"the death of the subject,\" Bruce Fink explores what it means to come into being as a subject where impersonal forces once reigned, subjectify the alien roll of the dice at the beginning of our universe, and make our own knotted web of our parents' desires that led them to bring us into this world.");
        book.setIsbn("0691015899");
        book.setPrice(46.95f);

        // for create a Date object from a String, we need to use a DateFormat
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date publishDate = dateFormat.parse("12/05/1996");

        book.setPublishDate(publishDate);

        String imagePath = "F:\\MehdiSarfBookStore\\src\\main\\webapp\\images\\bookcover\\thelacaniansubject.jpg";

        //read all bytes from a path
        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

        book.setImage(imageBytes);

        Book createdBook = bookDAO.create(book);

        assertTrue(createdBook.getBookId() > 0);
    }

    @Test
    public void testCreateTwo() throws ParseException, IOException {

        Book book = new Book();

        Category category = new Category("Politics");
        category.setCategoryId(30);

        book.setCategory(category);

        book.setTitle("The End of History and the Last Man");
        book.setAuthor("Francis Fukuyama");
        book.setDescription("Ever since its first publication in 1992, The End of History and the Last Man has provoked controversy and debate. Francis Fukuyama's prescient analysis of religious fundamentalism, politics, scientific progress, ethical codes, and war is as essential for a world fighting fundamentalist terrorists as it was for the end of the Cold War. Now updated with a new afterword, The End of History and the Last Man is a modern classic.");
        book.setIsbn("0743284550");
        book.setPrice(20.00f);

        // for create a Date object from a String, we need to use a DateFormat
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date publishDate = dateFormat.parse("10/01/1992");

        book.setPublishDate(publishDate);

        String imagePath = "F:\\MehdiSarfBookStore\\src\\main\\webapp\\images\\bookcover\\theendofhistory.jpg";

        // read all bytes from a path
        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

        book.setImage(imageBytes);

        Book createdBook = bookDAO.create(book);

        assertTrue(createdBook.getBookId() > 0);
    }

    @Test
    public void testUpdate() throws ParseException, IOException {

        Book existingBook = new Book();
        existingBook.setBookId(32);

        Category category = new Category("Psychology");
        category.setCategoryId(12);

        existingBook.setCategory(category);

        existingBook.setTitle("The End of History and the Last Man");
        existingBook.setAuthor("Bruce Fink");
        existingBook.setDescription("This Book presents the radically new theory of subjectivity found in the work of Jacques Lacan. Against the tide of post-structuralist thinkers who announce \"the death of the subject,\" Bruce Fink explores what it means to come into being as a subject where impersonal forces once reigned, subjectify the alien roll of the dice at the beginning of our universe, and make our own knotted web of our parents' desires that led them to bring us into this world.");
        existingBook.setIsbn("0691015899");
        existingBook.setPrice(42.50f);

        // for create a Date object from a String, we need to use a DateFormat
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date publishDate = dateFormat.parse("12/05/1996");

        existingBook.setPublishDate(publishDate);

        String imagePath = "F:\\MehdiSarfBookStore\\src\\main\\webapp\\images\\bookcover\\thelacaniansubject.jpg";

        //read all bytes from a path
        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

        existingBook.setImage(imageBytes);

        Book updatedBook = bookDAO.update(existingBook);

        assertEquals(updatedBook.getAuthor(), existingBook.getAuthor());
    }

    @Test
    public void testGet() {

        int bookId = 33;
        Book theBook = bookDAO.get(bookId);

        assertEquals(theBook.getAuthor(), "Bruce Fink");
    }

    @Test()
    public void testGetFail() {

        int bookId = 3753;
        Book theBook = bookDAO.get(bookId);

        assertNull(theBook);
    }

    @Test
    public void testDelete() {
        int bookId = 32;
        bookDAO.delete(bookId);
        assertNull(bookDAO.get(bookId));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteFail() {

        int bookId = 33;
        bookDAO.delete(bookId);
    }

    @Test
    public void testListAll() {
        List<Book> books = bookDAO.listAll();

        for (Book book : books) {
            System.out.println(book.getTitle() + " ---- " + book.getAuthor());
        }

        assertFalse(books.isEmpty());
    }

    @Test
    public void testCount() {

        long totalBooks = bookDAO.count();

        assertEquals(6, totalBooks);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        bookDAO.closeFactory();
    }

    @Test
    public void findByTitle() {
        Book theBook = bookDAO.findByTitle("The End of History and the Last Man");

        System.out.println(theBook.getAuthor() + " -- " + theBook.getCategory().getName());

        assertNotNull(theBook);
        assertTrue(theBook.getBookId() > 0);
    }

    @Test
    public void findByTitleFail() {
        Book theBook = bookDAO.findByTitle("nonsense:|");
        assertNull(theBook);
    }

    @Test
    public void listByCategory() {
        List<Book> books = bookDAO.listByCategory(31);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
        assertNotNull(books);
    }

    @Test
    public void searchInTitle() {

        List<Book> foundBooks = bookDAO.search("The");

        for (Book book : foundBooks) {
            System.out.println(book.getTitle() + "  " + book.getAuthor());
        }

        assertEquals(3, foundBooks.size());
    }

    @Test
    public void searchInAuthor() {

        List<Book> foundBooks = bookDAO.search("taleb");

        for (Book book : foundBooks) {
            System.out.println(book.getTitle() + "  " + book.getAuthor());
        }

        assertEquals(1, foundBooks.size());
    }

    @Test
    public void searchInDescription() {

        List<Book> foundBooks = bookDAO.search("influential");

        for (Book book : foundBooks) {
            System.out.println(book.getTitle() + "  " + book.getAuthor());
        }

        assertEquals(1, foundBooks.size());
    }

    @Test
    public void listRecentlyAdded() {

    }

    @Test
    public void listNewBook() {
        List<Book> books = bookDAO.listNewBook();

        for (Book book : books) {
            System.out.println(book.getTitle());
        }

        assertEquals(4, books.size());
    }

    @Test
    public void countByCategory() {

        long count = bookDAO.countByCategory(31);

        assertEquals(5, count);
    }

    @Test
    public void listBestSellingBook() {
        List<Book> books = bookDAO.listBestSellingBook();

        assertEquals(4, books.size());

        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    @Test
    public void listMostInterestedBooks() {

        List<Book> mostInterestedBooks = bookDAO.listMostInterestedBooks();

        assertEquals(4, mostInterestedBooks.size());

        for (Book book : mostInterestedBooks) {
            System.out.println(book.getTitle());
        }

    }
}