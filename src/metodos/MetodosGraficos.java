package metodos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JYearChooser;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;

import graphic.*;
import entidades.*;
import conexion.*;

public class MetodosGraficos {
    
	
	// BUSCAR MEDIO
    public static void buscarMedio(JComboBox<String> comboBox, JTextField textField) {
        String seleccion = comboBox.getSelectedItem().toString();
        String valor = textField.getText().trim();
        
        if (valor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Introduzca un valor para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        switch (seleccion) {
            case "Nº de registro":
                buscarPorNumeroRegistro(valor);
                break;
            case "ISBN (Libro)":
                new ModLibro().setVisible(true);
                break;
            case "ISBN (Revista)":
                new ModRevista().setVisible(true);
                break;
            case "ISMN":
                new ModDisco().setVisible(true);
                break;
            case "ISAN":
                new ModPelicula().setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Selección no válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void buscarPorNumeroRegistro(String numeroRegistro) {
        String[] tablas = {"discos", "libros", "revistas", "peliculas"};
        
        try {
            for (String tabla : tablas) { 
                String consulta = "SELECT * FROM " + tabla + " WHERE Medio_num_registro = ?";
                try (PreparedStatement ps = ConexionDB.con.prepareStatement(consulta)) {
                    ps.setString(1, numeroRegistro);
                    ResultSet rs = ps.executeQuery();
                    
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Medio encontrado en " + tabla + "!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "No se encontró el medio con el Nº de registro ingresado.", "Resultado", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    
	// METODOS IMPRIMIR
	public static void imprimirActores(JTable tabla) {
	    ArrayList<Protagonista> actores = (ArrayList<Protagonista>) RepoProtagonista.findAll();
	    
	    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
	    
	    model.setRowCount(0);
	    
	    for (Protagonista a : actores) {
	        Object[] fila = {
	            a.getIdProta(),
	            a.getNombre()
	        };
	        
	        model.addRow(fila);
	    }
	}
	
	// Imprimir
	public static void imprimirMedios(JTable tabla) {
	    ArrayList<Medio> medios = RepoMedio.findAll();
	    
	    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
	    
	    model.setRowCount(0);
	    
	    for (Medio m : medios) {
	        Object[] fila = {
	        	m.getNumRegistro(),
	        	m.getFechaAdquisicion(),
	        	m.getPrecioCompra(),
	        	m.getNumEjemplares()
	        };
	        model.addRow(fila);
	    }

	}
	
	// IMPRIMIR
    public static void rellenarTablaMedios(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        
        ArrayList<Medio> medios = RepoMedio.findAll();
        
        for (Medio medio : medios) {
            Object[] fila = {
                medio.getNumRegistro(),
                medio.getFechaAdquisicion(),
                medio.getPrecioCompra(),
                medio.getNumEjemplares()
            };
            modelo.addRow(fila);
        }
    }
    
    // IMPRIMIR PELICULAS
    public static void rellenarTablaPeliculas(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); // Limpiar tabla
        
        ArrayList<Peliculas> peliculas = RepoPelicula.findAll();
        
        for (Peliculas pelicula : peliculas) {
            // Obtener los nombres de los protagonistas de la lista que ya tiene la película
            String nombresProtagonistas = pelicula.getProtagonistas().stream()
                .map(Protagonista::getNombre)
                .collect(Collectors.joining(", "));
            
            Object[] fila = {
                pelicula.getISAN(),
                pelicula.getTitulo(),
                pelicula.getDirector(),
                nombresProtagonistas,
                pelicula.getEstilo(),
                pelicula.getSoporte(),
                pelicula.getDuracion(),
                pelicula.getAnioPublicacion(),
                pelicula.getNumRegistro()
            };
            modelo.addRow(fila);
        }
    }
    
    // IMPRIMIR DISCOS
    public static void rellenarTablaDiscos(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        
        ArrayList<Discos> discos = RepoDiscos.findAll();
        
        for (Discos disco : discos) {
            // Obtener las canciones y concatenar sus nombres
            ArrayList<Cancion> canciones = RepoCancion.findByDiscoISMN(disco.getISMN());
            String nombresCanciones = canciones.stream()
                .map(Cancion::getNombre)
                .collect(Collectors.joining(", "));
            
            Object[] fila = {
                disco.getISMN(),
                disco.getTitulo(),
                disco.getInterprete(),
                disco.getEstilo(),
                disco.getSoporte(),
                disco.getAnioPublicacion(),
                nombresCanciones,
                disco.getNumRegistro()
            };
            modelo.addRow(fila);
        }
    }
	
	
	// Devuelve Medio en Principales
	public static Medio devuelveMedio(JTable tabla) {
		int filaSeleccionada = tabla.getSelectedRow();
		
		TableModel modelo = tabla.getModel();
		
		int numRegistro = (int) modelo.getValueAt(filaSeleccionada, 0);
		
		Medio m = RepoMedio.findById(numRegistro);
		
		return m;
	}
	
	
	// Devuelve Pelicula seleccionada
	public static Peliculas devuelvePelicula(JTable tabla) {
	    int filaSeleccionada = tabla.getSelectedRow();
	    
	    if (filaSeleccionada == -1) {
	        return null;
	    }
	    
	    try {
	        TableModel modelo = tabla.getModel();
	        String isan = modelo.getValueAt(filaSeleccionada, 0).toString();
	        return RepoPelicula.findByISAN(isan);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	// Devuelve Revista seleccionada
	public static Revistas devuelveRevista(JTable tabla) {
	    int filaSeleccionada = tabla.getSelectedRow();
	    
	    if (filaSeleccionada == -1) {
	        return null;
	    }
	    
	    try {
	        TableModel modelo = tabla.getModel();
	        String isbn = modelo.getValueAt(filaSeleccionada, 0).toString();
	        return RepoRevistas.findByISBN(isbn);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
	// IMPRIMIR PELICULA
	public static void rellenarFormularioPelicula(Peliculas pelicula, ModPelicula dialog) {
	    if (pelicula != null) {
	        dialog.textFieldPelicula.setText(String.valueOf(pelicula.getNumRegistro()));
	        dialog.textFieldISAN.setText(pelicula.getISAN());
	        dialog.textFieldTitulo.setText(pelicula.getTitulo());
	        dialog.textFieldDirector.setText(pelicula.getDirector());
	        
	        // Establecer el estilo en el comboBox
	        dialog.comboBoxEstilo.setSelectedItem(pelicula.getEstilo());
	        
	        // Establecer el soporte
	        if ("Físico".equals(pelicula.getSoporte())) {
	            dialog.rdbtnFisico.setSelected(true);
	        } else {
	            dialog.rdbtnDigital.setSelected(true);
	        }
	        
	        // Establecer la duración
	        dialog.spinnerDuracion.setValue(pelicula.getDuracion());
	        
	        // Establecer el año de publicación
	        if (pelicula.getAnioPublicacion() != null) {
	            dialog.yearChooser.setYear(pelicula.getAnioPublicacion().getYear());
	        }
	        
	     // Limpiar tablas
	        dialog.modelTodos.setRowCount(0);
	        dialog.modelAñadidos.setRowCount(0);
	        
	        // Obtener todos los protagonistas
	        ArrayList<Protagonista> todosProtagonistas = (ArrayList<Protagonista>) RepoProtagonista.findAll();
	        List<Protagonista> protasEnPelicula = pelicula.getProtagonistas();
	        
	        // Crear una lista de IDs de protagonistas en la película
	        List<Integer> idsProtasEnPelicula = protasEnPelicula.stream()
	            .map(Protagonista::getIdProta)
	            .collect(Collectors.toList());
	        
	        // Primero, rellenar la tabla de protagonistas de la película
	        for (Protagonista prota : protasEnPelicula) {
	            dialog.modelAñadidos.addRow(new Object[]{
	                prota.getIdProta(),
	                prota.getNombre()
	            });
	        }
	        
	        // Luego, rellenar la tabla de protagonistas disponibles (comparando por ID)
	        for (Protagonista prota : todosProtagonistas) {
	            if (!idsProtasEnPelicula.contains(prota.getIdProta())) {
	                dialog.modelTodos.addRow(new Object[]{
	                    prota.getIdProta(),
	                    prota.getNombre()
	                });
	            }
	        }
	        
	        // Hacemos no editable el ISAN
	        dialog.textFieldISAN.setEditable(false);
	    }
	}

	
	// IMPRIMIR REVISTAS
	public static void rellenarTablaRevistas(JTable tabla) {
	    DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
	    modelo.setRowCount(0); // Limpiar tabla
	    
	    ArrayList<Revistas> revistas = (ArrayList<Revistas>) RepoRevistas.findAll();
	    
	    for (Revistas revista : revistas) {
	        // Obtener los nombres de los artículos
	        String nombresArticulos = revista.getIndice().stream()
	            .map(Articulo::getNombre)
	            .collect(Collectors.joining(", "));
	        
	        Object[] fila = {
	            revista.getISBN(),
	            revista.getTitulo(),
	            revista.getTematica(),
	            nombresArticulos,
	            revista.getAnioPublicacion(),
	            revista.getNumPaginas(),
	            revista.getNumRegistro()
	        };
	        modelo.addRow(fila);
	    }
	}
	
	public static void rellenarFormularioRevista(Revistas revista, ModRevista dialog) {
	    if (revista != null) {
	        dialog.textFieldRevista.setText(String.valueOf(revista.getNumRegistro()));
	        dialog.textFieldISBN.setText(revista.getISBN());
	        dialog.textFieldTitulo.setText(revista.getTitulo());
	        
	        // Establecer la temática
	        dialog.selectedItemLabel.setText("Temática seleccionada: " + revista.getTematica());
	        
	        // Establecer el número de páginas
	        dialog.spinnerNumPaginas.setValue(revista.getNumPaginas());
	        
	        // Establecer el año de publicación
	        if (revista.getAnioPublicacion() != null) {
	            dialog.yearChooser.setYear(revista.getAnioPublicacion().getYear());
	        }
	        
	        // Rellenar el área de texto con los artículos
	        StringBuilder articulos = new StringBuilder();
	        for (Articulo articulo : revista.getIndice()) {
	            if (articulos.length() > 0) {
	                articulos.append("\n");
	            }
	            articulos.append(articulo.getNombre());
	        }
	        dialog.textAreaIndice.setText(articulos.toString());
	        
	        // Hacemos no editable el ISBN
	        dialog.textFieldISBN.setEditable(false);
	    }
	}
	
	
	// GUARDAR DATOS
	
	// CREAR MEDIO
	public static void crearMedio(CrearMedio dialog) {
	    try {
	        if (dialog.calendarFechaCompra != null) {
	            // Convert JCalendar date to LocalDate
	            LocalDate fechaCompra = dialog.calendarFechaCompra.getDate().toInstant()
	                    .atZone(ZoneId.systemDefault())
	                    .toLocalDate();

	            // Get values from spinners
	            double precioCompra = Double.parseDouble(dialog.spinnerPrecioCompra.getValue().toString());
	            int numEjemplares = Integer.parseInt(dialog.spinnerEjemplares.getValue().toString());

	            // Create Medio object
	            Medio medio = new Medio(fechaCompra, precioCompra, numEjemplares);

	            // Call repository create method
	            int resultado = RepoMedio.create(medio);

	            if (resultado > 0) {
	            	JOptionPane.showMessageDialog(dialog, "Medio creao de locos", "Me gusta como caza la perra", JOptionPane.INFORMATION_MESSAGE);
	                dialog.dispose();
	            } else {
	                JOptionPane.showMessageDialog(dialog, "Error al crear el Medio", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(dialog, "El calendario no está inicializado", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(dialog, "Error al procesar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}



	
	
	// CREAR PROTAGONISTA
	public static void guardarProtagonista(CrearProtagonista crearProtagonista) {
	    String nombreProta = crearProtagonista.getNombreProtagonista();
	    
	    Protagonista prota = new Protagonista(nombreProta, null, null);
	    
	    int resultado = RepoProtagonista.create(prota);
	    
	    // Si el resultado es 1, muestra el mensaje de éxito; si es 0, el mensaje de error
	    if (resultado == 1) {
	        JOptionPane.showMessageDialog(null, "Protagonista creado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(null, "No se ha podido crear el protagonista", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	// CREAR PELICULA
	public static void guardarPelicula(JTextField textFieldISAN, JTextField textFieldTitulo, 
	        JTextField textFieldDirector, JComboBox comboBoxEstilo, ButtonGroup soporteGroup, 
	        JSpinner spinnerDuracion, JYearChooser yearChooser, JTable tableAñadidos, JTextField textFieldMedio) {
	    
	    // Recogemos los datos
	    String isan = textFieldISAN.getText();
	    String titulo = textFieldTitulo.getText();
	    String director = textFieldDirector.getText();
	    String estilo = comboBoxEstilo.getSelectedItem().toString();
	    int duracion = (int) spinnerDuracion.getValue();
	    int anio = yearChooser.getYear();
	    
	    String IDMedio = textFieldMedio.getText();
	    int idMedio = Integer.parseInt(IDMedio);
	    
	    // Obtener el soporte seleccionado (Físico o Digital)
	    String soporte = "";
	    for (Enumeration<AbstractButton> buttons = soporteGroup.getElements(); buttons.hasMoreElements();) {
	        JRadioButton button = (JRadioButton) buttons.nextElement();
	        
	        if (button.isSelected()) {
	            soporte = button.getText();
	            break;
	        }
	    }
	    
	    // Obtener datos del medio
	    Medio m = RepoMedio.findById(idMedio);

	    
	    // Crear lista de protagonistas
	    List<Protagonista> protagonistas = new ArrayList<>();
	    for (int i = 0; i < tableAñadidos.getRowCount(); i++) {
	        String idProta = tableAñadidos.getValueAt(i, 0).toString();
	        // Asumiendo que tienes un método para buscar protagonistas por ID
	        Protagonista protagonista = RepoProtagonista.findById(Integer.parseInt(idProta));
	        if (protagonista != null) {
	            protagonistas.add(protagonista);
	            System.out.println(protagonista);
	        }
	    }
	    
	    // Crear el objeto Pelicula
	    Peliculas pelicula = new Peliculas(
	                                   m.getNumRegistro(),
	                                   m.getFechaAdquisicion(),
	                                   m.getPrecioCompra(),
	                                   m.getNumEjemplares(),
	                                   isan,
	                                   titulo,
	                                   director,
	                                   protagonistas, // Ahora pasamos la lista de protagonistas
	                                   estilo,
	                                   soporte,
	                                   duracion,
	                                   LocalDate.of(anio, 1, 1)
	                                );
	    
	    // Guardar la película en la base de datos
	    int resultado = RepoPelicula.create(pelicula);
	    
	    if (resultado != 0) {
	        // Limpiar el formulario
	        textFieldISAN.setText("");
	        textFieldTitulo.setText("");
	        textFieldDirector.setText("");
	        comboBoxEstilo.setSelectedIndex(0);
	        spinnerDuracion.setValue(0);
	        yearChooser.setYear(LocalDate.now().getYear());
	        textFieldMedio.setText("");
	        
	        // Limpiar la tabla de protagonistas
	        DefaultTableModel model = (DefaultTableModel) tableAñadidos.getModel();
	        model.setRowCount(0);
	        
	        JOptionPane.showMessageDialog(null, 
	            "Película guardada correctamente", 
	            "Éxito", 
	            JOptionPane.INFORMATION_MESSAGE);
	    
	    } else {
	        JOptionPane.showMessageDialog(null, 
	            "Error al guardar la película", 
	            "Error", 
	            JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	
	// GUARDAR REVISTA Y ARTICULOS
		public static void guardarRevista(JTextField textFieldISMN, JTextField textFieldTitulo, 
	        JLabel selectedItemLabel, JSpinner spinnerNumPaginas, JTextArea textAreaIndice, 
	        JYearChooser yearChooser, JTextField textFieldMedio) {
	    
		    try {
		        // Recogemos los datos
		        String ismn = textFieldISMN.getText().trim();
		        String titulo = textFieldTitulo.getText().trim();
		        String tematica = selectedItemLabel.getText().replace("Temática seleccionada: ", "").trim();
		        int numPaginas = (int) spinnerNumPaginas.getValue();
		        int anio = yearChooser.getYear();
		        
		        String IDMedio = textFieldMedio.getText();
		        int numRegistro = Integer.parseInt(IDMedio);
		        
		        // Obtener el medio base
		        Medio medioBase = RepoMedio.findById(numRegistro);
		        
		        // Crear la revista
		        Revistas revista = new Revistas(
		            medioBase.getNumRegistro(),
		            medioBase.getFechaAdquisicion(),
		            medioBase.getPrecioCompra(),
		            medioBase.getNumEjemplares(),
		            ismn,  // ISMN
		            titulo,
		            tematica,
		            new ArrayList<>(), // Lista vacía inicial de artículos
		            LocalDate.of(anio, 1, 1),
		            numPaginas
		        );
		        
		        // Guardar la revista primero
		        int resultadoRevista = RepoRevistas.create(revista);
		        
		        if (resultadoRevista > 0) {
		            boolean todosArticulosCreados = true; // Bandera para saber si se crearon todos los artículos

		            // Dividir el texto del área de índice en líneas separadas por salto de línea 
		            String[] lineasArticulos = textAreaIndice.getText().split("\n");

		            for (String linea : lineasArticulos) { // Procesar cada línea
		                
		            	if (!linea.trim().isEmpty()) { // Si la línea no está vacía
		                    // Dividir cada línea por comas
		                    String[] articulosEnLinea = linea.split(",");
		                    
		                    // Procesar cada artículo separado por coma
		                    for (String nombreArticulo : articulosEnLinea) { // Procesar cada artículo
		                        if (!nombreArticulo.trim().isEmpty()) { // Si el artículo no está vacío
		                            Articulo articulo = new Articulo( // Crear un artículo
		                                nombreArticulo.trim(), 
		                                revista,
		                                medioBase
		                            );
		                            
		                            // Guardar el artículo
		                            int resultadoArticulo = RepoArticulo.create(articulo);
		                            
	                                if (resultadoArticulo <= 0) {
		                                todosArticulosCreados = false; // Si no se creó el artículo, la bandera se vuelve false
		                                System.out.println("Error al crear el artículo: " + nombreArticulo);
		                            }
		                        }
		                    }
		                }
		            }
		            
		            if (todosArticulosCreados) {
		            	// Limpiamos formulario
		                textFieldISMN.setText("");
		                textFieldTitulo.setText("");
		                selectedItemLabel.setText("Temática seleccionada: ");
		                spinnerNumPaginas.setValue(0);
		                yearChooser.setYear(LocalDate.now().getYear());
		                textFieldMedio.setText("");
		                textAreaIndice.setText("");
		                
		                JOptionPane.showMessageDialog(null,"Revista y artículos creados exitosamente","Éxito", JOptionPane.INFORMATION_MESSAGE);
		                
		            } else {
		                JOptionPane.showMessageDialog(null, 
		                    "La revista se creó pero hubo errores al crear algunos artículos", 
		                    "Advertencia", 
		                    JOptionPane.WARNING_MESSAGE);
		            }
		            
		        } else {
		            JOptionPane.showMessageDialog(null, 
		                "Error al crear la revista", 
		                "Error", 
		                JOptionPane.ERROR_MESSAGE);
		        }
		        
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(null, 
		            "Error en el formato de los números: " + e.getMessage(), 
		            "Error", 
		            JOptionPane.ERROR_MESSAGE);
		        e.printStackTrace();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, 
		            "Error al guardar la revista: " + e.getMessage(), 
		            "Error", 
		            JOptionPane.ERROR_MESSAGE);
		        e.printStackTrace();
		    }
		}
		
		
		
		// GUARDAR DISCOS
		public static void guardarDisco(JTextField textFieldISMN, JTextField textFieldTitulo, 
		        JComboBox<String> comboBoxInterprete, JComboBox<String> comboBoxEstilo,
		        JRadioButton rdbtnFisico, JRadioButton rdbtnDigital,
		        JYearChooser yearChooser, JTextField textFieldMedio) {
		    
		    try {
		        // Recogemos los datos
		        String ismn = textFieldISMN.getText().trim();
		        String titulo = textFieldTitulo.getText().trim();
		        String interprete = comboBoxInterprete.getSelectedItem().toString();
		        String estilo = comboBoxEstilo.getSelectedItem().toString();
		        
		        // Determinar el soporte basado en el radio button seleccionado
		        String soporte = rdbtnFisico.isSelected() ? "Físico" : "Digital";
		        
		        int anio = yearChooser.getYear();
		        String IDMedio = textFieldMedio.getText().trim();
		        int numRegistro = Integer.parseInt(IDMedio);
		        
		        
		        // Obtener el medio base
		        Medio medioBase = RepoMedio.findById(numRegistro);
		        
		        // Crear el disco sin canciones primero
		        Discos disco = new Discos(
		            medioBase.getNumRegistro(),
		            medioBase.getFechaAdquisicion(),
		            medioBase.getPrecioCompra(),
		            medioBase.getNumEjemplares(),
		            
		            ismn,
		            titulo,
		            interprete,
		            estilo,
		            soporte,
		            LocalDate.of(anio, 1, 1)
		        );
		        
		        // Guardar el disco
		        int resultadoDisco = RepoDiscos.create(disco);
		        
		        if (resultadoDisco > 0) {
		            JOptionPane.showMessageDialog(null, 
		                "Disco creado exitosamente", 
		                "Éxito", 
		                JOptionPane.INFORMATION_MESSAGE);
		            
		        } else {
		            JOptionPane.showMessageDialog(null, 
		                "Error al crear el disco", 
		                "Error", 
		                JOptionPane.ERROR_MESSAGE);
		        }
		        
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(null, 
		            "Error en el formato de los números: " + e.getMessage(), 
		            "Error", 
		            JOptionPane.ERROR_MESSAGE);
		        e.printStackTrace();
		        
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, 
		            "Error al guardar el disco: " + e.getMessage(), 
		            "Error", 
		            JOptionPane.ERROR_MESSAGE);
		        e.printStackTrace();
		    }
		}
		
		
		
		// GUARDAR CANCIONES
		public static void guardarCancion(JTextField textFieldTitulo, JSpinner spinnerDuracion,
		        String discoISMN) {
		    
		    try {
		        // Recoger los datos
		        String titulo = textFieldTitulo.getText().trim();
		        int duracionMinutos = (int) spinnerDuracion.getValue();
		        
		        Discos disco = RepoDiscos.findByISMN(discoISMN);
		        
		        int registro = disco.numRegistro;
		        
		        Medio m = RepoMedio.findById(registro);
		        
		        // Crear canción
		        Cancion cancion = new Cancion(
		            titulo,
		            disco,
		            duracionMinutos,
		            m
		        );
		        
		        // Guardar la canción
		        int resultado = RepoCancion.create(cancion);
		        
		        if (resultado > 0) {
		            JOptionPane.showMessageDialog(null, 
		                "Canción creada exitosamente", 
		                "Éxito", 
		                JOptionPane.INFORMATION_MESSAGE);
		                
		            // Limpiar los campos
		            textFieldTitulo.setText("");
		            spinnerDuracion.setValue(0);
		            textFieldTitulo.requestFocus();
		            
		        } else {
		            JOptionPane.showMessageDialog(null, 
		                "Error al crear la canción", 
		                "Error", 
		                JOptionPane.ERROR_MESSAGE);
		        }
		        
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(null, 
		            "Error en el formato de los números: " + e.getMessage(), 
		            "Error", 
		            JOptionPane.ERROR_MESSAGE);
		        
		        e.printStackTrace();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, 
		            "Error al guardar la canción: " + e.getMessage(), 
		            "Error", 
		            JOptionPane.ERROR_MESSAGE);
		        
		        e.printStackTrace();
		    }
		}
		
	
		// MODIFICAR
		
		// MODIFICAR PELICULA
		public static void modificarPelicula(
		        JTextField textFieldISAN,
		        JTextField textFieldTitulo,
		        JTextField textFieldDirector,
		        JComboBox<String> comboBoxEstilo,
		        ButtonGroup soporteGroup,
		        JSpinner spinnerDuracion,
		        JYearChooser yearChooser,
		        JTable tableAñadidos,
		        JTextField textFieldMedio) {
		    
		    try {
		        // Obtener la película existente
		        String isan = textFieldISAN.getText().trim();
		        Peliculas peliculaExistente = RepoPelicula.findByISAN(isan);
		        
		        if (peliculaExistente == null) {
		            JOptionPane.showMessageDialog(null, 
		                "No se encontró la película con ISAN: " + isan, 
		                "Error", 
		                JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Recoger los datos de los componentes
		        String titulo = textFieldTitulo.getText().trim();
		        String director = textFieldDirector.getText().trim();
		        String estilo = comboBoxEstilo.getSelectedItem().toString();
		        int duracion = (int) spinnerDuracion.getValue();
		        int anio = yearChooser.getYear();
		        
		        // Obtener el soporte seleccionado
		        String soporte = "";
		        for (Enumeration<AbstractButton> buttons = soporteGroup.getElements(); buttons.hasMoreElements();) {
		            JRadioButton button = (JRadioButton) buttons.nextElement();
		            if (button.isSelected()) {
		                soporte = button.getText();
		                break;
		            }
		        }
		        
		        // Crear lista de protagonistas desde la tabla
		        List<Protagonista> protagonistas = new ArrayList<>();
		        DefaultTableModel model = (DefaultTableModel) tableAñadidos.getModel();
		        for (int i = 0; i < model.getRowCount(); i++) {
		            int idProta = Integer.parseInt(model.getValueAt(i, 0).toString());
		            Protagonista protagonista = RepoProtagonista.findById(idProta);
		            if (protagonista != null) {
		                protagonistas.add(protagonista);
		            }
		        }
		        
		        // Crear el objeto Película actualizado
		        Peliculas peliculaActualizada = new Peliculas(
		            peliculaExistente.getNumRegistro(),
		            peliculaExistente.getFechaAdquisicion(),
		            peliculaExistente.getPrecioCompra(),
		            peliculaExistente.getNumEjemplares(),
		            isan,
		            titulo,
		            director,
		            protagonistas,
		            estilo,
		            soporte,
		            duracion,
		            LocalDate.of(anio, 1, 1)
		        );
		        
		        // Llamar al método modify del repositorio
		        int resultado = RepoPelicula.modify(peliculaActualizada);
		        
		        if (resultado > 0) {
		            JOptionPane.showMessageDialog(null, 
		                "Película modificada correctamente", 
		                "Éxito", 
		                JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(null, 
		                "Error al modificar la película", 
		                "Error", 
		                JOptionPane.ERROR_MESSAGE);
		        }
		        
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(null, 
		            "Error en el formato de los números: " + e.getMessage(), 
		            "Error", 
		            JOptionPane.ERROR_MESSAGE);
		        e.printStackTrace();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, 
		            "Error al modificar la película: " + e.getMessage(), 
		            "Error", 
		            JOptionPane.ERROR_MESSAGE);
		        e.printStackTrace();
		    }
		}
		
		
		
		// BORRAR
		
		// BORRAR PELICULA
		public static void eliminarPelicula(String isan, int numRegistro) {
		    try {
		        // 1. Eliminamos la película
		        int resultadoPelicula = RepoPelicula.delete(isan, numRegistro);
		        
		        if (resultadoPelicula > 0) {
		            // 2. Eliminamos el medio
		            int resultadoMedio = RepoMedio.deleteById(numRegistro);
		            
		            if (resultadoMedio > 0) {
		                JOptionPane.showMessageDialog(null,
		                    "Película eliminada correctamente",
		                    "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null,
		                    "Error al eliminar el medio",
		                    "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null,
		                "Error al eliminar la película",
		                "Error",
		                JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null,
		            "Error durante la eliminación: " + e.getMessage(),
		            "Error",
		            JOptionPane.ERROR_MESSAGE);
		        e.printStackTrace();
		    }
		}
	
}
