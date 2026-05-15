import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class GUI_APARTADO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private String cod_use;
    private DefaultTableModel modelo;
    private JComboBox comboBox_DIA;
    private JComboBox comboBox_MES;
    private JComboBox comboBox_AÑO;
    private JComboBox comboBox_categoria;
 
    LocalDate hoy = LocalDate.now();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_APARTADO frame = new GUI_APARTADO("123");
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
	public GUI_APARTADO(String cod_use) {
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
 
        JLabel lblLIMITE = new JLabel("AGREGAR LIMITES");
        lblLIMITE.setHorizontalAlignment(SwingConstants.CENTER);
        lblLIMITE.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblLIMITE.setBounds(78, 10, 154, 14);
        card.add(lblLIMITE);
 
        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblFecha.setBounds(10, 34, 121, 14);
        card.add(lblFecha);
 
        comboBox_DIA = new JComboBox();
        comboBox_DIA.setModel(new DefaultComboBoxModel(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"}));
        comboBox_DIA.setSelectedIndex(hoy.getDayOfMonth() - 1);
        comboBox_DIA.setBounds(10, 58, 86, 22);
        card.add(comboBox_DIA);
 
        comboBox_MES = new JComboBox();
        comboBox_MES.setModel(new DefaultComboBoxModel(new String[]{"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"}));
        comboBox_MES.setSelectedIndex(hoy.getMonthValue() - 1);
        comboBox_MES.setBounds(102, 58, 92, 22);
        card.add(comboBox_MES);
 
        comboBox_AÑO = new JComboBox();
        comboBox_AÑO.setModel(new DefaultComboBoxModel(new String[]{"2026","2027","2028","2029","2030","2031","2032","2033","2034","2035"}));
        comboBox_AÑO.setSelectedItem(String.valueOf(hoy.getYear()));
        comboBox_AÑO.setBounds(200, 58, 92, 22);
        card.add(comboBox_AÑO);
 
        JLabel lbllimite = new JLabel("Monto limite");
        lbllimite.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lbllimite.setBounds(10, 90, 121, 14);
        card.add(lbllimite);
 
        textField = new JTextField();
        textField.setBounds(10, 114, 282, 25);
        card.add(textField);
 
        JLabel lblCategoria = new JLabel("Categoria");
        lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCategoria.setBounds(10, 142, 121, 14);
        card.add(lblCategoria);
 
        comboBox_categoria = new JComboBox();
        comboBox_categoria.setModel(new DefaultComboBoxModel(new String[]{ "COMIDA","TRANSPORTE","VIVIENDA","SERVICIOS","COMPRAS","ENTRETENIMIENTO","SALUD","EDUCACION","ROPA","MASCOTA"}));
        comboBox_categoria.setBounds(10, 166, 282, 22);
        card.add(comboBox_categoria);
 
        JButton btnguardar = new JButton("Guardar");
        btnguardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double limite = Double.parseDouble(textField.getText());
                    String categoria = comboBox_categoria.getSelectedItem().toString();
 
                    int dia  = Integer.parseInt(comboBox_DIA.getSelectedItem().toString());
                    int mes  = comboBox_MES.getSelectedIndex() + 1;
                    int anio = Integer.parseInt(comboBox_AÑO.getSelectedItem().toString());
                    java.sql.Date fecha = java.sql.Date.valueOf(LocalDate.of(anio, mes, dia));
 
                    Apartado apa = new Apartado(null, cod_use, limite, categoria, fecha);
                    boolean ok = ConsultasBD.guardarApartado(apa);
 
                    if (ok) {
                        JOptionPane.showMessageDialog(null, "Apartado guardado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar apartado");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ingresa un monto valido");
                }
            }
        });
        btnguardar.setForeground(Color.WHITE);
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnguardar.setFocusPainted(false);
        btnguardar.setBorderPainted(false);
        btnguardar.setBackground(new Color(46, 204, 113));
        btnguardar.setBounds(10, 218, 282, 35);
        card.add(btnguardar);
 
        JButton btn_eliminar = new JButton("Eliminar");
        btn_eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = table.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un apartado de la tabla");
                    return;
                }
                String codigo = table.getValueAt(fila, 0).toString();
                boolean ok = ConsultasBD.eliminarApartado(codigo);
                if (ok) {
                    JOptionPane.showMessageDialog(null, "Apartado eliminado");
                    btnVerLimitesEstablecidos.doClick();
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
        btn_eliminar.setBounds(10, 269, 282, 31);
        card.add(btn_eliminar);
 
        table = new JTable();
        table.setBounds(10, 311, 282, 175);
        card.add(table);

        JButton btnvolver = new JButton("Volver al Inicio");
        btnvolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
                PRE.setVisible(true);
                dispose();
            }
        });
        btnvolver.setForeground(Color.WHITE);
        btnvolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnvolver.setFocusPainted(false);
        btnvolver.setBorderPainted(false);
        btnvolver.setBackground(new Color(233, 30, 99));
        btnvolver.setBounds(10, 495, 282, 34);
        card.add(btnvolver);
    }
}
 