package br.com.capisoft.arvores.models.DTOs;

public record ArvoreNode(
        String raiz,

        ArvoreNode nodeEsquerda,

        ArvoreNode nodeDireita
) {
}
