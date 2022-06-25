package br.com.petshop.moldes.pets;

import br.com.petshop.interfaces.Servicos;

public abstract class Animal implements Servicos {
    private String nome;
    private String raca;
    private int pelagem;
    private int porte;
    private double valorDoContrato;

    public void adicionarValorNoContrato(double valor) {
        this.setValorDoContrato(getValorDoContrato() + valor);
    }
    
    public void adestrar() {
        this.adicionarValorNoContrato(200);
    }

    public void cortarUnha() {
        this.adicionarValorNoContrato(20);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getPelagem() {
        return pelagem;
    }

    public void setPelagem(int pelagem) {
        this.pelagem = pelagem;
    }

    public int getPorte() {
        return porte;
    }

    public void setPorte(int porte) {
        this.porte = porte;
    }

    public double getValorDoContrato() {
        return valorDoContrato;
    }
    public void setValorDoContrato(double valor){
        this.valorDoContrato = valor;
    }
}
