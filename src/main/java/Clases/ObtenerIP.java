package Clases;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ObtenerIP {
    public static void main(String[] args) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ip = localHost.getHostAddress();
            JOptionPane.showMessageDialog(null,"IP de tu computadora: " + ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
