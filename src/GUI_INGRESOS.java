
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
	private JTextField textingreso;
	private JComboBox comboBox_DIA;
	private JComboBox comboBox_MES;
    private JComboBox comboBox_AÑO;
    LocalDate hoy = LocalDate.now();
	private JTextField textField;
	private String cod_use;
	private JTable table_ing;

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
        
        JLabel lblPresupuesto = new JLabel("INGRESOS\r\n");
        lblPresupuesto.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPresupuesto.setBounds(115, 11, 112, 14);
        card.add(lblPresupuesto);
        
        JLabel lblNewLabel_1 = new JLabel("Ingreso extra");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(8, 91, 121, 25);
        card.add(lblNewLabel_1);
        
        textingreso = new JTextField();
        textingreso.setBounds(8, 115, 282, 25);
        card.add(textingreso);
        textingreso.setColumns(10);
        
        JLabel lblNewLabel_1_2 = new JLabel("Descripcion");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_2.setBounds(8, 143, 121, 25);
        card.add(lblNewLabel_1_2);
        
        JButton btnguardar = new JButton("Guardar");
        btnguardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			double monto = Double.parseDouble(textingreso.getText());
        		    String descripcion = textField.getText();

        		    int dia = Integer.parseInt(comboBox_DIA.getSelectedItem().toString());
        		    int mes = comboBox_MES.getSelectedIndex() + 1;
        		    int anio = Integer.parseInt(comboBox_AÑO.getSelectedItem().toString());
        		    java.sql.Date fecha = java.sql.Date.valueOf(LocalDate.of(anio, mes, dia));

        		    Ingreso ing = new Ingreso(null, cod_use, monto, descripcion, fecha);
        		    boolean ok = ConsultasBD.guardarIngreso(ing);

        		    if (ok) {
        		    	JOptionPane.showMessageDialog(null, "Ingreso guardado");
        		    } else {
        		    	JOptionPane.showMessageDialog(null, "Error al guardar");
        		    }
        		} catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, "Ingresa un monto valido");
        		}
            }
        });
 
        btnguardar.setBounds(8, 202, 282, 35);
        btnguardar.setBackground(new Color(46, 204, 113));
        btnguardar.setForeground(Color.WHITE);
        btnguardar.setFocusPainted(false);
        btnguardar.setBorderPainted(false);
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnguardar);

        
        JButton btnVerDetalle = new JButton("Ver detalle");
        btnVerDetalle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		java.util.LinkedList<Ingreso> lista = ConsultasBD.getIngresos(cod_use);
        		DefaultTableModel modelo = new DefaultTableModel();
        		modelo.addColumn("Codigo");
        		modelo.addColumn("Monto");
        		modelo.addColumn("Descripcion");
        		modelo.addColumn("Fecha");
        		for (Ingreso i : lista) {
        			modelo.addRow(new Object[]{
        					i.getIng_codigo(),
        		            i.getIng_monto(),
        		            i.getIng_descripcion(),
        		            i.getIng_fecha()
        		    });
        		}
        		table_ing.setModel(modelo);
        		table_ing.getColumnModel().getColumn(0).setMinWidth(0);
        		table_ing.getColumnModel().getColumn(0).setMaxWidth(0);
            }
        });	
  
        btnVerDetalle.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVerDetalle.setBounds(8, 247, 282, 35);
        btnVerDetalle.setBackground(new Color(52, 152, 219));
        btnVerDetalle.setForeground(Color.WHITE);
        btnVerDetalle.setFocusPainted(false);
        btnVerDetalle.setBorderPainted(false);
        card.add(btnVerDetalle);
        
        JButton btnvolver = new JButton("Volver al Inicio");
        btnvolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
				PRE.setVisible(true);

				
				dispose();
        	}
        });
        btnvolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnvolver.setBounds(10, 495, 280, 34);
        btnvolver.setBackground(new Color(233, 30, 99));
        btnvolver.setForeground(Color.WHITE);
        btnvolver.setFocusPainted(false);
        btnvolver.setBorderPainted(false);
        card.add(btnvolver);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(8, 167, 282, 25);
        card.add(textField);
        
        JLabel lblNewLabel_1_1 = new JLabel("Fecha");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1.setBounds(8, 35, 121, 14);
        card.add(lblNewLabel_1_1);
        
        comboBox_DIA = new JComboBox();
        comboBox_DIA.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_DIA.setSelectedIndex(hoy.getDayOfMonth() - 1);
        comboBox_DIA.setBounds(8, 59, 86, 22);
        card.add(comboBox_DIA);
        
        comboBox_MES = new JComboBox();
        comboBox_MES.setModel(new DefaultComboBoxModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"}));
        comboBox_MES.setSelectedIndex(hoy.getMonthValue() - 1);
        comboBox_MES.setBounds(100, 59, 92, 22);
        card.add(comboBox_MES);
        
        comboBox_AÑO = new JComboBox();
        comboBox_AÑO.setModel(new DefaultComboBoxModel(new String[] {"2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035"}));
        comboBox_AÑO.setSelectedItem(String.valueOf(hoy.getYear()));
        comboBox_AÑO.setBounds(198, 59, 92, 22);
        card.add(comboBox_AÑO);
        
        JButton btn_eliminar = new JButton("Eliminar ");
        btn_eliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int fila = table_ing.getSelectedRow();
        		if (fila == -1) {
        			JOptionPane.showMessageDialog(null, "Selecciona un ingreso de la tabla");
        		    return;
        		}
        		String codigo = table_ing.getValueAt(fila, 0).toString();
        		boolean ok = ConsultasBD.eliminarIngreso(codigo);
        		if (ok) {
        		   JOptionPane.showMessageDialog(null, "Ingreso eliminado");
        		   btnVerDetalle.doClick();
        		} else {
        		   JOptionPane.showMessageDialog(null, "Error al eliminar");
        		}
        	}
        });
        btn_eliminar.setForeground(Color.WHITE);
        btn_eliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn_eliminar.setFocusPainted(false);
        btn_eliminar.setBorderPainted(false);
        btn_eliminar.setBackground(Color.RED);
        btn_eliminar.setBounds(8, 290, 282, 31);
        card.add(btn_eliminar);
        
        table_ing = new JTable();
        table_ing.setBounds(8, 343, 282, 98);
        card.add(table_ing);
	}
}
