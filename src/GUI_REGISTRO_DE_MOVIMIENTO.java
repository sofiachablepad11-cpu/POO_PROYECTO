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
import javax.swing.JTextArea;
import javax.swing.JButton;

public class GUI_REGISTRO_DE_MOVIMIENTO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_monto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_REGISTRO_DE_MOVIMIENTO frame = new GUI_REGISTRO_DE_MOVIMIENTO();
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
	public GUI_REGISTRO_DE_MOVIMIENTO() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox_movimiento = new JComboBox();
		comboBox_movimiento.setModel(new DefaultComboBoxModel(new String[] {"INGRESO", "GASTO"}));
		comboBox_movimiento.setBounds(116, 32, 136, 21);
		contentPane.add(comboBox_movimiento);
		
		JLabel lbl_titulo = new JLabel("REGISTRO DE MOVIMIENTO");
		lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titulo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_titulo.setBounds(10, 10, 416, 12);
		contentPane.add(lbl_titulo);
		
		JLabel lbl_tipo = new JLabel("TIPO");
		lbl_tipo.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_tipo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_tipo.setBounds(10, 32, 96, 13);
		contentPane.add(lbl_tipo);
		
		text_monto = new JTextField();
		text_monto.setBounds(116, 63, 136, 18);
		contentPane.add(text_monto);
		text_monto.setColumns(10);
		
		JLabel lbl_monto = new JLabel("MONTO");
		lbl_monto.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_monto.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_monto.setBounds(10, 66, 96, 13);
		contentPane.add(lbl_monto);
		
		JLabel lbl_fecha = new JLabel("FECHA");
		lbl_fecha.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_fecha.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_fecha.setBounds(10, 99, 242, 12);
		contentPane.add(lbl_fecha);
		
		JComboBox comboBox_DIA = new JComboBox();
		comboBox_DIA.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_DIA.setBounds(10, 123, 242, 20);
		contentPane.add(comboBox_DIA);
		
		JComboBox comboBox_MES = new JComboBox();
		comboBox_MES.setModel(new DefaultComboBoxModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"}));
		comboBox_MES.setBounds(10, 153, 242, 20);
		contentPane.add(comboBox_MES);
		
		JComboBox comboBox_AÑO = new JComboBox();
		comboBox_AÑO.setModel(new DefaultComboBoxModel(new String[] {"2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035"}));
		comboBox_AÑO.setBounds(10, 183, 242, 20);
		contentPane.add(comboBox_AÑO);
		
		JTextArea textArea_descripcion = new JTextArea();
		textArea_descripcion.setBounds(281, 60, 136, 143);
		contentPane.add(textArea_descripcion);
		
		JLabel lbl_descripcion = new JLabel("DESCRIPCION");
		lbl_descripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_descripcion.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_descripcion.setBounds(281, 41, 136, 12);
		contentPane.add(lbl_descripcion);
		
		JButton btn_guardar = new JButton("GUARDAR");
		btn_guardar.setBounds(10, 221, 190, 20);
		contentPane.add(btn_guardar);
		
		JButton btn_cancelar = new JButton("CANCELAR");
		btn_cancelar.setBounds(227, 221, 190, 20);
		contentPane.add(btn_cancelar);

	}
}
