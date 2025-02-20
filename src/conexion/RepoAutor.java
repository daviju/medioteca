package conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Autor;

public class RepoAutor {

    // FIND BY ID
    public static Autor findById(int idAutor) {
        Autor autor = null;
        String sql = "SELECT * FROM autor WHERE idAutor = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setInt(1, idAutor);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                autor = new Autor(
                    rs.getInt("idAutor"),
                    rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error en findById: " + e.getMessage());
        }
        return autor;
    }

    // CREATE
    public static int create(Autor autor) {
        int resultado = 0;
        String sql = "INSERT INTO autor (nombre) VALUES (?)";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, autor.getNombre());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en create: " + e.getMessage());
        }
        return resultado;
    }

    // DELETE
    public static int delete(int idAutor) {
        int resultado = 0;
        String sql = "DELETE FROM autor WHERE idAutor = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setInt(1, idAutor);

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en delete: " + e.getMessage());
        }
        return resultado;
    }

    // FIND ALL
    public static ArrayList<Autor> findAll() {
        ArrayList<Autor> listaAutores = new ArrayList<>();
        String sql = "SELECT * FROM autor";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Autor autor = new Autor(
                    rs.getInt("idAutor"),
                    rs.getString("nombre")
                );
                listaAutores.add(autor);
            }
        } catch (SQLException e) {
            System.err.println("Error en findAll: " + e.getMessage());
        }
        return listaAutores;
    }

    // MODIFY
    public static int modify(Autor autor) {
        int resultado = 0;
        String sql = "UPDATE autor SET nombre = ? WHERE idAutor = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, autor.getNombre());
            st.setInt(2, autor.getIdAutor());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en modify: " + e.getMessage());
        }
        return resultado;
    }
}