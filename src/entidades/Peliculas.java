package entidades;

import java.time.LocalDate;
import java.util.List;

public class Peliculas extends Medio {
    private String ISAN;
    private String titulo;
    private String director;
    private List<Protagonista> protagonistas;
    private String estilo;
    private String soporte;
    private int duracion;
    private LocalDate anioPublicacion;

    // CONSTRUCTOR COMPLETO (CON ISAN)
    public Peliculas(int numRegistro, LocalDate fechaAdquisicion, double precioCompra, int numEjemplares,
                    String ISAN, String titulo, String director, List<Protagonista> protagonistas, 
                    String estilo, String soporte, int duracion, LocalDate anioPublicacion) {
        super(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);
        this.setISAN(ISAN);
        this.setTitulo(titulo);
        this.setDirector(director);
        this.setProtagonistas(protagonistas);
        this.setEstilo(estilo);
        this.setSoporte(soporte);
        this.setDuracion(duracion);
        this.setAnioPublicacion(anioPublicacion);
    }

    // CONSTRUCTOR SIN ISAN
    public Peliculas(int numRegistro, LocalDate fechaAdquisicion, double precioCompra, int numEjemplares,
                    String titulo, String director, List<Protagonista> protagonistas, 
                    String estilo, String soporte, int duracion, LocalDate anioPublicacion) {
        super(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);
        this.setTitulo(titulo);
        this.setDirector(director);
        this.setProtagonistas(protagonistas);
        this.setEstilo(estilo);
        this.setSoporte(soporte);
        this.setDuracion(duracion);
        this.setAnioPublicacion(anioPublicacion);
    }

    // GETTERS AND SETTERS
    public String getISAN() {
        return ISAN;
    }

    public void setISAN(String ISAN) {
        this.ISAN = ISAN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Protagonista> getProtagonistas() {
        return protagonistas;
    }

    public void setProtagonistas(List<Protagonista> protagonistas) {
        this.protagonistas = protagonistas;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public LocalDate getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(LocalDate anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    @Override
    public void imprimirFicha() {
        System.out.println("=== FICHA DE LA PELÍCULA ===");
        System.out.println("Número de registro: " + numRegistro);
        System.out.println("Fecha de adquisición: " + fechaAdquisicion);
        System.out.println("Precio de compra: " + precioCompra + "€");
        System.out.println("Número de ejemplares: " + numEjemplares);
        System.out.println("ISAN: " + ISAN);
        System.out.println("Título: " + titulo);
        System.out.println("Director: " + director);
        System.out.println("Protagonistas: " + protagonistas);
        System.out.println("Estilo: " + estilo);
        System.out.println("Soporte: " + soporte);
        System.out.println("Duración: " + duracion + " minutos");
        System.out.println("Año de publicación: " + anioPublicacion);
        System.out.println("========================");
    }
}
