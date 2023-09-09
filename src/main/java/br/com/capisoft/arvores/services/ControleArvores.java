package br.com.capisoft.arvores.services;

import br.com.capisoft.arvores.models.Arvore;
import br.com.capisoft.arvores.models.DirecaoNode;
import br.com.capisoft.arvores.models.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControleArvores {

    private static Logger LOG = LoggerFactory.getLogger(ControleArvores.class);

    private Arvore arvore;

    public ControleArvores(Arvore arvore){
        this.arvore = arvore;
        this.arvore.adicionarNaListaDePalavras(arvore.getRoot().getTexto());
    }

    public Arvore getArvore(){
        return this.arvore;
    }

    public void adicionarNaArvore(Node node){
        if (!arvore.contemRaiz()){
            LOG.info("Arvore está vazia, vou adicionar nova arvore com o root ->"+node.toString());
            arvore.setRoot(node);
        } else {
            LOG.info("Vou adicionar o node -> "+node.toString());
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
        LOG.info("Rotacao para a esquerda iniciada no node "+node.toString());
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
            LOG.info("raiz "+raiz+" vazia, vou adicionar o node de texto ->"+novoNode.getTexto().toUpperCase());
            adicionarNode(raiz, novoNode, DirecaoNode.NODE_ATUAL);
        }
        int res = raiz.getTexto().compareTo(novoNode.getTexto().toUpperCase());
        if (res == 0){
            LOG.info("o novo node "+novoNode+" já está na arvore, vou adicionar a contagem de palavras");
            arvore.adicionarNaListaDePalavras(novoNode.getTexto().toUpperCase());
        }
        if (res < 0){
            LOG.info("node central "+raiz.getTexto()+" é maior que o node "+ novoNode.getTexto()+", vou inseri-lo na Esquerda");
            if (raiz.contemNoEsquerdo()){
                novoNode.adicionarNivel();
                this.preencherArvore(raiz.getNoEsquerdo(),novoNode);
            } else {
                adicionarNode(raiz,novoNode,DirecaoNode.ESQUERDA);
            }
        } else if (res > 0){
            LOG.info("node central "+raiz.getTexto().toUpperCase()+" é menor que o node "+ novoNode.getTexto().toUpperCase()+", vou inseri-lo na Direita");
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
            LOG.info("o novo node "+novoNode.getTexto().toUpperCase()+" foi adicionado na árvore");
            raiz = novoNode;
        }
        if (direcao == DirecaoNode.ESQUERDA){
            LOG.info("o novo node "+novoNode.getTexto().toUpperCase()+" adicionado ESQUERDA do node "+raiz.getTexto().toUpperCase());
            raiz.adicionarNaEsquerda(novoNode);
        } else {
            LOG.info("o novo node "+novoNode.getTexto().toUpperCase()+" adicionado DIREITA do node "+raiz.getTexto().toUpperCase());
            raiz.adicionarNaDireita(novoNode);
        }
        novoNode.adicionarNivel();
        arvore.adicionarNaListaDePalavras(novoNode.getTexto());
        //rebalanceamento(raiz);
    }
}
