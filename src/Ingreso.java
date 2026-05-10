import java.sql.Date;

public class Ingreso {
	 private String ing_codigo;
	 private String usu_codigo;
	 private double ing_monto;
	 private String ing_descripcion;
	 private Date ing_fecha;
	 
	 public Ingreso() {
	 }

	 public Ingreso(String ing_codigo, String usu_codigo, double ing_monto, String ing_descripcion,
			Date ing_fecha) {
		this.ing_codigo = ing_codigo;
		this.usu_codigo = usu_codigo;
		this.ing_monto = ing_monto;
		this.ing_descripcion = ing_descripcion;
		this.ing_fecha = ing_fecha;
	 }

	 public String getIng_codigo() {
		 return ing_codigo;
	 }

	 public void setIng_codigo(String ing_codigo) {
		 this.ing_codigo = ing_codigo;
	 }

	 public String getUsu_codigo() {
		 return usu_codigo;
	 }

	 public void setUsu_codigo(String usu_codigo) {
		 this.usu_codigo = usu_codigo;
	 }

	 public double getIng_monto() {
		 return ing_monto;
	 }

	 public void setIng_monto(double ing_monto) {
		 this.ing_monto = ing_monto;
	 }

	 public String getIng_descripcion() {
		 return ing_descripcion;
	 }

	 public void setIng_descripcion(String ing_descripcion) {
		 this.ing_descripcion = ing_descripcion;
	 }

	 public Date getIng_fecha() {
		 return ing_fecha;
	 }

	 public void setIng_fecha(Date ing_fecha) {
		 this.ing_fecha = ing_fecha;
	 }
	 
	 
	 
	 
	 
	 

}
