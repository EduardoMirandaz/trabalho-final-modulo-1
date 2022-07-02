package br.com.petshop.manipulacao;

import br.com.petshop.moldes.cliente.Cliente;

import java.util.Scanner;

import static br.com.petshop.Main.indiceEhValido;
import static br.com.petshop.manipulacao.UsernameValidator.isValidDigit;

public class ContratoManipulacao {
    public void menuContratos() {
        System.out.println("""
                Quais contratos voce quer contratar?
                1- Banho
                2- Tosa
                3- Corte de unha
                4- Adestramento
                5- Meu valor atual
                6- Confirmar""");
    }
    public void realizarContratos(String opcao, PetManipulacao petManipulacao, Cliente clienteVigente, Scanner scan){
        switch(opcao)
        {
            case "1" -> {
                System.out.println("Selecione o index do pet que deseja adicionar o banho: ");
                petManipulacao.listarAnimais(clienteVigente);
                opcao = scan.nextLine();
                while (!isValidDigit(opcao)) {
                    System.out.println("\n--/ opcao invalida =( /--\n");
                    opcao = scan.nextLine();
                }
                if (!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size() - 1)) {
                    break;
                }
                int opcaoConvertida = Integer.parseInt(opcao);
                petManipulacao.adicionarContratoDeBanho(clienteVigente, opcaoConvertida);
            }
            case "2" -> {
                System.out.println("Selecione o index do pet que deseja adicionar a tosa: ");
                petManipulacao.listarAnimais(clienteVigente);
                opcao = scan.nextLine();
                while (!isValidDigit(opcao)) {
                    System.out.println("\n--/ opcao invalida =( /--\n");
                    opcao = scan.nextLine();
                }
                if (!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size() - 1)) {
                    break;
                }
                int opcaoConvertida = Integer.parseInt(opcao);
                petManipulacao.adicionarContratoDeTosa(clienteVigente, opcaoConvertida);
            }
            case "3" -> {
                System.out.println("Selecione o index do pet que deseja adicionar o corte de unha: ");
                petManipulacao.listarAnimais(clienteVigente);
                opcao = scan.nextLine();
                if (!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size() - 1)) {
                    break;
                }
                while (!isValidDigit(opcao)) {
                    System.out.println("\n--/ opcao invalida =( /--\n");
                    opcao = scan.nextLine();
                }
                int opcaoConvertida = Integer.parseInt(opcao);
                petManipulacao.adicionarContratoDeCorteDeUnha(clienteVigente, opcaoConvertida);
            }
            case "4" -> {
                System.out.println("Selecione o index do pet que deseja adicionar o adestramento: ");
                petManipulacao.listarAnimais(clienteVigente);
                opcao = scan.nextLine();
                if (!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size() - 1)) {
                    break;
                }
                while (!isValidDigit(opcao)) {
                    System.out.println("\n--/ opcao invalida =( /--\n");
                    opcao = scan.nextLine();
                }
                int opcaoConvertida = Integer.parseInt(opcao);
                petManipulacao.adicionarContratoDeAdestramento(clienteVigente, opcaoConvertida);
            }
            case "5" -> {
                System.out.println("Selecione o index do pet para ver o valor: ");
                opcao = scan.nextLine();
                if (!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size() - 1)) {
                    break;
                }

                while (!isValidDigit(opcao)) {
                    System.out.println("\n--/ opcao invalida =( /--\n");
                    opcao = scan.nextLine();
                }
                int opcaoConvertida = Integer.parseInt(opcao);
                petManipulacao.valorContrato(clienteVigente, opcaoConvertida);
            }
            case "6" -> {
                System.out.println("Obrigado por contratar nosso servicos, seu pet sera bem cuidado =)!!" +
                        "");
            }
        }
    }
}
