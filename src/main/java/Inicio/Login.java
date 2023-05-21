package Inicio;


import javax.swing.*;
import javax.swing.border.*;

import Clases.Parametros;
import Clases.TextPrompt;

import Principal.MenuPrincipal;

import java.awt.*;
import java.awt.event.*;
import java.net.*;


public class Login extends JFrame implements KeyListener {
	private JLabel lbUserNo, lbPassNo, lbLinea1, lbLinea2;
	private JPasswordField txtPass;
	private JTextField txtUsuario;
	private UIManager.LookAndFeelInfo apariencia[];
	private JLabel lbCaps;
	protected int intento;
	private Parametros info;
	private Object intentoUser[][];
        
        public static void main(String[] args) {
            new Login().setVisible(true);
        }

	public Login() {
		super("Iniciar Sesion");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setUndecorated(true);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.info = new Parametros();
		apariencia = UIManager.getInstalledLookAndFeels();
		intentoUser = new Object[0][2];
		
		this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/Logo-fastcar0.png")).getImage());
		this.setTitle("Alquileres Fastcar");

		try {
			UIManager.setLookAndFeel(apariencia[0].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
			InetAddress ip = InetAddress.getLocalHost();
			// javax.swing.JOptionPane.showMessageDialog(null, ip.getHostAddress());

		}

		// procesar problemas al cambiar la apariencia visual
		catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		iniciarComponentes();
	}

	public void iniciarComponentes() {
		
		setTitle(("Iniciar Sesión"));
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBounds(0, 0, getWidth(), getHeight());
		panelPrincipal.setBackground(Color.white);
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		panelPrincipal.setFocusable(true);

		JLabel lbLogo = new JLabel(new ImageIcon(getClass().getResource("/imagenes/favicon.png")));
		lbLogo.setBounds(50, 50, 47, 35);
		panelPrincipal.add(lbLogo);

		JLabel lbLogo2 = new JLabel("Alquileres Fastcar");
		lbLogo2.setForeground(Color.black);
		lbLogo2.setBounds(lbLogo.getWidth() + lbLogo.getX(), 50, 200, 35);
		lbLogo2.setFont(new Font("Roboto Medium", 1, 20));
		panelPrincipal.add(lbLogo2);

		JLabel lbLogin = new JLabel("INICIAR SESION");
		lbLogin.setForeground(Color.black);
		lbLogin.setBounds(50, lbLogo.getHeight() + lbLogo.getY() + 20, 300, 35);
		lbLogin.setFont(new Font("Roboto Medium", 1, 24));
		panelPrincipal.add(lbLogin);

		JLabel lbUser = new JLabel("Usuario");
		lbUser.setForeground(Color.black);
		lbUser.setBounds(50, lbLogin.getHeight() + lbLogin.getY() + 30, 300, 35);
		lbUser.setFont(new Font("Roboto Medium", 0, 18));
		panelPrincipal.add(lbUser);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(88, 210, 282, 40);
		txtUsuario.setBorder(null);
		txtUsuario.setOpaque(false);
		txtUsuario.setFont(new Font("Roboto Medium", 0, 16));
		panelPrincipal.add(txtUsuario);

		txtUsuario.setForeground(Color.black);
		txtUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		txtUsuario.addKeyListener(this);
                new TextPrompt("Ingrese su nombre de usuario", txtUsuario);

		lbUserNo = new JLabel();
		lbUserNo.setBounds(50, txtUsuario.getY() + txtUsuario.getHeight() + 2, 350, 25);
		lbUserNo.setForeground(Color.red);
		panelPrincipal.add(lbUserNo);

		lbLinea1 = new JLabel();
		lbLinea1.setBounds(50, txtUsuario.getHeight() + txtUsuario.getY(), 320, 1);
		lbLinea1.setBorder(new LineBorder(Color.black, 1));
		panelPrincipal.add(lbLinea1);

		JLabel lbPass = new JLabel("Contraseña");
		lbPass.setForeground(Color.black);
		lbPass.setBounds(50, lbLinea1.getHeight() + lbLinea1.getY() + 40, 300, 35);
		lbPass.setFont(new Font("Roboto Medium", 0, 16));
		panelPrincipal.add(lbPass);

		txtPass = new JPasswordField();
		txtPass.setBounds(88, 326, 289, 40);
		txtPass.setBorder(null);
		txtPass.setOpaque(false);
		txtPass.setFont(new Font("Roboto Medium", 0, 18));
		txtPass.setForeground(Color.black);
		panelPrincipal.add(txtPass);
		txtPass.addKeyListener(this);
		txtPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresar();

			}
		});
                new TextPrompt("Ingrese su contraseña", txtPass);
		lbPassNo = new JLabel();
		lbPassNo.setBounds(50, txtPass.getY() + txtPass.getHeight() + 2, 350, 25);
		lbPassNo.setForeground(Color.red);
		panelPrincipal.add(lbPassNo);

		lbLinea2 = new JLabel();
		lbLinea2.setForeground(new Color(255, 255, 255));
		lbLinea2.setBounds(50, txtPass.getHeight() + txtPass.getY(), 320, 1);
		lbLinea2.setBorder(new LineBorder(Color.black, 1));
		panelPrincipal.add(lbLinea2);

		intento = 0;

		JButton btnIngresar = new JButton("INGRESAR");
		btnIngresar.setBounds(50, lbLinea2.getHeight() + lbLinea2.getY() + 35, 150, 40);
		btnIngresar.setBackground(new Color(33, 37, 41));
		btnIngresar.setForeground(Color.white);
		btnIngresar.setFont(new Font("Roboto Medium", 0, 18));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresar();
			}
		});
		panelPrincipal.add(btnIngresar);

		lbCaps = new JLabel("La tecla Bloq Mayús está activada");
		lbCaps.setBounds(50, 460, 300, 35);
		lbCaps.setFont(new Font("Roboto Medium", 1, 14));
		lbCaps.setForeground(Color.red);
		panelPrincipal.add(lbCaps);
		boolean capsAtivo = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
		lbCaps.setVisible(capsAtivo);

		JPanel subPanel1 = new JPanel();
		subPanel1.setBounds(500, 0, 310, 500);
		subPanel1.setBackground(new Color(33, 37, 41));
		panelPrincipal.add(subPanel1);
		subPanel1.setLayout(null);

		JLabel lbCity = new JLabel();
		lbCity.setBackground(new Color(255, 255, 255));
		lbCity.setForeground(new Color(255, 255, 255));
		lbCity.setBounds(0, 0, subPanel1.getWidth(), subPanel1.getHeight());
                
                
                
		ImageIcon imgIcon = new ImageIcon(getClass().getResource("/imagenes/Logo-fastcar0.png"));
                Image imgEscalada = imgIcon.getImage().getScaledInstance(250,
                250, Image.SCALE_SMOOTH);
                Icon iconoEscalado = new ImageIcon(imgEscalada);

		JLabel lbLogo1 = new JLabel(iconoEscalado);
		lbLogo1.setBounds(0, 125, 300, 255);

		JButton btnCerrar = new JButton(new ImageIcon(getClass().getResource("/imagenes/salir2.png")));
		btnCerrar.setBounds(265, 5, 30, 30);
		btnCerrar.setBackground(new Color(255, 255, 255));
		btnCerrar.setForeground(new Color(0, 0, 0));
		btnCerrar.setFont(new Font("Roboto Medium", 0, 26));
		btnCerrar.setBorder(null);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setRolloverIcon(new ImageIcon(getClass().getResource("/imagenes/Salir.png")));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// btnCerrar.setContentAreaFilled(false);
		subPanel1.add(btnCerrar);
		subPanel1.add(lbLogo1);
		subPanel1.add(lbCity);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/iconoUser.png")));
		lblNewLabel.setBounds(50, 215, 30, 30);
		panelPrincipal.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/imagenes/iconoPass.png")));
		lblNewLabel_1.setBounds(50, 330, 30, 30);
		panelPrincipal.add(lblNewLabel_1);

	}

	protected void ingresar() {
		
	}



	public void limpiar() {
		txtPass.setText(null);
		txtUsuario.setText(null);
		lbLinea1.setBorder(new LineBorder(Color.black, 1));
		lbLinea2.setBorder(new LineBorder(Color.black, 1));
		lbUserNo.setText(null);
		lbPassNo.setText(null);
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
			boolean capsAtivo = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
			lbCaps.setVisible(capsAtivo);
		}
		lbLinea1.setBorder(new LineBorder(Color.white, 1));
		lbLinea2.setBorder(new LineBorder(Color.white, 1));
		lbUserNo.setText(null);
		lbPassNo.setText(null);
		// JOptionPane.showMessageDialog(null, e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
