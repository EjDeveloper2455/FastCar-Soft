/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author Dell
 */
public class SubMenu extends JDialog {

    JButton btnIconos[];
    private int btnSelect;

    public SubMenu(MenuPrincipal menuPrincipal, JPanel pnl, JButton btn, String iconText[], String iconPath[]) {
        super(menuPrincipal);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setUndecorated(true);
        setModal(true);
        btnSelect = 0;
        JPanel panel = new JPanel();
        panel.setBackground(new Color(33, 37, 41));
        panel.setBounds(0, 0, 204, 500);
        panel.setBorder(new LineBorder(Color.white, 1));
        panel.setLayout(null);
        getContentPane().add(panel);
        
        JLabel lbTitulo = new JLabel(btn.getText());
        lbTitulo.setBounds(20,5,260,30);
        lbTitulo.setFont(new Font("Times New Roman", 1, 16));
        lbTitulo.setForeground(Color.white);
        panel.add(lbTitulo);

        JButton btnSalir = new JButton(new ImageIcon(getClass().getResource("/imagenes/salir2.png")));
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnSalir.setRolloverIcon(new ImageIcon(getClass().getResource("/imagenes/Salir.png")));
        btnSalir.setContentAreaFilled(false);
        btnSalir.setBorder(null);
        btnSalir.setBounds(panel.getWidth() - 40, 0, 30, 40);
        panel.add(btnSalir);

        int iconosY = 40;
        btnIconos = new JButton[iconText.length];
        for (int i = 0; i < iconText.length; i++) {
            btnIconos[i] = new JButton(iconText[i], new ImageIcon(getClass().getResource("/imagenes/" + iconPath[i] + ".png")));
            btnIconos[i].setBounds(2, iconosY, 200, 50);
            panel.add(btnIconos[i]);
            btnIconos[i].setBackground(new Color(33, 37, 41));
            btnIconos[i].setHorizontalAlignment(SwingConstants.LEFT);
            btnIconos[i].setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
            btnIconos[i].setForeground(Color.white);
            btnIconos[i].setFont(new Font("Times New Roman", 1, 18));
            //btnIconos[i].setContentAreaFilled(false);
            btnIconos[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < btnIconos.length; i++) {
                        if (e.getSource() == btnIconos[i]) {
                            pintarBoton(i);
                            btnSelect = i;
                            menuPrincipal.seleccionarPanel("panel" + iconText[i]);
                            dispose();
                        }
                    }
                }
            });

            btnIconos[i].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    for (int i = 0; i < btnIconos.length; i++) {
                        if (e.getSource() == btnIconos[i]) {
                            pintarBoton2(i);
                        }
                    }
                }

                public void mouseExited(MouseEvent e) {
                    for (int i = 0; i < btnIconos.length; i++) {
                        if (e.getSource() == btnIconos[i]) {
                            despintarBoton(i);
                        }
                    }
                }
            });

            iconosY += 50;
        }
        setSize(200, iconosY);
        setBounds(btn.getX()+btn.getWidth()+80, btn.getY()+3, 200, iconosY+20);
        setVisible(true);
    }

    public void pintarBoton(int index) {
        for (int i = 0; i < btnIconos.length; i++) {
            btnIconos[i].setBackground(new Color(33, 37, 41));
        }
        btnIconos[index].setBackground(new Color(72, 76, 81));
    }

    public void pintarBoton2(int index) {
        for (int i = 0; i < btnIconos.length; i++) {
            if (i != btnSelect) {
                btnIconos[i].setBackground(new Color(33, 37, 41));
            }
        }
        btnIconos[index].setBackground(new Color(72, 76, 81));
    }

    public void despintarBoton(int index) {
        for (int i = 0; i < btnIconos.length; i++) {
            if (i != btnSelect) {
                btnIconos[i].setBackground(new Color(33, 37, 41));
            }
        }
    }

}
