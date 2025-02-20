package conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entidades.*;

public class RepoProtagonista {

    // FIND BY ID
    public static Protagonista findById(int idProta) {
        Protagonista protagonista = null;
        String sql = "SELECT * FROM protagonista WHERE idProta = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setInt(1, idProta);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String peliculasStr = rs.getString("Peliculas_ISAN");
                int numRegistro = rs.getInt("Peliculas_Medio_num_registro");
                Medio medio = RepoMedio.findById(numRegistro);

                // Convertir los ISAN en una lista de películas
                List<Peliculas> peliculas = new ArrayList<>();

                if (peliculasStr != null && !peliculasStr.isEmpty()) {
                    List<String> listaIsan = Arrays.asList(peliculasStr.split(","));

                    for (String isan : listaIsan) {
                        Peliculas pelicula = RepoPelicula.findByISAN(isan);

                        if (pelicula != null) {
                            peliculas.add(pelicula);
                        }
                    }
                }

                protagonista = new Protagonista(idProta, nombre, peliculas, medio);
            }
        } catch (SQLException e) {
            System.err.println("Error en findById: " + e.getMessage());
        }
        return protagonista;
    }

    // CREATE
    public static int create(Protagonista protagonista) {
        int resultado = 0;
        String sql = "INSERT INTO protagonista (nombre, Peliculas_ISAN, Peliculas_Medio_num_registro) VALUES (?, ?, ?)";

        try (PreparedStatement st = ConexionDB.con.prepareStatement(sql)) {
            st.setString(1, protagonista.getNombre());

            // Insertamos NULL para ambos campos (Peliculas_ISAN y Peliculas_Medio_num_registro)
            st.setNull(2, java.sql.Types.VARCHAR); // Peliculas_ISAN
            st.setNull(3, java.sql.Types.INTEGER); // Peliculas_Medio_num_registro

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en create: " + e.getMessage());
        }
        return resultado;
    }


    // MODIFY
    public static int modify(Protagonista protagonista) {
        int resultado = 0;
        String sql = "UPDATE protagonista SET nombre = ?, Peliculas_ISAN = ? WHERE idProta = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, protagonista.getNombre());
            st.setString(2, String.join(",", 
                    protagonista.getPeliculas()
                                .stream()
                                .map(
                                    p -> p.getISAN()).toList()
                                ));
            st.setInt(3, protagonista.getIdProta());

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en modify: " + e.getMessage());
        }
        return resultado;
    }

    // FIND ALL PROTAGONISTAS
    public static List<Protagonista> findAll() {
        List<Protagonista> protagonistas = new ArrayList<>();
        String sql = "SELECT * FROM protagonista";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idProta = rs.getInt("idProta");
                String nombre = rs.getString("nombre");
                String peliculasStr = rs.getString("Peliculas_ISAN");
                int numRegistro = rs.getInt("Peliculas_Medio_num_registro");
                Medio medio = RepoMedio.findById(numRegistro);

                // Convertir los ISAN en una lista de películas
                List<Peliculas> peliculas = new ArrayList<>();

                if (peliculasStr != null && !peliculasStr.isEmpty()) {
                    List<String> listaIsan = Arrays.asList(peliculasStr.split(","));

                    for (String isan : listaIsan) {
                        Peliculas pelicula = RepoPelicula.findByISAN(isan);

                        if (pelicula != null) {
                            peliculas.add(pelicula);
                        }
                    }
                }

                // Crear el objeto Protagonista y añadirlo a la lista
                Protagonista protagonista = new Protagonista(idProta, nombre, peliculas, medio);
                protagonistas.add(protagonista);
            }
        } catch (SQLException e) {
            System.err.println("Error en findAll: " + e.getMessage());
        }
        return protagonistas;
    }

    // DELETE
    public static int delete(int idProta, String ISAN, int numRegistro) {
        int resultado = 0;
        String sql = "DELETE FROM protagonista WHERE idProta = ? AND Peliculas_ISAN = ? AND Peliculas_Medio_num_registro = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setInt(1, idProta);
            st.setString(2, ISAN);
            st.setInt(3, numRegistro);

            resultado = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en delete: " + e.getMessage());
        }
        return resultado;
    }
}

