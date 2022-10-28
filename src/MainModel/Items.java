
package MainModel;


public class Items {
    private String item;
    private float price;
    private int nofItems;
    private Invoice invoice;

    public Items() {
    }

    public Items(String item, float price, int nofItems, Invoice invoice) {
        this.item = item;
        this.price = price;
        this.nofItems = nofItems;
        this.invoice = invoice;
    }

    public Items(Items item, float price, int nofItems, Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public double getItemTotal(){
        return price*nofItems ;
    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNofItems() {
        return nofItems;
    }

    public void setNofItems(int nofItems) {
        this.nofItems = nofItems;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
  public String getAsCSV() {
        return invoice.getNumber() + "," + item + "," + price + "," + nofItems ;
    }
    
}
