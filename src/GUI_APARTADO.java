import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
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
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class GUI_APARTADO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private String cod_use;
    private JComboBox comboBox_categoria;
    private JDateChooser dateChooser;
    private String codigoEditando = null;
    private JPopupMenu popupMenu;
 
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
 
        final JButton btnguardar = new JButton("Guardar");
        btnguardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
                    double limite = Double.parseDouble(textField.getText());
                    String categoria = comboBox_categoria.getSelectedItem().toString();

                    java.util.Date fechaUtil = dateChooser.getDate();
                    if (fechaUtil == null) {
                        JOptionPane.showMessageDialog(null, "Selecciona una fecha");
                        return;
                    }
                    java.sql.Date fecha = new java.sql.Date(fechaUtil.getTime());

                    if (codigoEditando != null) {
                        
                        ConsultasBD.eliminarApartado(codigoEditando);
                        Apartado apa = new Apartado(null, cod_use, limite, categoria, fecha);
                        boolean ok = ConsultasBD.guardarApartado(apa);
                        if (ok) {
                            JOptionPane.showMessageDialog(null, "Apartado actualizado");
                            codigoEditando = null;
                            btnguardar.setText("Guardar");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al actualizar");
                        }
                    } else {
                        
                        Apartado apa = new Apartado(null, cod_use, limite, categoria, fecha);
                        boolean ok = ConsultasBD.guardarApartado(apa);
                        if (ok) {
                            JOptionPane.showMessageDialog(null, "Apartado guardado correctamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al guardar apartado");
                        }
                    }

                    cargarTablaApartados();
                    textField.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ingresa un monto válido");
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
                     cargarTablaApartados();
                    
                    
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
        btn_eliminar.setBounds(10, 269, 131, 31);
        card.add(btn_eliminar);
 
        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 311, 282, 175);
        card.add(scroll);

        // POPUP MENU
        popupMenu = new JPopupMenu();

        javax.swing.JMenuItem itemEliminar = new javax.swing.JMenuItem("Eliminar");
        itemEliminar.setForeground(Color.RED);
        itemEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = table.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un apartado");
                    return;
                }
                String codigo = table.getValueAt(fila, 0).toString();
                boolean ok = ConsultasBD.eliminarApartado(codigo);
                if (ok) {
                    JOptionPane.showMessageDialog(null, "Apartado eliminado");
                    cargarTablaApartados();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }
        });

        javax.swing.JMenuItem itemEditar = new javax.swing.JMenuItem("Editar");
        itemEditar.setForeground(new Color(0, 120, 215));
        itemEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = table.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un apartado para editar");
                    return;
                }
                codigoEditando = table.getValueAt(fila, 0).toString();
                String limite = table.getValueAt(fila, 1).toString().replace("$", "").replace(",", ".").trim();
                String categoria = table.getValueAt(fila, 2).toString();
                textField.setText(limite);
                comboBox_categoria.setSelectedItem(categoria);
                btnguardar.setText("Actualizar");
            }
        });

        popupMenu.add(itemEditar);
        popupMenu.add(itemEliminar);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) mostrarPopup(e);
            }
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) mostrarPopup(e);
            }
            private void mostrarPopup(java.awt.event.MouseEvent e) {
                int fila = table.rowAtPoint(e.getPoint());
                if (fila >= 0) {
                    table.setRowSelectionInterval(fila, fila);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

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
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(10, 58, 282, 18);
        dateChooser.setDate(new java.util.Date());
        card.add(dateChooser);
        
        JButton btneditar = new JButton("Editar");
        btneditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int fila = table.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un apartado para editar");
                    return;
                }
                codigoEditando = table.getValueAt(fila, 0).toString();
                String limite = table.getValueAt(fila, 1).toString().replace("$", "").replace(",", ".").trim();
                String categoria = table.getValueAt(fila, 2).toString();
                textField.setText(limite);
                comboBox_categoria.setSelectedItem(categoria);
                btnguardar.setText("Actualizar");
            }
        });

        btneditar.setForeground(Color.WHITE);
        btneditar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btneditar.setFocusPainted(false);
        btneditar.setBorderPainted(false);
        btneditar.setBackground(new Color(0, 120, 215));
        btneditar.setBounds(152, 269, 141, 31);
        card.add(btneditar);
        
        cargarTablaApartados();
    }
	private void cargarTablaApartados() {
	    LinkedList<Apartado> lista = ConsultasBD.getApartados(cod_use);

	    DefaultTableModel modelo = new DefaultTableModel();
	    modelo.addColumn("Codigo");
	    modelo.addColumn("Limite");
	    modelo.addColumn("Categoria");
	    modelo.addColumn("Fecha");

	    for (Apartado a : lista) {
	        modelo.addRow(new Object[]{
	            a.getApa_codigo(),
	            String.format("$ %.2f", a.getApa_limite()),
	            a.getApa_categoria(),
	            a.getApa_fecha()
	        });
	    }

	    table.setModel(modelo);
	    table.getColumnModel().getColumn(0).setMinWidth(0);
	    table.getColumnModel().getColumn(0).setMaxWidth(0);
	}
}