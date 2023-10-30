package com.crescendo.booking.crescendobookingspring.security;

import com.crescendo.booking.crescendobookingspring.security.jwt.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class OwnResourceFilter extends OncePerRequestFilter {

    public OwnResourceFilter() { }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
        String endpoint = request.getRequestURI().substring(request.getContextPath().length());
        if (endpoint.compareTo("/rest/profile") == 0
                && request.getMethod().compareTo(String.valueOf(HttpMethod.POST)) == 0) {
            Authentication authentication = JwtHelper.parse(request);
            // No need to check if authentication is null, JwtInterceptingFilter already did that.
            String email = (String) authentication.getPrincipal();
            String emailFromBody = request.getParameter("email");
            System.out.println(emailFromBody + " " + email);
            if (emailFromBody != null && emailFromBody.compareTo(email) != 0) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not allowed to access this resource.");
            }
        }
        filterChain.doFilter(request, response);
    }
}
