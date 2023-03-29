package br.com.ada.sebo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LivroDTO {

    private Long id;

    @NotBlank(message = "O nome do livro não pode ser nulo ou vazio!")
    private String nome;

    @Size(max = 13, message = "Tamanho acima do permitido. Máximo de 13 caracteres!")
    @NotBlank(message = "Informe o ISBN do livro.")
    private String isbn;

    private EditoraDTO editora;

    private CategoriaDTO categoria;
}
