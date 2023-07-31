package Principal;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Json;
import Clases.Parametros;
import Empleado.PanelEmpleado;
import Inicio.Login;
import Solicitud.SOLICITUD;
import Vehiculo.Vehiculos;
import Web.PanelWeb;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;

import javax.swing.SwingConstants;

public class MenuPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane, panelLateral, panelPrincipal, panelCabecera;
    private PanelPadre panels[],panelEmpleado,panelInicio,panelVehiculos,panelSolicitud;
    private JButton btnIconos[];
    private MenuPrincipal menuPrincipal;
    protected Mensajeria dialogMensaje;
    private boolean estadoBtnMensaje;
    private Parametros info;
    private JButton btnAyuda, btnHistorial, btnSalir, btnMiminizar, btnMensaje;
    private JLabel lbTitulo;
    private CardLayout cardLayout;
    private int btnSelect;
	private AbstractButton btnLogin;
	private JLabel lbNombreUsuario;
	private JLabel lbCargo;
	private boolean isLogeado;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setUndecorated(true);
        
        this.info = new Parametros();
        menuPrincipal = this;
        estadoBtnMensaje = false;
        this.info = new Parametros();
        btnSelect = 0;
        isLogeado = false;

        String iconText[] = {"Inicio","Empleados", "Vehículos","Solicitud","Nuevas Placas", "Documentos",  "Alquiler",
            "Reporte","Administración"};
        
        String iconPath[] = {"Inicio","Empleados","Vehiculos","web", "Clientes", "Documentos",  "Alquiler",
            "Reportes","Administracion"};
        
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);
        panelPrincipal.setBounds(200, 40, 1000, 660);
        contentPane.add(panelPrincipal);
        
        //JOptionPane.showMessageDialog(null, "Se inicia");
        
        //Navegacion entre paneles-----------------------------------------------------------
        
        panelEmpleado = new PanelEmpleado(this);
        panelInicio = new PanelInicio(this);
        panelVehiculos = new Vehiculos(this,true);
        panelSolicitud = new SOLICITUD(this);
        
        
        panels = new PanelPadre[]{panelInicio,panelEmpleado, panelVehiculos,panelSolicitud};
        for (int i = 0; i < panels.length; i++) {
            panelPrincipal.add(panels[i], "panel" + iconText[i]);
        }
        panelPrincipal.setVisible(false);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        //----------------------------------------------------------------------------------------

        panelLateral = new JPanel();
        panelLateral.setBackground(info.getPrimaryColor());
        panelLateral.setBounds(0, 0, 200, getHeight());
        panelLateral.setLayout(null);
        contentPane.add(panelLateral);

        JLabel lbLogo = new JLabel(info.redimensionarImg(new ImageIcon(getClass().getResource("/imagenes/Logo-fastcar0.png")), 150, 150));
        lbLogo.setBounds(0, 0, 200, 100);
        panelLateral.add(lbLogo);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(72, 76, 81));
        separator.setBackground(new Color(72, 76, 81));
        separator.setBounds(0, 160, 200, 1);
        panelLateral.add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(new Color(72, 76, 81));
        separator_1.setBackground(new Color(72, 76, 81));
        separator_1.setBounds(0, 640, 200, 1);
        panelLateral.add(separator_1);

        Calendar c = new GregorianCalendar();

        lbNombreUsuario = new JLabel(info.getUsuario());
        lbNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lbNombreUsuario.setForeground(Color.WHITE);
        lbNombreUsuario.setFont(new Font("Roboto Medium", Font.BOLD, 12));
        lbNombreUsuario.setBounds(0, 90, 200, 20);
        panelLateral.add(lbNombreUsuario);

        lbCargo = new JLabel();
        lbCargo.setHorizontalAlignment(SwingConstants.CENTER);
        lbCargo.setForeground(Color.WHITE);
        lbCargo.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lbCargo.setBounds(0, 110, 200, 20);
        panelLateral.add(lbCargo);
        
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(new Font("Roboto Medium", Font.BOLD, 11));
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnLogin.setText("Iniciar Sesión");
        		btnLogin.setBackground(Color.white);
        		new Login(menuPrincipal).setVisible(true);
        	}
        });
        btnLogin.setBackground(new Color(255, 255, 255));
        btnLogin.setBounds(40, 130, 120, 20);
        panelLateral.add(btnLogin);

        panelCabecera = new JPanel();
        panelCabecera.setBounds(200, 0, getWidth() - 200, 40);
        panelCabecera.setBackground(info.getSecondColor());
        contentPane.add(panelCabecera);
        panelCabecera.setLayout(null);

        this.lbTitulo = new JLabel("Inicio");
        lbTitulo.setFont(new Font("Roboto Medium", Font.BOLD, 18));
        lbTitulo.setBounds(10, 5, 200, 30);
        lbTitulo.setForeground(Color.white);
        panelCabecera.add(lbTitulo);

        btnAyuda = new JButton(new ImageIcon(getClass().getResource("/imagenes/ayuda.png")));
        btnAyuda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnAyuda.setRolloverIcon(new ImageIcon(getClass().getResource("/imagenes/ayudan.png")));
        btnAyuda.setContentAreaFilled(false);
        btnAyuda.setBorder(null);
        btnAyuda.setBounds(getWidth() - 410, 0, 30, 40);
        panelCabecera.add(btnAyuda);

        btnHistorial = new JButton(new ImageIcon(getClass().getResource("/imagenes/historial.png")));
        btnHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnHistorial.setRolloverIcon(new ImageIcon(getClass().getResource("/imagenes/historialn.png")));
        btnHistorial.setContentAreaFilled(false);
        btnHistorial.setBorder(null);
        btnHistorial.setBounds(getWidth() - 370, 0, 30, 40);
        panelCabecera.add(btnHistorial);

        btnMensaje = new JButton(new ImageIcon(getClass().getResource("/imagenes/mensaje.png")));
        btnMensaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!estadoBtnMensaje) {
                    dialogMensaje = new Mensajeria(btnMensaje, menuPrincipal);
                    dialogMensaje.setVisible(true);
                    estadoBtnMensaje = true;
                } else {
                    dialogMensaje.dispose();
                    estadoBtnMensaje = false;
                }
            }
        });
        btnMensaje.setRolloverIcon(new ImageIcon(getClass().getResource("/imagenes/mensajen.png")));
        btnMensaje.setContentAreaFilled(false);
        btnMensaje.setBorder(null);
        btnMensaje.setBounds(getWidth() - 330, 0, 30, 40);
        panelCabecera.add(btnMensaje);

        btnMiminizar = new JButton(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/miminizar.png")));
        btnMiminizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPrincipal.setState(menuPrincipal.ICONIFIED);
            }
        });
        btnMiminizar.setRolloverIcon(new ImageIcon(getClass().getResource("/imagenes/miminizarn.png")));
        btnMiminizar.setContentAreaFilled(false);
        btnMiminizar.setBorder(null);
        btnMiminizar.setBounds(getWidth() - 280, 0, 30, 40);
        panelCabecera.add(btnMiminizar);

        btnSalir = new JButton(new ImageIcon(getClass().getResource("/imagenes/Salirn.png")));
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(ABORT);
            }
        });
        btnSalir.setRolloverIcon(new ImageIcon(getClass().getResource("/imagenes/Salir.png")));
        btnSalir.setContentAreaFilled(false);
        btnSalir.setBorder(null);
        btnSalir.setBounds(getWidth() - 240, 0, 30, 40);
        panelCabecera.add(btnSalir);

        btnIconos = new JButton[iconText.length];
        int iconosY = 175;

        for (int i = 0; i < iconText.length; i++) {
            btnIconos[i] = new JButton(iconText[i], info.redimensionarImg(new ImageIcon(getClass().getResource("/imagenes/"+iconPath[i]+".png")),30,30));
            btnIconos[i].setBounds(0, iconosY, 200, 50);
            panelLateral.add(btnIconos[i]);
            btnIconos[i].setBackground(info.getPrimaryColor());
            btnIconos[i].setHorizontalAlignment(SwingConstants.LEFT);
            btnIconos[i].setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
            btnIconos[i].setForeground(Color.white);
            btnIconos[i].setFont(new Font("Times New Roman", 1, 16));
            btnIconos[i].setVisible(false);
            btnIconos[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < btnIconos.length; i++) {
                        if (e.getSource() == btnIconos[i]) {
                            pintarBoton(i);
                            btnSelect = i;
                            seleccionarPanel("panel" + iconText[i]);
                            
                            if(iconText[i].equals("Administración")){
                                String nuevoIconText[] = {"Usuarios","Moneda","Configuración"};
                                int alto = 0;
                                for (int j = 0; j < iconText.length; j++) alto += 50;
                                
                                SubMenu subMenu = new SubMenu(menuPrincipal,panelLateral,btnIconos[i],nuevoIconText,nuevoIconText);
                            }
                        }
                    }
                }
            });
            btnIconos[i].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e){
                     for (int i = 0; i < btnIconos.length; i++) {
                        if (e.getSource() == btnIconos[i]) {
                            pintarBoton2(i);
                        }
                    }
                }
                public void mouseExited(MouseEvent e){
                    for (int i = 0; i < btnIconos.length; i++) {
                        if (e.getSource() == btnIconos[i]) {
                            despintarBoton(i);
                        }
                    }
                 }
            });
            
            iconosY += 50;
        }

        seleccionarPanel("panel" + iconText[0]);
        
        setVisible(true);
        
        //Verificar si esta logueado
        if(info.getToken()==null) {
        	new Login(this).setVisible(true);
        }

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                panelCabecera.setBounds(200, 0, getWidth() - 200, 40);
                panelLateral.setBounds(0, 0, 200, getHeight());
                btnMensaje.setBounds(getWidth() - 330, 0, 30, 40);
                btnAyuda.setBounds(getWidth() - 360, 0, 30, 40);
                btnHistorial.setBounds(getWidth() - 370, 0, 30, 40);
                btnAyuda.setBounds(getWidth() - 410, 0, 30, 40);
                btnMiminizar.setBounds(getWidth() - 280, 0, 30, 40);
                btnSalir.setBounds(getWidth() - 240, 0, 30, 40);
                panelPrincipal.setBounds(200, 40, getWidth() - 200, getHeight() - 40);
                for (int i = 0; i < panels.length; i++) {
                    panels[i].redimensionar();
                }
            }
        });
        
        setLogin();

    }
    
   public void login(Json response) {
	   //Hacer visibles los iconos del menu de navegacion----------------------------------------
	   panelPrincipal.setVisible(true);
	   for (int i = 0; i < btnIconos.length; i++) {
		   btnIconos[i].setVisible(true);
	   }
	   
	   //Actualizar el encabezado del menu de navegacion----------------------------------------
	   
	   btnLogin.setText("Cerrar Sesión");
	   btnLogin.setBackground(Color.red);
	   lbNombreUsuario.setText(response.getIn("user~Nombre").toString());
	   lbCargo.setText(response.getIn("user~Rol").toString());
	   
	   //Actualizar los parametros--------------------------------------------------------------
	   
	   this.info.setToken(response.get("token").toString());
	   this.info.setSucursal(Integer.parseInt(response.get("sucursal").toString()));
	   
	   
	   //Hacer login a los paneles---------------------------------------------------------
	   panelEmpleado.login();
	   panelInicio.login();
	   panelVehiculos.login();
	   isLogeado = true;
   }
   
   public void setLogin() {
	   Runnable runnable = () -> {
		   while(true) {
			   try {
				Thread.sleep(60000);
				if(isLogeado) {
					   panelEmpleado.login();
					   panelInicio.login();
					   panelVehiculos.login();
				   }
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   
		   /**/
	   };
	   Thread miThread = new Thread(runnable);
	   miThread.start();
   }
    
    public Parametros getInfo() {
        return this.info;
    }

    public void setTitulo(String titulo) {
        this.lbTitulo.setText(titulo);
    }

    public void seleccionarPanel(String name) {
        cardLayout.show(panelPrincipal, name);
        setTitulo("Vehículo");
    }
    
    public void pintarBoton(int index) {
        for (int i = 0; i < btnIconos.length; i++) {
            btnIconos[i].setBackground(info.getPrimaryColor());
        }
        btnIconos[index].setBackground(info.getSecondColor());
    }
    public void pintarBoton2(int index) {
        for (int i = 0; i < btnIconos.length; i++) {
            if(i != btnSelect)btnIconos[i].setBackground(info.getPrimaryColor());
        }
        btnIconos[index].setBackground(info.getSecondColor());
    }
    public void despintarBoton(int index) {
        for (int i = 0; i < btnIconos.length; i++) {
           if(i != btnSelect) btnIconos[i].setBackground(info.getPrimaryColor());
        }
    }
}
