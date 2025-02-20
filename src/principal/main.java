package principal;

import graphic.mainMenu;
import interfaz.MainMenu;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Creamos Conexion
		conexion.ConexionDB.crearConexion();
		
		mainMenu.main();
	    
	    
	}

}
