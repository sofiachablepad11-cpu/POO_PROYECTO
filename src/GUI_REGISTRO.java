
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import conec.CONECTA;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class GUI_REGISTRO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GUI_REGISTRO frame = new GUI_REGISTRO();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public GUI_REGISTRO() {

		
		setTitle("AHORRA YA! - REGISTRO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(15, 45, 90));
		setContentPane(contentPane);

		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(15, 45, 90));
		panel.setBounds(0, 0, 334, 561);

		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AHORRA YA!");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(95, 37, 145, 35);
		panel.add(lblNewLabel);
		
		JLabel lblregi = new JLabel("REGISTRO");
		lblregi.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblregi.setForeground(new Color(255, 255, 255));
		lblregi.setBounds(105, 83, 127, 35);
		panel.add(lblregi);

	
		JLabel lbl_nombre = new JLabel("Nombre");
		lbl_nombre.setBounds(40, 129, 200, 20);
		lbl_nombre.setForeground(Color.WHITE);
		panel.add(lbl_nombre);

		JTextField text_nombre = new JTextField();
		text_nombre.setBounds(40, 153, 250, 35);
		panel.add(text_nombre);
		
		JLabel lbl_correo = new JLabel("Correo");
		lbl_correo.setBounds(40, 257, 200, 20);
		lbl_correo.setForeground(Color.WHITE);
		panel.add(lbl_correo);

		JTextField text_correo = new JTextField();
		text_correo.setBounds(40, 278, 250, 35);
		panel.add(text_correo);

		
		JLabel lbl_contraseña = new JLabel("Contraseña");
		lbl_contraseña.setBounds(40, 324, 200, 20);
		lbl_contraseña.setForeground(Color.WHITE);
		panel.add(lbl_contraseña);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(40, 344, 250, 35);
		panel.add(passwordField);

		
		JLabel lbl_confirmar = new JLabel("Confirmar contraseña");
		lbl_confirmar.setBounds(40, 384, 200, 20);
		lbl_confirmar.setForeground(Color.WHITE);
		panel.add(lbl_confirmar);

		JPasswordField passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(40, 404, 250, 35);
		panel.add(passwordField_1);

		
		JButton btn_registrar = new JButton("REGISTRAR");
		btn_registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = CONECTA.conectar();
					String sql = "INSERT INTO usuarios (\"USU_NOMBRE\", \"USU_APELLIDO\", \"USU_CORREO\", \"USU_PASSWORD\") VALUES (?,?,?,?)";
					PreparedStatement ps = con.prepareStatement(sql);
					
					ps.setString(1, text_nombre.getText());
				    ps.setString(2, textField.getText()); 
				    ps.setString(3, text_correo.getText());
				    ps.setString(4, new String(passwordField.getPassword()));

				    ps.executeUpdate();
				    JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
				    } catch (Exception ex) {
				    	JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				        }
				    }
				});
		
		btn_registrar.setBounds(40, 464, 250, 40);
		btn_registrar.setBackground(new Color(0, 123, 255));
		btn_registrar.setForeground(Color.WHITE);
		panel.add(btn_registrar);

		
		JButton btn_volver = new JButton("VOLVER AL LOGIN");
		btn_volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GUI_LOGIN login = new GUI_LOGIN();
				login.setVisible(true);

				
				dispose();
				
			}
		});
		btn_volver.setBounds(81, 515, 159, 35);
		panel.add(btn_volver);
		
		JLabel lbl_nombre_1 = new JLabel("Apellido");
		lbl_nombre_1.setForeground(Color.WHITE);
		lbl_nombre_1.setBounds(40, 188, 200, 20);
		panel.add(lbl_nombre_1);
		
		textField = new JTextField();
		textField.setBounds(40, 212, 250, 35);
		panel.add(textField);
		
	}
}