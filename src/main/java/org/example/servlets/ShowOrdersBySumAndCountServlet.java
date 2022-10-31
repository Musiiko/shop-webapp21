package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.domain.Order;
import org.example.services.SelectService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "showOrdersBySumAndCount", value = "/showOrdersBySumAndCount")
public class ShowOrdersBySumAndCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/showOrdersBySumAndCount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            SelectService selectService = new SelectService();
            float sum = Float.parseFloat(request.getParameter("sum"));
            int count = Integer.parseInt(request.getParameter("count"));
            List<Order> orders = selectService.showOrdersBySumAndCount(sum, count);
            String query = "List of orders with sum < " + sum + " and count of products = " + count + ".";
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
        request.getRequestDispatcher("/jsp/showOrdersBySumAndCount.jsp").forward(request, response);
    }
}
