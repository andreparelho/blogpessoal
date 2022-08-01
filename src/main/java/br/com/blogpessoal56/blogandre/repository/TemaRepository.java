package br.com.blogpessoal56.blogandre.repository;

import br.com.blogpessoal56.blogandre.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Long> {
    List<Tema> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
