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

    //Rotacoes Simples
    private Node rotacaoDireita(Node node){
        Node x = node.getNoEsquerdo();
        Node z = x.getNoDireito();
        x.setNodeDireito(node);
        node.setNodeEsquerdo(z);
        updateAltura(node);
        updateAltura(x);
        return x;
    }

    private Node rotacaoEsquerda(Node node){
        Node x = node.getNoDireito();
        Node z = x.getNoEsquerdo();
        x.setNodeEsquerdo(node);
        node.setNodeDireito(z);
        updateAltura(node);
        updateAltura(x);
        return x;
    }

    //Rebalanceamento
    private void rebalanceamento(Node z) {
        updateAltura(z);
        int balance = obterBalanceamento(z);
        if (balance > 1) {
            if (altura(z.getNoDireito().getNoDireito()) > altura(z.getNoDireito().getNoEsquerdo())) {
                z = rotacaoEsquerda(z);
            } else {
                z.setNodeDireito(rotacaoDireita(z.getNoDireito()));
                z = rotacaoEsquerda(z);
            }
        } else if (balance < -1) {
            if (altura(z.getNoEsquerdo().getNoEsquerdo()) > altura(z.getNoEsquerdo().getNoDireito()))
                z = rotacaoDireita(z);
            else {
                z.setNodeEsquerdo(rotacaoEsquerda(z.getNoEsquerdo()));
                z = rotacaoDireita(z);
            }
        }
    }

    private void updateAltura(Node node){
        node.setAltura(1 + Math.max(altura(node.getNoEsquerdo()) , altura(node.getNoDireito())));
    }

    private int obterBalanceamento(Node node){
        return (node == null) ? 0: Math.max(altura(node.getNoEsquerdo()) , altura(node.getNoDireito()));
    }

    private int altura(Node node){
        return (node == null) ? -1 : node.getNivelAtual();
    }


    //Adicionando na Arvore
    private void preencherArvore(Node raiz, Node novoNode){
        if (raiz == null){
            raiz = novoNode;
            adicionarNode(raiz, novoNode, DirecaoNode.NODE_ATUAL);
        }
        int res = raiz.getTexto().compareTo(novoNode.getTexto());
        if (res < 0){
            if (raiz.contemNoEsquerdo()){
                novoNode.adicionarNivel();
                this.preencherArvore(raiz.getNoEsquerdo(),novoNode);
            } else {
                adicionarNode(raiz,novoNode,DirecaoNode.ESQUERDA);
            }
        } else if (res > 0){
            if (raiz.contemNoDireito()) {
                novoNode.adicionarNivel();
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
        rebalanceamento(raiz);
    }
}
