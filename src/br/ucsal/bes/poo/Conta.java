package br.ucsal.bes.poo;

public abstract class Conta implements Operavel {
    private int numConta;
    private double saldo;
    private Cliente titular;
    private String senha;


    public Conta(int numConta, Cliente titular, String senha) {
        if (senha == null || senha.length() < 4) {
            throw new IllegalArgumentException("Senha deve ter no mínimo 4 caracteres.");
        }
        this.numConta = numConta;
        this.saldo = 0;
        this.titular = titular;
        this.senha = senha;
    }

    public boolean verificarSenha(String senhaInformada) {
        return this.senha.equals(senhaInformada);
    }

    public void depositar(double valor) {
        if (valor <= 0){
            throw new IllegalArgumentException("Valor do deposito deve ser maior que R$ 0,00.");
        }
        this.saldo += valor;
    }


    public void sacar(double valor) {
        if (valor <= 0){
            throw new IllegalArgumentException("Valor do saque deve ser maior que R$ 0,00.");
        }
        if (valor > this.saldo){
            throw new IllegalArgumentException("Saldo insuficiente. Disponivel: R$ " + String.format("%.2f", getSaldo()));
        }
        this.saldo -= valor;
    }

    public abstract void atualizarSaldo();

    public void exibirDados() {
        System.out.println("\n=== Dados da Conta ===");
        titular.exibirDados();
        System.out.println("\nNúmero da conta: " + numConta);
        System.out.println("Saldo: R$ " + saldo);

    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumero() {
        return numConta;
    }

    public Cliente getTitular() {
        return titular;
    }


}

