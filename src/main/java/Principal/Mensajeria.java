package Principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;

public class Mensajeria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelContacto;
	private JPanel panelMensaje;
	private JPanel panelChat;
	private JPanel panelEscritura;


	public Mensajeria(JButton btnMensaje,JFrame fr) {
		super(fr);
		setBounds(btnMensaje.getX()-100,btnMensaje.getY()+50,500, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBorder(new LineBorder(Color.black,1));
		setUndecorated(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		panelContacto = new JPanel();
		panelContacto.setBackground(new Color(33, 37, 41));
		
		JScrollPane scrollContacto = new JScrollPane(panelContacto);
		panelContacto.setLayout(null);
		scrollContacto.setBounds(0, 30, 150, 570);
		contentPanel.add(scrollContacto);
		
		panelMensaje = new JPanel();
		panelMensaje.setBackground(Color.white);
		JScrollPane scrollMensaje = new JScrollPane(panelMensaje);
		panelMensaje.setLayout(null);
		scrollMensaje.setBounds(150, 30, 350, 570);
		contentPanel.add(scrollMensaje);
		
		panelChat = new JPanel();
		panelChat.setBounds(0, 0, 350, 520);
		panelChat.setBackground(Color.white);
		panelMensaje.add(panelChat);
		
		panelEscritura = new JPanel();
		panelEscritura.setBounds(-1, panelChat.getHeight()+panelChat.getY(), 350, 50);
		panelEscritura.setBackground(Color.white);
		panelEscritura.setBorder(new LineBorder(Color.black,1));
		panelMensaje.add(panelEscritura);
		panelEscritura.setLayout(null);
		
		JTextPane txtEscritura = new JTextPane();
		txtEscritura.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtEscritura.setBorder(null);
		
		JScrollPane scrolEscritura = new JScrollPane(txtEscritura);
		scrolEscritura.setBounds(2, 2, 300, 45);
		panelEscritura.add(scrolEscritura);
		//JScrollPane.setBorder(null);
		
		JButton btnEnviar = new JButton();
		btnEnviar.setIcon(new ImageIcon(Mensajeria.class.getResource("/imagenes/enviarMensaje.png")));
		btnEnviar.setBounds(302, 2, 45, 45);
		btnEnviar.setBorder(null);
		btnEnviar.setContentAreaFilled(false);
		btnEnviar.setRolloverIcon(new ImageIcon(Mensajeria.class.getResource("/imagenes/enviarMensajen.png")));
		panelEscritura.add(btnEnviar);
	}
}
