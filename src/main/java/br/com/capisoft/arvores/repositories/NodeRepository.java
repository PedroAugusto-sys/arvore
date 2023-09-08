package br.com.capisoft.arvores.repositories;

import br.com.capisoft.arvores.models.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NodeRepository extends JpaRepository<Node, UUID> {
}
