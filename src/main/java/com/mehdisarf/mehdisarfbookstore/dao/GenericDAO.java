package com.mehdisarf.mehdisarfbookstore.dao;

import java.util.List;

public interface GenericDAO<T> {

    T create(T t);

    T update(T t);

    T get(Object id);

    void delete(Object id);

    List<T> listAll();

    // returns the total number of rows in a table.
    long count();
}
