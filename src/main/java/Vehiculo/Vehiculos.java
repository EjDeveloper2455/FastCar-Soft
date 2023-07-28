
package Vehiculo;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

import Clases.ColorearFilasIntercaladas;
import Clases.Json;
import Clases.ModeloTabla;
import Clases.Peticion;
import Principal.MenuPrincipal;
import Principal.PanelPadre;

public class Vehiculos extends PanelPadre {

	private JDateChooser fechaDesde;
	private JDateChooser fechaHasta;
	private JComboBox cmbBuscarPor;
	public javax.swing.JButton btnnuevovehiculo, btnnuevomodelo;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane scrollVehiculo, scrollModelo;
	public javax.swing.JTable jTableVehiculo, jTableModelo;
	private javax.swing.JPanel jpbarradisenoVehiculo, jpbarradisenoModelo;
	private javax.swing.JPanel jpmuestratabla;
	private javax.swing.JTextField txtbuscar;
	private Vehiculos vehiculos;
	private Json modelos, jsonVehiculos;
	private int modeloIndex,vehiculoIndex;
	
	public Vehiculos(MenuPrincipal menuPrincipal, boolean par) {
		super(menuPrincipal);
		this.vehiculos = this;
		initComponents();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {		
		modeloIndex = -1;
		vehiculoIndex = -1;
		
		btnnuevomodelo = new javax.swing.JButton("Nuevo modelo",
				new ImageIcon(getClass().getResource("/imagenes/agg.png")));
		btnnuevomodelo.setBackground(new Color(255, 255, 255));
		btnnuevomodelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogModelo(menuPrincipal, vehiculos).setVisible(true);
			}
		});
		btnnuevomodelo.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
		add(btnnuevomodelo);
		btnnuevomodelo.setBounds(10, 5, 200, 40);

		// Boton para abrir formulario para crear nuevo vehiculo
		btnnuevovehiculo = new javax.swing.JButton("Nuevo vehiculo",
				new ImageIcon(getClass().getResource("/imagenes/addCar.png")));
		btnnuevovehiculo.setBackground(info.getPrimaryColor());
		btnnuevovehiculo.setForeground(Color.white);
		btnnuevovehiculo.addActionListener(v ->
				new NuevoVehiculo(menuPrincipal,this).setVisible(true));
		
		btnnuevovehiculo.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
		add(btnnuevovehiculo);

		btnnuevovehiculo.setBounds(btnnuevomodelo.getWidth() + btnnuevomodelo.getHeight() + 10, 5, 200, 40);

		// Inicializar los componentes
		jpmuestratabla = new javax.swing.JPanel();
		jpbarradisenoVehiculo = new javax.swing.JPanel();
		jpbarradisenoModelo = new javax.swing.JPanel();
		scrollVehiculo = new javax.swing.JScrollPane();
		jTableVehiculo = new javax.swing.JTable();
		scrollModelo = new javax.swing.JScrollPane();
		jTableModelo = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		txtbuscar = new javax.swing.JTextField();

		setBackground(new java.awt.Color(255, 255, 255));
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setMaximumSize(new java.awt.Dimension(1000, 650));
		setName("Vehiculos"); // NOI18N
		setPreferredSize(new java.awt.Dimension(1000, 650));
		setLayout(null);

		jpmuestratabla.setBackground(new java.awt.Color(86, 89, 92));
		jpmuestratabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
		jpmuestratabla.setLayout(null);

		jpbarradisenoModelo.setBackground(new java.awt.Color(33, 37, 41));
		jpbarradisenoModelo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MODELOS",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
		jpmuestratabla.add(jpbarradisenoModelo);
		jpbarradisenoModelo.setBounds(0, 0, 355, 40);

		jpbarradisenoVehiculo.setBackground(new java.awt.Color(33, 37, 41));
		jpbarradisenoVehiculo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "VEHÍCULOS",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
		jpmuestratabla.add(jpbarradisenoVehiculo);
		jpbarradisenoVehiculo.setBounds(jpbarradisenoModelo.getWidth() + jpbarradisenoModelo.getX() + 5, 0, 620, 40);

		jTableVehiculo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));
		jTableVehiculo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jTableVehiculo.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 14));
		jTableVehiculo.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Vin", "Placa", "Marca", "Modelo" }));
		scrollVehiculo.setViewportView(jTableVehiculo);

		jTableModelo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));
		jTableModelo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jTableModelo.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 14));
		jTableModelo.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Marca", "Modelo", "Tipo" }));

		scrollModelo.setViewportView(jTableModelo);

		jpmuestratabla.add(scrollVehiculo);
		jpmuestratabla.add(scrollModelo);

		scrollModelo.setBounds(5, jpbarradisenoVehiculo.getHeight() + jpbarradisenoVehiculo.getY() + 5, 350, 450);
		scrollVehiculo.setBounds(scrollModelo.getWidth() + scrollModelo.getX() + 5, scrollModelo.getY(), 615, 450);

		add(jpmuestratabla);
		jpmuestratabla.setBounds(10, 120, 980, 506);

		jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		jLabel1.setText("Buscar: ");
		add(jLabel1);
		jLabel1.setBounds(692, 85, 71, 30);
		add(txtbuscar);
		txtbuscar.setBounds(761, 85, 200, 30);

		JSeparator separator = new JSeparator();
		separator.setBounds(15, 47, 980, 2);
		add(separator);

		JButton btnBuscar = new JButton(new ImageIcon(getClass().getResource("/imagenes/lupa.png")));
		btnBuscar.setBackground(Color.white);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Buscar");
			}
		});
		btnBuscar.setBounds(960, 85, 30, 30);
		add(btnBuscar);

		fechaDesde = new JDateChooser();
		fechaDesde.setBounds(110, 62, 240, 25);
		add(fechaDesde);

		fechaHasta = new JDateChooser();
		fechaHasta.setBounds(110, 90, 240, 25);
		add(fechaHasta);

		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDesde.setBounds(10, 61, 102, 25);
		add(lblDesde);

		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblHasta.setBounds(10, 90, 102, 25);
		add(lblHasta);

		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblBuscarPor.setBounds(718, 61, 79, 25);
		add(lblBuscarPor);

		cmbBuscarPor = new JComboBox(new String[] { "ID", "Placa", "Modelo", "Marca" });
		cmbBuscarPor.setBounds(796, 59, 194, 25);
		add(cmbBuscarPor);
		
	}// </editor-fold>//GEN-END:initComponents
	
	public void login() {
		setComponents();
		
	}
	
	public void mostrarOpcionesVehiculo(Json jsonV) {
		
		javax.swing.JDialog dialog = new javax.swing.JDialog(menuPrincipal,true);
		dialog.setSize(300,300);
		dialog.setLocationRelativeTo(jTableVehiculo);
		dialog.setResizable(false);
		dialog.setUndecorated(true);
		dialog.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, dialog.getWidth(), dialog.getHeight());
		panel.setLayout(null);
		panel.setBackground(Color.white);
		panel.setBorder(new LineBorder(info.getPrimaryColor(),2));
		dialog.getContentPane().add(panel);
		
		JButton  btnCloseDialog = new JButton(new ImageIcon(getClass().getResource("/imagenes/Salirn.png")));
		btnCloseDialog.setRolloverIcon(new ImageIcon(getClass().getResource("/imagenes/Salir.png")));
		btnCloseDialog.setBounds(dialog.getWidth()-32,2,30,40);
		btnCloseDialog.setBorder(null);
		btnCloseDialog.setContentAreaFilled(false);
		btnCloseDialog.addActionListener(v -> dialog.dispose());
		panel.add(btnCloseDialog);
		
		JLabel lbTitulo = new JLabel("Opciones de vehículo");
		lbTitulo.setBounds(10, 5, 280, 40);
		lbTitulo.setFont(new Font("Arial Black",1,18));
		panel.add(lbTitulo);
		
		JLabel lb1[] = {new JLabel("Vin:"), new JLabel("Placa:"),
				new JLabel("Marca:"),new JLabel("Modelo:")};
		JLabel lb2[] = {new JLabel(jsonV.get("vin").toString()), new JLabel(jsonV.get("placa").toString()),
				new JLabel(jsonV.get("marca").toString()),new JLabel(jsonV.get("modelo").toString())};
		
		int lbY = lbTitulo.getY()+lbTitulo.getHeight()+5;
		for (int i = 0; i < lb2.length; i++) {
			lb1[i].setBounds(5, lbY, 70, 18);
			lb2[i].setBounds(lb1[i].getWidth()+lb1[i].getX()+5, lbY, 175, 18);
			lb1[i].setFont(new Font("Arial Black",1,14));
			lb2[i].setFont(new Font("Arial",1,14));
			panel.add(lb1[i]);
			panel.add(lb2[i]);
			lbY += 20;
		}
		
		JButton btnOpciones[] = new JButton[3];
		String buttonText[] = {"Solicitar Revision","Modificar Vehículo","Inhabilitar Vehículo"};
		int btnOpcionesY = lbY+20;
		for (int i = 0; i < btnOpciones.length; i++) {
			btnOpciones[i] = new JButton(buttonText[i]);
			btnOpciones[i].setBounds(10, btnOpcionesY, 280, 50);
			btnOpciones[i].setBackground(info.getPrimaryColor());
			btnOpciones[i].setForeground(Color.white);
			btnOpciones[i].addActionListener(e -> {
				if(e.getSource() == btnOpciones[1]) {
					new NuevoVehiculo(menuPrincipal, this,jsonV).setVisible(true);
					dialog.dispose();
				}
			});
			btnOpcionesY += 46;
			panel.add(btnOpciones[i]);
		}
		
		dialog.setVisible(true);
	}

	public void setModelos() {
		Thread hilo = new Thread(() -> {
			try {

				modelos = Peticion.get(info.getApiPath() + "/api/modelo/", "");
				jTableModelo.setModel(new ModeloTabla(modelos, new String[] { "marca", "modelo", "tipo" },
						new String[] { "Marca", "Modelo", "Tipo" }));
				jTableModelo.setRowHeight(28);
				jTableModelo.setDefaultRenderer(Object.class, new ColorearFilasIntercaladas(info.getSecondColor(),info.getPrimaryColor(),Color.white));
			} catch (Exception e) {
				// TODO: handle exception
			}
		});
		hilo.start();
	}
	public void setVehiculos() {
		Thread hilo = new Thread(() -> {
			try {

				jsonVehiculos = Peticion.get(info.getApiPath() + "/api/vehiculo/", "");
				jTableVehiculo.setModel(new ModeloTabla(jsonVehiculos, new String[] { "vin", "placa", "marca", "modelo" },
						new String[] { "Vin", "Placa", "Marca", "Modelo" }));
				jTableVehiculo.setRowHeight(28);
				jTableVehiculo.setDefaultRenderer(Object.class, new ColorearFilasIntercaladas(info.getSecondColor(),info.getPrimaryColor(),Color.white));
			} catch (Exception e) {
				// TODO: handle exception
			}
		});
		hilo.start();
	}

	private void setComponents() {
		setModelos();
		setVehiculos();
		try {
			jTableModelo.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int selectedRow = jTableModelo.getSelectedRow();
	                if(modeloIndex>-1 && modeloIndex==selectedRow) {
	                	new DialogModelo(menuPrincipal, vehiculos,new Json(modelos.get(selectedRow))).setVisible(true);
	                	modeloIndex = -1;
	                }else {
	                	modeloIndex = selectedRow;
	                }
	            }
	        });
			jTableVehiculo.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int selectedRow = jTableVehiculo.getSelectedRow();
	                if(vehiculoIndex>-1 && vehiculoIndex==selectedRow) {
	                	//new DialogModelo(menuPrincipal, vehiculos,new Json(modelos.get(selectedRow))).setVisible(true);
	                	mostrarOpcionesVehiculo(new Json(jsonVehiculos.get(vehiculoIndex)));
	                	
	                	vehiculoIndex = -1;
	                }else {
	                	vehiculoIndex = selectedRow;
	                }
	            }
	        });
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	protected void redimensionar() {
		// TODO Auto-generated method stub

	}
	
}
