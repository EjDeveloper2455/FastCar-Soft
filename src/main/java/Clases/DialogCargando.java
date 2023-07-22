package Clases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class DialogCargando extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogCargando dialog = new DialogCargando("Cargando...");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogCargando(String texto) {
		iniciarComponentes(texto);
	}
	public DialogCargando(String texto,JDialog dialog) {
		super(dialog,true);
		iniciarComponentes(texto);
	}
	public DialogCargando(String texto,JFrame fr) {
		super(fr,true);
		iniciarComponentes(texto);
	}
	
	private void iniciarComponentes(String texto) {
		setSize(500, 400);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(241, 242, 243));
		
		JLabel lbImagenCargando = new JLabel(new ImageIcon(getClass().getResource("/imagenes/cargando.gif")));
		lbImagenCargando.setBounds(150, 50, 200, 200);
		contentPanel.add(lbImagenCargando);
		
		JLabel lbTexto = new JLabel(texto,SwingConstants.CENTER);
		lbTexto.setFont(new Font("Arial Black", Font.BOLD, 16));
		lbTexto.setBounds(10, 288, 464, 38);
		lbTexto.setForeground(new Color(29,63,114));
		contentPanel.add(lbTexto);
	}

}
