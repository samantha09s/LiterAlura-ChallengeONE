package com.literalura.literalura;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.literalura.literalura.dto.DataBook;
import com.literalura.literalura.dto.DataResults;
import com.literalura.literalura.service.BookService;
import com.literalura.literalura.service.ConexionAPI;
import com.literalura.literalura.service.ConvierteDatos;

//Clase que gestiona la interacción del usuario con el catálogo de libros.
public class Principal {
    // Definición de constantes para el menú y los idiomas soportados.
    private static final String MENU =
            "\n\n\t\t\t\t\t    BIBLIOTECA DIGITAL\n\n" +
                    "----------------- MENÚ PRINCIPAL -----------------\n" +
                    "1. Buscar libro por título\n" +
                    "2. Listar libros registrados\n" +
                    "3. Listar autores registrados\n" +
                    "4. Listar autores vivos en un determinado año\n" +
                    "5. Listar libros por idioma\n" +
                    "6. Visualizar datos estadísticos\n" +
                    "7. Mostrar top 10 libros descargados\n" +
                    "8. Buscar autor por nombre\n" +
                    "9. Listar autores que nacieron y murieron en un determinado rango de años\n" +
                    "0. Salir de la aplicación\n";

    private static final String[] IDIOMAS = {"es", "en", "fr", "pt"};

    // Dependencias del servicio.
    private ConexionAPI connection = new ConexionAPI();
    private ConvierteDatos convertidor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private BookService servicio;

    /*
     * Constructor que recibe el servicio de libros.
     * @param servicio Instancia de BookService para gestionar las operaciones con los libros.
     */
    public Principal(BookService servicio) {
        this.servicio = servicio;
    }

    // Muestra el menú principal y gestiona la interacción del usuario.
    public void mostrarMenu() {
        System.out.println(MENU);

        while (true) {
            try {
                System.out.print("\nSelecciona una opción (0-9): ");
                int opcion = Integer.parseInt(teclado.nextLine());
                if (opcion == 0) {
                    System.out.println("Gracias por usar la aplicación. ¡Hasta pronto!");
                    break;
                }
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("¡Opción no válida! Por favor, ingresa un número del 0 al 9.");
            }
        }
    }

    /*
     * Procesa la opción seleccionada por el usuario.
     * @param opcion Número de la opción seleccionada.
     */
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                buscarLibro();
                break;
            case 2:
                listarLibros();
                break;
            case 3:
                listarAutores();
                break;
            case 4:
                listarAutoresVivos();
                break;
            case 5:
                listarLibroXIdioma();
                break;
            case 6:
                mostrarDatosEstadisticos();
                break;
            case 7:
                listarTop10Libros();
                break;
            case 8:
                buscarAutorXNombre();
                break;
            case 9:
                listarAutoresVivosDentroDeRango();
                break;
            default:
                System.out.println("¡Opción no válida! Por favor, elige una opción del menú.");
        }
        System.out.println("\nRegresando al menú principal...");
    }

    // Lista autores vivos dentro de un rango de años.
    private void listarAutoresVivosDentroDeRango() {
        try {
            System.out.println("¡Descubre autores que nos inspiraron en un rango de años!");
            int anioDesde = obtenerAno("Ingresa el año de inicio (año desde el cual deseas buscar autores vivos): ");
            int anioHasta = obtenerAno("Ingresa el año final (año hasta el cual deseas buscar autores vivos): ");
            servicio.getAuthorsAliveRange(anioDesde, anioHasta);
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingresa un número válido para los años.");
        }
    }

    // Busca un autor por su nombre.
    private void buscarAutorXNombre() {
        System.out.println("¡Descubre a tus autores favoritos!");
        System.out.print("Ingresa el nombre del autor que deseas buscar: ");
        String nombre = teclado.nextLine().trim();
        if (!nombre.isEmpty()) {
            servicio.getAutor(nombre);
        } else {
            System.out.println("El nombre del autor no puede estar vacío.");
        }
    }
    // Lista los 10 libros más descargados.
    private void listarTop10Libros() {
        servicio.getTopBooks();
    }

    // Muestra datos estadísticos.
    private void mostrarDatosEstadisticos() {
        servicio.getStadisticData();
    }

    // Lista libros por idioma.
    private void listarLibroXIdioma() {
        System.out.println("¡Viaja a través de las letras del mundo!");
        System.out.println("........................................");
        System.out.println("Descubre libros en diferentes idiomas:");
        for (String idioma : IDIOMAS) {
            System.out.println("• " + idioma);
        }
        System.out.print("Elige el idioma que te gustaría explorar: ");
        String lenguaje = teclado.nextLine().trim();
        if (lenguajeValido(lenguaje)) {
            servicio.listBooksByLanguage(lenguaje);
        } else {
            System.out.println("Idioma no válido. Por favor, elige uno de la lista.");
        }
    }

    // Lista autores vivos en un determinado año.
    private void listarAutoresVivos() {
        try {
            System.out.println("¡Descubre autores que aún nos inspiran!");
            int anio = obtenerAno("Ingresa el año desde el cual deseas ver autores vivos: ");
            servicio.listAuthorsAlive(anio);
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingresa un número válido para el año.");
        }
    }

    // Lista todos los libros registrados.
    private void listarAutores() {
        servicio.listAuthors();
    }

    // Busca un libro por su título.
    private void listarLibros() {
        servicio.listBooks();
    }

    private void buscarLibro() {
        System.out.println("Ingrese el título del libro que deseas buscar:");
        String titulo = teclado.nextLine().trim();
        if (!titulo.isEmpty()) {
            try {
                String json = connection.obtenerDatos(titulo);
                DataResults datos = convertidor.convertidora(json, DataResults.class);
                List<DataBook> resultados = datos.results();
                if (resultados.isEmpty()) {
                    System.out.println("Lo sentimos, no se encontró ningún libro con ese título. ¿Desea intentar con otro término de búsqueda?");
                } else {
                    servicio.saveBook(resultados.get(0));
                    System.out.println("¡Libro encontrado!");
                }
            } catch (Exception e) {
                System.out.println("Error al buscar el libro. Por favor, intenta nuevamente.");
            }
        } else {
            System.out.println("El título del libro no puede estar vacío.");
        }
    }

    /*
     * Obtiene un año ingresado por el usuario.
     * @param mensaje Mensaje a mostrar al usuario.
     * @return Año ingresado por el usuario.
     * @throws NumberFormatException Si el usuario ingresa un valor no numérico.
     */
    private int obtenerAno(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(teclado.nextLine());
    }

    private boolean lenguajeValido(String lenguaje) {
        for (String idioma : IDIOMAS) {
            if (idioma.equalsIgnoreCase(lenguaje)) {
                return true;
            }
        }
        return false;
    }
}