package graphic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BuscarRevista extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarRevista dialog = new BuscarRevista();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarRevista() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
		setTitle("Buscar Revista");
		setFont(new Font("Dialog", Font.ITALIC, 18));
		setBounds(100, 100, 670, 415);
		getContentPane().setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ISBN", "Titulo", "Tematica", "Indice", "Año de Publicación", "Nº Páginas", "Num Registro"
			}
		));
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
	}

}
