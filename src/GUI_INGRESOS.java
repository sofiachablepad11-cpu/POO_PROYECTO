import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
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
import com.toedter.calendar.JDateChooser;

public class GUI_INGRESOS extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textingreso;
    private JTextField textField;
    private JTable table_ing;
    private String cod_use;
    private JDateChooser dateChooser;
    
    private String codigoEditando = null; 
    private JPopupMenu popupMenu;
    
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

        		            java.util.Date fechaUtil = dateChooser.getDate();
        		            if (fechaUtil == null) {
        		                JOptionPane.showMessageDialog(null, "Selecciona una fecha");
        		                return;
        		            }
        		            java.sql.Date fecha = new java.sql.Date(fechaUtil.getTime());

        		            if (codigoEditando != null) {
        		                
        		                ConsultasBD.eliminarIngreso(codigoEditando);
        		                Ingreso ing = new Ingreso(null, cod_use, monto, descripcion, fecha);
        		                boolean ok = ConsultasBD.guardarIngreso(ing);
        		                if (ok) {
        		                    JOptionPane.showMessageDialog(null, "Ingreso actualizado");
        		                    codigoEditando = null; 
        		                    btnguardar.setText("Guardar");
        		                } else {
        		                    JOptionPane.showMessageDialog(null, "Error al actualizar");
        		                }
        		            } else {
        		                
        		                Ingreso ing = new Ingreso(null, cod_use, monto, descripcion, fecha);
        		                boolean ok = ConsultasBD.guardarIngreso(ing);
        		                if (ok) {
        		                    JOptionPane.showMessageDialog(null, "Ingreso guardado");
        		                } else {
        		                    JOptionPane.showMessageDialog(null, "Error al guardar");
        		                }
        		            }

        		            cargarTabla();
        		            textingreso.setText("");
        		            textField.setText("");

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

       
        table_ing = new JTable();
        JScrollPane scroll = new JScrollPane(table_ing);
        scroll.setBounds(8, 317, 282, 146);
        card.add(scroll);

        // POPUP MENU
        popupMenu = new JPopupMenu();
        javax.swing.JMenuItem itemEliminar = new javax.swing.JMenuItem("Eliminar");
        itemEliminar.setForeground(Color.RED);
        itemEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        javax.swing.JMenuItem itemEditar = new javax.swing.JMenuItem("Editar");
        itemEditar.setForeground(new Color(0, 120, 215));
        itemEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = table_ing.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un ingreso para editar");
                    return;
                }
                codigoEditando = table_ing.getValueAt(fila, 0).toString();
                String monto = table_ing.getValueAt(fila, 1).toString();
                String descripcion = table_ing.getValueAt(fila, 2).toString();
                textingreso.setText(monto);
                textField.setText(descripcion);
                btnguardar.setText("Actualizar");
            }
        });

        popupMenu.add(itemEditar);
        popupMenu.add(itemEliminar);

       
        table_ing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) mostrarPopup(e);
            }
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) mostrarPopup(e);
            }
            private void mostrarPopup(java.awt.event.MouseEvent e) {
                int fila = table_ing.rowAtPoint(e.getPoint());
                if (fila >= 0) {
                    table_ing.setRowSelectionInterval(fila, fila); 
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        

       
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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
        	    }
        	});

        btnEliminar.setBounds(8, 263, 121, 31);
        btnEliminar.setBackground(Color.RED);
        btnEliminar.setForeground(Color.WHITE);
       
        card.add(btnEliminar);

    
        JButton btnVolver = new JButton("Volver al Inicio");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
                PRE.setVisible(true);
                dispose();
        	}
        });
        btnVolver.setBounds(10, 495, 280, 34);
        btnVolver.setBackground(new Color(233, 30, 99));
        btnVolver.setForeground(Color.WHITE);



        card.add(btnVolver);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(8, 59, 282, 18);
        dateChooser.setDate(new java.util.Date());
        card.add(dateChooser);
        
        JButton btneditar = new JButton("Editar");
        btneditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int fila = table_ing.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un ingreso para editar");
                    return;
                }

                codigoEditando = table_ing.getValueAt(fila, 0).toString();
                String monto = table_ing.getValueAt(fila, 1).toString();
                String descripcion = table_ing.getValueAt(fila, 2).toString();

                textingreso.setText(monto);
                textField.setText(descripcion);
                btnguardar.setText("Actualizar");
            }
        });
        btneditar.setForeground(Color.WHITE);
        btneditar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btneditar.setFocusPainted(false);
        btneditar.setBorderPainted(false);
        btneditar.setBackground(new Color(0, 120, 215));
        btneditar.setBounds(151, 263, 139, 31);
        card.add(btneditar);

       
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
