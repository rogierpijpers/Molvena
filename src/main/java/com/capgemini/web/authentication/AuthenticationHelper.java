package com.capgemini.web.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class AuthenticationHelper {

    public static boolean userIsGuest(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_GUEST");
        return auth.getAuthorities().contains(grantedAuthority);
    }

    public static boolean userIsReceptionist() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_RECEPTIONIST");
        return auth.getAuthorities().contains(grantedAuthority);
    }

    public static boolean userIsAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        return auth.getAuthorities().contains(grantedAuthority);
    }

    public static String getCurrentUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        return currentUser.getUsername();
    }
}
