package graphic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import metodos.MetodosGraficos;
import entidades.Peliculas;

public class BuscaPelicula extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    
    private Peliculas peliculaSeleccionada; // Variable para almacenar la película seleccionada
    private JDialog parent; // Para guardar referencia al diálogo padre

    /**
     * Create the dialog.
     */
    public BuscaPelicula(JDialog parent) {
        super(parent, true); // Hacemos el diálogo modal
        this.parent = parent;
        
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
        setTitle("Buscar Película");
        setFont(new Font("Dialog", Font.ITALIC, 18));
        setBounds(100, 100, 800, 481); // Un poco más ancho para acomodar más columnas
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 786, 403);
        contentPanel.add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ISAN", "Título", "Director", "Protagonistas", "Estilo", "Soporte", "Duración", "Año de Publicación"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setFont(new Font("Tahoma", Font.PLAIN, 18));
        scrollPane.setViewportView(table);
        
        // Llamar a imprimirPeliculas
        MetodosGraficos.imprimirPeliculas(table);
        
        // Panel de botones
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        // Botón Seleccionar
        JButton okButton = new JButton("Seleccionar");
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        okButton.setActionCommand("OK");
        okButton.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                // Verifica si hay una fila seleccionada en la tabla  
                if (table.getSelectedRow() != -1) {  
                    // Obtiene la película correspondiente a la fila seleccionada  
                    peliculaSeleccionada = MetodosGraficos.devuelvePelicula(table);  

                    // Asigna la película seleccionada al padre
                    if (parent instanceof ModPelicula) {  
                        ((ModPelicula) parent).setPeliculaSeleccionada(peliculaSeleccionada);  
                    }  
                    
                    // Cierra el cuadro de diálogo  
                    dispose();  
                }  
            }  
        });  

        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        
        // Botón Cancelar
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }
    
    // Getter para la película seleccionada
    public Peliculas getPeliculaSeleccionada() {
        return peliculaSeleccionada;
    }
}