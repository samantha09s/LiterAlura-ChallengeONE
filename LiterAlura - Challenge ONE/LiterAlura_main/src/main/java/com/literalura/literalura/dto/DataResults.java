package com.literalura.literalura.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Data Transfer Object (DTO) para representar la respuesta JSON de la API de Gutendex.
 * Esta clase captura la lista de libros devuelta en la respuesta.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DataResults(

        /*
         * Lista de libros obtenidos de la API de Gutendex.
         * Esta lista es mapeada desde la propiedad "results" en la respuesta JSON.
         */
        @NotNull
        @Valid
        @JsonAlias("results")
        List<DataBook> results

) {
    // Clase creada con el propósito de almacenar los resultados obtenidos de la API de Gutendex.
}