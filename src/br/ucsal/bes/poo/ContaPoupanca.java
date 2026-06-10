package br.ucsal.bes.poo;

public class ContaPoupanca extends Conta implements Operavel {
    private double taxaRendimento;

    public ContaPoupanca(int numConta, Cliente titular, String senha, double rendimento) {
        super(numConta, titular, senha);
        this.taxaRendimento = rendimento;

    }

    public void transferir(double valor, Conta destino) {
        this.sacar(valor);
        destino.depositar(valor);
    }

    public double consultarSaldo() {
        return getSaldo();
    }

    public void atualizarSaldo() {
        double rendimento = getSaldo() * (taxaRendimento / 100);
        setSaldo(getSaldo() + rendimento);
        System.out.println("[Conta Poupança] Rendimento de R$ " + rendimento + " aplicado" +
                "\nNovo saldo: R$ " + getSaldo());
    }

    public void exibirDados() {
        super.exibirDados();
        System.out.println("Taxa de rendimento: " + taxaRendimento + "%");
        System.out.println("Rendimento mensal estimado: R$ " + String.format("%.2f", getRendimentoMensal()));

    }
    public double getRendimentoMensal() {
        return getSaldo() * taxaRendimento / 100;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

}
