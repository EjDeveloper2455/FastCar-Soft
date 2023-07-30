package Clases;

import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Parametros {
	private String usuario, token, apiPath;
	private Color primaryColor, secondColor;
	private int sucursal;
	

	public Parametros() {
		token = null;
		usuario = null;
		apiPath = "http://34.16.133.9:8080";
		//apiPath = "http://34.16.133.9:8080";
		primaryColor = new Color(33,37,41);
		secondColor = new Color(86,89,92);
		sucursal = -1;
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

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String newPath) {
		this.apiPath = newPath;
	}
	
	public Color getPrimaryColor() {
		return this.primaryColor;
	}
	public Color getSecondColor() {
		return this.secondColor;
	}
	
	public int getSucursal() {
		return sucursal;
	}

	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	public ImageIcon redimensionarImg(ImageIcon imgIcon, int ancho, int alto) {
        Image imgEscalada = imgIcon.getImage().getScaledInstance(ancho,
                alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgEscalada);
	}
	public ImageIcon obtenerImagen(String urlImagen) {
		try {
			java.net.URL url = new java.net.URL(urlImagen);
            
            return new ImageIcon(url);
        } catch (Exception e) {

        }
		return null;
	}

}
