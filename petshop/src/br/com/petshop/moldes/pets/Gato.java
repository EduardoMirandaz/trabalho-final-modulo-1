package br.com.petshop.moldes.pets;

public class Gato extends Animal {

    boolean podeBrincarComBolaDeLa;

    public boolean podeBrincarComBolaDeLa() {
        return podeBrincarComBolaDeLa;
    }

    public void setPodeBrincarComBolaDeLa(boolean podeBrincarComBolaDeLa) {
        this.podeBrincarComBolaDeLa = podeBrincarComBolaDeLa;
    }

    @Override
    public void contratarBanho() {
        switch (this.getPorte()){
            case 1 -> {
                adicionarValorNoContrato(25);
                setQuantidadeContratos(getQuantidadeContratos() +1);
                System.out.println("O servico de banho no valor de R$25 foi contratado com sucesso");
            }
            case 2 -> {
                adicionarValorNoContrato(34);
                setQuantidadeContratos(getQuantidadeContratos() +1);
                System.out.println("O servico de banho no valor de R$34 foi contratado com sucesso");
            }
            case 3 -> {
                adicionarValorNoContrato(50);
                setQuantidadeContratos(getQuantidadeContratos() +1);
                System.out.println("O servico de banho no valor de R$50 foi contratado com sucesso");
            }
        }
    }

    @Override
    public void contratarTosa() {
        switch (this.getPelagem()){
            case 1 -> {
                adicionarValorNoContrato(20);
                setQuantidadeContratos(getQuantidadeContratos() +1);
                System.out.println("O servico de tosagem no valor de R$20 foi contratado com sucesso");
            }
            case 2 -> {
                adicionarValorNoContrato(35);
                setQuantidadeContratos(getQuantidadeContratos() +1);
                System.out.println("O servico de tosagem no valor de R$35 foi contratado com sucesso");
            }
            case 3 -> {
                adicionarValorNoContrato(50);
                setQuantidadeContratos(getQuantidadeContratos() +1);
                System.out.println("O servico de tosagem no valor de R$50 foi contratado com sucesso");
            }
        }
    }
}
