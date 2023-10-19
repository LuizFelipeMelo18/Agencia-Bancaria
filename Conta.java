package Agencia.Programa;

import javax.swing.JOptionPane;
import Agencia.utilitarios.Utils;

public class Conta {
    private static int contadorDeContas = 1;

    private int numeroConta;
    private Pessoa pessoa;
    private Double saldo = 0.0;
    private Double investir = 0.0;

    public Conta(Pessoa pessoa) {
        this.numeroConta = contadorDeContas;
        this.pessoa = pessoa;
        contadorDeContas += 1;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getInvestir() {
        return investir;
    }

    public void setInvestir(Double investir) {
        this.investir = investir;
    }

    public String toString() {
        return "\nNúmero da conta: " + this.getNumeroConta() +
                "\nNome: " + this.pessoa.getNome() +
                "\nCPF: " + this.pessoa.getCPF() +
                "\nEmail: " + this.pessoa.getEmail() +
                "\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
                "\nInvestimentos: " + Utils.doubleToString(this.getInvestir());
    }

    public void depositar(Double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar o depósito!");
        }
    }

    public void sacar(Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar o saque!");
        }
    }

    public void transferir(Conta contaParaDeposito, Double valor) {
        if (valor >= 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);

            contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a transferência!");
        }
    }

    public void investir(Double valorInvestir) {
        investirComRetiradaDoSaldo(valorInvestir, true);
    }

    public void investirSemRetirarSaldo(Double valorInvestir) {
        investirComRetiradaDoSaldo(valorInvestir, false);
    }

    private void investirComRetiradaDoSaldo(Double valorInvestir, boolean retirarDoSaldo) {
        if (valorInvestir > 0 && (retirarDoSaldo ? this.getSaldo() >= valorInvestir : true)) {
            if (retirarDoSaldo) {
                setSaldo(getSaldo() - valorInvestir);
            }
            setInvestir(getInvestir() + valorInvestir);
            JOptionPane.showMessageDialog(null, "Investimento realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar o investimento!");
        }
    }
}
