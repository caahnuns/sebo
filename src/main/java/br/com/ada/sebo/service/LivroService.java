package br.com.ada.sebo.service;

import br.com.ada.sebo.model.dto.LivroDTO;
import br.com.ada.sebo.model.entity.CategoriaEntity;
import br.com.ada.sebo.model.entity.EditoraEntity;
import br.com.ada.sebo.model.entity.LivroEntity;
import br.com.ada.sebo.model.mapper.LivroMapper;
import br.com.ada.sebo.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    LivroRepository repository;

    @Autowired
    LivroMapper mapper;

    public List<LivroDTO> listar() {
        List<LivroEntity> entities = repository.findAll();
        return mapper.updateListToDTO(entities);
    }

    public LivroDTO listar(Long id) {
        Optional<LivroEntity> livro = repository.findById(id);

        if(livro.isPresent()) {
            LivroEntity entity = livro.get();
            return mapper.update(entity);
        }
        throw new EntityNotFoundException("Ops... Livro não encontrado!");
    }

    public LivroDTO criar(LivroDTO livroDTO) {
        LivroEntity entity = mapper.update(livroDTO);
        entity = repository.save(entity);

        return mapper.update(entity);
    }

    public LivroDTO editar(LivroDTO livroDTO, Long id) {
        if(repository.existsById(id)) {
            LivroEntity entity = mapper.update(livroDTO);
            entity.setId(id);
            entity = repository.save(entity);

            return mapper.update(entity);
        }
        throw new EntityNotFoundException("Ops... Livro não encontrado!");
    }

    public void deletar(Long id) {
        Optional<LivroEntity> livro = repository.findById(id);

        if(livro.isPresent()) {
            LivroEntity entity = livro.get();
            repository.delete(entity);

            return;
        }
        throw new EntityNotFoundException("Ops... Livro não encontrado!");
    }

    public List<LivroDTO> listarPorCategoria(Long id) {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(id);

        List<LivroEntity> entities = repository.findByCategoria(categoria);
        return mapper.updateListToDTO(entities);
    }

    public List<LivroDTO> listarPorEditora(Long id) {
        EditoraEntity editora = new EditoraEntity();
        editora.setId(id);

        List<LivroEntity> entities = repository.findByEditora(editora);
        return mapper.updateListToDTO(entities);
    }

    public List<LivroDTO> listarPorNomeOuIsbn(String nome, String isbn) {
        List<LivroEntity> entities = repository.findByNomeOrIsbn(nome, isbn);
        return mapper.updateListToDTO(entities);
    }
}
