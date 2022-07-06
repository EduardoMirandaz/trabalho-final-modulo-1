package br.com.petshop.service;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.model.cliente.Endereco;
import br.com.petshop.repository.EnderecoRepository;

public class EnderecoService {
    private EnderecoRepository enderecoRepository;

    public EnderecoService() {
        enderecoRepository = new EnderecoRepository();
    }

    public void adicionarEndereco(Endereco endereco){
        try{
            Endereco enderecoAdd = enderecoRepository.adicionar(endereco);
            System.out.println("endereco adicionado: " + enderecoAdd);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
    public void remover(Integer id) {
        try {
            boolean enderecoRemovido = enderecoRepository.remover(id);
            System.out.println("removido " + enderecoRemovido);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
    public void editar (Integer id, Endereco endereco){
        try{
            boolean enderecoEditado = enderecoRepository.editar(id, endereco);
            System.out.println("endereco editado: " + enderecoEditado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listar(){
        try{
            enderecoRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
}
