# 🏦 Sistema Bancário Simples

Trabalho Prático da disciplina de **Programação Orientada a Objetos**  
Curso: Engenharia de Software — UCSAL  
Professor(a): Patrícia Almeida

---

## 📋 Sobre o projeto

Sistema bancário via terminal que permite cadastrar contas, realizar login e executar operações financeiras. O projeto demonstra os principais conceitos de POO: classe, objeto, encapsulamento, herança, polimorfismo, classe abstrata e interface.

---

## 🗂️ Estrutura do projeto

```
src/
└──  br/ucsal/bes/poo
    ├── SistemaBancario2.java       ← ponto de entrada, menus e operações
    ├── Cliente.java          ← dados do titular da conta
    ├── Conta.java            ← classe abstrata base
    ├── ContaCorrente.java    ← conta com limite e tarifa mensal
    ├── ContaPoupanca.java    ← conta com rendimento mensal
    └── Operavel.java         ← interface com operações básicas
```

---

## 🧠 Conceitos de POO aplicados

| Conceito | Onde foi aplicado |
|---|---|
| **Classe e Objeto** | `Cliente`, `Conta`, `ContaCorrente`, `ContaPoupanca` |
| **Encapsulamento** | Atributos `private` com getters e setters |
| **Herança** | `ContaCorrente` e `ContaPoupanca` estendem `Conta` |
| **Polimorfismo** | `atualizarSaldo()` sobrescrito em cada subclasse |
| **Classe Abstrata** | `Conta` é abstrata, não pode ser instanciada diretamente |
| **Interface** | `Operavel` implementada por `ContaCorrente` e `ContaPoupanca` |
| **Tratamento de Exceção** | `IllegalArgumentException` e `InputMismatchException` |

---

## ⚙️ Funcionalidades

- Cadastrar conta corrente ou poupança
- Login com número da conta e senha
- Depositar
- Sacar (conta corrente permite saldo negativo até o limite)
- Transferir entre contas
- Consultar saldo e dados da conta
- Atualizar saldo (aplica tarifa na corrente ou rendimento na poupança)

---

## 🖥️ Como rodar

### Pré-requisito
- Java 11 ou superior instalado

### No IntelliJ IDEA

1. Abra o IntelliJ
2. Clique em **File → Open** e selecione a pasta do projeto
3. Aguarde o IntelliJ indexar os arquivos
4. Abra `AppSistema.java`
5. Clique no botão **▶ Run** ou pressione `Shift + F10`

### No Eclipse

1. Abra o Eclipse
2. Clique em **File → Import**
3. Selecione **General → Existing Projects into Workspace**
4. Clique em **Browse** e selecione a pasta do projeto
5. Clique em **Finish**
6. Clique com botão direito em `AppSistema.java`
7. Selecione **Run As → Java Application**

---

## 🔄 Fluxo de uso

```
=== Banco UCSAL ===
1 - Cadastrar conta
2 - Entrar na conta
0 - Sair

  → após entrar com número e senha:

--- Conta #1 | João ---
1 - Depositar
2 - Sacar
3 - Transferir
4 - Consultar saldo
5 - Atualizar saldo (taxas/rendimentos)
0 - Sair da conta
```

---

## 📌 Observações

- A senha deve ter no mínimo 4 caracteres
- Conta Corrente permite saldo negativo até o valor do limite
- A taxa de rendimento da Conta Poupança deve ser informada em % (ex: `5` para 5%)
- O sistema valida entradas inválidas (letras no lugar de números)

---

## 👥 Equipe

| Nome     | GitHub |
|----------|---|
| Vinicius | [@ViniVenegeroles](https://github.com/ViniVenegeroles) |
| Allan    | [@usuariodele](https://github.com/usuariodele) |