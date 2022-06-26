package br.com.petshop;

import br.com.petshop.manipulacao.ClienteManipulacao;
import br.com.petshop.manipulacao.PetManipulacao;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static br.com.petshop.manipulacao.UsernameValidator.*;

public class Main {


    static boolean cadastrarNovoLogin(Scanner scan, ArrayList<Login> logins){
        System.out.println("Insira o login desejado");
        String loginDesejado = scan.nextLine();
        while (!isValidLogin(loginDesejado) || loginJaExiste(logins, loginDesejado)){
            System.out.println("Login inválido ou já existente, tente outro por favor");
            loginDesejado = scan.nextLine();
        }
        System.out.println("Insira a senha desejada, apenas números");
        String senhaDesejada = scan.nextLine();
        while (!isValidSenha(senhaDesejada)){
            System.out.println("Senha inválida, insira uma senha numérica com 6 a 8 dígitos");
            senhaDesejada = scan.nextLine();
        }
        logins.add(new Login(loginDesejado, senhaDesejada));
        System.out.println("Login e senha válidos, vamos agora preencher seus dados e dos seus bixinhos\n=============================");
        return true;
    }

    private static boolean loginJaExiste(ArrayList<Login> logins, String loginDesejado) {
        return logins.stream().anyMatch(login -> login.getLogin().equals(loginDesejado));
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        PetManipulacao petManipulacao = new PetManipulacao();
        ClienteManipulacao clienteManipulacao = new ClienteManipulacao();
        ArrayList<Login> logins = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        HashMap<Login, Cliente> mapaLoginCliente = new HashMap<>();

        System.out.println("BEM-VINDO AO SISTEMA DE OPERAÇÕES DO PETSHOP");
        int opcao = -1;
        while(opcao != 0){
            System.out.println("DIGITE:");
            System.out.println("1 - caso seja um(a) novo(a) cliente");
            System.out.println("2 - caso já tenha cadastro no sistema");
            System.out.println("0 - sair");
            opcao = scan.nextInt();
            scan.nextLine();
            if(opcao > 2 || opcao < 0){
                System.out.println("\n--/ opcao invalida =( /--\n");
                continue;
            }
            if(opcao == 0){
                System.out.println("/-- Obrigado por visitar nosso PetShop! /--\n/-- Volte sempre que precisar! =) /--\n");
            }
            if(opcao == 1){
                System.out.println("/-- Cadastro /--\n");
                boolean novoLoginValido = cadastrarNovoLogin(scan, logins);
                if(novoLoginValido){
                    boolean novoClienteValido = clienteManipulacao.cadastrarNovoCliente(logins.get(logins.size()-1), clientes);

                }
            }
//            switch (opcao) {
//                case 1 -> {
//                    Cachorro cachorro = new Cachorro();
//                    System.out.println("Digite o nome do cachorro:");
//                    cachorro.setNome(scan.nextLine());
//                    System.out.println("Digite a raca do cachorro:");
//                    cachorro.setRaca(scan.nextLine());
//                    System.out.println("Informe o tipo de pelagem (1=curto | 2=medio | 3=comprido):");
//                    cachorro.setPelagem(scan.nextInt());
//                    scan.nextLine();
//                    boolean pelagemValida;
//                    switch (cachorro.getPelagem()) {
//                        case 1, 2, 3 -> {
//                            pelagemValida = true;
//                        }
//                        default -> {
//                            pelagemValida = false;
//                        }
//                    }
//                    while(!pelagemValida) {
//                        System.out.println("Tipo de pelagem invalido, informe novamente:");
//                        cachorro.setPelagem(scan.nextInt());
//                        scan.nextLine();
//                        if(cachorro.getPelagem() >= 1 && cachorro.getPelagem() <= 3) {
//                            pelagemValida = true;
//                        }
//                    }
//                    System.out.println("Informe o porte do cachorro (1=pequeno | 2=medio | 3=grande)");
//                    cachorro.setPorte(scan.nextInt());
//                    scan.nextLine();
//                    boolean porteValido;
//                    switch (cachorro.getPorte()) {
//                        case 1, 2, 3 -> {
//                            porteValido = true;
//                        }
//                        default -> {
//                            porteValido = false;
//                        }
//                    }
//                    while(!porteValido) {
//                        System.out.println("Porte invalido, informe novamente:");
//                        cachorro.setPorte(scan.nextInt());
//                        scan.nextLine();
//                        if(cachorro.getPorte() >= 1 && cachorro.getPorte() <= 3) {
//                            porteValido = true;
//                        }
//                    }
//                }
//                case 2 -> {
//                    Gato gato = new Gato();
//                    System.out.println("Digite o nome do gato:");
//                    gato.setNome(scan.nextLine());
//                    System.out.println("Digite a raca do gato:");
//                    gato.setRaca(scan.nextLine());
//                    System.out.println("Informe o tipo de pelagem (1=curto | 2=medio | 3=comprido):");
//                    gato.setPelagem(scan.nextInt());
//                    scan.nextLine();
//                    while(gato.getPelagem() > 3 && gato.getPelagem() < 1) {
//                        System.out.println("Tipo inválido, informe novamente:");
//                        gato.setPelagem(scan.nextInt());
//                        scan.nextLine();
//                    }
//                    System.out.println("Informe o porte do gato (1=pequeno | 2=medio | 3=comprido)");
//                    gato.setPorte(scan.nextInt());
//                    scan.nextLine();
//                    while (gato.getPorte() > 4 && gato.getPorte() < 1) {
//                        System.out.println("Porte invalido, informe novamente:");
//                        gato.setPelagem(scan.nextInt());
//                        scan.nextLine();
//                    }
//                }
//            }
        }
    }
}
