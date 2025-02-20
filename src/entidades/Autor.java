package entidades;

public class Autor {

    // ATRIBUTOS
    private int idAutor;
    private String nombre;

    // CONSTRUCTOR CON SETTERS
    public Autor(int idAutor, String nombre) {
        this.setIdAutor(idAutor);
        this.setNombre(nombre);
    }

    // GETTERS AND SETTERS
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    // TO STRING
    @Override
    public String toString() {
        return "Autor [idAutor=" + idAutor + ", nombre=" + nombre +"]";
    }
}
