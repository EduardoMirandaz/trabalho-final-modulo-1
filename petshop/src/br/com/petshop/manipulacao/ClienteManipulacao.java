package br.com.petshop.manipulacao;

import br.com.petshop.moldes.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteManipulacao {

    private List<Cliente> listaCliente;

    public ClienteManipulacao() {
        this.listaCliente = new ArrayList<>();
    }
    public void adicionarCliente(Cliente cliente){
        this.listaCliente.add(cliente);
    }
    public void removerClientePorIndice(Integer index){
        this.listaCliente.remove(index.intValue());
    }
    public void editarCliente(Integer index, Cliente cliente){
        Cliente clienteProcurado = listaCliente.get(index);
        clienteProcurado.setNome(cliente.getNome());
        clienteProcurado.setContatos(cliente.getContatos());
        clienteProcurado.setEndereco(cliente.getEndereco());
        clienteProcurado.setPets(cliente.getPets());
    }
    public void listarCliente (){
        if (listaCliente != null) {
            for (int i = 0; i < listaCliente.size(); i++) {
                System.out.println("id =" + i + " | " + listaCliente.get(i));
            }
        }
    }
}
