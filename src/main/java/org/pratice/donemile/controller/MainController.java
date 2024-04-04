package org.pratice.donemile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage() {
//        System.out.println("======" +principal.getName());
        return "main";
    }
}
