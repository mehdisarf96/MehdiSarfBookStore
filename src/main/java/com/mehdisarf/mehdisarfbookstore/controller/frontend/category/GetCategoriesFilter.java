package com.mehdisarf.mehdisarfbookstore.controller.frontend.category;

import com.mehdisarf.mehdisarfbookstore.dao.CategoryDAO;
import com.mehdisarf.mehdisarfbookstore.entity.Category;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "GetCategoriesFilter", urlPatterns = "/*")
public class GetCategoriesFilter implements Filter {

    private final CategoryDAO categoryDAO = new CategoryDAO();
    private FilterConfig filterConfig;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getRequestURI().startsWith(filterConfig.getServletContext().getContextPath() + "/admin")) { // afarin be khodam
            chain.doFilter(req, resp);
        } else {

            List<Category> categories = categoryDAO.listAll();

            req.setAttribute("categories", categories);

            System.out.println(" --'fetched Categories'-- ");

            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }
}
