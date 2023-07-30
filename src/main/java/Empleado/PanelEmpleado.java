/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Empleado;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FileDialog;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import Clases.ColorearFilasIntercaladas;
import Clases.Json;
import Clases.ModeloTabla;
import Clases.Peticion;
//import Empleados.VerImagen;
import Principal.MenuPrincipal;
import Principal.PanelPadre;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;

/**
 *
 * @author Dell
 */
public class PanelEmpleado extends PanelPadre {

    private JPanel panelVer;
	private CardLayout cardLayout;
	private JPanel panelPrincipal;
	private JButton btnNuevo;
	private JTable table;
	private JComboBox cmbBuscarEstado;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JDateChooser fechaDesde;
	private JDateChooser fechaHasta;
	private JLabel lblDesde;
	private JLabel lblHasta;
	private JComboBox cmbBuscarPor;


	/**
     * Creates new form PanelEmpleado
     */
    public PanelEmpleado(MenuPrincipal menuPrincipal) {
    	super(menuPrincipal);
        initComponents();
        setComponenst();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        
        setBounds(0,0,1000,660);
        setBackground(new Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMinimumSize(new java.awt.Dimension(1000, 660));
        setLayout(null);
 
        
        panelVer = new JPanel();
        panelVer.setBounds(0, 0, 1000, 620);
        panelVer.setLayout(null);
        panelVer.setBackground(Color.white);
        
        panelPrincipal = new JPanel();
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);
        panelPrincipal.setBounds(0, 0, 1000, 660);
        add(panelPrincipal,"panelVer");
        
        panelPrincipal.add(panelVer);
        
        btnNuevo = new JButton("Nuevo Empleado",new ImageIcon(getClass().getResource("/imagenes/nuevo-empleado.png")));
        btnNuevo.setBackground(new Color(255, 255, 255));
        btnNuevo.setFont(new Font("Roboto Medium", Font.BOLD, 14));
        btnNuevo.setBounds(10, 10, 200, 40);
        panelVer.add(btnNuevo);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 55, 980, 2);
        panelVer.add(separator);
        
        
        table = new JTable();
        table.setModel(new ModeloTabla(new Object[][] {},
	        	new String[] {"DNI", "Nombre Completo", "Cargo", "Departamento", "Estado"}
	        ));
        table.setRowHeight(28);
		table.setDefaultRenderer(Object.class, new ColorearFilasIntercaladas(info.getSecondColor(),info.getPrimaryColor(),Color.white));
        
        JScrollPane scrollTabla = new JScrollPane(table);
        scrollTabla.setBounds(10, 120, 980, 530);
        panelVer.add(scrollTabla);
        
        cmbBuscarEstado = new JComboBox(new String[] {"Todos","Activos","Inactivos"});
        cmbBuscarEstado.setBounds(407, 85, 240, 30);
        panelVer.add(cmbBuscarEstado);
        
        lblNewLabel = new JLabel("Estado");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel.setBounds(407, 61, 232, 25);
        panelVer.add(lblNewLabel);
        
        JLabel lblBuscarPor = new JLabel("Buscar por:");
        lblBuscarPor.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblBuscarPor.setBounds(718, 61, 79, 25);
        panelVer.add(lblBuscarPor);
        
        cmbBuscarPor = new JComboBox(new String[] {"DNI","Nombre"});
        cmbBuscarPor.setBounds(796, 59, 194, 25);
        panelVer.add(cmbBuscarPor);
        
        textField = new JTextField();
        textField.setBounds(718, 85, 241, 30);
        panelVer.add(textField);
        textField.setColumns(10);
        
        JButton btnBuscar = new JButton();
        btnBuscar.setBackground(Color.white);
        btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Buscar");
			}
        });
        btnBuscar.setBounds(960, 85, 30, 30);
        panelVer.add(btnBuscar);
        
        fechaDesde = new JDateChooser();
        fechaDesde.setBounds(110, 62, 240, 25);
        panelVer.add(fechaDesde);
        
        fechaHasta = new JDateChooser();
        fechaHasta.setBounds(110, 90, 240, 25);
        panelVer.add(fechaHasta);
        
        lblDesde = new JLabel("Desde:");
        lblDesde.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblDesde.setBounds(10, 61, 102, 25);
        panelVer.add(lblDesde);
        
        lblHasta = new JLabel("Hasta:");
        lblHasta.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblHasta.setBounds(10, 90, 102, 25);
        panelVer.add(lblHasta);
        
        
    }// </editor-fold>//GEN-END:initComponents
    
    public void setComponenst(){
        btnNuevo.addActionListener(v ->{
                JDialog dialog = new JDialog(menuPrincipal,true);
                dialog.setSize(920,680);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setLocationRelativeTo(menuPrincipal);
                dialog.getContentPane().add(new NuevoEmpleado(dialog,menuPrincipal));
                dialog.setVisible(true);
            });
    }
    
    public void login() {
    	Thread hilo = new Thread(() -> {
			try {
				
				Json empleados = Peticion.get(info.getApiPath()+"/api/empleado/", "");
				table.setModel(new ModeloTabla(
			        	empleados,new String[] {"empleado_dni", "empleado_nombre", "cargo_id", "departamento_id", "empleado_estado"},
			        	new String[] {"DNI", "Nombre Completo", "Cargo", "Departamento", "Estado"}
			        ));
				table.getColumnModel().getColumn(0).setPreferredWidth(150);
		        table.getColumnModel().getColumn(1).setPreferredWidth(300);
		        table.getColumnModel().getColumn(2).setPreferredWidth(150);
		        table.getColumnModel().getColumn(3).setPreferredWidth(150);
		        table.getColumnModel().getColumn(4).setPreferredWidth(50);
			}catch (Exception e) {
				// TODO: handle exception
			}
		});
		hilo.start();
    }
    
	@Override
	protected void redimensionar() {
		// TODO Auto-generated method stub
		
	}
}
