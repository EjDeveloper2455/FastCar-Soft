package Clases;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Peticion {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		/*try {
			JSONObject token = peticionHttpPostJson("http://localhost:8080/api/users");
			System.out.println("token: "+token);
			JSONArray datos = peticionGetArray("http://localhost:8080/api/users","");
			for (int i = 0; i < datos.size(); i++) {
				JSONObject jsonObject = (JSONObject) datos.get(i);
				System.out.println(jsonObject);
			}
			System.out.println("Respuesta de peticion get by\n"+peticionGetOne("http://localhost:8080/api/users/fernanditodavidmejia2@gmail.com",""));
			
			JSONObject nuevoUser = new JSONObject();
			nuevoUser.put("email", "eddynmejia55@gmail.com");
			nuevoUser.put("password", "12345678");
			nuevoUser.put("rol", "Contabilidad");
			System.out.println("Resultado de peticion post\n"+peticionPost("http://localhost:8080/api/users",nuevoUser));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	public static Json get(String ruta,String token) throws Exception {
		 
		StringBuilder resultado = new StringBuilder();
		  URL url = new URL(ruta);

		  HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		  conexion.setRequestMethod("GET");
		  if(!token.isEmpty())conexion.setRequestProperty("Authorization","Bearer "+token);
		  BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		  String linea;
		  while ((linea = rd.readLine()) != null) {
		    resultado.append(linea);
		  }
		  
		  rd.close();
	
		  return new Json(resultado.toString(),true);
	}
	public static Json getOne(String ruta,String token) throws Exception {
		 
		StringBuilder resultado = new StringBuilder();
		  URL url = new URL(ruta);

		  HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		  conexion.setRequestMethod("GET");
		  if(!token.isEmpty())conexion.setRequestProperty("Authorization",token);
		  BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		  String linea;
		  while ((linea = rd.readLine()) != null) {
		    resultado.append(linea);
		  }
		  rd.close();
	
		  return  new Json(resultado.toString(),false);
	}
	
	@SuppressWarnings("unchecked")
	public static Json post(String ruta,Json datos) throws IOException, ParseException {
        URL url = new URL(ruta);
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setDoOutput(true);

        conn.getOutputStream().write(datos.toJsonString().getBytes());
        
        
        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String response = "";
        for (int c = in.read(); c != -1; c = in.read())
        	response += (char) c;
        return new Json(response,false);
    }
	public static Json put(String ruta,Json datos) throws IOException, ParseException {
        URL url = new URL(ruta);
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setDoOutput(true);

        conn.getOutputStream().write(datos.toJsonString().getBytes());
        
        
        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String response = "";
        for (int c = in.read(); c != -1; c = in.read())
        	response += (char) c;
        return new Json(response,false);
    }
	public static Json putImagen(String serverUrl,ImageIcon imageIcon) {


        // Convertir el ImageIcon a un InputStream
        InputStream inputStream = convertImageIconToInputStream(imageIcon);

        try {
            // Establecer la conexión HTTP
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=---Boundary");

            // Configurar el cuerpo de la solicitud con la imagen adjunta
            OutputStream outputStream = connection.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream), true);

            writer.println("-----Boundary");
            writer.println("Content-Disposition: form-data; name=\"imagen\"; filename=\"image.png\"");
            writer.println("Content-Type: image/png");
            writer.println();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            writer.println();
            writer.println("-----Boundary--");

            writer.close();
            outputStream.close();

            // Enviar la solicitud al servidor
            int responseCode = connection.getResponseCode();

            // Imprimir el código de respuesta
            System.out.println("Response Code: " + responseCode);

            // Leer la respuesta del servidor
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Imprimir la respuesta del servidor
            return new Json(response.toString(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	private static InputStream convertImageIconToInputStream(ImageIcon imageIcon) {
        // Convertir el ImageIcon a un InputStream
        Image image = imageIcon.getImage();
        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageData = baos.toByteArray();
        return new ByteArrayInputStream(imageData);
    }
}

