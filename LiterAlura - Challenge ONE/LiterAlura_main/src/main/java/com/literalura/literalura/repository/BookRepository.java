package com.literalura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.literalura.literalura.model.Author;
import com.literalura.literalura.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
 * Interfaz de repositorio para gestionar las operaciones CRUD sobre la entidad Book.
 * Utiliza JpaRepository de Spring Data JPA para proporcionar métodos estándar de persistencia.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /*
     * Encuentra un libro por su ID.
     * @param id El ID del libro a buscar.
     * @return Un Optional con el libro encontrado o vacío si no se encuentra.
     */
    Optional<Book> findById(Long id);

    /*
     * Encuentra todos los autores de los libros en la base de datos.
     * @return Una lista de todos los autores encontrados.
     */
    @Query("SELECT a FROM Book b JOIN b.authors a")
    List<Author> encontrarAutores();

    /*
     * Encuentra autores que están vivos desde un año específico.
     * @param anio El año desde el cual buscar autores vivos.
     * @return Una lista de autores vivos en o después del año especificado.
     */
    @Query("SELECT a FROM Book b JOIN b.authors a WHERE a.yearDeath >= :anio")
    List<Author> encontrarAutoresVivos(int anio);

    /*
     * Encuentra todos los idiomas de los libros en la base de datos.
     * @return Un conjunto de idiomas únicos encontrados.
     */
    @Query("SELECT DISTINCT l FROM Book b JOIN b.languages l")
    Set<String> encontrarIdiomas();

    /*
     * Encuentra libros por un idioma específico.
     * @param lenguaje El idioma por el cual buscar libros.
     * @return Una lista de libros que están en el idioma especificado.
     */
    @Query("SELECT DISTINCT b FROM Book b JOIN b.languages l WHERE :lenguaje IN (l)")
    List<Book> encontrarLibroXIdioma(String lenguaje);

    /*
     * Encuentra los 10 libros más descargados.
     * @return Una lista de los 10 libros con más descargas.
     */
    @Query("SELECT b FROM Book b ORDER BY b.totalDownloads DESC")
    List<Book> encontrarTop10Libros();

    /*
     * Encuentra un autor por su nombre.
     * @param nombre El nombre del autor a buscar.
     * @return El autor encontrado o null si no se encuentra.
     */
    @Query("SELECT a FROM Book b JOIN b.authors a WHERE a.name LIKE :nombre%")
    Author encontrarAutor(String nombre);

    /*
     * Encuentra autores que nacieron y murieron dentro de un rango específico de años.
     * @param anioDesde El año de inicio del rango.
     * @param anioHasta El año final del rango.
     * @return Una lista de autores que nacieron y murieron dentro del rango especificado.
     */
    @Query("SELECT a FROM Book b JOIN b.authors a WHERE a.yearBirth BETWEEN :anioDesde AND :anioHasta AND a.yearDeath BETWEEN :anioDesde AND :anioHasta")
    List<Author> encontrarAutoresVivosRango(int anioDesde, int anioHasta);
}