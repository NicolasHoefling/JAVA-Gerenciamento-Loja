package nicolashoefling.loja;

import java.math.BigDecimal;

public class ItemCarrinho {
    public Produto produto;
    public int quantidade;
    public BigDecimal desconto;

    public ItemCarrinho(Produto produto, int quantidade, BigDecimal desconto) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.desconto = desconto;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public BigDecimal obterCusto() {
        BigDecimal precoComDesconto = produto.obterValorComDesconto(desconto);
        return precoComDesconto.multiply(new BigDecimal(quantidade));
    }
}