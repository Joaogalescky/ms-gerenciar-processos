package com.ms.funcionario.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    @Test
    @DisplayName("Testar getters")
    void testGetters() {

        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("João");
        funcionario.setDataNasc("2000-01-01");
        funcionario.setCpf("12345678901");
        funcionario.setSexo("M");

        assertEquals(1L, funcionario.getId());
        assertEquals("João", funcionario.getNome());
        assertEquals("2000-01-01", funcionario.getDataNasc());
        assertEquals("12345678901", funcionario.getCpf());
        assertEquals("M", funcionario.getSexo());
    }

    @Test
    @DisplayName("Testar setters")
    void testSetters() {

        Funcionario funcionario = new Funcionario();

        funcionario.setId(1L);
        assertEquals(1L, funcionario.getId());
        funcionario.setNome("João");
        assertEquals("João", funcionario.getNome());
        funcionario.setDataNasc("2000-01-01");
        assertEquals("2000-01-01", funcionario.getDataNasc());
        funcionario.setCpf("12345678901");
        assertEquals("12345678901", funcionario.getCpf());
        funcionario.setSexo("M");
        assertEquals("M", funcionario.getSexo());
    }

    @Test
    @DisplayName("Testar equals e hashCode")
    void EqualsAndHashCode() {

        Funcionario funcionario1 = new Funcionario();
        funcionario1.setId(1L);
        Funcionario funcionario2 = new Funcionario();
        funcionario2.setId(1L);
        Funcionario funcionario3 = new Funcionario();
        funcionario3.setId(2L);

        assertTrue(funcionario1.equals(funcionario2));
        assertEquals(funcionario1.hashCode(), funcionario2.hashCode());
        assertFalse(funcionario1.equals(funcionario3));
        assertNotEquals(funcionario1.hashCode(), funcionario3.hashCode());
    }

    @Test
    @DisplayName("Testar toString para garantir que não retorna nulo")
    void ToString() {

        Funcionario funcionario = new Funcionario();
        assertNotNull(funcionario.toString());
    }
}
