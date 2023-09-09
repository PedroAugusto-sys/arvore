package br.com.capisoft.arvores.repositories;

import br.com.capisoft.arvores.models.Node;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NodeRepository extends JpaRepository<Node, Long> {

    //TODO findByTexto para validar se já tem na arvore

}
