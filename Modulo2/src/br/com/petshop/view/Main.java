package br.com.petshop.view;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.repository.ClienteRepository;
import br.com.petshop.repository.PedidoRepository;
import br.com.petshop.view.manipulacao.ClienteManipulacao;
import br.com.petshop.view.manipulacao.LoginManipulacao;
import br.com.petshop.view.manipulacao.PedidoManipulacao;
import br.com.petshop.view.manipulacao.PetManipulacao;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Login;

import java.util.*;

import static br.com.petshop.util.UsernameValidator.*;

public class Main {
    public static void main(String[] args) throws BancoDeDadosException {
        Scanner scan = new Scanner(System.in);

        /* manipulacao */
        LoginManipulacao loginManipulacao = new LoginManipulacao();
        PetManipulacao petManipulacao = new PetManipulacao();
        ClienteManipulacao clienteManipulacao = new ClienteManipulacao();

        /* estruturas de dados */
        ArrayList<Login> logins = new ArrayList<>();
        HashMap<String,Cliente>  mapaDeAssociacaoLoginCliente = new HashMap<>();

        /* inicio do sistema */
        System.out.println("BEM-VINDO AO SISTEMA DE OPERAÇÕES DO PETSHOP");
        String opcao = "0";
        while(!opcao.equals("-1")){
            LoginManipulacao.telaInicialLoginCadastro();
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
                        case "1" -> {
                            clienteManipulacao.operacionarClientes(scan, new ClienteRepository(), clienteVigente);
                            // preciso deslogar pois a pessoa pode alterar/deletar a conta nesse módulo
                            loginVigente = null;
                        }
                        case "2" -> {
                            petManipulacao.operacionarPets(scan, petManipulacao, clienteVigente);
                        }
                        case "3" -> {
                            PedidoManipulacao pedidoManipulacao = new PedidoManipulacao();
                            pedidoManipulacao.operacionarPedidos(scan, new PedidoRepository(), clienteVigente);
                        }
                    }
                }
            }
        }
        scan.close();
    }

}
