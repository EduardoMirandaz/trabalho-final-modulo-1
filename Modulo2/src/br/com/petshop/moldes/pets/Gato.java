package br.com.petshop.moldes.pets;

public class Gato extends Animal {

    boolean podeBrincarComBolaDeLa;

    public boolean podeBrincarComBolaDeLa() {
        return podeBrincarComBolaDeLa;
    }

    public void setPodeBrincarComBolaDeLa(boolean podeBrincarComBolaDeLa) {
        this.podeBrincarComBolaDeLa = podeBrincarComBolaDeLa;
    }

//    @Override
//    public void contratarBanho() {
//        if(this.getPorte() >= 0 && this.getPorte() <= 4){
//            this.adicionarValorNoContrato(25);
//            System.out.println("O servico de banho no valor de R$25 foi contratado com sucesso!\n");
//            setQuantidadeContratos(getQuantidadeContratos() +1);
//        }
//        if(this.getPorte() > 4 && this.getPorte() <= 7){
//            this.adicionarValorNoContrato(35);
//            System.out.println("O servico de banho no valor de R$35 foi contratado com sucesso!\n");
//            setQuantidadeContratos(getQuantidadeContratos() +1);
//        }
//        if(this.getPorte() > 7 && this.getPorte() <=9){
//            this.adicionarValorNoContrato(50);
//            System.out.println("O servico de banho no valor de R$100 foi contratado com sucesso!\n");
//            setQuantidadeContratos(getQuantidadeContratos() +1);
//        }
//    }
//
//    @Override
//    public void contratarTosa() {
//        if(this.getPelagem() >= 0 && this.getPelagem() <= 4){
//            this.adicionarValorNoContrato(20);
//            System.out.println("O servico de tosagem no valor de R$20 foi contratado com sucesso!\n");
//            setQuantidadeContratos(getQuantidadeContratos() +1);
//        }
//        if(this.getPelagem() > 4 && this.getPelagem() <= 7){
//            this.adicionarValorNoContrato(35);
//            System.out.println("O servico de tosagem no valor de R$35 foi contratado com sucesso!\n");
//            setQuantidadeContratos(getQuantidadeContratos() +1);
//        }
//        if(this.getPelagem() > 7 && this.getPelagem() <=9) {
//            this.adicionarValorNoContrato(50);
//            System.out.println("O servico de tosagem no valor de R$50 foi contratado com sucesso!\n");
//            setQuantidadeContratos(getQuantidadeContratos() + 1);
//        }
//    }
}
