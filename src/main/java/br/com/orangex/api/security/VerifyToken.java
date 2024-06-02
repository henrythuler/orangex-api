package br.com.orangex.api.security;

import br.com.orangex.api.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class VerifyToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Getting the token inside authorization header
        String token = request.getHeader("Authorization");
        if(token == null) token = "";
        else{
            //Cleaning the token (Removing Bearer word)
            token = token.replace("Bearer", "").trim();

            String username = tokenService.verifyToken(token);
            UserDetails user = userRepository.findByUsername(username);

            UsernamePasswordAuthenticationToken userAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);

        }

        filterChain.doFilter(request, response);

    }

}
