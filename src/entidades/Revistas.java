package entidades;

import java.time.LocalDate;
import java.util.List;

public class Revistas extends Medio {

    // ATRIBUTOS
    private String ISBN;
    private String titulo;
    private String tematica;
    private List<Articulo> indice;
    private LocalDate anioPublicacion;
    private int numPaginas;

    // CONSTRUCTOR CON ISBN
    public Revistas(int numRegistro, LocalDate fechaAdquisicion, double precioCompra, int numEjemplares,
                   String ISBN, String titulo, String tematica, List<Articulo> indice, 
                   LocalDate anioPublicacion, int numPaginas) {
        super(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);
        this.setISBN(ISBN);
        this.setTitulo(titulo);
        this.setTematica(tematica);
        this.setIndice(indice);
        this.setAnioPublicacion(anioPublicacion);
        this.setNumPaginas(numPaginas);
    }

    // CONSTRUCTOR SIN ISBN
    public Revistas(int numRegistro, LocalDate fechaAdquisicion, double precioCompra, int numEjemplares,
                   String titulo, String tematica, List<Articulo> indice, 
                   LocalDate anioPublicacion, int numPaginas) {
        super(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);
        this.setTitulo(titulo);
        this.setTematica(tematica);
        this.setIndice(indice);
        this.setAnioPublicacion(anioPublicacion);
        this.setNumPaginas(numPaginas);
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

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public List<Articulo> getIndice() {
        return indice;
    }

    public void setIndice(List<Articulo> indice) {
        this.indice = indice;
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

    @Override
    public void imprimirFicha() {
        System.out.println("=== FICHA DE LA REVISTA ===");
        System.out.println("Número de registro: " + numRegistro);
        System.out.println("Fecha de adquisición: " + fechaAdquisicion);
        System.out.println("Precio de compra: " + precioCompra + "€");
        System.out.println("Número de ejemplares: " + numEjemplares);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Título: " + titulo);
        System.out.println("Temática: " + tematica);
        System.out.println("Artículos: " + indice);
        System.out.println("Año de publicación: " + anioPublicacion);
        System.out.println("Número de páginas: " + numPaginas);
        System.out.println("========================");
    }
}