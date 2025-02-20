package entidades;

public class Articulo {

    // ATRIBUTOS
    private int idArticulo;
    private String nombre;
    private Revistas revista;
    private Medio medio;

    // CONSTRUCTOR CON SETTERS
    public Articulo(int idArticulo, String nombre, Revistas revista , Medio medio) {
        this.setIdArticulo(idArticulo);
        this.setNombre(nombre);
        this.setRevista(revista);
        this.setMedio(medio);
    }
    
    // CONSTRUCTOR SIN ID
    public Articulo(String nombre, Revistas revista , Medio medio) {
        this.setNombre(nombre);
        this.setRevista(revista);
        this.setMedio(medio);
    }

    // GETTERS AND SETTERS
    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Revistas getRevista() {
        return revista;
    }

    public void setRevista(Revistas revista) {
        this.revista = revista;
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
        return "Articulo [idArticulo=" + idArticulo + ", nombre=" + nombre + ", revista=" + revista +  ", medio=" + medio + "]";
    }
}
