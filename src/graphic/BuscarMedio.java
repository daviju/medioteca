package graphic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class BuscarMedio extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarMedio dialog = new BuscarMedio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarMedio() {
		setFont(new Font("Dialog", Font.ITALIC, 12));
		setTitle("Buscar medio");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
		setBounds(100, 100, 559, 201);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de identificador para buscar el medio:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 33, 347, 22);
		contentPanel.add(lblNewLabel);
		
		JComboBox comboBoxIdentificador = new JComboBox();
		comboBoxIdentificador.setBounds(355, 36, 163, 22);
		contentPanel.add(comboBoxIdentificador);
		
		// AÑADIMOS ITEMS
		comboBoxIdentificador.addItem("Nº de registro");
		comboBoxIdentificador.addItem("ISBN (Libro)");
		comboBoxIdentificador.addItem("ISBN (Revista)");
		comboBoxIdentificador.addItem("ISMN");
		comboBoxIdentificador.addItem("ISAN");

		
		JLabel lblNewLabel_1 = new JLabel("Buscar Medio:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 85, 124, 22);
		contentPanel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(145, 85, 253, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
		lblNewLabel_2.setBounds(0, 0, 545, 123);
		contentPanel.add(lblNewLabel_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton okButton = new JButton("Buscar");
			okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			okButton.addActionListener(e -> metodos.MetodosGraficos.buscarMedio(comboBoxIdentificador, textField));
			buttonPane.add(okButton);
			{
		        JButton cancelButton = new JButton("Cancel");
		        cancelButton.setForeground(Color.RED);
		        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		        cancelButton.addActionListener(e -> dispose());
		        buttonPane.add(cancelButton);
			}
		}
	}
}
