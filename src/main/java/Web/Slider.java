package Web;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageTypeSpecifier;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Principal.PanelPadre;

public class Slider {
	private String texto,url;
	private ImageIcon imagen;
	private int orden,id,x;
	private Slider prev,next,slider;
	private boolean isModified,cargado;
	private Color color;
	JPanel panel;
	private JPanel pnl;
	public Slider(int id,String texto, String url, int x,int orden,  JPanel panel,boolean isModified,PanelPadre padre) {
		this.texto = texto;
		this.url = url;
		this.orden = orden;
		this.id = id;
		this.isModified = isModified;
		this.panel = panel;
		this.x = x;
		this.prev = null;
		this.next = null;
		this.imagen = new ImageIcon(getClass().getResource("/imagenes/esperandoImagen.png"));
		this.slider = this;
		cargado = false;
		color = Color.black;
		if(id==-1)color = Color.green;
		
		pnl = new JPanel();
		pnl.setBounds(x,20, 90, 105);
		panel.add(pnl);
		pnl.setBackground(color);
		pnl.setLayout(null);
		
		int newHeight = (int) (((double) this.imagen.getIconHeight() / this.imagen.getIconWidth()) * 80);
		JButton btn = new JButton(padre.redimensionarImg(this.imagen,80,newHeight));
		btn.setBounds(5, 5, 80, 80);
		btn.setBorder(new LineBorder(Color.white,1));
		btn.setBackground(Color.white);
		pnl.add(btn);
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Texto: "+texto+"\nOrden: "+orden);
			}
		});
		
		JButton btnPrev = new JButton("<");
		btnPrev.setBounds(5, btn.getHeight()+btn.getY(), 40, 15);
		btnPrev.setBackground(Color.white);
		btnPrev.setFont(new Font("Arial",1,10));
		btnPrev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(prev !=null) {
					int tmpX = prev.getX();
					prev.setX(x);
					setX(tmpX);
					int tmpOrden = prev.getOrden();
					prev.setOrden(orden);
					setOrden(tmpOrden);
					setModified(true);
					prev.setModified(true);
					Slider tmpNext = next;
					next = prev;
					prev.prev = slider;
				}
				
			}
		});
		pnl.add(btnPrev);
		
		JButton btnNext = new JButton(">");
		btnNext.setBounds(btnPrev.getWidth()+btnPrev.getX(), btnPrev.getY(), 40, 15);
		btnNext.setBackground(Color.white);
		btnNext.setFont(new Font("Arial",1,10));
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(next !=null) {
					int tmpX = next.getX();
					next.setX(x);
					setX(tmpX);
					int tmpOrden = next.getOrden();
					next.setOrden(orden);
					setOrden(tmpOrden);
					setModified(true);
					next.setModified(true);
					Slider tmpPrev = next;
					prev = next;
					prev.prev = slider;
				}
			}
		});
		pnl.add(btnNext);
		
		Thread hilo = new Thread(() -> {
			try {
				if(id>0)this.imagen = padre.obtenerImagen(url);
				else this.imagen = new ImageIcon(url);
				
				int newAlto = (int) (((double) this.imagen.getIconHeight() / this.imagen.getIconWidth()) * 80);
				btn.setIcon(padre.redimensionarImg(this.imagen,80,newAlto));
				cargado = true;
			}catch (Exception e) {
				// TODO: handle exception
			}
		});
		hilo.start();
		
	}
	
	
	public Slider getPrev() {
		return prev;
	}


	public void setPrev(Slider prev) {
		this.prev = prev;
	}


	public Slider getNext() {
		return next;
	}


	public void setNext(Slider next) {
		this.next = next;
	}


	public boolean isCargado() {
		return cargado;
	}


	public void setCargado(boolean cargado) {
		this.cargado = cargado;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
		pnl.setBackground(color);
	}


	//Implementacion de los getter y setter
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getX() {
		return x;
	}
	public void setX(int xx) {
		this.x = xx;
		pnl.setBounds(this.x, pnl.getY(), pnl.getWidth(), pnl.getHeight());
	}
	public boolean isModified() {
		return isModified;
	}
	public void setModified(boolean isModified) {
		this.isModified = isModified;
		if(isModified) {
			setColor(Color.orange);
			
		}
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ImageIcon getImagen() {
		return imagen;
	}
	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

}
