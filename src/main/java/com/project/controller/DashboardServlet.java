package com.project.controller;

import com.project.dao.BookingDAO;
import com.project.dao.VehicleDAO;
import com.project.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DashboardServlet extends HttpServlet {
    private final VehicleDAO vehicleDAO = new VehicleDAO();
    private final BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        if (user.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }
        try {
            request.setAttribute("vehicles", vehicleDAO.findByUserId(user.getId()));
            request.setAttribute("bookings", bookingDAO.findByUserId(user.getId()));
            request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Unable to load dashboard", e);
        }
    }
}
