package graphic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metodos.MetodosGraficos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class CrearProtagonista extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public static JTextField textFieldNombreProta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearProtagonista dialog = new CrearProtagonista();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearProtagonista() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
		setTitle("Crear Protagonista");
		setFont(new Font("Dialog", Font.ITALIC, 18));
		setBounds(100, 100, 601, 172);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombreProta = new JLabel("Nombre del Protagonista: ");
		lblNombreProta.setForeground(Color.WHITE);
		lblNombreProta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombreProta.setBounds(10, 33, 209, 22);
		contentPanel.add(lblNombreProta);
		
		textFieldNombreProta = new JTextField();
		textFieldNombreProta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldNombreProta.setBounds(229, 33, 348, 28);
		contentPanel.add(textFieldNombreProta);
		textFieldNombreProta.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
		lblNewLabel.setBounds(0, 0, 587, 94);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);

				// Acción del botón "Guardar"
				okButton.addActionListener(e -> {
				    // Validación del campo de texto
				    String nombreProta = getNombreProtagonista();
				    
				    if (nombreProta.isEmpty()) {
				    	
				        // Si el campo está vacío, mostrar un mensaje de advertencia
				        JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre del protagonista.", "Campo vacío", javax.swing.JOptionPane.WARNING_MESSAGE);
				    
				    } else {
				        // Si no está vacío, guardamos el protagonista
				        MetodosGraficos.guardarProtagonista(this);
				    }
				});


			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(e -> dispose());
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	// Método para obtener el nombre del protagonista
	public String getNombreProtagonista() {
		return textFieldNombreProta.getText().trim();
	}
}
