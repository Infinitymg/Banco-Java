package com.uniube;

import com.uniube.financeiro.Cliente;
import com.uniube.financeiro.ContaBancaria;
import com.uniube.financeiro.TipoConta;
import com.uniube.financeiro.exception.CpfInvalidoException;
import com.uniube.financeiro.exception.SaldoInsuficienteException;
import com.uniube.financeiro.exception.ValorInvalidoException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BancoApp {
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<ContaBancaria> contas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🏦 === BANCO PROFISSIONAL - Sistema Enterprise Edition === ");
        System.out.println("Versão demonstrativa otimizada para portfólio LinkedIn.\n");

        boolean rodando = true;
        while (rodando) {
            mostrarMenu();
            int opcao = lerInteiro("Escolha uma opção: ");
            try {
                switch (opcao) {
                    case 1 -> criarCliente();
                    case 2 -> criarConta();
                    case 3 -> depositar();
                    case 4 -> sacar();
                    case 5 -> transferir();
                    case 6 -> listarContas();
                    case 7 -> exibirContaDetalhes();
                    case 0 -> {
                        rodando = false;
                        System.out.println("👋 Obrigado por usar o BancoApp! Volte sempre.");
                    }
                    default -> System.out.println("❌ Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n📋 MENU:");
        System.out.println("1. Criar Cliente");
        System.out.println("2. Criar Conta");
        System.out.println("3. Depositar");
        System.out.println("4. Sacar");
        System.out.println("5. Transferir");
        System.out.println("6. Listar Contas");
        System.out.println("7. Detalhes da Conta");
        System.out.println("0. Sair");
    }

    private static void criarCliente() {
        String nome = lerString("Nome: ");
        String cpf = lerString("CPF (11 dígitos): ");
        try {
            Cliente cliente = new Cliente(nome, cpf);
            clientes.add(cliente);
            System.out.println("✅ Cliente criado: " + cliente);
        } catch (CpfInvalidoException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void criarConta() {
        if (clientes.isEmpty()) {
            System.out.println("❌ Crie um cliente primeiro!");
            return;
        }
        System.out.println("Clientes disponíveis:");
        clientes.forEach(c -> System.out.println("- " + c));
        int idxCliente = lerInteiro("Índice do cliente (0-based): ");
        if (idxCliente < 0 || idxCliente >= clientes.size()) {
            System.out.println("❌ Cliente inválido!");
            return;
        }
        Cliente cliente = clientes.get(idxCliente);
        int numConta = lerInteiro("Número da conta: ");
        BigDecimal saldoInicial = lerBigDecimal("Saldo inicial: ");
        TipoConta tipo = lerEnum(TipoConta.class, "Tipo (CORRENTE/POUPANCA): ");
        ContaBancaria conta = new ContaBancaria(numConta, saldoInicial, cliente, tipo);
        contas.add(conta);
        System.out.println("✅ Conta criada: " + conta);
    }

    private static void depositar() {
        ContaBancaria conta = selecionarConta("Para depósito: ");
        if (conta != null) {
            BigDecimal valor = lerBigDecimal("Valor: ");
            try {
                conta.depositar(valor);
            } catch (ValorInvalidoException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }

    private static void sacar() {
        ContaBancaria conta = selecionarConta("Para saque: ");
        if (conta != null) {
            BigDecimal valor = lerBigDecimal("Valor: ");
            try {
                conta.sacar(valor);
            } catch (ValorInvalidoException | SaldoInsuficienteException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }

    private static void transferir() {
        ContaBancaria origem = selecionarConta("Origem: ");
        if (origem == null) return;
        ContaBancaria destino = selecionarConta("Destino: ");
        if (destino == null || origem == destino) {
            System.out.println("❌ Destino inválido!");
            return;
        }
        BigDecimal valor = lerBigDecimal("Valor: ");
        try {
            origem.transferir(destino, valor);
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        System.out.println("📊 Contas:");
        contas.forEach(c -> System.out.println("- " + c));
    }

    private static void exibirContaDetalhes() {
        ContaBancaria conta = selecionarConta("Conta: ");
        if (conta != null) {
            conta.exibirDados();
        }
    }

    private static ContaBancaria selecionarConta(String prompt) {
        listarContas();
        if (contas.isEmpty()) {
            System.out.println("❌ Nenhuma conta!");
            return null;
        }
        int idx = lerInteiro(prompt + "Índice: ");
        return (idx >= 0 && idx < contas.size()) ? contas.get(idx) : null;
    }

    private static String lerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int lerInteiro(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(lerString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("❌ Digite um número inteiro!");
            }
        }
    }

    private static BigDecimal lerBigDecimal(String prompt) {
        while (true) {
            try {
                String input = lerString(prompt).replace(",", ".");
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Digite um valor numérico (ex: 100.50)!");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <E extends Enum<E>> E lerEnum(Class<E> enumClass, String prompt) {
        while (true) {
            String input = lerString(prompt).toUpperCase();
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Opções: " + java.util.Arrays.toString(enumClass.getEnumConstants()));
            }
        }
    }
}

