package br.com.petshop.moldes.pets;

public class Cachorro extends Animal {

    boolean podeRoerOsso;

    public boolean isPodeRoerOsso() {
        return podeRoerOsso;
    }

    public void setPodeRoerOsso(boolean podeRoerOsso) {
        this.podeRoerOsso = podeRoerOsso;
    }

    @Override
    public void contratarBanho() {
        switch (this.getPorte()){
            case 1 -> {
                adicionarValorNoContrato(50);
                System.out.println("O servico de banho no valor de R$50 foi contratado com sucesso!\n");
                setQuantidadeContratos(getQuantidadeContratos() +1);
            }
            case 2 -> {
                adicionarValorNoContrato(75);
                System.out.println("O servico de banho no valor de R$75 foi contratado com sucesso!\n");
                setQuantidadeContratos(getQuantidadeContratos() +1);
            }
            case 3 -> {
                adicionarValorNoContrato(100);
                System.out.println("O servico de banho no valor de R$100 foi contratado com sucesso!\n");
                setQuantidadeContratos(getQuantidadeContratos() +1);
            }
        }
    }

    @Override
    public void contratarTosa() {
        switch (this.getPelagem()){
            case 1 -> {
                adicionarValorNoContrato(45);
                System.out.println("O servico de tosagem no valor de R$45 foi contratado com sucesso!\n");
                setQuantidadeContratos(getQuantidadeContratos() +1);
            }
            case 2 -> {
                adicionarValorNoContrato(70);
                System.out.println("O servico de tosagem no valor de R$70 foi contratado com sucesso!\n");
                setQuantidadeContratos(getQuantidadeContratos() +1);
            }
            case 3 -> {
                adicionarValorNoContrato(120);
                System.out.println("O servico de tosagem no valor de R$120 foi contratado com sucesso!\n");
                setQuantidadeContratos(getQuantidadeContratos() +1);
            }
        }
    }

}
