package com.senac.bolsoseguro.repository;

import com.senac.bolsoseguro.entity.Gestao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GestaoRepository extends JpaRepository<Gestao, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Gestao g SET g.status = -1 where g.id = :id")
    void apagarGestao(@Param("id") Integer id);

    @Query("SELECT p FROM Gestao p where p.status >= 0")
    List<Gestao> listarGestoes();
}
