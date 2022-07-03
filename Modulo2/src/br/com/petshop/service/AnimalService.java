package br.com.petshop.service;

import br.com.petshop.exceptions.BancoDeDadosException;
import br.com.petshop.moldes.pets.Animal;
import br.com.petshop.repository.AnimalRepository;

public class AnimalService {
    private AnimalRepository animalRepository;

    public AnimalService() {
        animalRepository = new AnimalRepository();
    }


    public void adicionarAnimal(Animal animal) {
        try {
            Animal animalAdd = animalRepository.adicionar(animal);
            System.out.println("animal adicinado com sucesso! " + animalAdd);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }


    public void remover(Integer id) {
        try {
            boolean removerAnimal = animalRepository.remover(id);
            System.out.println("Animal removido: " + removerAnimal);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }


    public void editar(Integer id, Animal animal) {
        try {
            boolean editarAnimal = animalRepository.editar(id, animal);
            System.out.println("Animal editado: " + editarAnimal);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }


    public void listar() {
        try {
            animalRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
