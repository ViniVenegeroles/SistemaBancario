package br.ucsal.bes.poo;

public class ContaCorrente extends Conta implements Operavel {
    private double limite;
    private static final double TARIFA = 7.50;

    public ContaCorrente(int numConta, Cliente titular, String senha, double limite) {
        super(numConta, titular, senha);
        this.limite = limite;

    }

    public void transferir(double valor, Conta destino) {
        this.sacar(valor);
        destino.depositar(valor);
    }

    public double consultarSaldo() {
        return getSaldo() + limite;
    }

    public void sacar(double valor) {
        if (valor <= 0){
            throw new IllegalArgumentException("Valor do saque deve ser maior que R$ 0,00.");
        }
        if (valor > getSaldo() + limite){
            throw new IllegalArgumentException("Limite insuficiente. Disponivel: R$ " + String.format("%.2f", getSaldo() + limite));
        }
        setSaldo(getSaldo() - valor);
    }

    public void atualizarSaldo() {
        double novoSaldo = getSaldo() - TARIFA;
        setSaldo(novoSaldo);
        System.out.println("[Conta Corrente] Tarifa de manutenção de R$ " + TARIFA + " descontada." +
                "\nNovo saldo: R$ " + novoSaldo +"\nLimite: R$ "+ this.limite);
    }

    public void exibirDados() {
        super.exibirDados();
        System.out.println("Limite: R$ " + limite);
        System.out.println("Saldo disponível: R$ " + consultarSaldo());
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

}

