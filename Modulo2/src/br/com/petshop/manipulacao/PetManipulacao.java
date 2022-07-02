package br.com.petshop.manipulacao;

import br.com.petshop.moldes.cliente.Cliente;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.moldes.pets.Cachorro;
import br.com.petshop.moldes.pets.Gato;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.com.petshop.manipulacao.UsernameValidator.*;

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

//    public Animal adicionarNovoPet(Scanner scan, Cliente clienteVigente){
//        Animal animalCriado = popularNovoAnimal(scan);
//        // Busco a lista de pets do cliente
//        ArrayList<Animal> listaDeAnimais = clienteVigente.getPets();
//        // Adiciono meu novo animal no final da lista
//        listaDeAnimais.add(animalCriado);
//        clienteVigente.setPets(listaDeAnimais);
//        return animalCriado;
//    }

    public ArrayList<Animal> inserirPets(Scanner scan, ArrayList<Animal> listaDeAnimais){
        boolean temMaisAnimaisParaCadastrar = true;
        Animal novoAnimal = null;
        while(temMaisAnimaisParaCadastrar && listaDeAnimais.size() <= 5){
            novoAnimal = popularNovoAnimal(scan);
            String stringAux;
            if(novoAnimal != null){
                listaDeAnimais.add(novoAnimal);
            }
            temMaisAnimaisParaCadastrar = temMaisAnimaisParaCadastrar(scan);
        }
        if (listaDeAnimais.size() == 5) {
            System.out.println("Limite de animais atingido!\n" +
                    "Não será possivel cadastrar mais animais agora!");
        }
        System.out.println("Cadastro de pets finalizado!");
        this.setListaDeAnimais(listaDeAnimais);
        return listaDeAnimais;
    }

    private boolean temMaisAnimaisParaCadastrar(Scanner scan) {
        boolean temMaisAnimaisParaCadastrar = true;
        String stringAux;
        System.out.println("Deseja cadastrar mais animais ?\n1 - sim\n2 - nao");
        stringAux = scan.nextLine();
        while(!isValidDigit(stringAux)){
            System.out.println("Indique se deseja cadastrar mais animais com\n1 - para sim\n2 - para nao");
            stringAux = scan.nextLine();
        }
        if(stringAux.equals("2")){
            temMaisAnimaisParaCadastrar = false;
        }
        return temMaisAnimaisParaCadastrar;
    }

    private Animal popularNovoAnimal(Scanner scan) {
        Animal novoAnimal = null;
        //  ============ TIPO DE ANIMAL ============
        String stringAux = selecionarTipoDeAnimal(scan);
        //  ============ CRIANDO ANIMAL DEPENDENDO DO TIPO ============
        switch (Integer.parseInt(stringAux)){
            case 1 -> {
                Cachorro cachorro = new Cachorro();
                popularCaracteristicasGeraisDoAnimal(scan, cachorro);
                popularCaracteristicasEspecificasCachorro(scan, cachorro);
                novoAnimal = cachorro;
            }
            case 2 -> {
                Gato gato = new Gato();
                popularCaracteristicasGeraisDoAnimal(scan, gato);
                popularCaracteristicasEspecificasGato(scan, gato);
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
    private void popularCaracteristicasEspecificasCachorro(Scanner scan, Cachorro cachorro){
        // ============= O CACHORRO PODE ROER OSSO ? ===============
        System.out.println("Nos indique se seu cachorro pode roer osso:\n1 - para sim\n2 - para nao");
        String stringAux = scan.nextLine();
        while(!isValidDigit(stringAux)){
            System.out.println("""
                    Insira um valor valido:
                    1 - pode roer o osso ou\s
                    2 - nao pode roer o osso:""");
            stringAux = scan.nextLine();
        }
        cachorro.setPodeRoerOsso(stringAux.equals("1"));
    }
    private void popularCaracteristicasEspecificasGato(Scanner scan, Gato gato){
        System.out.println("Nos indique se seu gato pode brincar com a bolinha! \n1 - sim\n2 - nao");
        String stringAux = scan.nextLine();
        while(!isValidDigit(stringAux)){
            System.out.println("" +
                    "Insira um valor valido:" +
                    "1 - pode brincar com bola ou\s" +
                    "2 - nao pode brincar com a bola");
            stringAux = scan.nextLine();
            System.out.println("stringAux: " +stringAux);
        }
        gato.setPodeBrincarComBolaDeLa(stringAux.equals("1"));
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
//
//    public void editarAnimal(Cliente cliente, Animal animal, int i) {
//        Animal animalProcurado = cliente.getPets().get(i);
//        animalProcurado.setNome(animal.getNome());
//        animalProcurado.setRaca(animal.getRaca());
//        animalProcurado.setPorte(animal.getPorte());
//        animalProcurado.setPelagem(animal.getPelagem());
//    }
//
//     public void removerPetPorIndice(int index, Cliente cliente){
//        cliente.getPets().remove(index);
//     }
//
//    public void listarAnimais(Cliente cliente) {
//        for(int i = 0; i < cliente.getPets().size(); i++){
//            System.out.println("ID = ["+i+ "]\n"+cliente.getPets().get(i));
//        }
//    }
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
}
