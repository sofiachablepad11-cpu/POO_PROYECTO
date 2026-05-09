
import java.awt.EventQueue;
import javax.swing.*;

import conec.CONECTA;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class GUI_LOGIN extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_correo;
	
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GUI_LOGIN frame = new GUI_LOGIN();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public GUI_LOGIN() {

		setTitle("AHORRA YA!");
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
		lblNewLabel.setBounds(85, 35, 150, 68);
		panel.add(lblNewLabel);
		
		JLabel lbllogin = new JLabel("LOGIN");
		lbllogin.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lbllogin.setForeground(new Color(255, 255, 255));
		lbllogin.setBounds(118, 90, 139, 68);
		panel.add(lbllogin);
		
		text_correo = new JTextField();
		text_correo.setBounds(35, 216, 271, 32);
		panel.add(text_correo);
		text_correo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CORREO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(35, 191, 78, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("CONTRASEÑA");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(35, 282, 106, 14);
		panel.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(35, 318, 271, 32);
		panel.add(passwordField);
		
		JButton btn_registrar = new JButton("INICIAR SESION");
		btn_registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String correo = text_correo.getText();
		        String contra = new String(passwordField.getPassword());

		        try {
		            Connection con = CONECTA.conectar();

		            String sql = "SELECT * FROM usuarios WHERE \"USU_CORREO\"=? AND \"USU_PASSWORD\"=?";
		            PreparedStatement ps = con.prepareStatement(sql);

		            ps.setString(1, correo);
		            ps.setString(2, contra);

		            ResultSet rs = ps.executeQuery();

		            if (rs.next()) {
		            	String usuarioID = rs.getString("USU_CODIGO");
		                JOptionPane.showMessageDialog(null, "Bienvenido");

		                GUI_PANTALLA_PRINCIPAL ventana = new GUI_PANTALLA_PRINCIPAL(usuarioID);
		                ventana.setVisible(true);
		                dispose();

		            } else {
		                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
		            }

		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error");
		        }

			}
		});
		btn_registrar.setBounds(35, 389, 271, 40);
		btn_registrar.setBackground(new Color(0, 123, 255));
		btn_registrar.setForeground(Color.WHITE);
		panel.add(btn_registrar);

		
		JLabel lblNewLabel_2 = new JLabel("¿NO TIENES UNA CUENTA?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(30, 459, 185, 14);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("REGISTRAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_REGISTRO registro = new GUI_REGISTRO();
				registro.setVisible(true);
				dispose();	
				
			}
		});
		btnNewButton_1.setBounds(180, 455, 129, 23);
		panel.add(btnNewButton_1);
	}
}