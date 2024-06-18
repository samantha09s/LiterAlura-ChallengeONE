package com.literalura.literalura.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/*
 * La clase Author representa a un autor en el catálogo de libros.
 * Incluye información sobre el nombre del autor, año de nacimiento, año de fallecimiento,
 * y una lista de libros asociados.
 */
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int yearBirth;

    private int yearDeath;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    /*
     * Constructor para crear un autor con nombre, año de nacimiento y año de fallecimiento.
     *
     * @param name        El nombre del autor.
     * @param yearBirth   El año de nacimiento del autor.
     * @param yearDeath   El año de fallecimiento del autor.
     */
    public Author(String name, int yearBirth, int yearDeath) {
        this.name = name;
        this.yearBirth = yearBirth;
        this.yearDeath = yearDeath;
    }

    // Constructor por defecto para JPA.
    public Author() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public int getYearDeath() {
        return yearDeath;
    }

    public void setYearDeath(int yearDeath) {
        this.yearDeath = yearDeath;
    }

    /*
     * Devuelve la lista de libros asociados a este autor.
     *
     * @return Una lista de libros.
     */
    public List<Book> getBooks() {
        return books;
    }

    /*
     * Añade un libro a la lista de libros asociados a este autor.
     *
     * @param book El libro a añadir.
     */
    public void addBook(Book book) {
        this.books.add(book);
    }

    /*
     * Devuelve una representación en cadena del autor, incluyendo sus libros asociados.
     *
     * @return Una cadena que representa al autor.
     */
    @Override
    public String toString() {
        return "Autor: " + this.name +
                "\nFecha de Nacimiento: " + this.yearBirth +
                "\nFecha de Fallecimiento: " + this.yearDeath +
                "\nLibros: " + this.getBooks().stream().map(b -> b.getTitle()).collect(Collectors.toList()) +
                "\n";
    }
}