package com.ms.proposta.repositories;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.ms.proposta.web.dtos.FuncionarioDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FuncionarioClientTest {

    @Mock
    private FuncionarioDto mockFuncionarioDto;

    @Mock
    private FuncionarioClient funcionarioClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testBuscarPorId() {

        when(funcionarioClient.buscarPorId(1L)).thenReturn(mockFuncionarioDto);

        FuncionarioDto result = funcionarioClient.buscarPorId(1L);

        assertEquals(mockFuncionarioDto, result);
        verify(funcionarioClient, times(1)).buscarPorId(1L);
    }
}

