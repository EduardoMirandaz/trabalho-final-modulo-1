package br.com.petshop.service;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Pedido;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.repository.AnimalRepository;

import static br.com.petshop.moldes.pets.EnumTipoAnimal.CACHORRO;
import static br.com.petshop.moldes.pets.EnumTipoAnimal.GATO;

public class ContratosServices {

    public void contratarBanho(Cliente cliente, Integer id) throws BancoDeDadosException {
        Pedido novoPedido = new Pedido();
        AnimalRepository repository = new AnimalRepository();
        novoPedido.setCliente(cliente);
        Animal animal = repository.getAnimalPorId(id);
        novoPedido.setAnimal(animal);

        if(animal.getTipoAnimal().getTipo().equals(CACHORRO)) {
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
        if(animal.getTipoAnimal().getTipo().equals(GATO)) {
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
        PedidoService service = new PedidoService();
        service.adicionarPedido(novoPedido);
    }


    public void contratarTosa(Cliente cliente, Integer id) throws BancoDeDadosException {
        PedidoService service = new PedidoService();
        Pedido novoPedido = service.gerarPedido(cliente, id);

        AnimalRepository repository = new AnimalRepository();
        Animal animal = repository.getAnimalPorId(id);

        if(animal.getTipoAnimal().getTipo().equals(CACHORRO)) {
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
        if(animal.getTipoAnimal().getTipo().equals(GATO)) {
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
        service.adicionarPedido(novoPedido);
    }
    public void contratarCorteDeUnha(Cliente cliente, Integer id) throws BancoDeDadosException {
        PedidoService service = new PedidoService();
        Pedido novoPedido = service.gerarPedido(cliente, id);
        novoPedido.setDescricao("Corte de unha");
        novoPedido.setValor(20.0);
        service.adicionarPedido(novoPedido);
    }
    public void contratarAdestramento(Cliente cliente, Integer id) throws BancoDeDadosException {
        PedidoService service = new PedidoService();
        Pedido novoPedido = service.gerarPedido(cliente, id);
        novoPedido.setDescricao("Adestramento");
        novoPedido.setValor(200.0);
        service.adicionarPedido(novoPedido);
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
