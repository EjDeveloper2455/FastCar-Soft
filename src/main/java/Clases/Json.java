package Clases;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {
	JSONObject jsonObject;
	JSONArray jsonArray;
	public Json() {
		jsonObject = new JSONObject();
		jsonArray = new JSONArray();
	}
	public Json(JSONObject json) {
		jsonObject = json;
		jsonArray = new JSONArray();
	}
	public Json(String texto,boolean isArray) {
		JSONParser parser = new JSONParser();
		jsonArray = new JSONArray();
		try {
			if(!isArray)jsonObject = (JSONObject) parser.parse(texto);
			else jsonArray = (JSONArray) parser.parse(texto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void parseJson(String texto) {
		JSONParser parser = new JSONParser();
		try {
			jsonObject = (JSONObject) parser.parse(texto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void parseArray(String texto) {
		JSONParser parser = new JSONParser();
		try {
			jsonArray = (JSONArray) parser.parse(texto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void put(String key,String dato) {
		jsonObject.put(key, dato);
	}
	
	@SuppressWarnings("unchecked")
	public void add(JSONObject dato) {
		jsonArray.add(dato);
	}
	@SuppressWarnings("unchecked")
	public void add(JSONObject dato[]) {
		for (int i = 0; i < dato.length; i++)jsonArray.add(dato[i]);
	}
	public Object get(String key) {
		return jsonObject.get(key);
	}
	public Object getIn(String key) {
		String arrayKey[] = key.split("~");
		JSONObject newJson = jsonObject;
		for (int i = 0; i < arrayKey.length; i++) {
			try {
				newJson = (JSONObject)newJson.get(arrayKey[i]);
			}catch (Exception e) {
				
			}
		}
		
		return newJson.get(arrayKey[arrayKey.length-1]);
	}
	public JSONObject get(int index) {
		return (JSONObject) jsonArray.get(index);
	}
	public JSONObject getJson() {
		return jsonObject;
	}
	public JSONArray getArray() {
		return jsonArray;
	}
	public List<JSONObject> getArrayList() {
		List<JSONObject> list = new ArrayList<>();
		for (int i = 0; i < longitud(); i++) {
			list.add((JSONObject) jsonArray.get(i));
		}
		return list;
	}
	public String toJsonString() {
		return jsonObject.toJSONString();
	}
	public String toArrayString() {
		return jsonArray.toJSONString();
	}
	public int longitud() {
		return jsonArray.size();
	}
}
