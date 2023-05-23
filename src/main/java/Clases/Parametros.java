package Clases;

public class Parametros {
	private String usuario,token,apiPath;
	public Parametros() {
            token = null;
	    usuario = null;
            apiPath = "http://localhost:8080";
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
        public String getApiPath(){
            return apiPath;
        }
        public void setApiPath(String newPath){
            this.apiPath = newPath;
        }
	

}
