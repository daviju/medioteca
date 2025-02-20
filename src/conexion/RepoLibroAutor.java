package conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.LibroAutor;
import entidades.Libros;
import entidades.Autor;
import entidades.Medio;

public class RepoLibroAutor {

    // FIND BY IDs
    public static LibroAutor findByIds(String ISBN, int numRegistro, int idAutor) {
        LibroAutor libroAutor = null;
        String sql = "SELECT * FROM libros_has_autor WHERE Libros_ISBN = ? AND Libros_Medio_num_registro = ? AND Autor_idAutor = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, ISBN);
            st.setInt(2, numRegistro);
            st.setInt(3, idAutor);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Libros libro = RepoLibro.findByISBN(ISBN);
                Autor autor = RepoAutor.findById(idAutor);
                Medio medio = RepoMedio.findById(numRegistro);
                
                if (libro != null && autor != null && medio != null) {
                    libroAutor = new LibroAutor(libro, autor, medio);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en findByIds: " + e.getMessage());
        }
        return libroAutor;
    }

    // Find by libro ISBN
    public static Autor findByLibroISBN(String ISBN) {
        String sql = "SELECT * FROM libros_has_autor WHERE Libros_ISBN = ?";
        
        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, ISBN);
            ResultSet rs = st.executeQuery();
    
            if (rs.next()) {
                return RepoAutor.findById(rs.getInt("Autor_idAutor"));
            }
        } catch (SQLException e) {
            System.err.println("Error en findByLibroISBN: " + e.getMessage());
        }
        return null;
    }

    // CREATE
    public static int create(LibroAutor libroAutor) {
        int resultado = 0;
        String sql = "INSERT INTO libros_has_autor (Libros_ISBN, Libros_Medio_num_registro, Autor_idAutor) VALUES (?, ?, ?)";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, libroAutor.getLibro().getISBN());
            st.setInt(2, libroAutor.getMedio().getNumRegistro());
            st.setInt(3, libroAutor.getAutor().getIdAutor());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en create: " + e.getMessage());
        }
        return resultado;
    }

    // MODIFY
    public static int modify(LibroAutor libroAutor, String oldISBN, int oldNumRegistro, int oldAutorId) {
        int resultado = 0;
        String sql = "UPDATE libros_has_autor SET Libros_ISBN = ?, Libros_Medio_num_registro = ?, Autor_idAutor = ? " +
                    "WHERE Libros_ISBN = ? AND Libros_Medio_num_registro = ? AND Autor_idAutor = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, libroAutor.getLibro().getISBN());
            st.setInt(2, libroAutor.getMedio().getNumRegistro());
            st.setInt(3, libroAutor.getAutor().getIdAutor());
            st.setString(4, oldISBN);
            st.setInt(5, oldNumRegistro);
            st.setInt(6, oldAutorId);

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en modify: " + e.getMessage());
        }
        return resultado;
    }

    // FIND ALL
    public static ArrayList<LibroAutor> findAll() {
        ArrayList<LibroAutor> listaLibroAutor = new ArrayList<>();
        String sql = "SELECT * FROM libros_has_autor";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String ISBN = rs.getString("Libros_ISBN");
                int numRegistro = rs.getInt("Libros_Medio_num_registro");
                int autorId = rs.getInt("Autor_idAutor");

                Libros libro = RepoLibro.findByISBN(ISBN);
                Autor autor = RepoAutor.findById(autorId);
                Medio medio = RepoMedio.findById(numRegistro);

                if (libro != null && autor != null && medio != null) {
                    LibroAutor libroAutor = new LibroAutor(libro, autor, medio);
                    listaLibroAutor.add(libroAutor);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en findAll: " + e.getMessage());
        }
        return listaLibroAutor;
    }

    // DELETE
    public static int delete(String ISBN, int numRegistro, int autorId) {
        int resultado = 0;
        String sql = "DELETE FROM libros_has_autor WHERE Libros_ISBN = ? AND Libros_Medio_num_registro = ? AND Autor_idAutor = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, ISBN);
            st.setInt(2, numRegistro);
            st.setInt(3, autorId);

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en delete: " + e.getMessage());
        }
        return resultado;
    }
}
