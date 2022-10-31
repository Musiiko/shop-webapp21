package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.services.UpdateService;

import java.io.IOException;

@WebServlet(name = "deleteOrdersWithGivenCountAndProduct", value = "/deleteOrdersWithGivenCountAndProduct")
public class DeleteOrdersWithGivenCountAndProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/deleteOrdersWithGivenCountAndProduct.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            UpdateService updateService = new UpdateService();
            String number = request.getParameter("productName");
            Integer count = Integer.parseInt(request.getParameter("count"));
            updateService.deleteOrdersWithGivenCountAndProduct(count, number);
            request.setAttribute("message", "Success!");
        }
        catch (Exception ex){
            request.setAttribute("message", ex.getMessage());
        }
        request.getRequestDispatcher("/jsp/deleteOrdersWithGivenCountAndProduct.jsp").forward(request, response);
    }
}
