package graphic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JCalendar;

public class ModDisco extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldTitulo;
    private JComboBox<String> comboBoxInterprete;
    private JComboBox<String> comboBoxEstilo;
    private JRadioButton rdbtnFisico;
    private JRadioButton rdbtnDigital;
    private JTextField textFieldISMN;

    public static void main(String[] args) {
        try {
            ModDisco dialog = new ModDisco();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ModDisco() {
        setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
        setTitle("Modificar Disco");
        setBounds(100, 100, 540, 586);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitulo.setBounds(20, 101, 100, 20);
        contentPanel.add(lblTitulo);

        textFieldTitulo = new JTextField();
        textFieldTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldTitulo.setBounds(130, 97, 361, 28);
        contentPanel.add(textFieldTitulo);
        textFieldTitulo.setColumns(10);

        JLabel lblInterprete = new JLabel("Intérprete:");
        lblInterprete.setForeground(Color.WHITE);
        lblInterprete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblInterprete.setBounds(20, 146, 100, 20);
        contentPanel.add(lblInterprete);

        comboBoxInterprete = new JComboBox<>(new String[]{"Queen", "The Who", "El Fary"});
        comboBoxInterprete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxInterprete.setBounds(130, 142, 200, 28);
        contentPanel.add(comboBoxInterprete);

        JLabel lblEstilo = new JLabel("Estilo:");
        lblEstilo.setForeground(Color.WHITE);
        lblEstilo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEstilo.setBounds(20, 192, 100, 20);
        contentPanel.add(lblEstilo);

        comboBoxEstilo = new JComboBox<>(new String[]{"Rock", "Pop", "Folk"});
        comboBoxEstilo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxEstilo.setBounds(130, 187, 200, 30);
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
        lblAnio.setBounds(20, 270, 179, 20);
        contentPanel.add(lblAnio);

        JCalendar calendar = new JCalendar();
        calendar.setBounds(20, 301, 221, 163);
        contentPanel.add(calendar);

        JLabel lblISMN = new JLabel("ISMN:");
        lblISMN.setForeground(Color.WHITE);
        lblISMN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblISMN.setBounds(20, 65, 62, 14);
        contentPanel.add(lblISMN);

        textFieldISMN = new JTextField();
        textFieldISMN.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldISMN.setBounds(130, 58, 361, 28);
        contentPanel.add(textFieldISMN);
        textFieldISMN.setColumns(10);
        
        JButton btnNewButton = new JButton("Buscar Disco");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(20, 25, 144, 23);
        contentPanel.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
        lblNewLabel.setBounds(10, 0, 516, 508);
        contentPanel.add(lblNewLabel);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnNewButton_1 = new JButton("Modificar");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_1.setForeground(Color.GREEN);
        buttonPane.add(btnNewButton_1);

        JButton okButton = new JButton("Borrar");
        okButton.setForeground(Color.ORANGE);
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(
                        ModDisco.this,
                        "El Disco actualmente se encuentra sin canciones asociadas. ¿Desea añadir canciones?",
                        "Advertencia",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    ModCancion modCancionDialog = new ModCancion();
                    modCancionDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    modCancionDialog.setVisible(true);
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.RED);
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }
}
