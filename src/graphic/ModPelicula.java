package graphic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JOptionPane;
import com.toedter.calendar.JYearChooser;
import conexion.RepoProtagonista;
import entidades.Peliculas;
import entidades.Protagonista;
import metodos.MetodosGraficos;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class ModPelicula extends JDialog {

    private static final long serialVersionUID = 1L;
    public static JPanel contentPanel;
    public static JTextField textFieldISAN;
    public static JTextField textFieldTitulo;
    public static JTextField textFieldDirector;
    public static JTable tableDisponibles;
    public static JTable tableAñadidos;
    public static DefaultTableModel modelTodos;
    public static DefaultTableModel modelAñadidos;
    public static ButtonGroup soporteGroup;
    public static JTextField textFieldPelicula;
    public static JComboBox<String> comboBoxEstilo;
    public static JSpinner spinnerDuracion;
    public static JYearChooser yearChooser;
    public static JRadioButton rdbtnFisico;
    public static JRadioButton rdbtnDigital;

    public static void main(String[] args) {
        try {
            ModPelicula dialog = new ModPelicula();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ModPelicula() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
        setTitle("Modificar Pelicula");
        setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
        setBounds(100, 100, 768, 582);
        
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblISAN = new JLabel("ISAN:");
        lblISAN.setForeground(Color.WHITE);
        lblISAN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblISAN.setBounds(25, 76, 62, 17);
        contentPanel.add(lblISAN);

        textFieldISAN = new JTextField();
        textFieldISAN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldISAN.setBounds(97, 68, 275, 32);
        contentPanel.add(textFieldISAN);
        textFieldISAN.setColumns(10);

        JLabel lblTitulo = new JLabel("Titulo:");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitulo.setBounds(25, 118, 68, 17);
        contentPanel.add(lblTitulo);

        textFieldTitulo = new JTextField();
        textFieldTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldTitulo.setBounds(97, 111, 275, 30);
        contentPanel.add(textFieldTitulo);
        textFieldTitulo.setColumns(10);

        JLabel lblDirector = new JLabel("Director:");
        lblDirector.setForeground(Color.WHITE);
        lblDirector.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDirector.setBounds(25, 159, 81, 20);
        contentPanel.add(lblDirector);

        textFieldDirector = new JTextField();
        textFieldDirector.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldDirector.setBounds(107, 154, 265, 30);
        contentPanel.add(textFieldDirector);
        textFieldDirector.setColumns(10);

        soporteGroup = new ButtonGroup();
        
        rdbtnFisico = new JRadioButton("Físico");
        rdbtnFisico.setFont(new Font("Tahoma", Font.PLAIN, 18));
        rdbtnFisico.setBounds(501, 31, 81, 23);
        contentPanel.add(rdbtnFisico);

        rdbtnDigital = new JRadioButton("Digital");
        rdbtnDigital.setFont(new Font("Tahoma", Font.PLAIN, 18));
        rdbtnDigital.setBounds(584, 31, 81, 23);
        contentPanel.add(rdbtnDigital);

        soporteGroup.add(rdbtnFisico);
        soporteGroup.add(rdbtnDigital);
        
        JLabel lblSoporte = new JLabel("Soporte:");
        lblSoporte.setForeground(Color.WHITE);
        lblSoporte.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSoporte.setBounds(425, 27, 81, 30);
        contentPanel.add(lblSoporte);

        JLabel lblEstilo = new JLabel("Estilo:");
        lblEstilo.setForeground(Color.WHITE);
        lblEstilo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEstilo.setBounds(25, 210, 62, 23);
        contentPanel.add(lblEstilo);

        comboBoxEstilo = new JComboBox<>();
        comboBoxEstilo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxEstilo.setBounds(97, 205, 275, 32);
        contentPanel.add(comboBoxEstilo);

        comboBoxEstilo.addItem("Aventuras");
        comboBoxEstilo.addItem("Historia");
        comboBoxEstilo.addItem("Ciencia Ficción");
        comboBoxEstilo.addItem("Porno");
        comboBoxEstilo.addItem("Suspense");
        comboBoxEstilo.addItem("Miedo");

        spinnerDuracion = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        spinnerDuracion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        spinnerDuracion.setBounds(516, 89, 104, 20);
        contentPanel.add(spinnerDuracion);

        JLabel lblDuracion = new JLabel("Duración:");
        lblDuracion.setForeground(Color.WHITE);
        lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDuracion.setBounds(425, 89, 81, 20);
        contentPanel.add(lblDuracion);

        yearChooser = new JYearChooser();
        yearChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 18));
        yearChooser.setBounds(603, 133, 62, 23);
        contentPanel.add(yearChooser);

        JLabel lblAñopublicacion = new JLabel("Año de publicación:");
        lblAñopublicacion.setForeground(Color.WHITE);
        lblAñopublicacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblAñopublicacion.setBounds(425, 133, 167, 23);
        contentPanel.add(lblAñopublicacion);

        JLabel lblActoresDisponibles = new JLabel("Actores disponibles:");
        lblActoresDisponibles.setForeground(Color.WHITE);
        lblActoresDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblActoresDisponibles.setBounds(77, 272, 158, 20);
        contentPanel.add(lblActoresDisponibles);

        JLabel lblActoresSeleccionados = new JLabel("Actores en la Película:");
        lblActoresSeleccionados.setForeground(Color.WHITE);
        lblActoresSeleccionados.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblActoresSeleccionados.setBounds(511, 274, 177, 17);
        contentPanel.add(lblActoresSeleccionados);

        JButton btnAgregar = new JButton(">");
        btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnAgregar.setBounds(334, 366, 89, 23);
        contentPanel.add(btnAgregar);

        JButton btnQuitar = new JButton("<");
        btnQuitar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnQuitar.setBounds(334, 436, 89, 23);
        contentPanel.add(btnQuitar);

        JScrollPane scrollPaneDisponibles = new JScrollPane();
        scrollPaneDisponibles.setBounds(10, 303, 294, 190);
        contentPanel.add(scrollPaneDisponibles);

        tableDisponibles = new JTable();
        modelTodos = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nombre"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableDisponibles.setModel(modelTodos);
        tableDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 17));
        scrollPaneDisponibles.setViewportView(tableDisponibles);

        JScrollPane scrollPaneAñadidos = new JScrollPane();
        scrollPaneAñadidos.setBounds(450, 303, 294, 190);
        contentPanel.add(scrollPaneAñadidos);

        tableAñadidos = new JTable();
        modelAñadidos = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nombre"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableAñadidos.setModel(modelAñadidos);
        tableAñadidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableAñadidos.setFont(new Font("Tahoma", Font.PLAIN, 17));
        scrollPaneAñadidos.setViewportView(tableAñadidos);
        
        JButton btnPelicula = new JButton("Buscar Película");
        btnPelicula.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BuscaPelicula dialog = new BuscaPelicula(ModPelicula.this);
                dialog.setModal(true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        
        btnPelicula.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnPelicula.setBounds(205, 16, 167, 23);
        contentPanel.add(btnPelicula);
        
        JLabel lblPelicula = new JLabel("Película:");
        lblPelicula.setForeground(Color.WHITE);
        lblPelicula.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPelicula.setBounds(10, 20, 77, 14);
        contentPanel.add(lblPelicula);
        
        textFieldPelicula = new JTextField();
        textFieldPelicula.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldPelicula.setBounds(77, 9, 96, 32);
        contentPanel.add(textFieldPelicula);
        textFieldPelicula.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
        lblNewLabel.setBounds(0, 0, 754, 504);
        contentPanel.add(lblNewLabel);

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveProta(tableDisponibles, tableAñadidos, modelTodos, modelAñadidos);
            }
        });

        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveProta(tableAñadidos, tableDisponibles, modelAñadidos, modelTodos);
            }
        });

     // En ModPelicula.java, modificar la sección de los botones:

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnModificar.setForeground(Color.BLUE);
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    try {
                        MetodosGraficos.modificarPelicula(
                            textFieldISAN,
                            textFieldTitulo,
                            textFieldDirector,
                            comboBoxEstilo,
                            soporteGroup,
                            spinnerDuracion,
                            yearChooser,
                            tableAñadidos,
                            textFieldPelicula
                        );
                        dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ModPelicula.this,
                            "Error al modificar la película: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });
        buttonPane.add(btnModificar);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnBorrar.setForeground(Color.RED);
        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    int confirm = JOptionPane.showConfirmDialog(
                        ModPelicula.this,
                        "¿Está seguro de que desea eliminar esta película?\nEsta acción no se puede deshacer.",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                    );
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        String isan = textFieldISAN.getText();
                        int numRegistro = Integer.parseInt(textFieldPelicula.getText());
                        MetodosGraficos.eliminarPelicula(isan, numRegistro);
                        dispose();
                    }
                }
            }
        });
        buttonPane.add(btnBorrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnCancelar.setForeground(Color.GRAY);
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);
    }
        
	    private void fillTableDisponibles(ArrayList<Protagonista> protagonistas) {
	        modelTodos.setRowCount(0);
	        
	        // Obtener la lista de protagonistas ya añadidos
	        List<Integer> protasAñadidosIds = new ArrayList<>();
	        for (int i = 0; i < modelAñadidos.getRowCount(); i++) {
	            protasAñadidosIds.add(Integer.parseInt(modelAñadidos.getValueAt(i, 0).toString()));
	        }
	        
	        // Solo añadir a la tabla de disponibles los que no están en la tabla de añadidos
	        for (Protagonista prota : protagonistas) {
	            if (!protasAñadidosIds.contains(prota.getIdProta())) {
	                modelTodos.addRow(new Object[]{
	                    prota.getIdProta(),
	                    prota.getNombre()
	                });
	            }
	        }
	    }

	    private void moveProta(JTable fromTable, JTable toTable, DefaultTableModel fromModel, DefaultTableModel toModel) {
	        int selectedRow = fromTable.getSelectedRow();
	        if (selectedRow != -1) {
	            // Obtener los datos de la fila seleccionada
	            Object[] rowData = new Object[fromModel.getColumnCount()];
	            for (int i = 0; i < fromModel.getColumnCount(); i++) {
	                rowData[i] = fromModel.getValueAt(selectedRow, i);
	            }
	            
	            // Añadir a la tabla destino
	            toModel.addRow(rowData);
	            
	            // Eliminar de la tabla origen
	            fromModel.removeRow(selectedRow);
	        }
	    }

        private boolean validateForm() {
            if (textFieldISAN.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo ISAN no puede estar vacío", "Error de validación", JOptionPane.ERROR_MESSAGE);
                textFieldISAN.requestFocus();
                return false;
            }
            
            if (textFieldTitulo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo Título no puede estar vacío", "Error de validación", JOptionPane.ERROR_MESSAGE);
                textFieldTitulo.requestFocus();
                return false;
            }
            
            if (textFieldDirector.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo Director no puede estar vacío", "Error de validación", JOptionPane.ERROR_MESSAGE);
                textFieldDirector.requestFocus();
                return false;
            }
            
            if (textFieldPelicula.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una película", "Error de validación", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            return true;
        }

        public void setPeliculaSeleccionada(Peliculas pelicula) {
            if (pelicula != null) {
                // Configurar los campos básicos
                textFieldPelicula.setText(String.valueOf(pelicula.getNumRegistro()));
                textFieldISAN.setText(pelicula.getISAN());
                textFieldTitulo.setText(pelicula.getTitulo());
                textFieldDirector.setText(pelicula.getDirector());
                comboBoxEstilo.setSelectedItem(pelicula.getEstilo());
                
                // Configurar soporte
                if ("Físico".equals(pelicula.getSoporte())) {
                    rdbtnFisico.setSelected(true);
                } else {
                    rdbtnDigital.setSelected(true);
                }
                
                spinnerDuracion.setValue(pelicula.getDuracion());
                
                if (pelicula.getAnioPublicacion() != null) {
                    yearChooser.setYear(pelicula.getAnioPublicacion().getYear());
                }
                
                // Limpiar tablas
                modelTodos.setRowCount(0);
                modelAñadidos.setRowCount(0);
                
                // 1. Primero añadir los protagonistas de la película a la tabla de añadidos
                for (Protagonista p : pelicula.getProtagonistas()) {
                    modelAñadidos.addRow(new Object[]{p.getIdProta(), p.getNombre()});
                }
                
                // 2. Obtener todos los protagonistas
                ArrayList<Protagonista> todosProtagonistas = new ArrayList<>(RepoProtagonista.findAll());
                
                // 3. Para cada protagonista, verificar si NO está en la película
                protas: for (Protagonista prota : todosProtagonistas) {
                    // Revisar si este protagonista ya está en la tabla de añadidos
                    for (int i = 0; i < modelAñadidos.getRowCount(); i++) {
                        int idEnTabla = (int) modelAñadidos.getValueAt(i, 0);
                        if (idEnTabla == prota.getIdProta()) {
                            continue protas; // Si lo encontramos, pasamos al siguiente protagonista
                        }
                    }
                    // Si llegamos aquí es porque el protagonista no está en la tabla de añadidos
                    modelTodos.addRow(new Object[]{prota.getIdProta(), prota.getNombre()});
                }
                
                textFieldISAN.setEditable(false);
            }
        }
    }