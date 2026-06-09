package com.senac.bolsoseguro.repository;

import com.senac.bolsoseguro.entity.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Categoria p SET p.status = -1 where p.id = :id")
    void apagarCategoria(@Param("id") Integer id);
}

