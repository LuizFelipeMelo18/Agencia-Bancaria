package Agencia.Programa;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class AgenciaBancaria {
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();
        operacoes();
    }

    public static void operacoes() {
        int operacao = Integer.parseInt(JOptionPane.showInputDialog("<<BEM VINDO A NOSSA AGENCIA>>" +
                "\n|   SELECIONE A OPERAÇÃO    |" +
                "\n|   Opção 1 - Criar conta   |" +
                "\n|   Opção 2 - Depositar     |" +
                "\n|   Opção 3 - Sacar         |" +
                "\n|   Opção 4 - Transferir    |" +
                "\n|   Opção 5 - Investimento  |" +
                "\n|   Opção 6 - Listar        |" +
                "\n|   Opção 7 - Sair          |"));

        switch (operacao) {
            case 1:
                criarConta();
                break;

            case 2:
                depositar();
                break;

            case 3:
                sacar();
                break;

            case 4:
                transferir();
                break;

            case 5:
                investir();
                break;

            case 6:
                listarContas();
                break;

            case 7:
                JOptionPane.showMessageDialog(null, "Obrigado por utilizar os serviços da nossa agencia<3");
                System.exit(0);

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                operacoes();
                break;
        }
    }

    public static void criarConta() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(JOptionPane.showInputDialog("Nome: "));
        pessoa.setCPF(JOptionPane.showInputDialog("CPF: "));
        pessoa.setEmail(JOptionPane.showInputDialog("Email: "));

        Conta conta = new Conta(pessoa);

        contasBancarias.add(conta);
        JOptionPane.showMessageDialog(null, "Sua conta foi criada com sucesso!");
        operacoes();
    }

    private static Conta encontrarConta(int numeroConta) {
        Conta conta = null;
        if (contasBancarias.size() > 0) {
            for (Conta contaa : contasBancarias) {
                if (contaa.getNumeroConta() == numeroConta) {
                    conta = contaa;
                }
            }
        }
        return conta;
    }

    public static void depositar() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta para depositar: "));
        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            Double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do deposito: "));
            conta.depositar(valorDeposito);
            JOptionPane.showMessageDialog(null, "Valor depositado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não há nenhuma conta cadastrada!");
        }

        operacoes();
    }

    public static void sacar() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta para saque: "));
        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            Double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do saque: "));
            conta.sacar(valorSaque);
            JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não há nenhuma conta cadastrada!");
        }

        operacoes();
    }

    public static void transferir() {
        int numeroContaRemetente = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta do remetente: "));
        Conta contaRemetente = encontrarConta(numeroContaRemetente);

        if (contaRemetente != null) {
            int numeroContaDestinatario = Integer
                    .parseInt(JOptionPane.showInputDialog("Numero da conta do destinatario: "));
            Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

            if (contaDestinatario != null) {
                Double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da transferência: "));
                contaRemetente.transferir(contaDestinatario, valor);
                JOptionPane.showMessageDialog(null, "Transferência feita com sucesso!");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Não há nenhuma conta cadastrada!");
        }
        operacoes();
    }

    public static void investir() {
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.yesButtonText", "Sim");

        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta para investir: "));
        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            Double valorInvestir = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor para investir: "));
            int retirarDoSaldo = JOptionPane.showConfirmDialog(null, "Deseja retirar o valor do saldo?");

            if (retirarDoSaldo == JOptionPane.YES_OPTION) {
                conta.investir(valorInvestir);
            } else {
                conta.investirSemRetirarSaldo(valorInvestir);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não há nenhuma conta cadastrada!");
        }

        operacoes();
    }

    public static void listarContas() {
        if (contasBancarias.size() > 0) {
            for (Conta conta : contasBancarias) {
                JOptionPane.showMessageDialog(null, conta);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não há nenhuma conta cadastrada!");
        }

        operacoes();
    }
}
