/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fastcar.soft;
import Inicio.Login;
import Principal.MenuPrincipal;

import javax.swing.JOptionPane;

import Clases.Parametros;
/**
 *
 * @author Dell
 */
public class FastcarSoft {

    public static void main(String[] args) {
    	try {
        new MenuPrincipal().setVisible(true);
    	}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
    }
}
