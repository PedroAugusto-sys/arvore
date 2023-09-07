package br.com.capisoft.arvores.models;

import jakarta.persistence.*;

@Entity
@Table(name = "arvore")
public class Arvore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "altura")
    private int altura = 0;

    @OneToOne
    private Node root;

    @Transient
    boolean isAVL = false;


    public Arvore(Node root) {
        this.root = root;
        this.altura++;
        root.adicionarNivel();
        this.isAVL = false;
    }

    public Arvore(Node root, boolean isAVL) {
        this.isAVL = isAVL;
        this.root = root;
        this.altura++;
        root.adicionarNivel();
    }

    public boolean contemRaiz(){
        return this.root != null;
    }

    public Node getRoot() {
        return root;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getAltura(){
        return this.altura;
    }

    public void aumentarAltura(){
        this.altura++;
    }

    public void diminuirAltura(){
        this.altura--;
    }

    public void setRoot(Node node){
        this.root = node;
    }
}
