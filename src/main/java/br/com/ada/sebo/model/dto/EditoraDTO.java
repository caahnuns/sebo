package br.com.ada.sebo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditoraDTO {

    private Long id;
    @Size(max = 255, message = "Tamanho acima do permitido. Máximo de 255 caracteres!")
    @NotBlank(message = "Informe o nome da editora.")
    private String nome;
    private String descricao;
}
