import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class GUI_REPORTES extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox_MES;
    private JComboBox comboBox_AÑO;
    LocalDate hoy = LocalDate.now();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_REPORTES frame = new GUI_REPORTES();
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
	public GUI_REPORTES() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_titulo = new JLabel("REPORTES");
		lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titulo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_titulo.setBounds(10, 10, 544, 12);
		contentPane.add(lbl_titulo);
		
		JComboBox comboBox_MES = new JComboBox();
		comboBox_MES.setModel(new DefaultComboBoxModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO ", "SEPTIEMBRE", "NOVIEMBRE", "DICIEMBRE"}));
		comboBox_MES.setSelectedIndex(hoy.getMonthValue() - 1);
		comboBox_MES.setBounds(135, 56, 113, 20);
		contentPane.add(comboBox_MES);
		
		JLabel lbl_mes = new JLabel("SELECCIONAR MES");
		lbl_mes.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_mes.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_mes.setBounds(8, 60, 96, 13);
		contentPane.add(lbl_mes);
		
		JLabel lbl_año = new JLabel("SELECCIONAR AÑO");
		lbl_año.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_año.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_año.setBounds(8, 83, 96, 13);
		contentPane.add(lbl_año);
		
		JComboBox comboBox_AÑO = new JComboBox();
		comboBox_AÑO.setModel(new DefaultComboBoxModel(new String[] {"2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035"}));
		comboBox_AÑO.setSelectedItem(String.valueOf(hoy.getYear()));
		comboBox_AÑO.setBounds(135, 79, 113, 20);
		contentPane.add(comboBox_AÑO);
		
		JLabel lbl_ingresos = new JLabel("TOTAL DE INGRESOS");
		lbl_ingresos.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_ingresos.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_ingresos.setBounds(8, 124, 117, 13);
		contentPane.add(lbl_ingresos);
		
		JLabel lbl_tot_ingresos = new JLabel(".");
		lbl_tot_ingresos.setBounds(135, 124, 115, 12);
		contentPane.add(lbl_tot_ingresos);
		
		JLabel lbl_gastos = new JLabel("TOTAL DE GASTOS");
		lbl_gastos.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_gastos.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_gastos.setBounds(8, 147, 113, 13);
		contentPane.add(lbl_gastos);
		
		JLabel lbl_tot_gastos = new JLabel(".");
		lbl_tot_gastos.setBounds(133, 147, 115, 12);
		contentPane.add(lbl_tot_gastos);
		
		JButton btn_filtrar = new JButton("FILTRAR");
		btn_filtrar.setBounds(8, 170, 240, 20);
		contentPane.add(btn_filtrar);
		
		JList list = new JList();
		list.setBounds(260, 56, 294, 134);
		contentPane.add(list);
		
		JLabel lbl_movimientos = new JLabel("LISTA DE MOVIMIENTOS");
		lbl_movimientos.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_movimientos.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_movimientos.setBounds(260, 32, 294, 12);
		contentPane.add(lbl_movimientos);
		
		JButton btn_movimientos = new JButton("VER MOVIMIENTOS DEL MES");
		btn_movimientos.setBounds(260, 200, 294, 20);
		contentPane.add(btn_movimientos);
		
		JButton btn_volver = new JButton("VOLER AL INICIO");
		btn_volver.setBounds(10, 200, 238, 20);
		contentPane.add(btn_volver);

	}
}
