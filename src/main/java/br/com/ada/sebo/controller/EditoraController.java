package br.com.ada.sebo.controller;

import br.com.ada.sebo.model.dto.EditoraDTO;
import br.com.ada.sebo.model.dto.MensagemDTO;
import br.com.ada.sebo.service.EditoraService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/editoras")
public class EditoraController {

    @Autowired
    EditoraService service;

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
    public ResponseEntity<Object> criar(@RequestBody @Valid EditoraDTO editoraDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criar(editoraDTO));
        } catch (Exception e) {
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@RequestBody @Valid EditoraDTO editoraDTO,
                                         @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.editar(editoraDTO, id));
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
            return ResponseEntity.ok(new MensagemDTO("Editora #" + id + " removida com sucesso!"));
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
}
