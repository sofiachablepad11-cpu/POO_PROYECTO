
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
    private JTextField textingreso;
    private JTextField textField;
    private JTable table_gas;
    private JComboBox comboBox_DIA;
    private JComboBox comboBox_MES;
    private JComboBox comboBox_AÑO;
    private JComboBox comboBox_categoria;
    LocalDate hoy = LocalDate.now();
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
 
        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblFecha.setBounds(8, 35, 121, 14);
        card.add(lblFecha);
 
        comboBox_DIA = new JComboBox();
        comboBox_DIA.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"}));
        comboBox_DIA.setSelectedIndex(hoy.getDayOfMonth() - 1);
        comboBox_DIA.setBounds(8, 59, 86, 22);
        card.add(comboBox_DIA);
 
        comboBox_MES = new JComboBox();
        comboBox_MES.setModel(new DefaultComboBoxModel(new String[] {"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"}));
        comboBox_MES.setSelectedIndex(hoy.getMonthValue() - 1);
        comboBox_MES.setBounds(100, 59, 92, 22);
        card.add(comboBox_MES);
 
        comboBox_AÑO = new JComboBox();
        comboBox_AÑO.setModel(new DefaultComboBoxModel(new String[] {"2026","2027","2028","2029","2030","2031","2032","2033","2034","2035"}));
        comboBox_AÑO.setSelectedItem(String.valueOf(hoy.getYear()));
        comboBox_AÑO.setBounds(198, 59, 92, 22);
        card.add(comboBox_AÑO);
 
        JLabel lblMonto = new JLabel("Monto");
        lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblMonto.setBounds(8, 91, 121, 14);
        card.add(lblMonto);
 
        textingreso = new JTextField();
        textingreso.setBounds(8, 115, 282, 25);
        card.add(textingreso);
 
        JLabel lblCategoria = new JLabel("Categoria");
        lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCategoria.setBounds(8, 143, 121, 14);
        card.add(lblCategoria);
 
        comboBox_categoria = new JComboBox();
        comboBox_categoria.setModel(new DefaultComboBoxModel(new String[] {"COMIDA","TRANSPORTE","VIVIENDA","SERVICIOS","COMPRAS","ENTRETENIMIENTO","SALUD","EDUCACION","ROPA","MASCOTA"}));
        comboBox_categoria.setBounds(8, 167, 282, 22);
        card.add(comboBox_categoria);
 
        JLabel lblDescripcion = new JLabel("Descripcion");
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblDescripcion.setBounds(8, 195, 121, 14);
        card.add(lblDescripcion);
 
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(8, 219, 282, 25);
        card.add(textField);
 
        JButton btnguardar = new JButton("Guardar");
        btnguardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double monto = Double.parseDouble(textingreso.getText());
                    String categoria = comboBox_categoria.getSelectedItem().toString();
                    String descripcion = textField.getText();
 
                    int dia = Integer.parseInt(comboBox_DIA.getSelectedItem().toString());
                    int mes = comboBox_MES.getSelectedIndex() + 1;
                    int anio = Integer.parseInt(comboBox_AÑO.getSelectedItem().toString());
                    java.sql.Date fecha = java.sql.Date.valueOf(LocalDate.of(anio, mes, dia));
 
                    Gasto gas = new Gasto(null, cod_use, monto, categoria, descripcion, fecha);
                    boolean ok = ConsultasBD.guardarGasto(gas);
 
                    if (ok) {
                        JOptionPane.showMessageDialog(null, "Gasto guardado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar gasto");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ingresa un monto valido");
                }
            }
        });
        btnguardar.setBounds(8, 255, 282, 35);
        btnguardar.setBackground(new Color(46, 204, 113));
        btnguardar.setForeground(Color.WHITE);
        btnguardar.setFocusPainted(false);
        btnguardar.setBorderPainted(false);
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnguardar);
 
        JButton btnVerDetalle = new JButton("Ver gastos");
        btnVerDetalle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.util.LinkedList<Gasto> lista = ConsultasBD.getGastos(cod_use);
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("Codigo");
                modelo.addColumn("Monto");
                modelo.addColumn("Categoria");
                modelo.addColumn("Descripcion");
                modelo.addColumn("Fecha");
                for (Gasto g : lista) {
                    modelo.addRow(new Object[]{
                        g.getGas_codigo(),
                        g.getGas_monto(),
                        g.getGas_categoria(),
                        g.getGas_descripcion(),
                        g.getGas_fecha()
                    });
                }
                table_gas.setModel(modelo);
                table_gas.getColumnModel().getColumn(0).setMinWidth(0);
                table_gas.getColumnModel().getColumn(0).setMaxWidth(0);
            }
        });
        btnVerDetalle.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVerDetalle.setBounds(8, 300, 282, 35);
        btnVerDetalle.setBackground(new Color(52, 152, 219));
        btnVerDetalle.setForeground(Color.WHITE);
        btnVerDetalle.setFocusPainted(false);
        btnVerDetalle.setBorderPainted(false);
        card.add(btnVerDetalle);
 
        JButton btn_eliminar = new JButton("Eliminar");
        btn_eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = table_gas.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un gasto de la tabla");
                    return;
                }
                String codigo = table_gas.getValueAt(fila, 0).toString();
                boolean ok = ConsultasBD.eliminarGasto(codigo);
                if (ok) {
                    JOptionPane.showMessageDialog(null, "Gasto eliminado");
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
        btn_eliminar.setBounds(8, 345, 282, 31);
        card.add(btn_eliminar);
 
        table_gas = new JTable();
        table_gas.setBounds(8, 388, 282, 98);
        card.add(table_gas);
 
        JButton btnvolver = new JButton("Volver al Inicio");
        btnvolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
                PRE.setVisible(true);
                dispose();
            }
        });
        btnvolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnvolver.setBounds(8, 495, 282, 34);
        btnvolver.setBackground(new Color(233, 30, 99));
        btnvolver.setForeground(Color.WHITE);
        btnvolver.setFocusPainted(false);
        btnvolver.setBorderPainted(false);
        card.add(btnvolver);
    }
}