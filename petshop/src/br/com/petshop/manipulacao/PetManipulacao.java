package br.com.petshop.manipulacao;

import br.com.petshop.moldes.cliente.Contato;
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

    public PetManipulacao() {
        this.listaDeAnimais = new ArrayList<>();
    }

    public void adicionarAnimal(Animal animal) {
        this.listaDeAnimais.add(animal);
    }

    public void removerAnimalPorIndice(Integer index) {
        this.listaDeAnimais.remove(index.intValue());
    }

    public ArrayList<Animal> inserirPets(Scanner scan, ArrayList<Animal> listaDeAnimais){
        boolean temMaisAnimaisParaCadastrar = true;
        Animal novoAnimal = null;
        while(temMaisAnimaisParaCadastrar && listaDeAnimais.size() <= 5){
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

            if(novoAnimal != null){
                listaDeAnimais.add(novoAnimal);
            }

            System.out.println("Deseja cadastrar mais animais ?\n1 - sim\n2 - nao");
            stringAux = scan.nextLine();
            System.out.println("stringAux: "+stringAux);
            while(!isValidDigit(stringAux)){
                System.out.println("Indique se deseja cadastrar mais animais com\n1 - para sim\n2 - para nao");
                stringAux = scan.nextLine();
                System.out.println("stringAux: "+stringAux);
            }
            if(stringAux.equals("2")){
                temMaisAnimaisParaCadastrar = false;
            }
        }
        return listaDeAnimais;
    }

    private void popularCaracteristicasGeraisDoAnimal(Scanner scan, Animal animal) {
        String stringAux;
        // ============= NOME DO ANIMAL ===============
        System.out.println("Insira o nome do seu animal:");
        stringAux = scan.nextLine();
        System.out.println("stringAux: "+stringAux);
        while(!isValidName(stringAux)){
            System.out.println("Infelizmente, o nome do seu animal nao pode ter digitos nem simbolos");
            stringAux = scan.nextLine();
            System.out.println("stringAux: "+stringAux);
        }
        animal.setNome(stringAux);

        // ============= RACA DO ANIMAL ===============
        System.out.println("Insira a raca do seu animal:");
        stringAux = scan.nextLine();
        System.out.println("stringAux: "+stringAux);
        while(!isValidName(stringAux)){
            System.out.println("Infelizmente, a raca do seu animal nao pode ter digitos nem simbolos");
            stringAux = scan.nextLine();
            System.out.println("stringAux: "+stringAux);
        }
        animal.setRaca(stringAux);

        // ============= PELAGEM DO ANIMAL ===============
        System.out.println("Insira o nivel de pelagem do seu animal(0-pelado ate 9-pelo maximo da raca):");
        stringAux = scan.nextLine();
        System.out.println("stringAux: "+stringAux);
        while(!isValidDigit(stringAux)){
            stringAux = scan.nextLine();
            System.out.println("""
                    Insira valores de 0 a 9 referentes a pelagem do seu animal, sendo
                    0 - pelado
                    9 - Com o maximo de pelo da raca""");
            System.out.println("stringAux: "+stringAux);
        }
        animal.setPelagem(Integer.parseInt(stringAux));

        // ============= PORTE DO ANIMAL ===============
        System.out.println(
                "Insira valores de 0 a 9 referentes ao porte do seu animal, alguns parametros:\n" +
                "0 - Pinscher, Maltes, Shih Tzu, Yorkshire\n" +
                "9 - Pastor Alemao, Dobberman, Labrador, Dalmata\"\"\");");
        stringAux = scan.nextLine();
        System.out.println("stringAux: " + stringAux);
        while(!isValidDigit(stringAux)){
            stringAux = scan.nextLine();
            System.out.println("""
                    Insira valores de 0 a 9 referentes ao porte do seu animal, alguns parametros:
                    0 - Pinscher, Maltes, Shih Tzu, Yorkshire
                    9 - Pastor Alemao, Dobberman, Labrador, Dalmata""");
            System.out.println("stringAux: "+stringAux);
        }
        animal.setPorte(Integer.parseInt(stringAux));

        // ============= IDADE DO ANIMAL ===============
        System.out.println("Insira a idade do seu animal:");
        stringAux = scan.nextLine();
        System.out.println("stringAux: "+stringAux);
        while(!isValidDigit(stringAux)){
            stringAux = scan.nextLine();
            System.out.println("Insira a idade do seu animal, apenas digitos");
            System.out.println("stringAux: "+stringAux);
        }
        animal.setIdade(Integer.parseInt(stringAux));
    }
    private void popularCaracteristicasEspecificasCachorro(Scanner scan, Cachorro cachorro){
        // ============= O CACHORRO PODE ROER OSSO ? ===============
        System.out.println("Nos indique se seu cachorro pode roer osso!\n1 - sim\n2 - nao:");
        String stringAux = scan.nextLine();
        System.out.println("stringAux: "+stringAux);
        while(!isValidDigit(stringAux)){
            System.out.println("""
                    Insira um valor valido:
                    1 - pode roer o osso ou\s
                    2 - nao pode roer o osso:""");
            stringAux = scan.nextLine();
            System.out.println("stringAux: "+stringAux);
        }
        cachorro.setPodeRoerOsso(stringAux.equals("1"));
    }
    private void popularCaracteristicasEspecificasGato(Scanner scan, Gato gato){
        System.out.println("Nos indique se seu gato pode brincar com a bolinha! \n1 - sim\n 2 - nao:");
        String stringAux = scan.nextLine();
        System.out.println("stringAux: "+ stringAux);
        while(!isValidDigit(stringAux)){
            System.out.println("" +
                    "Insira um valor valido:" +
                    "1 - pode brincar com bola ou\s" +
                    "2 - nao pode brincar com a bola:");
            stringAux = scan.nextLine();
            System.out.println("stringAux: " +stringAux);
        }
        gato.setPodeBrincarComBolaDeLa(stringAux.equals("1"));
    }

    private String selecionarTipoDeAnimal(Scanner scan) {
        System.out.println("Insira a especie do seu animal\n1- Cachorro\n2- Gato\n3- Roedor");
        String stringAux = scan.nextLine();
        System.out.println("stringAux: "+stringAux);
        while(!isValidDigit(stringAux)){
            System.out.println("""
                    Insira uma espécie como especificado.
                    1- Caso seu pet seja um cachorro
                    2- Caso seu pet seja um gato""");
            stringAux = scan.nextLine();
            System.out.println("stringAux: "+stringAux);
        }
        return stringAux;
    }

    public void editarAnimal(Integer index, Animal animal) {
        Animal animalProcurado = listaDeAnimais.get(index);
        animalProcurado.setNome(animal.getNome());
        animalProcurado.setRaca(animal.getRaca());
        animalProcurado.setPorte(animal.getPorte());
        animalProcurado.setPelagem(animal.getPelagem());
    }

    public void listarAnimais() {
        for(int i=0; i < listaDeAnimais.size(); i++) {
            System.out.println("id=" + i + "||" + listaDeAnimais.get(i));
        }
    }
}
