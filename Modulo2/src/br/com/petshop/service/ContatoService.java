package br.com.petshop.service;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Contato;
import br.com.petshop.repository.ContatoRepository;


public class ContatoService {
    private ContatoRepository contatoRepository;

    public ContatoService() {
        contatoRepository = new ContatoRepository();
    }

    public void adicionarContato(Contato contato){
        try{
            Contato contatoAdd = contatoRepository.adicionar(contato);
            System.out.println("contato adicionado: " + contatoAdd);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
    public void remover(Integer id) {
        try {
            boolean contatoRemovido = contatoRepository.remover(id);
            System.out.println("contato removido: " + contatoRemovido);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
    public void editar (Integer id, Contato contato){
        try{
            boolean contatoEditado = contatoRepository.editar(id, contato);
            System.out.println("contato editado: " + contatoEditado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listar(){
        try{
            contatoRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
}

