package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.domain.Order;
import org.example.services.SelectService;

import java.io.IOException;

@WebServlet(name = "showOrderByNumber", value = "/showOrderByNumber")
public class ShowOrderByNumberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/showOrderByNumber.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SelectService selectService = new SelectService();
        String number = request.getParameter("number");

        try {
            Order order = selectService.showOrderByNumber(number);
            if (order == null) {
                request.setAttribute("order", "");
                request.setAttribute("message", "Order number not found!");
            } else {
                request.setAttribute("order", order);
            }
        }
        catch (Exception ex){
            request.setAttribute("message", ex.getMessage());
        }
        request.getRequestDispatcher("/jsp/showOrderByNumber.jsp").forward(request, response);
    }
}
