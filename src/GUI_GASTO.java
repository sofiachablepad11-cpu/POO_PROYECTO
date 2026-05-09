
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
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class GUI_GASTO extends JFrame {

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
					GUI_GASTO frame = new GUI_GASTO("123");
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
	public GUI_GASTO(String cod_use) {
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
        
        JLabel lbGASTO = new JLabel("AGREGAR GASTO");
        lbGASTO.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbGASTO.setBounds(87, 11, 154, 14);
        card.add(lbGASTO);
        
        JLabel lblNewLabel_1 = new JLabel("Monto");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(8, 106, 121, 14);
        card.add(lblNewLabel_1);
        
        textingreso = new JTextField();
        textingreso.setBounds(8, 131, 282, 25);
        card.add(textingreso);
        textingreso.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Categoria ");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1.setBounds(8, 167, 121, 14);
        card.add(lblNewLabel_1_1);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"COMIDA", "TRANPORTE", "VIVIENDA", "SERVICIOS", "COMPRAS", "ENTRETENIMIENTO", "SALUD", "EDUCACION", "ROPA", "MASCOTA"}));
        comboBox.setBounds(8, 192, 282, 22);
        card.add(comboBox);
        
        JLabel lblNewLabel_1_2 = new JLabel("Descripcion");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_2.setBounds(8, 225, 121, 29);
        card.add(lblNewLabel_1_2);
        
        JButton btnguardar = new JButton("Guardar");
        btnguardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    double monto = Double.parseDouble(textingreso.getText());
                    String categoria = comboBox.getSelectedItem().toString();
                    String descripcion = textField.getText();

                    java.sql.Connection con = CONECTA.conectar();

                    String sql = "INSERT INTO gastos (\"USU_CODIGO\", \"GAS_MONTO\", \"GAS_CATEGORIA\", \"GAS_DESCRIPCION\", \"GAS_FECHA\") VALUES (?, ?, ?, ?, ?)";

                    java.sql.PreparedStatement ps = con.prepareStatement(sql);

                    
                    ps.setObject(1, java.util.UUID.fromString(cod_use));

                    ps.setDouble(2, monto);
                    ps.setString(3, categoria);
                    ps.setString(4, descripcion);

                   
                    ps.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));

                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Gasto guardado correctamente");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al guardar gasto");
                }
            }
        });
 
        btnguardar.setBounds(10, 283, 280, 35);
        btnguardar.setBackground(new Color(52, 152, 219));
        btnguardar.setForeground(Color.WHITE);
        btnguardar.setFocusPainted(false);
        btnguardar.setBorderPainted(false);
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnguardar);

        
        JButton btnVerDetalle = new JButton("Ver gastos");
        btnVerDetalle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    java.sql.Connection con = CONECTA.conectar();

                    String sql = "SELECT \"GAS_MONTO\", \"GAS_CATEGORIA\", \"GAS_FECHA\" FROM gastos WHERE \"USU_CODIGO\" = ?";

                    java.sql.PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, java.util.UUID.fromString(cod_use));

                    java.sql.ResultSet rs = ps.executeQuery();

                   
                    DefaultTableModel modelo = new DefaultTableModel();
                    modelo.addColumn("Monto");
                    modelo.addColumn("Categoría");
                    modelo.addColumn("Fecha");

                 
                    while (rs.next()) {
                        Object[] fila = new Object[3];
                        fila[0] = rs.getDouble("GAS_MONTO");
                        fila[1] = rs.getString("GAS_CATEGORIA");
                        fila[2] = rs.getDate("GAS_FECHA");

                        modelo.addRow(fila);
                    }

                   
                    table_estado.setModel(modelo);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al cargar gastos");
                }
            }
        });

        btnVerDetalle.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVerDetalle.setBounds(10, 333, 280, 35);
        btnVerDetalle.setBackground(new Color(52, 152, 219));
        btnVerDetalle.setForeground(Color.WHITE);
        btnVerDetalle.setFocusPainted(false);
        btnVerDetalle.setBorderPainted(false);
        card.add(btnVerDetalle);
        
        table = new JTable();
        table.setBounds(20, 425, 272, 0);
        card.add(table);
        
        JButton btnvolver = new JButton("Volver al Inicio");
        btnvolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
				PRE.setVisible(true);

				
				dispose();
        	}
        });
        btnvolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnvolver.setBounds(10, 379, 282, 35);
        btnvolver.setBackground(new Color(233, 30, 99));
        btnvolver.setForeground(Color.WHITE);
        btnvolver.setFocusPainted(false);
        btnvolver.setBorderPainted(false);
        card.add(btnvolver);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(8, 247, 282, 25);
        card.add(textField);
        
        table_estado = new JTable();
        table_estado.setBounds(10, 425, 282, 98);
        card.add(table_estado);
        
        comboBox_MES = new JComboBox();
        comboBox_MES.setModel(new DefaultComboBoxModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"}));
        comboBox_MES.setSelectedIndex(4);
        comboBox_MES.setSelectedIndex(hoy.getMonthValue() - 1);
        comboBox_MES.setBounds(8, 61, 124, 22);
        card.add(comboBox_MES);
        
        comboBox_AÑO = new JComboBox();
        comboBox_AÑO.setModel(new DefaultComboBoxModel(new String[] {"2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035"}));
        comboBox_AÑO.setBounds(166, 61, 124, 22);
        comboBox_AÑO.setSelectedItem(String.valueOf(hoy.getYear()));
        card.add(comboBox_AÑO);
	}
}
