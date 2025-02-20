package graphic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;
import com.toedter.calendar.JYearChooser;

import entidades.Medio;
import metodos.MetodosGraficos;

import com.toedter.calendar.JCalendar;

public class CrearDisco extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    public static JTextField textFieldTitulo;
    public static JComboBox<String> comboBoxInterprete;
    public static JComboBox<String> comboBoxEstilo;
    public static JRadioButton rdbtnFisico;
    public static JRadioButton rdbtnDigital;
    public static JTextField textFieldISMN;
    public static JTextField textFieldMedio;

    public static void main(String[] args) {
        try {
            CrearDisco dialog = new CrearDisco();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CrearDisco() {
    	setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
        setTitle("Crear Disco");
        setBounds(100, 100, 540, 433);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitulo.setBounds(20, 100, 100, 20);
        contentPanel.add(lblTitulo);
        
        textFieldTitulo = new JTextField();
        textFieldTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldTitulo.setBounds(130, 96, 361, 28);
        contentPanel.add(textFieldTitulo);
        textFieldTitulo.setColumns(10);
        
        JLabel lblInterprete = new JLabel("Intérprete:");
        lblInterprete.setForeground(Color.WHITE);
        lblInterprete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblInterprete.setBounds(20, 148, 100, 20);
        contentPanel.add(lblInterprete);
        
        comboBoxInterprete = new JComboBox<>(new String[]{"Queen", "The Who", "El Fary"});
        comboBoxInterprete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxInterprete.setBounds(130, 144, 200, 28);
        contentPanel.add(comboBoxInterprete);
        
        JLabel lblEstilo = new JLabel("Estilo:");
        lblEstilo.setForeground(Color.WHITE);
        lblEstilo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEstilo.setBounds(20, 190, 100, 20);
        contentPanel.add(lblEstilo);
        
        comboBoxEstilo = new JComboBox<>(new String[]{"Rock", "Pop", "Folk"});
        comboBoxEstilo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxEstilo.setBounds(130, 185, 200, 30);
        contentPanel.add(comboBoxEstilo);
        
        JLabel lblSoporte = new JLabel("Soporte:");
        lblSoporte.setForeground(Color.WHITE);
        lblSoporte.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSoporte.setBounds(20, 239, 100, 20);
        contentPanel.add(lblSoporte);
        
        rdbtnFisico = new JRadioButton("Físico");
        rdbtnFisico.setForeground(Color.BLACK);
        rdbtnFisico.setFont(new Font("Tahoma", Font.PLAIN, 17));
        rdbtnFisico.setBounds(130, 239, 80, 20);
        rdbtnFisico.setSelected(true);
        contentPanel.add(rdbtnFisico);
        
        rdbtnDigital = new JRadioButton("Digital");
        rdbtnDigital.setForeground(Color.BLACK);
        rdbtnDigital.setFont(new Font("Tahoma", Font.PLAIN, 18));
        rdbtnDigital.setBounds(250, 239, 80, 20);
        contentPanel.add(rdbtnDigital);
        
        ButtonGroup groupSoporte = new ButtonGroup();
        groupSoporte.add(rdbtnFisico);
        groupSoporte.add(rdbtnDigital);
        
        JLabel lblAnio = new JLabel("Año de publicación:");
        lblAnio.setForeground(Color.WHITE);
        lblAnio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblAnio.setBounds(20, 273, 179, 20);
        contentPanel.add(lblAnio);
        
        JLabel lblISMN = new JLabel("ISMN:");
        lblISMN.setForeground(Color.WHITE);
        lblISMN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblISMN.setBounds(20, 63, 62, 14);
        contentPanel.add(lblISMN);
        
        textFieldISMN = new JTextField();
        textFieldISMN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldISMN.setBounds(130, 57, 361, 28);
        contentPanel.add(textFieldISMN);
        textFieldISMN.setColumns(10);
        
        JLabel lblMedio = new JLabel("Buscar Medio:");
        lblMedio.setForeground(Color.WHITE);
        lblMedio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMedio.setBounds(20, 23, 112, 14);
        contentPanel.add(lblMedio);
        
        textFieldMedio = new JTextField();
        textFieldMedio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldMedio.setBounds(142, 22, 96, 23);
        contentPanel.add(textFieldMedio);
        textFieldMedio.setColumns(10);
        
        JButton btnNewButton = new JButton("Buscar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BuscaMedio dialogBuscar = new BuscaMedio(CrearDisco.this); // Pasa la referencia del diálogo actual
                
                dialogBuscar.setModal(true);
                dialogBuscar.setLocationRelativeTo(null);
                dialogBuscar.setVisible(true);
                
                // Cuando se cierra el diálogo BuscarMedio, obtén el medio seleccionado
                Medio medioSeleccionado = dialogBuscar.getMedioSeleccionado();
                if (medioSeleccionado != null) {
                    setMedioSeleccionado(medioSeleccionado);
                }
            }
        });
        
        
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(253, 22, 89, 23);
        contentPanel.add(btnNewButton);
        
        JYearChooser yearChooser = new JYearChooser();
        yearChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 18));
        yearChooser.setBounds(188, 266, 62, 28);
        contentPanel.add(yearChooser);
        
        JLabel lblFondo = new JLabel("");
        lblFondo.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
        lblFondo.setBounds(0, 0, 526, 355);
        contentPanel.add(lblFondo);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton okButton = new JButton("OK");
        okButton.setForeground(Color.GREEN);
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (validateForm()) {
                    MetodosGraficos.guardarDisco(
                        textFieldISMN,
                        textFieldTitulo,
                        comboBoxInterprete,
                        comboBoxEstilo,
                        rdbtnFisico,
                        rdbtnDigital,
                        yearChooser,
                        textFieldMedio
                    );
                
	                int respuesta = JOptionPane.showConfirmDialog(
	                    CrearDisco.this, 
	                    "El Disco actualmente se encuentra sin canciones asociadas. ¿Desea añadir canciones?", 
	                    "Advertencia", 
	                    JOptionPane.YES_NO_OPTION,
	                    JOptionPane.WARNING_MESSAGE
	                );
	                
	                if (respuesta == JOptionPane.YES_OPTION) {
	                    String ismn = textFieldISMN.getText().trim();
	                    
	                    CrearCancion crearCancionDialog = new CrearCancion(ismn);
	                    crearCancionDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	                    crearCancionDialog.setVisible(true);
	                }
            	}
            }
        });

        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.RED);
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
        
    }
    
    public void setMedioSeleccionado(Medio medio) {
        if (medio != null) {
            textFieldMedio.setText(String.valueOf(medio.numRegistro));
            
        }
    }
    
    private boolean validateForm() {
        // Validar ISMN
        if (textFieldISMN.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "El campo ISMN no puede estar vacío", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            textFieldISMN.requestFocus();
            return false;
        }
        
        // Validar Título
        if (textFieldTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "El campo Título no puede estar vacío", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            textFieldTitulo.requestFocus();
            return false;
        }
        
        // Validar que se haya seleccionado un Medio
        if (textFieldMedio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Debe seleccionar un medio", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar que se haya seleccionado un soporte
        if (!rdbtnFisico.isSelected() && !rdbtnDigital.isSelected()) {
            JOptionPane.showMessageDialog(this, 
                "Debe seleccionar un soporte", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}
