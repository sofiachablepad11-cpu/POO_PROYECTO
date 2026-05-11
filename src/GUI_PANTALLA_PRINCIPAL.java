
import java.awt.Color;
import java.awt.EventQueue;
import conec.CONECTA;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_PANTALLA_PRINCIPAL extends JFrame {

	private static final long serialVersionUID = 1L;
	private String cod_use;
	private JLabel lblsaldo;
    private JLabel lblingreso;
    private JLabel lblgasto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_PANTALLA_PRINCIPAL frame = new GUI_PANTALLA_PRINCIPAL("123");
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
	public GUI_PANTALLA_PRINCIPAL(String cod_use) {
		    this.cod_use= cod_use;

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
        
        JLabel lblNewLabel = new JLabel("INICIO");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(130, 11, 112, 14);
        card.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Saldo actual");
        lblNewLabel_1.setBounds(20, 35, 86, 14);
        card.add(lblNewLabel_1);
        
        lblsaldo = new JLabel("0.00");
        lblsaldo.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblsaldo.setBounds(56, 50, 247, 51);
        card.add(lblsaldo);
        
        JLabel lblNewLabel_3 = new JLabel("$");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_3.setBounds(30, 60, 18, 34);
        card.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Ingreso extra:");
        lblNewLabel_4.setForeground(new Color(0, 153, 51));
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_4.setBounds(20, 112, 95, 14);
        card.add(lblNewLabel_4);
        
        lblingreso = new JLabel("$ 0.00");
        lblingreso.setForeground(new Color(0, 153, 51));
        lblingreso.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblingreso.setBounds(112, 112, 71, 14);
        card.add(lblingreso);
        
        JLabel lblNewLabel_4_1 = new JLabel("Gastos:");
        lblNewLabel_4_1.setForeground(new Color(153, 51, 51));
        lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_4_1.setBounds(193, 112, 66, 14);
        card.add(lblNewLabel_4_1);
        
        lblgasto = new JLabel("$ 0.00");
        lblgasto.setForeground(new Color(153, 51, 51));
        lblgasto.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblgasto.setBounds(244, 112, 71, 14);
        card.add(lblgasto);
        
        JButton btningreso = new JButton("Agregar ingresos extras");
        btningreso.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_INGRESOS PRE = new GUI_INGRESOS(cod_use);
				PRE.setVisible(true);

				
				dispose();
        	}
        });
        btningreso.setBounds(20, 136, 269, 34);
        btningreso.setBackground(new Color(52, 152, 219));
        btningreso.setForeground(Color.WHITE);
        btningreso.setFocusPainted(false);
        btningreso.setBorderPainted(false);
        btningreso.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btningreso);

        
        JButton btngasto = new JButton("Agregar gasto");
        btngasto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_GASTO GAS = new GUI_GASTO(cod_use);
				GAS.setVisible(true);

				
				dispose();
        	}
        });
        btngasto.setBounds(20, 193, 269, 34);
        btngasto.setBackground(new Color(52, 152, 219));
        btngasto.setForeground(Color.WHITE);
        btngasto.setFocusPainted(false);
        btngasto.setBorderPainted(false);
        btngasto.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btngasto);
        
        JButton btnmovimientos = new JButton("Movimientos");
        btnmovimientos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_REPORTES REP = new GUI_REPORTES(cod_use);
				REP.setVisible(true);

				
				dispose();
        		
        	}
        });
        btnmovimientos.setBounds(20, 300, 269, 34);
        btnmovimientos.setBackground(new Color(52, 152, 219));
        btnmovimientos.setForeground(Color.WHITE);
        btnmovimientos.setFocusPainted(false);
        btnmovimientos.setBorderPainted(false);
        btnmovimientos.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(btnmovimientos);
        
        JLabel lblMasOpciones = new JLabel("MAS OPCIONES");
        lblMasOpciones.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblMasOpciones.setBounds(20, 362, 112, 14);
        card.add(lblMasOpciones);
        
        JButton btnpresupuesto = new JButton("Presupuesto");
        btnpresupuesto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_PRESUPUESTO PRE = new GUI_PRESUPUESTO(cod_use);
				PRE.setVisible(true);
				dispose();
        	}
        });
        btnpresupuesto.setBounds(20, 387, 269, 34);
        btnpresupuesto.setBackground(Color.WHITE);
        btnpresupuesto.setFocusPainted(false);
        btnpresupuesto.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        card.add(btnpresupuesto);
        
        JButton btnalertas = new JButton("Alertas");
        btnalertas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_ALARMAS ALA = new GUI_ALARMAS(cod_use);
				ALA.setVisible(true);

				
				dispose();
        	}
        });
        btnalertas.setBounds(20, 438, 269, 34);
        btnalertas.setBackground(Color.WHITE);
        btnalertas.setFocusPainted(false);
        btnalertas.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        card.add(btnalertas);
        
        JButton btncerrar = new JButton("Cerrar Sesion");
        btncerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_LOGIN login = new GUI_LOGIN();
				login.setVisible(true);
				dispose();
        	}
        });
        btncerrar.setBounds(20, 494, 269, 34);
        btncerrar.setBackground(new Color(233, 30, 99));
        btncerrar.setForeground(Color.WHITE);
        btncerrar.setFocusPainted(false);
        btncerrar.setBorderPainted(false);
        card.add(btncerrar);
        
        JButton btn_apartados = new JButton("Apartados");
        btn_apartados.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUI_APARTADO APA = new GUI_APARTADO(cod_use);
                APA.setVisible(true);
                dispose();
        	}
        });
        btn_apartados.setForeground(Color.WHITE);
        btn_apartados.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn_apartados.setFocusPainted(false);
        btn_apartados.setBorderPainted(false);
        btn_apartados.setBackground(new Color(52, 152, 219));
        btn_apartados.setBounds(20, 248, 269, 34);
        card.add(btn_apartados);
        
        cargarResumen();
		

	}
	
	private void cargarResumen() {
        double totalIng = ConsultasBD.getTotalIngresos(cod_use);
        double totalGas = ConsultasBD.getTotalGastos(cod_use);
        double saldo    = ConsultasBD.getSaldo(cod_use);

        lblingreso.setText(String.format("$ %.2f", totalIng));
        lblgasto.setText(String.format("$ %.2f", totalGas));
        lblsaldo.setText(String.format("%.2f", saldo));

        if (saldo < 0) {
            lblsaldo.setForeground(new Color(220, 53, 69));
        } else {
            lblsaldo.setForeground(Color.BLACK);
        }
    }
}
