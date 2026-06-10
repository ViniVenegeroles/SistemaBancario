package br.ucsal.bes.poo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaBancario2 {
    public static int numeroDaConta = 1;
    static Scanner sc = new Scanner(System.in);
    static List<Conta> contas = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n=== Banco UCSAL ===");
            System.out.println("1 - Cadastrar conta");
            System.out.println("2 - Entrar na conta");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> entrar();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    static void menuConta(Conta conta) {
        int opcao;

        do {
            System.out.println("\n--- Conta #" + conta.getNumero() +
                    " | " + conta.getTitular().getNome() + " ---");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Consultar saldo");
            System.out.println("5 - Atualizar saldo (taxas/rendimentos)");
            System.out.println("0 - Sair da conta");
            System.out.print("Opcao: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> depositar(conta);
                case 2 -> sacar(conta);
                case 3 -> transferir(conta);
                case 4 -> conta.exibirDados();
                case 5 -> conta.atualizarSaldo();
                case 0 -> System.out.println("Saindo da conta...");
                default -> System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    static void cadastrar() {

        try {
            System.out.println("\n-- Cadastrar Conta --");
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Endereco: ");
            String endereco = sc.nextLine();
            System.out.print("Senha (minimo 4 caracteres): ");
            String senha = sc.nextLine();

            System.out.println("Tipo: 1 - Corrente  2 - Poupanca");
            System.out.print("Opcao: ");
            int tipo = lerInt();
            Cliente cliente = new Cliente(nome, cpf, endereco);
            Conta nova;

            if (tipo == 1) {
                System.out.print("Limite (R$): ");
                double limite = lerDouble();
                nova = new ContaCorrente(numeroDaConta++, cliente, senha, limite);
            } else {
                System.out.print("Taxa de rendimento em %: ");
                double taxa = lerDouble();
                nova = new ContaPoupanca(numeroDaConta++, cliente, senha, taxa);
            }

            contas.add(nova);
            System.out.println("Conta #" + nova.getNumero() + " criada com sucesso!");

        } catch (IllegalArgumentException e) {
            System.err.println("Erro no cadastro: " + e.getMessage());
        }
    }

    static void entrar() {
        if (contas.isEmpty()) {
            System.err.println("Nenhuma conta cadastrada.");
            return;
        }

        System.out.print("Numero da conta: ");
        int numero = lerInt();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Conta conta = buscarConta(numero);

        if (conta == null) {
            System.err.println("Conta nao encontrada.");
            return;
        }

        if (!conta.verificarSenha(senha)) {
            System.err.println("Senha incorreta.");
            return;
        }

        System.out.println("Bem-vindo, " + conta.getTitular().getNome() + "!");
        menuConta(conta);

    }

    static Conta buscarConta(int numero) {
        for (Conta c : contas) {
            if (c.getNumero() == numero)
                return c;
        }
        return null;
    }

    static void depositar(Conta conta) {
        System.out.print("Valor: ");
        double valor = lerDouble();
        try {
            conta.depositar(valor);
            System.out.printf("Deposito realizado! Saldo: R$ %.2f%n", conta.consultarSaldo());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro no deposito: " + e.getMessage());
        }
    }

    static void sacar(Conta conta) {
        System.out.print("Valor: ");
        double valor = lerDouble();
        try {
            conta.sacar(valor);
            System.out.printf("Saque realizado! Saldo: R$ %.2f%n",
                    conta.consultarSaldo());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro no saque: " + e.getMessage());
        }
    }

    static void transferir(Conta conta) {
        if (contas.size() < 2) {
            System.out.println("Nao ha outras contas cadastradas.");
            return;
        }
        try {
            System.out.print("Numero da conta destino: ");
            int numero = lerInt();

            Conta destino = buscarConta(numero);

            if (destino == null) {
                System.err.println("Conta destino nao encontrada.");
                return;
            }

            if (destino == conta) {
                System.err.println("Nao e possivel transferir para a propria conta.");
                return;
            }

            System.out.print("Valor: ");
            double valor = lerDouble();
            conta.transferir(valor, destino);
            System.out.printf("Transferencia realizada! Saldo: R$ %.2f%n", conta.consultarSaldo());

        } catch (IllegalArgumentException e) {
            System.err.println("Erro na transferencia: " + e.getMessage());
        }
    }

    static int lerInt() {
        while (true) {
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Digite apenas numeros: ");
                sc.nextLine(); // limpa o buffer
            }
        }
    }

    static double lerDouble() {
        while (true) {
            try {
                double valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Digite um valor numerico valido: ");
                sc.nextLine();
            }
        }
    }
}