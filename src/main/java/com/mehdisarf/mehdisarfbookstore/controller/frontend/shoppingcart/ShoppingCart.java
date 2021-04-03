package com.mehdisarf.mehdisarfbookstore.controller.frontend.shoppingcart;

import com.mehdisarf.mehdisarfbookstore.dao.BookDAO;
import com.mehdisarf.mehdisarfbookstore.entity.Book;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<Book, Integer> cart;

    public ShoppingCart() {
        this.cart = new HashMap<>();
    }

    public void setCart(Map<Book, Integer> cart) {
        this.cart = cart;
    }

    public void addItem(Book book) {

        if (this.cart.containsKey(book)) {

            Integer quantity = cart.get(book);

            quantity++;

            cart.replace(book, quantity);

        } else {

            cart.put(book, 1);
        }
    }

    public void removeItem(Book book) {
        cart.remove(book);
    }

    public void removeItemById(int id) {

        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.get(id);

        cart.remove(book);
    }

    public void clear() {
        cart.clear();
    }

    public int getTotalItem() {
        return cart.size();
    }

    public int getTotalQuantity() {

        int total = 0;

        for (Book book : cart.keySet()) {
            total += cart.get(book);
        }

        return total;
    }

    public float getTotalAmount() {

        float amount = 0.0f;

        for (Book book : cart.keySet()) {

            Integer quantity = cart.get(book);
            amount += (book.getPrice() * quantity);
        }

        return amount;
    }

    public Map<Book, Integer> getItems() {
        return cart;
    }

    public void updateCart(int[] bookIds, int[] quantities) {

        for (int i = 0; i < bookIds.length; i++) {

            Book key = new Book(bookIds[i]);
            int value = quantities[i];

            cart.replace(key, value);
        }
    }

    @Override
    public String toString() {
        return "salam";
    }
}
