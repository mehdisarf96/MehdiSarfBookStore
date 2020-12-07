package com.mehdisarf.mehdisarfbookstore.service;

import com.mehdisarf.mehdisarfbookstore.dao.UserDAO;
import com.mehdisarf.mehdisarfbookstore.entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserService extends BaseService {

    private UserDAO userDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public UserService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        userDAO = new UserDAO(entityManager);
    }

    public void listUser(String msg) throws ServletException, IOException {

        List<Users> usersList = userDAO.listAll();
        request.setAttribute("usersList", usersList);

        if (msg != null)
            request.setAttribute("msg", msg);

        String userListPage = "user_list.jsp";

        dispatch(userListPage);
    }

    public void listUser() throws ServletException, IOException {
        listUser(null);
    }

    public void create() throws ServletException, IOException {

        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String password = request.getParameter("password");

        Users theUser = userDAO.findByEmail(email);

        if (theUser != null) {

            String msg = "An account with this email already exists: " + email;
            request.setAttribute("msg", msg);

            dispatch("message.jsp");

        } else {
            Users user = new Users(email, fullName, password);
            userDAO.create(user);
            listUser("New User Created Successfully!");
        }
    }

    public Users find(int id) {
        return userDAO.get(id);
    }

    public void update() throws ServletException, IOException {

        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("id"));

        Users queriedUser = userDAO.findByEmail(email);

        if (queriedUser == null || (queriedUser != null && queriedUser.getUserId() == id)) {

            Users theUser = new Users(id, email, fullName, password);
            userDAO.update(theUser);

            String msg = "User Has Been Updated Successfully!";
            listUser(msg);
        } else {
            String msg = "Update Failed! An account with this email already exists: " + email;
            request.setAttribute("msg", msg);

            dispatch("message.jsp");
        }
    }

    public void edit() throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userid"));
        Users theUser = find(userId);

        request.setAttribute("theUser", theUser);

        String editPage = "user_edit.jsp";

        dispatch(editPage);
    }

    public void delete() throws ServletException, IOException {

        int theUserId = Integer.parseInt(request.getParameter("userid"));

        userDAO.delete(theUserId);

        listUser("The User Has Been Deleted Successfully!");
    }

    public void login() throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isAuthenticated = userDAO.checkLogin(email, password);

        if (isAuthenticated) {

            HttpSession session = request.getSession();

            session.setAttribute("email", email);

            dispatch("index.jsp");

        } else {

            String message = "Login Faild.";

            request.setAttribute("msg", message);

            dispatch("login.jsp");
        }
    }

    public void logout() throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        session.invalidate();

        String message = "You have been successfully logged out!";

        request.setAttribute("msg", message);

        dispatch("login.jsp");
    }

    private void dispatch(String destination) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }
}
