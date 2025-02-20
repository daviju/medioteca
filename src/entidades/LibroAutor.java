package entidades;

public class LibroAutor {

    // ATRIBUTOS
    private Libros libro;
    private Autor autor;
    private Medio medio;

    // CONSTRUCTOR CON SETTERS
    public LibroAutor(Libros libro, Autor autor , Medio medio) {
        this.setLibro(libro);
        this.setAutor(autor);
        this.setMedio(medio);
    }

    // GETTERS AND SETTERS
    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Medio getMedio() {
        return medio;
    }

    public void setMedio(Medio medio) {
        this.medio = medio;
    }

    // TO STRING
    @Override
    public String toString() {
        return "LibroAutor [libro=" + libro + ", autor=" + autor + ", medio=" + medio + "]";
    }
}
