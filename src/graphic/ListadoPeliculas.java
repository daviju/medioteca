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

import metodos.MetodosGraficos;

public class ListadoPeliculas extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable tablePeliculas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoPeliculas dialog = new ListadoPeliculas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoPeliculas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
		setTitle("Listado Peliculas");
		setFont(new Font("Dialog", Font.ITALIC, 18));
		setBounds(100, 100, 1084, 465);
		getContentPane().setLayout(new BorderLayout());
		{
			JScrollPane scrollPanePeliculas = new JScrollPane();
			getContentPane().add(scrollPanePeliculas, BorderLayout.CENTER);
			{
				tablePeliculas = new JTable();
				tablePeliculas.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"ISAN", "Titulo", "Director", "Protagonistas", "Estilo", "Soporte", "Duración", "Año Publicación", "ID Medio"
					}
				));
				tablePeliculas.setFont(new Font("Tahoma", Font.PLAIN, 18));
				scrollPanePeliculas.setViewportView(tablePeliculas);
			}
		}
		
		MetodosGraficos.rellenarTablaPeliculas(tablePeliculas);
	}
	
	// Añadir getter para la tabla
    public JTable getTablePeliculas() {
        return tablePeliculas;
    }

}
