package br.com.petshop.manipulacao;

import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.cliente.Contato;
import br.com.petshop.moldes.cliente.Endereco;
import br.com.petshop.moldes.cliente.Login;
import br.com.petshop.moldes.pets.Animal;

import java.util.ArrayList;
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
        System.out.println("stringAux: "+stringAux);
        while(!isValidFONENUMBER(stringAux)){
            System.out.println("Utilize o formato 00 00000-0000");
            stringAux = scan.nextLine();
            System.out.println("stringAux: "+stringAux);
        }
        telefonePrincipal.setTelefone(stringAux);
        //  ============ DESCRICAO TELEFONEMOVEL ============
        System.out.println("Insira a descricao do contato");
        stringAux = scan.nextLine();
        System.out.println("stringAux: "+stringAux);
        while(!isValidName(stringAux)){
            System.out.println("A descricao não pode conter símbolos ou números");
            stringAux = scan.nextLine();
            System.out.println("stringAux: "+stringAux);
        }
        telefonePrincipal.setDescricao(stringAux);

        Contato telefoneReserva = new Contato();
        //  ============ TELEFONEMOVEL ============
        System.out.println("Insira seu telefone reserva para contato:\nUtilize o formato 00 90000-0000");
        stringAux = scan.nextLine();
        System.out.println("stringAux: "+stringAux);
        while(!isValidFONENUMBER(stringAux)){
            System.out.println("Utilize o formato 00 00000-0000");
            stringAux = scan.nextLine();
            System.out.println("stringAux: "+stringAux);
        }
        telefoneReserva.setTelefone(stringAux);
        //  ============ DESCRICAO TELEFONEMOVEL ============
        System.out.println("Insira a descricao do contato");
        stringAux = scan.nextLine();
        System.out.println("stringAux: "+stringAux);
        while(!isValidName(stringAux)){
            System.out.println("A descricao não pode conter símbolos ou números");
            stringAux = scan.nextLine();
            System.out.println("stringAux: "+stringAux);
        }
        telefoneReserva.setDescricao(stringAux);

        return new ArrayList<Contato>(List.of(telefonePrincipal, telefoneReserva));
    }

    public Endereco inserirEndereco(Scanner scan){

        Endereco endereco = new Endereco();
        //  ============ CEP ============
        System.out.println("Insira seu CEP no formato 00000-000:");
        String stringAux = scan.nextLine();
        System.out.println("stringAux: "+stringAux);
        while(!isValidCEP(stringAux)){
            System.out.println("O CEP deve estar no formato 00000-000");
            stringAux = scan.nextLine();
            System.out.println("stringAux: "+stringAux);
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

    public boolean cadastrarNovoCliente(Login login, ArrayList<Cliente> listaDeClientes){
//        System.out.println("ultimo login criado:");
//        System.out.println(login);

//        private ArrayList<Animal> pets = new ArrayList<Animal>();
        Cliente cliente = new Cliente();
        Scanner scan = new Scanner(System.in);

        // Inserir nome:
        System.out.println("Insira seu nome completo:");
        String nomeAux = inserirNome(scan.nextLine(), scan);
        cliente.setNome(nomeAux);

        // Inserir id:
        cliente.setId(++ID);

        // Inserir endereco:
        cliente.setEndereco(inserirEndereco(scan));
        System.out.println(cliente.getEndereco());

        // Inserir contatos:
        cliente.setContatos(inserirContatos(scan));
        System.out.println(cliente.getContatos());

//        // todo organizar como a lista de pets será setada
//        // Inserir pets:
//        PetManipulacao petManipulacao = new PetManipulacao();
//        cliente.setPets(petManipulacao.inserirPets(scan, cliente.getPets()));
//        System.out.println(cliente.getPets());

        System.out.println("Cadastro realizado com sucesso.\n==================================");
        scan.close();
        this.adicionarCliente(cliente);
        return true;
    }
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
