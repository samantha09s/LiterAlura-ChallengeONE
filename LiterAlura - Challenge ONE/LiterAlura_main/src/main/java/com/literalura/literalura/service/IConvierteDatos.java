package com.literalura.literalura.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.literalura.service.IConvierteDatos;
import org.springframework.stereotype.Service;

// Implementación de la interfaz IConvierteDatos utilizando la biblioteca Jackson para la conversión de JSON a objetos Java.
@Service
public class ConvierteDatosImpl implements IConvierteDatos {

    private final ObjectMapper objectMapper;

    // Constructor que inicializa el ObjectMapper.
    public ConvierteDatosImpl() {
        this.objectMapper = new ObjectMapper();
    }

    /*
     * Convierte una cadena JSON en un objeto del tipo especificado utilizando Jackson.
     *
     * @param json la cadena JSON a convertir
     * @param clase la clase del tipo al que se desea convertir el JSON
     * @return el objeto convertido del tipo especificado
     */
    @Override
    public <T> T convertidora(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (Exception e) {
            // Manejo de errores en la conversión JSON
            System.err.println("Error al convertir JSON a objeto: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}