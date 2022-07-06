package br.com.petshop.view.manipulacao;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.model.cliente.Cliente;
import br.com.petshop.model.cliente.Pedido;
import br.com.petshop.model.pets.Animal;
import br.com.petshop.repository.AnimalRepository;
import br.com.petshop.repository.PedidoRepository;
import br.com.petshop.service.ContratoService;

import java.util.Scanner;

import static br.com.petshop.util.UsernameValidator.isValidDigit;
import static br.com.petshop.util.UsernameValidator.isValidNUM;

public class PedidoManipulacao {
    public void operacionarPedidos(Scanner scan, PedidoRepository pedidoRepository, Cliente clienteVigente){
        // CRUD pedidos
        telaInicialCrudPedidos();
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
                    System.out.println(pedidoRepository.listarPedidosPorCliente(clienteVigente));
                    System.out.println("Insira o id do pedido que deseja editar, em seguida, popule o novo pedido!!");
                    opcao = scan.nextLine();
                    while(!isValidNUM(opcao)){
                        System.out.println("\n--/ opcao invalida =( /--\n");
                        opcao = scan.nextLine();
                    }
                    Pedido pedidoAntigo = pedidoRepository.getPedidoPorId(Integer.parseInt(opcao));
                    Pedido novoPedido = popularNovoPedido(clienteVigente, pedidoAntigo.getIdAnimal());
                    novoPedido.setIdPedido(Integer.parseInt(opcao));
                    novoPedido.setCliente(clienteVigente);
                    novoPedido.setIdAnimal(pedidoAntigo.getIdAnimal());
                    AnimalRepository animalRepository = new AnimalRepository();
                    Animal animal;
                    animal = animalRepository.getAnimalPorId(pedidoAntigo.getIdAnimal(), clienteVigente.getId());
                    novoPedido.setAnimal(animal);
                    pedidoRepository.editar(Integer.parseInt(opcao), novoPedido);
                } catch (BancoDeDadosException e) {
                    throw new RuntimeException(e);
                }
            }
            case "2" -> {
                try {
                    System.out.println(pedidoRepository.listarPedidosPorCliente(clienteVigente));
                    System.out.println("Insira o id do pedido que deseja remover!");
                    opcao = scan.nextLine();
                    while(!isValidNUM(opcao)){
                        System.out.println("\n--/ opcao invalida =( /--\n");
                        opcao = scan.nextLine();
                    }
                    pedidoRepository.remover(Integer.parseInt(opcao));
                } catch (BancoDeDadosException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + opcao);
        }
    }

    private Pedido popularNovoPedido(Cliente clienteVigente, Integer idPetSelecionado) {
        PetManipulacao petManipulacao = new PetManipulacao();
        petManipulacao.telaInicialContratarServicosPet();
        Scanner scan = new Scanner(System.in);
        Pedido novoPedido = new Pedido();
        String opcao = scan.nextLine();
        while(!isValidDigit(opcao)) {
            System.out.println("\n--/ opcao invalida =( /--\n");
            opcao = scan.nextLine();
        }
        switch (opcao){
            case "1" -> {
                System.out.println("Adicionando banho... ");
                ContratoService contratoService = new ContratoService();
                try {
                    novoPedido = contratoService.contratarBanho(clienteVigente, idPetSelecionado, true);
                } catch (BancoDeDadosException e) {
                    throw new RuntimeException(e);
                }
            }
            case "2" -> {
                System.out.println("Adicionando tosa... ");
                ContratoService contratoService = new ContratoService();
                try {
                    novoPedido = contratoService.contratarTosa(clienteVigente, idPetSelecionado, true);
                } catch (BancoDeDadosException e){
                    throw new RuntimeException(e);
                }
            }
            case "3" -> {
                System.out.println("Adicionando corte de unha... ");
                ContratoService contratoService = new ContratoService();
                try {
                    novoPedido = contratoService.contratarCorteDeUnha(clienteVigente, idPetSelecionado, true);
                } catch (BancoDeDadosException e){
                    throw new RuntimeException(e);
                }
            }
            case "4" -> {
                System.out.println("Adicionando adestramento... ");
                ContratoService contratoService = new ContratoService();
                try {
                    novoPedido = contratoService.contratarAdestramento(clienteVigente, idPetSelecionado, true);
                } catch (BancoDeDadosException e){
                    throw new RuntimeException(e);
                }
            }
            // todo fazer operacao que mostrará valor total com select pelo id_cliente
            case "5" -> {
                System.out.println("Obrigado por contratar nosso servicos, seu pet sera bem cuidado =)!!" +
                        "");
            }
        }
        return novoPedido;
    }

    private void telaInicialCrudPedidos() {
        System.out.println("Bem vindo ao menu de alteracao de pedidos!");
        System.out.println("Insira:\n1- Para editar um pedido.\n2- Para remover um pedido\n0- Para sair.");
    }
}
