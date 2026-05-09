
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_ALARMAS extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private String cod_use;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ALARMAS frame = new GUI_ALARMAS();
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
	public GUI_ALARMAS() {
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
        
        JLabel lblAlertas = new JLabel("ALERTAS");
        lblAlertas.setBounds(114, 31, 112, 14);
        lblAlertas.setFont(new Font("Tahoma", Font.BOLD, 14));
        card.add(lblAlertas);
        
        JLabel lblNewLabel = new JLabel("Notificaciones");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(10, 74, 129, 14);
        card.add(lblNewLabel);
        
        table = new JTable();
        table.setBounds(10, 100, 295, 215);
        card.add(table);
        
        JButton btnNewButton = new JButton("Marcar como leídas");
        btnNewButton.setBounds(10, 346, 295, 28);
        btnNewButton.setBackground(new Color(52, 152, 219)); 
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnNewButton);
        
        JButton btnVolverAlInicio = new JButton("Volver al inicio");
        btnVolverAlInicio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
				PRE.setVisible(true);
				dispose();
        	}
        });
        btnVolverAlInicio.setBounds(10, 396, 295, 28); 
        btnVolverAlInicio.setBackground(new Color(231, 76, 60)); 
        btnVolverAlInicio.setForeground(Color.WHITE);
        btnVolverAlInicio.setFocusPainted(false);
        btnVolverAlInicio.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnVolverAlInicio);
	}

}
