package br.com.petshop.view.manipulacao;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.*;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.repository.AnimalRepository;
import br.com.petshop.repository.ClienteRepository;
import br.com.petshop.service.ClienteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static br.com.petshop.util.UsernameValidator.*;

public class ClienteManipulacao {
    static int ID = 0;
    public String inserirNome(String nome, Scanner scan){
        while(!isValidName(nome)){
            System.out.println("O nome não pode conter símbolos ou números, insira novamente");
            nome = scan.nextLine();
        }
        return nome;
    }


    public boolean cadastrarNovoCliente(Login login, PetManipulacao petManipulacao, HashMap<String,Cliente> mapa) throws BancoDeDadosException {

        Cliente cliente = new Cliente();
        Scanner scan = new Scanner(System.in);

        // Inserir nome:
        System.out.println("Insira seu nome completo:");
        String nomeAux = inserirNome(scan.nextLine(), scan);
        cliente.setNome(nomeAux);

        // Inserir quantidade de contratos
        cliente.setQuantidadeDePedidos(0);

        mapa.put(login.getLogin(), cliente);
        this.adicionarCliente(cliente);
        ClienteRepository clienteRepository = new ClienteRepository();
        cliente = clienteRepository.adicionar(cliente);
        System.out.println("Faca login para continuar as operacoes");
        return cliente != null;

    }
    private List<Cliente> listaCliente;

    public ClienteManipulacao() {
        this.listaCliente = new ArrayList<>();
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public void adicionarCliente(Cliente cliente){
        this.listaCliente.add(cliente);
    }

    public void operacionarClientes(Scanner scan, ClienteRepository clienteRepository, Cliente clienteVigente) {
        telaInicialCrudCliente();
        String opcao = scan.nextLine();
        while(!isValidDigit(opcao)){
            System.out.println("\n--/ opcao invalida =( /--\n");
            opcao = scan.nextLine();
        }
        switch (opcao) {
            // VOLTAR AO MENU ANTERIOR
            case "0" -> {
                System.out.println("Voltando ao menu inicial");
            }
            case "1" -> {
                try {
                    Cliente cliente = clienteRepository.getClientePeloId(clienteVigente.getId());
                    Cliente novoCliente = new Cliente();
                    String novoNome = scan.nextLine();
                    while(!isValidName(opcao)){
                        System.out.println("\n--/ opcao invalida =( /--\n");
                        novoNome = scan.nextLine();
                    }
                    novoCliente.setNome(novoNome);
                    novoCliente.setQuantidadeDePedidos(cliente.getQuantidadeDePedidos());
                    novoCliente.setId(cliente.getId());
                    clienteRepository.editar(cliente.getId(), novoCliente);
                } catch (BancoDeDadosException e) {
                    throw new RuntimeException(e);
                }
            }
            case "2" -> {
                try {
                    clienteRepository.remover(clienteVigente.getId());
                } catch (BancoDeDadosException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + opcao);
        }

    }

    private void telaInicialCrudCliente() {
        System.out.println("Bem vindo ao menu de alteracao de pedidos!");
        System.out.println("Insira:\n1- Para editar seu cadastro.\n2- Para remover seu cadastro\n0- Para sair.");
    }
}
