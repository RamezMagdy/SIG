
package ViewPackage;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class InvoicesDialog extends JDialog {
    private JTextField customerName;
    private JTextField invoiceDate;
    private JLabel customerNameLabel;
    private JLabel invoiceDateLabel;
    private JButton ButtonOK;
    private JButton ButtonCancel;

    public InvoicesDialog(Interface frame) {
        customerNameLabel = new JLabel("Customer Name:");
        customerName = new JTextField(20);
        invoiceDateLabel = new JLabel("Invoice Date:");
        invoiceDate = new JTextField(20);
        ButtonOK = new JButton("OK");
        ButtonCancel = new JButton("Cancel");
        
        ButtonOK.setActionCommand("OKCreateInv");
        ButtonCancel.setActionCommand("CancelCreateInvoice");
        
        ButtonOK.addActionListener(frame.getControl());
        ButtonCancel.addActionListener(frame.getControl());
        setLayout(new GridLayout(3, 2));
        
        add(invoiceDateLabel);
        add(invoiceDate);
        add(customerNameLabel);
        add(customerName);
        add(ButtonOK);
        add(ButtonCancel);
        
        pack();
        
    }

    public JTextField getCustomerName() {
        return customerName;
    }

    public JTextField getInvoiceDate() {
        return invoiceDate;
    }
    
}
