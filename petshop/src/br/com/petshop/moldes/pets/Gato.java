package br.com.petshop.moldes.pets;

public class Gato extends Animal {
    @Override
    public void darBanho() {
        switch (this.getPorte()){
            case 1 -> adicionarValorNoContrato(25);
            case 2 -> adicionarValorNoContrato(34);
            case 3 -> adicionarValorNoContrato(50);
        }
    }

    @Override
    public void tosar() {
        switch (this.getPelagem()){
            case 1 -> adicionarValorNoContrato(20);
            case 2 -> adicionarValorNoContrato(35);
            case 3 -> adicionarValorNoContrato(50);
        }
    }
}
