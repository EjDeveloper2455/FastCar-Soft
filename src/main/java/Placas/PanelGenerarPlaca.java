package Placas;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import Clases.Parametros;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JToggleButton;

public class PanelGenerarPlaca extends JPanel {

	private String numeroPlaca;
	private JLabel lbNumeroPlaca;
	public PanelGenerarPlaca() {
		setBackground(new Color(255, 255, 255));
		setBorder(new LineBorder(new Color(0, 0, 0), 1));
		setSize(500,223);
		setLayout(null);
		
		numeroPlaca = "";
		
		JPanel panelCabecera = new JPanel();
		panelCabecera.setBackground(new Color(0, 39, 214));
		panelCabecera.setBounds(0, 0, 500, 53);
		add(panelCabecera);
		panelCabecera.setLayout(null);
		
		Parametros info = new Parametros();
		
		JLabel lbImgHn = new JLabel(info.redimensionarImg(new ImageIcon(getClass().getResource("/imagenes/hn.png")), 45, 25));
		lbImgHn.setBorder(new LineBorder(Color.white,2));
		lbImgHn.setBounds(15, 11, 45, 25);
		panelCabecera.add(lbImgHn);
		
		JLabel lblNewLabel = new JLabel("HONDURAS");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(150, 8, 200, 30);
		panelCabecera.add(lblNewLabel);
		
		JLabel lbCiruculo1 = new JLabel(new ImageIcon(getClass().getResource("/imagenes/circulo_gris.png")));
		lbCiruculo1.setBounds(95, 10, 15, 15);
		panelCabecera.add(lbCiruculo1);
		
		JLabel lbCiruculo2 = new JLabel(new ImageIcon(getClass().getResource("/imagenes/circulo_gris.png")));
		lbCiruculo2.setBounds(405, 10, 15, 15);
		panelCabecera.add(lbCiruculo2);
		
		lbNumeroPlaca = new JLabel(numeroPlaca);
		lbNumeroPlaca.setFont(new Font("Crossfit Demi", Font.PLAIN, 130));
		lbNumeroPlaca.setBounds(15, 60, 480, 139);
		add(lbNumeroPlaca);
		
		JLabel lbCA = new JLabel("CENTROAMÉRICA");
		lbCA.setFont(new Font("Arial", Font.PLAIN, 30));
		lbCA.setHorizontalAlignment(SwingConstants.CENTER);
		lbCA.setBounds(95, 185, 300, 38);
		lbCA.setForeground(new Color(0, 39, 214));
		add(lbCA);
		
		JLabel lbQR = new JLabel(info.redimensionarImg(new ImageIcon(getClass().getResource("/imagenes/qr.png")),30,30));
		lbQR.setBounds(15, 185, 30, 30);
		add(lbQR);
		
		JLabel lbBarcode = new JLabel(info.redimensionarImg(new ImageIcon(getClass().getResource("/imagenes/barcode.png")),50, 10));
		lbBarcode.setBounds(435, 202, 50, 10);
		add(lbBarcode);
		
		JPanel panelRect1 = new JPanel();
		panelRect1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRect1.setBackground(new Color(128, 128, 128));
		panelRect1.setBounds(68, 202, 25, 10);
		add(panelRect1);
		
		JPanel panelRect2 = new JPanel();
		panelRect2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRect2.setBackground(Color.GRAY);
		panelRect2.setBounds(395, 202, 25, 10);
		add(panelRect2);
	}
	public void setNumeroPlaca(String nuevoNumero) {
		nuevoNumero = nuevoNumero.toUpperCase();
		
		String c = "";
		for(int i=0; i<nuevoNumero.length();i++) {
			if(i==1)c+=" ";
			if(i==3)c+=" ";
			c+=nuevoNumero.charAt(i);
		}
		numeroPlaca = c;
		lbNumeroPlaca.setText(numeroPlaca);
	}
}
