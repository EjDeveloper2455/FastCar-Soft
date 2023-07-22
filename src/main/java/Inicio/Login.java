package Inicio;


import javax.swing.*;
import javax.swing.border.*;

import Clases.Json;
import Clases.Parametros;
import Clases.Peticion;
import Clases.TextPrompt;
import java.util.ArrayList;
import java.util.List;

import Principal.MenuPrincipal;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Login extends JDialog implements KeyListener {
	private JLabel lbUserNo, lbPassNo, lbLinea1, lbLinea2;
	private JPasswordField txtPass;
	private JTextField txtUsuario;
	private UIManager.LookAndFeelInfo apariencia[];
	private JLabel lbCaps;
	protected int intento;
	private MenuPrincipal menuPrincipal;
	private Object intentoUser[][];
        public static void main(String[] args) {
            //new Login().setVisible(true);
        }

	public Login(MenuPrincipal menuPrincipal) {
		super(menuPrincipal);
		setSize(720, 500);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(menuPrincipal);
		getContentPane().setLayout(null);
		setUndecorated(true);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.menuPrincipal = menuPrincipal;
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
		panelPrincipal.setBorder(new LineBorder(new Color(33, 37, 41), 2));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBounds(0, 0, 767, 500);
		panelPrincipal.setBackground(Color.white);
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		panelPrincipal.setFocusable(true);

		JLabel lbLogo = new JLabel(new ImageIcon(getClass().getResource("/imagenes/favicon.png").toString()));
		lbLogo.setBounds(50, 50, 47, 35);
		panelPrincipal.add(lbLogo);

		JLabel lbLogo2 = new JLabel("Alquileres Fastcar");
		lbLogo2.setForeground(Color.black);
		lbLogo2.setBounds(lbLogo.getWidth() + lbLogo.getX(), 50, 200, 35);
		lbLogo2.setFont(new Font("Roboto Medium", 1, 20));
		panelPrincipal.add(lbLogo2);

		JLabel lbLogin = new JLabel("INICIAR SESION");
		lbLogin.setForeground(Color.black);
		lbLogin.setBounds(40, 105, 300, 35);
		lbLogin.setFont(new Font("Roboto Medium", 1, 24));
		panelPrincipal.add(lbLogin);

		JLabel lbUser = new JLabel("Usuario");
		lbUser.setForeground(Color.black);
		lbUser.setBounds(40, 170, 300, 35);
		lbUser.setFont(new Font("Roboto Medium", 0, 18));
		panelPrincipal.add(lbUser);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(78, 210, 282, 40);
		txtUsuario.setBorder(null);
		txtUsuario.setOpaque(false);
		txtUsuario.setFont(new Font("Roboto Medium", 0, 16));
		panelPrincipal.add(txtUsuario);

		txtUsuario.setForeground(Color.black);
		txtUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPass.requestFocus();
			}
		});
		txtUsuario.addKeyListener(this);
                new TextPrompt("Ingrese su nombre de usuario", txtUsuario);

		lbUserNo = new JLabel();
		lbUserNo.setBounds(40, 252, 350, 25);
		lbUserNo.setForeground(Color.red);
		panelPrincipal.add(lbUserNo);

		lbLinea1 = new JLabel();
		lbLinea1.setBounds(40, 250, 320, 1);
		lbLinea1.setBorder(new LineBorder(Color.black, 1));
		panelPrincipal.add(lbLinea1);

		JLabel lbPass = new JLabel("Contraseña");
		lbPass.setForeground(Color.black);
		lbPass.setBounds(40, 291, 300, 35);
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
		lbLinea2.setBounds(40, 366, 320, 1);
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
		subPanel1.setBounds(420, 0, 310, 500);
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
				dispose();
			}
		});
		// btnCerrar.setContentAreaFilled(false);
		subPanel1.add(btnCerrar);
		subPanel1.add(lbLogo1);
		subPanel1.add(lbCity);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/iconoUser.png")));
		lblNewLabel.setBounds(40, 215, 30, 30);
		panelPrincipal.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/imagenes/iconoPass.png")));
		lblNewLabel_1.setBounds(40, 330, 30, 30);
		panelPrincipal.add(lblNewLabel_1);

	}

	protected void ingresar() {
            Json datos = new Json();
            datos.put("email", txtUsuario.getText());
            datos.put("password", String.valueOf(txtPass.getPassword()));
            try{
               Json response = Peticion.post(menuPrincipal.getInfo().getApiPath()+"/api/user/login", datos);
               
               String ip = getIp();
               Json sucursal = Peticion.get(menuPrincipal.getInfo().getApiPath()+"/api/computadora/"+ip, "");
               
               if(sucursal.longitud()>0) {
            	   response.put("sucursal", sucursal.get(0).get("sucursal").toString());
            	   dispose();
                   menuPrincipal.login(response);
               }else {
            	   registrarPC(response.getIn("user~Rol").toString(),ip,response);
               }
               
            }catch(Exception exp){
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas","Error",0);
            }
	}
	
	public void registrarPC(String rol,String ip,Json response) {
		if(rol.equals("Administrador") || rol.equals("Gerente de sucursal")) {
			try {
				List<JSONObject> sucursales = Peticion.get("http://localhost:8080/api/sucursal/", "").getArrayList();
				String opciones[] = new String[sucursales.size()];
				int i = 0;
				for (JSONObject data : sucursales) {
					opciones[i] = data.get("ciudad")+" - "+data.get("sucursal");
					i++;
				}
				
				JPanel panel = new JPanel();
				panel.setSize(200,100);
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
				JLabel lbTitulo = new  JLabel("Esta computadora no se encuentra registrada en el sistema");
				lbTitulo.setFont(new Font("Arial Black", Font.BOLD, 14));
				JComboBox cmbOpciones = new JComboBox(opciones);
				
				panel.add(lbTitulo);
				panel.add(new JLabel("Para agregar su computadora al sistema selecciona una sucursal y has click en \"Registrar\""));
				panel.add(new JLabel(""));
				panel.add(cmbOpciones);
				
				Object botones[] = {"Registrar","Cancelar"};
				int select = JOptionPane.showOptionDialog(this, panel, "Registrar", 1, 1, null, botones, botones);
				if(select == 0) {
					Json datos = new Json();
					datos.put("ip", ip);
					datos.put("sucursal", sucursales.get(cmbOpciones.getSelectedIndex()).get("id").toString());
					Json res = Peticion.post(menuPrincipal.getInfo().getApiPath()+"/api/computadora/", datos);
					if(res.get("mensaje").toString().equals("Se ha guardado exitosamente")) {
						JOptionPane.showMessageDialog(this, res.get("mensaje"));
						response.put("sucursal", datos.get("sucursal").toString());
						dispose();
		                menuPrincipal.login(response);
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(this, "Esta computadora no se encuentra registrada en el sistema\n\n"+
					"Solos los usuarios con el permiso requerido \npueden agregar computadoras al sistema");
		}
	}

	public String getIp() {
		String ip = "";
		try {
            InetAddress localHost = InetAddress.getLocalHost();
            ip = localHost.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
		return ip;
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
		lbLinea1.setBorder(new LineBorder(Color.black, 1));
		lbLinea2.setBorder(new LineBorder(Color.black, 1));
		lbUserNo.setText(null);
		lbPassNo.setText(null);
		// JOptionPane.showMessageDialog(null, e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
