package com.elearning.filer;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFiler extends BasicAuthenticationFilter {
    private UserDetailsService userDetailsService;


    public JWTAuthorizationFiler(AuthenticationManager authenticationManager
            , UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String JWT_SECRET = "CYBERELEARN";
        String tokenBearer = request.getHeader("Authorization");
        if (tokenBearer != null && tokenBearer.startsWith("Bearer ")) {
String token =tokenBearer.replace("Bearer ", "");
String email = Jwts.parser()
        .setSigningKey(JWT_SECRET)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
            UserDetails userDetail = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request,response);
    }
}
