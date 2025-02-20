package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

	public static Connection con;
	
		// metodo para crear la conexion con la base de datos
		public static void crearConexion() {
			con = null;
			String url = "jdbc:mysql://127.0.0.1:3307/medioteca"; // Añade el puerto 3306
	        String usuario = "root";
	        String contraseña = "";
	        
	        try {
	        	con = DriverManager.getConnection(url, usuario, contraseña);
	        	
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	}
}
