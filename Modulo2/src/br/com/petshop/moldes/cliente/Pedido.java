package br.com.petshop.moldes.cliente;

import br.com.petshop.moldes.pets.Animal;

public class Pedido {
    private Integer idPedido;
    private Cliente cliente;
    private Animal animal;
    private Double valor;
    private String descricao;
    private Integer IdAnimal;


    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Pedido{\n" +
                "\t--------->idPedido=[" + idPedido +
                "]\t\n idCliente=[" + cliente.getId() +
                "],\t\n nomeCliente=" + cliente.getNome() +
                ",\t\n idPet=[" + animal.getIdAnimal() +
                "],\t\n nomePet=" + animal.getNome() +
                ",\t\n valor=" + valor +
                ",\t\n descricao=" + descricao +
                "\n}";
    }

    public void setIdAnimal(Integer id_animal) {
        this.IdAnimal = id_animal;
    }

    public Integer getIdAnimal() {
        return IdAnimal;
    }
}
