package Clases;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class ColorearFilasIntercaladas extends DefaultTableCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  Color EVEN_ROW_COLOR;
    private Color ODD_ROW_COLOR;
    private Color foregroundColor;
    public ColorearFilasIntercaladas(Color color1, Color color2) {
    	EVEN_ROW_COLOR = color1;
        ODD_ROW_COLOR = color2;
        foregroundColor = null;
    }
    public ColorearFilasIntercaladas(Color color1, Color color2,Color color3) {
    	EVEN_ROW_COLOR = color1;
        ODD_ROW_COLOR = color2;
        foregroundColor = color3;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (!isSelected) {
            Color backgroundColor = (row % 2 == 0) ? EVEN_ROW_COLOR : ODD_ROW_COLOR;
            Color foreColor = (row % 2 != 0) ? EVEN_ROW_COLOR : ODD_ROW_COLOR;
            c.setForeground((foregroundColor==null)?foreColor:foregroundColor);
            c.setBackground(backgroundColor);
            
        }
        return c;
    }
}
