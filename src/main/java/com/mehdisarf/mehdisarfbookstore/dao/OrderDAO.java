package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.entity.Book;
import com.mehdisarf.mehdisarfbookstore.entity.BookOrder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {

    @Override
    public BookOrder create(BookOrder bookOrder) {

        bookOrder.setOrderDate(new Date());
        return super.create(bookOrder);
    }

    @Override
    public BookOrder update(BookOrder bookOrder) {
        return super.update(bookOrder);
    }

    @Override
    public BookOrder get(Object id) {
        return super.find(BookOrder.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(BookOrder.class, id);
    }

    @Override
    public List<BookOrder> listAll() {
        return super.findWithNamedQuery("BookOrder.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("BookOrder.countAll");
    }

    public List<BookOrder> listByCustomer(int customerId) {
        return super.findWithNamedQuery("BookOrder.listByCustomer", "customerId", customerId);
    }

    public BookOrder get(int orderId, int customerId) {

        Map<String, Object> orderIdAndCustomerId = new HashMap<>();

        orderIdAndCustomerId.put("orderId", orderId);
        orderIdAndCustomerId.put("customerId", customerId);

        List<BookOrder> foundByCustomer = super.findWithNamedQuery("BookOrder.findByCustomer", orderIdAndCustomerId);

        if (foundByCustomer != null && foundByCustomer.size() == 1)
            return foundByCustomer.get(0);

        return null;
    }

    public List<BookOrder> mostRecentOrder() {
        return super.findWithNamedQuery("BookOrder.mostRecentOrder", 0, 3);
    }
}
