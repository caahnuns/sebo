package br.com.ada.sebo.model.mapper;

import br.com.ada.sebo.model.dto.EditoraDTO;
import br.com.ada.sebo.model.entity.EditoraEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EditoraMapper {

    public EditoraDTO update(EditoraEntity editoraEntity) {
        EditoraDTO editora = new EditoraDTO();

        editora.setId(editoraEntity.getId());
        editora.setNome(editoraEntity.getNome());
        editora.setDescricao(editoraEntity.getDescricao());

        return editora;
    }

    public EditoraEntity update(EditoraDTO editoraDTO) {
        EditoraEntity editora = new EditoraEntity();

        editora.setId(editoraDTO.getId());
        editora.setNome(editoraDTO.getNome());
        editora.setDescricao(editoraDTO.getDescricao());

        return editora;
    }

    public List<EditoraDTO> updateListToDTO(List<EditoraEntity> entityList) {
        return entityList.stream()
                .map(editoraEntity -> this.update(editoraEntity))
                .toList();
    }

    public List<EditoraEntity> updateListToEntities(List<EditoraDTO> dtoList) {
        return dtoList.stream()
                .map(editoraDTO -> this.update(editoraDTO))
                .toList();
    }
}
