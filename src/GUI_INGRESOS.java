import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class GUI_INGRESOS extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textingreso;
    private JComboBox comboBox_DIA;
    private JComboBox comboBox_MES;
    private JComboBox comboBox_AÑO;
    private JTextField textField;
    private JTable table_ing;
    private String cod_use;

    LocalDate hoy = LocalDate.now();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GUI_INGRESOS frame = new GUI_INGRESOS("123");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

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

        JLabel lblPresupuesto = new JLabel("INGRESOS");
        lblPresupuesto.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPresupuesto.setBounds(115, 11, 112, 14);
        card.add(lblPresupuesto);

        JLabel lblIngreso = new JLabel("Ingreso extra");
        lblIngreso.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblIngreso.setBounds(8, 91, 121, 25);
        card.add(lblIngreso);

        textingreso = new JTextField();
        textingreso.setBounds(8, 115, 282, 25);
        card.add(textingreso);

        JLabel lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblDescripcion.setBounds(8, 143, 121, 25);
        card.add(lblDescripcion);

        textField = new JTextField();
        textField.setBounds(8, 167, 282, 25);
        card.add(textField);

        
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
        	        	cargarTabla(); 
        	        	textingreso.setText("");
        	        	textField.setText("");
        	        	} else {
        	        		JOptionPane.showMessageDialog(null, "Error al guardar");
        	        		}
        	        } catch (Exception ex) {
        	        	JOptionPane.showMessageDialog(null, "Ingresa un monto válido");
        	        	}
        		}
        	});
      
        btnguardar.setBounds(8, 217, 282, 35);
        btnguardar.setBackground(new Color(46, 204, 113));
        btnguardar.setForeground(Color.WHITE);

       

        card.add(btnguardar);

       
        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblFecha.setBounds(8, 35, 121, 14);
        card.add(lblFecha);

        comboBox_DIA = new JComboBox();
        comboBox_DIA.setModel(new DefaultComboBoxModel(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"}));
        comboBox_DIA.setSelectedIndex(hoy.getDayOfMonth() - 1);
        comboBox_DIA.setBounds(8, 59, 86, 22);
        card.add(comboBox_DIA);

        comboBox_MES = new JComboBox();
        comboBox_MES.setModel(new DefaultComboBoxModel(new String[]{"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"}));
        comboBox_MES.setSelectedIndex(hoy.getMonthValue() - 1);
        comboBox_MES.setBounds(100, 59, 92, 22);
        card.add(comboBox_MES);

        comboBox_AÑO = new JComboBox();
        comboBox_AÑO.setModel(new DefaultComboBoxModel(new String[]{"2026","2027","2028","2029","2030","2031","2032","2033","2034","2035"}));
        comboBox_AÑO.setSelectedItem(String.valueOf(hoy.getYear()));
        comboBox_AÑO.setBounds(198, 59, 92, 22);
        card.add(comboBox_AÑO);

       
        table_ing = new JTable();
        table_ing.setBounds(8, 317, 282, 146);
        card.add(table_ing);

       
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(8, 263, 282, 31);
        btnEliminar.setBackground(Color.RED);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.addActionListener(e -> {
            int fila = table_ing.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un ingreso");
                return;
            }

            String codigo = table_ing.getValueAt(fila, 0).toString();
            boolean ok = ConsultasBD.eliminarIngreso(codigo);

            if (ok) {
                JOptionPane.showMessageDialog(null, "Ingreso eliminado");
                cargarTabla(); 
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
        });

        card.add(btnEliminar);

    
        JButton btnVolver = new JButton("Volver al Inicio");
        btnVolver.setBounds(10, 495, 280, 34);
        btnVolver.setBackground(new Color(233, 30, 99));
        btnVolver.setForeground(Color.WHITE);

        btnVolver.addActionListener(e -> {
            GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
            PRE.setVisible(true);
            dispose();
        });

        card.add(btnVolver);

       
        cargarTabla();
    }

    
    private void cargarTabla() {
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
}
