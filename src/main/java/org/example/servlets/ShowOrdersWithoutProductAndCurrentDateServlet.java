package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.domain.Order;
import org.example.services.SelectService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "showOrdersWithoutProductAndCurrentDate", value = "/showOrdersWithoutProductAndCurrentDate")
public class ShowOrdersWithoutProductAndCurrentDateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/showOrdersWithoutProductAndCurrentDate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SelectService selectService = new SelectService();
        String productName = request.getParameter("productName");

        try {
            List<Order> orders = selectService.showOrdersWithoutProductAndCurrentDate(productName);
            String query = "List of orders without product '" + productName + "' and date " + LocalDate.now();
            request.setAttribute("query", query);
            if (orders.size() == 0) {
                request.setAttribute("orders", "");
                request.setAttribute("message", "Orders matching the query were not found!");
            } else {
                request.setAttribute("orders", orders);
            }
        }
        catch (Exception ex){
            request.setAttribute("message", ex.getMessage());
        }
        request.getRequestDispatcher("/jsp/showOrdersWithoutProductAndCurrentDate.jsp").forward(request, response);
    }
}
