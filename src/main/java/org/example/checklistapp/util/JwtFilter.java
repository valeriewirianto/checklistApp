package org.example.checklistapp.util;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, java.io.IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtUtil.isValid(token)) {
                Claims claims = jwtUtil.extractClaims(token);

                String username = claims.getSubject();
                String role = claims.get("role", String.class);
                Long userId = claims.get("userId", Long.class);

                // mark user as authenticated
                var auth = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        username, null, java.util.Collections.emptyList()
                );

                auth.setDetails(claims);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }
}
