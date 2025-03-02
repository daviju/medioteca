package graphic;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import com.toedter.calendar.JYearChooser;

import entidades.Medio;
import metodos.MetodosGraficos;

public class CrearRevista extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    public static JTextField textFieldTitulo;
    public static JList<String> listTematicas;
    public static DefaultListModel<String> listModel;
    public static JScrollPane scrollPaneTematicas;
    public static JLabel selectedItemLabel;
    public static JSpinner spinnerNumPaginas;
    public static JTextArea textAreaIndice;
    public static JYearChooser yearChooser;
    public static JTextField textFieldMedio;
    private JTextField textFieldISMN;

    public static void main(String[] args) {
        try {
            CrearRevista dialog = new CrearRevista();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CrearRevista() {
        setTitle("Crear Revista");
        setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
        setupMainFrame();
        setupComponents();
        setupList();
        setupButtons();
    }

    private void setupMainFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
        setBounds(100, 100, 518, 676);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
    }

    private void setupComponents() {
        // Título Label and TextField
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitulo.setBounds(20, 184, 77, 20);
        contentPanel.add(lblTitulo);

        textFieldTitulo = new JTextField();
        textFieldTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldTitulo.setBounds(107, 176, 312, 37);
        contentPanel.add(textFieldTitulo);
        textFieldTitulo.setColumns(10);

        // Categories Label
        JLabel lblCategories = new JLabel("Temática:");
        lblCategories.setForeground(Color.WHITE);
        lblCategories.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCategories.setBounds(20, 293, 90, 37);
        contentPanel.add(lblCategories);

        // Selected Category Label
        selectedItemLabel = new JLabel("Temática seleccionada: Ninguna");
        selectedItemLabel.setForeground(Color.WHITE);
        selectedItemLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        selectedItemLabel.setBounds(107, 224, 300, 25);
        contentPanel.add(selectedItemLabel);

        // Índice Label and TextArea
        JLabel lblIndice = new JLabel("Artículos:");
        lblIndice.setForeground(Color.WHITE);
        lblIndice.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblIndice.setBounds(20, 426, 90, 20);
        contentPanel.add(lblIndice);

        textAreaIndice = new JTextArea();
        textAreaIndice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JScrollPane scrollPaneIndice = new JScrollPane(textAreaIndice);
        scrollPaneIndice.setBounds(106, 382, 375, 100);
        contentPanel.add(scrollPaneIndice);

        JLabel lblAdvertencia = new JLabel("Separa los artículos por comas");
        lblAdvertencia.setForeground(Color.RED);
        lblAdvertencia.setBounds(107, 367, 189, 14);
        contentPanel.add(lblAdvertencia);

        // Año de publicación
        JLabel lblAnio = new JLabel("Año:");
        lblAnio.setForeground(Color.WHITE);
        lblAnio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblAnio.setBounds(20, 503, 47, 20);
        contentPanel.add(lblAnio);


        // Número de páginas
        JLabel lblNumPaginas = new JLabel("Páginas:");
        lblNumPaginas.setForeground(Color.WHITE);
        lblNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNumPaginas.setBounds(20, 552, 90, 20);
        contentPanel.add(lblNumPaginas);

        SpinnerModel pagesModel = new SpinnerNumberModel(1, 1, 1000, 1);
        spinnerNumPaginas = new JSpinner(pagesModel);
        spinnerNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spinnerNumPaginas.setBounds(107, 548, 100, 30);
        contentPanel.add(spinnerNumPaginas);


    }

    private void setupList() {
        // Create and populate the list model
        listModel = new DefaultListModel<>();
        String[] categories = {
            "Deportiva",
            "Interés General",
            "Cotilleos",
            "Ciencia",
            "Tecnología",
            "Moda",
            "Cocina"
        };
        for (String category : categories) {
            listModel.addElement(category);
        }

        // Create and configure the JList
        listTematicas = new JList<>(listModel);
        listTematicas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTematicas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        listTematicas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        listTematicas.setBackground(new Color(250, 250, 250));
        
        // Add selection listener
        listTematicas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = listTematicas.getSelectedValue();
                if (selected != null) {
                    selectedItemLabel.setText("Temática seleccionada: " + selected);
                }
            }
        });

        // Create and configure the scroll pane
        scrollPaneTematicas = new JScrollPane(listTematicas);
        scrollPaneTematicas.setBounds(107, 246, 312, 100);
        scrollPaneTematicas.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        contentPanel.add(scrollPaneTematicas);
        
        yearChooser = new JYearChooser();
        yearChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 18));
        yearChooser.setBounds(107, 493, 100, 30);
        contentPanel.add(yearChooser);
        
        JLabel lblMedio = new JLabel("Buscar Medio:");
        lblMedio.setForeground(Color.WHITE);
        lblMedio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMedio.setBounds(20, 34, 112, 14);
        contentPanel.add(lblMedio);
        
        textFieldMedio = new JTextField();
        textFieldMedio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldMedio.setBounds(141, 26, 96, 30);
        contentPanel.add(textFieldMedio);
        textFieldMedio.setColumns(10);
        
        JButton btnNewButton = new JButton("Buscar");
        btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        BuscaMedio dialog = new BuscaMedio(CrearRevista.this);
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
        
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(267, 33, 89, 23);
        contentPanel.add(btnNewButton);
        
        JLabel lblISMN = new JLabel("ISMN:");
        lblISMN.setForeground(Color.WHITE);
        lblISMN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblISMN.setBounds(20, 114, 77, 14);
        contentPanel.add(lblISMN);
        
        textFieldISMN = new JTextField();
        textFieldISMN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldISMN.setBounds(107, 103, 312, 37);
        contentPanel.add(textFieldISMN);
        textFieldISMN.setColumns(10);
        
        JLabel lblFondo = new JLabel("");
        lblFondo.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
        lblFondo.setBounds(0, 0, 504, 598);
        contentPanel.add(lblFondo);
    }

    private void setupButtons() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Guardar");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    MetodosGraficos.guardarRevista(
                        textFieldISMN,
                        textFieldTitulo,
                        selectedItemLabel,
                        spinnerNumPaginas,
                        textAreaIndice,
                        yearChooser,
                        textFieldMedio
                    );
                }
            }
        });
        
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.RED);
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }

    private boolean validateFields() {
        if (textFieldTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, introduce un título",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (listTematicas.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this,
                "Por favor, selecciona una temática",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (textAreaIndice.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, introduce al menos un artículo en el índice",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    // Getters para obtener los valores
    public String getTitulo() {
        return textFieldTitulo.getText().trim();
    }

    public String getTematica() {
        return listTematicas.getSelectedValue();
    }

    public String[] getArticulos() {
        return textAreaIndice.getText().split(",");
    }

    public int getAnioPublicacion() {
        return yearChooser.getYear();
    }


    public int getNumPaginas() {
        return (Integer) spinnerNumPaginas.getValue();
    }
    
    
    
    
    public void setMedioSeleccionado(Medio medio) {
        if (medio != null) {
            textFieldMedio.setText(String.valueOf(medio.numRegistro));
        }
    }
    
    
    private boolean validateForm() {
        // Validar ISBN
        if (textFieldISMN.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo ISMN no puede estar vacío", "Error de validación", JOptionPane.ERROR_MESSAGE);
            textFieldISMN.requestFocus();
            return false;
        }
        
        // Validar Título
        if (textFieldTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Título no puede estar vacío", "Error de validación", JOptionPane.ERROR_MESSAGE);
            textFieldTitulo.requestFocus();
            return false;
        }
        
        // Validar Temática
        if (selectedItemLabel.getText().contains("Ninguna")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una temática", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar que se hayan ingresado artículos
        if (textAreaIndice.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar al menos un artículo", "Error de validación", JOptionPane.ERROR_MESSAGE);
            textAreaIndice.requestFocus();
            return false;
        }
        
        // Validar que se haya seleccionado un Medio
        if (textFieldMedio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un medio", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Si todas las validaciones pasan, retornar true
        return true;
    }
}