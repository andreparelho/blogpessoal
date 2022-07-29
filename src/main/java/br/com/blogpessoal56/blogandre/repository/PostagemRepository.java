package br.com.blogpessoal56.blogandre.repository;

import br.com.blogpessoal56.blogandre.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostagemRepository extends JpaRepository <Postagem, Long> {
}
