package Vehiculo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;

import Clases.ColorearFilasIntercaladas;
import Clases.Json;
import Clases.ModeloTabla;
import Clases.Validar;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogVehiculoExtra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDescripcion;
	private JTextField txtCosto;
	private JTable tabla;
	private Json datos;
	private NuevoVehiculo nuevoVehiculo;

	public DialogVehiculoExtra(Json datos,NuevoVehiculo nuevoVehiculo) {
		super(nuevoVehiculo,true);
		setBounds(100, 100, 600, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(33, 37, 41));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		this.datos = datos;
		this.nuevoVehiculo = nuevoVehiculo;
		
		contentPanel.setLayout(null);
		{
			txtDescripcion = new JTextField();
			txtDescripcion.setBounds(163, 27, 395, 25);
			contentPanel.add(txtDescripcion);
			txtDescripcion.addActionListener(e -> txtCosto.requestFocus());
			txtDescripcion.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Descripcion:");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
			lblNewLabel.setBounds(20, 25, 200, 25);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblCosto = new JLabel("Costo:");
			lblCosto.setForeground(Color.WHITE);
			lblCosto.setFont(new Font("Arial Black", Font.BOLD, 14));
			lblCosto.setBounds(20, 61, 72, 25);
			contentPanel.add(lblCosto);
		}
		{
			txtCosto = new JTextField();
			txtCosto.setColumns(10);
			txtCosto.setBounds(394, 63, 164, 25);
			txtCosto.addKeyListener(new Validar(txtCosto).monedas(5));
			txtCosto.addActionListener(e -> actualizarTabla());
			txtCosto.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(txtCosto);
		}
		{
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.addActionListener(e -> actualizarTabla());
			btnAgregar.setForeground(new Color(255, 255, 255));
			btnAgregar.setFont(new Font("Arial Black", Font.BOLD, 14));
			btnAgregar.setBounds(408, 114, 150, 30);
			btnAgregar.setBackground(new Color(33, 37, 41));
			contentPanel.add(btnAgregar);
		}
		{
			JButton btnLimpiar = new JButton("Limpiar");
			btnLimpiar.setForeground(new Color(255, 255, 255));
			btnLimpiar.setFont(new Font("Arial Black", Font.BOLD, 14));
			btnLimpiar.setBackground(new Color(33, 37, 41));
			btnLimpiar.setBounds(20, 114, 150, 30);
			contentPanel.add(btnLimpiar);
		}
		
		
		
		tabla = new JTable();
		tabla.setModel(new ModeloTabla(datos, new String[] {"descripcion","costo","estado"},new String[] {"Descripción","Costo","Estado"}));
		tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabla.setFont(new Font("Arial", Font.BOLD, 12));
		tabla.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 14));
		
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(20, 163, 538, 237);
		contentPanel.add(scrollPane);
	}
	
	@SuppressWarnings("unchecked")
	private void actualizarTabla() {
		double costo = 0.00;
		if(txtDescripcion.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Este campo es obligatorio","ERROR",0);
			txtDescripcion.requestFocus();
			return;
		}
		try{
			costo = Double.parseDouble(txtCosto.getText());
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al intentar agregar el costo\nVerifica si los caracteres ingresados son correctos","ERROR",0);
			txtCosto.requestFocus();
			return;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", -1);
		jsonObject.put("descripcion", txtDescripcion.getText());
		jsonObject.put("costo", costo);
		jsonObject.put("estado", "nuevo");
		datos.add(jsonObject);
		nuevoVehiculo.setJsonExtra(datos);
		tabla.setModel(new ModeloTabla(datos, new String[] {"descripcion","costo","estado"},new String[] {"Descripción","Costo","Estado"}));
		tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabla.setRowHeight(28);
		tabla.setDefaultRenderer(Object.class, new ColorearFilasIntercaladas(nuevoVehiculo.getMenuPrincipal().getInfo().getSecondColor(),nuevoVehiculo.getMenuPrincipal().getInfo().getPrimaryColor(),Color.white));
		txtDescripcion.setText(null);
		txtCosto.setText(null);
	}
}
