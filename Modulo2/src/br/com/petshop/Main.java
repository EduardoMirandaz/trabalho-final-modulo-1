package br.com.petshop;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.manipulacao.ClienteManipulacao;
import br.com.petshop.manipulacao.LoginManipulacao;
import br.com.petshop.manipulacao.PetManipulacao;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Login;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.moldes.pets.Cachorro;
import br.com.petshop.moldes.pets.EnumTipoAnimal;
import br.com.petshop.repository.AnimalRepository;

import java.util.*;

import static br.com.petshop.manipulacao.UsernameValidator.*;

public class Main {
    public static void main(String[] args) throws BancoDeDadosException {
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
                // Verifico se a pessoa está com um login válido
                Login loginVigente = loginManipulacao.verificarLogin(scan, logins);
                while(loginVigente != null) {
                    Cliente clienteVigente = null;
                    clienteVigente = mapaDeAssociacaoLoginCliente.get(loginVigente.getLogin());
                    if (clienteVigente == null) {
                        System.out.println("Não foi possível encontrar o cliente");
                        break;
                    }

                    // Vejo se a pessoa quer fazer operacoes de conta ou operacoes sobre o pet.
                    loginManipulacao.telaInicialPosLogin();
                    opcao = scan.nextLine();
                    while (!isValidDigit(opcao)) {
                        System.out.println("\n--/ opcao invalida =( /--\n");
                        opcao = scan.nextLine();
                    }
                    switch (opcao) {
                        case "0" -> {
                            loginVigente = null;
                            clienteVigente = null;
                        }
                        case "2" -> {
                            petManipulacao.operacionarPets(scan, petManipulacao, clienteVigente);
                        }
                    }
                }
            }
        }
        scan.close();
    }

    public static boolean indiceEhValido(int opcao, int ultimoIndice) {
        if(opcao > ultimoIndice ||
                opcao < 0){
            System.out.println("Index de pet inválido");
            return false;
        }
        return true;
    }

    public void telaInicialManipularCadastros() {

    }

    public void telaInicialManipularPedidos() {

    }
    private static void telaInicialLoginCadastro() {
        System.out.println("DIGITE:");
        System.out.println("1 - caso seja um(a) novo(a) cliente");
        System.out.println("2 - caso já tenha cadastro no sistema");
        System.out.println("0 - sair");
    }

}
