package interfaz;

import java.util.Scanner;

public class MainMenu {

    private static Scanner scanner = new Scanner(System.in);

    /*
     * Por ahora llevo las clases con sus repositorios hechos
     * Igualmente tengo que modificarlos porque me di cuenta de que tenía algunos tipos mal, como los IDs que algunos los tenía como string
     * y no me di cuenta hasta luego mas tarde cuando retomé el proyecto para terminar los menús
     * Esto ya está cambiado en la base de datos pero me queda modificarlo aquí en el programa lo que pasa que no me ha dado tiempo con el proyecto del Kebab
     * */

    // Menú y Opciones
    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("MENU DE GESTION DE MEDIOS");
            System.out.println("1. Anadir medios a la coleccion");
            System.out.println("2. Modificar datos de medios");
            System.out.println("3. Borrar medios");
            System.out.println("4. Imprimir ficha");
            System.out.println("5. Listado de medios");
            System.out.println("6. Salir");
    		System.out.println("");
    		
            System.out.print("Selecciona una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpio buffer

            switch (opcion) {
                case 1:
                    añadirMedios();
                    break;
                case 2:
                    modificarMedios();
                    break;
                case 3:
                    borrarMedios();
                    break;
                case 4:
                    imprimirFicha();
                    break;
                case 5:
                	listarMedios();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida. Inténtalo de nuevo.");
            }
        } while (opcion != 5);
    }

    // Añadir medios
    private void añadirMedios() {        
        AñadirMedios.añadir();
        
        System.out.println("Medio añadido correctamente.");
		System.out.println("");
    }

    // Modificar medios
    private void modificarMedios() {
        ModificarMedios.Mod();
        
        System.out.println("Datos del medio modificados correctamente.");
		System.out.println("");
    }

    // Borrar medios
    private void borrarMedios() {
        BorrarMedios.Del();
        
        System.out.println("Medio borrado correctamente.");
		System.out.println("");
    }

    // Imprimir la ficha de un medio
    private void imprimirFicha() {
        ImprimirFicha.Pr();
        
        System.out.println("Ficha impresa correctamente.");
		System.out.println("");
    }
    
    // Listar todos los medios
    private void listarMedios() {
        ListarMedios.LM();
    }
}
