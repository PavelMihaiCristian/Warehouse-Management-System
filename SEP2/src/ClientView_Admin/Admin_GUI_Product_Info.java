package ClientView_Admin;



import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.CellRendererPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.GridLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.FlowLayout;

public class Admin_GUI_Product_Info extends JPanel{

	private JPanel frmProductInformation;
	private JTextField textField;
	private JButton search;
	private JButton cancelButton;
	private JTextArea textArea;
	private JScrollPane scroll;

	/**
	 * Create the application.
	 */
	public Admin_GUI_Product_Info() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProductInformation = new JPanel();
		
		frmProductInformation.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JLabel lblProductId = new JLabel("Product ID:");
		panel.add(lblProductId);
		
		textField = new JTextField();
		
		panel.add(textField);
		textField.setColumns(10);
		
		search = new JButton("Search");
		cancelButton=new JButton("Cancel");
		
		panel.add(search);	
		panel.add(cancelButton);

		//JPanel text = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textArea = new JTextArea(10,50);
		scroll=new JScrollPane(textArea);
		//text.add(textArea);
		textArea.setEditable(false);
		
		frmProductInformation.add(panel, BorderLayout.NORTH);
		frmProductInformation.add(scroll, BorderLayout.CENTER);
		
		/*JPanel main=new JPanel(new BorderLayout());
		main.add(panel,BorderLayout.NORTH);
		main.add(frmProductInformation,BorderLayout.CENTER);*/
		
		add(frmProductInformation);
		setVisible(true);
	}
	
	//update 02/12/2012
	public JButton getsearch() {
		return search;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextArea getTextArea() {
		return textArea;
	}
	
	/*public void printToTextArea(String productName, int palletID, Object expiryDate, int amount)
	{
		textArea.append("Product Name:" + productName + ", Pallet ID: " + palletID + ", Expiry Date: " + expiryDate + ", Amount: " + amount + "\n");
	}
	*/
}


