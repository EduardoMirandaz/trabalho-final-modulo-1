package br.com.petshop.moldes.cliente;

import br.com.petshop.moldes.pets.Animal;

import java.util.ArrayList;

public class Cliente {
    private String nome;
    private Integer id;

    private Integer quantidadeDePedidos;
    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", quantidadeDePedidos=" + quantidadeDePedidos +
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
