package com.mehdisarf.mehdisarfbookstore.dao;

import com.mehdisarf.mehdisarfbookstore.entity.Users;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

    public Users create(Users user) {
        return super.create(user);
    }

    public Users update(Users user) {
        return super.update(user);
    }

    @Override
    public Users get(Object id) {
        return super.find(Users.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Users.class, id);
    }

    @Override
    public List<Users> listAll() {
        return super.findWithNamedQuery("Users.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Users.countAll");
    }

    public Users findByEmail(String email) {
        List<Users> usersList = super.findWithNamedQuery("Users.findByEmail", "email", email);

        if (usersList != null && usersList.size() == 1)
            return usersList.get(0);

        return null;
    }

    public boolean checkLogin(String email, String password) {

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("email", email);
        parameters.put("pass", password);

        // remember that Users.checkLogin has 2 parameters unlike other Named Query that have one param.
        List<Users> foundUsers = super.findWithNamedQuery("Users.checkLogin", parameters);

        if (foundUsers.size() == 1)
            return true;

        return false;
    }
}
