package conexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import entidades.*;

public class RepoPelicula {

    // FIND BY ISAN
    public static Peliculas findByISAN(String ISAN) {
        Peliculas pelicula = null;
        String sql = "SELECT * FROM peliculas WHERE ISAN = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, ISAN);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // Obtener el medio asociado
                Medio medio = RepoMedio.findById(rs.getInt("Medio_num_registro"));

                // Convertir los idProta en una lista de protagonistas
                List<Protagonista> protagonistas = new ArrayList<>();
                String protagonistasStr = rs.getString("protagonistas");

                if (protagonistasStr != null && !protagonistasStr.isEmpty()) {
                    List<Integer> listaIdProta = Arrays.stream(protagonistasStr.split(","))
                            .map(Integer::parseInt)
                            .toList();

                    for (int idProta : listaIdProta) {
                        Protagonista protagonista = RepoProtagonista.findById(idProta);
                        
                        if (protagonista != null) {
                            protagonistas.add(protagonista);
                        }
                    }
                }

                pelicula = new Peliculas(
                    medio.getNumRegistro(),
                    medio.getFechaAdquisicion(),
                    medio.getPrecioCompra(),
                    medio.getNumEjemplares(),
                    ISAN,
                    rs.getString("titulo"),
                    rs.getString("director"),
                    protagonistas, // Lista de objetos Protagonista
                    rs.getString("estilo"),
                    rs.getString("soporte"),
                    rs.getInt("duracion"),
                    rs.getDate("anio_publicacion") != null ? 
                        rs.getDate("anio_publicacion").toLocalDate() : null
                );
            }
        } catch (SQLException e) {
            System.err.println("Error en findByISAN: " + e.getMessage());
        }
        return pelicula;
    }

    // CREATE
    public static int create(Peliculas pelicula) {
        int resultado = 0;
        String sql = "INSERT INTO peliculas (ISAN, titulo, director, protagonistas, estilo, soporte, duracion, anio_publicacion, Medio_num_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);

            // Convertir la lista de protagonistas a un String con sus IDs
            String protagonistasIds = "";
            
            // Agregar los IDs de los protagonistas a la lista
            if (pelicula.getProtagonistas() != null && !pelicula.getProtagonistas().isEmpty()) {
                protagonistasIds = pelicula.getProtagonistas().stream()
                        .map(protagonista -> String.valueOf(protagonista.getIdProta()))
                        .collect(Collectors.joining(","));
            }

            st.setString(1, pelicula.getISAN());
            st.setString(2, pelicula.getTitulo());
            st.setString(3, pelicula.getDirector());
            st.setString(4, protagonistasIds); // Guardamos los IDs separados por comas
            st.setString(5, pelicula.getEstilo());
            st.setString(6, pelicula.getSoporte());
            st.setInt(7, pelicula.getDuracion());
            st.setDate(8, pelicula.getAnioPublicacion() != null ? Date.valueOf(pelicula.getAnioPublicacion()) : null);
            st.setInt(9, pelicula.getNumRegistro());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en create: " + e.getMessage());
        }

        return resultado;
    }

    // MODIFY
    public static int modify(Peliculas pelicula) {
        int resultado = 0;
        String sql = "UPDATE peliculas SET titulo = ?, director = ?, protagonistas = ?, estilo = ?, soporte = ?, duracion = ?, anio_publicacion = ? WHERE ISAN = ? AND Medio_num_registro = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, pelicula.getTitulo());
            st.setString(2, pelicula.getDirector());
            st.setString(3, String.join(",", pelicula.getProtagonistas().stream()
                    .map(p -> String.valueOf(p.getIdProta()))
                    .toList())); // Convertir lista de protagonistas a cadena de idProta
            st.setString(4, pelicula.getEstilo());
            st.setString(5, pelicula.getSoporte());
            st.setInt(6, pelicula.getDuracion());
            st.setDate(7, pelicula.getAnioPublicacion() != null ? 
                Date.valueOf(pelicula.getAnioPublicacion()) : null);
            st.setString(8, pelicula.getISAN());
            st.setInt(9, pelicula.getNumRegistro());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en modify: " + e.getMessage());
        }
        return resultado;
    }

    // FIND ALL
    public static ArrayList<Peliculas> findAll() {
        ArrayList<Peliculas> listaPeliculas = new ArrayList<>();
        String sql = "SELECT * FROM peliculas";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Medio medio = RepoMedio.findById(rs.getInt("Medio_num_registro"));

                // Convertir los idProta en una lista de protagonistas
                List<Protagonista> protagonistas = new ArrayList<>();
                String protagonistasStr = rs.getString("protagonistas");

                if (protagonistasStr != null && !protagonistasStr.isEmpty()) {
                    List<Integer> listaIdProta = Arrays.stream(protagonistasStr.split(","))
                            .map(Integer::parseInt)
                            .toList();

                    for (int idProta : listaIdProta) {
                        Protagonista protagonista = RepoProtagonista.findById(idProta);
                        if (protagonista != null) {
                            protagonistas.add(protagonista);
                        }
                    }
                }

                Peliculas pelicula = new Peliculas(
                    medio.getNumRegistro(),
                    medio.getFechaAdquisicion(),
                    medio.getPrecioCompra(),
                    medio.getNumEjemplares(),
                    rs.getString("ISAN"),
                    rs.getString("titulo"),
                    rs.getString("director"),
                    protagonistas, // Lista de objetos Protagonista
                    rs.getString("estilo"),
                    rs.getString("soporte"),
                    rs.getInt("duracion"),
                    rs.getDate("anio_publicacion") != null ? 
                        rs.getDate("anio_publicacion").toLocalDate() : null
                );

                listaPeliculas.add(pelicula);
            }
        } catch (SQLException e) {
            System.err.println("Error en findAll: " + e.getMessage());
        }
        return listaPeliculas;
    }

    // DELETE
    public static int delete(String ISAN, int numRegistro) {
        int resultado = 0;
        String sql = "DELETE FROM peliculas WHERE ISAN = ? AND Medio_num_registro = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, ISAN);
            st.setInt(2, numRegistro);

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en delete: " + e.getMessage());
        }
        return resultado;
    }
}