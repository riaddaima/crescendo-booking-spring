package com.crescendo.booking.crescendobookingspring.security;

import com.crescendo.booking.crescendobookingspring.security.jwt.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class OwnResourceFilter extends OncePerRequestFilter {

    public OwnResourceFilter() { }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
        Authentication authentication = JwtHelper.parse(request);
        // No need to check if authentication is null, JwtInterceptingFilter already does that.
        String email = (String) authentication.getPrincipal();
        String emailFromBody = request.getParameter("email");
        if (emailFromBody != null && emailFromBody.compareTo(email) != 0) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not allowed to access this resource.");
        }

        filterChain.doFilter(request, response);
    }
}
