package br.com.capisoft.arvores.models;

import br.com.capisoft.arvores.models.DTOs.NodeSimplesDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "node")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "palavra")
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

    public Node(){}

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

    public Long getId() {
        return this.id;
    }

    public NodeSimplesDTO getDTO(){
        return new NodeSimplesDTO(
                this.getId(),
                this.getTexto(),
                this.getNivelAtual()
        );
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

    @Override
    public String toString() {
        return "NODE['" + texto.toUpperCase() + "\']";
    }
}
