package br.com.capisoft.arvores.models;

public class Arvore {

    private int altura = 0;

    private Node root;

    public Arvore(Node root) {
        this.root = root;
        altura++;
    }

    public boolean contemRaiz(){
        return this.root != null;
    }

    public Node getRoot() {
        return root;
    }
}
