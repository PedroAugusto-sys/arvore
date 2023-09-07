package br.com.capisoft.arvores.services;

import br.com.capisoft.arvores.models.Arvore;
import br.com.capisoft.arvores.models.Node;
import br.com.capisoft.arvores.repositories.ArvoreRepository;
import br.com.capisoft.arvores.utils.Dados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArvoresService {

    private Dados dados = new Dados();

    private ControleArvores arvoreSimples;
    private ControleArvores arvoreAVL;

    @Autowired
    private ArvoreRepository arvoreHistorico;

    public ResponseEntity arquivoLeituraTeste(MultipartFile arquivo) throws IOException {
        dados.adicionarTextoTeste(arquivo);
        return ResponseEntity.ok("lido com sucesso");
    }

    public ResponseEntity obterTXTEMontarArvoreSimples(MultipartFile arquivo) throws IOException {
        for (String palavraNode : dados.carregarListaDePalavras(arquivo)){
            adicionarNaArvore(palavraNode,false);
        }
        return ResponseEntity.ok("deu bom");
    }

    public ResponseEntity obterTXTEMontarArvoreAVL(MultipartFile arquivo) throws IOException {
        for (String palavraNode : dados.carregarListaDePalavras(arquivo)){
            adicionarNaArvore(palavraNode,true);
        }
        return ResponseEntity.ok("deu bom");
    }

    public ResponseEntity adicionarNodeArvoreSimples(String texto){
        adicionarNaArvore(texto,false);
        return ResponseEntity.ok("deu bom");
    }

    public ResponseEntity adicionarNodeArvoreAVL(String texto){
        adicionarNaArvore(texto, true);
        return ResponseEntity.ok("deu bom");
    }

    private void adicionarNaArvore(String textoNode, boolean isAVL){
        Node novoNode = new Node(textoNode);
        Arvore arvore = new Arvore(novoNode, isAVL);
        if (isAVL){
            if(arvoreAVL == null) {
                arvoreAVL = new ControleArvores(arvore);
            } else {
                arvoreAVL.adicionarNaArvore(novoNode);
            }
        } else {
            if (arvoreSimples == null){
                arvoreSimples = new ControleArvores(arvore);
            } else {
                arvoreSimples.adicionarNaArvore(novoNode);
            }
        }
    }
}
