package com.literalura.literalura.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonAlias;

/*
 * Representa un objeto de transferencia de datos de autor para la API de Gutendex.
 * Esta clase se utiliza para mapear la respuesta JSON a un objeto Java.
 */
public record DataAuthor(

        // El nombre del autor.
        @NotNull
        @NotEmpty
        @JsonAlias("name")
        String name,

        /*
         * Año de nacimiento del autor.
         * Este valor debe ser un entero positivo o cero.
         */
        @PositiveOrZero
        @JsonAlias("birth_year")
        int birthYear,

        /*
         * Año de fallecimiento del autor.
         * Este valor debe ser un número entero positivo o cero.
         */
        @PositiveOrZero
        @JsonAlias("death_year")
        int deathYear

) {
}