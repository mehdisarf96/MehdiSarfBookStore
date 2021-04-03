package com.mehdisarf.mehdisarfbookstore.service;

import com.mehdisarf.mehdisarfbookstore.dao.BookDAO;
import com.mehdisarf.mehdisarfbookstore.dao.CategoryDAO;
import com.mehdisarf.mehdisarfbookstore.entity.Book;
import com.mehdisarf.mehdisarfbookstore.entity.Category;
import com.mehdisarf.mehdisarfbookstore.entity.Review;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BookService {

    private BookDAO bookDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public BookService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        bookDAO = new BookDAO();
    }

    public void list(String msg) throws ServletException, IOException {

        List<Book> books = bookDAO.listAll();
        request.setAttribute("books", books);

        if (msg != null)
            request.setAttribute("msg", msg);

        dispatch("book_list.jsp");
    }

    public void list() throws ServletException, IOException {
        list(null);
    }

    public void showBookForm() throws ServletException, IOException {

        CategoryDAO categoryDAO = new CategoryDAO();

        List<Category> categories = categoryDAO.listAll();

        request.setAttribute("categories", categories);

        dispatch("book_form.jsp");
    }

    public void create() throws ServletException, IOException {

        String title = request.getParameter("title");

        Book bookFoundByTitle = bookDAO.findByTitle(title);

        if (bookFoundByTitle != null) {

            String message = "A Book with this title already exists: " + title;
            request.setAttribute("msg", message);

            dispatch("message.jsp");

        } else {

            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String author = request.getParameter("author");
            String isbn = request.getParameter("isbn");
            float price = Float.parseFloat(request.getParameter("price"));
            String description = request.getParameter("description");

            // a DateFormat Will parse the String to Date object.
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            Date publishDate = null;

            try {
                publishDate = dateFormat.parse(request.getParameter("publishDate"));
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

            Part part = request.getPart("bookImage");
            byte[] imageBytes;

            Book newBook = new Book();

            newBook.setTitle(title);
            newBook.setAuthor(author);
            newBook.setIsbn(isbn);
            newBook.setDescription(description);
            newBook.setPrice(price);
            newBook.setPublishDate(publishDate);
            newBook.setCategory(new CategoryDAO().get(categoryId));

            if (part != null && part.getSize() > 0) {

                int size = (int) part.getSize();
                imageBytes = new byte[size];

                InputStream inputStream = part.getInputStream();
                inputStream.read(imageBytes);
                inputStream.close();
                newBook.setImage(imageBytes);
            }

            bookDAO.create(newBook);

            request.setAttribute("msg", "New Book Created Successfully!");
            list();
        }
    }

    public void edit() throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("bookid"));
        Book book = bookDAO.get(bookId);
        request.setAttribute("book", book);

        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.listAll();
        request.setAttribute("categories", categories);

        dispatch("book_edit.jsp");
    }

    public void update() throws IOException, ServletException {

        int bookId = Integer.parseInt(request.getParameter("id"));

        Book existingBook = bookDAO.get(bookId);

        Book bookFoundByTitle = bookDAO.findByTitle(request.getParameter("title"));

        if (bookFoundByTitle != null && !existingBook.equals(bookFoundByTitle)) {

            String msg = "Update Failed! A Book with this title already exists: " + bookFoundByTitle.getTitle();
            request.setAttribute("msg", msg);

            dispatch("message.jsp");

        } else {

            String title = request.getParameter("title");
            String author = request.getParameter("author");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String isbn = request.getParameter("isbn");
            String description = request.getParameter("description");
            float price = Float.parseFloat(request.getParameter("price"));

            // a DateFormat Will parse the String to Date object.
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            Date publishDate = null;

            try {
                publishDate = dateFormat.parse(request.getParameter("publishDate"));
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

            Part part = request.getPart("bookImage");
            byte[] imageBytes;

            Book newBook = new Book();

            newBook.setBookId(bookId);
            newBook.setTitle(title);
            newBook.setAuthor(author);
            newBook.setIsbn(isbn);
            newBook.setPrice(price);
            newBook.setDescription(description);
            newBook.setPublishDate(publishDate);
            newBook.setCategory(new CategoryDAO().get(categoryId));

            if (part != null && part.getSize() > 0) {

                int size = (int) part.getSize();
                imageBytes = new byte[size];

                InputStream inputStream = part.getInputStream();
                inputStream.read(imageBytes);
                inputStream.close();
                newBook.setImage(imageBytes);
            }

            bookDAO.update(newBook);

            String msg = "The Book Has Been Updated Successfully.";
            request.setAttribute("msg", msg);
            list();
        }
    }

    public void delete() throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("bookid"));

        Set<Review> theBookReviews = bookDAO.get(bookId).getReviews();

        String msg;

        if (theBookReviews.size() == 0) {

            bookDAO.delete(bookId);
            msg = "The Book Has Been Deleted Successfully.";

        } else {
            msg = "Could not delete the book with ID " + bookId + " because it has reviews";
        }

        list(msg);
    }

    public void listByCategory() throws ServletException, IOException {

        int categoryId = Integer.parseInt(request.getParameter("id"));
        List<Book> books = bookDAO.listByCategory(categoryId);
        request.setAttribute("books", books);

        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = categoryDAO.get(categoryId);
        request.setAttribute("category", category);

        dispatch("/frontend/books_list_by_category.jsp");
    }

    public List<Book> listNewBook() {
        return bookDAO.listNewBook();
    }

    public void viewBookDetail() throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookDAO.get(id);
        request.setAttribute("book", book);

        dispatch("/frontend/book_detail.jsp");
    }

    public void search() throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        if (keyword.equals("")) {

            request.setAttribute("isBlank", true);

            List<Book> books = bookDAO.listAll();
            request.setAttribute("foundBooks", books);

            dispatch("/frontend/search_result.jsp");

        } else {

            List<Book> foundBooks = bookDAO.search(keyword);

            request.setAttribute("foundBooks", foundBooks);

            request.setAttribute("keyword", keyword);

            String path = "/frontend/search_result.jsp";
            dispatch(path);
        }
    }

    private void dispatch(String destination) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }
}
