package mx.uam.tmc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import java.awt.Toolkit;

public class VentanaMaquina extends JFrame {
	// Exportar la VentanaTuring

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String Direccion;
	private static VentanaMaquina frame = new VentanaMaquina();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public VentanaMaquina() {
		setTitle("Maquina de Turing");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Las lokis\\Documents\\My Bluetooth\\images.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);

		JLabel lblDireccinDelArchivo = new JLabel("Direcci\u00F3n del archivo:");
		lblDireccinDelArchivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccinDelArchivo.setBounds(10, 24, 145, 14);
		contentPane.add(lblDireccinDelArchivo);

		JLabel apuntador = new JLabel("^");
		apuntador.setFont(new Font("Tahoma", Font.BOLD, 11));
		apuntador.setBounds(124, 128, 21, 14);
		contentPane.add(apuntador);

		JTextField cuadro2 = new JTextField();
		cuadro2.setFont(new Font("Courier New", Font.PLAIN, 14));
		cuadro2.setEditable(false);
		cuadro2.setBounds(89, 80, 275, 37);
		contentPane.add(cuadro2, BorderLayout.CENTER);
		cuadro2.setColumns(10);

		JTextField estado = new JTextField();
		estado.setBounds(89, 158, 86, 20);
		contentPane.add(estado);
		estado.setColumns(10);
		estado.setEditable(false);

		JTextField posicion = new JTextField();
		posicion.setEditable(false);
		posicion.setBounds(294, 158, 86, 20);
		contentPane.add(posicion);
		posicion.setColumns(10);

		// Manda llamar el archivo
		JTextField caja1 = new JTextField();
		caja1.setForeground(new Color(0, 0, 0));
		caja1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		caja1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Direccion = caja1.getText();
					MaquinaTuring.Proceso(Direccion);
					cuadro2.setText(MaquinaTuring.getCadena());
					estado.setText("q" + MaquinaTuring.getEstado());
					caja1.setEditable(false);
					apuntador.setLocation((7 * (MaquinaTuring.getPosi() - 1) + 91 + MaquinaTuring.getPosi()), 128);
					posicion.setText(MaquinaTuring.getPosi() + "");

				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Dirección no encontrada");
					caja1.setText("");
				}
			}
		});
		caja1.setBounds(89, 49, 275, 20);
		contentPane.add(caja1, BorderLayout.NORTH);
		caja1.setColumns(10);

		// Realiza un determinado número de pasos
		JButton btnSaltar = new JButton("Saltar pasos");
		btnSaltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int value = 0;
				if (caja1.getText().equals(""))
					JOptionPane.showMessageDialog(null, "No se ha escrito la dirección");
				else {
					try {
						try {
							value = Integer.parseInt(
									JOptionPane.showInputDialog("Ingrese el número de pasos que se quiere saltar: "));
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "No ingresó pasos.");
						}
						int i = 0;
						while ((!MaquinaTuring.paro()) && (i < value)) {
							MaquinaTuring.creaMaquina();
							i++;

						}
						if (MaquinaTuring.getEstado() == -1) {
							estado.setText("h");
							btnSaltar.setEnabled(false);

						} else {
							if (MaquinaTuring.flag1() == false) {
								estado.setText("q" + MaquinaTuring.getEstado());
								cuadro2.setText(MaquinaTuring.getCinta());
								apuntador.setLocation(
										(7 * (MaquinaTuring.getPosi() - 1) + 91 + MaquinaTuring.getPosi()), 128);
								posicion.setText(MaquinaTuring.getPosi() + "");
							} else {
								JOptionPane.showMessageDialog(null, "ERROR, hay un simbolo indefinido");
								caja1.setText("");
								cuadro2.setText("");
								estado.setText("");
								caja1.setEnabled(true);
								i = value;
							}

						}
						if (MaquinaTuring.getEstado() == -1)
							JOptionPane.showMessageDialog(null, "Demasiados pasos");
					} catch (NullPointerException e) {
						JOptionPane.showMessageDialog(null, "No se ha escrito la dirección");
					}
				}

			}

		});
		btnSaltar.setBounds(224, 214, 123, 23);
		contentPane.add(btnSaltar);

		// Realiza un paso a la vez
		JButton btnComenzar = new JButton("Siguiente");
		btnComenzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!MaquinaTuring.paro()) {
						MaquinaTuring.creaMaquina();
						if (MaquinaTuring.getEstado() == -1) {
							estado.setText("h");
							btnComenzar.setEnabled(false);
							btnSaltar.setEnabled(false);
						} else {
							if (MaquinaTuring.flag1() == false) {
								estado.setText("q" + MaquinaTuring.getEstado());
								cuadro2.setText(MaquinaTuring.getCinta());
								apuntador.setLocation(
										(7 * (MaquinaTuring.getPosi() - 1) + 91 + MaquinaTuring.getPosi()), 128);
								posicion.setText(MaquinaTuring.getPosi() + "");
							} else {
								JOptionPane.showMessageDialog(null, "ERROR, ");
								caja1.setText("");
								cuadro2.setText("");
								estado.setText("");
								caja1.setEnabled(true);
								posicion.setText("");
							}

						}
					} else {
						btnComenzar.setEnabled(false);
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "No se ha escrito la dirección");
				}

			}
		});
		btnComenzar.setBounds(112, 214, 102, 23);
		contentPane.add(btnComenzar);

		// Se sale del programa
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		btnSalir.setBounds(357, 214, 89, 23);
		contentPane.add(btnSalir);

		JLabel lblEstado = new JLabel("Estado: ");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBounds(29, 161, 46, 14);
		contentPane.add(lblEstado);

		JLabel lblPosicin = new JLabel("Posici\u00F3n: ");
		lblPosicin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPosicin.setBounds(205, 161, 64, 14);
		contentPane.add(lblPosicin);

		// Limpia el contenido de las cajas y reinicializa todo
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				caja1.setText("");
				cuadro2.setText("");
				estado.setText("");
				caja1.setEditable(true);
				btnComenzar.setEnabled(true);
				btnSaltar.setEnabled(true);
				Direccion = "";
				posicion.setText("");
				MaquinaTuring.edo0 = "";
				MaquinaTuring.edos = new HashMap<>();
				MaquinaTuring.flag1 = false;
				MaquinaTuring.pila = new ArrayList<String>();

			}
		});
		btnLimpiar.setBounds(10, 214, 89, 23);
		contentPane.add(btnLimpiar);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
