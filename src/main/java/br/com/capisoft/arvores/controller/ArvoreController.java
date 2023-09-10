package br.com.capisoft.arvores.controller;

import br.com.capisoft.arvores.services.ArvoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/arvore")
public class ArvoreController {

    @Autowired
    private ArvoresService arvoresService;

    @GetMapping("/buscaPalavra={palavra}")
    public ResponseEntity uploadArquivoTeste(@PathVariable String palavra) throws IOException {
        return arvoresService.buscarNode(palavra);
    }
}
