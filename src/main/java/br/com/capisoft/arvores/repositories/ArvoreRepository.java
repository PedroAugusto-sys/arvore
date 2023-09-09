package br.com.capisoft.arvores.repositories;

import br.com.capisoft.arvores.models.Arvore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArvoreRepository extends JpaRepository<Arvore, Long> {
}
