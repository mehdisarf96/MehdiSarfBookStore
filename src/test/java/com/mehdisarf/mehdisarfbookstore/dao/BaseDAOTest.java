package com.mehdisarf.mehdisarfbookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDAOTest {

    protected static EntityManagerFactory managerFactory;
    protected static EntityManager entityManager;

    public static void setUpClass() throws Exception {
        managerFactory = Persistence.createEntityManagerFactory("MehdiSarfBookStore");
        entityManager = managerFactory.createEntityManager();
    }

    public static void tearDownClass() throws Exception {
        entityManager.close();
        managerFactory.close();
    }
}
