package Clases;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class PruebaSubirImagen extends JFrame {

	private JPanel contentPane;
	private JLabel lbImagen;
	private JButton btnSubir;
	private JButton btnAbrir;
	private ImageIcon imgSeleccionada;
	private JTextField txtDni;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaSubirImagen frame = new PruebaSubirImagen();
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
	public PruebaSubirImagen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		imgSeleccionada = null;

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnSubir = new JButton("Subir");
		btnSubir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Json res = Peticion.putImagen("http://localhost:8080/api/empleado/imagen/"+txtDni.getText(), imgSeleccionada);
				System.out.println( res.toJsonString());
			}
		});
		btnSubir.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnSubir.setBackground(new Color(255, 255, 255));
		btnSubir.setBounds(168, 320, 89, 30);
		contentPane.add(btnSubir);
		
		lbImagen = new JLabel();
		lbImagen.setBounds(65, 9, 300, 200);
		lbImagen.setBorder(new LineBorder(Color.black,2));
		contentPane.add(lbImagen);
		
		btnAbrir = new JButton("Abrir imagen");
		btnAbrir.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnAbrir.setBackground(new Color(128, 128, 255));
		btnAbrir.setBorder(new LineBorder(Color.black,1));
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		btnAbrir.setBounds(65, 212, 300, 30);
		contentPane.add(btnAbrir);
		
		txtDni = new JTextField();
		txtDni.setBounds(65, 279, 300, 30);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("DNI");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel.setBounds(65, 250, 300, 30);
		contentPane.add(lblNewLabel);
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
					if(imgAncho<imgAlto)return;
					int newHeight = (int) (((double) imgAlto / imgAncho) * lbImagen.getWidth());
					imgSeleccionada = img;
					lbImagen.setIcon(redimensionarImg(img, lbImagen.getWidth(), newHeight));
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Ocurrió un error");
			}
		}
    }
	public ImageIcon redimensionarImg(ImageIcon imgIcon, int ancho, int alto) {
        Image imgEscalada = imgIcon.getImage().getScaledInstance(ancho,
                alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgEscalada);
	}
}
