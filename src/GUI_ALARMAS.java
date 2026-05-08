import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JButton;

public class GUI_ALARMAS extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_titulo = new JLabel("ALERTAS");
		lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titulo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_titulo.setBounds(10, 10, 416, 12);
		contentPane.add(lbl_titulo);
		
		JLabel lbl_gastos = new JLabel("MENSAJE GENERAL");
		lbl_gastos.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_gastos.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_gastos.setBounds(10, 32, 113, 13);
		contentPane.add(lbl_gastos);
		
		JLabel lbl_tot_gastos = new JLabel(".");
		lbl_tot_gastos.setBounds(135, 32, 291, 12);
		contentPane.add(lbl_tot_gastos);
		
		JList list = new JList();
		list.setBounds(10, 62, 416, 128);
		contentPane.add(list);
		
		JButton btn_leidas = new JButton("MARCAR COMO LEIDAS");
		btn_leidas.setBounds(10, 202, 416, 20);
		contentPane.add(btn_leidas);
		
		JButton btn_volver = new JButton("VOLVER AL INICIO");
		btn_volver.setBounds(10, 233, 416, 20);
		contentPane.add(btn_volver);

	}

}
