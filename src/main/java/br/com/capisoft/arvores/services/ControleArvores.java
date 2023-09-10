package br.com.capisoft.arvores.services;

import br.com.capisoft.arvores.models.Arvore;
import br.com.capisoft.arvores.models.DirecaoNode;
import br.com.capisoft.arvores.models.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            //Aqui realiza a busca na árvore se tal node já está presente na árvore, se sim apenas acrescenta na contagem de palavras, se não então insere na árvore
            LOG.info("Vou iniciar uma Busca Binária na "+arvore.toString()+" e verificar se contém o Node "+node.getTexto().toUpperCase());
            Node no = Busca.binariaDaArvore(this.arvore,node.getTexto());
            if(no != null){
                LOG.info("Node com texto "+node.getTexto().toUpperCase()+" foi encontrado na árvore, logo, não irei preencher, apenas adicionar a contagem de palavras");
                arvore.adicionarNaListaDePalavras(node.getTexto());
            } else {
                LOG.info("Vou adicionar o node pois foi verificado que o mesmo não foi encontrado na árvore -> "+node.toString());
                preencherArvore(arvore.getRoot(), node);
            }
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
    private Node preencherArvore(Node raiz, Node novoNode){
        String raizPalavra = raiz.getTexto().toUpperCase();
        String nodePalavra = novoNode.getTexto().toUpperCase();

        if (raiz == null){
            raiz = novoNode;
            LOG.info("Raiz "+raiz+" vazia, vou adicionar o node de texto ->"+novoNode.getTexto().toUpperCase());
            return adicionarNode(raiz, novoNode, DirecaoNode.NODE_ATUAL);
        }
        int res = raiz.getTexto()
                .trim()
                .toLowerCase()
                .compareTo(novoNode.getTexto().trim().toLowerCase());
        if (res == 0){
            LOG.info("O novo node "+nodePalavra+" já está na arvore, vou adicionar a contagem de palavras");
            arvore.adicionarNaListaDePalavras(nodePalavra);
        }
        if (res > 0){
            LOG.info("Node central "+raizPalavra+" é maior que o node "+ nodePalavra+", vou inserir node "+nodePalavra+" na Esquerda do "+raizPalavra);
            if (raiz.contemNoEsquerdo()){
                novoNode.adicionarNivel();
                return preencherArvore(raiz.getNoEsquerdo(),novoNode);
            } else {
                return adicionarNode(raiz,novoNode,DirecaoNode.ESQUERDA);
            }
        } else {
            LOG.info("Node central "+raizPalavra+" é menor que o node "+ nodePalavra+", vou inserir node "+nodePalavra+" na Direita do "+raizPalavra);
            if (raiz.contemNoDireito()) {
                novoNode.adicionarNivel();
                return preencherArvore(raiz.getNoDireito(),novoNode);
            } else {
                return adicionarNode(raiz,novoNode,DirecaoNode.DIREITA);
            }
        }
    }

    private Node adicionarNode(Node raiz, Node novoNode, DirecaoNode direcao){
        if(direcao == DirecaoNode.NODE_ATUAL){
            LOG.info("O node "+novoNode.getTexto().toUpperCase()+" foi adicionado na árvore");
            raiz = novoNode;
        }
        if (direcao == DirecaoNode.ESQUERDA){
            LOG.info("O node "+novoNode.getTexto().toUpperCase()+" adicionado ESQUERDA do node "+raiz.getTexto().toUpperCase());
            raiz.adicionarNaEsquerda(novoNode);
        } else {
            LOG.info("O node "+novoNode.getTexto().toUpperCase()+" adicionado DIREITA do node "+raiz.getTexto().toUpperCase());
            raiz.adicionarNaDireita(novoNode);
        }
        novoNode.adicionarNivel();
        arvore.adicionarNaListaDePalavras(novoNode.getTexto());
        if (arvore.isAVL()){
            LOG.info("Como é uma arvore AVL, vou rebalancea-la.");
            rebalanceamento(raiz);
        }
        return novoNode;
    }
}
