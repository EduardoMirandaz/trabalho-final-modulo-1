package br.com.petshop.moldes.pets;

public class Cachorro extends Animal {
    @Override
    public void darBanho() {
        switch (this.getPorte()){
            case 1 -> adicionarValorNoContrato(50);
            case 2 -> adicionarValorNoContrato(75);
            case 3 -> adicionarValorNoContrato(100);
        }
    }

    @Override
    public void tosar() {
        switch (this.getPelagem()){
            case 1 -> adicionarValorNoContrato(45);
            case 2 -> adicionarValorNoContrato(70);
            case 3 -> adicionarValorNoContrato(120);
        }
    }

}
