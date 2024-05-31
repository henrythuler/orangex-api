package br.com.orangex.api.security;

import br.com.orangex.api.exception.AuthException;
import br.com.orangex.api.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${jwt.secret.key}")
    private String key;

    public String generateToken(User user){

        try{
            Algorithm algorithm = Algorithm.HMAC256(key);
            String token = JWT.create()
                    .withIssuer("orangex_api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(3L)))
                    .sign(algorithm);
            return token;
        }catch(JWTCreationException e){
            throw new AuthException(e.getMessage());
        }

    }

    public String verifyToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(key);
            //Returning email
            return JWT.require(algorithm)
                    .withIssuer("orangex_api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException e){
            throw new AuthException("Token verification failed...");
        }
    }

}
