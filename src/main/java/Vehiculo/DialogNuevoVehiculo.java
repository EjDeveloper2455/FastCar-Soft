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
import javax.swing.JProgressBar;

public class DialogNuevoVehiculo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JPanel panelCargando;
	private JProgressBar barraCargando;
	private JPanel panel;

	public DialogNuevoVehiculo(MenuPrincipal fr,Vehiculos vehiculos) {
		super(fr);
		setBounds(0, 0, 1000, 675);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		contentPanel.setBounds(0, 0, getWidth(), getHeight());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel);
		
		panel = new JPanel();
		panel.setBounds(0, 0, getWidth(), getHeight());
		panel.setLayout(null);
		contentPanel.add(panel);
		//panel.add(new NuevoVehiculo(fr,this,vehiculos));
			
		panelCargando = new JPanel();
		panelCargando.setBounds(0, 0, getWidth(), getHeight());
		panelCargando.setBackground(new Color(241,242,243));
		panelCargando.setVisible(false);
		panel.add(panelCargando);
		panelCargando.setLayout(null);
		
		JLabel lbImgCargando = new JLabel(new ImageIcon(getClass().getResource("/imagenes/cargando.gif")));
		lbImgCargando.setBounds(400, 150, 200, 200);
		panelCargando.add(lbImgCargando);
		
		JLabel lbTextoCargando = new JLabel("Guardando los datos del vehículo..",SwingConstants.CENTER);
		lbTextoCargando.setFont(new Font("Arial Black", Font.BOLD, 12));
		lbTextoCargando.setBounds(361, 401, 286, 30);
		lbTextoCargando.setForeground(new Color(29,63,114));
		panelCargando.add(lbTextoCargando);
		
		barraCargando = new JProgressBar();
		barraCargando.setBounds(100, 551, 800, 40);
		panelCargando.add(barraCargando);
	}
	public void setCargando() {
		panelCargando.setVisible(true);
	}
	public void setBarraSum(int barra) {
		barraCargando.setValue(barraCargando.getValue()+barra);
	}
	public void setBarra(int barra) {
		barraCargando.setValue(barra);
	}
}
