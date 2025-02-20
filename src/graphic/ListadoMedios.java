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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import metodos.MetodosGraficos;

public class ListadoMedios extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable tableMedios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoMedios dialog = new ListadoMedios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoMedios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
		setFont(new Font("Dialog", Font.ITALIC, 18));
		setTitle("Listado Medios");
		setBounds(100, 100, 689, 448);
		getContentPane().setLayout(new BorderLayout());
		
		JScrollPane scrollPaneMedios = new JScrollPane();
		getContentPane().add(scrollPaneMedios, BorderLayout.CENTER);
		
		tableMedios = new JTable();
		tableMedios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Número de Registro", "Fecha de Aquisición", "Precio de Compra", "Nº de Ejemplares"
			}
		));
		tableMedios.setBorder(UIManager.getBorder("List.noFocusBorder"));
		tableMedios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPaneMedios.setViewportView(tableMedios);
		
		MetodosGraficos.rellenarTablaMedios(tableMedios);
	}
	
	// Añadir getter para la tabla
    public JTable getTableMedios() {
        return tableMedios;
    }
}
