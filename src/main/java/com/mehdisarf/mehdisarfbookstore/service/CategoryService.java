package com.mehdisarf.mehdisarfbookstore.service;

import com.mehdisarf.mehdisarfbookstore.dao.BookDAO;
import com.mehdisarf.mehdisarfbookstore.dao.CategoryDAO;
import com.mehdisarf.mehdisarfbookstore.entity.Category;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryService {

    private CategoryDAO categoryDAO;

    private HttpServletRequest request;
    private HttpServletResponse response;

    public CategoryService(HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;

        categoryDAO = new CategoryDAO();
    }

    public void list(String msg) throws ServletException, IOException {

        List<Category> categories = categoryDAO.listAll();

        request.setAttribute("categories", categories);

        if (msg != null)
            request.setAttribute("msg", msg);

        String categoryListPage = "category_list.jsp";
        dispatch(categoryListPage);
    }

    public void list() throws ServletException, IOException {
        list(null);
    }

    public List<Category> listAll() {
        return categoryDAO.listAll();
    }

    public void create() throws ServletException, IOException {

        String categoryName = request.getParameter("name");

        Category categoryFoundByName = categoryDAO.findByName(categoryName);

        if (categoryFoundByName == null) {

            Category newCategory = new Category(categoryName);
            categoryDAO.create(newCategory);
            list("New Category Created Successfully!");

        } else {

            String msg = "A Category with this name already exists: " + categoryName;
            request.setAttribute("msg", msg);

            dispatch("message.jsp");
        }
    }

    public void edit() throws ServletException, IOException {

        int categoryId = Integer.parseInt(request.getParameter("categoryid"));
        Category theCategory = null;

        try {

            theCategory = categoryDAO.get(categoryId);

        } catch (EntityNotFoundException ex) {

            String msg = "Could not find category with ID " + categoryId;
            request.setAttribute("msg", msg);

            dispatch("message.jsp");
        }

        request.setAttribute("theCategory", theCategory);

        String editPage = "category_edit.jsp";
        dispatch(editPage);
    }

    public void update() throws ServletException, IOException {

        String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("id"));

        Category categoryFoundByName = categoryDAO.findByName(name);
        Category categoryFoundByID = categoryDAO.get(id);

        if ((categoryFoundByName != null) && (categoryFoundByName.getCategoryId() != categoryFoundByID.getCategoryId())) {

            String msg = "Update Failed! A Category with this Name already exists: " + name;
            request.setAttribute("msg", msg);

            dispatch("message.jsp");

        } else {

            Category theCategory = new Category(id, name);
            categoryDAO.update(theCategory);

            String msg = "User Has Been Updated Successfully!";
            list(msg);
        }
    }

    public void delete() throws ServletException, IOException {

        int categoryId = Integer.parseInt(request.getParameter("categoryid"));
        long totalNumberOfBooks = new BookDAO().countByCategory(categoryId);


        if (totalNumberOfBooks == 0) {

            try {
                categoryDAO.delete(categoryId);

            } catch (EntityNotFoundException ex) { // ye exception dg ke marbut be commit o tx hast dad.

                String msg = "Could not find category with ID " + categoryId + ", or it might have been deleted. ";
                request.setAttribute("msg", msg);

                dispatch("message.jsp");
            }

            list("The Category Has Been Deleted Successfully!");

        } else {

            list("Could not delete category with ID " + categoryId + ", Because This category contains some books. ");
        }
    }

    private void dispatch(String destination) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }
}
