package graphic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class mainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu frame = new mainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainMenu() {
		setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 20));
		setTitle("MEDIOTECA VIJU");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1019, 834);
		
		JMenuBar navBar = new JMenuBar();
		setJMenuBar(navBar);
		
		JMenu mnFicherosMaestros = new JMenu("Ficheros Maestros");
		navBar.add(mnFicherosMaestros);
		
		JMenu mnMedios = new JMenu("Medios");
		mnFicherosMaestros.add(mnMedios);
		
		
		
		// LIBROS
		JMenu mnLibros = new JMenu("Libros");
		mnMedios.add(mnLibros);
		
		JMenuItem mntmCrearLibros = new JMenuItem("Crear");
		mntmCrearLibros.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        CrearLibro dialog = new CrearLibro();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		mnLibros.add(mntmCrearLibros);
		
		JMenuItem mntmModificarLibros = new JMenuItem("Modificar/Borrar");
		mntmModificarLibros.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ModLibro dialog = new ModLibro();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		
		mnLibros.add(mntmModificarLibros);
		
		
		
		// REVISTAS
		JMenu mnRevistas = new JMenu("Revistas");
		mnMedios.add(mnRevistas);
		
		JMenuItem mntmCrearRevistas = new JMenuItem("Crear");
		mntmCrearRevistas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        CrearRevista dialog = new CrearRevista();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		mnRevistas.add(mntmCrearRevistas);
		
		JMenuItem mntmModificarRevistas = new JMenuItem("Modificar/Borrar");
		mntmModificarRevistas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ModRevista dialog = new ModRevista();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		
		mnRevistas.add(mntmModificarRevistas);
		
		
		
		// DISCOS
		JMenu mnDiscos = new JMenu("Discos");
		mnMedios.add(mnDiscos);
		
		JMenuItem mntmCrearDiscos = new JMenuItem("Crear");
		mntmCrearDiscos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        CrearDisco dialog = new CrearDisco();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		mnDiscos.add(mntmCrearDiscos);
		
		JMenuItem mntmModificarDiscos = new JMenuItem("Modificar/Borrar");
		mntmModificarDiscos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ModDisco dialog = new ModDisco();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		
		mnDiscos.add(mntmModificarDiscos);
		
		
		
		// PELICULAS
		JMenu mnPeliculas = new JMenu("Peliculas");
		mnMedios.add(mnPeliculas);
		
		JMenuItem mntmCrearPeliculas = new JMenuItem("Crear");
		mntmCrearPeliculas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        CrearPeliculas dialog = new CrearPeliculas();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		mnPeliculas.add(mntmCrearPeliculas);
		
		JMenuItem mntmModificarPeliculas = new JMenuItem("Modificar/Borrar");
		mntmModificarPeliculas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ModPelicula dialog = new ModPelicula();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		
		mnPeliculas.add(mntmModificarPeliculas);
		
		
		// MEDIOS
		JMenuItem mntmCrearmedio = new JMenuItem("Crear Medio");
		mntmCrearmedio.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        CrearMedio dialog = new CrearMedio();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		
		mnMedios.add(mntmCrearmedio);
		
		JMenuItem mntmCrearProtagonista = new JMenuItem("Crear Protagonista");
		mntmCrearProtagonista.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        CrearProtagonista dialog = new CrearProtagonista();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		
		mnMedios.add(mntmCrearProtagonista);
		
		
		
		// LISTADOS
		JMenu mnListados = new JMenu("Listados");
		navBar.add(mnListados);
		
		
		// LISTADO MEDIOS
		JMenuItem mntmListadoGeneral = new JMenuItem("Listado de Medios");
		mntmListadoGeneral.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ListadoMedios dialog = new ListadoMedios();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		
		mnListados.add(mntmListadoGeneral);
		
		
		// LISTADO PELICULAS
		JMenuItem mntmListadoPeliculas = new JMenuItem("Listado Peliculas");
		mntmListadoPeliculas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ListadoPeliculas dialog = new ListadoPeliculas();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		
		mnListados.add(mntmListadoPeliculas);
		
		
		// LISTADO DISCOS
		JMenuItem mntmListadoDiscos = new JMenuItem("Listado Discos");
		mntmListadoDiscos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ListadoDiscos dialog = new ListadoDiscos();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		
		mnListados.add(mntmListadoDiscos);
		
		
		JMenuItem mntmListadoLibros = new JMenuItem("Listado Libros");
		mnListados.add(mntmListadoLibros);
		
		
		
		// BUSCAR MEDIO
		JMenu mnBuscarMedio = new JMenu("Buscar Medios");
		navBar.add(mnBuscarMedio);
		
		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        BuscarMedio dialog = new BuscarMedio();
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		});
		mnBuscarMedio.add(mntmBuscar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// FONDO
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("C:\\Users\\Daviju\\Desktop\\2º DAW\\Recuperaciones\\Programación\\Copia\\medioteca\\images\\Logo.png"));
		lblFondo.setBounds(0, 0, 1005, 775);
		contentPane.add(lblFondo);
		

	}
}
