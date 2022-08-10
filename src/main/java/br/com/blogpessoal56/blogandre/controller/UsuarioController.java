package br.com.blogpessoal56.blogandre.controller;

import br.com.blogpessoal56.blogandre.model.Usuario;
import br.com.blogpessoal56.blogandre.model.UsuarioLogin;
import br.com.blogpessoal56.blogandre.repository.UsuarioRepository;
import br.com.blogpessoal56.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/logar")
    public ResponseEntity<UsuarioLogin> autenticationUsuario(
            @RequestBody Optional<UsuarioLogin> usuario) {
        return service.logarUsuario(usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> postUsuario(
            @Valid @RequestBody Usuario usuario) {
        return service.cadastrarUsuario(usuario)
                .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> putUsuario(
            @Valid @RequestBody Usuario usuario) {
        return service.atualizarUsuario(usuario)
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
