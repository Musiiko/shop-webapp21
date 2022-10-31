package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.domain.Order;
import org.example.services.UpdateService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "generateNewOrderFromProductsOrderedToday", value = "/generateNewOrderFromProductsOrderedToday")
public class GenerateNewOrderFromProductsOrderedTodayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/generateNewOrderFromProductsOrderedToday.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            UpdateService updateService = new UpdateService();
            String number = request.getParameter("number");
            Order order = null;
            order = updateService.generateNewOrderFromProductsOrderedToday(number);
            if (order == null) {
                request.setAttribute("order", "");
                request.setAttribute("message", "No products ordered today");
            } else {
                request.setAttribute("order", order);
            }
        }
        catch (Exception ex){
            request.setAttribute("message", ex.getMessage());
        }
        request.getRequestDispatcher("/jsp/generateNewOrderFromProductsOrderedToday.jsp").forward(request, response);
    }
}
