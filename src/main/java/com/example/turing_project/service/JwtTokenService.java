package com.example.turing_project.service;

import com.example.turing_project.entity.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {

    @Autowired
    private EmployeeService employeeService;

    private final String secret_key = "never gonna give you up";

    public String generateToken(String username, String password) throws Exception {
        if (username == null || password == null)
            return null;
        Employee employee = employeeService.getEmployeeByUsername(username);
        if (employee==null){
            return null;
        }
        Map<String, Object> tokenData = new HashMap<>();
        if (password.equals(employee.getPassword())) {
            tokenData.put("userID", employee.getId().toString());
            tokenData.put("username", username);

            JwtBuilder jwtBuilder = Jwts.builder();

            return jwtBuilder.setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                    .setClaims(tokenData)
                    .signWith(SignatureAlgorithm.HS256, secret_key).compact();
        } else {
            throw new Exception("Authentication error");
        }
    }
    public Boolean validateToken(String token) {
        try {
            final Claims claims = extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret_key).build().parseClaimsJws(token)
                .getBody();
    }

}
