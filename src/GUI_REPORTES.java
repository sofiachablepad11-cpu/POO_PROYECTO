
import java.awt.Color;
import java.awt.EventQueue;
import conec.CONECTA;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class GUI_REPORTES extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox_MES;
    private JComboBox comboBox_AÑO;
    LocalDate hoy = LocalDate.now();
    private JTable table;
    
    
    
    private String cod_use;
    private JLabel totalIng;
    private JLabel totalGas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_REPORTES frame = new GUI_REPORTES("123");
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
	public GUI_REPORTES(String cod_use) {
	    this.cod_use = cod_use;
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
        
        JLabel lblReportes = new JLabel("REPORTES");
        lblReportes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblReportes.setBounds(103, 11, 154, 14);
        card.add(lblReportes);
        
        comboBox_MES = new JComboBox();
        comboBox_MES.setModel(new DefaultComboBoxModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE", ""}));
        comboBox_MES.setSelectedIndex(hoy.getMonthValue() - 1);
        comboBox_MES.setBounds(10, 61, 124, 22);
        card.add(comboBox_MES);
        
        comboBox_AÑO = new JComboBox();
        comboBox_AÑO.setModel(new DefaultComboBoxModel(new String[] {"2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035"}));
        comboBox_AÑO.setBounds(168, 61, 124, 22);
        comboBox_AÑO.setSelectedItem(String.valueOf(hoy.getYear()));
        card.add(comboBox_AÑO);
        
        JPanel panelIngresos = new JPanel();
        panelIngresos.setBackground(new Color(46, 204, 113));
        panelIngresos.setBounds(10, 110, 295, 70);
        panelIngresos.setLayout(null);
        card.add(panelIngresos);

        JLabel lblIng = new JLabel("Ingresos");
        lblIng.setForeground(Color.WHITE);
        lblIng.setBounds(10, 10, 100, 20);
        panelIngresos.add(lblIng);

        totalIng = new JLabel("$0.00");
        totalIng.setForeground(Color.WHITE);
        totalIng.setFont(new Font("Segoe UI", Font.BOLD, 18));
        totalIng.setBounds(10, 30, 200, 30);
        panelIngresos.add(totalIng);
        
        JPanel panelGastos = new JPanel();
        panelGastos.setBackground(new Color(255, 80, 80));
        panelGastos.setBounds(10, 190, 295, 70);
        panelGastos.setLayout(null);
        card.add(panelGastos);

        JLabel lblGas = new JLabel("Gastos");
        lblGas.setForeground(Color.WHITE);
        lblGas.setBounds(10, 10, 100, 20);
        panelGastos.add(lblGas);

        totalGas = new JLabel("$0.00");
        totalGas.setForeground(Color.WHITE);
        totalGas.setFont(new Font("Segoe UI", Font.BOLD, 18));
        totalGas.setBounds(10, 30, 200, 30);
        panelGastos.add(totalGas);
        
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ex) {
        		 try {
        	            Connection con = conec.CONECTA.conectar();

        	            String sqlIng = "SELECT SUM(\"ING_MONTO\") AS total FROM ingresos WHERE \"USU_CODIGO\" = ?";
        	            PreparedStatement psIng = con.prepareStatement(sqlIng);
        	            psIng.setObject(1, java.util.UUID.fromString(cod_use));
        	            ResultSet rsIng = psIng.executeQuery();

        	            double totalIngresos = 0;
        	            if (rsIng.next()) {
        	                totalIngresos = rsIng.getDouble("total");
        	            }

        	            totalIng.setText("$ " + totalIngresos);

        	            String sqlGas = "SELECT SUM(\"GAS_MONTO\") AS total FROM gastos WHERE \"USU_CODIGO\" = ?";
        	            PreparedStatement psGas = con.prepareStatement(sqlGas);
        	            psGas.setObject(1, java.util.UUID.fromString(cod_use));
        	            ResultSet rsGas = psGas.executeQuery();

        	            double totalGastos = 0;
        	            if (rsGas.next()) {
        	                totalGastos = rsGas.getDouble("total");
        	            }

        	            totalGas.setText("$ " + totalGastos);

        	        } catch (Exception ex2) {
        	            ex2.printStackTrace();
        	            JOptionPane.showMessageDialog(null, "Error al cargar totales");
        	        }
        	    }
        	});

        btnActualizar.setBounds(10, 272, 295, 35);
        btnActualizar.setBackground(new Color(0, 120, 215));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        card.add(btnActualizar);
        
        JLabel lblNewLabel_1 = new JLabel("Movimientos");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(10, 318, 121, 14);
        card.add(lblNewLabel_1);
        
        table = new JTable();
        table.setBounds(10, 322, 295, 122);
        card.add(table);
        
        JButton btnVerDetalles = new JButton("Ver Detalles");
        btnVerDetalles.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        	        Connection con = conec.CONECTA.conectar();

        	        String sqlMov =
        	            "SELECT \"ING_MONTO\" AS MONTO, 'INGRESO' AS TIPO, \"ING_FECHA\" AS FECHA FROM ingresos WHERE \"USU_CODIGO\" = ? " +
        	            "UNION ALL " +
        	            "SELECT \"GAS_MONTO\" AS MONTO, 'GASTO' AS TIPO, \"GAS_FECHA\" AS FECHA FROM gastos WHERE \"USU_CODIGO\" = ?";

        	        PreparedStatement ps = con.prepareStatement(sqlMov);
        	        ps.setObject(1, java.util.UUID.fromString(cod_use));
        	        ps.setObject(2, java.util.UUID.fromString(cod_use));

        	        ResultSet rs = ps.executeQuery();

        	        DefaultTableModel modelo = new DefaultTableModel();
        	        modelo.addColumn("Monto");
        	        modelo.addColumn("Tipo");
        	        modelo.addColumn("Fecha");

        	        while (rs.next()) {
        	            modelo.addRow(new Object[]{
        	                rs.getDouble("MONTO"),
        	                rs.getString("TIPO"),
        	                rs.getDate("FECHA")
        	            });
        	        }

        	        table.setModel(modelo);
        	    } catch (Exception ex) {
        	        ex.printStackTrace();
        	    }
        	}
        });
        btnVerDetalles.setBounds(10, 461, 295, 23);
        btnVerDetalles.setBackground(new Color(0, 120, 215));
        btnVerDetalles.setForeground(Color.WHITE);
        btnVerDetalles.setFocusPainted(false);
        card.add(btnVerDetalles);
        
 
        JButton btnVolver = new JButton("Volver al inicio");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
				PRE.setVisible(true);

				
				dispose();
        	}
        });
        btnVolver.setBounds(10, 495, 295, 23);
        btnVolver.setBackground(Color.GRAY);
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        card.add(btnVolver);
		
        }
}
