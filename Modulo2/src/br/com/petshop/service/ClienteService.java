package br.com.petshop.service;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.model.cliente.Cliente;
import br.com.petshop.repository.ClienteRepository;


public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService() {
        clienteRepository = new ClienteRepository();
    }

    public void adicionarCliente(Cliente cliente){
        try{
            Cliente clienteAdd = clienteRepository.adicionar(cliente);
            System.out.println("cliente adicionado: " + clienteAdd);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
    public void remover(Integer id) {
        try {
            boolean clienteRemovido = clienteRepository.remover(id);
            System.out.println("cliente removido: " + clienteRemovido);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
    public void editar (Integer id, Cliente cliente){
        try{
            boolean clienteEditado = clienteRepository.editar(id, cliente);
            System.out.println("cliente editado: " + clienteEditado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listar(){
        try{
            clienteRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
}
