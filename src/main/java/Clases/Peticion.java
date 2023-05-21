package Clases;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

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
	public static JSONArray get(String ruta,String token) throws Exception {
		 
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
		  
		  JSONParser parser = new JSONParser();
		  rd.close();
	
		  return (JSONArray) parser.parse(resultado.toString());
	}
	public static JSONObject getOne(String ruta,String token) throws Exception {
		 
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
		  
		  JSONParser parser = new JSONParser();
		  rd.close();
	
		  return (JSONObject) parser.parse(resultado.toString());
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject post(String ruta,JSONObject datos) throws IOException, ParseException {
        URL url = new URL(ruta);
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setDoOutput(true);

        conn.getOutputStream().write(datos.toJSONString().getBytes());
        
        
        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String response = "";
        for (int c = in.read(); c != -1; c = in.read())
        	response += (char) c;
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(response);
    }

}
