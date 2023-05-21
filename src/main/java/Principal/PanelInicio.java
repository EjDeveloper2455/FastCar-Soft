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
	
	public PanelInicio(final MenuPrincipal menuPrincipal) {
		super(menuPrincipal);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("token: "+info.getToken());
		textPane.setBounds(41, 584, 862, 65);
		add(textPane);
		
		setVisible(false);
	}
	@Override
	protected void redimensionar() {
		this.setBounds(getX(), getY(), menuPrincipal.getWidth()-200, menuPrincipal.getHeight()-40);
	}

}
