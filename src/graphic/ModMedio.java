package graphic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import com.toedter.calendar.JCalendar;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

public class ModMedio extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNumRegistro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModMedio dialog = new ModMedio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModMedio() {
		setFont(new Font("Dialog", Font.ITALIC, 18));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
		setTitle("Modificar Medio");
		setBounds(100, 100, 521, 487);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JCalendar calendarFechaCompra = new JCalendar();
		calendarFechaCompra.setBounds(20, 215, 191, 153);
		contentPanel.add(calendarFechaCompra);
		
		JLabel lblPrecioCompra = new JLabel("Precio de compra:");
		lblPrecioCompra.setForeground(new Color(255, 255, 255));
		lblPrecioCompra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrecioCompra.setBounds(20, 81, 154, 20);
		contentPanel.add(lblPrecioCompra);
		
		JLabel lblFechaCompra = new JLabel("Fecha de compra:");
		lblFechaCompra.setForeground(Color.WHITE);
		lblFechaCompra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFechaCompra.setBounds(20, 184, 161, 20);
		contentPanel.add(lblFechaCompra);
		
		JSpinner spinnerEjemplares = new JSpinner();
		spinnerEjemplares.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spinnerEjemplares.setBounds(184, 123, 83, 28);
		contentPanel.add(spinnerEjemplares);
		
		JSpinner spinnerPrecioCompra = new JSpinner();
		spinnerPrecioCompra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spinnerPrecioCompra.setBounds(184, 77, 83, 28);
		contentPanel.add(spinnerPrecioCompra);
		
		JLabel lblEjemplares = new JLabel("Ejemplares");
		lblEjemplares.setForeground(Color.WHITE);
		lblEjemplares.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEjemplares.setBounds(20, 127, 101, 20);
		contentPanel.add(lblEjemplares);
		
		JLabel lblNumRegistro = new JLabel("Número de Registro:");
		lblNumRegistro.setForeground(Color.WHITE);
		lblNumRegistro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNumRegistro.setBounds(20, 29, 173, 20);
		contentPanel.add(lblNumRegistro);
		
		textFieldNumRegistro = new JTextField();
		textFieldNumRegistro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldNumRegistro.setBounds(222, 27, 96, 23);
		contentPanel.add(textFieldNumRegistro);
		textFieldNumRegistro.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
		lblNewLabel.setBounds(0, 0, 507, 419);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnNewButton = new JButton("Modificar");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNewButton.setForeground(Color.GREEN);
			buttonPane.add(btnNewButton);
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
}