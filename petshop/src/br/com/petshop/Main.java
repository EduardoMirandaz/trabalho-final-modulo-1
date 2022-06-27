package br.com.petshop;

import br.com.petshop.manipulacao.ClienteManipulacao;
import br.com.petshop.manipulacao.LoginManipulacao;
import br.com.petshop.manipulacao.PetManipulacao;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Login;
import br.com.petshop.moldes.pets.Animal;

import java.util.*;

import static br.com.petshop.manipulacao.UsernameValidator.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LoginManipulacao loginManipulacao = new LoginManipulacao();
        PetManipulacao petManipulacao = new PetManipulacao();
        ClienteManipulacao clienteManipulacao = new ClienteManipulacao();
        ArrayList<Login> logins = new ArrayList<>();
        HashMap<String,Cliente>  mapaDeAssociacaoLoginCliente = new HashMap<>();

        System.out.println("BEM-VINDO AO SISTEMA DE OPERAÇÕES DO PETSHOP");
        String opcao = "0";
        while(!opcao.equals("-1")){
            telaInicialLoginCadastro();
            opcao = scan.nextLine();
            while(!isValidDigit(opcao)){
                System.out.println("\n--/ opcao invalida =( /--\n");
                opcao = scan.nextLine();
            }
            if(opcao.equals("0")){
                System.out.println("/-- Obrigado por visitar nosso PetShop! /--\n/-- Volte sempre que precisar! =) /--\n");
                break;
            }
            if(opcao.equals("1")) {
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
            if(opcao.equals("2")){
                Login loginVigente = loginManipulacao.verificarLogin(scan, logins);
                while(loginVigente != null){
                    Cliente clienteVigente = null;
                    clienteVigente = mapaDeAssociacaoLoginCliente.get(loginVigente.getLogin());
                    if(clienteVigente == null){
                        System.out.println("Não foi possível encontrar o cliente");
                        break;
                    }
                    loginManipulacao.telaInicialLogin();
                    opcao = scan.nextLine();
                    while(!isValidDigit(opcao)){
                        System.out.println("\n--/ opcao invalida =( /--\n");
                        opcao = scan.nextLine();
                    }
                    switch (opcao) {
                        case "0" -> {
                            loginVigente = null;
                            clienteVigente = null;
                            continue;
                        }
                        case "1" -> {
                                petManipulacao.adicionarNovoPet(scan, clienteVigente);
                                System.out.println("Lista de pets atualizada!");
                        }
                        case "2" -> {
                            System.out.println("Informe o id do pet que deseja editar");
                            petManipulacao.listarAnimais(clienteVigente);
                            opcao = scan.nextLine();
                            while(!isValidNUM(opcao)){
                                System.out.println("Infelizmente, esse ID não é válido");
                                opcao = scan.nextLine();
                            }
                            // Verifico se é um pet possivel na lista
                            if(!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size()-1)){
                                break;
                            }
                            System.out.println("Insira agora as informacoes corrigidas");
                            Animal petEditado = petManipulacao.adicionarNovoPet(scan, clienteVigente);
                            petManipulacao.editarAnimal(clienteVigente, petEditado, Integer.parseInt(opcao));
                            petManipulacao.removerPetPorIndice(Integer.parseInt(opcao), clienteVigente);
                        }
                        case "3" -> {
                            System.out.println("Qual pet voce deseja excluir?");
                            petManipulacao.listarAnimais(clienteVigente);
                            opcao = scan.nextLine();
                            while(!isValidNUM(opcao)){
                                System.out.println("Infelizmente, o nome do seu animal nao pode ter digitos nem simbolos");
                                opcao = scan.nextLine();
                            }
                            if(Integer.parseInt(opcao) > clienteVigente.getPets().size()-1 ||
                                    Integer.parseInt(opcao) < 0){
                                System.out.println("Index de pet inválido");
                                break;
                            }
                            petManipulacao.removerPetPorIndice(Integer.parseInt(opcao), clienteVigente);
                        }
                        case "4" -> {
                            petManipulacao.listarAnimais(clienteVigente);
                        }
                        case "5" -> {
                            System.out.println("""
                                    Quais contratos voce quer contratar?
                                    1- Banho
                                    2- Tosa
                                    3- Corte de unha
                                    4- Adestramento
                                    5- Meu valor atual
                                    6- Confirmar""");
                            opcao = scan.nextLine();
                            while(!isValidDigit(opcao)) {
                                System.out.println("\n--/ opcao invalida =( /--\n");
                                opcao = scan.nextLine();
                            }

                            switch (opcao){
                                case "1" -> {
                                    System.out.println("Selecione o index do pet que deseja adicionar o banho: ");
                                    petManipulacao.listarAnimais(clienteVigente);
                                    opcao = scan.nextLine();
                                    while (!isValidDigit(opcao)) {
                                        System.out.println("\n--/ opcao invalida =( /--\n");
                                        opcao = scan.nextLine();
                                    }
                                    if(!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size()-1)){
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
                                    if(!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size()-1)){
                                        break;
                                    }
                                    int opcaoConvertida = Integer.parseInt(opcao);
                                    petManipulacao.adicionarContratoDeTosa(clienteVigente, opcaoConvertida);
                                }
                                case "3" -> {
                                    System.out.println("Selecione o index do pet que deseja adicionar o corte de unha: ");
                                    petManipulacao.listarAnimais(clienteVigente);
                                    opcao = scan.nextLine();
                                    if(!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size()-1)){
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
                                    if(!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size()-1)){
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
                                    if(!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size()-1)){
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
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }
        scan.close();
    }

    private static boolean indiceEhValido(int opcao, int ultimoIndice) {
        if(opcao > ultimoIndice ||
                opcao < 0){
            System.out.println("Index de pet inválido");
            return false;
        }
        return true;
    }

    private static void telaInicialLoginCadastro() {
        System.out.println("DIGITE:");
        System.out.println("1 - caso seja um(a) novo(a) cliente");
        System.out.println("2 - caso já tenha cadastro no sistema");
        System.out.println("0 - sair");
    }

}
