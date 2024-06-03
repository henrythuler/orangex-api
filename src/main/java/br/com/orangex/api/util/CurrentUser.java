package br.com.orangex.api.util;

import br.com.orangex.api.exception.AuthException;
import br.com.orangex.api.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {

    public static User getCurrentAuthenticatedUser(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null && auth.isAuthenticated()){
            User currentUser = (User) auth.getPrincipal();
            return currentUser;
        }else{
            throw new AuthException("An authentication error occurred...");
        }

    }

}
