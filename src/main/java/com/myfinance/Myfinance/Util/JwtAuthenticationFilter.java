package com.myfinance.Myfinance.Util;

import com.myfinance.Myfinance.Repository.ProfileRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtUtil jwtUtil;
    private final ProfileRepository profileRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String header = request.getHeader("Authorization");
      if(header == null || !header.startsWith("Bearer ")) {
         filterChain.doFilter(request,response);
          return;
      }
      String token = header.substring(7);
      if(jwtUtil.validateToken(token)) {
          String email = jwtUtil.extractEmail(token);
          if(profileRepository.findByEmail(email).isPresent()) {
              filterChain.doFilter(request, response);
              return;
          }
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          response.getWriter().write("Invalid or expired token");
      }
    }
}
