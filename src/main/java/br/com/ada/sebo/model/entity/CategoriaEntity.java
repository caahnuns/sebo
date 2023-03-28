package br.com.ada.sebo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<LivroEntity> livros;
}
