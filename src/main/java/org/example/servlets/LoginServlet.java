package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.domain.Order;
import org.example.domain.User;
import org.example.services.UserService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("warningMessage", request.getSession().getAttribute("warningMessage"));
        request.getSession().removeAttribute("warningMessage");
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            User user = userService.verifyLoginAndPassword(login,password);
            if (user == null) {
                request.setAttribute("warningMessage", "Invalid Login Credentials");
            } else {
                request.getSession().setAttribute("user", user);
                Object obj = request.getSession().getAttribute("previousPage");
                if(obj != null) {
                    request.getSession().removeAttribute("previousPage");
                    response.sendRedirect(obj.toString());
                }
                else{
                    response.sendRedirect("index.jsp");
                }
                return;
            }
        }
        catch (Exception ex){
            request.setAttribute("message", ex.getMessage());
        }
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}
