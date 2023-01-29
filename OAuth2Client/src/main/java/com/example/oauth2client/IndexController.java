package com.example.oauth2client;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();
        System.out.println("authentication = " + authentication);
        return "home";
    }
}
