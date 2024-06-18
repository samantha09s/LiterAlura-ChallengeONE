package com.literalura.literalura;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class LiteraluraApplicationTests {

	@Autowired
	private LiteraluraApplication application;

	@Test
	void contextLoads() {
		// Verifica que la aplicación se inicie correctamente.
		assertNotNull(application);
	}

	// Ejemplo de prueba unitaria adicional.
	@Test
	void exampleUnitTest() {
		// Simula una prueba unitaria para verificar algún comportamiento específico.
		// Ejemplo básico de verificación:
		String expected = "Hola";
		String actual = "Querido y gentil lector.";

		// Verifica que el resultado esperado sea igual al actual
		assertEquals(expected, actual);
	}
}