package br.com.petshop.moldes.cliente;

public class Contato {
    private String descricao;
    private String telefone;

    @Override
    public String toString() {
        return "Contato{" +
                "descricao='" + descricao + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
