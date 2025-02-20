package metodos;

import entidades.*;
import conexion.*;
import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class MetodosListado {

    // Listado general ordenado por registro
    public static List<Medio> listarMediosOrdenadosPorRegistro() {
        List<Medio> medios = RepoMedio.findAll();
        medios.sort(Comparator.comparingInt(Medio::getNumRegistro));
        return medios;
    }

    // Listado por tipo y categoría
    public static List<Medio> listarMediosOrdenadosPorTipoYCategoria() {
        List<Medio> medios = RepoMedio.findAll();
        medios.sort((m1, m2) -> {
            int compareTipo = getTipoMedio(m1).compareTo(getTipoMedio(m2));
            if (compareTipo != 0) return compareTipo;
            return getCategoria(m1).compareTo(getCategoria(m2));
        });
        return medios;
    }

    // Películas por director
    public static List<Peliculas> listarPeliculasPorDirector(String director) {
        return RepoPelicula.findAll().stream()
               .filter(p -> p.getDirector().equalsIgnoreCase(director))
               .collect(Collectors.toList());
    }

    // Discos por intérprete
    public static List<Discos> listarDiscosPorInterprete(String interprete) {
        return RepoDiscos.findAll().stream()
               .filter(d -> d.getInterprete().equalsIgnoreCase(interprete))
               .collect(Collectors.toList());
    }

    // Libros por autor
    public static List<Libros> listarLibrosPorAutor(String nombreAutor) {
        List<Libros> todosLibros = RepoLibro.findAll();
        List<Libros> librosPorAutor = new ArrayList<>();
        
        for (Libros libro : todosLibros) {
            List<LibroAutor> librosAutores = RepoLibroAutor.findByLibroISBN(libro.getISBN());
            for (LibroAutor la : librosAutores) {
                if (la.getAutor().getNombre().equalsIgnoreCase(nombreAutor)) {
                    librosPorAutor.add(libro);
                    break;
                }
            }
        }
        return librosPorAutor;
    }

    // Listados adicionales útiles
    public static List<Medio> listarMediosPorRangoPrecio(double minPrecio, double maxPrecio) {
        return RepoMedio.findAll().stream()
               .filter(m -> m.getPrecioCompra() >= minPrecio && m.getPrecioCompra() <= maxPrecio)
               .collect(Collectors.toList());
    }

    public static List<Medio> listarMediosPorFechaAdquisicion(LocalDate inicio, LocalDate fin) {
        return RepoMedio.findAll().stream()
               .filter(m -> !m.getFechaAdquisicion().isBefore(inicio) && 
                          !m.getFechaAdquisicion().isAfter(fin))
               .collect(Collectors.toList());
    }

    // Métodos de impresión
    public static void imprimirListadoGeneral(List<Medio> medios) {
        System.out.println("\n=== LISTADO GENERAL DE MEDIOS ===");
        System.out.printf("%-10s %-40s %-12s %-20s %-15s%n", 
            "REGISTRO", "TÍTULO", "TIPO", "CATEGORÍA", "EJEMPLARES");
        System.out.println("=".repeat(97));
        
        for (Medio medio : medios) {
            System.out.printf("%-10d %-40s %-12s %-20s %-15d%n",
                medio.getNumRegistro(),
                getTitulo(medio),
                getTipoMedio(medio),
                getCategoria(medio),
                medio.getNumEjemplares());
        }
        System.out.println("=".repeat(97));
    }

    private static String getTipoMedio(Medio medio) {
        if (medio == null) return "N/A";
        try {
            String className = medio.getClass().getSimpleName();
            switch (className) {
                case "Libros": return "Libro";
                case "Peliculas": return "Película";
                case "Discos": return "Disco";
                case "Revistas": return "Revista";
                default: return "Otro";
            }
        } catch (Exception e) {
            return "N/A";
        }
    }

    private static String getCategoria(Medio medio) {
        if (medio == null) return "N/A";
        try {
            String className = medio.getClass().getSimpleName();
            switch (className) {
                case "Libros":
                    Libros libro = (Libros) medio;
                    return libro.getTematica() != null ? libro.getTematica() : "N/A";
                case "Peliculas":
                    Peliculas pelicula = (Peliculas) medio;
                    return pelicula.getEstilo() != null ? pelicula.getEstilo() : "N/A";
                case "Discos":
                    Discos disco = (Discos) medio;
                    return disco.getEstilo() != null ? disco.getEstilo() : "N/A";
                case "Revistas":
                    Revistas revista = (Revistas) medio;
                    return revista.getTematica() != null ? revista.getTematica() : "N/A";
                default:
                    return "N/A";
            }
        } catch (Exception e) {
            return "N/A";
        }
    }

    private static String getTitulo(Medio medio) {
        if (medio == null) return "Sin título";
        try {
            String className = medio.getClass().getSimpleName();
            switch (className) {
                case "Libros":
                    Libros libro = (Libros) medio;
                    return libro.getTitulo() != null ? libro.getTitulo() : "Sin título";
                case "Peliculas":
                    Peliculas pelicula = (Peliculas) medio;
                    return pelicula.getTitulo() != null ? pelicula.getTitulo() : "Sin título";
                case "Discos":
                    Discos disco = (Discos) medio;
                    return disco.getTitulo() != null ? disco.getTitulo() : "Sin título";
                case "Revistas":
                    Revistas revista = (Revistas) medio;
                    return revista.getTitulo() != null ? revista.getTitulo() : "Sin título";
                default:
                    return "Sin título";
            }
        } catch (Exception e) {
            return "Sin título";
        }
    }
}