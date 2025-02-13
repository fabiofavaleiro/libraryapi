package io.github.fabiofavaleiro.libraryapi.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String paginalogin(){
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    public  String paginaHome(Authentication authentication){
        return "Ola" + authentication.getName();
    }

}
