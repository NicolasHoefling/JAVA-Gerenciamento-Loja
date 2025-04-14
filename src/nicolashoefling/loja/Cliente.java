package nicolashoefling.loja;

import java.math.BigInteger;

public class Cliente {
    public String nome;
    public BigInteger cpf;

    public Cliente(String nome, BigInteger cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigInteger getCpf() {
        return cpf;
    }

    public void setCpf(BigInteger cpf) {
        this.cpf = cpf;
    }
}
