package Principal;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Clases.Parametros;

public abstract class PanelPadre extends JPanel{
	protected MenuPrincipal menuPrincipal;
	protected Parametros info;
	public PanelPadre(MenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
		setBackground(new Color(255, 255, 255));
		//setBounds(200, 40, 1000, 700);
		setBounds(200, 40, menuPrincipal.getWidth()-200, menuPrincipal.getHeight()-40);
		setLayout(null);
		setBorder(new LineBorder(Color.red,1));
		info = menuPrincipal.getInfo();
	}
	protected abstract void redimensionar();
}
