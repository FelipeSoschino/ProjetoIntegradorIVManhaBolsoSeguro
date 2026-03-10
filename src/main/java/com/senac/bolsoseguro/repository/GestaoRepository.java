package com.senac.bolsoseguro.repository;

import com.senac.bolsoseguro.entity.Gestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestaoRepository extends JpaRepository<Gestao, Integer> {
}
