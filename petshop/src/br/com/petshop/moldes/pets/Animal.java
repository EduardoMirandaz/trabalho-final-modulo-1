package br.com.petshop.moldes.pets;

import br.com.petshop.interfaces.Servicos;

public abstract class Animal implements Servicos {
    private String nome;
    private String raca;
    private int pelagem;
    private int porte;
    private double valorDoContrato;
    private int quantidadeContratos;


    public void cancelarContrato() {
        if(getValorDoContrato() != 0) {
            this.setValorDoContrato(0);
            this.setQuantidadeContratos(0);
            System.out.println("O seu contrato foi cancelado\n");
        } else {
            System.out.println("Você ainda não tem um contrato");
        }
    }
    public void adicionarValorNoContrato(double valor) {
        this.setValorDoContrato(getValorDoContrato() + valor);
    }
    
    public void adestrar() {
        this.adicionarValorNoContrato(200);
        setQuantidadeContratos(getQuantidadeContratos() +1);
        System.out.println("O servico de adestramento no valor de R$200 foi contratado com sucesso");
    }

    public void contratarCorteDeUnha() {
        this.adicionarValorNoContrato(20);
        setQuantidadeContratos(getQuantidadeContratos() +1);
        System.out.println("O servico de cortar unha no valor de R$20 foi contratado com sucesso");
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

    public int getQuantidadeContratos() {
        return quantidadeContratos;
    }

    public void setQuantidadeContratos(int quantidadeContratos) {
        this.quantidadeContratos = quantidadeContratos;
    }
}
