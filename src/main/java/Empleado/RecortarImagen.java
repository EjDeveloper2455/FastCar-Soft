package Empleado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.json.simple.JSONObject;

import Clases.Peticion;

public class RecortarImagen extends JDialog {

	private final JPanel contentPanel = new JPanel();


	private JLabel lbCuadro;
	private int x,y,ancho;
	private boolean recorte;
	public RecortarImagen(ImageIcon img,NuevoEmpleado nuevoEmpleado, JDialog dialog) {
		super(dialog,true);
		setSize(img.getIconWidth()+15, img.getIconHeight()+50);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		contentPanel.setBackground(Color.white);
		
		x = 0;
		y = 0;
		ancho = 300;
		recorte = false;
		
		lbCuadro = new JLabel();
		lbCuadro.setBounds(x, y, ancho+5, ancho+10);
		lbCuadro.setBorder(new LineBorder(Color.black,2));
		contentPanel.add(lbCuadro);
		
		JLabel lb = new JLabel(img);
		lb.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		contentPanel.add(lb);
		
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
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
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					if(!recorte)recorte = true;
					else {
						BufferedImage image = convertImage(img);
						ImageIcon imagenRecortada = new ImageIcon(image.getSubimage(x, y, ancho, ancho));
						int select = JOptionPane.showConfirmDialog(null, imagenRecortada,"¿Deseas realizar este recorte?",2);
						if(select == 0) {
							nuevoEmpleado.setImagen(imagenRecortada);
							dispose();
						}
					}
                } else if (SwingUtilities.isRightMouseButton(e)) {
                	if(recorte)recorte = false;
                }
				
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(!recorte) {
					
					if(e.getX()-(ancho/2)>=0 && e.getX()+(ancho/2)<img.getIconWidth()) {
						x = e.getX()-(ancho/2);
					}else {
						if(x<10) x = 0;
						else x = img.getIconWidth()-ancho;
					}
					if(e.getY()-(ancho/2)>=0 && e.getY()+(ancho/2)<img.getIconHeight()) {
						y = e.getY()-(ancho/2);
					}else {
						if(y < 10) y = 0;
						else y = img.getIconHeight()-ancho;
					}
					
					lbCuadro.setBounds(x, y, ancho, ancho);
				}
			}
		});
	}
	public BufferedImage convertImage(ImageIcon icon) {
        if (icon == null) {
            return null;
        }

        Image image = icon.getImage();
        BufferedImage bufferedImage = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return bufferedImage;
    }
	
}
