package entidades;

public class Capitulo {

    // ATRIBUTOS
    private int idCapitulo;
    private String nombre;
    private Libros libro;
    private Medio medio;

    // CONSTRUCTOR CON SETTERS
    public Capitulo(int idCapitulo, String nombre, Libros libro , Medio medio) {
        this.setIdCapitulo(idCapitulo);
        this.setNombre(nombre);
        this.setLibro(libro);
        this.setMedio(medio);
    }

    // GETTERS AND SETTERS
    public int getIdCapitulo() {
        return idCapitulo;
    }

    public void setIdCapitulo(int idCapitulo) {
        this.idCapitulo = idCapitulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
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
        return "Capitulo [idCapitulo=" + idCapitulo + ", nombre=" + nombre + ", libro=" + libro +  ", medio=" + medio + "]";
    }
}
