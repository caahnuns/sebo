package br.com.ada.sebo.service;

import br.com.ada.sebo.model.dto.EditoraDTO;
import br.com.ada.sebo.model.entity.EditoraEntity;
import br.com.ada.sebo.model.mapper.EditoraMapper;
import br.com.ada.sebo.repository.EditoraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {

    @Autowired
    EditoraRepository repository;

    @Autowired
    EditoraMapper mapper;

    public List<EditoraDTO> listar() {
        List<EditoraEntity> entities = repository.findAll();
        return mapper.updateListToDTO(entities);
    }

    public EditoraDTO listar(Long id) {
        Optional<EditoraEntity> editora = repository.findById(id);

        if(editora.isPresent()) {
            EditoraEntity entity = editora.get();
            return mapper.update(entity);
        }
        throw new EntityNotFoundException("Ops... Editora não encontrada!");
    }

    public EditoraDTO criar(EditoraDTO editoraDTO) {
        EditoraEntity entity = mapper.update(editoraDTO);
        entity = repository.save(entity);

        return mapper.update(entity);
    }

    public EditoraDTO editar(EditoraDTO editoraDTO, Long id) {
        if(repository.existsById(id)) {
            EditoraEntity entity = mapper.update(editoraDTO);
            entity.setId(id);
            entity = repository.save(entity);

            return mapper.update(entity);
        }
        throw new EntityNotFoundException("Ops... Editora não encontrada!");
    }

    public void deletar(Long id) {
        Optional<EditoraEntity> editora = repository.findById(id);

        if(editora.isPresent()) {
            EditoraEntity entity = editora.get();
            repository.delete(entity);

            return;
        }
        throw new EntityNotFoundException("Ops... Editora não encontrada!");
    }
}
