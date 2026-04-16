package com.uniube.financeiro;

import com.uniube.financeiro.ContaBancaria;
import com.uniube.financeiro.exception.SaldoInsuficienteException;
import com.uniube.financeiro.exception.ValorInvalidoException;
import com.uniube.financeiro.Cliente;
import com.uniube.financeiro.TipoConta;
import com.uniube.financeiro.exception.CpfInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class ContaBancariaTest {
    private ContaBancaria conta;
    private Cliente cliente;

    @BeforeEach
    void setUp() throws CpfInvalidoException {
        cliente = new Cliente("Teste", "12345678909");
        conta = new ContaBancaria(12345, BigDecimal.valueOf(1000), cliente, TipoConta.CORRENTE);
    }

    @Test
    void testDepositar() throws ValorInvalidoException {
        conta.depositar(BigDecimal.valueOf(500));
        assertEquals(0, BigDecimal.valueOf(1500).compareTo(conta.getSaldo()));
    }

    @Test
    void testDepositarInvalido() {
        assertThrows(ValorInvalidoException.class, () -> conta.depositar(BigDecimal.valueOf(-10)));
    }

    @Test
    void testSacar() throws ValorInvalidoException, SaldoInsuficienteException {
        conta.sacar(BigDecimal.valueOf(300));
        assertEquals(0, BigDecimal.valueOf(700).compareTo(conta.getSaldo()));
    }

    @Test
    void testSacarInsuficiente() {
        assertThrows(SaldoInsuficienteException.class, () -> conta.sacar(BigDecimal.valueOf(2000)));
    }

    @Test
    void testHistoricoNaoVazioAposOperacao() throws ValorInvalidoException {
        conta.depositar(BigDecimal.TEN);
        assertFalse(conta.getHistorico().isEmpty());
    }
}

