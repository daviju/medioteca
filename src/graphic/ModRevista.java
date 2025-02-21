package graphic;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import com.toedter.calendar.JYearChooser;

public class ModRevista extends JDialog {
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
    public static JTextField textFieldISBN;
    public static JTextField textFieldMedio;

    public static void main(String[] args) {
        try {
            ModRevista dialog = new ModRevista();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ModRevista() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
        setTitle("Modificar Revista");
        setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
        setupMainFrame();
        setupComponents();
        setupList();
        setupButtons();
    }

    private void setupMainFrame() {
        setBounds(100, 100, 626, 548);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
    }

    private void setupComponents() {
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitulo.setBounds(20, 68, 77, 20);
        contentPanel.add(lblTitulo);

        textFieldTitulo = new JTextField();
        textFieldTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldTitulo.setBounds(107, 60, 312, 37);
        contentPanel.add(textFieldTitulo);
        textFieldTitulo.setColumns(10);

        JLabel lblCategories = new JLabel("Temática:");
        lblCategories.setForeground(Color.WHITE);
        lblCategories.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCategories.setBounds(20, 144, 90, 37);
        contentPanel.add(lblCategories);

        selectedItemLabel = new JLabel("Temática seleccionada: Ninguna");
        selectedItemLabel.setForeground(Color.WHITE);
        selectedItemLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        selectedItemLabel.setBounds(107, 204, 300, 25);
        contentPanel.add(selectedItemLabel);

        JLabel lblIndice = new JLabel("Artículos:");
        lblIndice.setForeground(Color.WHITE);
        lblIndice.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblIndice.setBounds(20, 261, 90, 20);
        contentPanel.add(lblIndice);

        textAreaIndice = new JTextArea();
        textAreaIndice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JScrollPane scrollPaneIndice = new JScrollPane(textAreaIndice);
        scrollPaneIndice.setBounds(107, 230, 375, 100);
        contentPanel.add(scrollPaneIndice);

        JLabel lblAnio = new JLabel("Año:");
        lblAnio.setForeground(Color.WHITE);
        lblAnio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblAnio.setBounds(20, 350, 90, 20);
        contentPanel.add(lblAnio);

        JLabel lblNumPaginas = new JLabel("Páginas:");
        lblNumPaginas.setForeground(Color.WHITE);
        lblNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNumPaginas.setBounds(20, 400, 90, 20);
        contentPanel.add(lblNumPaginas);

        SpinnerModel pagesModel = new SpinnerNumberModel(1, 1, 1000, 1);
        spinnerNumPaginas = new JSpinner(pagesModel);
        spinnerNumPaginas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spinnerNumPaginas.setBounds(107, 396, 100, 30);
        contentPanel.add(spinnerNumPaginas);
    }

    private void setupList() {
        listModel = new DefaultListModel<>();
        String[] categories = {"Deportiva", "Interés General", "Cotilleos", "Ciencia", "Tecnología", "Moda", "Cocina"};
        for (String category : categories) {
            listModel.addElement(category);
        }

        listTematicas = new JList<>(listModel);
        listTematicas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTematicas.setFont(new Font("Tahoma", Font.PLAIN, 16));

        listTematicas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = listTematicas.getSelectedValue();
                if (selected != null) {
                    selectedItemLabel.setText("Temática seleccionada: " + selected);
                }
            }
        });

        scrollPaneTematicas = new JScrollPane(listTematicas);
        scrollPaneTematicas.setBounds(107, 108, 312, 100);
        contentPanel.add(scrollPaneTematicas);

        yearChooser = new JYearChooser();
        yearChooser.setBounds(94, 350, 100, 30);
        contentPanel.add(yearChooser);
        
        JButton btnNewButton = new JButton("Buscar Revista");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(20, 22, 147, 23);
        contentPanel.add(btnNewButton);
        
        textFieldISBN = new JTextField();
        textFieldISBN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldISBN.setBounds(196, 22, 135, 24);
        contentPanel.add(textFieldISBN);
        textFieldISBN.setColumns(10);
        
        JLabel lblMedio = new JLabel("Nº Registro");
        lblMedio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMedio.setBounds(341, 23, 100, 21);
        contentPanel.add(lblMedio);
        
        textFieldMedio = new JTextField();
        textFieldMedio.setBounds(448, 22, 108, 23);
        contentPanel.add(textFieldMedio);
        textFieldMedio.setColumns(10);
    }

    private void setupButtons() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Borrar");
        okButton.setForeground(Color.ORANGE);
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        okButton.addActionListener(e -> dispose());
        
        JButton btnNewButton_1 = new JButton("Modificar");
        btnNewButton_1.setForeground(Color.GREEN);
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonPane.add(btnNewButton_1);
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setForeground(Color.RED);
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }
}
