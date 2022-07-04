package br.com.petshop.service;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Pedido;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.repository.AnimalRepository;

import static br.com.petshop.moldes.pets.EnumTipoAnimal.CACHORRO;
import static br.com.petshop.moldes.pets.EnumTipoAnimal.GATO;

public class ContratoService {
    private AnimalService animalService;
    private AnimalRepository animalRepository;
    private PedidoService pedidoService;
    public ContratoService() {
        animalService = new AnimalService();
        animalRepository = new AnimalRepository();
        pedidoService = new PedidoService();
    }

    public void contratarBanho(Cliente cliente, Integer idAnimal) throws BancoDeDadosException {
        Pedido novoPedido = new Pedido();
        novoPedido.setCliente(cliente);
        Animal animal = animalRepository.getAnimalPorId(idAnimal, cliente.getId());

        if(animal == null) {
            System.err.println("O animal não existe ou não é seu!");
            return;
        }

        novoPedido.setAnimal(animal);

        if(animal.getTipoAnimal() == CACHORRO) {
            switch (verificarPorte(animal)) {
                case 1-> {
                    novoPedido.setValor(50.0);
                }
                case 2 -> {
                    novoPedido.setValor(75.0);
                }
                case 3 -> {
                    novoPedido.setValor(100.0);
                }
                default -> System.out.println("Desculpe, ocorreu um erro");
            }
        }
        else if(animal.getTipoAnimal() == GATO) {
            switch (verificarPorte(animal)) {
                case 1 -> {
                    novoPedido.setValor(25.0);
                }
                case 2 -> {
                    novoPedido.setValor(35.0);
                }
                case 3 -> {
                    novoPedido.setValor(50.0);
                }
                default -> System.out.println("Desculpe, ocorreu um erro");
            }
        }
        novoPedido.setDescricao("Banho");
        pedidoService.adicionarPedido(novoPedido);
    }


    public void contratarTosa(Cliente cliente, Integer idAnimal) throws BancoDeDadosException {
        Pedido novoPedido = new Pedido();
        novoPedido.setCliente(cliente);
        Animal animal = animalRepository.getAnimalPorId(idAnimal, cliente.getId());
        animal.setCliente(cliente);
        novoPedido.setAnimal(animal);
        if(animal == null) {
            System.err.println("O animal não existe ou não é seu!");
            return;
        }

        if(animal.getTipoAnimal() == CACHORRO) {
            switch (verificarPelagem(animal)) {
                case 1-> {
                    novoPedido.setValor(45.0);
                }
                case 2 -> {
                    novoPedido.setValor(70.0);
                }
                case 3 -> {
                    novoPedido.setValor(120.0);
                }
                default -> System.out.println("Desculpe, ocorreu um erro");
            }
        }
        else if(animal.getTipoAnimal() == GATO) {
            switch (verificarPelagem(animal)) {
                case 1 -> {
                    novoPedido.setValor(20.0);
                }
                case 2 -> {
                    novoPedido.setValor(35.0);
                }
                case 3 -> {
                    novoPedido.setValor(50.0);
                }
                default -> System.out.println("Desculpe, ocorreu um erro");
            }
        }
        novoPedido.setDescricao("Tosa");
        pedidoService.adicionarPedido(novoPedido);
    }
    public void contratarCorteDeUnha(Cliente cliente, Integer idAnimal) throws BancoDeDadosException {

        Pedido novoPedido = new Pedido();
        novoPedido.setCliente(cliente);
        Animal animal = animalRepository.getAnimalPorId(idAnimal, cliente.getId());
        if(novoPedido == null) {
            return;
        }
        novoPedido.setDescricao("Corte de unha");
        novoPedido.setValor(20.0);
        pedidoService.adicionarPedido(novoPedido);
    }
    public void contratarAdestramento(Cliente cliente, Integer idAnimal) throws BancoDeDadosException {
        Pedido novoPedido = new Pedido();
        novoPedido.setCliente(cliente);
        Animal animal = animalRepository.getAnimalPorId(idAnimal, cliente.getId());
        if(novoPedido == null) {
            return;
        }
        novoPedido.setDescricao("Adestramento");
        novoPedido.setValor(200.0);
        pedidoService.adicionarPedido(novoPedido);
    }


    public Integer verificarPorte(Animal animal) {
        if(animal.getPorte() >= 0 && animal.getPorte() <= 4) {
            return 1;
        }
        if(animal.getPorte() > 4 && animal.getPorte() <= 7) {
            return 2;
        }
        if(animal.getPorte() > 7 && animal.getPorte() <= 9) {
            return 3;
        }
        return 0;
    }

    public Integer verificarPelagem(Animal animal) {
        if(animal.getPelagem() >= 0 && animal.getPelagem() <=4) {
            return 1;
        }
        if(animal.getPelagem() > 4 && animal.getPelagem() <= 7) {
            return 2;
        }
        if(animal.getPelagem() > 7 && animal.getPelagem() <= 9){
            return 3;
        }
        return 0;
    }
}
