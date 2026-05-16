
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class GUI_ALARMAS extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
    private String cod_use;
    private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ALARMAS frame = new GUI_ALARMAS("123");
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
	public GUI_ALARMAS(String cod_use) {
        this.cod_use = cod_use;
        setTitle("AHORRA YA!");
        setSize(334, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
 
        JPanel fondo = new JPanel();
        fondo.setBackground(new Color(230, 235, 240));
        fondo.setLayout(null);
        setContentPane(fondo);
 
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBounds(10, 11, 303, 539);
        fondo.add(card);
        card.setLayout(null);
 
        JLabel lblAlertas = new JLabel("ALERTAS");
        lblAlertas.setHorizontalAlignment(SwingConstants.CENTER);
        lblAlertas.setBounds(10, 31, 273, 14);
        lblAlertas.setFont(new Font("Tahoma", Font.BOLD, 14));
        card.add(lblAlertas);
 
        JLabel lblNotificaciones = new JLabel("Notificaciones");
        lblNotificaciones.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNotificaciones.setBounds(10, 74, 129, 14);
        card.add(lblNotificaciones);
 
        modelo = new DefaultTableModel();
        modelo.addColumn("Tipo");
        modelo.addColumn("Mensaje");
 
        table = new JTable(modelo);
        table.setBounds(10, 100, 273, 215);
        card.add(table);
 
        JButton btnMarcar = new JButton("Marcar como leidas");
        btnMarcar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (modelo.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No hay alertas pendientes");
                    return;
                }
                modelo.setRowCount(0);
                JOptionPane.showMessageDialog(null, "Alertas marcadas como leidas");
            }
        });
        btnMarcar.setBounds(10, 330, 273, 28);
        btnMarcar.setBackground(new Color(52, 152, 219));
        btnMarcar.setForeground(Color.WHITE);
        btnMarcar.setFocusPainted(false);
        btnMarcar.setBorderPainted(false);
        btnMarcar.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnMarcar);
 
        JButton btnVolverAlInicio = new JButton("Volver al inicio");
        btnVolverAlInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
                PRE.setVisible(true);
                dispose();
            }
        });
        btnVolverAlInicio.setBounds(10, 370, 273, 28);
        btnVolverAlInicio.setBackground(new Color(231, 76, 60));
        btnVolverAlInicio.setForeground(Color.WHITE);
        btnVolverAlInicio.setFocusPainted(false);
        btnVolverAlInicio.setBorderPainted(false);
        btnVolverAlInicio.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnVolverAlInicio);
 
        cargarAlertas();
    }
 
	private void cargarAlertas() {
	    double presupuesto = ConsultasBD.getTotalPresupuesto(cod_use);
	    double gastos      = ConsultasBD.getTotalGastos(cod_use);
	    double saldo       = ConsultasBD.getSaldo(cod_use);

	    // Alarma 1: gastos al limite del presupuesto
	    if (gastos >= presupuesto && presupuesto > 0) {
	        modelo.addRow(new Object[]{
	            "PRESUPUESTO",
	            "Alcanzaste tu limite de $" + String.format("%.2f", presupuesto)
	        });
	    }

	    // Alarma 2: saldo negativo
	    if (saldo < 0) {
	        modelo.addRow(new Object[]{
	            "SALDO", "Tu saldo es negativo: $" + String.format("%.2f", saldo)
	        });
	    }

	    // Alarma 3: fecha de corte mañana
	    LinkedList<Presupuesto> presupuestos = ConsultasBD.getPresupuestos(cod_use);
	    java.time.LocalDate manana = java.time.LocalDate.now().plusDays(1);
	    for (Presupuesto p : presupuestos) {
	        if (p.getPre_fecha_final() != null) {
	            java.time.LocalDate corte = p.getPre_fecha_final().toLocalDate();
	            if (corte.equals(manana)) {
	                modelo.addRow(new Object[]{
	                    "CORTE",
	                    "Tu presupuesto vence mañana (" + manana + ")"
	                });
	            }
	        }
	    }

	    // Alarma 4: limites por apartado (sin cambios)
	    LinkedList<Apartado> apartados    = ConsultasBD.getApartados(cod_use);
	    LinkedList<Gasto>    listaGastos  = ConsultasBD.getGastos(cod_use);
	    for (Apartado apa : apartados) {
	        String categoria = apa.getApa_categoria();
	        double limite    = apa.getApa_limite();
	        double totalCat  = 0;
	        for (Gasto g : listaGastos) {
	            if (g.getGas_categoria().equalsIgnoreCase(categoria))
	                totalCat += g.getGas_monto();
	        }
	        if (totalCat > limite) {
	            modelo.addRow(new Object[]{
	                "LIMITE " + categoria,
	                "Gastaste $" + String.format("%.2f", totalCat) + " en " + categoria +
	                ", tu limite era $" + String.format("%.2f", limite)
	            });
	        }
	    }

	    if (modelo.getRowCount() == 0) {
	        modelo.addRow(new Object[]{ "OK", "Todo esta en orden" });
	    }
	}
}