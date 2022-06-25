package br.com.petshop.manipulacao;

import br.com.petshop.moldes.pets.Animal;

import java.util.ArrayList;
import java.util.List;

public class PetManipulacao {
    private List<Animal> listaDeAnimais;

    public PetManipulacao() {
        this.listaDeAnimais = new ArrayList<>();
    }

    public void adicionarAnimal(Animal animal) {
        this.listaDeAnimais.add(animal);
    }

    public void removerAnimalPorIndice(Integer index) {
        this.listaDeAnimais.remove(index.intValue());
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
