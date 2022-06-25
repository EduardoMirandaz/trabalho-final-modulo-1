package br.com.petshop.moldes.cliente;

import br.com.petshop.moldes.pets.Animal;

import java.util.ArrayList;

public class Cliente {
    private String nome;
    private Integer id;
    private Endereco endereco;
    private ArrayList<Contato> contatos = new ArrayList<Contato>();
    private ArrayList<Animal> pets = new ArrayList<Animal>();

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    public ArrayList<Animal> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Animal> pets) {
        this.pets = pets;
    }
}
