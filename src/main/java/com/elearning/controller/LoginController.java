package com.elearning.controller;

import com.elearning.dto.UserLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = {"/sign-in"})
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Medthod creates Token
            Date now = new Date();
            String token = Jwts.builder()
                    .setSubject(userLogin.getEmail())
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() +864000000L))
                    .signWith(SignatureAlgorithm.HS512, "CYBERELEARN")
                    .compact();
            return new ResponseEntity<String>(token, HttpStatus.OK);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Email or password incorrect", HttpStatus.BAD_REQUEST);
    }

}
