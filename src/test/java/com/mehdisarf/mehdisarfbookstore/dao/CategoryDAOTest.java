package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.entity.Category;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryDAOTest extends BaseDAOTest {

    private static CategoryDAO categoryDAO;

    @BeforeClass
    public static void setUpClass() throws Exception {

        BaseDAOTest.setUpClass();
        categoryDAO = new CategoryDAO(entityManager);
    }

    @Test
    public void testCreate() {

        Category newCategory = new Category("History");

        Category persistedCategory = categoryDAO.create(newCategory);

        assertNotNull(persistedCategory);
        assertTrue(persistedCategory.getCategoryId() > 0);
    }

    @Test
    public void testUpdate() {
        Category category = new Category("Fantasy");
        category.setCategoryId(13);

        Category updatedCategory = categoryDAO.update(category);

        assertEquals(category.getName(), updatedCategory.getName());
    }

    @Test
    public void testGetFound() {
        int categoryId = 12;

        Category retrievedCategory = categoryDAO.get(categoryId);

        System.out.println(retrievedCategory.getName());
        assertNotNull(retrievedCategory);
    }

    @Test
    public void testGetNotFound() {

        int categoryId = 1;

        Category retrievedCategory = categoryDAO.get(categoryId);

        assertNull(retrievedCategory);
    }

    @Test
    public void testDelete() {

        int categoryId = 11;

        categoryDAO.delete(categoryId);

        assertNull(categoryDAO.get(categoryId));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteNonExistCategory() {

        int categoryId = 2;

        categoryDAO.delete(categoryId);
    }

    @Test
    public void testListAll() {

        List<Category> categories = categoryDAO.listAll();

        assertTrue(categories.size() > 0);

        for (Category category : categories) {
            System.out.println(category.getName());
        }
    }

    @Test
    public void testCount() {
        long numberOfCategories = categoryDAO.count();
        assertEquals(2, numberOfCategories);
    }

    @Test
    public void testFindByName() {

        String categoryName = "History";
        Category category = categoryDAO.findByName(categoryName);

        assertNotNull(category);
        assertEquals("History", category.getName());
    }

    @Test
    public void testFindByNameNotFound() {

        String categoryName = "History2";
        Category category = categoryDAO.findByName(categoryName);

        assertNull(category);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        BaseDAOTest.tearDownClass();
    }

}