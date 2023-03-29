package br.com.ada.sebo.model.mapper;

import br.com.ada.sebo.model.dto.CategoriaDTO;
import br.com.ada.sebo.model.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaMapper {

    // Devolve o registro para a view
    public CategoriaDTO update(CategoriaEntity categoriaEntity) {
        CategoriaDTO categoria = new CategoriaDTO();

        categoria.setId(categoriaEntity.getId());
        categoria.setNome(categoriaEntity.getNome());

        return categoria;
    }

    // Entrega o registro pra persistência
    public CategoriaEntity update(CategoriaDTO categoriaDTO) {
        CategoriaEntity categoria = new CategoriaEntity();

        categoria.setId(categoriaDTO.getId());
        categoria.setNome(categoriaDTO.getNome());

        return categoria;
    }

    public List<CategoriaDTO> updateListToDTO(List<CategoriaEntity> entityList) {
        return entityList.stream()
                .map(categoriaEntity -> this.update(categoriaEntity))
                .toList();
    }

    public List<CategoriaEntity> updateListToEntities(List<CategoriaDTO> dtoList) {
        return dtoList.stream()
                .map(categoriaDTO -> this.update(categoriaDTO))
                .toList();
    }
}
