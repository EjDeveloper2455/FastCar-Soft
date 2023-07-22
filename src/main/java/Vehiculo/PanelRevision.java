package Vehiculo;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRevision extends JPanel {
	private JTextField txtFecha;

	/**
	 * Create the panel.
	 */
	public PanelRevision() {
		setLayout(null);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(10, 26, 86, 20);
		add(txtFecha);
		txtFecha.setColumns(10);

	}
}
