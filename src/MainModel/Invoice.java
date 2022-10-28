

package MainModel;

import java.util.ArrayList;
import java.util.Date;


public class Invoice {
    private int number;
    private String date;
    private String customer;
    private ArrayList<Items> items ;
    private double invTotal; 
    
    public Invoice() {
    }
public double getInvTotal (){
   double invoiceTotal =0.0;
   for (Items item : getItems()){
       invoiceTotal += item.getItemTotal() ;
   }
    return invoiceTotal;
    
}
    public ArrayList<Items> getItems() {
        if (items==null){
            items=new ArrayList<>();
        }
        return items;
    }

    public Invoice(int number, String date, String customer) {
        this.number = number;
        this.date = date;
        this.customer = customer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

   public String getAsCSV() {
        return number +  " , "  +  date  +  " , " +  customer  ;
    }
    
}