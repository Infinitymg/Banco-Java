package com.uniube.financeiro;

import com.uniube.financeiro.exception.SaldoInsuficienteException;
import com.uniube.financeiro.exception.ValorInvalidoException;
import com.uniube.financeiro.model.Transacao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ContaBancaria {
    private final int numeroConta;
    private final Cliente cliente;
    private final TipoConta tipoConta;
    private BigDecimal saldo;
    private final List<Transacao> historico;

    public ContaBancaria() {
        this.numeroConta = 0;
        this.saldo = BigDecimal.ZERO;
        this.cliente = new Cliente();
        this.tipoConta = TipoConta.CORRENTE;
        this.historico = new ArrayList<>();
    }

    public ContaBancaria(int numeroConta, BigDecimal saldoInicial, Cliente cliente, TipoConta tipoConta) {
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial != null ? saldoInicial : BigDecimal.ZERO;
        this.cliente = cliente;
        this.tipoConta = tipoConta != null ? tipoConta : TipoConta.CORRENTE;
        this.historico = new ArrayList<>();
        if (this.saldo.compareTo(BigDecimal.ZERO) < 0) {
            this.saldo = BigDecimal.ZERO;
        }
    }

    public int getNumeroConta() { return numeroConta; }
    public Cliente getCliente() { return cliente; }
    public TipoConta getTipoConta() { return tipoConta; }
    public BigDecimal getSaldo() { return saldo; }
    public List<Transacao> getHistorico() { return new ArrayList<>(historico); }

    public void depositar(BigDecimal valor) throws ValorInvalidoException {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("Valor de depósito deve ser positivo: " + valor);
        }
        saldo = saldo.add(valor);
        historico.add(new Transacao("DEPOSITO", valor));
        System.out.println("✅ Depósito de R$ " + valor.setScale(2) + " realizado com sucesso. Novo saldo: R$ " + saldo.setScale(2));
    }

    public void sacar(BigDecimal valor) throws ValorInvalidoException, SaldoInsuficienteException {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("Valor de saque deve ser positivo: " + valor);
        }
        if (valor.compareTo(saldo) > 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente. Saldo atual: R$ " + saldo.setScale(2) + ", solicitado: R$ " + valor.setScale(2));
        }
        saldo = saldo.subtract(valor);
        historico.add(new Transacao("SAQUE", valor.negate()));
        System.out.println("✅ Saque de R$ " + valor.setScale(2) + " realizado. Novo saldo: R$ " + saldo.setScale(2));
    }

    public void transferir(ContaBancaria destino, BigDecimal valor) throws Exception {
        sacar(valor);
        destino.depositar(valor);
        historico.add(new Transacao("TRANSFERENCIA_ENVIADA", valor.negate()));
        System.out.println("💸 Transferência de R$ " + valor + " para conta " + destino.numeroConta + " realizada.");
    }

    public void exibirDados() {
        System.out.println("🏦 Dados da Conta Bancária:");
        System.out.println("Número: " + numeroConta + " | Tipo: " + tipoConta + " | Saldo: R$ " + saldo.setScale(2));
        System.out.println("Cliente: " + cliente);
        System.out.println("Histórico recente:");
        historico.subList(Math.max(0, historico.size() - 5), historico.size()).forEach(System.out::println);
        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("ContaBancaria{num=%d, saldo=R$%.2f, cliente=%s, tipo=%s}", numeroConta, saldo.doubleValue(), cliente.getNome(), tipoConta);
    }
}

