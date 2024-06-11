package com.ms.funcionario.services;

import com.ms.funcionario.entities.Funcionario;
import com.ms.funcionario.exceptions.DadosFuncionarioInvalidos;
import com.ms.funcionario.repositories.FuncionarioRepository;
import com.ms.funcionario.web.dtos.FuncionarioCadastrarDto;
import com.ms.funcionario.web.dtos.FuncionarioListarDto;
import com.ms.funcionario.web.dtos.mappers.FuncionarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve listar todos os funcionários")
    void listarFuncionario() {
        List<Funcionario> funcionarios = List.of(new Funcionario(), new Funcionario());
        when(funcionarioRepository.findAll()).thenReturn(funcionarios);

        List<Funcionario> result = funcionarioService.listarFuncionario();

        assertEquals(2, result.size());
        verify(funcionarioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve criar um Funcionário com sucesso quando todos os dados estão corretos")
    void cadastrarFuncionarioSuccess() {
        FuncionarioCadastrarDto funcionarioCadastrarDto = new FuncionarioCadastrarDto(1L, "Pedro", "10/11/2005", "12345678912", "m");
        Funcionario funcionario = FuncionarioMapper.toFuncionarioCadastrar(funcionarioCadastrarDto);
        funcionario.setCpf("12345678912");

        when(funcionarioRepository.existsByCpf("12345678912")).thenReturn(false);
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        Funcionario result = funcionarioService.cadastrarFuncionario(funcionarioCadastrarDto);

        assertEquals("12345678912", result.getCpf());
        verify(funcionarioRepository, times(1)).existsByCpf("12345678912");
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando os dados obrigatórios estão incompletos ou inválidos")
    void cadastrarFuncionarioCase1() {
        FuncionarioCadastrarDto funcionarioCadastrarDto = new FuncionarioCadastrarDto(1L, "", "10/11/2005", "12345678912", "m");

        DadosFuncionarioInvalidos exception = assertThrows(DadosFuncionarioInvalidos.class, () -> {
            funcionarioService.cadastrarFuncionario(funcionarioCadastrarDto);
        });

        assertEquals("Dados obrigatórios do funcionário estão imcompletos ou inválidos", exception.getMessage());
        verify(funcionarioRepository, never()).save(any(Funcionario.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF é inválido")
    void cadastrarFuncionarioCase2() {
        FuncionarioCadastrarDto funcionarioCadastrarDto = new FuncionarioCadastrarDto(1L, "Pedro", "10/11/2005", "123", "m");

        DadosFuncionarioInvalidos exception = assertThrows(DadosFuncionarioInvalidos.class, () -> {
            funcionarioService.cadastrarFuncionario(funcionarioCadastrarDto);
        });

        assertEquals("CPF deve ter 11 dígitos", exception.getMessage());
        verify(funcionarioRepository, never()).save(any(Funcionario.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF já existe")
    void cadastrarFuncionarioCase3() {
        FuncionarioCadastrarDto funcionarioCadastrarDto = new FuncionarioCadastrarDto(1L, "Pedro", "10/11/2005", "12345678912", "m");

        when(funcionarioRepository.existsByCpf("12345678912")).thenReturn(true);

        DadosFuncionarioInvalidos exception = assertThrows(DadosFuncionarioInvalidos.class, () -> {
            funcionarioService.cadastrarFuncionario(funcionarioCadastrarDto);
        });

        assertEquals("Funcionario CPF=12345678912 já existente", exception.getMessage());
        verify(funcionarioRepository, times(1)).existsByCpf("12345678912");
        verify(funcionarioRepository, never()).save(any(Funcionario.class));
    }

    @Test
    @DisplayName("Deve buscar funcionário por ID com sucesso")
    void buscarPorIdSuccess() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        Funcionario result = funcionarioService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(funcionarioRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando funcionário não for encontrado pelo ID")
    void buscarPorIdCase1() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());

        DadosFuncionarioInvalidos exception = assertThrows(DadosFuncionarioInvalidos.class, () -> {
            funcionarioService.buscarPorId(1L);
        });

        assertEquals("Funcionario id=1 não encontrado", exception.getMessage());
        verify(funcionarioRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve alterar um funcionário com sucesso")
    void alterarFuncionarioSuccess() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setCpf("12345678912");

        FuncionarioListarDto funcionarioListarDto = new FuncionarioListarDto("Pedro", "10/11/2005", "98765432100", "m");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        when(funcionarioRepository.existsByCpf("98765432100")).thenReturn(false);
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        Funcionario result = funcionarioService.alterarFuncionario(1L, funcionarioListarDto);

        assertEquals("98765432100", result.getCpf());
        verify(funcionarioRepository, times(1)).findById(1L);
        verify(funcionarioRepository, times(1)).existsByCpf("98765432100");
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar alterar funcionário com dados incompletos")
    void alterarFuncionarioCase1() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);

        FuncionarioListarDto funcionarioListarDto = new FuncionarioListarDto("", "10/11/2005", "98765432100", "m");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        DadosFuncionarioInvalidos exception = assertThrows(DadosFuncionarioInvalidos.class, () -> {
            funcionarioService.alterarFuncionario(1L, funcionarioListarDto);
        });

        assertEquals("Dados obrigatórios do funcionário estão imcompletos ou inválidos", exception.getMessage());
        verify(funcionarioRepository, times(1)).findById(1L);
        verify(funcionarioRepository, never()).save(any(Funcionario.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar alterar funcionário com CPF inválido")
    void alterarFuncionarioCase2() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);

        FuncionarioListarDto funcionarioListarDto = new FuncionarioListarDto("Pedro", "10/11/2005", "123", "m");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        DadosFuncionarioInvalidos exception = assertThrows(DadosFuncionarioInvalidos.class, () -> {
            funcionarioService.alterarFuncionario(1L, funcionarioListarDto);
        });

        assertEquals("CPF deve ter 11 dígitos", exception.getMessage());
        verify(funcionarioRepository, times(1)).findById(1L);
        verify(funcionarioRepository, never()).save(any(Funcionario.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar alterar funcionário com CPF já existente")
    void alterarFuncionarioCase3() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setCpf("12345678912");

        FuncionarioListarDto funcionarioListarDto = new FuncionarioListarDto("Pedro", "10/11/2005", "98765432100", "m");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        when(funcionarioRepository.existsByCpf("98765432100")).thenReturn(true);

        DadosFuncionarioInvalidos exception = assertThrows(DadosFuncionarioInvalidos.class, () -> {
            funcionarioService.alterarFuncionario(1L, funcionarioListarDto);
        });

        assertEquals("CPF já existente", exception.getMessage());
        verify(funcionarioRepository, times(1)).findById(1L);
        verify(funcionarioRepository, times(1)).existsByCpf("98765432100");
        verify(funcionarioRepository, never()).save(any(Funcionario.class));
    }

    @Test
    @DisplayName("Deve deletar um funcionário com sucesso")
    void deletarFuncionarioSuccess() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        funcionarioService.deletarFuncionario(1L);

        verify(funcionarioRepository, times(1)).findById(1L);
        verify(funcionarioRepository, times(1)).delete(funcionario);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar funcionário que não existe")
    void deletarFuncionarioCase1() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());

        DadosFuncionarioInvalidos exception = assertThrows(DadosFuncionarioInvalidos.class, () -> {
            funcionarioService.deletarFuncionario(1L);
        });

        assertEquals("Funcionario id=1 não encontrado", exception.getMessage());
        verify(funcionarioRepository, times(1)).findById(1L);
        verify(funcionarioRepository, never()).delete(any(Funcionario.class));
    }
}
