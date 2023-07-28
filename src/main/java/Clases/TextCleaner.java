package Clases;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class TextCleaner {
    public static void main(String[] args) {
        try {
			Json json = Peticion.get("http://34.125.242.85:8080/api/consultar/MAT1234", "");
			JOptionPane.showMessageDialog(null, json.toArrayString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}