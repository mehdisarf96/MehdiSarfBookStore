package com.mehdisarf.mehdisarfbookstore.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrderDetail implements java.io.Serializable {

    private CompositeOrderDetailPk id = new CompositeOrderDetailPk();
    private Book book;
    private BookOrder bookOrder;
    private int quantity;
    private float subtotal;

    public OrderDetail() {
    }

    public OrderDetail(CompositeOrderDetailPk id) {
        this.id = id;
    }

    public OrderDetail(CompositeOrderDetailPk id, Book book, BookOrder bookOrder, int quantity, float subtotal) {
        super();
        this.id = id;
        this.book = book;
        this.bookOrder = bookOrder;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    @EmbeddedId
    @AttributeOverrides({@AttributeOverride(name = "bookOrder.orderId", column = @Column(name = "order_id", nullable = false)),
            @AttributeOverride(name = "book.bookId", column = @Column(name = "book_id", nullable = false)),
    })
    public CompositeOrderDetailPk getId() {
        return this.id;
    }

    public void setId(CompositeOrderDetailPk id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", insertable = false, updatable = false, nullable = false)
    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
        this.id.setBook(book);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
    public BookOrder getBookOrder() {
        return this.bookOrder;
    }

    public void setBookOrder(BookOrder bookOrder) {
        this.bookOrder = bookOrder;
        this.id.setBookOrder(bookOrder);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}


