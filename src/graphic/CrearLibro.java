package graphic;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import entidades.Medio;

import java.awt.event.*;

public class CrearLibro extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    public static JTextField textFieldTitulo;
    public static JList<String> listTematicas;
    public static DefaultListModel<String> listModel;
    public static JScrollPane scrollPaneTematicas;
    public static JLabel selectedItemLabel;
    public static JTextField textFieldISBN;
    public static JTextField textFieldMedio;

    public static void main(String[] args) {
        try {
            CrearLibro dialog = new CrearLibro();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CrearLibro() {
    	setTitle("Crear Libro");
    	setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
        setupMainFrame();
        setupComponents();
        setupList();
        setupButtons();
    }

    private void setupMainFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
        setBounds(100, 100, 558, 646);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
    }

    private void setupComponents() {
        // Title Label and TextField
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitulo.setBounds(20, 156, 77, 20);
        contentPanel.add(lblTitulo);

        textFieldTitulo = new JTextField();
        textFieldTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldTitulo.setBounds(107, 148, 312, 37);
        contentPanel.add(textFieldTitulo);
        textFieldTitulo.setColumns(10);

        // Categories Label
        JLabel lblCategories = new JLabel("Categorías:");
        lblCategories.setForeground(Color.WHITE);
        lblCategories.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCategories.setBounds(20, 294, 90, 37);
        contentPanel.add(lblCategories);

        // Selected Category Label
        selectedItemLabel = new JLabel("Categoría seleccionada: Ninguna");
        selectedItemLabel.setForeground(Color.WHITE);
        selectedItemLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        selectedItemLabel.setBounds(106, 241, 300, 25);
        contentPanel.add(selectedItemLabel);
    }

    private void setupList() {
        // Categorias de la Lista
        listModel = new DefaultListModel<>();
        String[] categories = {
            "Ciencia ficción",
            "Fantasía",
            "Novela",
            "Histórico",
            "Desarrollo personal"
        };
        for (String category : categories) {
            listModel.addElement(category);
        }

        // Crear y configuarar la Lista
        listTematicas = new JList<>(listModel);
        listTematicas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTematicas.setFont(new Font("Tahoma", Font.PLAIN, 18));
        listTematicas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        listTematicas.setBackground(new Color(250, 250, 250));
        
        // Para seleccionar la categoría
        listTematicas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = listTematicas.getSelectedValue();
                if (selected != null) {
                    selectedItemLabel.setText("Categoría seleccionada: " + selected);
                }
            }
        });

        // Add mouse listener for double-click
        listTematicas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = listTematicas.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        String selected = listModel.getElementAt(index);
                        JOptionPane.showMessageDialog(CrearLibro.this, 
                            "Has seleccionado: " + selected,
                            "Selección",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        // Create and configure the scroll pane
        scrollPaneTematicas = new JScrollPane(listTematicas);
        scrollPaneTematicas.setBounds(107, 263, 312, 100);
        scrollPaneTematicas.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        contentPanel.add(scrollPaneTematicas);
        
        JComboBox comboBoxAutor = new JComboBox();
        comboBoxAutor.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxAutor.setBounds(107, 200, 312, 21);
        contentPanel.add(comboBoxAutor);
        
        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setForeground(Color.WHITE);
        lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblAutor.setBounds(20, 203, 70, 14);
        contentPanel.add(lblAutor);
        
        JLabel lblCapitulos = new JLabel("Capítulos:");
        lblCapitulos.setForeground(Color.WHITE);
        lblCapitulos.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCapitulos.setBounds(20, 434, 90, 20);
        contentPanel.add(lblCapitulos);
        
        JTextArea textAreaCapitulos = new JTextArea();
        textAreaCapitulos.setBounds(107, 393, 375, 100);
        contentPanel.add(textAreaCapitulos);
        
        JLabel lblAdvertencia = new JLabel("Separa los capítulos por comas");
        lblAdvertencia.setForeground(Color.RED);
        lblAdvertencia.setBounds(106, 374, 189, 14);
        contentPanel.add(lblAdvertencia);
        
        JSpinner spinnerNumPaginas = new JSpinner();
        spinnerNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, 18));
        spinnerNumPaginas.setBounds(149, 512, 65, 37);
        contentPanel.add(spinnerNumPaginas);
        
        JLabel lblNumPaginas = new JLabel("Nº de páginas:");
        lblNumPaginas.setForeground(Color.WHITE);
        lblNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNumPaginas.setBounds(20, 520, 119, 20);
        contentPanel.add(lblNumPaginas);
        
        JLabel lblISBN = new JLabel("ISBN:");
        lblISBN.setForeground(Color.WHITE);
        lblISBN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblISBN.setBounds(20, 104, 70, 23);
        contentPanel.add(lblISBN);
        
        textFieldISBN = new JTextField();
        textFieldISBN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldISBN.setBounds(107, 102, 312, 25);
        contentPanel.add(textFieldISBN);
        textFieldISBN.setColumns(10);
        
        JLabel lblMedio = new JLabel("Buscar Medio:");
        lblMedio.setForeground(Color.WHITE);
        lblMedio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMedio.setBounds(20, 43, 112, 14);
        contentPanel.add(lblMedio);
        
        textFieldMedio = new JTextField();
        textFieldMedio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldMedio.setBounds(142, 34, 96, 32);
        contentPanel.add(textFieldMedio);
        textFieldMedio.setColumns(10);
        
        JButton btnBuscarMedio = new JButton("Buscar");
        btnBuscarMedio.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        BuscaMedio dialog = new BuscaMedio(CrearLibro.this);
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
        
        btnBuscarMedio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnBuscarMedio.setBounds(248, 39, 89, 23);
        contentPanel.add(btnBuscarMedio);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
        lblNewLabel_1.setBounds(0, 0, 544, 568);
        contentPanel.add(lblNewLabel_1);
    }

    private void setupButtons() {
        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(Color.WHITE);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.setForeground(Color.GREEN);
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        okButton.addActionListener(e -> {
            String selected = listTematicas.getSelectedValue();
            if (selected != null) {
                // Here you can handle the selected category
                JOptionPane.showMessageDialog(CrearLibro.this,
                    "Categoría seleccionada: " + selected,
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(CrearLibro.this,
                    "Por favor, selecciona una categoría",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
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

    // Method to get the selected category
    public String getSelectedCategory() {
        return listTematicas.getSelectedValue();
    }
    
    
    
    
    public void setMedioSeleccionado(Medio medio) {
        if (medio != null) {
            textFieldMedio.setText(String.valueOf(medio.numRegistro));

        }
    }
}