package br.com.capisoft.arvores.models;

import jakarta.persistence.*;

@Entity
@Table(name = "palavra")
public class Palavra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "palavra")
    private String palavra;

    @Column(name = "quantidade")
    private int quantidade = 1;

    public Palavra(String palavra){
        this.palavra = palavra.toLowerCase();
        this.quantidade = 1;
    }

    public Palavra(Arvore arvore,String palavra){
        this.arvore = arvore;
        this.palavra = palavra.toLowerCase();
        this.quantidade = 1;
    }

    @ManyToOne
    @JoinColumn(name = "arvore_id")
    private Arvore arvore;

    public void aumentarContagem(){
        this.quantidade++;
    }


    public String getPalavra() {
        return palavra;
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
