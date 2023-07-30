package Clases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Empleado.NuevoEmpleado;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualizarImagen extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public VisualizarImagen(ImageIcon img,NuevoEmpleado nuevoEmpledo, JDialog dialog) {
		super(dialog,true);
		setSize(496, 590);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(33,37,41));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbImagen = new JLabel(img);
		lbImagen.setBounds(0, 0, 480, 480);
		contentPanel.add(lbImagen);
		
		JButton btnNueva = new JButton("Nueva foto",new ImageIcon(getClass().getResource("/imagenes/nuevo.png")));
		btnNueva.setBackground(new Color(255, 255, 255));
		btnNueva.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnNueva.setBounds(20, 491, 180, 50);
		contentPanel.add(btnNueva);
		
		JButton btnAgregar = new JButton("Agregar",new ImageIcon(getClass().getResource("/imagenes/nueva-foto.png")));
		btnAgregar.addActionListener(e -> {
			nuevoEmpledo.setImagen(img);
			dispose();
		});
		btnAgregar.setBackground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnAgregar.setBounds(280, 491, 180, 50);
		contentPanel.add(btnAgregar);
	}
}
