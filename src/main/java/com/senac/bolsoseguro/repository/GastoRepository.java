package com.senac.bolsoseguro.repository;

import com.senac.bolsoseguro.entity.Gasto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer> {
    @Modifying
    @Transactional
    @Query("SELECT g FROM Gasto g WHERE g.status >= 0 AND g.gestao.id = :gestaoId")
    List<Gasto> listarGastosPorGestaoId(@Param("gestaoId") Integer gestaoId);
}
