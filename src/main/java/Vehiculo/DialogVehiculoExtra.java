package Vehiculo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;

import Clases.Json;
import Clases.ModeloTabla;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogVehiculoExtra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDescripcion;
	private JTextField txtCosto;
	private JTable tabla;
	private Json datos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogVehiculoExtra dialog = new DialogVehiculoExtra(new Json());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogVehiculoExtra(Json datos) {
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(33, 37, 41));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		this.datos = datos;
		
		contentPanel.setLayout(null);
		{
			txtDescripcion = new JTextField();
			txtDescripcion.setBounds(20, 50, 395, 25);
			contentPanel.add(txtDescripcion);
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
			lblCosto.setBounds(20, 86, 72, 25);
			contentPanel.add(lblCosto);
		}
		{
			txtCosto = new JTextField();
			txtCosto.setColumns(10);
			txtCosto.setBounds(95, 86, 320, 25);
			contentPanel.add(txtCosto);
		}
		{
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					datos.put("descripcion", txtDescripcion.getText());
					datos.put("costo", txtCosto.getText());
					tabla.setModel(new ModeloTabla(datos, new String[] {"descripcion","costo"},new String[] {"Descripción","Costo"}));
				}
			});
			btnAgregar.setForeground(new Color(255, 255, 255));
			btnAgregar.setFont(new Font("Arial Black", Font.BOLD, 14));
			btnAgregar.setBounds(265, 122, 150, 30);
			btnAgregar.setBackground(new Color(33, 37, 41));
			contentPanel.add(btnAgregar);
		}
		{
			JButton btnLimpiar = new JButton("Limpiar");
			btnLimpiar.setForeground(new Color(255, 255, 255));
			btnLimpiar.setFont(new Font("Arial Black", Font.BOLD, 14));
			btnLimpiar.setBackground(new Color(33, 37, 41));
			btnLimpiar.setBounds(20, 122, 150, 30);
			contentPanel.add(btnLimpiar);
		}
		
		
		
		tabla = new JTable();
		tabla.setModel(new ModeloTabla(datos, new String[] {"descripcion","costo"},new String[] {"Descripción","Costo"}));
		tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabla.setFont(new Font("Arial", Font.BOLD, 12));
		tabla.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 14));
		
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(20, 178, 395, 189);
		contentPanel.add(scrollPane);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
