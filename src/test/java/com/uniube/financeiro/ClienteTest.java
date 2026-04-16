package com.uniube.financeiro;

import com.uniube.financeiro.exception.CpfInvalidoException;
import com.uniube.financeiro.util.ValidadorCpf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    private Cliente cliente;

    @BeforeEach
    void setUp() throws CpfInvalidoException {

    }

    @Test
    void testConstrutorValido() {
        assertEquals("João Silva", cliente.getNome());
        assertEquals("12345678909", cliente.getCpf());
    }

@Test
    void testCpfImutavel() {

        assertNotNull(cliente.getCpf());
        assertNotNull(cliente.getNome());
    }

    @Test
    void testEqualsHashCode() throws CpfInvalidoException {
        Cliente outro = new Cliente("Maria", "12345678909");
        assertEquals(cliente, outro);
        assertEquals(cliente.hashCode(), outro.hashCode());
    }

    @Test
    void testToString() {
        assertTrue(cliente.toString().contains("João Silva"));
    }

    @Test
    void testCpfInvalido() {
        assertThrows(CpfInvalidoException.class, () -> new Cliente("Inválido", "123"));
    }
}

