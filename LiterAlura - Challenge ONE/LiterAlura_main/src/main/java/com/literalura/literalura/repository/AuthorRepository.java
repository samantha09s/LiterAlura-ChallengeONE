package com.literalura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.literalura.literalura.model.Author;

/*
 * Interfaz de repositorio para gestionar las operaciones CRUD sobre la entidad Author.
 * Utiliza JpaRepository de Spring Data JPA para proporcionar métodos estándar de persistencia.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /*
     * Busca un autor por su nombre, año de nacimiento y año de muerte.
     * Este método es útil para encontrar un autor específico con información completa de su vida.
     *
     * @param nombre El nombre del autor a buscar.
     * @param anioNacimiento El año de nacimiento del autor.
     * @param anioMuerte El año de muerte del autor.
     * @return El autor que coincide con los criterios especificados o null si no se encuentra.
     */
    @Query("SELECT a FROM Author a WHERE a.name LIKE :nombre% AND a.yearBirth = :anioNacimiento AND a.yearDeath = :anioMuerte")
    Author buscarAutor(String nombre, int anioNacimiento, int anioMuerte);
}

/*
 * Busca autores que nacieron y murieron dentro de un rango de años especificado.
 *
 * @param anioNacimientoInicio El año de inicio del rango de nacimiento (inclusivo).
 * @param anioNacimientoFin El año de fin del rango de nacimiento (inclusivo).
 * @param anioMuerteInicio El año de inicio del rango de muerte (inclusivo).
 * @param anioMuerteFin El año de fin del rango de muerte (inclusivo).
 * @return Una lista de autores que nacieron y murieron dentro del rango especificado.
 */
@Query("SELECT a FROM Author a WHERE a.yearBirth BETWEEN :anioNacimientoInicio AND :anioNacimientoFin AND a.yearDeath BETWEEN :anioMuerteInicio AND :anioMuerteFin")
List<Author> buscarAutoresPorRango(int anioNacimientoInicio, int anioNacimientoFin, int anioMuerteInicio, int anioMuerteFin);