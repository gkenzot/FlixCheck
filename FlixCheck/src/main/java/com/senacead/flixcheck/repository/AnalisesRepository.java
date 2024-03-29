package com.senacead.flixcheck.repository;

import com.senacead.flixcheck.model.AnalisesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnalisesRepository extends JpaRepository<AnalisesEntity, Integer> {

    public List<AnalisesEntity> findByFilmeId(Integer id);
}
