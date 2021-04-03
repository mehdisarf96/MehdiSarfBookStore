package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.entity.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

    @Override
    public Category create(Category category) {
        return super.create(category);
    }

    @Override
    public Category update(Category category) {
        return super.update(category);
    }

    @Override
    public Category get(Object id) {
        return super.find(Category.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Category.class, id);
    }

    @Override
    public List<Category> listAll() {
        return super.findWithNamedQuery("Category.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Category.countAll");
    }

    public Category findByName(String categoryName) {

        List<Category> categoryList = super.findWithNamedQuery("Category.find", "name", categoryName);

        if (categoryList != null && categoryList.size() == 1)
            return categoryList.get(0);

        return null;
    }
}
