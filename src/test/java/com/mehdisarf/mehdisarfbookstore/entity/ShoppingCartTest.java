package com.mehdisarf.mehdisarfbookstore.entity;

import com.mehdisarf.mehdisarfbookstore.controller.frontend.shoppingcart.ShoppingCart;
import org.junit.*;

import static org.junit.Assert.*;

public class ShoppingCartTest {

    private static ShoppingCart shoppingCart;

    @BeforeClass
    public static void setUp() throws Exception {

        shoppingCart = new ShoppingCart();

        Book book = new Book();
        book.setBookId(1);
        book.setPrice(10);
        shoppingCart.addItem(book);
    }

    @AfterClass
    public static void tearDown() throws Exception {

    }

    @Test
    public void addItem() {

        Book book = new Book();
        book.setBookId(1);

        shoppingCart.addItem(book);

        assertEquals(2, (long) shoppingCart.getItems().get(book));
    }

    @Test
    public void removeItem() {

        Book book = new Book();
        book.setBookId(1);
        shoppingCart.addItem(book);

        shoppingCart.removeItem(book);

        assertNull(shoppingCart.getItems().get(book));
        assertTrue(shoppingCart.getItems().isEmpty());
    }

    @Test
    public void removeItemTwo() {

        Book book = new Book();
        book.setBookId(2);
        shoppingCart.addItem(book);

        shoppingCart.removeItem(book);

        assertNull(shoppingCart.getItems().get(book));
    }

    @Test
    public void removeItemById() {
    }


    @Test
    public void getTotalItem() {

        Book book2 = new Book();
        book2.setBookId(2);

        shoppingCart.addItem(book2);

        assertEquals(2, shoppingCart.getTotalItem());
    }

    @Test
    public void getTotalQuantity() {

        Book book = new Book(3);
        shoppingCart.addItem(book);
        shoppingCart.addItem(book);
        shoppingCart.addItem(book);
        shoppingCart.addItem(book);

        Book book1 = new Book(4);
        shoppingCart.addItem(book1);
        shoppingCart.addItem(book1);
        shoppingCart.addItem(book1);
        shoppingCart.addItem(book1);
        shoppingCart.addItem(book1);
        shoppingCart.addItem(book1);


        assertEquals(11, shoppingCart.getTotalQuantity());
    }

    @Test
    public void getTotalAmount() {

        Book book = new Book(3);
        book.setPrice(20);
        shoppingCart.addItem(book);
        shoppingCart.addItem(book);
        shoppingCart.addItem(book);
        shoppingCart.addItem(book);

        assertEquals(90.0, shoppingCart.getTotalAmount(), 0.0);
    }

    @Test
    public void getTotalAmount2() {
        assertEquals(10.0, shoppingCart.getTotalAmount(), 0.0);
    }

    @Test
    public void updateCart() {
    }

    @Test
    public void clear() {

        shoppingCart.clear();

        assertEquals(0.0,shoppingCart.getTotalAmount(),0.0);

    }
}