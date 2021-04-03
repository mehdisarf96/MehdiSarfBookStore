package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.entity.BookOrder;
import com.mehdisarf.mehdisarfbookstore.entity.Customer;
import com.mehdisarf.mehdisarfbookstore.entity.Review;
import org.junit.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerDAOTest {

    private static CustomerDAO customerDAO;

    @BeforeClass
    public static void setUpClass() throws Exception {
        customerDAO = new CustomerDAO();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        customerDAO.closeFactory();
    }

    @Test
    public void testCreate() {

        Customer customer = new Customer();

        customer.setEmail("shami.kaghaloo@gmail.com");
        customer.setFullName("shami kaghaloo");
        customer.setAddress("kaghaloo's Home");
        customer.setCity("Tehran");
        customer.setCountry("Iran");
        customer.setPhone("532511");
        customer.setZipcode("021");
        customer.setPassword("4567");
        customer.setRegisterDate(new Date());
        customer.setReviews(new HashSet<Review>());
        customer.setBookOrders(new HashSet<BookOrder>());

        Customer recentlyAddedToDB = customerDAO.create(customer);

        assertTrue(recentlyAddedToDB.getCustomerId() > 0);
    }

    @Test
    public void testUpdate() {

        Customer customer = new Customer();

        customer.setCustomerId(13);

        customer.setEmail("Jean.Reno@gmail.com");
        customer.setFullName("Jean Reno");
        customer.setAddress("Sakhtemoon");
        customer.setCity("Paris");
        customer.setCountry("France");
        customer.setPhone("54568468");
        customer.setZipcode("1245");
        customer.setPassword("213");
        customer.setRegisterDate(new Date());
        customer.setReviews(new HashSet<Review>());
        customer.setBookOrders(new HashSet<BookOrder>());

        Customer updatedCustomer = customerDAO.update(customer);

        assertEquals("Sakhtemoon", updatedCustomer.getAddress());
    }

    @Test
    public void testGet() {

        int customerId = 12;

        Customer customer = customerDAO.get(customerId);

        assertEquals("Ahwaz", customer.getCity());
    }

    @Test
    public void testDelete() {

        int id = 11;
        customerDAO.delete(id);

        assertNull(customerDAO.get(11));
    }

    @Test
    public void testListAll() {

        List<Customer> customers = customerDAO.listAll();

        for (Customer customer : customers) {
            System.out.println(customer.getCustomerId() + ".  " + customer.getFullName() + "  " + customer.getEmail());
        }

        assertTrue(customers.size() > 0);
    }

    @Test
    public void testCount() {

        long totalCustomers = customerDAO.count();

        assertEquals(2, totalCustomers);
    }

    @Test
    public void testFindByEmail() {

        Customer customerFoundByEmail = customerDAO.findByEmail("Jean.Reno@gmail.com");

        System.out.println(customerFoundByEmail.getFullName() + "-" + customerFoundByEmail.getCountry());

        assertEquals(13, (long) customerFoundByEmail.getCustomerId());
    }

    @Test
    public void testCheckLogin() {

        Customer result = customerDAO.checkLogin("Ahmad.Mahmoud@gmail.com", "hamsayeha");

        assertNotNull(result);
    }
}