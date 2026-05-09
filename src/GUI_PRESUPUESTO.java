
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_PRESUPUESTO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String cod_use;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_PRESUPUESTO frame = new GUI_PRESUPUESTO();
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
	public GUI_PRESUPUESTO() {
		setTitle("AHORRA YA!");
        setSize(350, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel fondo = new JPanel();
        fondo.setBackground(new Color(230, 235, 240)); 
        fondo.setLayout(null);
        setContentPane(fondo);
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBounds(10, 11, 315, 539);
        fondo.add(card);
        card.setLayout(null);
        
        JLabel lblpresupuesto = new JLabel("PRESUPUESTO");
        lblpresupuesto.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblpresupuesto.setBounds(102, 21, 112, 14);
        card.add(lblpresupuesto);
        
        JLabel lblNewLabel_1 = new JLabel("Presupuesto");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(10, 66, 121, 14);
        card.add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(10, 91, 282, 25);
        card.add(textField);
        
        JButton btnAgregargasto = new JButton("Guardar presupuesto");
        btnAgregargasto.setBounds(10, 127, 282, 31);
        btnAgregargasto.setBackground(new Color(46, 204, 113)); 
        btnAgregargasto.setForeground(Color.WHITE);
        btnAgregargasto.setFocusPainted(false);
        btnAgregargasto.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnAgregargasto.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnAgregargasto);
        
        JLabel lblNewLabel_1_1 = new JLabel("Agregar Gasto");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1.setBounds(10, 179, 121, 14);
        card.add(lblNewLabel_1_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(10, 204, 282, 25);
        card.add(textField_1);
        
        JButton btnAgregarGasto = new JButton("Agregar gasto");
        btnAgregarGasto.setBounds(10, 240, 282, 31);
        btnAgregarGasto.setBackground(new Color(52, 152, 219)); 
        btnAgregarGasto.setForeground(Color.WHITE);
        btnAgregarGasto.setFocusPainted(false);
        btnAgregarGasto.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnAgregarGasto);
        
        JLabel lblNewLabel_1_2 = new JLabel("Disponible");
        lblNewLabel_1_2.setBounds(22, 296, 86, 14);
        card.add(lblNewLabel_1_2);
        
        JLabel lblsaldo = new JLabel("3,000.00");
        lblsaldo.setForeground(new Color(0, 153, 0));
        lblsaldo.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblsaldo.setBounds(58, 311, 247, 51);
        card.add(lblsaldo);
        
        JLabel lblNewLabel_3 = new JLabel("$");
        lblNewLabel_3.setForeground(new Color(0, 153, 0));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_3.setBounds(32, 321, 18, 34);
        card.add(lblNewLabel_3);
        
        JButton btnVolverAlInicio_1 = new JButton("Volver al inicio");
        btnVolverAlInicio_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
				PRE.setVisible(true);
				dispose();
        	}
        });
        btnVolverAlInicio_1.setForeground(Color.WHITE);
        btnVolverAlInicio_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVolverAlInicio_1.setFocusPainted(false);
        btnVolverAlInicio_1.setBackground(new Color(231, 76, 60));
        btnVolverAlInicio_1.setBounds(10, 435, 295, 28);
        card.add(btnVolverAlInicio_1);

	}

}
