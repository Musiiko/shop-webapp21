package org.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.domain.User;
import org.example.domain.UserRole;

import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {
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
        if(user == null)
        {
            chain.doFilter(request, response);
        }
        else if(
                servletPath.equals("/deleteOrdersWithGivenCountAndProduct")
                        || servletPath.equals("/generateNewOrderFromProductsOrderedToday")
        ){
            if (httpRequest.getMethod().equals("GET")) {
                if (user.getRole() != UserRole.ADMIN) {
                    httpRequest.setAttribute("warningMessage", "You are not authorized to perform this operation!");
                }
                httpRequest.getRequestDispatcher("/jsp" + servletPath + ".jsp")
                        .forward(httpRequest, response);
            }
            else{
                chain.doFilter(request, response);
            }

        }
    }
}
