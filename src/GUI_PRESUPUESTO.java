
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

public class GUI_PRESUPUESTO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private String cod_use;
    private JLabel lblsaldo;
    private JDateChooser dateChooserInicio;
    private JDateChooser dateChooserCorte; 
   

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_PRESUPUESTO frame = new GUI_PRESUPUESTO("123");
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
	public GUI_PRESUPUESTO(String cod_use) {
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
 
        JLabel lblpresupuesto = new JLabel("PRESUPUESTO");
        lblpresupuesto.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblpresupuesto.setBounds(104, 42, 112, 14);
        card.add(lblpresupuesto);
 
        JLabel lblNewLabel_1 = new JLabel("Presupuesto");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(10, 86, 121, 14);
        card.add(lblNewLabel_1);
 
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(10, 111, 282, 25);
        card.add(textField);
 
        JButton btn_guardarp = new JButton("Guardar presupuesto");
        btn_guardarp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
                    if (dateChooserInicio.getDate() == null || dateChooserCorte.getDate() == null) {
                        JOptionPane.showMessageDialog(null, "Selecciona ambas fechas");
                        return;
                    }
                    double monto = Double.parseDouble(textField.getText());
                    java.sql.Date fechaInicio = new java.sql.Date(dateChooserInicio.getDate().getTime());
                    java.sql.Date fechaCorte  = new java.sql.Date(dateChooserCorte.getDate().getTime());

                    Presupuesto pre = new Presupuesto(null, cod_use, monto, fechaInicio, fechaCorte);
                    boolean ok = ConsultasBD.guardarPresupuesto(pre);

                    if (ok) {
                        JOptionPane.showMessageDialog(null, "Presupuesto guardado correctamente");
                        actualizarDisponible();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ingresa un monto valido");
                }
            }
        });
        btn_guardarp.setBounds(10, 248, 282, 31);
        btn_guardarp.setBackground(new Color(46, 204, 113));
        btn_guardarp.setForeground(Color.WHITE);
        btn_guardarp.setFocusPainted(false);
        btn_guardarp.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btn_guardarp);
 
        JButton btn_actualizar = new JButton("Actualizar presupuesto");
        btn_actualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
                    if (dateChooserInicio.getDate() == null || dateChooserCorte.getDate() == null) {
                        JOptionPane.showMessageDialog(null, "Selecciona ambas fechas");
                        return;
                    }
                    double montoNuevo = Double.parseDouble(textField.getText());
                    java.sql.Date fechaInicio = new java.sql.Date(dateChooserInicio.getDate().getTime());
                    java.sql.Date fechaCorte  = new java.sql.Date(dateChooserCorte.getDate().getTime());

                    LinkedList<Presupuesto> lista = ConsultasBD.getPresupuestos(cod_use);
                    if (lista.isEmpty()) {
                        ConsultasBD.guardarPresupuesto(new Presupuesto(null, cod_use, montoNuevo, fechaInicio, fechaCorte));
                        JOptionPane.showMessageDialog(null, "Presupuesto guardado correctamente");
                    } else {
                        for (Presupuesto p : lista) ConsultasBD.eliminarPresupuesto(p.getPre_codigo());
                        boolean ok = ConsultasBD.guardarPresupuesto(new Presupuesto(null, cod_use, montoNuevo, fechaInicio, fechaCorte));
                        JOptionPane.showMessageDialog(null, ok
                            ? "Presupuesto actualizado a $" + String.format("%.2f", montoNuevo)
                            : "Error al actualizar");
                    }
                    actualizarDisponible();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ingresa un monto valido");
                }
            }
        });
        btn_actualizar.setBounds(10, 285, 282, 31);
        btn_actualizar.setBackground(new Color(52, 152, 219));
        btn_actualizar.setForeground(Color.WHITE);
        btn_actualizar.setFocusPainted(false);
        btn_actualizar.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btn_actualizar);
 
        JLabel lblNewLabel_1_1 = new JLabel("Fecha de inicio");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1.setBounds(10, 146, 121, 14);
        card.add(lblNewLabel_1_1);
 
        JLabel lblNewLabel_1_2 = new JLabel("Disponible");
        lblNewLabel_1_2.setBounds(10, 325, 86, 14);
        card.add(lblNewLabel_1_2);
 
        lblsaldo = new JLabel("0.00");
        lblsaldo.setForeground(new Color(0, 153, 0));
        lblsaldo.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblsaldo.setBounds(45, 355, 247, 51);
        card.add(lblsaldo);
 
        JLabel lblNewLabel_3 = new JLabel("$");
        lblNewLabel_3.setForeground(new Color(0, 153, 0));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_3.setBounds(20, 356, 18, 34);
        card.add(lblNewLabel_3);
 
        JButton btnVolverAlInicio_1 = new JButton("Volver al inicio");
        btnVolverAlInicio_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
                PRE.setVisible(true);
                dispose();
            }
        });
        btnVolverAlInicio_1.setForeground(Color.WHITE);
        btnVolverAlInicio_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVolverAlInicio_1.setFocusPainted(false);
        btnVolverAlInicio_1.setBackground(new Color(233, 30, 99));
        btnVolverAlInicio_1.setBounds(10, 435, 295, 28);
        card.add(btnVolverAlInicio_1);
        
        dateChooserInicio = new JDateChooser();
        dateChooserInicio.setBounds(10, 170, 282, 18);
        card.add(dateChooserInicio);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Fecha de corte");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1_1.setBounds(10, 198, 121, 14);
        card.add(lblNewLabel_1_1_1);
        
        dateChooserCorte = new JDateChooser();
        dateChooserCorte.setBounds(10, 220, 282, 18);
        card.add(dateChooserCorte);
 
        actualizarDisponible();
    }
 
    private void actualizarDisponible() {
        double total = ConsultasBD.getTotalPresupuesto(cod_use);
        lblsaldo.setText(String.format("%.2f", total));
    }
}
 