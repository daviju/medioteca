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
import metodos.MetodosGraficos;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import java.awt.Color;

public class CrearMedio extends JDialog {

	private static final long serialVersionUID = 1L;
	public final JPanel contentPanel = new JPanel();
	
	// Campo estático para el JCalendar
	public static JCalendar calendarFechaCompra;
    public static JSpinner spinnerPrecioCompra;
    public static JSpinner spinnerEjemplares;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearMedio dialog = new CrearMedio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearMedio() {
		setFont(new Font("Dialog", Font.ITALIC, 18));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
		setTitle("Crear Medio");
		setBounds(100, 100, 391, 469);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		// Usar el campo estático calendarFechaCompra
		calendarFechaCompra = new JCalendar();
		calendarFechaCompra.setBounds(20, 179, 191, 153);
		contentPanel.add(calendarFechaCompra);
		
		JLabel lblPrecioCompra = new JLabel("Precio de compra:");
		lblPrecioCompra.setForeground(new Color(255, 255, 255));
		lblPrecioCompra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrecioCompra.setBounds(20, 43, 154, 20);
		contentPanel.add(lblPrecioCompra);
		
		JLabel lblFechaCompra = new JLabel("Fecha de compra:");
		lblFechaCompra.setForeground(Color.WHITE);
		lblFechaCompra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFechaCompra.setBounds(20, 148, 161, 20);
		contentPanel.add(lblFechaCompra);
		
		// Usar el campo estático para spinnerEjemplares
		spinnerEjemplares = new JSpinner();
		spinnerEjemplares.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spinnerEjemplares.setBounds(184, 88, 83, 28);
		contentPanel.add(spinnerEjemplares);
		
		// Usar el campo estático para spinnerPrecioCompra
		spinnerPrecioCompra = new JSpinner();
		spinnerPrecioCompra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spinnerPrecioCompra.setBounds(184, 39, 83, 28);
		contentPanel.add(spinnerPrecioCompra);
		
		JLabel lblNewLabel = new JLabel("Ejemplares");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(20, 92, 101, 20);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\fondonormal.jpg"));
		lblNewLabel_1.setBounds(0, 0, 377, 391);
		contentPanel.add(lblNewLabel_1);
		
		// Panel de botones
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		// Botón para crear el medio
		{
	        JButton okButton = new JButton("Crear");
	        okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        okButton.setActionCommand("OK");
	        okButton.addActionListener(e -> MetodosGraficos.crearMedio(this));
	        buttonPane.add(okButton);
	        getRootPane().setDefaultButton(okButton);
		}
		
		// Botón para cancelar
		{
	        JButton cancelButton = new JButton("Cancel");
	        cancelButton.setForeground(Color.RED);
	        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        cancelButton.addActionListener(e -> dispose());
	        buttonPane.add(cancelButton);
		}
	}
}
