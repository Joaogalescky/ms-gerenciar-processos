package com.ms.resultado.repositories;

import com.ms.resultado.entities.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
}
