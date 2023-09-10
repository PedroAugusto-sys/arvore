package br.com.capisoft.arvores.models.DTOs;

import br.com.capisoft.arvores.models.Arvore;
import br.com.capisoft.arvores.models.Node;

public class GerarDTO {

    public static NodeDTO daArvore(Arvore arvore){
        return gerarDTO(arvore.getRoot());
    }

    private static NodeDTO gerarDTO(Node node){
        NodeDTO dir = null;
        NodeDTO esq = null;
        if (node.contemNoEsquerdo()){
            esq = gerarDTO(node.getNoEsquerdo());
        }
        if (node.contemNoDireito()){
            dir = gerarDTO(node.getNoDireito());
        }
        return new NodeDTO(node.getTexto(), esq, dir);
    }
}
