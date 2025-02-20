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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JYearChooser;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;

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
	
	
	// Devuelve Medio en Principales
	public static Medio devuelveMedio(JTable tabla) {
		int filaSeleccionada = tabla.getSelectedRow();
		
		TableModel modelo = tabla.getModel();
		
		int numRegistro = (int) modelo.getValueAt(filaSeleccionada, 0);
		
		Medio m = RepoMedio.findById(numRegistro);
		
		return m;
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
	    int idMedio = Integer.parseInt(IDMedio); // Lo covertimos a INT
	    
	    
	    // Obtener el soporte seleccionado (Físico o Digital)
	    String soporte = ""; // Variable para almacenar el soporte seleccionado
	    
	    // Enumeration sirve para obtener los elementos de una colección
	    // AbstractButton es una clase abstracta que sirve como base para los botones 
	    for (Enumeration<AbstractButton> buttons = soporteGroup.getElements(); buttons.hasMoreElements();) { // Ciclo para recorrer los botones del grupo soporteGroup
	        JRadioButton button = (JRadioButton) buttons.nextElement(); // Obtiene el siguiente botón del grupo
	        
	        if (button.isSelected()) { // Verifica si el botón está seleccionado
	            soporte = button.getText(); // Almacena el texto del botón seleccionado en la variable soporte
	            break; // Sale del ciclo
	        }
	    }
	    
	    System.out.println(isan);
	    System.out.println(titulo);
	    System.out.println(director);
	    System.out.println(estilo);
	    
	    System.out.println(duracion);
	    System.out.flush();
	    
	    System.out.println(anio);
	    System.out.flush();
	    
	    System.out.println(IDMedio);
	    System.out.println(idMedio);
	    System.out.flush();
	    
	    System.out.println(soporte);
	    
	    // Crear el objeto Pelicula
	    
	    //Primero conseguimos los datos del medio para insertarlos en el consturctor de Pelicula
	    Medio m = RepoMedio.findById(idMedio);
	    
	    System.out.println(m);
	    
	    //numRegistro, fechaAdquisicion, precioCompra, numEjemplares
	    int numRegistro = m.getNumRegistro();
	    LocalDate fechaAdquisicion = m.getFechaAdquisicion();
	    Double precioCompra = m.getPrecioCompra();
	    int numEjemplares = m.getNumEjemplares();
	    
        // Crear las relaciones con los protagonistas
        for (int i = 0; i < tableAñadidos.getRowCount(); i++) {
            String idProta = tableAñadidos.getValueAt(i, 0);
            
            System.out.println(idProta);
        }
	    
	    Peliculas pelicula = new Peliculas(
	    								   numRegistro,
	    								   fechaAdquisicion,
	    								   precioCompra,
	    								   numEjemplares,
	    								   
									       isan,
									       titulo,
									       director,
									       null, // protagonistas (se añadirán después)
									       estilo,
									       soporte,
									       duracion,
									       LocalDate.of(anio, 1, 1)  // anioPublicacion
									    );
	    
	    System.out.println(pelicula);
	    
	    // Guardar la película en la base de datos y obtener su ID
	    int resultado = RepoPelicula.create(pelicula);
	    
	    System.out.println(resultado);
	    
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
	
	
	
}
