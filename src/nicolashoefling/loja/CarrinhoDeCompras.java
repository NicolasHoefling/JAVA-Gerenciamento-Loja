package nicolashoefling.loja;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
    public List<ItemCarrinho> itens;
    public Cliente cliente;

    public CarrinhoDeCompras(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public boolean adicionarProduto(Produto produto, int quantidade, BigDecimal desconto) {
        if (quantidade <= 0 || desconto.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }
        ItemCarrinho item = new ItemCarrinho(produto, quantidade, desconto);
        itens.add(item);
        return true;
    }

    public BigDecimal obterValorTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemCarrinho item : itens) {
            total = total.add(item.obterCusto());
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carrinho de compras do cliente: ").append(cliente.nome).append("\n");
        for (ItemCarrinho item : itens) {
            sb.append("Produto: ").append(item.getProduto().getCodigo())
                    .append(", Quantidade: ").append(item.getQuantidade())
                    .append(", Desconto: ").append(item.getDesconto()).append("%\n");
        }
        sb.append("Total: ").append(obterValorTotal()).append("\n");
        return sb.toString();
    }
}
