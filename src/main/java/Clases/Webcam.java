package Clases;
import org.bytedeco.javacv.*;

import Empleado.NuevoEmpleado;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Webcam extends JDialog{
	private Frame frame;
	private OpenCVFrameGrabber grabber;
	private boolean grabando;
	private NuevoEmpleado nuevoEmpledo;
	private JDialog dialog;
    public Webcam(NuevoEmpleado nuevoEmpledo, JDialog dialog) {
        super(dialog,true);
        this.nuevoEmpledo = nuevoEmpledo;
        this.dialog = dialog;
    	this.setSize(655,590);
    	this.setLocationRelativeTo(null);
    	this.setResizable(false);
    	this.frame = null;
    	
    	this.grabando = true;
    	
    	JPanel panel = new JPanel();
    	panel.setBounds(-2, 0, 640, 550);
    	panel.setBackground(new Color(33,37,41));
    	this.getContentPane().add(panel);
    	panel.setLayout(null);
    	
    	//JLabel lbCuadro = new JLabel(new ImageIcon(getClass().getResource("/imagenes/abriendo-camara.png")));
    	JLabel lbCuadro = new JLabel();
    	lbCuadro.setBounds(80, 0, 480, 480);
    	lbCuadro.setBorder(new LineBorder(Color.black,2));
    	panel.add(lbCuadro);
    	
    	JLabel lbMostrar = new JLabel();
    	lbMostrar.setBounds(0, 0, 640, 480);
    	panel.add(lbMostrar);
    	
    	JButton btnTomar = new JButton(new ImageIcon(getClass().getResource("/imagenes/tomar_foto.png")));
    	btnTomar.setBounds(295, lbMostrar.getHeight()+lbMostrar.getY()+10, 50, 50);
    	btnTomar.setBackground(Color.white);
    	btnTomar.setContentAreaFilled(false);
    	btnTomar.setBorder(null);
    	btnTomar.setRolloverIcon(new ImageIcon(getClass().getResource("/imagenes/tomar_fotoN.png")));
    	panel.add(btnTomar);
    	btnTomar.addActionListener(v ->{
    		
            try {
            	// Guardar el fotograma como una imagen
                String nombreArchivo = "foto_webcam.jpg";
                String rutaArchivo = "./" + nombreArchivo;
                Java2DFrameConverter converter = new Java2DFrameConverter();
                java.awt.image.BufferedImage bufferedImage = converter.convert(frame);
                ImageIcon img = new ImageIcon(bufferedImage.getSubimage(80, 0, 480, 480));
				grabber.stop();
				dispose();
				grabando = false;
				new VisualizarImagen(img,nuevoEmpledo,dialog).setVisible(true);
				
			} catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	});
        
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { 
                try {
					grabber.stop();
					dispose();
					grabando = false;
				} catch (org.bytedeco.javacv.FrameGrabber.Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
            }
        };
        addWindowListener(windowListener);

        try {     
            Runnable miRunnable = () -> {
            	grabber = new OpenCVFrameGrabber(0); // 0 para la cámara predeterminada
                
            	try {
            		grabber.start(); // Iniciar la cámara
                    //lbCuadro.setIcon(null);
            		while(grabando) {
						frame = grabber.grab();
						Java2DFrameConverter converter = new Java2DFrameConverter();
			            java.awt.image.BufferedImage bufferedImage = converter.convert(frame);
			            lbMostrar.setIcon(new ImageIcon(bufferedImage));
            		}
            		if(!grabando) {
            			grabber.stop();
            		}
				} catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
					
				}
            };

            Thread thread = new Thread(miRunnable);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*try {
                grabber.stop(); // Detener la cámara
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }*/
        }
    }
    
}
