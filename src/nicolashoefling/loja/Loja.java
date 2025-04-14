package nicolashoefling.loja;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class Loja {
    public List<Cliente> clientes;
    public List<Produto> estoque;
    public List<CarrinhoDeCompras> vendasFinalizadas;
    public CarrinhoDeCompras vendaEmAndamento;

    public Loja() {
        this.clientes = new ArrayList<>();
        this.estoque = new ArrayList<>();
        this.vendasFinalizadas = new ArrayList<>();
        this.vendaEmAndamento = null;
    }

    public void cadastrarCliente(Cliente novo) {
        clientes.add(novo);
    }

    public Cliente pesquisarCliente(String nome) {
        for (Cliente c : clientes) {
            if (c.nome.equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    public void cadastrarProduto(Produto novo) {
        estoque.add(novo);
    }

    public Produto pesquisarProduto(int codigo) {
        for (Produto p : estoque) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public boolean iniciarVenda(String nomeCliente) {
        Cliente cliente = pesquisarCliente(nomeCliente);
        if (cliente != null) {
            vendaEmAndamento = new CarrinhoDeCompras(cliente);
            return true;
        }
        return false;
    }

    public boolean adicionarProduto(int codigo, int quantidade, BigDecimal desconto) {
        if (vendaEmAndamento == null) {
            return false;
        }
        Produto produto = pesquisarProduto(codigo);
        if (produto != null) {
            return vendaEmAndamento.adicionarProduto(produto, quantidade, desconto);
        }
        return false;
    }

    public void fecharVenda() {
        if (vendaEmAndamento != null) {
            vendasFinalizadas.add(vendaEmAndamento);
            vendaEmAndamento = null;
        }
    }
}
