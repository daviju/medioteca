package conexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import entidades.Medio;

public class RepoMedio {

    // FIND BY ID
    public static Medio findById(int id) {
        Medio m = null;

        try {
            java.sql.Statement st = ConexionDB.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * "
            							+ "FROM medio "
            							+ "WHERE num_registro='" + id + "';");
            
            if (rs.next()) {
                int numRegistro = rs.getInt("num_registro");
                LocalDate fechaAdquisicion = rs.getDate("fecha_adquisicion").toLocalDate();
                double precioCompra = rs.getDouble("precio_compra");
                int numEjemplares = rs.getInt("num_ejemplares");

                // Crear el objeto Medio
                m = new Medio(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return m;
    }

    
    // CREATE
    public static int create(Medio m) {
        int resultado = 0;

        String sql = "INSERT INTO medio (fecha_adquisicion, precio_compra, num_ejemplares) "
        			+ "VALUES (?, ?, ?);";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            
	            st.setDate(1, Date.valueOf(m.getFechaAdquisicion()));
	            st.setDouble(2, m.getPrecioCompra());
	            st.setInt(3, m.getNumEjemplares());
	
	            resultado = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    
    // DELETE
    public static int deleteById(int id) {
        int resultado = 0;
        String sql = "DELETE FROM medio "
        			+ "WHERE num_registro=?;";

        try {
            
        	PreparedStatement st = ConexionDB.con.prepareStatement(sql);
	            st.setInt(1, id);
	            resultado = st.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    
    // FIND ALL
    public static ArrayList<Medio> findAll() {
        ArrayList<Medio> listaMedios = new ArrayList<>();

        String sql = "SELECT num_registro, fecha_adquisicion, precio_compra, num_ejemplares "
        			+ "FROM medio;";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int numRegistro = rs.getInt("num_registro");
                LocalDate fechaAdquisicion = rs.getDate("fecha_adquisicion").toLocalDate();
                double precioCompra = rs.getDouble("precio_compra");
                int numEjemplares = rs.getInt("num_ejemplares");

                // Crear el objeto Medio
                Medio m = new Medio(numRegistro, fechaAdquisicion, precioCompra, numEjemplares);

                listaMedios.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaMedios;
    }

    
    // MODIFY
    public static int modify(Medio m) {
        int resultado = 0;
        String sql = "UPDATE medio "
        			+ "SET fecha_adquisicion = ?, precio_compra = ?, num_ejemplares = ? "
        			+ "WHERE num_registro = ?;";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            
	            st.setDate(1, Date.valueOf(m.getFechaAdquisicion()));
	            st.setDouble(2, m.getPrecioCompra());
	            st.setInt(3, m.getNumEjemplares());
	            st.setInt(4, m.getNumRegistro());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }
}
