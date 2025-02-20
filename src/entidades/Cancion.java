package entidades;

public class Cancion {

    // ATRIBUTOS
    private int idCancion;
    private String nombre;
    private Discos disco;
    private int duracionMinutos;
    private Medio medio;

    // Constructor completo
    public Cancion(int idCancion, String nombre, Discos disco, int duracionMinutos, Medio medio) {
        this.setIdCancion(idCancion);
        this.setNombre(nombre);
        this.setDisco(disco);
        this.setDuracionMinutos(duracionMinutos);
        this.setMedio(medio);
    }
    
    // Constructor sin ID
    public Cancion(String nombre, Discos disco, int duracionMinutos, Medio medio) {
        this.setNombre(nombre);
        this.setDisco(disco);
        this.setDuracionMinutos(duracionMinutos);
        this.setMedio(medio);
    }

    // Constructor sin disco
    public Cancion(int idCancion, String nombre, int duracionMinutos, Medio medio) {
        this.setIdCancion(idCancion);
        this.setNombre(nombre);
        this.setDuracionMinutos(duracionMinutos);
        this.setMedio(medio);
    }

    // Constructor con solo nombre
    public Cancion(String nombre, int duracionMinutos) {
        this.setNombre(nombre);
        this.setDuracionMinutos(duracionMinutos);
    }

    // GETTERS AND SETTERS
    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Discos getDisco() {
        return disco;
    }

    public void setDisco(Discos disco) {
        this.disco = disco;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public Medio getMedio() {
        return medio;
    }

    public void setMedio(Medio medio) {
        this.medio = medio;
    }

    // Método para convertir duración a formato HH:MM:SS
    public String getDuracionString() {
        return String.format("%02d:%02d:%02d", getDuracionMinutos() / 60, getDuracionMinutos() % 60, getDuracionMinutos() % 60);
    }

    // TO STRING
    @Override
    public String toString() {
        return "Cancion{" +
                "idCancion=" + idCancion +
                ", nombre='" + nombre + '\'' +
                ", duración=" + getDuracionString() +
                '}';
    }
}
