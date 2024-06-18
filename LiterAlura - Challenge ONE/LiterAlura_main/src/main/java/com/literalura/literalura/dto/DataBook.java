package com.literalura.literalura.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Representa un objeto de transferencia de datos de libro para la API de Gutendex.
 * Esta clase se utiliza para mapear la respuesta JSON a un objeto Java.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(

        // El ID único del libro.
        @NotNull
        @Positive
        @JsonAlias("id")
        Long id,

        // El título del libro.
        @NotNull
        @NotEmpty
        @JsonAlias("title")
        String title,

        // Los idiomas en los que está disponible el libro.
        @NotNull
        @NotEmpty
        @JsonAlias("languages")
        List<String> languages,

        // El número total de descargas del libro.
        @Positive
        @JsonAlias("download_count")
        int totalDownloads,

        // La lista de autores del libro.
        @NotNull
        @NotEmpty
        @Valid
        @JsonAlias("authors")
        List<DataAuthor> authors

) {
}