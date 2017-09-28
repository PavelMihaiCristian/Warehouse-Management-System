package ClientView_Admin;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import util.Invoice;

public class GUI_ListOfInvoices extends JPanel{

	private DefaultListModel<Invoice> invoiceModel;
	private JList<Invoice> invoiceList;
	private JScrollPane invoiceScroll;
	
	private JLabel customerLabel;
	private JTextField customerTextField;
	
	private JButton cancelButton;
	private JButton searchButton;
	private JButton showAllButton;
	
	private JPanel container;
	private JPanel listPanel;
	private JPanel searchByCustomerPanel;
	private JPanel buttonPanel;
	
	public GUI_ListOfInvoices(){
		initiate();
	}

	private void initiate() {
		container=new JPanel();
		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.insets=new  Insets(10, 10, 10, 10);
		
		gbc.gridx=0;
		gbc.gridy=0;
		
		listPanel=new JPanel();
		invoiceModel=new DefaultListModel<Invoice>();
		invoiceList=new JList<Invoice>(invoiceModel);
		invoiceList.setVisibleRowCount(15);
		invoiceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		invoiceList.setLayoutOrientation(JList.VERTICAL);
		invoiceScroll=new JScrollPane(invoiceList);
		invoiceScroll.setPreferredSize(new Dimension(750, 200));
		listPanel.add(invoiceScroll);
		container.add(listPanel,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		
		searchByCustomerPanel=new JPanel();
		customerLabel=new JLabel("Customer id");
		customerTextField=new JTextField(10);
		searchButton=new JButton("Search");
		searchByCustomerPanel.add(customerLabel);
		searchByCustomerPanel.add(customerTextField);
		searchByCustomerPanel.add(searchButton);
		
		container.add(searchByCustomerPanel,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		buttonPanel=new JPanel();
		showAllButton=new JButton("Show All");
		cancelButton=new JButton("Cancel");
		buttonPanel.add(showAllButton);
		buttonPanel.add(cancelButton);
		
		container.add(buttonPanel,gbc);
		
		add(container);
		setVisible(true);
	}
	
	public DefaultListModel<Invoice> getInvoiceModel() {
		return invoiceModel;
	}

	public JList<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public JTextField getCustomerTextField() {
		return customerTextField;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getShowAllButton() {
		return showAllButton;
	}
}
