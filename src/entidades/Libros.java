package entidades;

import java.time.LocalDate;
import java.util.List;

public class Libros extends Medio {
    private String ISBN;
    private String titulo;
    private Autor autor;
    private String tematica;
    private LocalDate anioPublicacion;
    private int numPaginas;
    private List<Capitulo> capitulos;

    // CONSTRUCTOR COMPLETO (CON ISBN)
    public Libros(int numRegistro, LocalDate fechaAdquisicion, double precioCompra, int numEjemplares,
                 String ISBN, String titulo, Autor autor, String tematica,
                 LocalDate anioPublicacion, int numPaginas, List<Capitulo> capitulos) {
        super(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);
        this.setISBN(ISBN);
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setTematica(tematica);
        this.setAnioPublicacion(anioPublicacion);
        this.setNumPaginas(numPaginas);
        this.setCapitulos(capitulos);
    }

    // CONSTRUCTOR SIN ISBN
    public Libros(int numRegistro, LocalDate fechaAdquisicion, double precioCompra, int numEjemplares,
                 String titulo, Autor autor, String tematica,
                 LocalDate anioPublicacion, int numPaginas, List<Capitulo> capitulos) {
        super(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setTematica(tematica);
        this.setAnioPublicacion(anioPublicacion);
        this.setNumPaginas(numPaginas);
        this.setCapitulos(capitulos);
    }

    // GETTERS AND SETTERS
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public LocalDate getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(LocalDate anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    @Override
    public void imprimirFicha() {
        System.out.println("=== Ficha del Libro ===");
        System.out.println("Número de registro: " + getNumRegistro());
        System.out.println("Fecha de adquisición: " + getFechaAdquisicion());
        System.out.println("Precio de compra: " + getPrecioCompra());
        System.out.println("Número de ejemplares: " + getNumEjemplares());
        System.out.println("ISBN: " + ISBN);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Temática: " + tematica);
        System.out.println("Año de publicación: " + anioPublicacion);
        System.out.println("Número de páginas: " + numPaginas);
        System.out.println("Capítulos: " + capitulos);
    }
}
