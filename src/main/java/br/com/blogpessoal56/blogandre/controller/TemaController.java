package br.com.blogpessoal56.blogandre.controller;


import br.com.blogpessoal56.blogandre.model.Postagem;
import br.com.blogpessoal56.blogandre.model.Tema;
import br.com.blogpessoal56.blogandre.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/descricao")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class TemaController {

    @Autowired
    private TemaRepository temaRepository;

    @GetMapping
    public ResponseEntity<List<Tema>> getAll() {
        return ResponseEntity.ok(temaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> getById (@PathVariable Long id) {
        return temaRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/tema/{tema}")
    public ResponseEntity<List<Tema>> getByTema(@PathVariable String tema) {
        return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(tema));
    }

    @PostMapping
    public ResponseEntity<Tema> post(@Valid @RequestBody Tema tema) {
        return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
    }

    @PutMapping
    public ResponseEntity<Tema> put(@Valid @RequestBody Tema tema) {
        return temaRepository.findById(tema.getId()).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Tema> tema = temaRepository.findById(id);
        if (tema.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        temaRepository.deleteById(id);
    }

}
