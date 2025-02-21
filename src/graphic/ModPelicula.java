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
import entidades.Pelicula;
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
        
        JRadioButton rdbtnFisico = new JRadioButton("Físico");
        rdbtnFisico.setFont(new Font("Tahoma", Font.PLAIN, 18));
        rdbtnFisico.setBounds(501, 31, 81, 23);
        contentPanel.add(rdbtnFisico);

        JRadioButton rdbtnDigital = new JRadioButton("Digital");
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

        JComboBox comboBoxEstilo = new JComboBox();
        comboBoxEstilo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxEstilo.setBounds(97, 205, 275, 32);
        contentPanel.add(comboBoxEstilo);

        comboBoxEstilo.addItem("Aventuras");
        comboBoxEstilo.addItem("Historia");
        comboBoxEstilo.addItem("Ciencia Ficción");
        comboBoxEstilo.addItem("Porno");
        comboBoxEstilo.addItem("Suspense");
        comboBoxEstilo.addItem("Miedo");

        JSpinner spinnerDuracion = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        spinnerDuracion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        spinnerDuracion.setBounds(516, 89, 104, 20);
        contentPanel.add(spinnerDuracion);

        JLabel lblDuracion = new JLabel("Duración:");
        lblDuracion.setForeground(Color.WHITE);
        lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDuracion.setBounds(425, 89, 81, 20);
        contentPanel.add(lblDuracion);

        JYearChooser yearChooser = new JYearChooser();
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

        // Rellenar la tabla de disponibles
        ArrayList<Protagonista> todosProtagonistas = new ArrayList<>(RepoProtagonista.findAll());
        fillTableDisponibles(todosProtagonistas);

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

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        okButton.setForeground(Color.GREEN);
        okButton.addActionListener(new ActionListener() {
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

        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cancelButton.setForeground(Color.RED);
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }

    private void fillTableDisponibles(ArrayList<Protagonista> protagonistas) {
        modelTodos.setRowCount(0);
        for (Protagonista prota : protagonistas) {
            modelTodos.addRow(new Object[]{
                prota.getIdProta(),
                prota.getNombre()
            });
        }
    }

    private void moveProta(JTable fromTable, JTable toTable, DefaultTableModel fromModel, DefaultTableModel toModel) {
        int fila = fromTable.getSelectedRow();
        if (fila != -1) {
            Object[] datos = new Object[fromTable.getColumnCount()];
            for (int i = 0; i < fromTable.getColumnCount(); i++) {
                datos[i] = fromModel.getValueAt(fila, i);
            }
            toModel.addRow(datos);
            fromModel.removeRow(fila);
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
            textFieldPelicula.setText(String.valueOf(pelicula.getISAN()));
        }
    }
}