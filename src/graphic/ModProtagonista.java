package graphic;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ModProtagonista extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldNombreProta;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ModProtagonista dialog = new ModProtagonista();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ModProtagonista() {
        setTitle("Modificar Protagonista");
        setFont(new Font("Dialog", Font.ITALIC, 18));
        setBounds(100, 100, 601, 226);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Nombre del Protagonista
        JLabel lblNombreProta = new JLabel("Nombre del Protagonista: ");
        lblNombreProta.setForeground(Color.WHITE);
        lblNombreProta.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNombreProta.setBounds(10, 76, 209, 22);
        contentPanel.add(lblNombreProta);

        textFieldNombreProta = new JTextField();
        textFieldNombreProta.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldNombreProta.setBounds(229, 73, 348, 28);
        contentPanel.add(textFieldNombreProta);
        textFieldNombreProta.setColumns(10);
        
        JButton btnNewButton = new JButton("Buscar Protagonista");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(189, 25, 198, 23);
        contentPanel.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
        lblNewLabel.setBounds(0, 0, 587, 148);
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
            JButton cancelButton = new JButton("Cancelar");
            cancelButton.setForeground(Color.RED);
            cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);
        }
    }
}
