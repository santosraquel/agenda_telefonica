package br.ifpr.agenda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalControle {

    @GetMapping("/agenda")
    public String acessarPrincipal() {
        return "agenda/home";
    }
}
