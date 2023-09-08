package br.com.capisoft.arvores.repositories;

import br.com.capisoft.arvores.models.Arvore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.UUID;

public interface ArvoreRepository extends JpaRepository<Arvore, UUID> {
}
