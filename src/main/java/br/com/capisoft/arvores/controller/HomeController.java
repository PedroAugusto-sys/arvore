package br.com.capisoft.arvores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/arvores")
public class HomeController {

    @GetMapping
    public String homepage() {
        System.out.println("acessou aqui");
        return "index";
    }
}
