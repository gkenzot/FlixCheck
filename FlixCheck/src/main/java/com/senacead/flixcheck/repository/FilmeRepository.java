package com.senacead.flixcheck.repository;

import com.senacead.flixcheck.model.FilmeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity,Integer> {
    // Você pode adicionar métodos personalizados aqui, se necessário
}
