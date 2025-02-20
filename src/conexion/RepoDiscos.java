package conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import entidades.*;

public class RepoDiscos {

    // FIND BY ISMN
    public static Discos findByISMN(String ISMN) {
        Discos disco = null;
        String sql = "SELECT * FROM discos WHERE ISMN = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, ISMN);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // Obtener el medio asociado
                Medio medio = RepoMedio.findById(rs.getInt("Medio_num_registro"));

                // Primero creamos el disco sin canciones
                disco = new Discos(
                        medio.getNumRegistro(),
                        medio.getFechaAdquisicion(),
                        medio.getPrecioCompra(),
                        medio.getNumEjemplares(),
                        ISMN,
                        rs.getString("titulo"),
                        rs.getString("interprete"),
                        rs.getString("estilo"),
                        rs.getString("soporte"),
                        rs.getDate("anio_publicacion") != null ? rs.getDate("anio_publicacion").toLocalDate() : null);

                // Obtenemos las canciones asociadas usando el ISMN
                ArrayList<Cancion> canciones = RepoCancion.findByDiscoISMN(ISMN);
                disco.setCanciones(canciones);
            }
        } catch (SQLException e) {
            System.err.println("Error en findByISMN: " + e.getMessage());
        }
        return disco;
    }

    // CREATE
    public static int create(Discos disco) {
        int resultado = 0;
        String sql = "INSERT INTO discos (ISMN, titulo, interprete, estilo, soporte, " +
                "anio_publicacion, Medio_num_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, disco.getISMN());
            st.setString(2, disco.getTitulo());
            st.setString(3, disco.getInterprete());
            st.setString(4, disco.getEstilo());
            st.setString(5, disco.getSoporte());
            st.setDate(6, disco.getAnioPublicacion() != null ? Date.valueOf(disco.getAnioPublicacion()) : null);
            st.setInt(7, disco.getNumRegistro());

            resultado = st.executeUpdate();

            // Las canciones se añadirán después con el JSwing
        } catch (SQLException e) {
            System.err.println("Error en create: " + e.getMessage());
        }
        return resultado;
    }

    // MODIFY
    public static int modify(Discos disco) {
        int resultado = 0;
        String sql = "UPDATE discos SET interprete = ?, estilo = ?, soporte = ?, canciones = ?, anio_publicacion = ? WHERE ISMN = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, disco.getInterprete());
            st.setString(2, disco.getEstilo());
            st.setString(3, disco.getSoporte());
            st.setString(4, String.join(",",
                    disco.getCanciones().stream().map(c -> String.valueOf(c.getIdCancion())).toList()));
            st.setDate(5, disco.getAnioPublicacion() != null ? Date.valueOf(disco.getAnioPublicacion()) : null);
            st.setString(6, disco.getISMN());
            st.setInt(7, disco.getNumRegistro());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en modify: " + e.getMessage());
        }
        return resultado;
    }

    // FIND ALL
    public static ArrayList<Discos> findAll() {
        ArrayList<Discos> listaDiscos = new ArrayList<>();
        String sql = "SELECT * FROM discos";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                // Obtener el medio asociado
                Medio medio = RepoMedio.findById(rs.getInt("Medio_num_registro"));
                String ISMN = rs.getString("ISMN");

                // Crear el disco sin canciones
                Discos disco = new Discos(
                        medio.getNumRegistro(),
                        medio.getFechaAdquisicion(),
                        medio.getPrecioCompra(),
                        medio.getNumEjemplares(),
                        ISMN,
                        rs.getString("titulo"),
                        rs.getString("interprete"),
                        rs.getString("estilo"),
                        rs.getString("soporte"),
                        rs.getDate("anio_publicacion") != null ? rs.getDate("anio_publicacion").toLocalDate() : null);

                // Obtener y asignar las canciones
                ArrayList<Cancion> canciones = RepoCancion.findByDiscoISMN(ISMN);
                disco.setCanciones(canciones);

                listaDiscos.add(disco);
            }
        } catch (SQLException e) {
            System.err.println("Error en findAll: " + e.getMessage());
        }
        return listaDiscos;
    }

    // DELETE
    public static int delete(String ISMN, int numRegistro) {
        int resultado = 0;

        try {
            // 1. Eliminar primero las canciones asociadas
            String sqlCanciones = "DELETE FROM cancion WHERE Disco_ISMN = ?";
            PreparedStatement stCanciones = ConexionDB.con.prepareStatement(sqlCanciones);
            stCanciones.setString(1, ISMN);
            stCanciones.executeUpdate();

            // 2. Eliminar el disco
            String sqlDisco = "DELETE FROM discos WHERE ISMN = ? AND Medio_num_registro = ?";
            PreparedStatement stDisco = ConexionDB.con.prepareStatement(sqlDisco);
            stDisco.setString(1, ISMN);
            stDisco.setInt(2, numRegistro);

            resultado = stDisco.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en delete: " + e.getMessage());
        }
        return resultado;
    }
}