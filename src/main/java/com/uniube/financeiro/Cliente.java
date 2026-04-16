package com.uniube.financeiro;

import com.uniube.financeiro.exception.CpfInvalidoException;
import com.uniube.financeiro.util.ValidadorCpf;


public class Cliente {
    private final String nome;
    private final String cpf;

    public Cliente() {
        this.nome = "";
        this.cpf = "";
    }

    public Cliente(String nome, String cpf) throws CpfInvalidoException {
        if (!ValidadorCpf.isValid(cpf)) {
            throw new CpfInvalidoException("CPF inválido: " + cpf);
        }
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return String.format("Cliente{nome='%s', cpf='%s'}", nome, cpf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cpf.equals(cliente.cpf);
    }

    @Override
    public int hashCode() {
        return cpf.hashCode();
    }

    public void exibirDados() {
        System.out.println("Dados do Cliente:");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println();
    }
}

