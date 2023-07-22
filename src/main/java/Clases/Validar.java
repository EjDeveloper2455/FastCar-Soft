package Clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class Validar {
	JTextField txt;
	public Validar(JTextField txt) {
		this.txt = txt;
	}
	
	public KeyAdapter decimales(int limite) {
		KeyAdapter keyAdapter = new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
             // Permite dígitos y el punto decimal
                if (!(Character.isDigit(c) || c == '.')) {
                	if(c != '-')e.consume(); // Ignora el evento si el caracter no es válido
                }
                if(c == '-') {
                	if(txt.getCaretPosition()!=0){
                		e.consume();
                	}
                }
                

                // Permite solo un punto decimal
                if (c == '.' && txt.getText().contains(".")) {
                    e.consume(); // Ignora el evento si ya hay un punto en el texto
                }
                
                if (txt.getText().length() >= limite && limite>0) {
                    e.consume(); // Ignora el evento si se alcanzó el límite de caracteres
                }
            }
		};
		return keyAdapter;
		
	}
	public KeyAdapter monedas(int limite) {
		KeyAdapter keyAdapter = new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
             // Permite dígitos y el punto decimal
                if (!(Character.isDigit(c) || c == '.')) {
                	e.consume(); // Ignora el evento si el caracter no es válido
                }  

                // Permite solo un punto decimal
                if (c == '.' && txt.getText().contains(".")) {
                    e.consume(); // Ignora el evento si ya hay un punto en el texto
                }
                
                if (txt.getText().length() >= limite && limite>0) {
                    e.consume(); // Ignora el evento si se alcanzó el límite de caracteres
                }
                
            }
		};
		return keyAdapter;
		
	}
	
	public KeyAdapter monedasNegativo(int limite) {
		KeyAdapter keyAdapter = new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
             // Permite dígitos y el punto decimal
                if (!(Character.isDigit(c) || c == '.')) {
                	if(c != '-')e.consume(); // Ignora el evento si el caracter no es válido
                }  
                if(c == '-') {
                	if(txt.getCaretPosition()!=0){
                		e.consume();
                	}
                }

                // Permite solo un punto decimal
                if (c == '.' && txt.getText().contains(".")) {
                    e.consume(); // Ignora el evento si ya hay un punto en el texto
                }
                
                if (txt.getText().length() >= limite && limite>0) {
                    e.consume(); // Ignora el evento si se alcanzó el límite de caracteres
                }
                
                String arr[] = txt.getText().split(".");
                if(arr.length>1) {
                	if(arr[2].length()>2) {
                		e.consume();
                	}
                }
            }
		};
		return keyAdapter;
		
	}
	public KeyAdapter naturales(int limite) {
		KeyAdapter keyAdapter = new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                // Permite dígitos y el punto decimal
                if (!(Character.isDigit(c) || c == '.')) {
                    e.consume(); // Ignora el evento si el caracter no es válido
                }

                // Permite solo un punto decimal
                if (c == '.' && txt.getText().contains(".")) {
                    e.consume(); // Ignora el evento si ya hay un punto en el texto
                }
                
                if (txt.getText().length() >= limite && limite>0) {
                    e.consume(); // Ignora el evento si se alcanzó el límite de caracteres
                }
            }
		};
		return keyAdapter;
		
	}
	public KeyAdapter enteros(int limite) {
		KeyAdapter keyAdapter = new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
             // Permite dígitos y el punto decimal
                if (!(Character.isDigit(c) )) {
                	if(c != '-')e.consume(); // Ignora el evento si el caracter no es válido
                }
                
                if(c == '-') {
                	if(txt.getCaretPosition()>0){
                		e.consume();
                	}
                	
                }
                

                // Permite solo un punto decimal
                if (c == '.' && txt.getText().contains(".")) {
                    e.consume(); // Ignora el evento si ya hay un punto en el texto
                }
                
                if (txt.getText().length() >= limite && limite>0) {
                    e.consume(); // Ignora el evento si se alcanzó el límite de caracteres
                }
            }
		};
		return keyAdapter;
		
	}

}
