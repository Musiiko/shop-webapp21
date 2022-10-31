package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.domain.Product;
import org.example.services.UpdateService;

import java.io.IOException;

@WebServlet(name = "insertProduct", value = "/insertProduct")
public class InsertProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/insertProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        UpdateService updateService = new UpdateService();
        float price = Float.parseFloat(request.getParameter("price"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Product phone = new Product(price, name, description);
        try {
            updateService.insertProduct(phone);
            if (phone == null) {
                request.setAttribute("phone", "");
            } else {
                request.setAttribute("phone", phone);
            }
        }
        catch (Exception ex){
            request.setAttribute("message", ex.getMessage());
        }

        request.getRequestDispatcher("/jsp/insertProduct.jsp").forward(request, response);
    }
}
