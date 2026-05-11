import java.sql.Date;

public class Gasto {
	private String gas_codigo;
    private String usu_codigo;
    private double gas_monto;
    private String gas_categoria;
    private String gas_descripcion;
    private Date gas_fecha;
    private String apa_codigo;
    
	public Gasto() {
	}

	public Gasto(String gas_codigo, String usu_codigo, double gas_monto, String gas_categoria, String gas_descripcion,
			Date gas_fecha, String apa_codigo) {
		super();
		this.gas_codigo = gas_codigo;
		this.usu_codigo = usu_codigo;
		this.gas_monto = gas_monto;
		this.gas_categoria = gas_categoria;
		this.gas_descripcion = gas_descripcion;
		this.gas_fecha = gas_fecha;
		this.apa_codigo = apa_codigo;
	}

	public String getGas_codigo() {
		return gas_codigo;
	}

	public void setGas_codigo(String gas_codigo) {
		this.gas_codigo = gas_codigo;
	}

	public String getUsu_codigo() {
		return usu_codigo;
	}

	public void setUsu_codigo(String usu_codigo) {
		this.usu_codigo = usu_codigo;
	}

	public double getGas_monto() {
		return gas_monto;
	}

	public void setGas_monto(double gas_monto) {
		this.gas_monto = gas_monto;
	}

	public String getGas_categoria() {
		return gas_categoria;
	}

	public void setGas_categoria(String gas_categoria) {
		this.gas_categoria = gas_categoria;
	}

	public String getGas_descripcion() {
		return gas_descripcion;
	}

	public void setGas_descripcion(String gas_descripcion) {
		this.gas_descripcion = gas_descripcion;
	}

	public Date getGas_fecha() {
		return gas_fecha;
	}

	public void setGas_fecha(Date gas_fecha) {
		this.gas_fecha = gas_fecha;
	}

	public String getApa_codigo() {
		return apa_codigo;
	}

	public void setApa_codigo(String apa_codigo) {
		this.apa_codigo = apa_codigo;
	}
	
	
	
	
    
    

}
