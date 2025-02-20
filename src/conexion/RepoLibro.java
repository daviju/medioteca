package conexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.*;

public class RepoLibro {

    // FIND BY ISBN
    public static Libros findByISBN(String ISBN) {
        Libros libro = null;
        String sql = "SELECT * FROM libros WHERE ISBN = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, ISBN);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Medio medio = RepoMedio.findById(rs.getInt("Medio_num_registro"));

                // Obtener el único autor asociado al libro
                Autor autor = RepoLibroAutor.findByLibroISBN(ISBN);

                // Obtener los capítulos (si los hay)
                List<Capitulo> capitulos = obtenerCapitulos(rs.getString("indice"));

                libro = new Libros(
                        medio.getNumRegistro(),
                        medio.getFechaAdquisicion(),
                        medio.getPrecioCompra(),
                        medio.getNumEjemplares(),

                        ISBN,
                        rs.getString("titulo"),
                        autor, // Pasamos el único autor
                        rs.getString("tematica"),
                        rs.getDate("anio_publicacion") != null ? rs.getDate("anio_publicacion").toLocalDate() : null,
                        rs.getInt("num_paginas"),
                        capitulos);
            }
        } catch (SQLException e) {
            System.err.println("Error en findByISBN: " + e.getMessage());
        }
        return libro;
    }

    // CREATE
    public static int create(Libros libro) {
        int resultado = 0;
        String sql = "INSERT INTO libros (ISBN, titulo, tematica, anio_publicacion, num_paginas, Medio_num_registro) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, libro.getISBN());
            st.setString(2, libro.getTitulo());
            st.setString(3, libro.getTematica());
            st.setDate(4, libro.getAnioPublicacion() != null ? Date.valueOf(libro.getAnioPublicacion()) : null);
            st.setInt(5, libro.getNumPaginas());
            st.setInt(6, libro.getNumRegistro());

            resultado = st.executeUpdate();

            // Insertar en libros_has_autor
            RepoLibroAutor.create(
                    new LibroAutor(
                            libro,
                            libro.getAutor(),
                            RepoMedio.findById(libro.getNumRegistro())));

        } catch (SQLException e) {
            System.err.println("Error en create: " + e.getMessage());
        }
        return resultado;
    }

    // MODIFY
    public static int modify(Libros libro) {
        int resultado = 0;
        String sql = "UPDATE libros SET titulo = ?, tematica = ?, anio_publicacion = ?, num_paginas = ?, indice = ? WHERE ISBN = ? AND Medio_num_registro = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, libro.getTitulo());
            st.setString(2, libro.getTematica());
            st.setDate(3, libro.getAnioPublicacion() != null ? Date.valueOf(libro.getAnioPublicacion()) : null);
            st.setInt(4, libro.getNumPaginas());
            st.setString(5, convertirCapitulosAString(libro.getCapitulos())); // Convertir lista de capítulos a cadena
            st.setString(6, libro.getISBN());
            st.setInt(7, libro.getNumRegistro());

            resultado = st.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error en modify: " + e.getMessage());
        }
        return resultado;
    }

    // FIND ALL
    public static ArrayList<Libros> findAll() {
        ArrayList<Libros> listaLibros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        
        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                String isbn = rs.getString("ISBN"); // Obtener el ISBN del libro actual

                // Obtener el medio asociado al libro actual
                Medio medio = RepoMedio.findById(rs.getInt("Medio_num_registro"));

                // Obtener el autor usando el ISBN del libro actual
                Autor autor = RepoLibroAutor.findByLibroISBN(isbn);

                // Obtener los capítulos (si los hay)
                List<Capitulo> capitulos = obtenerCapitulos(rs.getString("indice"));
                
                Libros libro = new Libros(
                    medio.getNumRegistro(),
                    medio.getFechaAdquisicion(),
                    medio.getPrecioCompra(),
                    medio.getNumEjemplares(),

                    isbn,
                    rs.getString("titulo"),
                    autor,
                    rs.getString("tematica"),
                    rs.getDate("anio_publicacion") != null ? rs.getDate("anio_publicacion").toLocalDate() : null,
                    rs.getInt("num_paginas"),
                    capitulos
                );
                
                listaLibros.add(libro);
            }
        } catch (SQLException e) {
            System.err.println("Error en findAll: " + e.getMessage());
        }
        return listaLibros;
    }

    // DELETE
    public static int delete(String ISBN, int numRegistro) {
        int resultado = 0;
        String sql = "DELETE FROM libros WHERE ISBN = ? AND Medio_num_registro = ?";

        try {
            PreparedStatement st = ConexionDB.con.prepareStatement(sql);
            st.setString(1, ISBN);
            st.setInt(2, numRegistro);

            resultado = st.executeUpdate();

            // Si eliminamos el libro, eliminamos su relación con el autor también
            if (resultado > 0) {
                RepoLibroAutor.delete(ISBN, numRegistro, -1);
            }
        } catch (SQLException e) {
            System.err.println("Error en delete: " + e.getMessage());
        }
        return resultado;
    }

    // Método auxiliar para convertir capítulos a cadena
    private static String convertirCapitulosAString(List<Capitulo> capitulos) {
        if (capitulos == null || capitulos.isEmpty()) { // Si no hay capítulos
            return ""; // Devolver cadena vacía
        }

        return capitulos.stream()
                .map(c -> String.valueOf(c.getIdCapitulo())) // Convertir cada capítulo a su ID
                .reduce((c1, c2) -> c1 + "," + c2) // Unir los IDs con comas
                .orElse(""); // Devolver cadena vacía si no hay capítulos
    }

    // Método auxiliar para obtener capítulos desde una cadena
    private static List<Capitulo> obtenerCapitulos(String capitulosStr) {
        List<Capitulo> capitulos = new ArrayList<>();
        if (capitulosStr != null && !capitulosStr.isEmpty()) {
            String[] ids = capitulosStr.split(",");
            for (String id : ids) {
                Capitulo capitulo = RepoCapitulo.findById(Integer.parseInt(id));
                if (capitulo != null) {
                    capitulos.add(capitulo);
                }
            }
        }
        return capitulos;
    }
}