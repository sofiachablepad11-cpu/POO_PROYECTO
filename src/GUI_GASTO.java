import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class GUI_GASTO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textingreso;
    private JTextField textField;
    private JTable table_gas;
    private JComboBox comboBox_categoria;
    private JComboBox comboBox_apartado;
    private JDateChooser dateChooser;
    private String cod_use;
    private LinkedList<Apartado> listaApartados = new LinkedList<>();
	

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

                    java.util.Date fechaUtil = dateChooser.getDate();
                    if (fechaUtil == null) {
                        JOptionPane.showMessageDialog(null, "Selecciona una fecha");
                        return;
                    }
                    java.sql.Date fecha = new java.sql.Date(fechaUtil.getTime());

                    Gasto gas = new Gasto(null, cod_use, monto, categoria, descripcion, fecha, null);

                    boolean ok = ConsultasBD.guardarGasto(gas);

                    if (ok) {
                        JOptionPane.showMessageDialog(null, "Gasto guardado correctamente");

                      
                        LinkedList<Gasto> lista = ConsultasBD.getGastos(cod_use);

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

                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar gasto");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ingresa un monto válido");
                }
            }
        });
        btnguardar.setBounds(8, 272, 282, 35);
        btnguardar.setBackground(new Color(46, 204, 113));
        btnguardar.setForeground(Color.WHITE);
        btnguardar.setFocusPainted(false);
        btnguardar.setBorderPainted(false);
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnguardar);
 
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

                   
                     LinkedList<Gasto> lista = ConsultasBD.getGastos(cod_use);

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
        btn_eliminar.setBounds(8, 318, 282, 31);
        card.add(btn_eliminar);
 
        table_gas = new JTable();
        JScrollPane scroll = new JScrollPane(table_gas);
        scroll.setBounds(8, 360, 282, 126);
        card.add(scroll);
 
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
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(8, 59, 282, 18);
        card.add(dateChooser);
    }
}