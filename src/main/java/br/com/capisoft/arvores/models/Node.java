package br.com.capisoft.arvores.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "node")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "texto")
    private String texto;

    @Column(name = "nivel_atual")
    private int nivelAtual = 0;

    @Column(name = "altura_atual")
    private int altura = 0;

    @ManyToOne
    private Node nodeDireito;

    @ManyToOne
    private Node nodeEsquerdo;

    @Transient
    private int fatorBalanceamento = 0;

    public Node(String texto) {
        this.texto = texto;
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

    public int getFatorBalanceamento() {
        return fatorBalanceamento;
    }

    public void setFatorBalanceamento(int fatorBalanceamento) {
        this.fatorBalanceamento = fatorBalanceamento;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setNodeEsquerdo(Node node){
        this.nodeEsquerdo = node;
    }

    public void setNodeDireito(Node node){
        this.nodeDireito = node;
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
