import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_REGISTRO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_nombre;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField text_correo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_REGISTRO frame = new GUI_REGISTRO();
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
	public GUI_REGISTRO() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_registro = new JLabel("REGISTRO");
		lbl_registro.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_registro.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_registro.setBounds(144, 10, 97, 12);
		contentPane.add(lbl_registro);
		
		text_nombre = new JTextField();
		text_nombre.setBounds(162, 49, 211, 18);
		contentPane.add(text_nombre);
		text_nombre.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(162, 132, 211, 18);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(162, 105, 211, 18);
		contentPane.add(passwordField_1);
		
		text_correo = new JTextField();
		text_correo.setBounds(162, 77, 211, 18);
		contentPane.add(text_correo);
		text_correo.setColumns(10);
		
		JLabel lbl_correo = new JLabel("CORREO");
		lbl_correo.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_correo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_correo.setBounds(10, 80, 96, 13);
		contentPane.add(lbl_correo);
		
		JLabel lbl_contraseña = new JLabel("CONTRASEÑA");
		lbl_contraseña.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_contraseña.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_contraseña.setBounds(10, 110, 96, 13);
		contentPane.add(lbl_contraseña);
		
		JButton btn_registrar = new JButton("REGISTRAR");
		btn_registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_registrar.setBounds(10, 180, 363, 20);
		contentPane.add(btn_registrar);
		
		JButton btn_iniciar = new JButton("VOLVER AL INICIO");
		btn_iniciar.setBounds(10, 227, 363, 20);
		contentPane.add(btn_iniciar);
		
		JLabel lbl_nombre = new JLabel("NOMBRE");
		lbl_nombre.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_nombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_nombre.setBounds(10, 52, 96, 13);
		contentPane.add(lbl_nombre);
		
		JLabel lbl_confirmar = new JLabel("CONFIRMAR CONTRASEÑA");
		lbl_confirmar.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_confirmar.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_confirmar.setBounds(10, 135, 142, 13);
		contentPane.add(lbl_confirmar);

	}
}
