package conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.*;

public class RepoArticulo {

    // FIND BY ID
    public static Articulo findById(int idArticulo) {
        Articulo articulo = null;

        try {
            java.sql.Statement st = ConexionDB.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM articulo WHERE idArticulo=" + idArticulo + ";");
            
            if (rs.next()) {
                int idArti = rs.getInt("idArticulo");
                String nombre = rs.getString("nombre");
                String Revistas_ISBN = rs.getString("Revistas_ISBN");
                int Revistas_Medio_num_registro = rs.getInt("Revistas_Medio_num_registro");

                Medio m = RepoMedio.findById(Revistas_Medio_num_registro);
                Revistas revista = RepoRevistas.findByISBN(Revistas_ISBN);

                articulo = new Articulo(idArti, nombre, revista, m);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articulo;
    }

    // CREATE
    public static int create(Articulo articulo) {
        int resultado = 0;
        String sql = "INSERT INTO articulo (idArticulo, nombre, Revistas_ISBN, Revistas_Medio_num_registro) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setInt(1, articulo.getIdArticulo());
            st.setString(2, articulo.getNombre());
            st.setString(3, articulo.getRevista() != null ? articulo.getRevista().getISBN() : null);
            st.setInt(4, articulo.getMedio().getNumRegistro());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    // DELETE
    public static int delete(int idArticulo) {
        int resultado = 0;
        String sql = "DELETE FROM articulo WHERE idArticulo=?;";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setInt(1, idArticulo);

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    // FIND ALL
    public static ArrayList<Articulo> findAll() {
        ArrayList<Articulo> listaArticulos = new ArrayList<>();
        String sql = "SELECT * FROM articulo;";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Articulo articulo = new Articulo(
                        rs.getInt("idArticulo"),
                        rs.getString("nombre"),
                        RepoRevistas.findByISBN(rs.getString("Revistas_ISBN")),
                        RepoMedio.findById(rs.getInt("Revistas_Medio_num_registro")));

                listaArticulos.add(articulo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaArticulos;
    }

    // MODIFY
    public static int modify(Articulo articulo) {
        int resultado = 0;
        String sql = "UPDATE articulo SET nombre = ?, Revistas_ISBN = ?, Revistas_Medio_num_registro = ? WHERE idArticulo = ?;";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, articulo.getNombre());
            st.setString(2, articulo.getRevista() != null ? articulo.getRevista().getISBN() : null);
            st.setInt(3, articulo.getMedio().getNumRegistro());
            st.setInt(4, articulo.getIdArticulo());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }
}
