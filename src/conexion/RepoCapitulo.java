package conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.*;

public class RepoCapitulo {
    
    // FIND BY LIBRO ISBN
    public static ArrayList<Capitulo> findByLibroISBN(String ISBN) {
        ArrayList<Capitulo> listaCapitulos = new ArrayList<>();
        String sql = "SELECT * FROM capitulo WHERE Libros_ISBN = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, ISBN);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Libros libro = RepoLibro.findByISBN(ISBN);
                Medio medio = RepoMedio.findById(rs.getInt("Libros_Medio_num_registro"));
                
                Capitulo capitulo = new Capitulo(
                    rs.getInt("idCapitulo"),
                    rs.getString("nombre"),
                    libro,
                    medio
                );
                listaCapitulos.add(capitulo);
            }
        } catch (SQLException e) {
            System.err.println("Error en findByLibroISBN: " + e.getMessage());
        }
        return listaCapitulos;
    }

    // FIND BY ID
    public static Capitulo findById(int idCapitulo) {
        Capitulo capitulo = null;
        String sql = "SELECT * FROM capitulo WHERE idCapitulo = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setInt(1, idCapitulo);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String isbnLibro = rs.getString("Libros_ISBN");
                int numRegistro = rs.getInt("Libros_Medio_num_registro");
                
                Libros libro = RepoLibro.findByISBN(isbnLibro);
                Medio medio = RepoMedio.findById(numRegistro);

                capitulo = new Capitulo(idCapitulo, nombre, libro, medio);
            }
        } catch (SQLException e) {
            System.err.println("Error en findById: " + e.getMessage());
        }
        return capitulo;
    }

    // CREATE
    public static int create(Capitulo capitulo) {
        int resultado = 0;
        String sql = "INSERT INTO capitulo (idCapitulo, nombre, Libros_ISBN, Libros_Medio_num_registro) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setInt(1, capitulo.getIdCapitulo());
            st.setString(2, capitulo.getNombre());
            st.setString(3, capitulo.getLibro().getISBN());
            st.setInt(4, capitulo.getMedio().getNumRegistro());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en create: " + e.getMessage());
        }
        return resultado;
    }

    // DELETE
    public static int delete(int idCapitulo) {
        int resultado = 0;
        String sql = "DELETE FROM capitulo WHERE idCapitulo = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setInt(1, idCapitulo);
            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en delete: " + e.getMessage());
        }
        return resultado;
    }

    // FIND ALL
    public static ArrayList<Capitulo> findAll() {
        ArrayList<Capitulo> listaCapitulos = new ArrayList<>();
        String sql = "SELECT * FROM capitulo";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idCapitulo");
                String nombre = rs.getString("nombre");
                String isbnLibro = rs.getString("Libros_ISBN");
                int numRegistro = rs.getInt("Libros_Medio_num_registro");

                Libros libro = RepoLibro.findByISBN(isbnLibro);
                Medio medio = RepoMedio.findById(numRegistro);

                Capitulo capitulo = new Capitulo(id, nombre, libro, medio);
                listaCapitulos.add(capitulo);
            }
        } catch (SQLException e) {
            System.err.println("Error en findAll: " + e.getMessage());
        }
        return listaCapitulos;
    }

    // MODIFY
    public static int modify(Capitulo capitulo) {
        int resultado = 0;
        String sql = "UPDATE capitulo SET nombre = ?, Libros_ISBN = ?, Libros_Medio_num_registro = ? WHERE idCapitulo = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, capitulo.getNombre());
            st.setString(2, capitulo.getLibro().getISBN());
            st.setInt(3, capitulo.getMedio().getNumRegistro());
            st.setInt(4, capitulo.getIdCapitulo());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en modify: " + e.getMessage());
        }
        return resultado;
    }
}