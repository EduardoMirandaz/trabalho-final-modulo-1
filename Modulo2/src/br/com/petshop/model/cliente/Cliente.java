package br.com.petshop.model.cliente;

public class Cliente {
    private String nome;
    private Integer id;
    private Integer quantidadeDePedidos;
    @Override
    public String toString() {
        return "Cliente{\n" +
                "nome='" + nome + '\'' +
                ", id--------->" + id +
                "\nquantidadeDePedidos=" + quantidadeDePedidos +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidadeDePedidos() {
        return quantidadeDePedidos;
    }

    public void setQuantidadeDePedidos(Integer quantidadeDePedidos) {
        this.quantidadeDePedidos = quantidadeDePedidos;
    }
}
