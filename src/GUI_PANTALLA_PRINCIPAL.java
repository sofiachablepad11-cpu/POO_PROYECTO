import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class GUI_PANTALLA_PRINCIPAL extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_PANTALLA_PRINCIPAL frame = new GUI_PANTALLA_PRINCIPAL();
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
	public GUI_PANTALLA_PRINCIPAL() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 266, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_saldo_actual = new JLabel("SALDO ACTUAL");
		lbl_saldo_actual.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_saldo_actual.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_saldo_actual.setBounds(20, 54, 96, 13);
		contentPane.add(lbl_saldo_actual);
		
		JLabel lbl_ingresos = new JLabel("TOTAL DE INGRESOS");
		lbl_ingresos.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_ingresos.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_ingresos.setBounds(20, 86, 118, 13);
		contentPane.add(lbl_ingresos);
		
		JLabel lbl_gastos = new JLabel("TOTAL DE GASTOS");
		lbl_gastos.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_gastos.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_gastos.setBounds(20, 109, 118, 13);
		contentPane.add(lbl_gastos);
		
		JButton btn_ingreso = new JButton("AGREGAR INGRESO");
		btn_ingreso.setBounds(20, 145, 214, 20);
		contentPane.add(btn_ingreso);
		
		JButton btn_gasto = new JButton("AGREGAR GASTO");
		btn_gasto.setBounds(20, 175, 214, 20);
		contentPane.add(btn_gasto);
		
		JButton btn_reporte = new JButton("VER REPORTES");
		btn_reporte.setBounds(20, 205, 214, 20);
		contentPane.add(btn_reporte);
		
		JButton btn_alertas = new JButton("VER ALERTAS");
		btn_alertas.setBounds(20, 235, 214, 20);
		contentPane.add(btn_alertas);
		
		JLabel lbl_actual = new JLabel(".");
		lbl_actual.setBounds(115, 54, 119, 12);
		contentPane.add(lbl_actual);
		
		JLabel lbl_titulo = new JLabel("TITULO");
		lbl_titulo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titulo.setBounds(94, 10, 44, 12);
		contentPane.add(lbl_titulo);
		
		JLabel lbl_tot_ingresos = new JLabel(".");
		lbl_tot_ingresos.setBounds(145, 86, 89, 12);
		contentPane.add(lbl_tot_ingresos);
		
		JLabel lbl_tot_gastos = new JLabel(".");
		lbl_tot_gastos.setBounds(145, 109, 97, 12);
		contentPane.add(lbl_tot_gastos);
		
		JButton btn_presupuesto = new JButton("ESTABLECER PRESUPUESTO");
		btn_presupuesto.setBounds(20, 262, 214, 20);
		contentPane.add(btn_presupuesto);
		
		JButton btn_cerrar = new JButton("CERRAR SESION");
		btn_cerrar.setBounds(20, 319, 214, 20);
		contentPane.add(btn_cerrar);

	}

}
