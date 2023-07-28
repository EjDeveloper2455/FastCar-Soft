package Solicitud;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

public class DatosCliente extends JDialog {

	private JPanel contentPane;
	private JTextField txtXxxxxxxxxxx;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textEmail;
	private JTextField textTarjeta;
	private JTextField textDNI;

	JDialog dialog;
	public DatosCliente(JDialog dialog) {
		super(dialog,true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(509, 455);
		setLocationRelativeTo(null);
		this.dialog = dialog;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(208, 208, 208));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDniCliente = new JLabel("DNI Cliente");
		lblDniCliente.setFont(new Font("Arial", Font.BOLD, 18));
		lblDniCliente.setBounds(23, 11, 164, 20);
		contentPane.add(lblDniCliente);
		
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setFont(new Font("Arial", Font.BOLD, 18));
		lblDireccion.setBounds(23, 63, 164, 20);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono de Contacto");
		lblTelefono.setFont(new Font("Arial", Font.BOLD, 18));
		lblTelefono.setBounds(23, 149, 203, 20);
		contentPane.add(lblTelefono);
		
		JLabel lblEmail = new JLabel("Email de Contacto");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 18));
		lblEmail.setBounds(23, 205, 203, 20);
		contentPane.add(lblEmail);
		
		textDireccion = new JTextField();
		textDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		textDireccion.setBounds(23, 83, 203, 55);
		contentPane.add(textDireccion);
		textDireccion.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(23, 174, 203, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(23, 230, 203, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblPago = new JLabel("Tipo de Pago");
		lblPago.setFont(new Font("Arial", Font.BOLD, 18));
		lblPago.setBounds(23, 261, 164, 20);
		contentPane.add(lblPago);
		
		JComboBox comboBoxPago = new JComboBox();
		comboBoxPago.setBounds(23, 284, 203, 22);
		contentPane.add(comboBoxPago);
		
		JLabel lblTarjeta = new JLabel("Numero de Tarjeto");
		lblTarjeta.setFont(new Font("Arial", Font.BOLD, 18));
		lblTarjeta.setBounds(23, 317, 203, 20);
		contentPane.add(lblTarjeta);
		
		textTarjeta = new JTextField();
		textTarjeta.setColumns(10);
		textTarjeta.setBounds(23, 338, 203, 20);
		contentPane.add(textTarjeta);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setBackground(new Color(0, 0, 0));
		btnGuardar.setFont(new Font("Arial", Font.BOLD, 22));
		btnGuardar.setBounds(179, 377, 151, 23);
		contentPane.add(btnGuardar);
		
		JPanel panelRecibo = new JPanel();
		panelRecibo.setBackground(new Color(255, 255, 255));
		panelRecibo.setBounds(288, 32, 197, 326);
		contentPane.add(panelRecibo);
		
		JLabel lblMontoAPagar = new JLabel("Monto a Pagar");
		lblMontoAPagar.setFont(new Font("Arial", Font.BOLD, 18));
		lblMontoAPagar.setBounds(288, 11, 164, 20);
		contentPane.add(lblMontoAPagar);
		
		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(23, 32, 203, 20);
		contentPane.add(textDNI);
	}
}
