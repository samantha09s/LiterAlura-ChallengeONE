package com.literalura.literalura.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/*
 * La clase Book representa un libro en el catálogo de LiterAlura.
 * Incluye información sobre el título, idiomas, número de descargas, autores, y otros atributos adicionales.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;

    private int totalDownloads;

    // Atributos adicionales
    private String publicationDate;
    private String genre;
    private String synopsis;
    private String publisher;

    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Author> authors = new ArrayList<>();

    /*
     * Constructor para crear un libro con detalles específicos.
     *
     * @param id             El ID del libro.
     * @param title          El título del libro.
     * @param languages      Los idiomas en que el libro está disponible.
     * @param totalDownloads El número total de descargas del libro.
     */
    public Book(Long id, String title, List<String> languages, int totalDownloads) {
        this.id = id;
        this.title = title;
        this.languages = languages;
        this.totalDownloads = totalDownloads;
    }

    // Constructor por defecto para JPA.
    public Book() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalDownloads() {
        return totalDownloads;
    }

    public void setTotalDownloads(int totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /*
     * Devuelve una representación en cadena del libro, incluyendo sus autores e idiomas.
     *
     * @return Una cadena que representa el libro.
     */
    @Override
    public String toString() {
        return "----- LIBRO ---- \n" +
                "Título: " + this.getTitle() + "\n" +
                "Autor(es): " + String.join(" y ", this.getAuthors().stream().map(Author::getName).collect(Collectors.toList())) + "\n" +
                "Idiomas: " + String.join(", ", this.getLanguages()) + "\n" +
                "Número de descargas: " + this.getTotalDownloads() + "\n" +
                "Fecha de publicación: " + this.getPublicationDate() + "\n" +
                "Género: " + this.getGenre() + "\n" +
                "Sinopsis: " + this.getSynopsis() + "\n" +
                "Editorial: " + this.getPublisher() + "\n" +
                "----------------";
    }
}