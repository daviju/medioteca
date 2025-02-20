package interfaz;

import java.util.Scanner;

public class BorrarMedios {
	
	private static Scanner scanner = new Scanner(System.in);

	public static void Del() {
		// TODO Auto-generated constructor stub
		
		System.out.println("");
	    System.out.println("Elija el tipo de medio para eliminar:");
	    System.out.println("1. Libros");
	    System.out.println("2. Peliculas");
	    System.out.println("3. Discos");
	    System.out.println("4. Revistas");
	    System.out.println("5. Borrar por número de registro");
	    System.out.println("6. Salir");
		System.out.println("");

	    System.out.print("Opcion: ");

	    int opcionMedio = scanner.nextInt();
	    scanner.nextLine();

	    switch (opcionMedio) {
	        case 1:
	            delLibro();
	            break;
	        case 2:
	            delPelicula();
	            break;
	        case 3:
	            delDisco();
	            break;
	        case 4:
	        	delRevista();
	        	break;
	        case 5:
	            borrarPorNumRegistro();
	            break;
            case 6:
                System.out.println("Saliendo...");
                break;
	        default:
	            System.out.println("Opcion de medio no valida.");
	    }
	}

	// Borrar cada tipo de medio
	private static void delLibro() {
	    System.out.print("Ingrese el número de registro del libro a eliminar: ");
	    int numRegistro = scanner.nextInt();
	    scanner.nextLine();
	}

	private static void delPelicula() {
	    System.out.print("Ingrese el número de registro de la película a eliminar: ");
	    int numRegistro = scanner.nextInt();
	    scanner.nextLine();
	}

	private static void delDisco() {
	    System.out.print("Ingrese el número de registro del disco a eliminar: ");
	    int numRegistro = scanner.nextInt();
	    scanner.nextLine();
	}	
	
	private static void delRevista() {
	    System.out.print("Ingrese el número de registro de la revista a eliminar: ");
	    int numRegistro = scanner.nextInt();
	    scanner.nextLine();
	}
	
	private static void borrarPorNumRegistro() {
	    System.out.print("Ingrese el número de registro del medio a eliminar: ");
	    int numRegistro = scanner.nextInt();
	    scanner.nextLine(); 
	}

}