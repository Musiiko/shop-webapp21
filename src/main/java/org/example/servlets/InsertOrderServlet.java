package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.domain.Order;
import org.example.services.UpdateService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "insertOrder", value = "/insertOrder")
public class InsertOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/insertOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateService updateService = new UpdateService();

        String number = request.getParameter("number");
        Order order = new Order(LocalDate.now(), number);
        try {
            updateService.insertOrder(order);
            if (order == null) {
                request.setAttribute("order", "");
            } else {
                request.setAttribute("order", order);
            }
        }
        catch (Exception ex){
            request.setAttribute("message", ex.getMessage());
        }
        request.getRequestDispatcher("/jsp/insertOrder.jsp").forward(request, response);

    }
}
