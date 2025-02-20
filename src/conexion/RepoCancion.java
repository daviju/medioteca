package conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.*;

public class RepoCancion {

// FIND BY Disco
public static ArrayList<Cancion> findByDiscoISMN(String ISMN) {
    ArrayList<Cancion> listaCanciones = new ArrayList<>();
    String sql = "SELECT * FROM cancion WHERE Discos_ISMN = ?";

    try {
        PreparedStatement st = ConexionDB.con.prepareStatement(sql);
        st.setString(1, ISMN);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            // Obtener el disco y el medio asociado
            Discos disco = RepoDiscos.findByISMN(ISMN);
            Medio medio = RepoMedio.findById(rs.getInt("Discos_Medio_num_registro"));

            Cancion cancion = new Cancion(
                rs.getInt("idCancion"),
                rs.getString("nombre"),
                disco, // Aquí pasamos el objeto disco completo
                rs.getInt("duracionMinutos"),
                medio  // Aquí pasamos el objeto medio completo
            );
            listaCanciones.add(cancion);
        }
    } catch (SQLException e) {
        System.err.println("Error en findByDiscoISMN: " + e.getMessage());
    }
    return listaCanciones;
}

    // CREATE
    public static int create(Cancion cancion) {
        int resultado = 0;
        String sql = "INSERT INTO cancion (idCancion, nombre, duracionMinutos, Discos_ISMN, Discos_Medio_num_registro) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setInt(1, cancion.getIdCancion());
            st.setString(2, cancion.getNombre());
            st.setInt(3, cancion.getDuracionMinutos());
            st.setString(4, cancion.getDisco().getISMN()); // Solo guardamos el ISMN
            st.setInt(5, cancion.getMedio().getNumRegistro()); // Solo guardamos el número de registro

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en create: " + e.getMessage());
        }
        return resultado;
    }

    // DELETE
    public static int delete(int idCancion) {
        int resultado = 0;
        String sql = "DELETE FROM cancion WHERE idCancion = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setInt(1, idCancion);

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en delete: " + e.getMessage());
        }

        return resultado;
    }

    // FIND ALL
    public static ArrayList<Cancion> findAll() {
        ArrayList<Cancion> listaCanciones = new ArrayList<>();
        String sql = "SELECT * FROM cancion";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idCanc = rs.getInt("idCancion");
                String nombre = rs.getString("nombre");

                Discos disco = RepoDiscos.findByISMN(rs.getString("Discos_ISMN"));
                Medio m = RepoMedio.findById(rs.getInt("Discos_Medio_num_registro"));

                int duracionMinutos = rs.getInt("duracionMinutos");

                Cancion cancion = new Cancion(idCanc, nombre, disco, duracionMinutos, m);

                listaCanciones.add(cancion);
            }
        } catch (SQLException e) {
            System.err.println("Error en findAll: " + e.getMessage());
        }

        return listaCanciones;
    }

    // MODIFY
    public static int modify(Cancion cancion) {
        int resultado = 0;
        String sql = "UPDATE cancion SET nombre = ?, duracionMinutos = ?, Discos_ISMN = ?, Discos_Medio_num_registro = ?  WHERE idCancion = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, cancion.getNombre());
            st.setString(2, cancion.getDisco() != null ? cancion.getDisco().getISMN() : null);
            st.setInt(3, cancion.getDuracionMinutos());
            st.setInt(4, cancion.getIdCancion());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en modify: " + e.getMessage());
        }

        return resultado;
    }
}
