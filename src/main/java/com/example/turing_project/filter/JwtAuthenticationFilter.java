package com.example.turing_project.filter;

import com.example.turing_project.entity.Employee;
import com.example.turing_project.service.EmployeeService;
import com.example.turing_project.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private final JwtTokenService jwtTokenService;
    @Autowired
    private final EmployeeService employeeService;

    public JwtAuthenticationFilter(JwtTokenService jwtTokenService, EmployeeService employeeService) {
        this.jwtTokenService = jwtTokenService;
        this.employeeService = employeeService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getRequestURI();

        if (requestPath.equals("/auth/register") || requestPath.equals("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        if (token == null || !jwtTokenService.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return;
        } else {
            Claims claims = jwtTokenService.getClaims(token);
            String email = claims.get("email").toString();

            Employee employee = employeeService.getEmployeeByEmail(email);
            if (employee != null) {

                UserDetails userDetails = new User(
                        employee.getEmail(),
                        employee.getPassword(),
                        new ArrayList<>()
                );

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        }
    }
}
