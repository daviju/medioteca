package entidades;

import java.util.List;

public class Protagonista {

    // ATRIBUTOS
    private int idProta;
    private String nombre;
    private List<Peliculas> peliculas; // Lista de películas
    private Medio medio;

    // CONSTRUCTOR CON SETTERS
    public Protagonista(int idProta, String nombre, List<Peliculas> peliculas, Medio medio) {
        this.setIdProta(idProta);
        this.setNombre(nombre);
        this.setPeliculas(peliculas);
        this.setMedio(medio);
    }
    
    // CONSTRUCTOR SIN ID
    public Protagonista(String nombre, List<Peliculas> peliculas, Medio medio) {
        this.setNombre(nombre);
        this.setPeliculas(peliculas);
        this.setMedio(medio);
    }
    

    // GETTERS AND SETTERS
    public int getIdProta() {
        return idProta;
    }

    public void setIdProta(int idProta) {
        this.idProta = idProta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Peliculas> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Peliculas> peliculas) {
        this.peliculas = peliculas;
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
        return "Protagonista [idProta=" + idProta + ", nombre=" + nombre + ", peliculas=" + peliculas + ", medio=" + medio + "]";
    }
}
