package graphic;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class ModLibro extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldTitulo;
    private JList<String> listTematicas;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPaneTematicas;
    private JLabel selectedItemLabel;
    private JTextField textFieldISBN;

    public static void main(String[] args) {
        try {
            ModLibro dialog = new ModLibro();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ModLibro() {
        setTitle("Modificar Libro");
        setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
        setupMainFrame();
        setupComponents();
        setupList();
        setupButtons();
    }

    private void setupMainFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
        setBounds(100, 100, 688, 559);
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
        lblTitulo.setBounds(20, 86, 77, 20);
        contentPanel.add(lblTitulo);

        textFieldTitulo = new JTextField();
        textFieldTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldTitulo.setBounds(107, 78, 312, 37);
        contentPanel.add(textFieldTitulo);
        textFieldTitulo.setColumns(10);

        // Categories Label
        JLabel lblCategories = new JLabel("Categorías:");
        lblCategories.setForeground(Color.WHITE);
        lblCategories.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCategories.setBounds(20, 198, 90, 37);
        contentPanel.add(lblCategories);

        // Selected Category Label
        selectedItemLabel = new JLabel("Categoría seleccionada: Ninguna");
        selectedItemLabel.setForeground(Color.WHITE);
        selectedItemLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        selectedItemLabel.setBounds(107, 258, 300, 25);
        contentPanel.add(selectedItemLabel);
    }

    private void setupList() {
        // Create and populate the list model
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
                        JOptionPane.showMessageDialog(ModLibro.this, 
                            "Has seleccionado: " + selected,
                            "Selección",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        // Create and configure the scroll pane
        scrollPaneTematicas = new JScrollPane(listTematicas);
        scrollPaneTematicas.setBounds(107, 164, 312, 100);
        scrollPaneTematicas.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        contentPanel.add(scrollPaneTematicas);
        
        JComboBox comboBoxAutor = new JComboBox();
        comboBoxAutor.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxAutor.setBounds(107, 132, 312, 21);
        contentPanel.add(comboBoxAutor);
        
        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setForeground(Color.WHITE);
        lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblAutor.setBounds(20, 138, 70, 14);
        contentPanel.add(lblAutor);
        
        JLabel lblCapitulos = new JLabel("Capítulos:");
        lblCapitulos.setForeground(Color.WHITE);
        lblCapitulos.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCapitulos.setBounds(20, 338, 90, 20);
        contentPanel.add(lblCapitulos);
        
        JTextArea textAreaCapitulos = new JTextArea();
        textAreaCapitulos.setBounds(107, 307, 375, 100);
        contentPanel.add(textAreaCapitulos);
        
        JLabel lblAdvertencia = new JLabel("Separa los capítulos por comas");
        lblAdvertencia.setForeground(Color.RED);
        lblAdvertencia.setBounds(107, 293, 189, 14);
        contentPanel.add(lblAdvertencia);
        
        JSpinner spinnerNumPaginas = new JSpinner();
        spinnerNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, 18));
        spinnerNumPaginas.setBounds(150, 437, 65, 37);
        contentPanel.add(spinnerNumPaginas);
        
        JLabel lblNumPaginas = new JLabel("Nº de páginas:");
        lblNumPaginas.setForeground(Color.WHITE);
        lblNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNumPaginas.setBounds(20, 442, 119, 20);
        contentPanel.add(lblNumPaginas);
        
        JLabel lblISBN = new JLabel("ISBN:");
        lblISBN.setForeground(Color.WHITE);
        lblISBN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblISBN.setBounds(20, 35, 70, 23);
        contentPanel.add(lblISBN);
        
        textFieldISBN = new JTextField();
        textFieldISBN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldISBN.setBounds(107, 33, 312, 25);
        contentPanel.add(textFieldISBN);
        textFieldISBN.setColumns(10);
        
        JButton btnNewButton = new JButton("Buscar Libro");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(489, 33, 144, 23);
        contentPanel.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
        lblNewLabel.setBounds(0, 0, 674, 481);
        contentPanel.add(lblNewLabel);
    }

    private void setupButtons() {
        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(Color.WHITE);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Borrar");
        okButton.setForeground(Color.ORANGE);
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        okButton.addActionListener(e -> {
            String selected = listTematicas.getSelectedValue();
            if (selected != null) {
                // Here you can handle the selected category
                JOptionPane.showMessageDialog(ModLibro.this,
                    "Categoría seleccionada: " + selected,
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(ModLibro.this,
                    "Por favor, selecciona una categoría",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            }
        });
        
        JButton btnNewButton_1 = new JButton("Modificar");
        btnNewButton_1.setForeground(Color.GREEN);
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonPane.add(btnNewButton_1);
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
}