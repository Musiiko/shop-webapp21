package org.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.domain.User;

import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String servletPath = httpRequest.getServletPath();
        User user = (User) httpRequest.getSession().getAttribute("user");
        if(user != null)
        {
            chain.doFilter(request, response);
        }
        else if(
                servletPath.equals("/deleteOrdersWithGivenCountAndProduct")
                || servletPath.equals("/generateNewOrderFromProductsOrderedToday")
        ){
            //httpRequest.setAttribute("message", "You are not authenticated! Please enter login and password!");
            httpRequest.getSession().setAttribute("warningMessage", "You are not authenticated to perform this operation!");
            httpRequest.getSession().setAttribute("previousPage", httpRequest.getContextPath() + servletPath);
            httpResponse.sendRedirect("login");
            return;
        }
        chain.doFilter(request, response);

    }
}
