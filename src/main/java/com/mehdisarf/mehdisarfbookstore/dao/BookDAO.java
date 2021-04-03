package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

    public BookDAO() {
    }

    @Override
    public Book create(Book book) {
        book.setLastUpdateTime(new Date());
        return super.create(book);
    }

    @Override
    public Book update(Book book) {
        book.setLastUpdateTime(new Date());
        return super.update(book);
    }

    @Override
    public Book get(Object id) {
        return super.find(Book.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Book.class, id);
    }

    @Override
    public List<Book> listAll() {
        return super.findWithNamedQuery("Book.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Book.countAll");
    }

    public Book findByTitle(String title) {
        List<Book> result = super.findWithNamedQuery("Book.findByTitle", "title", title);

        if (!(result.isEmpty())) {
            return result.get(0);
        }

        return null;
    }

    public long countByCategory(int categoryId) {

        return super.countWithNamedQuery("Book.countByCategory", "catId", categoryId);
    }

    public List<Book> listByCategory(int categoryId) {
        return super.findWithNamedQuery("Book.findByCategory", "categoryId", categoryId);
    }

    public List<Book> listNewBook() {

        Query query = super.factory.createEntityManager().createNamedQuery("Book.listNew");

        query.setFirstResult(0);
        query.setMaxResults(4);

        return query.getResultList();
    }

    public List<Book> search(String keyword) {
        return super.findWithNamedQuery("Book.search", "keyword", keyword);
    }

    public List<Book> listRecentlyAdded() {
        return null;
    }

    public List<Book> listBestSellingBook() {
        return super.findWithNamedQuery("OrderDetail.bestSell", 0, 4);
    }

    public List<Book> listMostInterestedBooks() {

        List<Book> mostInterestedBooks = new ArrayList<>();

        List<Object[]> result = super.findWithNamedQueryObjects("Review.mostInterestedBook", 0, 4);

        if (!result.isEmpty()) {

            for (Object[] element : result) {
                Book book = (Book) element[0];
                mostInterestedBooks.add(book);
            }

            return mostInterestedBooks;
        }

        return null;
    }
}
