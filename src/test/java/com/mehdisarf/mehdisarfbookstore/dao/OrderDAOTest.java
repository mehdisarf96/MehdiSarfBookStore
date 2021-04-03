package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.entity.*;
import org.junit.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class OrderDAOTest {

    private static OrderDAO orderDAO;

    @BeforeClass
    public static void setUp() throws Exception {
        orderDAO = new OrderDAO();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        orderDAO.closeFactory();
    }

    @Test
    public void create() {

        Customer customer = new Customer(12);

        BookOrder bookOrder = new BookOrder();
        bookOrder.setCustomer(customer);
        bookOrder.setPaymentMethod("COD");
        bookOrder.setRecipientName("Hashem");
        bookOrder.setRecipientPhone("021");
        bookOrder.setShippingAddress("Park Lale");
        bookOrder.setStatus("good");

        Set<OrderDetail> details = new HashSet<>();

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setBook(new Book(44));
        orderDetail.setBookOrder(bookOrder);
        orderDetail.setQuantity(2);
        orderDetail.setSubtotal(20.0f);

        details.add(orderDetail);

        bookOrder.setOrderDetails(details);

        BookOrder savedOrder = orderDAO.create(bookOrder);

        assertTrue(savedOrder.getOrderDetails().size() > 0);
        //assertNotNull(savedOrder);
    }

    @Test
    public void update() {

        Customer customer = new Customer(14);

        BookOrder bookOrder = new BookOrder();
        bookOrder.setOrderId(24);
        bookOrder.setCustomer(customer);
        bookOrder.setPaymentMethod("COD");
        bookOrder.setRecipientName("Mina");
        bookOrder.setRecipientPhone("02155574585");
        bookOrder.setShippingAddress("Museum");
        bookOrder.setStatus("In Progress");
        bookOrder.setOrderDate(new Date());

        Set<OrderDetail> details = new HashSet<>();

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setBook(new Book(46));
        orderDetail.setBookOrder(bookOrder);
        orderDetail.setQuantity(1);
        orderDetail.setSubtotal(500.0f);

        details.add(orderDetail);

        bookOrder.setOrderDetails(details);

        BookOrder savedOrder = orderDAO.update(bookOrder);

        assertEquals("Mina", savedOrder.getRecipientName());
    }

    @Test
    public void get() {

        BookOrder order = orderDAO.get(25);

        System.out.println(order.getRecipientName());
        System.out.println(order.getPaymentMethod());
        System.out.println(order.getRecipientPhone());
        System.out.println(order.getShippingAddress());
        System.out.println(order.getStatus());

        assertEquals("Hashem", order.getRecipientName());
    }

    @Test
    public void delete() {
        orderDAO.delete(25);
        assertNull(orderDAO.get(25));
    }

    @Test
    public void listAll() {

        List<BookOrder> bookOrders = orderDAO.listAll();

        for (BookOrder order : bookOrders) {
            System.out.println(order.getRecipientName() + " " + order.getOrderDate() + " " + order.getShippingAddress() + " " + order.getRecipientPhone());
        }

        assertTrue(bookOrders.size() > 0);
    }

    @Test
    public void count() {
        assertEquals(3, orderDAO.count());
    }

    @Test
    public void listByCustomer() {

        List<BookOrder> bookOrders = orderDAO.listByCustomer(12);

        for (BookOrder order : bookOrders) {
            System.out.println(order.getOrderId() + " . " + order.getTotal());
        }

        assertTrue(bookOrders.size() > 0);
    }

    @Test
    public void get1() {
        BookOrder order = orderDAO.get(26, 12);
        assertNotNull(order);
        System.out.println(order.getRecipientName() + "  " + order.getTotal() + "  " + order.getStatus() + "  " + order.getPaymentMethod());
    }
}