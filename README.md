# 🏦 Banco Profissional - Sistema Bancário Enterprise Edition

![Demo](demo.gif) *(Adicione screenshot do menu rodando)*

## 🚀 Visão Geral
Sistema bancário completo em **Java 17+** desenvolvido como projeto de portfólio para **LinkedIn**. 
Demonstra **best practices enterprise**:
- **SOLID principles**
- **BigDecimal** para precisão financeira
- **Custom exceptions**, **enums**, **validação CPF**
- **Testes unitários JUnit5**
- **Maven** build automation
- **Arquitetura limpa** com packages dedicados (model, exception, util)
- **UI interativa** com menu loop e handling de erros

## ✨ Features
- Cadastro de múltiplos **clientes** com validação CPF (algoritmo oficial)
- Criação de **contas** (Corrente/Poupança) com saldo inicial
- Operações: **depósito, saque, transferência** entre contas
- **Histórico de transações** com timestamp
- **Tratamento de exceções** (SaldoInsuficiente, ValorInvalido, etc.)
- **Menu intuitivo** com listagem e seleção

## 🛠️ Tech Stack
```
Java 17 | Maven | JUnit5 | BigDecimal | Enums | Custom Exceptions
```
100% puro Java, sem frameworks externos.

## 📦 Como Rodar
1. **Clone/Abra o projeto**
2. **Maven setup** (se não tiver: `winget install Apache.Maven`)
3. ```bash
   mvn clean compile
   mvn exec:java -Dexec.mainClass="com.uniube.BancoApp"
   ```
4. Ou VSCode: Run Java > BancoApp

**Demo esperado:**
```
🏦 === BANCO PROFISSIONAL ===
1. Criar Cliente > João, 12345678901
2. Criar Conta > 12345, 1000.00, CORRENTE
3. Depositar 500 > Saldo: 1500.00
...
```

## ✅ Testes
```bash
mvn test
```
Cobertura: Construtores, operações, exceptions (ver relatórios).

## 📈 Por que este projeto?
- **Profissionalizado** de um código básico para **enterprise-ready**
- Pronto para **entrevistas técnicas** e **portfólio**
- Fácil extensão: Adicione BD (JDBC/Hibernate), GUI (JavaFX), REST API (Spring Boot)

**Autor:** Mathe | [LinkedIn](https://linkedin.com/in/mathe) | Contato para colaborações 🚀

**Status:** Production-ready demo. Código limpo e testado!

"# Banco-Java" 
