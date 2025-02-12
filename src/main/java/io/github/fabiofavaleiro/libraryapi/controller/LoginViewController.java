package io.github.fabiofavaleiro.libraryapi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String paginalogin(){

        return "login";

    }

}
