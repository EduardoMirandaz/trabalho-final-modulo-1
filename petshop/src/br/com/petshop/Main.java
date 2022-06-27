package br.com.petshop;

import br.com.petshop.manipulacao.ClienteManipulacao;
import br.com.petshop.manipulacao.LoginManipulacao;
import br.com.petshop.manipulacao.PetManipulacao;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Login;
import br.com.petshop.moldes.pets.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static br.com.petshop.manipulacao.UsernameValidator.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LoginManipulacao loginManipulacao = new LoginManipulacao();
        PetManipulacao petManipulacao = new PetManipulacao();
        ClienteManipulacao clienteManipulacao = new ClienteManipulacao();
        ArrayList<Login> logins = new ArrayList<>();
        HashMap<Login,Cliente>  mapaDeAssociacaoLoginCliente = new HashMap<>();
        Cliente clienteVigente = new Cliente();

        System.out.println("BEM-VINDO AO SISTEMA DE OPERAÇÕES DO PETSHOP");
        int opcao = -1;
        while(opcao != 0){
            telaInicialLoginCadastro();
            opcao = scan.nextInt();
            scan.nextLine();
            if(opcao > 2 || opcao < 0){
                System.out.println("\n--/ opcao invalida =( /--\n");
                continue;
            }
            if(opcao == 0){
                System.out.println("/-- Obrigado por visitar nosso PetShop! /--\n/-- Volte sempre que precisar! =) /--\n");
                break;
            }
            if(opcao == 1) {
                System.out.println("/-- Cadastro /--\n");
                boolean novoLoginEhValido = loginManipulacao.cadastrarNovoLogin(scan, logins);
                if (novoLoginEhValido) {
                    boolean novoClienteEhValido = clienteManipulacao.cadastrarNovoCliente(logins.get(logins.size() - 1), petManipulacao, mapaDeAssociacaoLoginCliente);
                    if (novoClienteEhValido) {
                        continue;
                    }
                    System.out.println("Houve algum erro no cadastro =(");
                }
            }
            if(opcao == 2){
                Login loginVigente = loginManipulacao.verificarLogin(scan, logins);
                while(loginVigente != null){
                    for (Login login : mapaDeAssociacaoLoginCliente.keySet()) {
                        //Capturamos o valor a partir da chave
                        clienteVigente = mapaDeAssociacaoLoginCliente.get(login);
                    }
                    loginManipulacao.telaInicialLogin();
                    opcao = scan.nextInt();
                    scan.nextLine();
                    switch (opcao) {
                        case -1 -> {
                            loginVigente = null;
                            continue;
                        }
                        case 1 -> {
                            petManipulacao.adicionarNovoPet(scan, clienteVigente.getPets());
                            System.out.println("Lista de pets atualizada!");
                            System.out.println(clienteVigente.getPets());
                        }
                        case 2 -> {
                            System.out.println("Informe o id do pet que deseja editar");
                            petManipulacao.listarAnimais(clienteVigente);
                            int index = scan.nextInt();
                            scan.nextLine();
                            Animal petEditado = petManipulacao.adicionarNovoPet(scan, clienteVigente.getPets());
                            petManipulacao.editarAnimal(clienteVigente, petEditado, index);
                            petManipulacao.removerPetPorIndice(index, clienteVigente);
                        }
                        case 3 -> {

                        }
                        case 4 -> {
                            petManipulacao.listarAnimais(clienteVigente);
                        }
                    }
                    loginManipulacao.selecionarContratosDeServico();
                }
            }
        }
        scan.close();
    }

    private static void telaInicialLoginCadastro() {
        System.out.println("DIGITE:");
        System.out.println("1 - caso seja um(a) novo(a) cliente");
        System.out.println("2 - caso já tenha cadastro no sistema");
        System.out.println("0 - sair");
    }

}
