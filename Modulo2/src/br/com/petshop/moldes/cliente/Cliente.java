package br.com.petshop.moldes.cliente;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.repository.ClienteRepository;

import java.util.ArrayList;

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

    public void setQuantidadeDePedidos(Integer quantidadeDePedidos/*, int idCliente*/) {
//        ClienteRepository clienteRepository = new ClienteRepository();
//        try {
//            clienteRepository.setPedidosBanco(idCliente, quantidadeDePedidos);
//        } catch (BancoDeDadosException e) {
//            throw new RuntimeException(e);
//        }
        this.quantidadeDePedidos = quantidadeDePedidos;
    }
}
