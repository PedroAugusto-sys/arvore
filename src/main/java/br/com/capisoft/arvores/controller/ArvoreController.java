package br.com.capisoft.arvores.controller;

import br.com.capisoft.arvores.services.ArvoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/arvores")
public class ArvoreController {

    @Autowired
    private ArvoresService arvoresService;
}
