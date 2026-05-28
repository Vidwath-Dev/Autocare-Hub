package com.project.controller;

import com.project.dao.UserDAO;
import com.project.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = trim(request.getParameter("email"));
        String password = trim(request.getParameter("password"));

        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Email and password are required.");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            return;
        }

        try {
            User user = userDAO.login(email, password);
            if (user == null) {
                request.setAttribute("error", "Invalid email or password.");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                return;
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30 * 60);
            if (user.isAdmin()) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                response.sendRedirect(request.getContextPath() + "/dashboard");
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Database connection failed. Please check MySQL credentials and import schema.sql.");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }
}
