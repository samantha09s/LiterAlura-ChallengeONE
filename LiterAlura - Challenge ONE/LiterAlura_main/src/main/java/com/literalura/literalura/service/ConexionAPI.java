package com.literalura.literalura.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.literalura.literalura.service.impl.ConvierteDatosImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

// Servicio para realizar conexiones a la API externa de Gutendex.
@Service
public class ConexionAPI {

    private final ConvierteDatosImpl convierteDatos;

    public ConexionAPI(ConvierteDatosImpl convierteDatos) {
        this.convierteDatos = convierteDatos;
    }

    /*
     * Obtiene datos de libros desde la API de Gutendex utilizando el título proporcionado.
     *
     * @param titulo el título del libro a buscar
     * @return una lista de objetos Book obtenidos de la API
     * @throws ConexionApiException si ocurre un error en la conexión o procesamiento de datos
     */
    public List<Book> obtenerDatos(String titulo) {
        String encodedTitulo = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://gutendex.com/books/?search=" + encodedTitulo))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new ConexionApiException("Error en la respuesta de la API: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new ConexionApiException("Error al conectarse a la API", e);
        }

        // Convertir el JSON a una lista de objetos Book utilizando ConvierteDatos
        try {
            return convierteDatos.convertidora(response.body(), new TypeReference<List<Book>>() {});
        } catch (Exception e) {
            throw new ConexionApiException("Error al procesar la respuesta de la API", e);
        }
    }
}

// Excepción personalizada para errores de conexión a la API.
class ConexionApiException extends RuntimeException {
    public ConexionApiException(String message) {
        super(message);
    }

    public ConexionApiException(String message, Throwable cause) {
        super(message, cause);
    }
}