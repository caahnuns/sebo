package br.com.ada.sebo.service;

import br.com.ada.sebo.model.dto.CategoriaDTO;
import br.com.ada.sebo.model.entity.CategoriaEntity;
import br.com.ada.sebo.model.mapper.CategoriaMapper;
import br.com.ada.sebo.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;

    @Autowired
    CategoriaMapper mapper;

    public List<CategoriaDTO> listar() {
        List<CategoriaEntity> entities = repository.findAll();
        return mapper.updateListToDTO(entities);
    }

    public CategoriaDTO listar(Long id) {
        Optional<CategoriaEntity> categoria = repository.findById(id);

        if(categoria.isPresent()) {
            CategoriaEntity entity = categoria.get();
            return mapper.update(entity);
        }
        throw new EntityNotFoundException("Ops... Categoria não encontrada!");
    }

    public CategoriaDTO criar(CategoriaDTO categoriaDTO) {
        CategoriaEntity entity = mapper.update(categoriaDTO);
        entity = repository.save(entity);

        return mapper.update(entity);
    }

    public CategoriaDTO editar(CategoriaDTO categoriaDTO, Long id) {
        if(repository.existsById(id)) {
            CategoriaEntity entity = mapper.update(categoriaDTO);
            entity.setId(id);
            entity = repository.save(entity);

            return mapper.update(entity);
        }
        throw new EntityNotFoundException("Ops... Categoria não encontrada!");
    }

    public void deletar(Long id) {
        Optional<CategoriaEntity> categoria = repository.findById(id);

        if(categoria.isPresent()) {
            CategoriaEntity entity = categoria.get();
            repository.delete(entity);

            return;
        }
        throw new EntityNotFoundException("Ops... Categoria não encontrada!");
    }
}
