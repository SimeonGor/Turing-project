package com.example.turing_project.service;

import com.example.turing_project.entity.Employee;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.*;

@Service
public class JwtTokenService {

    @Autowired
    private EmployeeService employeeService;

    private final Key secretKey;

    public JwtTokenService() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateToken(String email, String password) throws Exception {
        if (email == null || password == null)
            return null;
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("email", email);
        JwtBuilder jwtBuilder = builder();
        return jwtBuilder.setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .setClaims(tokenData)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();

    }

    public boolean validateToken(String token) {
        try {
            return getClaims(token) != null;
        } catch (Exception e) {
            return false;
        }

    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
