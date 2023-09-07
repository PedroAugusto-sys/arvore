package br.com.capisoft.arvores.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "node")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "texto")
    private String texto;

    @Column(name = "nivel_atual")
    private int nivelAtual = 0;

    @ManyToOne
    private Node nodeDireito;

    @ManyToOne
    private Node nodeEsquerdo;

    public Node(String texto) {
        this.texto = texto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Node getNoDireito(){
       return this.nodeDireito;
    }

    public Node getNoEsquerdo(){
        return this.nodeEsquerdo;
    }

    public boolean contemNoDireito(){
        return this.nodeDireito != null;
    }

    public boolean contemNoEsquerdo(){
        return this.nodeEsquerdo != null;
    }

    public void adicionarNaEsquerda(Node node){
        this.nodeEsquerdo = node;
    }

    public void adicionarNaDireita(Node node){
        this.nodeDireito = node;
    }

    public void removerNaEsquerda(){
        this.nodeEsquerdo = null;
    }

    public void removerNaDireita(){
        this.nodeDireito = null;
    }

    public String getTexto(){
        return this.texto;
    }

    public void adicionarNivel(){
        this.nivelAtual++;
    }

    public void removerNivel(){
        this.nivelAtual--;
    }

    public int getNivelAtual() {
        return nivelAtual;
    }

    public void setNivelAtual(int nivelAtual) {
        this.nivelAtual = nivelAtual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return nivelAtual == node.nivelAtual && Objects.equals(id, node.id) && Objects.equals(texto, node.texto) && Objects.equals(nodeDireito, node.nodeDireito) && Objects.equals(nodeEsquerdo, node.nodeEsquerdo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texto, nivelAtual, nodeDireito, nodeEsquerdo);
    }
}
