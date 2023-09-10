package br.com.capisoft.arvores.models.DTOs;

import br.com.capisoft.arvores.models.Node;

public record NodeDTO(
        String palavra,

        NodeDTO nodeEsquerdo,

        NodeDTO nodeDireito
) {
}
