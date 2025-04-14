package nicolashoefling.loja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;


public class LojaGUI {
    private JFrame frame;
    private Loja loja;

    public LojaGUI() {
        loja = new Loja();
        frame = new JFrame("Loja - Menu Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(8, 1));

        JButton btnCadastrarCliente = new JButton("1 - Cadastrar Cliente");
        JButton btnCadastrarProduto = new JButton("2 - Cadastrar Produto");
        JButton btnIniciarVenda = new JButton("3 - Iniciar Venda");
        JButton btnAdicionarProduto = new JButton("4 - Adicionar Produto ao Carrinho");
        JButton btnExibirCarrinho = new JButton("5 - Exibir Conteúdo do Carrinho");
        JButton btnFecharVenda = new JButton("6 - Fechar Venda");
        JButton btnSair = new JButton("0 - Sair");

        frame.add(btnCadastrarCliente);
        frame.add(btnCadastrarProduto);
        frame.add(btnIniciarVenda);
        frame.add(btnAdicionarProduto);
        frame.add(btnExibirCarrinho);
        frame.add(btnFecharVenda);
        frame.add(btnSair);

        btnCadastrarCliente.addActionListener(e -> cadastrarCliente());
        btnCadastrarProduto.addActionListener(e -> cadastrarProduto());
        btnIniciarVenda.addActionListener(e -> iniciarVenda());
        btnAdicionarProduto.addActionListener(e -> adicionarProdutoAoCarrinho());
        btnExibirCarrinho.addActionListener(e -> exibirCarrinho());
        btnFecharVenda.addActionListener(e -> fecharVenda());
        btnSair.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Nome do cliente:");
        String cpfStr = JOptionPane.showInputDialog("CPF do cliente:");
        if (nome != null && cpfStr != null) {
            try {
                loja.cadastrarCliente(new Cliente(nome, new BigInteger(cpfStr)));
                JOptionPane.showMessageDialog(frame, "Cliente cadastrado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "CPF inválido!");
            }
        }
    }

    private void cadastrarProduto() {
        String codigoStr = JOptionPane.showInputDialog("Código do produto:");
        String valorStr = JOptionPane.showInputDialog("Valor do produto:");
        if (codigoStr != null && valorStr != null) {
            try {
                Produto produto = new Produto(Integer.parseInt(codigoStr), new BigDecimal(valorStr));
                loja.cadastrarProduto(produto);
                JOptionPane.showMessageDialog(frame, "Produto cadastrado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Código ou valor inválido!");
            }
        }
    }

    private void iniciarVenda() {
        String nomeCliente = JOptionPane.showInputDialog("Nome do cliente para a venda:");
        if (loja.iniciarVenda(nomeCliente)) {
            JOptionPane.showMessageDialog(frame, "Venda iniciada para " + nomeCliente);
        } else {
            JOptionPane.showMessageDialog(frame, "Cliente não encontrado!");
        }
    }

    private void adicionarProdutoAoCarrinho() {
        String codigoStr = JOptionPane.showInputDialog("Código do produto:");
        String quantidadeStr = JOptionPane.showInputDialog("Quantidade:");
        String descontoStr = JOptionPane.showInputDialog("Desconto (%):");
        if (codigoStr != null && quantidadeStr != null && descontoStr != null) {
            try {
                int codigo = Integer.parseInt(codigoStr);
                int quantidade = Integer.parseInt(quantidadeStr);
                BigDecimal desconto = new BigDecimal(descontoStr);
                if (loja.adicionarProduto(codigo, quantidade, desconto)) {
                    JOptionPane.showMessageDialog(frame, "Produto adicionado ao carrinho!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar produto!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Entrada inválida!");
            }
        }
    }

    private void exibirCarrinho() {
        JOptionPane.showMessageDialog(frame, loja.vendaEmAndamento != null ? loja.vendaEmAndamento.toString() : "Nenhuma venda em andamento");
    }

    private void fecharVenda() {
        loja.fecharVenda();
        JOptionPane.showMessageDialog(frame, "Venda finalizada com sucesso!");
    }

    public static void main(String[] args) {
        new LojaGUI();
    }
}
