package br.com.petshop.view.manipulacao;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.repository.AnimalRepository;
import br.com.petshop.service.ContratoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.com.petshop.util.UsernameValidator.*;
import static br.com.petshop.moldes.pets.EnumTipoAnimal.CACHORRO;
import static br.com.petshop.moldes.pets.EnumTipoAnimal.GATO;

public class PetManipulacao {
    private List<Animal> listaDeAnimais;
    static int MAXIMO_DE_ANIMAIS = 5;

    public List<Animal> getListaDeAnimais() {
        return listaDeAnimais;
    }

    public void setListaDeAnimais(List<Animal> listaDeAnimais) {
        this.listaDeAnimais = listaDeAnimais;
    }

    public PetManipulacao() {
        this.listaDeAnimais = new ArrayList<>();
    }

    public void adicionarAnimal(Animal animal) {
        this.listaDeAnimais.add(animal);
    }

    public void removerAnimalPorIndice(Integer index) {
        this.listaDeAnimais.remove(index.intValue());
    }

    public Animal adicionarNovoPet(Scanner scan, Cliente clienteVigente){
        Animal animalCriado = popularNovoAnimal(scan);
        animalCriado.setCliente(clienteVigente);
        animalCriado.getCliente().setId(clienteVigente.getId());
        animalCriado.getCliente().setNome(clienteVigente.getNome());
        return animalCriado;
    }

    private Animal popularNovoAnimal(Scanner scan) {
        Animal novoAnimal = null;
        //  ============ TIPO DE ANIMAL ============
        String stringAux = selecionarTipoDeAnimal(scan);
        //  ============ CRIANDO ANIMAL DEPENDENDO DO TIPO ============
        switch (Integer.parseInt(stringAux)){
            case 1 -> {
                Animal cachorro = new Animal();
                cachorro.setTipoAnimal(CACHORRO);
                popularCaracteristicasGeraisDoAnimal(scan, cachorro);
                novoAnimal = cachorro;
            }
            case 2 -> {
                Animal gato = new Animal();
                gato.setTipoAnimal(GATO);
                popularCaracteristicasGeraisDoAnimal(scan, gato);
                novoAnimal = gato;
            }
        }
        return novoAnimal;
    }

    private void popularCaracteristicasGeraisDoAnimal(Scanner scan, Animal animal) {
        String stringAux;
        // ============= NOME DO ANIMAL ===============
        System.out.println("Insira o nome do seu animal:");
        stringAux = scan.nextLine();
        while(!isValidName(stringAux)){
            System.out.println("Infelizmente, o nome do seu animal nao pode ter digitos nem simbolos");
            stringAux = scan.nextLine();
        }
        animal.setNome(stringAux);



        // ============= RACA DO ANIMAL ===============
        System.out.println("Insira a raca do seu animal:");
        stringAux = scan.nextLine();
        while(!isValidName(stringAux)){
            System.out.println("Infelizmente, a raca do seu animal nao pode ter digitos nem simbolos");
            stringAux = scan.nextLine();
        }
        animal.setRaca(stringAux);


        // ============= PELAGEM DO ANIMAL ===============
        System.out.println("Insira o nivel de pelagem do seu animal(0-pelado ate 9-pelo maximo da raca):");
        stringAux = scan.nextLine();
        while(!isValidDigit(stringAux)){
            System.out.println("""
                    Insira valores de 0 a 9 referentes a pelagem do seu animal, sendo
                    0 - pelado
                    9 - Com o maximo de pelo da raca""");
            stringAux = scan.nextLine();
        }
        animal.setPelagem(Integer.parseInt(stringAux));


        // ============= PORTE DO ANIMAL ===============
        System.out.println(
                "Insira valores de 0 a 9 referentes ao porte do seu animal, alguns parametros:\n" +
                "0 - Pinscher, Maltes, Shih Tzu, Yorkshire\n" +
                "9 - Pastor Alemao, Dobberman, Labrador, Dalmata\"\"\");");
        stringAux = scan.nextLine();
        while(!isValidDigit(stringAux)){
            System.out.println("""
                    Insira valores de 0 a 9 referentes ao porte do seu animal, alguns parametros:
                    0 - Pinscher, Maltes, Shih Tzu, Yorkshire
                    9 - Pastor Alemao, Dobberman, Labrador, Dalmata""");
            stringAux = scan.nextLine();
        }
        animal.setPorte(Integer.parseInt(stringAux));


        // ============= IDADE DO ANIMAL ===============
        System.out.println("Insira a idade do seu animal:");
        stringAux = scan.nextLine();
        while(!isValidNUM(stringAux)){
            System.out.println("Insira a idade do seu animal, apenas digitos");
            stringAux = scan.nextLine();
        }
        animal.setIdade(Integer.parseInt(stringAux));



    }

    private String selecionarTipoDeAnimal(Scanner scan) {
        System.out.println("Insira a especie do seu animal\n1- Cachorro\n2- Gato\n");
        String stringAux = scan.nextLine();
        while(!isValidDigit(stringAux)){
            System.out.println("""
                    Insira uma espécie como especificado.
                    1- Caso seu pet seja um cachorro
                    2- Caso seu pet seja um gato""");
            stringAux = scan.nextLine();
        }
        return stringAux;
    }

    public void operacionarPets(Scanner scan, PetManipulacao petManipulacao, Cliente clienteVigente){
        // CRUD pets + contratar servicos
        telaInicialManipularPets();
        String opcao = scan.nextLine();
        while(!isValidDigit(opcao)){
            System.out.println("\n--/ opcao invalida =( /--\n");
            opcao = scan.nextLine();
        }
        switch (opcao){
            // VOLTAR AO MENU ANTERIOR
            case "0" -> {
                System.out.println("Voltando ao menu inicial");
            }
            // ADICIONAR PETS
            case "1" -> {
                AnimalRepository animalRepository = new AnimalRepository();
                try{
                    Animal animalCriado = petManipulacao.adicionarNovoPet(scan, clienteVigente);
                    System.out.println(animalCriado);
                    animalRepository.adicionar(animalCriado);
                }catch (SQLException e){
                    System.out.println("Erro ao adicionar pet no bd");
                    e.getCause();
                }
                System.out.println("Lista de pets atualizada!");
            }
            // EDITAR PETS
            case "2" -> {
                AnimalRepository animalRepository = new AnimalRepository();
                listarPetsPorCliente(clienteVigente);
                System.out.println("Informe o id do pet que deseja editar");
                opcao = scan.nextLine();
                while(!isValidNUM(opcao)){
                    System.out.println("Infelizmente, esse ID não é válido");
                    opcao = scan.nextLine();
                }
                System.out.println("Insira agora as informacoes corrigidas");
                Animal petEditado = petManipulacao.adicionarNovoPet(scan, clienteVigente);
                petEditado.setIdAnimal(Integer.parseInt(opcao));
                try {
                    animalRepository.editar(Integer.parseInt(opcao), petEditado);
                } catch (BancoDeDadosException e) {
                    throw new RuntimeException(e);
                }
            }
             //REMOVER PETS
            case "3" -> {
                AnimalRepository animalRepository = new AnimalRepository();
                listarPetsPorCliente(clienteVigente);
                System.out.println("Qual pet voce deseja excluir?\nInsira o ID");
                opcao = scan.nextLine();
                while(!isValidNUM(opcao)){
                    System.out.println("ID invalido");
                    opcao = scan.nextLine();
                }
                if(Integer.parseInt(opcao) < 0){
                    System.out.println("Index de pet inválido");
                    break;
                }
                try {
                    animalRepository.remover(Integer.parseInt(opcao));
                } catch (BancoDeDadosException e) {
                    throw new RuntimeException(e);
                }
            }
            // LISTAR PETS
            case "4" -> {
                listarPetsPorCliente(clienteVigente);
            }
            // OPERACIONAR CONTRATOS e SERVICOS do PET
            case "5" -> {
                telaInicialContratarServicosPet();
                operacionarContratosServicosPet(scan, petManipulacao, clienteVigente);
            }
        }
    }

    private void listarPetsPorCliente(Cliente clienteVigente) {
        System.out.println("LISTANDO SEUS ANIMAIS CADASTRADOS!");
        AnimalRepository animalRepository = new AnimalRepository();
        try{
            List<Animal> animais = animalRepository.listarAnimalPorCliente(clienteVigente);
            if(animais.size() != 0){
                System.out.println(animais);
            }
            else{
                System.out.println("Você nao tem animais cadastrados!");
            }
        }catch (SQLException e){
            e.getCause();
        }
    }

    public void operacionarContratosServicosPet(Scanner scan, PetManipulacao petManipulacao, Cliente clienteVigente) {
        String opcao = scan.nextLine();
        while(!isValidDigit(opcao)) {
            System.out.println("\n--/ opcao invalida =( /--\n");
            opcao = scan.nextLine();
        }
        switch (opcao){
            case "1" -> {
                System.out.println("Indique o ID do pet que deseja adicionar o banho: ");
                listarPetsPorCliente(clienteVigente);
                opcao = scan.nextLine();
                while (!isValidNUM(opcao)) {
                    System.out.println("\n--/ opcao invalida =( /--\n");
                    opcao = scan.nextLine();
                }
                int idPetSelecionado = Integer.parseInt(opcao);
                ContratoService contratoService = new ContratoService();
                try {
                    contratoService.contratarBanho(clienteVigente, idPetSelecionado, false);
                } catch (BancoDeDadosException e) {
                    throw new RuntimeException(e);
                }
            }
            case "2" -> {
                System.out.println("Indique o ID do pet que deseja adicionar a tosa: ");
                listarPetsPorCliente(clienteVigente);
                opcao = scan.nextLine();
                while (!isValidNUM(opcao)) {
                    System.out.println("\n--/ opcao invalida =( /--\n");
                    opcao = scan.nextLine();
                }
                int idPetSelecionado = Integer.parseInt(opcao);
                ContratoService contratoService = new ContratoService();
                try {
                    contratoService.contratarTosa(clienteVigente, idPetSelecionado, false);
                } catch (BancoDeDadosException e){
                    throw new RuntimeException(e);
                }
            }
            case "3" -> {
                System.out.println("Selecione o index do pet que deseja adicionar o corte de unha: ");
                listarPetsPorCliente(clienteVigente);
                opcao = scan.nextLine();
                while (!isValidNUM(opcao)) {
                    System.out.println("\n--/ opcao invalida =( /--\n");
                    opcao = scan.nextLine();
                }
                int idPetSelecionado = Integer.parseInt(opcao);
                ContratoService contratoService = new ContratoService();
                try {
                    contratoService.contratarCorteDeUnha(clienteVigente, idPetSelecionado, false);
                } catch (BancoDeDadosException e){
                    throw new RuntimeException(e);
                }
            }
            case "4" -> {
                System.out.println("Selecione o index do pet que deseja adicionar o adestramento: ");
                listarPetsPorCliente(clienteVigente);
                opcao = scan.nextLine();
                while (!isValidNUM(opcao)) {
                    System.out.println("\n--/ opcao invalida =( /--\n");
                    opcao = scan.nextLine();
                }
                int idPetSelecionado = Integer.parseInt(opcao);
                ContratoService contratoService = new ContratoService();
                try {
                    contratoService.contratarAdestramento(clienteVigente, idPetSelecionado, false);
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
    }

    public void telaInicialManipularPets() {
        System.out.println("""
            Insira o que deseja fazer
            1 - Para adicionar novo pet.
            2 - Para editar um pet existente.
            3 - Para remover um pet existente.
            4 - Para listar seus pets.
            5 - Para contratar servicos.
            0 - Para sair""");
    }
    public void telaInicialContratarServicosPet() {
        System.out.println("""
            Bem vindo a tela de servicos para o pet!
            Insira o que deseja fazer
            Quais contratos voce quer adquirir?
            1- Banho
            2- Tosa
            3- Corte de unha
            4- Adestramento
            5- Voltar ao menu anterior""");
    }
}