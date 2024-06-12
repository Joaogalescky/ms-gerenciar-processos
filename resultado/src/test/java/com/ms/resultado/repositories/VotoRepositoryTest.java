package com.ms.resultado.repositories;

import com.ms.resultado.entities.Voto;
import com.ms.resultado.enums.Escolha;
import com.ms.resultado.web.dtos.VotoCadastroDto;
import com.ms.resultado.web.dtos.mappers.VotoMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class VotoRepositoryTest {

    @Autowired
    VotoRepository votoRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("Deve retornar true quando voto existe no banco de dados")
    @Transactional
    void existsByIdSessaoAndIdFuncSuccess() {
        VotoCadastroDto votoCadastroDto = new VotoCadastroDto(1L, 1L, 2L, Escolha.APROVADO);
        Voto voto = cadastrarVoto(votoCadastroDto);
        boolean resultado = votoRepository.existsByIdSessaoAndIdFunc(voto.getIdSessao(), voto.getIdFunc());

        assertThat(resultado).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando voto não existe no banco de dados")
    void existsByIdSessaoAndIdFuncCase2() {
        boolean resultado = votoRepository.existsByIdSessaoAndIdFunc(1L, 1L);

        assertThat(resultado).isFalse();
    }

    @Test
    @DisplayName("Deve retornar lista de votos para uma sessão específica")
    @Transactional
    void findByIdSessaoSuccess() {
        VotoCadastroDto votoCadastroDto1 = new VotoCadastroDto(1L, 1L, 2L, Escolha.APROVADO);
        VotoCadastroDto votoCadastroDto2 = new VotoCadastroDto(2L, 1L, 1L, Escolha.REPROVADO);
        Voto voto1 = cadastrarVoto(votoCadastroDto1);
        Voto voto2 = cadastrarVoto(votoCadastroDto2);

        List<Voto> votos = votoRepository.findByIdSessao(1L);

        assertThat(votos).hasSize(2);
        assertThat(votos).contains(voto1, voto2);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não há votos para uma sessão específica")
    void findByIdSessaoCase2() {
        List<Voto> votos = votoRepository.findByIdSessao(1L);

        assertThat(votos).isEmpty();
    }

    private Voto cadastrarVoto(VotoCadastroDto votoCadastroDto) {
        Voto voto = VotoMapper.toVotoCadastrar(votoCadastroDto);
        return entityManager.merge(voto);
    }
}
