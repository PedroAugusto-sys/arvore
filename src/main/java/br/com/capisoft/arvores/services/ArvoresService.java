package br.com.capisoft.arvores.services;

import br.com.capisoft.arvores.models.Arvore;
import br.com.capisoft.arvores.models.Node;
import br.com.capisoft.arvores.utils.Dados;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArvoresService {

    private Dados dados = new Dados();

    private static Arvore arvore;

    public ResponseEntity arquivoLeituraTeste(MultipartFile arquivo) throws IOException {
        dados.adicionarTextoTeste(arquivo);
        return ResponseEntity.ok("feito meu chapa");
    }

    public ResponseEntity montarArvore(MultipartFile arquivo) throws IOException {
        for (String palavraNode : dados.carregarListaDePalavras(arquivo)){
            if (this.arvore == null){
                this.arvore = new Arvore(new Node(palavraNode));
            }
            preencherArvore(this.arvore.getRoot(),new Node(palavraNode));
        }
        return ResponseEntity.ok("deu bom");
    }


    private void preencherArvore(Node raiz, Node novoNode){
        int res = raiz.getTexto().compareTo(novoNode.getTexto());
        if (res < 0){
            if (raiz.contemNoEsquerdo()){
                this.preencherArvore(raiz.getNoEsquerdo(),novoNode);
            } else {
                raiz.adicionarNaEsquerda(novoNode);
            }
        } else if (res > 0){
            if (raiz.contemNoDireito()) {
                this.preencherArvore(raiz.getNoDireito(),novoNode);
            } else {
                raiz.adicionarNaDireita(novoNode);
            }
        }
    }
}
