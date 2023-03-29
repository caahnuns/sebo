package br.com.ada.sebo.model.mapper;

import br.com.ada.sebo.model.dto.LivroDTO;
import br.com.ada.sebo.model.entity.LivroEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroMapper {

    private EditoraMapper editoraMapper = new EditoraMapper();
    private CategoriaMapper categoriaMapper = new CategoriaMapper();

    public LivroDTO update(LivroEntity livroEntity) {
        LivroDTO livro = new LivroDTO();

        livro.setId(livroEntity.getId());
        livro.setNome(livroEntity.getNome());
        livro.setIsbn(livroEntity.getIsbn());

        livro.setEditora(editoraMapper.update(livroEntity.getEditora()));
        livro.setCategoria(categoriaMapper.update(livroEntity.getCategoria()));

        return livro;
    }

    public LivroEntity update(LivroDTO livroDTO) {
        LivroEntity livro = new LivroEntity();

        livro.setId(livroDTO.getId());
        livro.setNome(livroDTO.getNome());
        livro.setIsbn(livroDTO.getIsbn());

        livro.setEditora(editoraMapper.update(livroDTO.getEditora()));
        livro.setCategoria(categoriaMapper.update(livroDTO.getCategoria()));

        return livro;
    }

    public List<LivroDTO> updateListToDTO(List<LivroEntity> entityList) {
        return entityList.stream()
                .map(livroEntity -> this.update(livroEntity))
                .toList();
    }

    public List<LivroEntity> updateListToEntities(List<LivroDTO> dtoList) {
        return dtoList.stream()
                .map(livroDTO -> this.update(livroDTO))
                .toList();
    }
}
