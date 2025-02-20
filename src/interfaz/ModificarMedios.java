package interfaz;

import java.time.LocalDate;
import java.util.Scanner;

public class ModificarMedios {

	private static Scanner scanner = new Scanner(System.in);

	
	public static void Mod() {
		System.out.println("");
		System.out.println("Elija el tipo de medio para modificar:");
	    System.out.println("1. Libros");
	    System.out.println("2. Peliculas");
	    System.out.println("3. Discos");
	    System.out.println("4. Revistas");
		System.out.println("5. Salir");
		System.out.println("");

	    System.out.print("Opcion: ");

	    int opcionMedio = scanner.nextInt();
	    scanner.nextLine();

	    switch (opcionMedio) {
	        case 1:
	            modLibro();
	            break;
	        case 2:
	            modPelicula();
	            break;
	        case 3:
	            modDisco();
	            break;
	        case 4:
	        	modRevista();
	        	break;
            case 5:
                System.out.println("Saliendo...");
                break;
	        default:
	            System.out.println("Opción de medio no válida.");
	    }
	}

	// Modificar cada tipo de medio
	private static void modLibro() {
	    System.out.println("Ingrese los datos del medio:");
	 
	    System.out.print("Número de registro: ");
	    int numRegistro = scanner.nextInt();
	    scanner.nextLine();

	    System.out.print("Fecha de adquisición (formato YYYY-MM-DD): ");
	    String fechaAdquisicionStr = scanner.nextLine();

	    System.out.print("Precio de compra: ");
	    double precioCompra = scanner.nextDouble();

	    System.out.print("Número de ejemplares: ");
	    int numEjemplares = scanner.nextInt();
	    scanner.nextLine(); 
	    
	    // Crear Medio

	    System.out.println("");
	    System.out.println("Ingrese los datos del libro:");

	    System.out.print("ISBN: ");
	    int isbn = scanner.nextInt();
	    scanner.nextLine();

	    System.out.print("Título del libro: ");
	    String titulo = scanner.nextLine();

	    System.out.print("Autor del libro: ");
	    String autor = scanner.nextLine();

	    System.out.print("Temática del libro: ");
	    String tematica = scanner.nextLine();

	    System.out.print("Año de publicación: ");
	    String anioPublicacion = scanner.nextLine();

	    System.out.print("Número de páginas: ");
	    int numPaginas = scanner.nextInt();
	    scanner.nextLine();

	    System.out.print("Índice (separar los capítulos con coma): ");
	    String indice = scanner.nextLine();

	    // Convertir string a localdate para el constructor
	    LocalDate fechaAdquisicion = LocalDate.parse(fechaAdquisicionStr);
	    
	    System.out.println("Libro modificada correctamente.");
	}

	private static void modPelicula() {
	    System.out.println("Ingrese los datos del medio:");

	    System.out.print("Número de registro: ");
	    int numRegistro = scanner.nextInt();
	    scanner.nextLine();

	    System.out.print("Fecha de adquisición (formato YYYY-MM-DD): ");
	    String fechaAdquisicionStr = scanner.nextLine();

	    System.out.print("Precio de compra: ");
	    double precioCompra = scanner.nextDouble();

	    System.out.print("Número de ejemplares: ");
	    int numEjemplares = scanner.nextInt();
	    scanner.nextLine();

	    // Crear el objeto Medio aquí

	    System.out.println("");
	    System.out.println("Ingrese los datos de la película:");

	    System.out.print("ISAN: ");
	    int isan = scanner.nextInt();
	    scanner.nextLine();

	    System.out.print("Título de la película: ");
	    String titulo = scanner.nextLine();

	    System.out.print("Director de la película: ");
	    String director = scanner.nextLine();

	    System.out.print("Protagonistas: ");
	    String protagonistas = scanner.nextLine();

	    System.out.print("Estilo de la película: ");
	    String estilo = scanner.nextLine();

	    System.out.print("Soporte (Físico o Digital): ");
	    String soporte = scanner.nextLine();

	    System.out.print("Duración de la película (en minutos): ");
	    int duracion = scanner.nextInt();
	    scanner.nextLine();
	    
	    System.out.print("Año de publicación: ");
	    String anioPublicacion = scanner.nextLine();

	    // Convertir la fecha a LocalDate
	    LocalDate fechaAdquisicion = LocalDate.parse(fechaAdquisicionStr);


	    System.out.println("Película modificada correctamente.");
	}


	private static void modDisco() {
	    System.out.println("Ingrese los datos del medio:");

	    System.out.print("Número de registro: ");
	    int numRegistro = scanner.nextInt();
	    scanner.nextLine();

	    System.out.print("Fecha de adquisición (formato YYYY-MM-DD): ");
	    String fechaAdquisicionStr = scanner.nextLine();

	    System.out.print("Precio de compra: ");
	    double precioCompra = scanner.nextDouble();

	    System.out.print("Número de ejemplares: ");
	    int numEjemplares = scanner.nextInt();
	    scanner.nextLine();

	    // Crear el objeto Medio aquí

	    System.out.println("");
	    System.out.println("Ingrese los datos del disco:");

	    System.out.print("ISMN: ");
	    int ismn = scanner.nextInt();
	    scanner.nextLine();

	    System.out.print("Título del disco: ");
	    String titulo = scanner.nextLine();

	    System.out.print("Intérprete del disco: ");
	    String interprete = scanner.nextLine();

	    System.out.print("Estilo del disco: ");
	    String estilo = scanner.nextLine();

	    System.out.print("Canciones: ");
	    String canciones = scanner.nextLine();

	    System.out.print("Soporte (Físico o Digital): ");
	    String soporte = scanner.nextLine();

	    System.out.print("Año de publicación: ");
	    String anioPublicacion = scanner.nextLine();

	    // Convertir la fecha a LocalDate
	    LocalDate fechaAdquisicion = LocalDate.parse(fechaAdquisicionStr);

	    System.out.println("Disco modificada correctamente.");
	}

	
	private static void modRevista() {
	    System.out.println("Ingrese los datos del medio:");

	    System.out.print("Número de registro: ");
	    int numRegistro = scanner.nextInt();
	    scanner.nextLine();

	    System.out.print("Fecha de adquisición (formato YYYY-MM-DD): ");
	    String fechaAdquisicionStr = scanner.nextLine();

	    System.out.print("Precio de compra: ");
	    double precioCompra = scanner.nextDouble();

	    System.out.print("Número de ejemplares: ");
	    int numEjemplares = scanner.nextInt();
	    scanner.nextLine();

	    // Crear el objeto Medio aquí

	    System.out.println("");
	    System.out.println("Ingrese los datos de la revista:");

	    System.out.print("ISBN: ");
	    int isbn = scanner.nextInt();
	    scanner.nextLine();

	    System.out.print("Título de la revista: ");
	    String titulo = scanner.nextLine();

	    System.out.print("Temática de la revista: ");
	    String tematica = scanner.nextLine();

	    System.out.print("Año de publicación: ");
	    String anioPublicacion = scanner.nextLine();

	    System.out.print("Número de páginas: ");
	    int numPaginas = scanner.nextInt();
	    scanner.nextLine();

	    System.out.print("Índice: ");
	    String indice = scanner.nextLine();

	    // Convertir la fecha a LocalDate
	    LocalDate fechaAdquisicion = LocalDate.parse(fechaAdquisicionStr);


	    System.out.println("Revista modificada correctamente.");
	}

}
