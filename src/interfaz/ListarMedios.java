package interfaz;

import java.util.*;

import conexion.RepoDiscos;
import conexion.RepoLibro;
import conexion.RepoMedio;
import conexion.RepoPelicula;
import conexion.RepoRevistas;
import entidades.Discos;
import entidades.Libros;
import entidades.Medio;
import entidades.Peliculas;
import entidades.Revistas;

public class ListarMedios {

    private static Scanner scanner = new Scanner(System.in);

    // Menú principal para listar medios
    public static void LM() {
        System.out.println("Seleccione el criterio para listar los medios:");

        System.out.println("1. Listar todos los medios");
        System.out.println("2. Listar por tipo de medio (Libro, Película, Disco, Revista)");
        System.out.println("3. Listar por categoría (Temática/Estilo)");
        System.out.println("4. Ordenar por número de registro");
        System.out.println("5. Salir");
        System.out.print("Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                listarTodosMedios();
                break;
            case 2:
                listarPorTipoMedio();
                break;
            case 3:
                listarPorCategoria();
                break;
            case 4:
                ordenarPorNumeroRegistro();
                break;
            case 5:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    // Listar todos los medios
    private static void listarTodosMedios() {
        System.out.println("Listado de todos los medios:");

        List<Medio> medios = RepoMedio.findAll();
        
        if (medios.isEmpty()) {
            System.out.println("No hay medios registrados.");
        
        } else {
            for (Medio medio : medios) {
                medio.imprimirFicha();
            }
        }
    }

    // Listar por tipo de medio
    private static void listarPorTipoMedio() {
        System.out.println("");
    	System.out.println("Seleccione el tipo de medio:");
        System.out.println("1. Libro");
        System.out.println("2. Película");
        System.out.println("3. Disco");
        System.out.println("4. Revista");
        System.out.println("");
        
        System.out.print("Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                listarLibros();
                break;
            case 2:
                listarPeliculas();
                break;
            case 3:
                listarDiscos();
                break;
            case 4:
                listarRevistas();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void listarLibros() {
        List<Libros> libros = RepoLibro.findAll();
        
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        
        } else {
            for (Libros libro : libros) {
                libro.imprimirFicha();
            }
        }
    }

    private static void listarPeliculas() {
        List<Peliculas> peliculas = RepoPelicula.findAll();
        
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas registradas.");
        
        } else {
            for (Peliculas pelicula : peliculas) {
                pelicula.imprimirFicha();
            }
        }
    }

    private static void listarDiscos() {
        List<Discos> discos = RepoDiscos.findAll();
        
        if (discos.isEmpty()) {
            System.out.println("No hay discos registrados.");
        
        } else {
            for (Discos disco : discos) {
                disco.imprimirFicha();
            }
        }
    }

    private static void listarRevistas() {
        List<Revistas> revistas = RepoRevistas.findAll();
        
        if (revistas.isEmpty()) {
            System.out.println("No hay revistas registradas.");
        
        } else {
            for (Revistas revista : revistas) {
                revista.imprimirFicha();
            }
        }
    }

    
    // Listar por categoría
    private static void listarPorCategoria() {
        System.out.print("Ingrese la categoría (temática/estilo): ");
        String categoria = scanner.nextLine();

        // Faltaría implementar esto (hacer despues de arreglar los repositorios)
        /* List<Medio> mediosFiltrados = RepoMedio.findMedioByCategoria(categoria);
        
        if (mediosFiltrados.isEmpty()) {
            System.out.println("No se encontraron medios con esa categoría.");
        
        } else {
            for (Medio medio : mediosFiltrados) {
                medio.imprimirFicha();
            }
        }
        */
    }

    
    // Ordenar por número de registro
    private static void ordenarPorNumeroRegistro() {
        List<Medio> medios = RepoMedio.findAll();

        medios.sort(Comparator.comparingInt(Medio::getNumRegistro));

        if (medios.isEmpty()) {
            System.out.println("No hay medios registrados.");
        
        } else {
            System.out.println("Medios ordenados por número de registro:");
        
            for (Medio medio : medios) {
                medio.imprimirFicha();
            }
        }
    }
}
