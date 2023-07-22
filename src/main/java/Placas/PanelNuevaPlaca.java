package Placas;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class PanelNuevaPlaca extends JPanel {
	private JTextField txtPlaca;
	private PanelGenerarPlaca panelPlaca;

	public static void main(String args[]) {
		JFrame fr = new JFrame();
		fr.setSize(1000,660);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.getContentPane().add(new PanelNuevaPlaca());
		fr.setVisible(true);
	}
	public PanelNuevaPlaca() {
		setBackground(new Color(255, 255, 255));
		setSize(1000,660);
		setLayout(null);
		
		txtPlaca = new JTextField();
		txtPlaca.setBounds(10, 30, 300, 30);
		txtPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendNumeroPlaca(txtPlaca.getText());
			}
		});
		add(txtPlaca);
		txtPlaca.setColumns(10);
		
		JButton btnGenerar = new JButton("Generar placa");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendNumeroPlaca(txtPlaca.getText());
			}
		});
		btnGenerar.setBackground(new Color(255, 255, 255));
		btnGenerar.setFont(new Font("Arial", Font.BOLD, 12));
		btnGenerar.setBounds(320, 30, 120, 30);
		add(btnGenerar);
		
		panelPlaca = new PanelGenerarPlaca();
		panelPlaca.setBounds(btnGenerar.getX()+btnGenerar.getWidth()+10, txtPlaca.getY(), 500, 233);
		add(panelPlaca);
	}
	public void sendNumeroPlaca(String nuevoNumero) {
		if(nuevoNumero.length()>7) {
			JOptionPane.showMessageDialog(null, "La cantidad de caracteres en el numero de placa no debe ser mayor a 7","ERROR",0);
			return;
		}
		String le = nuevoNumero.substring(0, 3);
		JOptionPane.showMessageDialog(null, le);
		panelPlaca.setNumeroPlaca(nuevoNumero);
	}
	public boolean validarNumeros(char l) {
		char n[] = {'0','1','2','3','4','5','6','7','8','9'};
		for (int i = 0; i < n.length; i++) if(l==n[i])return true;
		return false;
	}
}
