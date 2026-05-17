import java.awt.Color;
import java.awt.EventQueue;
import conec.CONECTA;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

public class GUI_REPORTES extends JFrame {

	private static final long serialVersionUID = 1L;
    LocalDate hoy = LocalDate.now();
    private JTable table;
    private String cod_use;
    private JLabel totalIng;
    private JLabel totalGas;
    private JLabel totalPre;
    private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_REPORTES frame = new GUI_REPORTES("123");
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
    public GUI_REPORTES(String cod_use) {
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

        JLabel lblReportes = new JLabel("REPORTES");
        lblReportes.setHorizontalAlignment(SwingConstants.CENTER);
        lblReportes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblReportes.setBounds(0, 10, 315, 14);
        card.add(lblReportes);

        JPanel panelPre = new JPanel();
        panelPre.setBackground(new Color(52, 152, 219));
        panelPre.setBounds(10, 70, 295, 60);
        panelPre.setLayout(null);
        card.add(panelPre);

        JLabel lblPre = new JLabel("Presupuesto");
        lblPre.setForeground(Color.WHITE);
        lblPre.setBounds(10, 8, 120, 20);
        panelPre.add(lblPre);

        totalPre = new JLabel("$ 0.00");
        totalPre.setForeground(Color.WHITE);
        totalPre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalPre.setBounds(10, 30, 200, 25);
        panelPre.add(totalPre);

        JPanel panelIngresos = new JPanel();
        panelIngresos.setBackground(new Color(46, 204, 113));
        panelIngresos.setBounds(10, 140, 295, 60);
        panelIngresos.setLayout(null);
        card.add(panelIngresos);

        JLabel lblIng = new JLabel("Ingresos");
        lblIng.setForeground(Color.WHITE);
        lblIng.setBounds(10, 8, 100, 20);
        panelIngresos.add(lblIng);

        totalIng = new JLabel("$ 0.00");
        totalIng.setForeground(Color.WHITE);
        totalIng.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalIng.setBounds(10, 30, 200, 25);
        panelIngresos.add(totalIng);

        JPanel panelGastos = new JPanel();
        panelGastos.setBackground(new Color(255, 80, 80));
        panelGastos.setBounds(10, 210, 295, 60);
        panelGastos.setLayout(null);
        card.add(panelGastos);

        JLabel lblGas = new JLabel("Gastos");
        lblGas.setForeground(Color.WHITE);
        lblGas.setBounds(10, 8, 100, 20);
        panelGastos.add(lblGas);

        totalGas = new JLabel("$ 0.00");
        totalGas.setForeground(Color.WHITE);
        totalGas.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalGas.setBounds(10, 30, 200, 25);
        panelGastos.add(totalGas);

        JButton btnActualizar = new JButton("Imprimir");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (table.getModel().getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null,
                        "Primero presiona 'Ver movimientos' para cargar los datos.");
                    return;
                }
                exportarExcel();
            }
        });
        btnActualizar.setBounds(10, 451, 295, 35);
        btnActualizar.setBackground(new Color(0, 153, 51));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        card.add(btnActualizar);

        JLabel lblMovimientos = new JLabel("Movimientos");
        lblMovimientos.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblMovimientos.setBounds(10, 328, 121, 14);
        card.add(lblMovimientos);

        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 345, 295, 110);
        card.add(scroll);

        JButton btnVerDetalles = new JButton("Ver movimientos");
        btnVerDetalles.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVerDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("Monto");
                modelo.addColumn("Tipo");
                modelo.addColumn("Fecha");

                java.util.LinkedList<Ingreso> ingresos = ConsultasBD.getIngresos(cod_use);
                for (Ingreso i : ingresos) {
                    modelo.addRow(new Object[]{
                        String.format("$ %.2f", i.getIng_monto()), "INGRESO", i.getIng_fecha()
                    });
                }

                java.util.LinkedList<Gasto> gastos = ConsultasBD.getGastos(cod_use);
                for (Gasto g : gastos) {
                    modelo.addRow(new Object[]{
                        String.format("$ %.2f", g.getGas_monto()), "GASTO", g.getGas_fecha()
                    });
                }

                table.setModel(modelo);
            }
        });
        btnVerDetalles.setBounds(10, 281, 295, 36);
        btnVerDetalles.setBackground(new Color(0, 120, 215));
        btnVerDetalles.setForeground(Color.WHITE);
        btnVerDetalles.setFocusPainted(false);
        btnVerDetalles.setBorderPainted(false);
        card.add(btnVerDetalles);

        JButton btnVolver = new JButton("Volver al inicio");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI_PANTALLA_PRINCIPAL PRE = new GUI_PANTALLA_PRINCIPAL(cod_use);
                PRE.setVisible(true);
                dispose();
            }
        });
        btnVolver.setBounds(10, 497, 295, 30);
        btnVolver.setBackground(new Color(233, 30, 99));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        card.add(btnVolver);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(10, 42, 295, 18);
        card.add(dateChooser);

        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblFecha.setBounds(10, 24, 121, 14);
        card.add(lblFecha);
        
        actualizarTotales();
        
    }
    private void actualizarTotales() {
        double ing = ConsultasBD.getTotalIngresos(cod_use);
        double gas = ConsultasBD.getTotalGastos(cod_use);
        double pre = ConsultasBD.getTotalPresupuesto(cod_use);

        totalIng.setText(String.format("$ %.2f", ing));
        totalGas.setText(String.format("$ %.2f", gas));
        totalPre.setText(String.format("$ %.2f", pre));
    }
    

    private void exportarExcel() {
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        javax.swing.filechooser.FileNameExtensionFilter filter =
            new javax.swing.filechooser.FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar reporte");
        chooser.setAcceptAllFileFilterUsed(false);

        String nombreArchivo = "Reporte_AhorraYa";
        java.util.Date fechaUtil = dateChooser.getDate();
        if (fechaUtil != null) {
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(fechaUtil);
            nombreArchivo += "_" + (cal.get(java.util.Calendar.MONTH) + 1)
                           + "_" + cal.get(java.util.Calendar.YEAR);
        }
        chooser.setSelectedFile(new java.io.File(nombreArchivo + ".xls"));

        if (chooser.showSaveDialog(null) == javax.swing.JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString();
            if (!ruta.endsWith(".xls")) ruta = ruta + ".xls";

            try {
                java.io.File archivoXLS = new java.io.File(ruta);
                if (archivoXLS.exists()) archivoXLS.delete();
                archivoXLS.createNewFile();

                org.apache.poi.ss.usermodel.Workbook libro =
                    new org.apache.poi.hssf.usermodel.HSSFWorkbook();
                java.io.FileOutputStream archivo = new java.io.FileOutputStream(archivoXLS);
                org.apache.poi.ss.usermodel.Sheet hoja = libro.createSheet("Reporte Ahorra Ya");

                javax.swing.table.TableModel modelo = table.getModel();
                org.apache.poi.ss.usermodel.Row filaEnc = hoja.createRow(0);
                for (int c = 0; c < modelo.getColumnCount(); c++) {
                    filaEnc.createCell(c).setCellValue(modelo.getColumnName(c));
                }

                for (int f = 0; f < modelo.getRowCount(); f++) {
                    org.apache.poi.ss.usermodel.Row fila = hoja.createRow(f + 1);
                    for (int c = 0; c < modelo.getColumnCount(); c++) {
                        Object val = modelo.getValueAt(f, c);
                        fila.createCell(c).setCellValue(val != null ? val.toString() : "");
                    }
                }

                int filaRes = modelo.getRowCount() + 2;

                double totalIngresos = ConsultasBD.getTotalIngresos(cod_use);
                double totalGastos   = ConsultasBD.getTotalGastos(cod_use);
                double saldo         = ConsultasBD.getSaldo(cod_use);
                String[][] resumen = {
                    {"Total Ingresos",   String.format("$ %.2f", totalIngresos)},
                    {"Total Gastos",     String.format("$ %.2f", totalGastos)},
                    {"Saldo Disponible", String.format("$ %.2f", saldo)}
                };
                for (int i = 0; i < resumen.length; i++) {
                    org.apache.poi.ss.usermodel.Row r = hoja.createRow(filaRes + i);
                    r.createCell(0).setCellValue(resumen[i][0]);
                    r.createCell(1).setCellValue(resumen[i][1]);
                }

                libro.write(archivo);
                archivo.close();

                java.awt.Desktop.getDesktop().open(archivoXLS);

                JOptionPane.showMessageDialog(null,
                    "Reporte guardado correctamente.\n\nUbicacion:\n" + ruta,
                    "Exportacion exitosa", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,
                    "Error al exportar: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        
        }
        
    }
    private void cargarMovimientos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Monto");
        modelo.addColumn("Tipo");
        modelo.addColumn("Fecha");

        java.util.LinkedList<Ingreso> ingresos = ConsultasBD.getIngresos(cod_use);
        for (Ingreso i : ingresos) {
            modelo.addRow(new Object[]{
                String.format("$ %.2f", i.getIng_monto()), "INGRESO", i.getIng_fecha()
            });
        }

        java.util.LinkedList<Gasto> gastos = ConsultasBD.getGastos(cod_use);
        for (Gasto g : gastos) {
            modelo.addRow(new Object[]{
                String.format("$ %.2f", g.getGas_monto()), "GASTO", g.getGas_fecha()
            });
        }

        table.setModel(modelo);
    }
}