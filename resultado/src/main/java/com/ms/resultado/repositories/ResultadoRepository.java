package com.ms.resultado.repositories;

import com.ms.resultado.entities.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
    Resultado findByIdSessao(Long IdSessao);
}
