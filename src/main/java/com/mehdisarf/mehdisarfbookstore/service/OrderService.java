package com.mehdisarf.mehdisarfbookstore.service;

import com.mehdisarf.mehdisarfbookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.mehdisarf.mehdisarfbookstore.dao.OrderDAO;
import com.mehdisarf.mehdisarfbookstore.entity.Book;
import com.mehdisarf.mehdisarfbookstore.entity.BookOrder;
import com.mehdisarf.mehdisarfbookstore.entity.Customer;
import com.mehdisarf.mehdisarfbookstore.entity.OrderDetail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class OrderService {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private OrderDAO orderDAO;

    public OrderService() {
    }

    public OrderService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.orderDAO = new OrderDAO();
    }

    public void list() throws ServletException, IOException {

        List<BookOrder> orders = orderDAO.listAll();

        request.setAttribute("orders", orders);

        String path = "order_list.jsp";
        dispatch(path);
    }

    private void dispatch(String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    public void viewDetailForAdmin() throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        BookOrder order = orderDAO.get(orderId);

        request.setAttribute("order", order);

        dispatch("order_detail.jsp");
    }

    public void checkOut() throws ServletException, IOException {
        dispatch("frontend/checkout.jsp");
    }

    public void placeOrder() throws ServletException, IOException {

        BookOrder order = new BookOrder();

        String recipientName = request.getParameter("recipientName");
        String paymentMethod = request.getParameter("paymentMethod");
        String address = request.getParameter("address");
        String recipientPhone = request.getParameter("recipientPhone");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String finalAddress = address + " " + zip + " " + city + " " + country;

        HttpSession session = request.getSession(false);
        Customer theCustomer = (Customer) session.getAttribute("theCustomer");

        order.setRecipientName(recipientName);
        order.setPaymentMethod(paymentMethod);
        order.setShippingAddress(finalAddress);
        order.setRecipientPhone(recipientPhone);
        order.setCustomer(theCustomer);
        order.setStatus("In Transit");

        Set<OrderDetail> orderDetails = new HashSet<>();

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        Map<Book, Integer> items = cart.getItems();

        Iterator<Book> bookIterator = items.keySet().iterator();
        while (bookIterator.hasNext()) {

            Book book = bookIterator.next();
            Integer quantity = items.get(book);
            float subTotal = quantity * book.getPrice();

            OrderDetail detail = new OrderDetail();
            detail.setBook(book);
            detail.setQuantity(quantity);
            detail.setSubtotal(subTotal);
            detail.setBookOrder(order);

            orderDetails.add(detail);
        }

        order.setOrderDetails(orderDetails);
        order.setTotal(cart.getTotalAmount());

        orderDAO.create(order);

        cart.clear();

        String message = "Thanks So Much For Your Purchase! Your Order Has Been Placed.";

        String message2 = "You Will Receive A Confirmation Email With Your Transaction Number Along With" +
                "Information On How To Track Your Order Once It Has Been Shipped.";


        request.setAttribute("message", message);
        request.setAttribute("message2", message2);
        dispatch("/frontend/message.jsp");
    }

    public void showOrderHistoryForCustomer() throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Customer theCustomer = (Customer) session.getAttribute("theCustomer");

        List<BookOrder> ordersOfTheCustomer = orderDAO.listByCustomer(theCustomer.getCustomerId());

        request.setAttribute("orders", ordersOfTheCustomer);

        dispatch("/frontend/order_list.jsp");
    }

    public void viewDetailForCustomer() throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));

        HttpSession session = request.getSession(false);
        Customer theCustomer = (Customer) session.getAttribute("theCustomer");

        BookOrder order = orderDAO.get(orderId, theCustomer.getCustomerId());

        request.setAttribute("order", order);

        dispatch("/frontend/order_detail.jsp");
    }
}
