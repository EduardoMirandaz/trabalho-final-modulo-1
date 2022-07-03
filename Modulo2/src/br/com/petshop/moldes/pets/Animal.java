package br.com.petshop.moldes.pets;

import br.com.petshop.moldes.cliente.Cliente;

public class Animal {

    private Integer idAnimal;

    private Cliente cliente;
    private String nome;
    private EnumTipoAnimal tipoAnimal;
    private String raca;
    private Integer pelagem;
    private Integer porte;
    private Integer idade;

    public Integer getIdAnimal() {
        return idAnimal;
    }
    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public EnumTipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }
    public void setTipoAnimal(EnumTipoAnimal tipo) {
        this.tipoAnimal = tipo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getPelagem() {
        return pelagem;
    }

    public void setPelagem(int pelagem) {
        this.pelagem = pelagem;
    }

    public Integer getPorte() {
        return porte;
    }

    public void setPorte(int porte) {
        this.porte = porte;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "idAnimal=" + idAnimal +
                ", nome=" + nome +
                ", tipo=" + tipoAnimal +
                ", raca=" + raca +
                ", pelagem=" + pelagem +
                ", porte=" + porte +
                ", idade=" + idade +
                ", idDono=" + (cliente != null ? cliente.getId() : null) +
                ", nomeDono=" + (cliente != null ? cliente.getNome() : null) +
                "}";
    }
}
