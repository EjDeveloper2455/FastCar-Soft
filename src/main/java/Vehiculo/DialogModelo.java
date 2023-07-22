package Vehiculo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.FileDialog;

import javax.swing.border.TitledBorder;

import org.json.simple.JSONObject;

import Clases.Json;
import Clases.DialogCargando;
import Clases.ModeloTabla;
import Clases.Parametros;
import Clases.Peticion;
import Principal.MenuPrincipal;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JProgressBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class DialogModelo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtModelo;
	private JButton btnSeleccionar;
	private Json jsonMarca, jsonTipo;
	private Parametros info;

	private MenuPrincipal menuPrincipal;
	private JComboBox cmbMarca;
	private JComboBox cmbTipo;
	private JLabel lbImagen;
	private ImageIcon imgSeleccionada;

	private JPanel panelImagenesAgregadas;
	private Vehiculos vehiculos;
	int panelX = 10;
	private JProgressBar barra;
	private JButton btnGuardar;
	private JPanel panelCargando;
	private boolean isEdit,isBlock,imgEdit;
	private Json data;
	private int id;

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public DialogModelo(MenuPrincipal menuPrincipal, Vehiculos vehiculos) {
		super(menuPrincipal,true);
		this.menuPrincipal = menuPrincipal;
		this.vehiculos = vehiculos;
		iniciarComponentes();
		isEdit = false;
		id = -1;
	}
	public DialogModelo(MenuPrincipal menuPrincipal, Vehiculos vehiculos,Json data) {
		super(menuPrincipal,true);
		this.menuPrincipal = menuPrincipal;
		this.vehiculos = vehiculos;
		
		iniciarComponentes();
		isEdit = true;
		try {
			this.data = data;
			id = Integer.parseInt(data.get("id").toString());
			txtModelo.setText(data.get("modelo").toString());
			
			ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/esperandoImagen.png"));
			int newWidth = (int) (((double) img.getIconWidth() / img.getIconHeight()) * lbImagen.getHeight());
			lbImagen.setIcon(info.redimensionarImg(img, newWidth, lbImagen.getHeight()) );
			obtenerImagen();
		}catch(Exception exp) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un problema al intentar cargar los datos","ERROR",0);
		}
	}
	
	public int buscarIndex(int id,Json json) {
		List<JSONObject> jsonList = json.getArrayList();
		int i=0;
		for (JSONObject jsonObject : jsonList) {
			if(id == Integer.parseInt(jsonObject.get("id").toString()))return i;
			i++;
		}
		return -1;
	}
	
	private void iniciarComponentes() {
		this.info = this.menuPrincipal.getInfo();
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(!isBlock)dispose();
                else JOptionPane.showMessageDialog(null, "No se puede cerrar mientras se esta guardando información","Advertencia",1);
            }
        };

        // Agregar el WindowListener al JFrame
       addWindowListener(windowListener);
		
		jsonMarca = null;
		jsonTipo = null;
		imgSeleccionada = null;
		
		isBlock = false;
		imgEdit = false;

		setSize(696, 418);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setBackground(info.getSecondColor());
		
		contentPanel.setBackground(info.getPrimaryColor());
		contentPanel.setBounds(0,0,getWidth(),getHeight());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		contentPanel.setVisible(true);


		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(new Color(33, 37, 41));
		panelDatos.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelDatos.setBounds(10, 10, 382, 292);
		contentPanel.add(panelDatos);
		panelDatos.setLayout(null);

		cmbMarca = new JComboBox(new String[] { "Esperando respuesta..." });
		cmbMarca.setBounds(10, 53, 355, 30);
		panelDatos.add(cmbMarca);

		JLabel lbMarca = new JLabel("Marca");
		lbMarca.setForeground(new Color(255, 255, 255));
		lbMarca.setFont(new Font("Arial Black", Font.BOLD, 14));
		lbMarca.setBounds(10, 22, 355, 30);
		panelDatos.add(lbMarca);

		txtModelo = new JTextField();
		txtModelo.setBounds(10, 132, 355, 30);
		panelDatos.add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lbModelo = new JLabel("Descripción del modelo");
		lbModelo.setForeground(new Color(255, 255, 255));
		lbModelo.setFont(new Font("Arial Black", Font.BOLD, 14));
		lbModelo.setBounds(10, 100, 355, 30);
		panelDatos.add(lbModelo);

		cmbTipo = new JComboBox(new String[] { "Esperando respuesta" });
		cmbTipo.setBounds(10, 219, 355, 30);
		panelDatos.add(cmbTipo);

		JLabel lbTipo = new JLabel("Tipo de vehículo");
		lbTipo.setForeground(new Color(255, 255, 255));
		lbTipo.setFont(new Font("Arial Black", Font.BOLD, 14));
		lbTipo.setBounds(10, 188, 355, 30);
		panelDatos.add(lbTipo);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(33, 37, 41));
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Agregar imagen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBounds(panelDatos.getX()+panelDatos.getWidth()+10, 10, 275, 292);
		contentPanel.add(panel);
		panel.setLayout(null);

		lbImagen = new JLabel();
		lbImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lbImagen.setBounds(12, 15, 250, 200);
		lbImagen.setBorder(new LineBorder(new Color(255, 255, 255), 2));

		panel.add(lbImagen);

		btnSeleccionar = new JButton("Seleccionar imagen",
				new ImageIcon(getClass().getResource("/imagenes/galeria.png")));
		btnSeleccionar.setBackground(new Color(255, 255, 255));
		btnSeleccionar.setFont(new Font("Arial", Font.BOLD, 12));
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		btnSeleccionar.setBounds(12, 226, 250, 50);
		panel.add(btnSeleccionar);

		obtenerMarcas();
		obtenerTipos();

		panelImagenesAgregadas = new JPanel();
		panelImagenesAgregadas.setForeground(new Color(255, 255, 255));
		panelImagenesAgregadas.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Imagenes agregadas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelImagenesAgregadas.setBackground(new Color(33, 37, 41));
		panelImagenesAgregadas.setBounds(10, 305, 964, 162);
		panelImagenesAgregadas.setLayout(null);
		//contentPanel.add(panelImagenesAgregadas);
		
		panelCargando = new JPanel();
		panelCargando.setBounds(0, 0, 687, 383);
		panelCargando.setBackground(new Color(241,242,243));
		panelCargando.setVisible(false);
		getContentPane().add(panelCargando);
		panelCargando.setLayout(null);
		
		JLabel lbImgCargando = new JLabel(new ImageIcon(getClass().getResource("/imagenes/cargando.gif")));
		lbImgCargando.setBounds(246, 52, 200, 200);
		panelCargando.add(lbImgCargando);
		
		JLabel lbTextoCargando = new JLabel("Guardando los datos del modelo...",SwingConstants.CENTER);
		lbTextoCargando.setFont(new Font("Arial Black", Font.BOLD, 12));
		lbTextoCargando.setBounds(203, 265, 286, 30);
		lbTextoCargando.setForeground(new Color(29,63,114));
		panelCargando.add(lbTextoCargando);
		
		btnGuardar = new JButton("GUARDAR",new ImageIcon(getClass().getResource("/imagenes/addCar.png")));
		btnGuardar.setBackground(new Color(255, 255, 255));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnGuardar.setBounds((getWidth()/2)-75, panelDatos.getY()+panelDatos.getHeight()+20, 150, 40);
		contentPanel.add(btnGuardar);
	}

	private void guardar() {
		
		if (!validar())
			return;
		Thread hilo = new Thread(() -> {
			try {
				contentPanel.setVisible(false);
				panelCargando.setVisible(true);
				isBlock = true;
				
				int modeloId = guardarModelo();
				if (modeloId >= 0) {
					guardarImagenes(modeloId);
				} else {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar guardar el modelo", "ERROR", 0);
				}
				
				
				vehiculos.setModelos();
				dispose();

			} catch (Exception e) {
				btnGuardar.setEnabled(true);

			}
		});
		hilo.start();
	}

	private int guardarModelo() {
		int idResponse = -1;
		try {
			Json data = new Json();
			data.put("marca", jsonMarca.get(cmbMarca.getSelectedIndex()).get("id").toString());
			data.put("modelo", txtModelo.getText());
			data.put("tipo", jsonTipo.get(cmbTipo.getSelectedIndex()).get("id").toString());
			
			if(!isEdit) {
				Json res = Peticion.post(info.getApiPath() + "/api/modelo/", data);
				idResponse =  Integer.parseInt(res.getIn("Data~insertId").toString());
			}else {
				Json res = Peticion.put(info.getApiPath() + "/api/modelo/"+id, data);
				idResponse = id;
			}
		} catch (Exception exp) {
			JOptionPane.showMessageDialog(null, exp);
		}

		return idResponse;
	}

	private void guardarImagenes(int id) {
			if(isEdit) {
				if(!imgEdit)return;
			}
			try {
				Json resPut = Peticion.putImagen(info.getApiPath() + "/api/modelo/imagen/"+id, imgSeleccionada);
			} catch (Exception exp) {
				JOptionPane.showMessageDialog(null, exp, "ERROR", 1);
			}
	}

	public boolean validar() {
		if (txtModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Este campo es obligatorio", "ERROR", 0);
			txtModelo.requestFocus();
			return false;
		} else if (jsonMarca == null) {
			JOptionPane.showMessageDialog(null, "Debe esperar una marca existente", "ERROR", 0);
			return false;
		} else if (jsonTipo == null) {
			JOptionPane.showMessageDialog(null, "Debe esperar un tipo de vehículo existente", "ERROR", 0);
			return false;
		}else if(imgSeleccionada == null && !isEdit) {
			JOptionPane.showMessageDialog(null, "Debe agregar una imagen al modelo", "ERROR", 0);
			return false;
		}
		return true;
	}

	private void obtenerMarcas() {
		Thread hilo = new Thread(() -> {
			try {
				jsonMarca = Peticion.get(info.getApiPath() + "/api/marca/", menuPrincipal.getInfo().getToken());
				List<JSONObject> listMarca = jsonMarca.getArrayList();
				cmbMarca.removeAllItems();
				for (JSONObject data : listMarca) {
					cmbMarca.addItem(data.get("nombre"));
				}
				
				int marcaIndex = buscarIndex(Integer.parseInt(data.get("idMarca").toString()),jsonMarca);
				if(marcaIndex>-1)cmbMarca.setSelectedIndex(marcaIndex);

			} catch (Exception e) {
				// TODO: handle exception
			}
		});
		hilo.start();
	}

	private void obtenerTipos() {
		Thread hilo = new Thread(() -> {
			try {

				jsonTipo = Peticion.get(info.getApiPath() + "/api/tipoVehiculo/", "");
				List<JSONObject> listTipo = jsonTipo.getArrayList();
				cmbTipo.removeAllItems();
				for (JSONObject data : listTipo) {
					cmbTipo.addItem(data.get("nombre"));
				}
				
				int tipoIndex = buscarIndex(Integer.parseInt(data.get("idTipo").toString()),jsonTipo);
				if(tipoIndex>-1)cmbTipo.setSelectedIndex(tipoIndex);
			} catch (Exception e) {
				// TODO: handle exception
			}
		});
		hilo.start();
	}
	
	private void obtenerImagen() {
		Thread hilo = new Thread(() -> {
			ImageIcon imgNotFound = new ImageIcon(getClass().getResource("/imagenes/imagenotfound.png"));
			int newWidth = (int) (((double) imgNotFound.getIconWidth() / imgNotFound.getIconHeight()) * lbImagen.getHeight());
			imgNotFound = info.redimensionarImg(imgNotFound, newWidth,lbImagen.getHeight());
			try {
				ImageIcon img = info.obtenerImagen(data.get("imagen").toString());
				
				
				if(img!=null) {
					int newHeight = (int) (((double) img.getIconHeight() / img.getIconWidth()) * lbImagen.getWidth());
					lbImagen.setIcon(info.redimensionarImg(img, lbImagen.getWidth(), newHeight));
				}else {
					lbImagen.setIcon(imgNotFound);
				}
			} catch (Exception e) {
				lbImagen.setIcon(imgNotFound);
			}
		});
		hilo.start();
	}

	public void openFile() {
		JDialog dia;
		FileDialog ventana = new FileDialog(this, "Seleccionar imagen", FileDialog.LOAD);
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
				lbImagen.setIcon(info.redimensionarImg(img, lbImagen.getWidth(), newHeight));
				if(isEdit) {
					imgEdit = true;
				}

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Ocurrió un error");
			}
		}
	}
}
