package com.sfl.cafe.controller;


import com.sfl.cafe.model.User;
import com.sfl.cafe.model.enums.UserType;
import com.sfl.cafe.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {

    private PasswordEncoder passwordEncoder;


    @Autowired
    public MainController(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String loginPage(ModelMap map, HttpServletRequest request){
        map.addAttribute("login_user", new User());
        if(request.getRequestURL().toString().contains("error"));
            map.addAttribute("errorMsg", "Bad credentials");
        return "index";
    }



    @GetMapping("/login-success")
    public String loginSuccess(ModelMap modelMap,
                               @AuthenticationPrincipal UserDetails userDetails
                               ) {
        CurrentUser currentUser = (CurrentUser) userDetails;
        modelMap.addAttribute("isLoggedIn", userDetails != null);

        if (currentUser != null) {
            User user = currentUser.getUser();
            modelMap.addAttribute("currentUser", user);
            if (user.getType()== UserType.MANAGER) {
                return "redirect:/manager/home";
            }else {
                return "redirect:/waiter/home";

            }
        }
        return "redirect:/home";


    }





}
