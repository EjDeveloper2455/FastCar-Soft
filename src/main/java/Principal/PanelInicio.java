package Principal;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Clases.Parametros;

public class PanelInicio extends PanelPadre{
	
	private JTextPane textPane;
	public PanelInicio(final MenuPrincipal menuPrincipal) {
		super(menuPrincipal);
		
		textPane = new JTextPane();
		
		textPane.setBounds(41, 584, 862, 65);
		add(textPane);
		
		setVisible(false);
	}
	public void login() {
		textPane.setText("sucursal: "+info.getSucursal());
	}
	@Override
	protected void redimensionar() {
		this.setBounds(getX(), getY(), menuPrincipal.getWidth()-200, menuPrincipal.getHeight()-40);
	}

}
