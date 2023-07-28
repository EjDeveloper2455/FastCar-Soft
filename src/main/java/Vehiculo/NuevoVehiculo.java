
package Vehiculo;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import Clases.Json;
import Clases.Peticion;
import Clases.Validar;
import Principal.MenuPrincipal;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NuevoVehiculo extends javax.swing.JDialog {

	private Json jsonMarca, jsonModelo, jsonModelSelect, jsonColor, jsonData, jsonExtra;
	private JPanel panelImagenesAgregadas;
	private MenuPrincipal menuPrincipal;
	private List<ImageIcon> listImagenes;
	private List<PanelImagenModelo> listPanelImagen;
	private ImageIcon imgSeleccionada;
	private boolean isEdit;
	private double costoInicial, costoTotal, costosExtras;
	private JSpinner spnAnio;
	private JSpinner spnKm;
	private Vehiculos vehiculos;
	private JPanel panel;
	private JPanel panelCargando;
	private JProgressBar barraCargando;

	/**
	 * @wbp.parser.constructor
	 */
	public NuevoVehiculo(MenuPrincipal menuPrincipal, Vehiculos vehiculos) {
		super(menuPrincipal, true);
		this.menuPrincipal = menuPrincipal;
		this.vehiculos = vehiculos;
		jsonData = null;
		isEdit = false;
		jsonExtra = new Json();
		initComponents();

	}

	public NuevoVehiculo(MenuPrincipal menuPrincipal, Vehiculos vehiculos, Json jsonData) {
		super(menuPrincipal,true);
		this.menuPrincipal = menuPrincipal;
		this.jsonData = jsonData;
		this.vehiculos = vehiculos;
		isEdit = true;
		costoInicial = 0.00;
		costoTotal = 0.00;
		initComponents();
		setComponents();
	}

	@SuppressWarnings("unchecked")
	private void setComponents() {
		String vin = jsonData.get("vin").toString();
		txtvin.setText(vin);
		txtvin.setEditable(false);
		txtplaca.setText(jsonData.get("placa").toString());
		txtCostoInicial.setText(jsonData.get("costo").toString());
		try {
			Json extraTmp = Peticion.get(menuPrincipal.getInfo().getApiPath() + "/api/vehiculo/extra/"+vin,
					menuPrincipal.getInfo().getToken());
			JOptionPane.showMessageDialog(null, extraTmp.toArrayString());
			double sumCosto = 0.00;
			for (JSONObject jsonObject : extraTmp.getArrayList()) {
				try {
					JSONObject json = new JSONObject();
					json.put("id", jsonObject.get("id"));
					json.put("descripcion", jsonObject.get("descripcion"));
					double costo = Double.parseDouble(jsonObject.get("costo").toString());
					sumCosto += costo;
					json.put("costo", costo);
					json.put("estado", "existente sin cambio");
					jsonExtra.add(json);
				}catch (Exception exp) {
					
				}
				
			}
			txtCostoExtra.setText(sumCosto+"");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		actualizarCostos();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		setBounds(0, 0, 1000, 675);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 1000, 675);
		panel.setBackground(new java.awt.Color(255, 255, 255));
		panel.setLayout(null);
		getContentPane().add(panel);

		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 255, 255)));
		panelDatos.setBounds(10, 61, 585, 287);
		panelDatos.setBackground(new Color(33, 37, 41));
		panel.add(panelDatos);
		panelDatos.setLayout(null);

		jPanel1 = new javax.swing.JPanel();
		jPanel1.setBounds(0, 0, 1000, 50);
		jLabel8 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jPanel2.setBounds(0, 575, 1000, 60);
		jLabel2 = new javax.swing.JLabel();
		jLabel2.setForeground(new Color(255, 255, 255));
		jLabel2.setBounds(296, 38, 62, 25);
		txtplaca = new javax.swing.JTextField();
		txtplaca.setBounds(295, 60, 270, 25);
		jLabel3 = new javax.swing.JLabel();
		jLabel3.setForeground(new Color(255, 255, 255));
		jLabel3.setBounds(20, 148, 200, 16);
		txtvin = new javax.swing.JTextField();
		txtvin.setBounds(20, 60, 270, 25);
		jLabel4 = new javax.swing.JLabel();
		jLabel4.setForeground(new Color(255, 255, 255));
		jLabel4.setBounds(390, 96, 170, 16);
		ComboBoxMarca = new javax.swing.JComboBox<>();
		ComboBoxMarca.setBounds(20, 112, 175, 25);
		ComboBoxModelo = new javax.swing.JComboBox<>();
		ComboBoxModelo.setBounds(205, 112, 175, 25);
		ComboBoxColor = new javax.swing.JComboBox<>();
		ComboBoxColor.setBounds(390, 112, 175, 25);
		jLabel5 = new javax.swing.JLabel();
		jLabel5.setForeground(new Color(255, 255, 255));
		jLabel5.setBounds(20, 38, 62, 25);
		jLabel6 = new javax.swing.JLabel();
		jLabel6.setForeground(new Color(255, 255, 255));
		jLabel6.setBounds(20, 96, 170, 16);
		jLabel7 = new javax.swing.JLabel();
		jLabel7.setForeground(new Color(255, 255, 255));
		jLabel7.setBounds(205, 99, 132, 10);

		txtTipoVehiculo = new javax.swing.JTextField();
		txtTipoVehiculo.setEditable(false);
		txtTipoVehiculo.setBounds(20, 166, 175, 25);

		jPanel1.setBackground(new java.awt.Color(86, 89, 92));

		jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
		jLabel8.setForeground(new java.awt.Color(255, 255, 255));
		jLabel8.setText("Agregando Nuevo Vehículo");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout
						.createSequentialGroup().addGap(377, 377, 377).addComponent(jLabel8,
								javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(394, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addContainerGap()));

		panel.add(jPanel1);

		jPanel2.setBackground(new java.awt.Color(33, 37, 41));
		btnguardar1 = new javax.swing.JButton();
		btnguardar1.addActionListener(v -> guardarTodo());
		btnguardar1.setBounds(542, 10, 150, 40);
		btnguardar1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		btnguardar1.setForeground(new Color(255, 255, 255));
		btnguardar1.setText("Guardar");
		btnguardar1.setBackground(new java.awt.Color(33, 37, 41));
		btnguardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		panel.add(jPanel2);
		jPanel2.setLayout(null);
		jPanel2.add(btnguardar1);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(v -> JOptionPane.showMessageDialog(this, jsonExtra.toArrayString()));
		btnLimpiar.setBounds(312, 10, 147, 40);
		jPanel2.add(btnLimpiar);
		btnLimpiar.setBackground(new java.awt.Color(33, 37, 41));
		btnLimpiar.setForeground(Color.white);

		jLabel2.setFont(new Font("Arial", Font.BOLD, 14)); // NOI18N
		jLabel2.setText("Placa:");
		panelDatos.add(jLabel2);

		txtplaca.setBorder(new LineBorder(Color.black, 1));
		txtplaca.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		panelDatos.add(txtplaca);

		jLabel3.setFont(new Font("Arial", Font.BOLD, 14)); // NOI18N
		jLabel3.setText("Tipo de vehiculo:");
		panelDatos.add(jLabel3);

		txtvin.setBorder(new LineBorder(Color.black, 1));
		txtvin.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		panelDatos.add(txtvin);

		jLabel4.setFont(new Font("Arial", Font.BOLD, 14)); // NOI18N
		jLabel4.setText("Color:");
		panelDatos.add(jLabel4);

		ComboBoxMarca.addActionListener(v -> obtenerModelo());
		ComboBoxMarca.setBorder(new LineBorder(Color.black, 1));
		ComboBoxMarca.addItem("Esperando respuesta del...");
		panelDatos.add(ComboBoxMarca);

		ComboBoxModelo.setBorder(new LineBorder(Color.black, 1));
		ComboBoxModelo.addActionListener(e -> obtenerTipoVehiculo());
		panelDatos.add(ComboBoxModelo);

		ComboBoxColor.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		ComboBoxColor.setBorder(new LineBorder(Color.black, 1));
		panelDatos.add(ComboBoxColor);

		jLabel5.setFont(new Font("Arial", Font.BOLD, 14)); // NOI18N
		jLabel5.setText("Vin:");
		panelDatos.add(jLabel5);

		jLabel6.setFont(new Font("Arial", Font.BOLD, 14)); // NOI18N
		jLabel6.setText("Marca:");
		panelDatos.add(jLabel6);

		jLabel7.setFont(new Font("Arial", Font.BOLD, 14)); // NOI18N
		jLabel7.setText("Modelo:");
		panelDatos.add(jLabel7);

		txtTipoVehiculo.setBorder(new LineBorder(Color.black, 1));
		txtTipoVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
		panelDatos.add(txtTipoVehiculo);

		panelImagenesAgregadas = new JPanel();
		panelImagenesAgregadas.setForeground(new Color(255, 255, 255));
		panelImagenesAgregadas.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Imagenes agregadas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelImagenesAgregadas.setBackground(new Color(33, 37, 41));
		panelImagenesAgregadas.setBounds(10, 402, 964, 162);
		panelImagenesAgregadas.setLayout(null);
		panel.add(panelImagenesAgregadas);

		JLabel lblCostoDeRenta = new JLabel();
		lblCostoDeRenta.setText("Costo de renta inicial:");
		lblCostoDeRenta.setForeground(Color.WHITE);
		lblCostoDeRenta.setFont(new Font("Arial", Font.BOLD, 12));
		lblCostoDeRenta.setBounds(20, 206, 170, 16);
		panelDatos.add(lblCostoDeRenta);

		txtCostoInicial = new JTextField();
		txtCostoInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCostoInicial.addKeyListener(new Validar(txtCostoInicial).monedas(7));

		txtCostoInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				actualizarCostos();
			}
		});
		txtCostoInicial.addActionListener(v -> actualizarCostos());
		txtCostoInicial.setBounds(20, 223, 130, 25);
		panelDatos.add(txtCostoInicial);
		txtCostoInicial.setColumns(10);

		JButton btnExtra = new JButton("Agregar extra");
		btnExtra.addActionListener(e -> new DialogVehiculoExtra(jsonExtra, this).setVisible(true));
		btnExtra.setBounds(305, 223, 114, 25);
		btnExtra.setBackground(new java.awt.Color(33, 37, 41));
		btnExtra.setForeground(Color.white);
		panelDatos.add(btnExtra);

		JLabel lblCostoDeRenta_2 = new JLabel();
		lblCostoDeRenta_2.setText("Costo de renta total:");
		lblCostoDeRenta_2.setForeground(Color.WHITE);
		lblCostoDeRenta_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblCostoDeRenta_2.setBounds(433, 209, 170, 16);
		panelDatos.add(lblCostoDeRenta_2);

		txtCostoTotal = new JTextField();
		txtCostoTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCostoTotal.setEditable(false);
		txtCostoTotal.setColumns(10);
		txtCostoTotal.setBounds(435, 223, 130, 25);
		panelDatos.add(txtCostoTotal);

		JLabel lblAo = new JLabel();
		lblAo.setText("Año:");
		lblAo.setForeground(Color.WHITE);
		lblAo.setFont(new Font("Arial", Font.BOLD, 14));
		lblAo.setBounds(205, 148, 132, 16);
		panelDatos.add(lblAo);

		spnAnio = new JSpinner();
		spnAnio.setModel(new SpinnerNumberModel(2010, 2010, 2023, 1));
		spnAnio.setBorder(new LineBorder(Color.black, 1));
		spnAnio.setBounds(205, 166, 175, 25);
		panelDatos.add(spnAnio);

		txtCostoExtra = new JTextField();
		txtCostoExtra.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCostoExtra.setEditable(false);
		txtCostoExtra.setColumns(10);
		txtCostoExtra.setBounds(174, 223, 130, 25);
		panelDatos.add(txtCostoExtra);

		JLabel lblCostosExtras = new JLabel();
		lblCostosExtras.setText("Costos extras:");
		lblCostosExtras.setForeground(Color.WHITE);
		lblCostosExtras.setFont(new Font("Arial", Font.BOLD, 14));
		lblCostosExtras.setBounds(174, 205, 170, 16);
		panelDatos.add(lblCostosExtras);

		panelImagen = new JPanel();
		panelImagen.setBorder(new TitledBorder(null, "Agregar imagen", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 255, 255)));
		panelImagen.setBounds(644, 61, 330, 318);
		panelImagen.setBackground(new Color(33, 37, 41));
		panel.add(panelImagen);
		panelImagen.setLayout(null);

		lbImagen = new javax.swing.JLabel();
		lbImagen.setBounds(16, 20, 300, 220);
		panelImagen.add(lbImagen);

		lbImagen.setToolTipText("");
		lbImagen.setBorder(new LineBorder(Color.white, 1));

		btnsubirimagen = new JButton("Subir imagen", new ImageIcon(getClass().getResource("/imagenes/galeria.png")));
		btnsubirimagen.setBounds(16, 247, 140, 60);
		panelImagen.add(btnsubirimagen);
		btnsubirimagen.addActionListener(e -> openFile());
		btnsubirimagen.setBackground(new java.awt.Color(33, 37, 41));
		btnsubirimagen.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		btnsubirimagen.setForeground(new java.awt.Color(255, 255, 255));
		btnsubirimagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnsubirimagen.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnsubirimagen.setHorizontalTextPosition(SwingConstants.CENTER);

		btnagregarimagen = new JButton("Agregar imagen", new ImageIcon(getClass().getResource("/imagenes/agg.png")));
		btnagregarimagen.addActionListener(v -> agregarImagen());
		btnagregarimagen.setBounds(177, 247, 140, 60);
		panelImagen.add(btnagregarimagen);
		btnagregarimagen.setBackground(new java.awt.Color(33, 37, 41));
		btnagregarimagen.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		btnagregarimagen.setForeground(new java.awt.Color(255, 255, 255));
		btnagregarimagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnagregarimagen.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnagregarimagen.setHorizontalTextPosition(SwingConstants.CENTER);

		jsonColor = new Json();
		jsonModelo = new Json();
		jsonMarca = new Json();
		jsonExtra = new Json();
		listImagenes = new ArrayList<>();
		imgSeleccionada = null;
		listPanelImagen = new ArrayList<>();
		costosExtras = 0.00;
		costoTotal = 0.00;

		txtCostoExtra.setText(NumberFormat.getCurrencyInstance().format(costosExtras));
		txtCostoTotal.setText(NumberFormat.getCurrencyInstance().format(costoTotal));

		spnKm = new JSpinner();
		spnKm.setBorder(new LineBorder(Color.black, 1));
		spnKm.setBounds(390, 166, 175, 25);
		panelDatos.add(spnKm);

		JLabel lblKilimetraje = new JLabel();
		lblKilimetraje.setText("Kilimetraje:");
		lblKilimetraje.setForeground(Color.WHITE);
		lblKilimetraje.setFont(new Font("Arial", Font.BOLD, 14));
		lblKilimetraje.setBounds(390, 150, 132, 16);
		panelDatos.add(lblKilimetraje);

		panelCargando = new JPanel();
		panelCargando.setBounds(0, 0, getWidth(), getHeight());
		panelCargando.setBackground(new Color(241, 242, 243));
		panelCargando.setVisible(false);
		getContentPane().add(panelCargando);
		panelCargando.setLayout(null);

		JLabel lbImgCargando = new JLabel(new ImageIcon(getClass().getResource("/imagenes/cargando.gif")));
		lbImgCargando.setBounds(400, 150, 200, 200);
		panelCargando.add(lbImgCargando);

		JLabel lbTextoCargando = new JLabel("Guardando los datos del vehículo..", SwingConstants.CENTER);
		lbTextoCargando.setFont(new Font("Arial Black", Font.BOLD, 12));
		lbTextoCargando.setBounds(361, 401, 286, 30);
		lbTextoCargando.setForeground(new Color(29, 63, 114));
		panelCargando.add(lbTextoCargando);

		barraCargando = new JProgressBar();
		barraCargando.setBounds(100, 551, 800, 40);
		panelCargando.add(barraCargando);

		Thread hilo = new Thread(() -> {

			try {
				jsonModelo = Peticion.get(menuPrincipal.getInfo().getApiPath() + "/api/modelo/",
						menuPrincipal.getInfo().getToken());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			obtenerMarcas();
			obtenerColor();
		});
		hilo.start();
	}// </editor-fold>//GEN-END:initComponents

	private void guardarTodo() {
		Thread hilo = new Thread(() -> {
			Json datosPost = new Json();
			datosPost.put("vin", txtvin.getText());
			datosPost.put("sucursal", menuPrincipal.getInfo().getSucursal() + "");
			datosPost.put("placa", txtplaca.getText().toUpperCase());
			datosPost.put("modelo", jsonModelSelect.get(ComboBoxModelo.getSelectedIndex()).get("id").toString());
			datosPost.put("costo", costoInicial + "");
			datosPost.put("color", jsonColor.get(ComboBoxColor.getSelectedIndex()).get("id").toString());
			datosPost.put("anio", spnAnio.getValue().toString());
			datosPost.put("kilometraje", spnKm.getValue().toString());

			try {
				panel.setVisible(false);
				panelCargando.setVisible(true);
				Json jsonResult = Peticion.post(menuPrincipal.getInfo().getApiPath() + "/api/vehiculo/", datosPost);
				barraCargando.setValue(5);
				if (jsonResult.get("mensaje").toString().equals("Se ha guardado exitosamente")) {
					for (JSONObject data : jsonExtra.getArrayList()) {
						Json datosPostExtra = new Json();
						datosPostExtra.put("vin", datosPost.get("vin").toString());
						datosPostExtra.put("descripcion", data.get("descripcion").toString());
						datosPostExtra.put("costo", data.get("costo").toString());
						Json jsonResultExtra = Peticion
								.post(menuPrincipal.getInfo().getApiPath() + "/api/vehiculo/extra/", datosPostExtra);
						barraCargando.setValue(barraCargando.getValue() + 5);
					}
					barraCargando.setValue(20);
					for (ImageIcon dataImg : listImagenes) {
						Json datosPostExtra = new Json();
						datosPostExtra.put("vin", datosPost.get("vin").toString());
						Json jsonResultImg = Peticion
								.post(menuPrincipal.getInfo().getApiPath() + "/api/vehiculo/imagen/", datosPostExtra);
						String idResponse = jsonResultImg.getIn("Data~insertId").toString();
						Json jsonResultImgUpdate = Peticion.putImagen(
								menuPrincipal.getInfo().getApiPath() + "/api/vehiculo/imagen/" + idResponse, dataImg);
						barraCargando.setValue(barraCargando.getValue() + 10);
					}
					barraCargando.setValue(100);
					vehiculos.setVehiculos();
					dispose();
				}
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		hilo.start();

	}

	protected void actualizarCostos() {
		try {
			costoInicial = Double.parseDouble(txtCostoInicial.getText());
			costoTotal = costoInicial + costosExtras;
			txtCostoTotal.setText(NumberFormat.getCurrencyInstance().format(costoTotal));
		} catch (Exception exp) {
			// TODO: handle exception
		}
	}

	public void obtenerMarcas() {

		try {
			ComboBoxMarca.removeAllItems();
			jsonMarca = Peticion.get(menuPrincipal.getInfo().getApiPath() + "/api/marca/",
					menuPrincipal.getInfo().getToken());

			for (JSONObject jsonObject : jsonMarca.getArrayList()) {
				ComboBoxMarca.addItem(jsonObject.get("nombre").toString());
			}
			obtenerModelo();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void obtenerColor() {

		try {
			ComboBoxColor.removeAllItems();
			jsonColor = Peticion.get(menuPrincipal.getInfo().getApiPath() + "/api/color/",
					menuPrincipal.getInfo().getToken());

			for (JSONObject jsonObject : jsonColor.getArrayList()) {
				ComboBoxColor.addItem(jsonObject.get("nombre").toString());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void obtenerModelo() {
		try {
			if (jsonMarca.longitud() > 0 && jsonModelo.longitud() > 0) {
				int cmbMarcaIndex = ComboBoxMarca.getSelectedIndex();
				String marcaId = jsonMarca.get(cmbMarcaIndex).get("id").toString();
				jsonModelSelect = new Json();
				ComboBoxModelo.removeAllItems();
				for (JSONObject jsonModel : jsonModelo.getArrayList()) {
					if (jsonModel.get("idMarca").toString().equals(marcaId)) {
						ComboBoxModelo.addItem(jsonModel.get("modelo").toString());
						jsonModelSelect.add(jsonModel);
					}
				}
				if (jsonModelSelect.longitud() > 0)
					ComboBoxModelo.setSelectedIndex(0);
			}
		} catch (Exception e) {

		}
	}

	public void obtenerTipoVehiculo() {
		try {
			if (jsonModelSelect.longitud() > 0) {
				JSONObject json = jsonModelSelect.get(ComboBoxModelo.getSelectedIndex());
				txtTipoVehiculo.setText(json.get("tipo").toString());

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void agregarImagen() {
		listImagenes.add(imgSeleccionada);
		listPanelImagen.add(new PanelImagenModelo(imgSeleccionada, this));

		reacomodarPanel();

		imgSeleccionada = null;
		lbImagen.setIcon(imgSeleccionada);
	}

	public void eliminarImagen(int position) {
		listImagenes.remove(position);
		listPanelImagen.remove(position);
		reacomodarPanel();
	}

	private void reacomodarPanel() {
		panelImagenesAgregadas.removeAll();
		panelImagenesAgregadas.revalidate();
		panelImagenesAgregadas.repaint();

		int panelX = (panelImagenesAgregadas.getWidth() / 2) - ((listPanelImagen.size() * 110) / 2);
		if (listPanelImagen.size() > 3)
			panelX -= 20;
		int index = 0;
		for (PanelImagenModelo panel : listPanelImagen) {
			panel.setBounds(panelX, 25, 110, 120);
			panel.setPosition(index);
			panelImagenesAgregadas.add(panel);
			panelX += 115;
			index++;
		}
	}

	public void setJsonExtra(Json extra) {
		this.jsonExtra = extra;
		costosExtras = 0.0;
		for (JSONObject json : jsonExtra.getArrayList()) {
			costosExtras += Double.parseDouble(json.get("costo").toString());
		}
		txtCostoExtra.setText(NumberFormat.getCurrencyInstance().format(costosExtras));
		costoTotal = costoInicial + costosExtras;
		txtCostoTotal.setText(NumberFormat.getCurrencyInstance().format(costoTotal));
	}

	public void openFile() {
		if (listImagenes.size() >= 8) {
			JOptionPane.showMessageDialog(null, "Solo puedes agregar 5 imagenes", "Advertencia", 1);
			return;
		}
		JDialog dia;
		FileDialog ventana = new FileDialog(menuPrincipal, "Seleccionar imagen", FileDialog.LOAD);
		ventana.show();
		if (ventana.getDirectory() != null) {
			String fl = ventana.getDirectory() + ventana.getFile();
			try {

				ImageIcon img = new ImageIcon(fl);
				int imgAncho = img.getIconWidth();
				int imgAlto = img.getIconHeight();
				if (imgAncho < imgAlto)
					return;
				int newHeight = (int) (((double) imgAlto / imgAncho) * lbImagen.getWidth());
				imgSeleccionada = img;
				lbImagen.setIcon(menuPrincipal.getInfo().redimensionarImg(img, lbImagen.getWidth(), newHeight));
				if (isEdit) {
					// imgEdit = true;
				}

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Ocurrió un error");
			}
		}
	}

	public MenuPrincipal getMenuPrincipal() {
		return this.menuPrincipal;
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JComboBox<String> ComboBoxColor;
	private javax.swing.JComboBox<String> ComboBoxMarca;
	private javax.swing.JComboBox<String> ComboBoxModelo;
	private javax.swing.JButton btnagregarimagen;
	private javax.swing.JButton btnguardar1;
	private javax.swing.JButton btnsubirimagen;
	private javax.swing.JLabel lbImagen;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JTextField txtTipoVehiculo;
	private javax.swing.JTextField txtplaca;
	private javax.swing.JTextField txtvin;
	private JPanel panelImagen;
	private JTextField txtCostoInicial;
	private JTextField txtCostoTotal;
	private JButton btnLimpiar;
	private JTextField txtCostoExtra;
}
