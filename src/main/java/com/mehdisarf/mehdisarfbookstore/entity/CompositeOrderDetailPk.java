package com.mehdisarf.mehdisarfbookstore.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CompositeOrderDetailPk implements Serializable {

    private Book book;
    private BookOrder bookOrder;

    public CompositeOrderDetailPk() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false, nullable = false)
    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
    public BookOrder getBookOrder() {
        return this.bookOrder;
    }

    public void setBookOrder(BookOrder bookOrder) {
        this.bookOrder = bookOrder;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((book == null) ? 0 : book.hashCode());
        result = prime * result + ((bookOrder == null) ? 0 : bookOrder.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CompositeOrderDetailPk other = (CompositeOrderDetailPk) obj;
        if (book == null) {
            if (other.book != null)
                return false;
        } else if (!book.equals(other.book))
            return false;
        if (bookOrder == null) {
            if (other.bookOrder != null)
                return false;
        } else if (!bookOrder.equals(other.bookOrder))
            return false;
        return true;
    }
}
