
package MainModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class InvoicesTable extends AbstractTableModel {
    
    ArrayList<Invoice> invoices;
     private String[] columns = {"No.", "Date", "Customer", "Total"};
    
    public InvoicesTable(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }
    
    
    @Override
    public int getRowCount() {
        return invoices.size();
    }

   
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoice invoice = invoices.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return invoice.getNumber();
            case 1: return invoice.getDate();
            case 2: return invoice.getCustomer();
            case 3: return invoice.getInvTotal();
            default : return "-";
    }
    
}

    public Invoice get(int selectedInvoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
