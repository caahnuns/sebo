package br.com.ada.sebo.repository;

import br.com.ada.sebo.model.entity.EditoraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<EditoraEntity, Long> {
}
