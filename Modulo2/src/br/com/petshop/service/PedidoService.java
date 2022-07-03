package br.com.petshop.service;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Pedido;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.repository.AnimalRepository;
import br.com.petshop.repository.PedidoRepository;

public class PedidoService {
    private PedidoRepository pedidoRepository;

    public PedidoService() {
        pedidoRepository = new PedidoRepository();
    }

    public void adicionarPedido(Pedido pedido){
        try{
            Pedido pedidoAdd = pedidoRepository.adicionar(pedido);
            System.out.println("pedido adicionado: " + pedidoAdd);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
    public void remover(Integer id) {
        try {
            boolean pedidoRemovido = pedidoRepository.remover(id);
            System.out.println("removido " + pedidoRemovido);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
    public void editar (Integer id, Pedido pedido){
        try{
            boolean pedidoEditado = pedidoRepository.editar(id, pedido);
            System.out.println("endereco editado: " + pedidoEditado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listar(){
        try{
            pedidoRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public Pedido gerarPedido(Cliente cliente, Integer id) throws BancoDeDadosException {
        Pedido novoPedido = new Pedido();
        AnimalRepository repository = new AnimalRepository();
        novoPedido.setCliente(cliente);
        Animal animal = repository.getAnimalPorId(id);
        novoPedido.setAnimal(animal);
        return novoPedido;
    }
}
