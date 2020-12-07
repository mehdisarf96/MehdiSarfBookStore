package com.mehdisarf.mehdisarfbookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JpaDAO<E> {

    protected EntityManager entityManager;

    public JpaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public E create(E entity) {

        entityManager.getTransaction().begin();

        entityManager.persist(entity);

        entityManager.flush();
        entityManager.refresh(entity);
        entityManager.getTransaction().commit();

        return entity;
    }

    public E update(E entity) {

        entityManager.getTransaction().begin();

        E theEntity = entityManager.merge(entity);

        entityManager.getTransaction().commit();

        return theEntity;
    }

    public E find(Class<E> type, Object id) {

        E entity = entityManager.find(type, id);

        if (entity != null)
            entityManager.refresh(entity);

        return entity;
    }

    public void delete(Class type, Object id) {

        entityManager.getTransaction().begin();

        Object theEntity = entityManager.getReference(type, id); // Vs. entityManager.find()

        entityManager.remove(theEntity);

        entityManager.getTransaction().commit();
    }

    public List<E> findWithNamedQuery(String queryName) {
        Query query = entityManager.createNamedQuery(queryName);
        return query.getResultList();
    }

    public List<E> findWithNamedQuery(String queryName, String parameterName, Object parameterValue) {
        Query query = entityManager.createNamedQuery(queryName);
        query.setParameter(parameterName, parameterValue);
        return query.getResultList();
    }

    // accept a map of parameters instead of accepting only one parameters.
    public List<E> findWithNamedQuery(String queryName, Map<String, Object> parameters) {

        Query query = entityManager.createNamedQuery(queryName);

        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet(); // returns all entries of the map as a Set.

        for (Map.Entry<String, Object> entry : rawParameters) {

            query.setParameter(entry.getKey(), entry.getValue());

        }

        return query.getResultList();
    }

    public long countWithNamedQuery(String queryName) {
        Query query = entityManager.createNamedQuery(queryName);
        return (long) query.getSingleResult();
    }

    public long countWithNamedQuery(String queryName, Map parameters) {
        return 1L;
    }
}
