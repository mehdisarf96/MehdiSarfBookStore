package com.mehdisarf.mehdisarfbookstore.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BaseService {

    protected static EntityManagerFactory factory = Persistence.createEntityManagerFactory("MehdiSarfBookStore");
    protected static EntityManager entityManager = factory.createEntityManager();

}
