package br.com.petshop;

import br.com.petshop.manipulacao.PetManipulacao;
import br.com.petshop.moldes.pets.Cachorro;
import br.com.petshop.moldes.pets.Gato;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);



        PetManipulacao petManipulacao = new PetManipulacao();

        int opcao = 0;
        while(opcao != 9){
            System.out.println("1 - adicionar cachorro");
            System.out.println("2 - adicionar gato");
            System.out.println("3 - listar seus pets");
            System.out.println("4 - excluir um pet");
            System.out.println(" 9 - sair");
            opcao = scan.nextInt();
            scan.nextLine();
            switch (opcao) {
                case 1 -> {
                    Cachorro cachorro = new Cachorro();
                    System.out.println("Digite o nome do cachorro:");
                    cachorro.setNome(scan.nextLine());
                    System.out.println("Digite a raca do cachorro:");
                    cachorro.setRaca(scan.nextLine());
                    System.out.println("Informe o tipo de pelagem (1=curto | 2=medio | 3=comprido):");
                    cachorro.setPelagem(scan.nextInt());
                    scan.nextLine();
                    boolean pelagemValida;
                    switch (cachorro.getPelagem()) {
                        case 1, 2, 3 -> {
                            pelagemValida = true;
                        }
                        default -> {
                            pelagemValida = false;
                        }
                    }
                    while(!pelagemValida) {
                        System.out.println("Tipo de pelagem invalido, informe novamente:");
                        cachorro.setPelagem(scan.nextInt());
                        scan.nextLine();
                        if(cachorro.getPelagem() >= 1 && cachorro.getPelagem() <= 3) {
                            pelagemValida = true;
                        }
                    }
                    System.out.println("Informe o porte do cachorro (1=pequeno | 2=medio | 3=grande)");
                    cachorro.setPorte(scan.nextInt());
                    scan.nextLine();
                    boolean porteValido;
                    switch (cachorro.getPorte()) {
                        case 1, 2, 3 -> {
                            porteValido = true;
                        }
                        default -> {
                            porteValido = false;
                        }
                    }
                    while(!porteValido) {
                        System.out.println("Porte invalido, informe novamente:");
                        cachorro.setPorte(scan.nextInt());
                        scan.nextLine();
                        if(cachorro.getPorte() >= 1 && cachorro.getPorte() <= 3) {
                            porteValido = true;
                        }
                    }
                }
                case 2 -> {
                    Gato gato = new Gato();
                    System.out.println("Digite o nome do gato:");
                    gato.setNome(scan.nextLine());
                    System.out.println("Digite a raca do gato:");
                    gato.setRaca(scan.nextLine());
                    System.out.println("Informe o tipo de pelagem (1=curto | 2=medio | 3=comprido):");
                    gato.setPelagem(scan.nextInt());
                    scan.nextLine();
                    while(gato.getPelagem() > 3 && gato.getPelagem() < 1) {
                        System.out.println("Tipo inválido, informe novamente:");
                        gato.setPelagem(scan.nextInt());
                        scan.nextLine();
                    }
                    System.out.println("Informe o porte do gato (1=pequeno | 2=medio | 3=comprido)");
                    gato.setPorte(scan.nextInt());
                    scan.nextLine();
                    while (gato.getPorte() > 4 && gato.getPorte() < 1) {
                        System.out.println("Porte invalido, informe novamente:");
                        gato.setPelagem(scan.nextInt());
                        scan.nextLine();
                    }
                }
            }
        }
    }
}
