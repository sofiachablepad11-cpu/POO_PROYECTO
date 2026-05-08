import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_PRESUPUESTO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_limite;
	private JTable table_detalle;
	private JTextField textField;
	private JTable table_presupuesto;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_titulo = new JLabel("PRESUPUESTO");
		lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titulo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_titulo.setBounds(7, 10, 819, 12);
		contentPane.add(lbl_titulo);
		
		JLabel lbl_gastos = new JLabel("GASTOS");
		lbl_gastos.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_gastos.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_gastos.setBounds(10, 79, 113, 13);
		contentPane.add(lbl_gastos);
		
		JComboBox comboBox_gastos = new JComboBox();
		comboBox_gastos.setModel(new DefaultComboBoxModel(new String[] {"COMIDA", "", "TRANSPORTE", "", "VIVIENDA (RENTA)", "", "SERVICIOS (LUZ, AGUA, INTERNET)", "", "COMPRAS", "", "ENTRETENIMIENTO", "", "SALUD", "", "EDUCACIÓN", "", "ROPA", "", "MASCOTAS"}));
		comboBox_gastos.setBounds(133, 75, 135, 21);
		contentPane.add(comboBox_gastos);
		
		JLabel lbl_limite = new JLabel("LIMITE");
		lbl_limite.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_limite.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_limite.setBounds(10, 115, 113, 13);
		contentPane.add(lbl_limite);
		
		text_limite = new JTextField();
		text_limite.setBounds(133, 112, 135, 18);
		contentPane.add(text_limite);
		text_limite.setColumns(10);
		
		JButton btn_guardar = new JButton("GUARDAR");
		btn_guardar.setBounds(10, 145, 258, 20);
		contentPane.add(btn_guardar);
		
		table_detalle = new JTable();
		table_detalle.setToolTipText("");
		table_detalle.setBounds(285, 43, 258, 186);
		contentPane.add(table_detalle);
		
		JButton btn_detalle = new JButton("VER DETALLE");
		btn_detalle.setBounds(10, 175, 258, 20);
		contentPane.add(btn_detalle);
		
		JButton btn_volver = new JButton("VOLER AL INICIO");
		btn_volver.setBounds(10, 209, 258, 20);
		contentPane.add(btn_volver);
		
		JLabel lbl_presupuesto = new JLabel("ESTABLECER INGRESO");
		lbl_presupuesto.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_presupuesto.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_presupuesto.setBounds(10, 46, 113, 13);
		contentPane.add(lbl_presupuesto);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(133, 43, 135, 18);
		contentPane.add(textField);
		
		JButton btn_detalle_1 = new JButton("ESTADO DEL PRESUPUESTO");
		btn_detalle_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_detalle_1.setBounds(560, 209, 214, 20);
		contentPane.add(btn_detalle_1);
		
		table_presupuesto = new JTable();
		table_presupuesto.setBounds(560, 43, 214, 152);
		contentPane.add(table_presupuesto);

	}
}
