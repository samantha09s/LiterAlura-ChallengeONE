package com.literalura.literalura.service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.literalura.literalura.dto.DataAuthor;
import com.literalura.literalura.dto.DataBook;
import com.literalura.literalura.model.Author;
import com.literalura.literalura.model.Book;
import com.literalura.literalura.repository.AuthorRepository;
import com.literalura.literalura.repository.BookRepository;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private AuthorRepository repoAuthor;
    @Autowired
    private BookRepository repoBook;

    /*
     * Guarda un nuevo libro en el repositorio si no existe.
     *
     * @param d Datos del libro a guardar.
     */
    public void saveBook(DataBook d) {
        Optional<Book> libroBuscado = repoBook.findById(d.id());
        if (libroBuscado.isEmpty()) {
            List<Author> autores = verifyAuthors(d.authors());
            Book nuevoLibro = new Book(d.id(), d.title(), d.languages(), d.totalDownloads());
            for (Author author : autores) {
                nuevoLibro.addAuthor(author);
            }
            repoBook.save(nuevoLibro);
            logger.info("Libro guardado: {}", nuevoLibro);
        } else {
            logger.warn("No se puede registrar el mismo libro más de una vez: {}", d.title());
        }
    }

    /*
     * Verifica si los autores ya existen en el repositorio y los retorna.
     *
     * @param authors Lista de datos de autores a verificar.
     * @return Lista de autores verificados.
     */
    public List<Author> verifyAuthors(List<DataAuthor> authors) {
        List<Author> autores = new ArrayList<>();
        for (DataAuthor a : authors) {
            Author autor = repoAuthor.buscarAutor(a.name(), a.birthYear(), a.deathYear());
            if (autor == null) {
                autores.add(new Author(a.name(), a.birthYear(), a.deathYear()));
            } else {
                autores.add(autor);
            }
        }
        return autores;
    }

    // Lista todos los libros almacenados en el repositorio.
    public void listBooks() {
        List<Book> books = repoBook.findAll();
        books.forEach(book -> logger.info(book.toString()));
    }

    // Lista todos los autores almacenados en el repositorio.
    public void listAuthors() {
        List<Author> authors = repoBook.encontrarAutores();
        authors.forEach(author -> logger.info(author.toString()));
    }

    /*
     * Lista todos los autores que están vivos en un año específico.
     *
     * @param anio Año para verificar autores vivos.
     */
    public void listAuthorsAlive(int anio) {
        List<Author> authors = repoBook.encontrarAutoresVivos(anio);
        authors.forEach(author -> logger.info(author.toString()));
    }

    // Lista todos los idiomas disponibles en el repositorio.
    public void listAvailableLanguages() {
        List<String> languages = new ArrayList<>(repoBook.encontrarIdiomas());
        languages.forEach(language -> logger.info(language));
    }

    /*
     * Lista todos los libros en un idioma específico.
     *
     * @param lenguaje Idioma para filtrar libros.
     */
    public void listBooksByLanguage(String lenguaje) {
        List<Book> books = repoBook.encontrarLibroXIdioma(lenguaje);
        books.forEach(book -> logger.info(book.toString()));
    }

    // Muestra estadísticas de los libros almacenados.
    public void getStadisticData() {
        List<Book> books = repoBook.findAll();
        DoubleSummaryStatistics data = books.stream().collect(Collectors.summarizingDouble(Book::getTotalDownloads));
        logger.info("----- DATOS LIBROS -----\n" +
                        "Media de descargas: {}\n" +
                        "Mayor descargada: {}\n" +
                        "Menor descargada: {}\n" +
                        "Cantidad de libros almacenados: {}\n" +
                        "------------------------------",
                String.format("%1.2f", data.getAverage()),
                data.getMax(),
                data.getMin(),
                data.getCount());
    }

    // Lista los 10 libros más descargados.
    public void getTopBooks() {
        List<Book> libros = repoBook.encontrarTop10Libros();
        libros.forEach(book -> logger.info(book.toString()));
    }

    /*
     * Busca y muestra un autor por nombre.
     *
     * @param nombre Nombre del autor a buscar.
     */
    public void getAutor(String nombre) {
        Author author = repoBook.encontrarAutor(nombre);
        logger.info(author.toString());
    }

    /*
     * Lista todos los autores vivos en un rango de años.
     *
     * @param anioDesde Año de inicio del rango.
     * @param anioHasta Año de fin del rango.
     */
    public void getAuthorsAliveRange(int anioDesde, int anioHasta) {
        List<Author> autores = repoBook.encontrarAutoresVivosRango(anioDesde, anioHasta);
        autores.forEach(author -> logger.info(author.toString()));
    }
}