package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.dao.UserDAO;
import com.mehdisarf.mehdisarfbookstore.entity.Users;
import org.junit.*;

import javax.persistence.*;

import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest extends BaseDAOTest {

    private static UserDAO userDAO;

    @BeforeClass
    public static void setUpClass() throws Exception {

        BaseDAOTest.setUpClass();
        userDAO = new UserDAO(entityManager);
    }

    @Test
    public void testCreate() {

        Users theUser = new Users();
        theUser.setFullName("MehdiSarf");
        theUser.setEmail("abc@gmail.com");
        theUser.setPassword("124");

        Users persistedUser = userDAO.create(theUser);

        assertNotNull(persistedUser);
        assertTrue(persistedUser.getUserId() > 0);
    }

    @Test(expected = PersistenceException.class)
    public void testCreateFieldsNotSet() {

        Users theUser = new Users();
        theUser.setFullName("MehdiAkhavanSales");
        theUser.setEmail("akhsales@gmail.com");

        Users persistedUser = userDAO.create(theUser);
    }

    @Test
    public void testUpdate() {

        Users user = new Users();
        user.setUserId(20);
        user.setFullName("Raul Gonzalez");
        user.setPassword("halamadrid");
        user.setEmail("raul_g@gmail.com");

        Users updatedUser = userDAO.update(user);

        assertNotNull(updatedUser);
    }

    @Test
    public void testGetFound() {

        int userId = 19;

        Users retrievedUser = userDAO.get(userId);

        assertNotNull(retrievedUser);
    }

    @Test
    public void testGetNotFound() {

        int userId = 1;

        Users theUser = userDAO.get(userId);

        assertNull(theUser);
    }

    @Test
    public void testDeleteUser() {
        int userId = 22;

        userDAO.delete(userId);

        assertNull(userDAO.get(userId));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteNonExistUser() {
        int userId = 564;

        userDAO.delete(userId);
    }

    @Test
    public void testListAll() {

        List<Users> users = userDAO.listAll();
        //assertNotNull(users);
        assertTrue(users.size() > 0);

        for (Users users1 : users) {
            System.out.println(users1.getFullName());
        }
    }

    @Test
    public void testCount() {
        long numberOfUsers = userDAO.count();
        assertEquals(3, numberOfUsers);
    }

    @Test
    public void testFindByEmail() {

        Users theUser = userDAO.findByEmail("jd.Salinger@gmail.com");
        assertNotNull(theUser);
    }

    @Test
    public void testCheckLogin() {
        assertTrue(userDAO.checkLogin("sartre@gmail.com","Jean-Paul "));
    }

    @Test
    public void testCheckLoginFailed() {
        assertFalse(userDAO.checkLogin("s623artre@gmail.com","Jean-Paul "));
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        BaseDAOTest.tearDownClass();
    }

}