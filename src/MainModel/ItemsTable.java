
package MainModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ItemsTable extends AbstractTableModel {
    
    private ArrayList<Items>items;
     private String[] columns = {"No.", "Item Name", "Item Price","Count","Item Total"};

    public ItemsTable(ArrayList<Items> item) {
        this.items = item;
    }

    public ArrayList<Items> getItems() {
        return items;
    }
     

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
       return columns.length;
    }
    
    @Override
    public String getColumnName(int col)
    {
        return columns[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Items item = items.get(rowIndex);
         switch(columnIndex) {
            case 0: return item.getInvoice().getNumber();
            case 1: return item.getItem();
            case 2: return item.getPrice();
            case 3: return item.getNofItems();
            case 4: return item.getItemTotal();
            default : return "--";
    }
    
}}
