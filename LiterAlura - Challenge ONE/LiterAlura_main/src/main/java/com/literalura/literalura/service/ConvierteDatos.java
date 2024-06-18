package com.literalura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// Implementación de la interfaz IConvierteDatos utilizando la biblioteca Jackson para la conversión de JSON a objetos y viceversa.
@Service
public class ConvierteDatos implements IConvierteDatos {

    private static final Logger logger = LoggerFactory.getLogger(ConvierteDatos.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    /*
     * Convierte una cadena JSON en un objeto del tipo especificado.
     *
     * @param json  la cadena JSON a convertir
     * @param clase la clase del tipo al que se desea convertir el JSON
     * @param <T>   el tipo de objeto resultante
     * @return el objeto convertido del tipo especificado, o null si ocurre un error
     */
    @Override
    public <T> T convertidora(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            logger.error("Error al convertir JSON a objeto: {}", e.getMessage());
            throw new ConversionException("Error al convertir JSON a objeto", e);
        }
    }

    /*
     * Convierte un objeto del tipo especificado en una cadena JSON.
     *
     * @param objeto el objeto a convertir
     * @param <T>    el tipo del objeto
     * @return la cadena JSON resultante
     */
    public <T> String convertirAJson(T objeto) {
        try {
            return objectMapper.writeValueAsString(objeto);
        } catch (JsonProcessingException e) {
            logger.error("Error al convertir objeto a JSON: {}", e.getMessage());
            throw new ConversionException("Error al convertir objeto a JSON", e);
        }
    }

    /*
     * Convierte entre clases DTO (Data Transfer Object).
     *
     * @param origen el objeto de origen
     * @param destinoClass la clase del objeto de destino
     * @param <T> el tipo del objeto de destino
     * @return el objeto convertido del tipo especificado
     */
    public <T> T convertirDTO(Object origen, Class<T> destinoClass) {
        try {
            String json = objectMapper.writeValueAsString(origen);
            return objectMapper.readValue(json, destinoClass);
        } catch (JsonProcessingException e) {
            logger.error("Error al convertir DTO: {}", e.getMessage());
            throw new ConversionException("Error al convertir DTO", e);
        }
    }
}

// Excepción personalizada para errores de conversión.
class ConversionException extends RuntimeException {
    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}