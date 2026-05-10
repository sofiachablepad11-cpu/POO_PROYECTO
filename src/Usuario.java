

public class Usuario {
	private String usu_codigo;
    private String usu_nombre;
    private String usu_apellido;
    private String usu_correo;
    private String usu_password;
    
	public Usuario() {
	}

	public Usuario(String usu_codigo, String usu_nombre, String usu_apellido, String usu_correo, String usu_password) {
		this.usu_codigo = usu_codigo;
		this.usu_nombre = usu_nombre;
		this.usu_apellido = usu_apellido;
		this.usu_correo = usu_correo;
		this.usu_password = usu_password;
	}

	public String getUsu_codigo() {
		return usu_codigo;
	}

	public void setUsu_codigo(String usu_codigo) {
		this.usu_codigo = usu_codigo;
	}

	public String getUsu_nombre() {
		return usu_nombre;
	}

	public void setUsu_nombre(String usu_nombre) {
		this.usu_nombre = usu_nombre;
	}

	public String getUsu_apellido() {
		return usu_apellido;
	}

	public void setUsu_apellido(String usu_apellido) {
		this.usu_apellido = usu_apellido;
	}

	public String getUsu_correo() {
		return usu_correo;
	}

	public void setUsu_correo(String usu_correo) {
		this.usu_correo = usu_correo;
	}

	public String getUsu_password() {
		return usu_password;
	}

	public void setUsu_password(String usu_password) {
		this.usu_password = usu_password;
	}
	
	
	
	
    
    

}
