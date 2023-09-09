package br.com.capisoft.arvores.services;

import br.com.capisoft.arvores.models.Arvore;
import br.com.capisoft.arvores.models.Node;
import br.com.capisoft.arvores.repositories.ArvoreRepository;
import br.com.capisoft.arvores.repositories.NodeRepository;
import br.com.capisoft.arvores.utils.Dados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArvoresService {

    private static Logger LOG = LoggerFactory.getLogger(ArvoresService.class);

    private Dados dados = new Dados();

    private ControleArvores arvoreSimples;
    private ControleArvores arvoreAVL;

    @Autowired
    private ArvoreRepository arvores;

    @Autowired
    private NodeRepository nodes;

    public ResponseEntity arquivoLeituraTeste(MultipartFile arquivo) throws IOException {
        dados.adicionarTextoTeste(arquivo);
        return ResponseEntity.ok("lido com sucesso");
    }

    public ResponseEntity obterTXTMontarArvoreSimples(MultipartFile arquivo) throws IOException {
        Arvore arv = null;
        for (String palavraNode : dados.carregarListaDePalavras(arquivo)){
            arv = adicionarNaArvore(palavraNode,false);
        }
        return ResponseEntity.ok(salvarArvore(arv));
    }

    public ResponseEntity obterTXTEMontarArvoreAVL(MultipartFile arquivo) throws IOException {
        Arvore arv = null;
        for (String palavraNode : dados.carregarListaDePalavras(arquivo)){
            arv = adicionarNaArvore(palavraNode,true);
        }
        return ResponseEntity.ok(salvarArvore(arv));
    }

    public ResponseEntity adicionarNodeArvoreSimples(String texto){
        Arvore arv = adicionarNaArvore(texto,false);
        return ResponseEntity.ok(salvarArvore(arv));
    }

    public ResponseEntity adicionarNodeArvoreAVL(String texto){
        Arvore arv = adicionarNaArvore(texto, true);
        return ResponseEntity.ok(salvarArvore(arv));
    }

    private Arvore adicionarNaArvore(String textoNode, boolean isAVL){
        Node novoNode = new Node(textoNode);
        Arvore arvore = new Arvore(novoNode, isAVL);
        if (isAVL){
            if(arvoreAVL == null) {
                LOG.info("Arvore AVL está vazia, vou iniciar ela com o root -> "+novoNode);
                arvoreAVL = new ControleArvores(arvore);
            } else {
                arvoreAVL.adicionarNaArvore(novoNode);
            }
            return arvoreAVL.getArvore();
        } else {
            if (arvoreSimples == null){
                LOG.info("Arvore SIMPLES está vazia, vou iniciar ela com o root -> "+novoNode);
                arvoreSimples = new ControleArvores(arvore);
            } else {
                arvoreSimples.adicionarNaArvore(novoNode);
            }
            return arvoreSimples.getArvore();
        }
    }

    private Arvore salvarArvore(Arvore arvore){
        arvore.setRoot(salvarNodes(arvore.getRoot()));
        return arvores.save(arvore);
    }

    private Node salvarNodes(Node no){
        if (no.contemNoEsquerdo()){
            salvarNodes(no.getNoEsquerdo());
        }
        if (no.contemNoDireito()){
            salvarNodes(no.getNoDireito());
        }
        return nodes.save(no);
    }
}
