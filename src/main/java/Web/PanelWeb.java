package Web;

import javax.swing.*;
import javax.swing.border.LineBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Clases.Json;
import Clases.Parametros;
import Clases.Peticion;
import Principal.MenuPrincipal;
import Principal.PanelPadre;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.awt.FileDialog;
import java.awt.Window;
public class PanelWeb extends PanelPadre {

	private List<JButton> btnImagenes;
	private List<Slider> sliders;
	private JButton btnAgregar;
	private ImageIcon imgSelect;
	private JLabel lbImagen;
	private JButton btnSubirImagen;
	private String newPath;
	private final PanelWeb panelWeb;
	private JPanel panelVerImagen;
	private JTextArea texto;
	public PanelWeb(MenuPrincipal menuPrincipal) {
		super(menuPrincipal);
		setBackground(new Color(255, 255, 255));
		setSize(1000,660);
		setLayout(null);
		
		imgSelect = null;
		newPath = null;
		
		panelWeb = this;
		
		JPanel panelAgregarImagen = new JPanel();
		panelAgregarImagen.setBounds(10, 10, 980, 300);
		panelAgregarImagen.setBackground(new java.awt.Color(86, 89, 92));
		panelAgregarImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Imagen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
		panelAgregarImagen.setLayout(null);
		add(panelAgregarImagen);
		
		texto = new JTextArea();
		texto.setBounds(10,20,400,210);
		texto.setBackground(Color.white);
		panelAgregarImagen.add(texto);
		
		
		lbImagen = new JLabel();
		lbImagen.setBounds(texto.getX()+texto.getWidth()+350, 20, 200, 200);
		lbImagen.setBorder(new LineBorder(Color.white,1));
		panelAgregarImagen.add(lbImagen);
		
		btnSubirImagen = new JButton("Seleccionar imagen");
		btnSubirImagen.setBounds(lbImagen.getX(), lbImagen.getHeight()+lbImagen.getY()+10, lbImagen.getWidth(), 50);
		btnSubirImagen.setBackground(Color.white);
		btnSubirImagen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				subirImagen();
			}
		});
		panelAgregarImagen.add(btnSubirImagen);
		
		btnAgregar = new JButton("Agregar", new ImageIcon(getClass().getResource("/imagenes/agg.png")));
		btnAgregar.setBounds(10, texto.getHeight()+texto.getY()+10, 120, 40);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarSlider();
			}
		});
		btnAgregar.setBackground(Color.white);
		panelAgregarImagen.add(btnAgregar);
		
		JPanel panelImagenAgregado = new JPanel();
		panelImagenAgregado.setBounds(10, panelAgregarImagen.getHeight()+panelAgregarImagen.getY()+10, 980, 120);
		panelImagenAgregado.setBackground(new java.awt.Color(86, 89, 92));
		panelImagenAgregado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Imagen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
		panelImagenAgregado.setLayout(null);
		//add(panelImagenAgregado);
		
		panelVerImagen = new JPanel();
		panelVerImagen.setBounds(10, panelImagenAgregado.getHeight()+panelImagenAgregado.getY()+10, 980, 140);
		panelVerImagen.setBackground(new java.awt.Color(86, 89, 92));
		panelVerImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Slider", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
		panelVerImagen.setLayout(null);
		add(panelVerImagen);
		
		//Se crea un hilo para cargar los recursos de imagenes desde el servidor
		Thread hilo = new Thread(() -> {
			try {
				Json res = Peticion.get(info.getApiPath()+"/api/slider", "");
				sliders = new ArrayList<>();
				int imgX = (panelVerImagen.getWidth()/2)-((res.longitud()*95)/2);
				for (JSONObject dato : res.getArrayList()) {
					sliders.add(new Slider(
							Integer.parseInt(dato.get("slider_id").toString()),
							dato.get("slider_texto").toString(),
					dato.get("slider_ruta_imagen").toString(),imgX,
					Integer.parseInt(dato.get("slider_orden").toString()),
					panelVerImagen,false,this));
					imgX += 95;
				}
			
			
				for (int i = 0; i < sliders.size(); i++) {
					if(i>0) sliders.get(i).setPrev(sliders.get(i-1));
					if(i<sliders.size()-1) sliders.get(i).setNext(sliders.get(i+1));
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		hilo.start();
	}
	
	public void agregarSlider() {
		if(imgSelect!=null) {
			if(sliders.size()==10) {
				JOptionPane.showMessageDialog(panelWeb, "El límite a de imagenes a agregar es 10","Advertencia",2);
				return;
			}
			
			int orden = 1;  
			Slider ultimo = null;
			if(sliders.size()>0) {
				ultimo = sliders.get(sliders.size()-1);
				orden = ultimo.getOrden()+1;
			}
			Slider nuevo = new Slider(-1,texto.getText(),newPath,0,orden,panelVerImagen,false,panelWeb);
			nuevo.setPrev(ultimo);
			ultimo.setNext(nuevo);
			sliders.add(nuevo);
			
			int imgX = (panelVerImagen.getWidth()/2)-((sliders.size()*95)/2);
			for (Slider slide : sliders) {
				slide.setX(imgX);
				imgX += 95;
			}
			newPath = null;
			imgSelect = null;
			lbImagen.setIcon(null);
		}else JOptionPane.showMessageDialog(panelWeb, "Debes seleccionar una imagen","Error",0);
	}
	@Override
	protected void redimensionar() {
		// TODO Auto-generated method stub
		
	}
	
	public void subirImagen() {
		// Crea un objeto FileDialog
        FileDialog fileDialog = new FileDialog(menuPrincipal, "Seleccionar imagen", FileDialog.LOAD);

        // Filtra solo los archivos de imagen
        fileDialog.setFile("*.jpg;*.jpeg;*.png;*.gif");

        // Abre el diálogo de selección de archivos
        fileDialog.setVisible(true);

        // Obtiene el archivo seleccionado
        String directory = fileDialog.getDirectory();
        String filename = fileDialog.getFile();
        
        // Comprueba si se seleccionó un archivo
        if (filename != null) {
        	this.newPath = directory + filename;
        	this.imgSelect =  new ImageIcon(this.newPath);
        	int newHeight = (int) (((double) this.imgSelect.getIconHeight() / this.imgSelect.getIconWidth()) * lbImagen.getWidth());
            lbImagen.setIcon(redimensionarImg(this.imgSelect,lbImagen.getWidth(),newHeight));
        }
	}
}
