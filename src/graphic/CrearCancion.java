package graphic;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import metodos.MetodosGraficos;

import java.awt.event.*;

public class CrearCancion extends JDialog {

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
            CrearCancion dialog = new CrearCancion(String discoISMN, String registro);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public CrearCancion(String discoISMN, String registro) {
        this.discoISMN = discoISMN;
        this.registro = registro;
        
    	setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
    	setTitle("Crear Cancion");
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
        setBounds(100, 100, 450, 320);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(null); // Usamos AbsoluteLayout
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Título
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitulo.setBounds(10, 20, 80, 30); // Posición absoluta
        contentPanel.add(lblTitulo);
        
        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtTitulo.setBounds(100, 20, 250, 30); // Posición absoluta
        contentPanel.add(txtTitulo);
        txtTitulo.setColumns(20);

        // Duración
        JLabel lblDuracion = new JLabel("Duración:");
        lblDuracion.setForeground(Color.WHITE);
        lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDuracion.setBounds(10, 70, 100, 30); // Posición absoluta
        contentPanel.add(lblDuracion);

        spinnerDuracion = new JSpinner();
        spinnerDuracion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        spinnerDuracion.setBounds(120, 70, 100, 30); // Posición absoluta
        contentPanel.add(spinnerDuracion);

        // Intérprete
        JLabel lblInterprete = new JLabel("Intérprete:");
        lblInterprete.setForeground(Color.WHITE);
        lblInterprete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblInterprete.setBounds(10, 120, 100, 30); // Posición absoluta
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
        scrollPaneInterpretes.setBounds(120, 120, 250, 100); // Posición absoluta
        contentPanel.add(scrollPaneInterpretes);
        
        JLabel lblFondo = new JLabel("");
        lblFondo.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
        lblFondo.setBounds(0, 0, 436, 322);
        contentPanel.add(lblFondo);

        // Botones
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton okButton = new JButton("OK");
            okButton.setForeground(Color.GREEN);
            okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
            okButton.setActionCommand("OK");
            okButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
                    if (validateForm()) {
                        // Estos valores deberían pasarse al crear el diálogo o establecerse de alguna manera
                        String discoISMN = /* obtener de algún lugar */;
                        int discoMedioNumRegistro = /* obtener de algún lugar */;
                        
                        MetodosGraficos.guardarCancion(
                            txtTitulo,
                            spinnerDuracion,
                            discoISMN,
                            discoMedioNumRegistro
                        );
                        
                        if (/* la operación fue exitosa */) {
                            dispose(); // Cerrar el diálogo
                        }
                    }
                }
            });
            }
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
    
    private boolean validateForm() {
        // Validar título
        if (txtTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "El título de la canción no puede estar vacío", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            
            txtTitulo.requestFocus();
            return false;
        }
        
        // Validar duración
        int duracion = (int) spinnerDuracion.getValue();
        if (duracion <= 0) {
            JOptionPane.showMessageDialog(this, 
                "La duración debe ser mayor a 0", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            
            spinnerDuracion.requestFocus();
            return false;
        }
        
        return true;
    }
}
