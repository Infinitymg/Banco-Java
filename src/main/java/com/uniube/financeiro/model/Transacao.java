package com.uniube.financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {
    private final String tipo;
    private final BigDecimal valor;
    private final LocalDateTime data;

    public Transacao(String tipo, BigDecimal valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = LocalDateTime.now();
    }


    public String getTipo() { return tipo; }
    public BigDecimal getValor() { return valor; }
    public LocalDateTime getData() { return data; }

    @Override
    public String toString() {
        return String.format("%s R$%.2f em %s", tipo, valor, data);
    }
}

