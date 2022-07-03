package br.com.petshop.moldes.pets;

import java.util.Arrays;

public enum EnumTipoAnimal {
    CACHORRO(1), //0
    GATO(2); //1

    private Integer tipo;

    EnumTipoAnimal(Integer tipo){
        this.tipo = tipo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public static EnumTipoAnimal ofTipo(Integer tipoAnimal){
        return Arrays.stream(EnumTipoAnimal.values())
                .filter(tp -> tp.getTipo().equals(tipoAnimal))
                .findFirst()
                .get();
    }
}
