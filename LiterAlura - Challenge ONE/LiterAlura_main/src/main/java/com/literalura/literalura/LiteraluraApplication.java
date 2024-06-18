package com.literalura.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.literalura.literalura.service.BookService;

/*
 * Clase principal de la aplicación Spring Boot.
 * Implementa CommandLineRunner para ejecutar el código al iniciar la aplicación.
 */
@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	// Inyección de dependencias del servicio BookService
	@Autowired
	private BookService servicio;

	/*
	 * Método principal que inicia la aplicación Spring Boot.
	 * @param args Argumentos de línea de comandos (no utilizados aquí).
	 */
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	/*
	 * Método que se ejecuta después de que la aplicación Spring Boot se haya iniciado.
	 * Aquí se crea una instancia de Principal y se muestra el menú.
	 * @param args Argumentos de línea de comandos (no utilizados aquí).
	 * @throws Exception En caso de que ocurra algún error durante la ejecución.
	 */
	@Override
	public void run(String... args) throws Exception {
		Principal miPrincipal = new Principal(servicio);
		miPrincipal.mostrarMenu();
	}
}