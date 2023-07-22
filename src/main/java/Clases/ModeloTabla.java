package Clases;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.simple.JSONObject;
public class ModeloTabla extends DefaultTableModel{
    private Object datos[][];
    String colum[];
    private boolean editable;
    public ModeloTabla(Object arrUser[][],int Height,String colum[]){
        this.colum=colum;
        if(arrUser.length>=Height){
            this.datos=new Object[arrUser.length][arrUser[0].length];
            for(int i=0; i<arrUser.length; i++){
            for(int j=0; j<arrUser[j].length; j++)this.datos[i][j]=arrUser[i][j];
        }
            
        }
        else{
        this.datos=new Object[Height][colum.length];
        
        for(int i=0; i<arrUser.length; i++){
            for(int j=0; j<this.colum.length; j++){
                //javax.swing.JOptionPane.showMessageDialog(null, i+" / "+j+" / "+this.colum.length);
                this.datos[i][j]=arrUser[i][j];
                
            }
        }

        for(int i=datos.length; i<this.datos.length; i++){
            for(int j=0; j<this.datos[i].length; j++)this.datos[i][j]=null;
        }
       }
        editable = false;
        this.setDataVector(this.datos, colum);
       
    }
    public ModeloTabla(Json data,String key[],String colum[]){
        this.colum=colum;
        this.datos = new Object[data.longitud()][key.length];
        List<JSONObject> list = data.getArrayList();
        int index = 0;
        for (JSONObject jsonData : list) {
			Object colData[] = new Object[key.length];
			for (int i = 0; i < colum.length; i++) colData[i] = jsonData.get(key[i]);
			this.datos[index] = colData;
			index++;
		}
        this.setDataVector(this.datos, colum);
        editable = false;
    }
    public ModeloTabla(Object arrUser[][],String colum[]){
        this.colum=colum;
        this.datos = arrUser;
        this.setDataVector(this.datos, colum);
        editable = false;
    }
   
    
    @Override
    public boolean isCellEditable(int row, int column) {
		return editable;
	}
    public void recargarDatos(Object arrUser[][]) {
    	this.datos = arrUser;
    	this.setDataVector(this.datos, colum);
    }
    public void addCheckBox(int column, JTable table)
    {
        TableColumn tc = table.getColumnModel().getColumn(column);
        tc.setCellEditor(table.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
    }
    public boolean IsSelected(int row, int column, JTable table)
    {    
        return table.getValueAt(row, column) != null;                       
    }    
    
}