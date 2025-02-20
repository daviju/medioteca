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
import entidades.Medio;

public class BuscaMedio extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    
    private Medio medioSeleccionado; // Añadimos variable para almacenar el medio seleccionado
    private JDialog parent; // Para guardar referencia al diálogo padre

    /**
     * Create the dialog.
     */
    public BuscaMedio(JDialog parent) {
        super(parent, true); // Hacemos el diálogo modal
        this.parent = parent;
        
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
        setTitle("Buscar Medio");
        setFont(new Font("Dialog", Font.ITALIC, 18));
        setBounds(100, 100, 635, 481);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 621, 403);
        contentPanel.add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Número de Registro", "Fecha de Adquisición", "Precio de Compra", "Número de Ejemplares"
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
        
        // Llamar a imprimirMedios
        MetodosGraficos.imprimirMedios(table);
        
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
                    // Obtiene el medio correspondiente a la fila seleccionada  
                    medioSeleccionado = MetodosGraficos.devuelveMedio(table);  

                    // Comprueba qué tipo de ventana es el "parent" y le asigna el medio seleccionado  
                    if (parent instanceof CrearLibro) {  
                        ((CrearLibro) parent).setMedioSeleccionado(medioSeleccionado);  

                    } else if (parent instanceof CrearRevista) {  
                        ((CrearRevista) parent).setMedioSeleccionado(medioSeleccionado);  

                    } else if (parent instanceof CrearPeliculas) {  
                        ((CrearPeliculas) parent).setMedioSeleccionado(medioSeleccionado);  

                    } else if (parent instanceof CrearDisco) {  
                        ((CrearDisco) parent).setMedioSeleccionado(medioSeleccionado);  
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
    
    // Getter para el medio seleccionado
    public Medio getMedioSeleccionado() {
        return medioSeleccionado;
    }
}