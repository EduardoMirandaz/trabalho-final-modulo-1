package br.com.petshop.manipulacao;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Contato;
import br.com.petshop.moldes.cliente.Endereco;
import br.com.petshop.moldes.cliente.Login;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.moldes.pets.Cachorro;
import br.com.petshop.moldes.pets.Gato;
import br.com.petshop.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static br.com.petshop.manipulacao.UsernameValidator.*;

public class ClienteManipulacao {
    static int ID = 0;
    public String inserirNome(String nome, Scanner scan){
        while(!isValidName(nome)){
            System.out.println("O nome não pode conter símbolos ou números, insira novamente");
            nome = scan.nextLine();
        }
        return nome;
    }


    public ArrayList<Contato> inserirContatos(Scanner scan){
        Contato telefonePrincipal = new Contato();
        //  ============ TELEFONEMOVEL ============
        System.out.println("Insira seu telefone móvel para contato:\nUtilize o formato 00 90000-0000");
        String stringAux = scan.nextLine();
        while(!isValidFONENUMBER(stringAux)){
            System.out.println("Utilize o formato 00 00000-0000");
            stringAux = scan.nextLine();
            }
        telefonePrincipal.setTelefone(stringAux);
        //  ============ DESCRICAO TELEFONEMOVEL ============
        System.out.println("Insira a descricao do contato");
        stringAux = scan.nextLine();
        while(!isValidName(stringAux)){
            System.out.println("A descricao não pode conter símbolos ou números");
            stringAux = scan.nextLine();
            }
        telefonePrincipal.setDescricao(stringAux);

        Contato telefoneReserva = new Contato();
        //  ============ TELEFONEMOVEL ============
        System.out.println("Insira seu telefone reserva para contato:\nUtilize o formato 00 90000-0000");
        stringAux = scan.nextLine();
        while(!isValidFONENUMBER(stringAux)){
            System.out.println("Utilize o formato 00 00000-0000");
            stringAux = scan.nextLine();
            }
        telefoneReserva.setTelefone(stringAux);
        //  ============ DESCRICAO TELEFONEMOVEL ============
        System.out.println("Insira a descricao do contato");
        stringAux = scan.nextLine();
        while(!isValidName(stringAux)){
            System.out.println("A descricao não pode conter símbolos ou números");
            stringAux = scan.nextLine();
            }
        telefoneReserva.setDescricao(stringAux);

        return new ArrayList<Contato>(List.of(telefonePrincipal, telefoneReserva));
    }

    public Endereco inserirEndereco(Scanner scan){

        Endereco endereco = new Endereco();
        //  ============ CEP ============
        System.out.println("Insira seu CEP no formato 00000-000:");
        String stringAux = scan.nextLine();
        while(!isValidCEP(stringAux)){
            System.out.println("O CEP deve estar no formato 00000-000");
            stringAux = scan.nextLine();
            }
        endereco.setCep(stringAux);

        //  ============ LOGRADOURO ============
        System.out.println("Insira seu logradouro:");
        stringAux = scan.nextLine();
        while(!isValidName(stringAux)){
            System.out.println("O logradouro não pode conter símbolos ou números");
            stringAux = scan.nextLine();
        }
        endereco.setLogradouro(stringAux);

        //  ============ CIDADE ============
        System.out.println("Insira sua cidade:");
        stringAux = scan.nextLine();
        while(!isValidName(stringAux)){
            System.out.println("O nome da cidade não pode conter símbolos ou números");
            stringAux = scan.nextLine();
        }
        endereco.setCidade(stringAux);

        //  ============ BAIRRO ============
        System.out.println("Insira seu bairro:");
        stringAux = scan.nextLine();
        while(!isValidName(stringAux)){
            System.out.println("O nome do bairro não pode conter símbolos ou números");
            stringAux = scan.nextLine();
        }
        endereco.setBairro(stringAux);

        //  ============ NUMERO ============
        System.out.println("Insira o número da residencia (0 se não houver):");
        stringAux = scan.nextLine();
        while(!isValidNUM(stringAux)){
            System.out.println("Você deve inserir apenas números nesse campo");
            stringAux = scan.nextLine();
        }
        endereco.setNumero(stringAux);

        //  ============ COMPLEMENTO ============
        System.out.println("Um complemento do endereço:");
        stringAux = scan.nextLine();
        while(!isValidName(stringAux)){
            System.out.println("O complemento não pode conter símbolos ou números");
            stringAux = scan.nextLine();
        }
        endereco.setComplemento(stringAux);

        return endereco;
    }




    public boolean cadastrarNovoCliente(Login login, PetManipulacao petManipulacao, HashMap<String,Cliente> mapa) throws BancoDeDadosException {

        Cliente cliente = new Cliente();
//        Scanner scan = new Scanner(System.in);
//
//        // Inserir nome:
//        System.out.println("Insira seu nome completo:");
//        String nomeAux = inserirNome(scan.nextLine(), scan);
//        cliente.setNome(nomeAux);
//
////
////        // Inserir endereco:
//        cliente.setEndereco(inserirEndereco(scan));
//        System.out.println(cliente.getEndereco());
//
////        // Inserir contatos:
//        cliente.setContatos(inserirContatos(scan));
//        System.out.println(cliente.getContatos());
//
//        // Inserir pets:
//        cliente.setPets(petManipulacao.inserirPets(scan, cliente.getPets()));
//
//        System.out.println("Cadastro realizado com sucesso.\n==================================");
//        mapa.put(login.getLogin(), cliente);
//        this.adicionarCliente(cliente);
        ClienteRepository clienteRepository = new ClienteRepository();
        clienteRepository.adicionar(cliente);
        return true;
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
}
