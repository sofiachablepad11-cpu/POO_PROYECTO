import java.sql.Date;

public class Apartado {
	private String apa_codigo;
    private String usu_codigo;
    private double apa_limite;
    private String apa_categoria;
    private Date apa_fecha;
    
	public Apartado() {
	}

	public Apartado(String apa_codigo, String usu_codigo, double apa_limite, String apa_categoria, Date apa_fecha) {
		this.apa_codigo = apa_codigo;
		this.usu_codigo = usu_codigo;
		this.apa_limite = apa_limite;
		this.apa_categoria = apa_categoria;
		this.apa_fecha = apa_fecha;
	}

	public String getApa_codigo() {
		return apa_codigo;
	}

	public void setApa_codigo(String apa_codigo) {
		this.apa_codigo = apa_codigo;
	}

	public String getUsu_codigo() {
		return usu_codigo;
	}

	public void setUsu_codigo(String usu_codigo) {
		this.usu_codigo = usu_codigo;
	}

	public double getApa_limite() {
		return apa_limite;
	}

	public void setApa_limite(double apa_limite) {
		this.apa_limite = apa_limite;
	}

	public String getApa_categoria() {
		return apa_categoria;
	}

	public void setApa_categoria(String apa_categoria) {
		this.apa_categoria = apa_categoria;
	}

	public Date getApa_fecha() {
		return apa_fecha;
	}

	public void setApa_fecha(Date apa_fecha) {
		this.apa_fecha = apa_fecha;
	}
	
	
    
    

}
