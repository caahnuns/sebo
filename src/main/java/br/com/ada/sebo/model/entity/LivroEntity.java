package br.com.ada.sebo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "livro")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "isbn", nullable = false, length = 13)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "editora")
    private EditoraEntity editora;
    @ManyToOne
    @JoinColumn(name = "categoria")
    private CategoriaEntity categoria;
}