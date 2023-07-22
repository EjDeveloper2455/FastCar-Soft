package Vehiculo;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelImagenModelo extends JPanel {

	private int position;
	private NuevoVehiculo dialog;
	public PanelImagenModelo(ImageIcon img,NuevoVehiculo dialog) {
		//setBounds(x,15,130,130);
		setBackground(new Color(86,89,92));
		setBorder(new LineBorder(Color.white,1));
		setLayout(null);
		
		this.position = 0;
		this.dialog = dialog;
		
		JLabel lbImagen = new JLabel();
		lbImagen.setBounds(5, 5, 100, 80);
		int newHeight = (int) (((double) img.getIconHeight() / img.getIconWidth()) * lbImagen.getWidth());
		lbImagen.setIcon(redimensionarImg(img, lbImagen.getWidth(), newHeight));
		lbImagen.setBorder(new LineBorder(Color.white,1));
		add(lbImagen);
		
		JButton btnEliminar = new JButton(new ImageIcon(getClass().getResource("/imagenes/delete.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.eliminarImagen(position);
			}
		});
		btnEliminar.setBackground(new Color(255, 255, 255));
		btnEliminar.setBounds(5, lbImagen.getHeight()+lbImagen.getY()+5, 100, 23);
		add(btnEliminar);

	}
	
	
	
	public int getPosition() {
		return position;
	}



	public void setPosition(int position) {
		this.position = position;
	}



	public ImageIcon redimensionarImg(ImageIcon imgIcon, int ancho, int alto) {
        Image imgEscalada = imgIcon.getImage().getScaledInstance(ancho,
                alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgEscalada);
	}
}
