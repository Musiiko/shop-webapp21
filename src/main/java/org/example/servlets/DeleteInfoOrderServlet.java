package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.services.UpdateService;

import java.io.IOException;

@WebServlet(name = "deleteInfoOrder", value = "/deleteInfoOrder")
public class DeleteInfoOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/deleteInfoOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UpdateService updateService = new UpdateService();
        int productId = Integer.parseInt(request.getParameter("productId"));
        int count = Integer.parseInt(request.getParameter("count"));
        try {
            updateService.deleteInfoOrder(productId, count);
            request.setAttribute("message", "Success!");
        }
        catch (Exception ex){
            request.setAttribute("message", ex.getMessage());
        }
        request.getRequestDispatcher("/jsp/deleteInfoOrder.jsp").forward(request, response);

    }
}
