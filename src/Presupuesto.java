import java.sql.Date;

public class Presupuesto {
	 private String pre_codigo;
	 private String usu_codigo;
	 private double pre_monto_total;
	 private Date pre_fecha_inicio;
	 private Date pre_fecha_final;
	 
	 public Presupuesto() {
	 }

	 public Presupuesto(String pre_codigo, String usu_codigo, double pre_monto_total, Date pre_fecha_inicio,
			Date pre_fecha_final) {
		this.pre_codigo = pre_codigo;
		this.usu_codigo = usu_codigo;
		this.pre_monto_total = pre_monto_total;
		this.pre_fecha_inicio = pre_fecha_inicio;
		this.pre_fecha_final = pre_fecha_final;
	 }

	 public String getPre_codigo() {
		 return pre_codigo;
	 }

	 public void setPre_codigo(String pre_codigo) {
		 this.pre_codigo = pre_codigo;
	 }

	 public String getUsu_codigo() {
		 return usu_codigo;
	 }

	 public void setUsu_codigo(String usu_codigo) {
		 this.usu_codigo = usu_codigo;
	 }

	 public double getPre_monto_total() {
		 return pre_monto_total;
	 }

	 public void setPre_monto_total(double pre_monto_total) {
		 this.pre_monto_total = pre_monto_total;
	 }

	 public Date getPre_fecha_inicio() {
		 return pre_fecha_inicio;
	 }

	 public void setPre_fecha_inicio(Date pre_fecha_inicio) {
		 this.pre_fecha_inicio = pre_fecha_inicio;
	 }

	 public Date getPre_fecha_final() {
		 return pre_fecha_final;
	 }

	 public void setPre_fecha_final(Date pre_fecha_final) {
		 this.pre_fecha_final = pre_fecha_final;
	 }
	 
	 

}