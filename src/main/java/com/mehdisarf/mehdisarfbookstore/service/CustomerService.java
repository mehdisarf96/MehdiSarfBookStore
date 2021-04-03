package com.mehdisarf.mehdisarfbookstore.service;

import com.mehdisarf.mehdisarfbookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.mehdisarf.mehdisarfbookstore.dao.CustomerDAO;
import com.mehdisarf.mehdisarfbookstore.entity.Customer;
import com.mehdisarf.mehdisarfbookstore.entity.Review;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class CustomerService {

    private CustomerDAO customerDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CustomerService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        customerDAO = new CustomerDAO();
    }

    public void list(String message) throws ServletException, IOException {

        List<Customer> customers = customerDAO.listAll();

        request.setAttribute("customers", customers);

        if (message != null) {
            request.setAttribute("msg", message);
        }

        String path = "customer_list.jsp";
        dispatch(path);
    }

    public void list() throws ServletException, IOException {
        list(null);
    }


    private void dispatch(String destination) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }

    public void create() throws ServletException, IOException {

        String email = request.getParameter("email");

        Customer customerFoundByEmail = customerDAO.findByEmail(email);

        if (customerFoundByEmail != null) {

            String message = "Could Not Create New Customer. The Email: " + email + " is already registered.";

            list(message);

        } else {

            Customer newCustomer = new Customer();
            setCustomerFieldsFromForm(newCustomer, null, email);

            customerDAO.create(newCustomer);

            String message = "New Customer Created Successfully!";
            list(message);
        }
    }

    public void register() throws ServletException, IOException {

        String email = request.getParameter("email");

        Customer customerFoundByEmail = customerDAO.findByEmail(email);

        if (customerFoundByEmail != null) {

            String message = "Registration Failed. The Email: " + email + " is already registered.";
            request.setAttribute("message", message);

            dispatch("/frontend/message.jsp");

        } else {

            Customer newCustomer = new Customer();
            setCustomerFieldsFromForm(newCustomer, null, email);

            customerDAO.create(newCustomer);

            String message = "Congratulation! Your Account Has Been Successfully Created.";
            request.setAttribute("message", message);

            dispatch("/frontend/message.jsp");
        }
    }

    public void edit() throws ServletException, IOException {

        int customerId = Integer.parseInt(request.getParameter("customerId"));

        Customer customer = customerDAO.get(customerId);

        request.setAttribute("customer", customer);

        dispatch("customer_edit.jsp");
    }

    public void update() throws ServletException, IOException {

        String email = request.getParameter("email");
        Integer id = Integer.parseInt(request.getParameter("customerId"));

        Customer customerFoundByEmail = customerDAO.findByEmail(email);

        if (customerFoundByEmail == null || (customerFoundByEmail != null && customerFoundByEmail.getCustomerId() == id)) {

            Customer existedCustomer = new Customer();
            setCustomerFieldsFromForm(existedCustomer, id, email);

            customerDAO.update(existedCustomer);

            String message = "Customer Has Been Updated Successfully!";
            list(message);

        } else {
            String message = "Update Failed! A customer with this email already exists: " + email;
            list(message);
        }
    }

    public void delete() throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("customerId"));
        Set<Review> customerReviews = customerDAO.get(id).getReviews();
        String message;

        if (customerReviews.size() == 0) {

            customerDAO.delete(id);
            message = "The User Has Been Deleted Successfully!";

        } else {
            message = "Could not delete customer with ID " + id + " because he/she posted reviews for books";
        }

        list(message);
    }

    private void setCustomerFieldsFromForm(Customer customer, Integer id, String email) {

        String fullName = request.getParameter("fullname");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String registerDate = request.getParameter("registerDate");
        String zip = request.getParameter("zip");
        Date formattedRegisterDate = registerDateFormatter(registerDate);

        if (id != null)
            customer.setCustomerId(id);

        if (email != null)
            customer.setEmail(email);

        customer.setFullName(fullName);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setPhone(phone);
        customer.setCountry(country);
        customer.setCity(city);
        customer.setRegisterDate(formattedRegisterDate);
        customer.setZipcode(zip);
    }

    public Date registerDateFormatter(String date) {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date formattedRegisterDate = null;

        try {
            formattedRegisterDate = formatter.parse(date);
        } catch (ParseException e) {
            formattedRegisterDate = new Date();
            e.printStackTrace();
        }

        return formattedRegisterDate;
    }

    public void doLogin() throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer checkedCustomer = customerDAO.checkLogin(email, password);

        if (checkedCustomer == null) {

            String message = "The Email and Password You entered, did not match our record. Please Try Again.";

            request.setAttribute("message", message);

            showLogin();

        } else {

            Customer theCustomer = customerDAO.findByEmail(email);

            HttpSession session = request.getSession();

            session.setAttribute("theCustomer", theCustomer);

            dispatch("frontend/customer_profile.jsp");
        }
    }

    public void showLogin() throws ServletException, IOException {
        dispatch("/frontend/login.jsp");
    }

    public void logout() throws ServletException, IOException {

        HttpSession existedSession = request.getSession(false);

        existedSession.invalidate();

        String message = "You have successfully logged out.";
        request.setAttribute("message", message);

        String homePage = "/";
        dispatch(homePage);
    }

    public void updateProfile() throws ServletException, IOException {

        HttpSession existedSession = request.getSession(false);

        Customer theCustomer = (Customer) existedSession.getAttribute("theCustomer");

        theCustomer.setFullName(request.getParameter("fullname"));
        theCustomer.setPhone(request.getParameter("phone"));
        theCustomer.setAddress(request.getParameter("address"));
        theCustomer.setCity(request.getParameter("city"));
        theCustomer.setZipcode(request.getParameter("zip"));
        theCustomer.setCountry(request.getParameter("country"));

        if (request.getParameter("password").length() > 0) {
            theCustomer.setPassword(request.getParameter("password"));
        }

        System.out.println("15");
        customerDAO.update(theCustomer);
        System.out.println("16");

        dispatch("frontend/customer_profile.jsp");
    }
}
