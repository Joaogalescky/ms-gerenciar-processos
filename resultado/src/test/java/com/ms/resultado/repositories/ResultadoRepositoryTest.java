package com.ms.resultado.repositories;

import com.ms.resultado.entities.Resultado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ResultadoRepositoryTest {

    @Autowired
    private ResultadoRepository resultadoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("Deve encontrar um resultado pelo id da sessão")
    void findByIdSessaoSuccess() {
        Resultado resultado = new Resultado();
        resultado.setIdSessao(1L);
        resultado.setVotosAprovados(10);
        resultado.setVotosReprovados(5);
        entityManager.persist(resultado);

        Resultado encontrado = resultadoRepository.findByIdSessao(1L);

        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getIdSessao()).isEqualTo(1L);
        assertThat(encontrado.getVotosAprovados()).isEqualTo(10);
        assertThat(encontrado.getVotosReprovados()).isEqualTo(5);
    }

    @Test
    @DisplayName("Deve retornar nulo quando não encontrar um resultado pelo id da sessão")
    void findByIdSessaoCase2() {
        Resultado encontrado = resultadoRepository.findByIdSessao(1L);

        assertThat(encontrado).isNull();
    }

}
