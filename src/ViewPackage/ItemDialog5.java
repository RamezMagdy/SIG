
package ViewPackage;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ItemDialog5 extends JDialog{
    private JTextField itemName;
    private JTextField itemCount;
    private JTextField itemPrice;
    private JLabel itemNameLabel;
    private JLabel itemCountLabel;
    private JLabel itemPriceLabel;
    private JButton ButtonOK;
    private JButton ButtonCancel;
    
    public ItemDialog5(Interface interfaace) {
        itemName = new JTextField(20);
        itemNameLabel = new JLabel("Item Name");
        
        itemCount = new JTextField(20);
        itemCountLabel = new JLabel("Item Count");
        
        itemPrice = new JTextField(20);
        itemPriceLabel = new JLabel("Item Price");
        
        ButtonOK = new JButton("OK");
        ButtonCancel = new JButton("Cancel");
        
        ButtonOK.setActionCommand("okCreateNewItem");
        ButtonCancel.setActionCommand("CancelCreateitem");
        
        ButtonOK.addActionListener(interfaace.getControl());
        ButtonCancel.addActionListener(interfaace.getControl());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLabel);
        add(itemName);
        add(itemCountLabel);
        add(itemCount);
        add(itemPriceLabel);
        add(itemPrice);
        add(ButtonOK);
        add(ButtonCancel);
        
        pack();
    }

    public JTextField getItemName() {
        return itemName;
    }

    public JTextField getItemCount() {
        return itemCount;
    }

    public JTextField getItemPrice() {
        return itemPrice;
    }
}
