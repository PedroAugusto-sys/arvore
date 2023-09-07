package br.com.capisoft.arvores.repositories;

import br.com.capisoft.arvores.models.Arvore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface ArvoreRepository extends JpaRepository<Arvore, Long> {

    @Query("SELECT a from Arvore a where 1=1 and a.id = :id")
    Optional<Boolean> verificarArvoreExiste(Long id);


}
