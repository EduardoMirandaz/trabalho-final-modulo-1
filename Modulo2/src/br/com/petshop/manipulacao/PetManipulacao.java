package br.com.petshop.manipulacao;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.moldes.pets.Cachorro;
import br.com.petshop.moldes.pets.Gato;
import br.com.petshop.repository.AnimalRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.com.petshop.Main.indiceEhValido;
import static br.com.petshop.manipulacao.UsernameValidator.*;
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
        return animalCriado;
    }

    private Animal popularNovoAnimal(Scanner scan) {
        Animal novoAnimal = null;
        //  ============ TIPO DE ANIMAL ============
        String stringAux = selecionarTipoDeAnimal(scan);
        //  ============ CRIANDO ANIMAL DEPENDENDO DO TIPO ============
        switch (Integer.parseInt(stringAux)){
            case 1 -> {
                Cachorro cachorro = new Cachorro();
                cachorro.setTipoAnimal(CACHORRO);
                popularCaracteristicasGeraisDoAnimal(scan, cachorro);
                novoAnimal = cachorro;
            }
            case 2 -> {
                Gato gato = new Gato();
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

//    public void adicionarContratoDeBanho(Cliente cliente, int i){
//        cliente.getPets().get(i).contratarBanho();
//    }
//    public void adicionarContratoDeTosa(Cliente cliente, int i){
//        cliente.getPets().get(i).contratarTosa();
//    }
//    public void adicionarContratoDeCorteDeUnha(Cliente cliente, int i){
//        cliente.getPets().get(i).corteDeUnha();
//    }
//    public void adicionarContratoDeAdestramento(Cliente cliente, int i) {
//        cliente.getPets().get(i).adestrar();
//    }
//    public void valorContrato (Cliente cliente, int i){
//        System.out.println("Valor total sem desconto: R$" + cliente.getPets().get(i).getValorDoContrato());
//        System.out.println("Valor com desconto: R$" + String.format("%.2f",cliente.getPets().get(i).valorDesconto()));
//    }
    public void operacionarPets(Scanner scan, PetManipulacao petManipulacao, Cliente clienteVigente){
        // CRUD pets + contratar servicos
        telaInicialManipularPets();
        String opcao = scan.nextLine();
        while(!isValidDigit(opcao)){
            System.out.println("\n--/ opcao invalida =( /--\n");
            opcao = scan.nextLine();
        }
        switch (opcao){
            case "0" -> {
                System.out.println("Voltando ao menu inicial");
            }
            // ADICIONAR PETS
            case "1" -> {
                AnimalRepository animalRepository = new AnimalRepository();
                try{
                    animalRepository.adicionar(petManipulacao.adicionarNovoPet(scan, clienteVigente));
                }catch (SQLException e){
                    System.out.println("Erro ao adicionar pet no bd");
                    e.getCause();
                }
                System.out.println("Lista de pets atualizada!");
            }
            // EDITAR PETS
            case "2" -> {
                System.out.println("Informe o id do pet que deseja editar");
                AnimalRepository animalRepository = new AnimalRepository();
                try{
                    System.out.println("vou tentar listar os animais!");
                    System.out.println(animalRepository.listarAnimalPorCliente(clienteVigente.getId()));
                }catch (SQLException e){
                    e.getCause();
                }
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
                System.out.println("Qual pet voce deseja excluir?\nInsira o ID");
                AnimalRepository animalRepository = new AnimalRepository();
                try{
                    System.out.println("vou tentar listar os animais!");
                    System.out.println(animalRepository.listarAnimalPorCliente(clienteVigente.getId()));
                }catch (SQLException e){
                    e.getCause();
                }
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
            case "4" -> {
                AnimalRepository animalRepository = new AnimalRepository();
                try{
                    System.out.println("vou tentar listar os animais!");
                    System.out.println(animalRepository.listarAnimalPorCliente(clienteVigente.getId()));
                }catch (SQLException e){
                    e.getCause();
                }
            }
//            case "5" -> {
//                telaInicialContratarServicosPet();
//                operacionarContratosServicosPet(scan, petManipulacao, clienteVigente);
//            }
        }
    }

//    public void operacionarContratosServicosPet(Scanner scan, PetManipulacao petManipulacao, Cliente clienteVigente) {
//        String opcao = scan.nextLine();
//        while(!isValidDigit(opcao)) {
//            System.out.println("\n--/ opcao invalida =( /--\n");
//            opcao = scan.nextLine();
//        }
//        switch (opcao){
//            case "1" -> {
//                System.out.println("Selecione o index do pet que deseja adicionar o banho: ");
//                petManipulacao.listarAnimais(clienteVigente);
//                opcao = scan.nextLine();
//                while (!isValidDigit(opcao)) {
//                    System.out.println("\n--/ opcao invalida =( /--\n");
//                    opcao = scan.nextLine();
//                }
//                if(!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size()-1)){
//                    break;
//                }
//                int opcaoConvertida = Integer.parseInt(opcao);
//                petManipulacao.adicionarContratoDeBanho(clienteVigente, opcaoConvertida);
//            }
//            case "2" -> {
//                System.out.println("Selecione o index do pet que deseja adicionar a tosa: ");
//                petManipulacao.listarAnimais(clienteVigente);
//                opcao = scan.nextLine();
//                while (!isValidDigit(opcao)) {
//                    System.out.println("\n--/ opcao invalida =( /--\n");
//                    opcao = scan.nextLine();
//                }
//                if(!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size()-1)){
//                    break;
//                }
//                int opcaoConvertida = Integer.parseInt(opcao);
//                petManipulacao.adicionarContratoDeTosa(clienteVigente, opcaoConvertida);
//            }
//            case "3" -> {
//                System.out.println("Selecione o index do pet que deseja adicionar o corte de unha: ");
//                petManipulacao.listarAnimais(clienteVigente);
//                opcao = scan.nextLine();
//                if(!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size()-1)){
//                    break;
//                }
//                while (!isValidDigit(opcao)) {
//                    System.out.println("\n--/ opcao invalida =( /--\n");
//                    opcao = scan.nextLine();
//                }
//                int opcaoConvertida = Integer.parseInt(opcao);
//                petManipulacao.adicionarContratoDeCorteDeUnha(clienteVigente, opcaoConvertida);
//            }
//            case "4" -> {
//                System.out.println("Selecione o index do pet que deseja adicionar o adestramento: ");
//                petManipulacao.listarAnimais(clienteVigente);
//                opcao = scan.nextLine();
//                if(!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size()-1)){
//                    break;
//                }
//                while (!isValidDigit(opcao)) {
//                    System.out.println("\n--/ opcao invalida =( /--\n");
//                    opcao = scan.nextLine();
//                }
//                int opcaoConvertida = Integer.parseInt(opcao);
//                petManipulacao.adicionarContratoDeAdestramento(clienteVigente, opcaoConvertida);
//            }
//            case "5" -> {
//                System.out.println("Selecione o index do pet para ver o valor: ");
//                opcao = scan.nextLine();
//                if(!indiceEhValido(Integer.parseInt(opcao), clienteVigente.getPets().size()-1)){
//                    break;
//                }
//
//                while (!isValidDigit(opcao)) {
//                    System.out.println("\n--/ opcao invalida =( /--\n");
//                    opcao = scan.nextLine();
//                }
//                int opcaoConvertida = Integer.parseInt(opcao);
//
//                petManipulacao.valorContrato(clienteVigente, opcaoConvertida);
//            }
//            case "6" -> {
//                System.out.println("Obrigado por contratar nosso servicos, seu pet sera bem cuidado =)!!" +
//                        "");
//            }
//        }
//    }

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
            Quais contratos voce quer contratar?
            1- Banho
            2- Tosa
            3- Corte de unha
            4- Adestramento
            5- Meu valor atual
            6- Confirmar""");
    }
}