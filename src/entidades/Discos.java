package entidades;

import java.time.LocalDate;
import java.util.List;

public class Discos extends Medio {

    // ATRIBUTOS
    private String ISMN;
    private String titulo;
    private String interprete;
    private String estilo;
    private String soporte;
    private LocalDate anioPublicacion;
    private Medio medio;

    private List<Cancion> canciones;

    // CONSTRUCTORES

    // Constructor completo
    public Discos(int numRegistro, LocalDate fechaAdquisicion, double precioCompra, int numEjemplares,
            String ISMN, String titulo, String interprete, String estilo, List<Cancion> canciones,
            String soporte, LocalDate anioPublicacion) {
        super(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);
        this.setISMN(ISMN);
        this.setTitulo(titulo);
        this.setInterprete(interprete);
        this.setEstilo(estilo);
        this.setCanciones(canciones);
        this.setSoporte(soporte);
        this.setAnioPublicacion(anioPublicacion);
    }
    
    // Constructor 
    public Discos(int numRegistro, LocalDate fechaAdquisicion, double precioCompra, int numEjemplares,
            String ISMN, String titulo) {
        super(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);
        this.setISMN(ISMN);
        this.setTitulo(titulo);
    }

    // Constructor sin ISMN
    public Discos(int numRegistro, LocalDate fechaAdquisicion, double precioCompra, int numEjemplares,
            String titulo, String interprete, String estilo, List<Cancion> canciones,
            String soporte, LocalDate anioPublicacion) {
        super(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);
        this.setTitulo(titulo);
        this.setInterprete(interprete);
        this.setEstilo(estilo);
        this.setCanciones(canciones);
        this.setSoporte(soporte);
        this.setAnioPublicacion(anioPublicacion);
    }

    // Constructor sin canciones
    public Discos(int numRegistro, LocalDate fechaAdquisicion, double precioCompra, int numEjemplares,
            String ISMN, String titulo, String interprete, String estilo,
            String soporte, LocalDate anioPublicacion) {
        super(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);
        this.setISMN(ISMN);
        this.setTitulo(titulo);
        this.setInterprete(interprete);
        this.setEstilo(estilo);
        this.setSoporte(soporte);
        this.setAnioPublicacion(anioPublicacion);
    }

    // GETTERS AND SETTERS
    public String getISMN() {
        return ISMN;
    }

    public void setISMN(String ISMN) {
        this.ISMN = ISMN;
    }

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    public LocalDate getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(LocalDate anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public Medio getMedio() {
        return medio;
    }

    public void setMedio(Medio medio) {
        this.medio = medio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // TO STRING
    @Override
    public void imprimirFicha() {
        System.out.println("=== Ficha del Disco ===");
        System.out.println("Número de registro: " + numRegistro);
        System.out.println("Fecha de adquisición: " + fechaAdquisicion);
        System.out.println("Precio de compra: " + precioCompra);
        System.out.println("Número de ejemplares: " + numEjemplares);
        System.out.println("ISMN: " + ISMN);
        System.out.println("Título: " + titulo);
        System.out.println("Intérprete: " + interprete);
        System.out.println("Estilo: " + estilo);
        System.out.println("Canciones: " + canciones);
        System.out.println("Soporte: " + soporte);
        System.out.println("Año de publicación: " + anioPublicacion);
    }

}
