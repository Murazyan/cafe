package com.sfl.cafe.handler;

import com.sfl.cafe.model.enums.UserType;
import com.sfl.cafe.security.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j

public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException, ServletException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        String str = "";
        if (auth != null) {
            UsernamePasswordAuthenticationToken currentUser = (UsernamePasswordAuthenticationToken) auth;
            if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority( UserType.MANAGER.name()))) {
                str = "/manager/home";
            }else if(currentUser.getAuthorities().contains(new SimpleGrantedAuthority( UserType.WAITER.name()))){
                str = "/waiter/home";
            }

            log.warn("User: " + auth.getName()
                    + " attempted to access the protected URL: "
                    + request.getRequestURI());
        }

        response.sendRedirect(request.getContextPath() + str);
    }
}