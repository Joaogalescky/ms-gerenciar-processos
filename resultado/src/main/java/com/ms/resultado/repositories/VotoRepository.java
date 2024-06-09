package com.ms.resultado.repositories;

import com.ms.resultado.entities.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByIdSessaoAndIdFunc(Long IdSessao, Long funcionarioId);
    List<Voto> findByIdSessao(Long IdSessao);
}
