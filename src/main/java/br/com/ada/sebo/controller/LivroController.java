package br.com.ada.sebo.controller;

import br.com.ada.sebo.model.dto.LivroDTO;
import br.com.ada.sebo.model.dto.MensagemDTO;
import br.com.ada.sebo.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    LivroService service;

    @GetMapping()
    public ResponseEntity<Object> listar() {
        try {
            return ResponseEntity.ok(service.listar());
        } catch (Exception e) {
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> listar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.listar(id));
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new MensagemDTO(e.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody @Valid LivroDTO livroDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criar(livroDTO));
        } catch (Exception e) {
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@RequestBody @Valid LivroDTO livroDTO,
                                         @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.editar(livroDTO, id));
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok(new MensagemDTO("Livro #" + id + " removido com sucesso!"));
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<Object> listarPorCategoria(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.listarPorCategoria(id));
        } catch (Exception e) {
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @GetMapping("/editora/{id}")
    public ResponseEntity<Object> listarPorEditora(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.listarPorEditora(id));
        } catch (Exception e) {
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<Object> listarPorNomeOuIsbn(@RequestParam(name = "nome") String nome,
                                                      @RequestParam(name = "isbn") String isbn) {
        try {
            return ResponseEntity.ok(service.listarPorNomeOuIsbn(nome, isbn));
        } catch (Exception e) {
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

}
