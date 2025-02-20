package graphic;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ModCancion extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtTitulo;
    private JSpinner spinnerDuracion;
    private JList<String> listInterpretes;
    private DefaultListModel<String> listModelInterpretes;
    private JScrollPane scrollPaneInterpretes;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ModCancion dialog = new ModCancion();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ModCancion() {
    	setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
    	setTitle("Modificar Canción");
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
        setBounds(100, 100, 450, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(null); // Usamos AbsoluteLayout
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Título
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitulo.setBounds(10, 60, 80, 30); // Posición absoluta
        contentPanel.add(lblTitulo);
        
        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtTitulo.setBounds(100, 60, 250, 30); // Posición absoluta
        contentPanel.add(txtTitulo);
        txtTitulo.setColumns(20);

        // Duración
        JLabel lblDuracion = new JLabel("Duración:");
        lblDuracion.setForeground(Color.WHITE);
        lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDuracion.setBounds(10, 112, 100, 30); // Posición absoluta
        contentPanel.add(lblDuracion);

        spinnerDuracion = new JSpinner();
        spinnerDuracion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        spinnerDuracion.setBounds(120, 112, 100, 30); // Posición absoluta
        contentPanel.add(spinnerDuracion);

        // Intérprete
        JLabel lblInterprete = new JLabel("Intérprete:");
        lblInterprete.setForeground(Color.WHITE);
        lblInterprete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblInterprete.setBounds(10, 190, 100, 30); // Posición absoluta
        contentPanel.add(lblInterprete);

        // Crear lista de intérpretes
        listModelInterpretes = new DefaultListModel<>();
        String[] interpretes = {
            "Shakira",
            "Bad Bunny",
            "Ed Sheeran",
            "Adele",
            "Juanes"
        };
        for (String interprete : interpretes) {
            listModelInterpretes.addElement(interprete);
        }

        // Crear y configurar la lista JList
        listInterpretes = new JList<>(listModelInterpretes);
        listInterpretes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listInterpretes.setFont(new Font("Tahoma", Font.PLAIN, 16));
        listInterpretes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        listInterpretes.setBackground(new Color(250, 250, 250));

        // Agregar listener de selección
        listInterpretes.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selected = listInterpretes.getSelectedValue();
                    if (selected != null) {
                        System.out.println("Intérprete seleccionado: " + selected);
                    }
                }
            }
        });

        // Crear y configurar el JScrollPane
        scrollPaneInterpretes = new JScrollPane(listInterpretes);
        scrollPaneInterpretes.setBounds(120, 164, 250, 100); // Posición absoluta
        contentPanel.add(scrollPaneInterpretes);
        
        JButton btnNewButton = new JButton("Buscar Cancion");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(10, 21, 182, 23);
        contentPanel.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
        lblNewLabel.setBounds(0, 0, 436, 322);
        contentPanel.add(lblNewLabel);

        // Botones
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnNewButton_1 = new JButton("Modificar");
        btnNewButton_1.setForeground(Color.GREEN);
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonPane.add(btnNewButton_1);
        {
            JButton okButton = new JButton("Borrar");
            okButton.setForeground(Color.ORANGE);
            okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
            okButton.setActionCommand("OK");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
        }
        {
        	JButton cancelButton = new JButton("Cancel");
            cancelButton.setForeground(Color.RED);
            cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
            cancelButton.addActionListener(e -> dispose());
            buttonPane.add(cancelButton);
        }
    }
}
