package Vehiculo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Principal.MenuPrincipal;

public class DialogNuevoVehiculo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JPanel panelCargando;

	public DialogNuevoVehiculo(MenuPrincipal fr) {
		super(fr);
		setBounds(0, 0, 1000, 675);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);

		getContentPane().add(new NuevoVehiculo(fr,this));
		
		panelCargando = new JPanel();
		panelCargando.setBounds(0, 0, getWidth(), getHeight());
		panelCargando.setBackground(new Color(241,242,243));
		panelCargando.setVisible(false);
		//getContentPane().add(panelCargando);
		panelCargando.setLayout(null);
		
		JLabel lbImgCargando = new JLabel(new ImageIcon(getClass().getResource("/imagenes/cargando.gif")));
		lbImgCargando.setBounds(246, 52, 200, 200);
		panelCargando.add(lbImgCargando);
		
		JLabel lbTextoCargando = new JLabel("Guardando los datos del modelo...",SwingConstants.CENTER);
		lbTextoCargando.setFont(new Font("Arial Black", Font.BOLD, 12));
		lbTextoCargando.setBounds(203, 265, 286, 30);
		lbTextoCargando.setForeground(new Color(29,63,114));
		panelCargando.add(lbTextoCargando);
	}

}
