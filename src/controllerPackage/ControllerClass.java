
package controllerPackage;

import MainModel.Invoice;
import MainModel.InvoicesTable;
import MainModel.Items;
import MainModel.ItemsTable;
import ViewPackage.Interface;
import ViewPackage.InvoicesDialog;
import ViewPackage.ItemDialog5;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ControllerClass implements ActionListener, ListSelectionListener {
    private Interface interfaace;
    private InvoicesDialog invoicesDialog;
    private ItemDialog5 itemsDialog;
    
public ControllerClass(Interface interfaace){
    this.interfaace = interfaace;
}
    
    @Override
    public void actionPerformed(ActionEvent e) {
      
        
        String command = e.getActionCommand();
              
        switch (command)
        {
            case "Load File":
                load();
            break;
            
             case "Save File":
                 save();
            break;
            
             case "Create New Invoice":
                 createNewInv();
            break;
            
             case "Delete Invoice":
                 deleteInv();
            break;
            
             case "Create New Item":
                 createNewItem();
            break;
            
             case "Delete Item":
                 deleteItem();
            break;
            
            
            
            
            case "CancelCreateInvoice":
                CancelCreateInvoice();
                break;
                
                
            case "OKCreateInv":
                OKCreateInv();
                break;
                
            case "okCreateNewItem":
                okCreateNewItem();
                break;
                
                
            case "CancelCreateitem":
                CancelCreateitem();
                break;
        }
        
    }

     @Override
    public void valueChanged(ListSelectionEvent e) {
        int selection = interfaace.getInvoicesTable2().getSelectedRow();
        if (selection != -1){
                Invoice curInvoice = interfaace.getInvoices().get(selection);
         interfaace.getInvNumberLabel().setText("" + curInvoice.getNumber());
            
            interfaace.getInvDateLabel().setText(curInvoice.getDate());
            
            interfaace.getCustomerLabel().setText(curInvoice.getCustomer());
            
            interfaace.getInvTotalLabel().setText("" + curInvoice.getInvTotal ());
            
            ItemsTable itemsTable = new ItemsTable(curInvoice.getItems());
            
            interfaace.getItemsTable().setModel(itemsTable);
            
            itemsTable.fireTableDataChanged();
        }
    }
    
    
    
    private void load() {
        JFileChooser fileChooser = new JFileChooser();
        
        try {
        int choice = fileChooser.showOpenDialog(interfaace);
        if  ( choice == JFileChooser.APPROVE_OPTION)
        {
            fileChooser.getSelectedFile();
            File invoiceFile = fileChooser.getSelectedFile();
           
            Path invoicePath = Paths.get(invoiceFile.getAbsolutePath());
                       List<String> invoiceLines = Files.readAllLines(invoicePath);
           
           ArrayList<Invoice> firstArray = new ArrayList<>();
           for (String invoiceLine : invoiceLines)
                       {
                          
               String[] invoiceCells = invoiceLine.split(","); 
               
               int invNumber = Integer.parseInt(invoiceCells[0]);
               String invDate = invoiceCells[1];
               String customerName = invoiceCells[2];
               Invoice invoice = new Invoice(invNumber,invDate,customerName);
               firstArray.add(invoice);
               
           }    
           
            choice = fileChooser.showOpenDialog(interfaace);
             if  ( choice == JFileChooser.APPROVE_OPTION)
             {
             File itemFile = fileChooser.getSelectedFile();
             Path itemPath = Paths.get(itemFile.getAbsolutePath());
                       List<String> itemLines = Files.readAllLines(itemPath);
              for (String lineLine : itemLines ) {
                        String lineParts[] = lineLine.split(",");
                        int invoiceNum = Integer.parseInt(lineParts[0]);
                        String itemName = lineParts[1];
                        float itemPrice = Float.parseFloat(lineParts[2]);
                        int count = Integer.parseInt(lineParts[3]);
                        Invoice inv = null;
                          for (Invoice invoice : firstArray) {
                            
                            if (invoice.getNumber() == invoiceNum) {
                                inv = invoice;
                                break;
                            }
                        }
                         Items line = new Items(itemName, itemPrice, count, inv);
                        inv.getItems().add(line);
                   } 
           
        }
             interfaace.setInvoices(firstArray);
             InvoicesTable invoicesTable = new InvoicesTable (firstArray);
             interfaace.setInvoicesTable(invoicesTable);
             interfaace.getInvoicesTable2().setModel(invoicesTable);
                interfaace.getInvoicesTable().fireTableDataChanged();
        }  
                  }catch (IOException exeption){
         exeption.printStackTrace();
                }
        }
       
    
        private void save() {
            ArrayList<Invoice> invoices = interfaace.getInvoices();
        String headers = "";
        String lines = "";
        
        for (Invoice invoice : invoices) {
            String invCSV = invoice.getAsCSV();
            headers += invCSV;
            headers += "\n";

            for (Items items : invoice.getItems()) {
                String lineCSV = items.getAsCSV();
                lines += lineCSV;
                lines += "\n";
            }
        }
       
        try {
            JFileChooser filech = new JFileChooser();
            int result = filech.showSaveDialog(interfaace);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = filech.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                hfw.write(headers);
                hfw.flush();
                hfw.close();
                result = filech.showSaveDialog(interfaace);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = filech.getSelectedFile();
                    FileWriter lfw = new FileWriter(lineFile);
                    lfw.write(lines);
                    lfw.flush();
                    lfw.close();
                 
                }       
            }
        } catch (Exception ex) {

        }
        
        }

    private void createNewInv() {
        invoicesDialog = new InvoicesDialog(interfaace);
        invoicesDialog.setVisible(true);
    }

    private void deleteInv() {
        int selectedRow = interfaace.getInvoicesTable2().getSelectedRow();
        
        if (selectedRow != -1) {
            interfaace.getInvoices().remove(selectedRow);
            interfaace.getInvoicesTable().fireTableDataChanged();
    }
    }
    private void createNewItem() {
        itemsDialog = new ItemDialog5(interfaace);
        itemsDialog.setVisible(true);
    }

    private void deleteItem() {
        int selectedRow = interfaace.getItemsTable().getSelectedRow();
        
        if (selectedRow != -1) {
            ItemsTable linesTeModel2 = (ItemsTable) interfaace.getItemsTable().getModel();
            linesTeModel2.getItems().remove(selectedRow);
            linesTeModel2.fireTableDataChanged();
            interfaace.getInvoicesTable().fireTableDataChanged();
    }
    }

    private void CancelCreateInvoice() {
         invoicesDialog.setVisible(false);
        invoicesDialog.dispose();
        invoicesDialog = null;
    }

    private void OKCreateInv() {
        DateFormat dateF = new SimpleDateFormat("dd-mm-yyyy");
String date = invoicesDialog.getInvoiceDate().getText();
        String customer = invoicesDialog.getCustomerName().getText();
                int num = interfaace.getNextInvNumber();
                
     try {
         String[] dateParts = date.split("-");
         if (dateParts.length < 3) {
             JOptionPane.showMessageDialog(interfaace, "Wrong Date Format (accepted date format dd-mm-yyyy)", "Error", JOptionPane.ERROR_MESSAGE);
         }else{
             int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                if (day > 31 || month > 12) {
                    JOptionPane.showMessageDialog(interfaace, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
         }else {
                    Invoice invoice = new Invoice(num, date, customer);
        interfaace.getInvoices().add(invoice);
        interfaace.getInvoicesTable().fireTableDataChanged();
        invoicesDialog.setVisible(false);
        invoicesDialog.dispose();
        invoicesDialog = null;
           } 
                        }
     }catch(Exception ex){
         JOptionPane.showMessageDialog(interfaace, "Wrong Date Format (accepted date format dd-mm-yyyy)", "Error", JOptionPane.ERROR_MESSAGE);
     }
        
    }

   private void CancelCreateitem(){
        itemsDialog.setVisible(false);
        itemsDialog.dispose();
        itemsDialog = null;
    }

   
    private void okCreateNewItem() {
System.out.println("no problem");
        String item = itemsDialog.getItemName().getText();
        String nofItemsStr = itemsDialog.getItemCount().getText();
        String priceStr = itemsDialog.getItemPrice().getText();
        int nofItems = Integer.parseInt(nofItemsStr);
        float price = Float.parseFloat(priceStr);
        int selectedInvoice = interfaace.getInvoicesTable2().getSelectedRow();
        
        if (selectedInvoice != -1) {
            Invoice invoice = interfaace.getInvoices().get(selectedInvoice);
            Items item5 = new Items(item, price, nofItems, invoice);
            invoice.getItems().add(item5);
             ItemsTable itemsTable = (ItemsTable) interfaace.getItemsTable().getModel();
             itemsTable.fireTableDataChanged();
            interfaace.getInvoicesTable().fireTableDataChanged();}
        
        itemsDialog.setVisible(false);
        itemsDialog.dispose();
        itemsDialog = null;    }

    
}
