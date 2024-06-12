package com.ms.funcionario.repositories;

import com.ms.funcionario.entities.Funcionario;
import com.ms.funcionario.web.dtos.FuncionarioCadastrarDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class FuncionarioRepositoryTest {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Deve retornar funcionário com sucesso do banco de dados")
    void existsByCpfSuccess() {
        FuncionarioCadastrarDto funcionarioCadastrarDto = new FuncionarioCadastrarDto(1L,"Pedro", "10/11/2005", "12345678912", "m");
        this.cadastrarFuncionario(funcionarioCadastrarDto);
        Boolean resultado = funcionarioRepository.existsByCpf(funcionarioCadastrarDto.getCpf());

        assertThat(resultado).isTrue();
    }
    @Test
    @DisplayName("Não deve retornar funcionário do banco de dados quando funcionário não existe")
    void existsByCpfCase2() {
        String cpf = "12345678912";
        Boolean resultado = funcionarioRepository.existsByCpf(cpf);

        assertThat(resultado).isFalse();
    }

    private Funcionario cadastrarFuncionario(FuncionarioCadastrarDto funcionarioCadastrarDto) {
        Funcionario novoFuncionario = new Funcionario(funcionarioCadastrarDto);
        return entityManager.merge(novoFuncionario);
    }
}
