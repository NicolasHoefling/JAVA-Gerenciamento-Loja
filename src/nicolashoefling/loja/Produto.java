package nicolashoefling.loja;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Produto {
    private Integer codigo;
    private BigDecimal valor;

    public Produto(Integer codigo, BigDecimal valor) {
        this.codigo = codigo;
        this.valor = valor.setScale(2, RoundingMode.HALF_EVEN); // Garante duas casas decimais
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal obterValorComDesconto(BigDecimal percentual) {
        BigDecimal desconto = valor.multiply(percentual).divide(new BigDecimal("100"), 2, RoundingMode.HALF_EVEN);
        return valor.subtract(desconto);
    }
}

