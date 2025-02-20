package conexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import entidades.*;

public class RepoRevistas {

    // FIND BY ISBN
    public static Revistas findByISBN(String ISBN) {
        Revistas revista = null;

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(
                    "SELECT * FROM revistas WHERE ISBN = ?");
            st.setString(1, ISBN);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String titulo = rs.getString("titulo");
                String tematica = rs.getString("tematica");
                String indice = rs.getString("indice");
                LocalDate anioPublicacion = rs.getDate("anio_publicacion").toLocalDate();
                int numPaginas = rs.getInt("num_paginas");
                int numRegistro = rs.getInt("Medio_num_registro");

                // Obtener el objeto Medio completo
                Medio medio = RepoMedio.findById(numRegistro);

                // Convertir el índice en una lista de artículos
                List<Articulo> articulos = new ArrayList<>();

                if (indice != null && !indice.isEmpty()) {

                    List<Integer> listaIds = Arrays.stream(indice.split(","))
                            .map(Integer::parseInt)
                            .toList();

                    for (int id : listaIds) {
                        Articulo articulo = RepoArticulo.findById(id);

                        if (articulo != null) {
                            articulos.add(articulo);
                        }
                    }
                }

                revista = new Revistas(
                        numRegistro,
                        medio.getFechaAdquisicion(),
                        medio.getPrecioCompra(),
                        medio.getNumEjemplares(),

                        ISBN,
                        titulo,
                        tematica,
                        articulos,
                        anioPublicacion,
                        numPaginas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revista;
    }

 // CREATE
    public static int create(Revistas revista) {
        int resultado = 0;
        String sql = "INSERT INTO revistas (ISBN, titulo, tematica, indice, anio_publicacion, num_paginas, Medio_num_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);

            // Convertir la lista de artículos a un String con sus nombres/IDs
            String articulosIndice = "";
            if (revista.getIndice() != null && !revista.getIndice().isEmpty()) {
                articulosIndice = revista.getIndice().stream()
                        .map(articulo -> String.valueOf(articulo.getNombre()))
                        .collect(Collectors.joining(","));
            }

            st.setString(1, revista.getISBN());
            st.setString(2, revista.getTitulo());
            st.setString(3, revista.getTematica());
            st.setString(4, articulosIndice); // Guardamos los artículos como string separado por comas
            st.setDate(5, revista.getAnioPublicacion() != null ? Date.valueOf(revista.getAnioPublicacion()) : null);
            st.setInt(6, revista.getNumPaginas());
            st.setInt(7, revista.getNumRegistro());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en create: " + e.getMessage());
            e.printStackTrace();
        }
        return resultado;
    }

    // MODIFY
    public static int modify(Revistas revista) {
        int resultado = 0;
        String sql = "UPDATE revistas SET titulo = ?, tematica = ?, indice = ?, anio_publicacion = ?, num_paginas = ? WHERE ISBN = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, revista.getTitulo());
            st.setString(2, revista.getTematica());
            st.setString(3, String.join(",",
                    revista.getIndice().stream().map(a -> String.valueOf(a.getIdArticulo())).toList()));
            st.setDate(4, Date.valueOf(revista.getAnioPublicacion()));
            st.setInt(5, revista.getNumPaginas());
            st.setString(6, revista.getISBN());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // FIND ALL REVISTAS
    public static List<Revistas> findAll() {
        List<Revistas> revistas = new ArrayList<>();

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(
                    "SELECT * FROM revistas");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String ISBN = rs.getString("ISBN");
                String titulo = rs.getString("titulo");
                String tematica = rs.getString("tematica");
                String indice = rs.getString("indice");
                LocalDate anioPublicacion = rs.getDate("anio_publicacion").toLocalDate();
                int numPaginas = rs.getInt("num_paginas");
                int numRegistro = rs.getInt("Medio_num_registro");

                // Obtener el objeto Medio completo
                Medio medio = RepoMedio.findById(numRegistro);

                // Convertir el índice en una lista de artículos
                List<Articulo> articulos = new ArrayList<>();

                if (indice != null && !indice.isEmpty()) {
                    List<Integer> listaIds = Arrays.stream(indice.split(","))
                            .map(Integer::parseInt)
                            .toList();

                    for (int id : listaIds) {
                        Articulo articulo = RepoArticulo.findById(id);

                        if (articulo != null) {
                            articulos.add(articulo);
                        }
                    }
                }

                Revistas revista = new Revistas(
                        numRegistro,
                        medio.getFechaAdquisicion(),
                        medio.getPrecioCompra(),
                        medio.getNumEjemplares(),

                        ISBN,
                        titulo,
                        tematica,
                        articulos,
                        anioPublicacion,
                        numPaginas);

                revistas.add(revista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revistas;
    }
}
