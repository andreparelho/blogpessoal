package br.com.blogpessoal56.blogandre.repository;

import br.com.blogpessoal56.blogandre.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
    public Optional<Usuario> findByUsuario(String usuario);
}
