package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.web.csrf.CsrfToken;


@RestController
public class HelloController {

    @GetMapping("/welcome")
    public String greet(){
        return "Welcome to the world of OAuth2 and Spring Security!";
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            System.out.println("Authenticated user: " + principal.getAttribute("name"));
            return Collections.singletonMap("name", principal.getAttribute("name"));
        } else {
            System.out.println("No authenticated user found.");
            return Collections.singletonMap("error", "No authenticated user");
        }
    }
}
