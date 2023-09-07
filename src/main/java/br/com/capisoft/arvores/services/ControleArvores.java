package br.com.capisoft.arvores.services;

import br.com.capisoft.arvores.models.Arvore;
import br.com.capisoft.arvores.models.DirecaoNode;
import br.com.capisoft.arvores.models.Node;

public class ControleArvores {

    private Arvore arvore;

    public ControleArvores(Arvore arvore){
        this.arvore = arvore;
    }

    public Arvore getArvore(){
        return this.arvore;
    }

    public void adicionarNaArvore(Node node){
        if (arvore.contemRaiz()){
            arvore.setRoot(node);
        } else {
            preencherArvore(arvore.getRoot(), node);
        }
    }

    private void preencherArvore(Node raiz, Node novoNode){
        if (raiz == null){
            raiz = novoNode;
            adicionarNode(raiz, novoNode, DirecaoNode.NODE_ATUAL);
        }
        int res = raiz.getTexto().compareTo(novoNode.getTexto());
        if (res < 0){
            if (raiz.contemNoEsquerdo()){
                this.preencherArvore(raiz.getNoEsquerdo(),novoNode);
            } else {
                adicionarNode(raiz,novoNode,DirecaoNode.ESQUERDA);
            }
        } else if (res > 0){
            if (raiz.contemNoDireito()) {
                this.preencherArvore(raiz.getNoDireito(),novoNode);
            } else {
                adicionarNode(raiz,novoNode,DirecaoNode.DIREITA);
            }
        }
    }

    private void adicionarNode(Node raiz, Node novoNode, DirecaoNode direcao){
        if(direcao == DirecaoNode.NODE_ATUAL){
            raiz = novoNode;
        }
        if (direcao == DirecaoNode.ESQUERDA){
            raiz.adicionarNaEsquerda(novoNode);
        } else {
            raiz.adicionarNaDireita(novoNode);
        }
        novoNode.adicionarNivel();
    }
}
