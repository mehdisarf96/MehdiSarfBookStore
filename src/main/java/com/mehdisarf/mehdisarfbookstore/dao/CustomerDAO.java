package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.entity.Customer;
import com.mehdisarf.mehdisarfbookstore.entity.Users;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

    @Override
    public Customer create(Customer customer) {
        return super.create(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return super.update(customer);
    }

    @Override
    public Customer get(Object id) {
        return super.find(Customer.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Customer.class, id);
    }

    @Override
    public List<Customer> listAll() {
        return super.findWithNamedQuery("Customer.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Customer.countAll");
    }

    public Customer findByEmail(String email) {

        List<Customer> customersListFoundByEmail = super.findWithNamedQuery("Customer.findByEmail", "email", email);

        if (customersListFoundByEmail != null && customersListFoundByEmail.size() == 1) {
            return customersListFoundByEmail.get(0);
        }
        return null;
    }

    public Customer checkLogin(String email, String password) {

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("email", email);
        parameters.put("pass", password);

        List<Customer> foundCustomers = super.findWithNamedQuery("Customer.checkLogin", parameters);

        if (foundCustomers != null && foundCustomers.size() == 1)
            return foundCustomers.get(0);

        return null;
    }
}
