
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conec.CONECTA;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class GUI_INGRESOS extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textingreso;
	private JTable table;
	private JTable table_estado;
	private JComboBox comboBox_MES;
    private JComboBox comboBox_AÑO;
    LocalDate hoy = LocalDate.now();
	private JTextField textField;
	private String cod_use;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_INGRESOS frame = new GUI_INGRESOS("123");
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
	public GUI_INGRESOS(String cod_use) {
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
        
        JLabel lblPresupuesto = new JLabel("INGRESOS\r\n");
        lblPresupuesto.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPresupuesto.setBounds(115, 11, 112, 14);
        card.add(lblPresupuesto);
        
        JLabel lblNewLabel_1 = new JLabel("Ingreso mensual");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(8, 83, 121, 14);
        card.add(lblNewLabel_1);
        
        textingreso = new JTextField();
        textingreso.setBounds(8, 108, 282, 25);
        card.add(textingreso);
        textingreso.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Categoria de gasto");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1.setBounds(8, 144, 121, 14);
        card.add(lblNewLabel_1_1);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"COMIDA", "TRANPORTE", "VIVIENDA", "SERVICIOS", "COMPRAS", "ENTRETENIMIENTO", "SALUD", "EDUCACION", "ROPA", "MASCOTA"}));
        comboBox.setBounds(8, 169, 282, 22);
        card.add(comboBox);
        
        JLabel lblNewLabel_1_2 = new JLabel("Limite de gasto");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_2.setBounds(8, 202, 121, 29);
        card.add(lblNewLabel_1_2);
        
        JButton btnguardar = new JButton("Guardar");
        btnguardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    
                    double monto = Double.parseDouble(textingreso.getText());
                    String categoria = comboBox.getSelectedItem().toString();

                    java.sql.Date fecha = java.sql.Date.valueOf(LocalDate.now());

                    
                 
                   
                    Connection con = CONECTA.conectar();

                  
                    String sql = "INSERT INTO ingresos (\"USU_CODIGO\", \"ING_MONTO\", \"ING_CATEGORIA\", \"ING_FECHA\") VALUES (?, ?, ?, ?)";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setObject(1, java.util.UUID.fromString(cod_use));
                    ps.setDouble(2, monto);
                    ps.setString(3, categoria);
                    ps.setDate(4, fecha);

                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Ingreso guardado");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                    ex.printStackTrace();
                }
            }
        });
 
        btnguardar.setBounds(10, 260, 280, 35);
        btnguardar.setBackground(new Color(52, 152, 219));
        btnguardar.setForeground(Color.WHITE);
        btnguardar.setFocusPainted(false);
        btnguardar.setBorderPainted(false);
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnguardar);

        
        JButton btnVerDetalle = new JButton("Ver detalle");
        btnVerDetalle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    Connection con = CONECTA.conectar();

                    String sql = "SELECT \"ING_MONTO\", \"ING_CATEGORIA\", \"ING_FECHA\" FROM ingresos WHERE \"USU_CODIGO\" = ?";

                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, java.util.UUID.fromString(cod_use));

                    ResultSet rs = ps.executeQuery();

                  
                    DefaultTableModel modelo = new DefaultTableModel();
                    modelo.addColumn("Monto");
                    modelo.addColumn("Categoría");
                    modelo.addColumn("Fecha");

                    
                    while (rs.next()) {
                        Object[] fila = new Object[3];
                        fila[0] = rs.getDouble("ING_MONTO");
                        fila[1] = rs.getString("ING_CATEGORIA");
                        fila[2] = rs.getDate("ING_FECHA");

                        modelo.addRow(fila);
                    }

                   
                    table_estado.setModel(modelo);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al cargar datos");
                }
            }
        });	
  
        btnVerDetalle.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVerDetalle.setBounds(12, 306, 280, 35);
        btnVerDetalle.setBackground(new Color(52, 152, 219));
        btnVerDetalle.setForeground(Color.WHITE);
        btnVerDetalle.setFocusPainted(false);
        btnVerDetalle.setBorderPainted(false);
        card.add(btnVerDetalle);
        
        table = new JTable();
        table.setBounds(20, 425, 272, 0);
        card.add(table);
        
        table_estado = new JTable();
        table_estado.setBounds(10, 344, 282, 98);
        card.add(table_estado);
        
        JButton btnestado = new JButton("Estado del presupuesto");
        btnestado.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnestado.setBounds(20, 453, 270, 30);
        btnestado.setBackground(new Color(52, 152, 219));
        btnestado.setForeground(Color.WHITE);
        btnestado.setFocusPainted(false);
        btnestado.setBorderPainted(false);
        card.add(btnestado);
        
        JButton btnvolver = new JButton("Volver al Inicio");
        btnvolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
				PRE.setVisible(true);

				
				dispose();
        	}
        });
        btnvolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnvolver.setBounds(20, 494, 270, 34);
        btnvolver.setBackground(new Color(233, 30, 99));
        btnvolver.setForeground(Color.WHITE);
        btnvolver.setFocusPainted(false);
        btnvolver.setBorderPainted(false);
        card.add(btnvolver);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(8, 224, 282, 25);
        card.add(textField);
        
        JComboBox comboBox_MES = new JComboBox();
        comboBox_MES.setModel(new DefaultComboBoxModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"}));
        comboBox_MES.setSelectedIndex(4);
        comboBox_MES.setSelectedIndex(hoy.getMonthValue() - 1);
        comboBox_MES.setBounds(12, 50, 124, 22);
        card.add(comboBox_MES);
        
        JComboBox comboBox_AÑO = new JComboBox();
        comboBox_AÑO.setModel(new DefaultComboBoxModel(new String[] {"2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035"}));
        comboBox_AÑO.setBounds(170, 50, 124, 22);
        comboBox_AÑO.setSelectedItem(String.valueOf(hoy.getYear()));
        card.add(comboBox_AÑO);
	}
}
