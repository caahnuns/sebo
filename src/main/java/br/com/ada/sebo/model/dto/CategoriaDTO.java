package br.com.ada.sebo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaDTO {

    private Long id;
    @Size(max = 100, message = "Tamanho acima do permitido. Máximo de 100 caracteres!")
    @NotBlank(message = "Informe o nome da categoria.")
    private String nome;
}
