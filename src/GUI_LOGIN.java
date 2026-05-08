import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_LOGIN extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_LOGIN frame = new GUI_LOGIN();
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
	public GUI_LOGIN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_login = new JLabel("LOGIN");
		lbl_login.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_login.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_login.setBounds(151, 20, 96, 13);
		contentPane.add(lbl_login);
		
		textField = new JTextField();
		textField.setBounds(151, 56, 225, 18);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lbl_correo = new JLabel("CORREO");
		lbl_correo.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_correo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_correo.setBounds(49, 59, 96, 13);
		contentPane.add(lbl_correo);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(151, 89, 225, 18);
		contentPane.add(passwordField);
		
		JLabel lbl_contraseña = new JLabel("CONTRASEÑA");
		lbl_contraseña.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_contraseña.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_contraseña.setBounds(49, 92, 96, 13);
		contentPane.add(lbl_contraseña);
		
		JButton btn_iniciar = new JButton("INICIAR SESION");
		btn_iniciar.setBounds(55, 181, 321, 20);
		contentPane.add(btn_iniciar);
		
		JButton btn_registrar = new JButton("REGISTRATE");
		btn_registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_registrar.setBounds(55, 144, 321, 20);
		contentPane.add(btn_registrar);

	}
}
