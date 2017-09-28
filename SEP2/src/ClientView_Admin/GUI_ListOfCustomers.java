package ClientView_Admin;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import util.Customer;
import util.Supplier;

public class GUI_ListOfCustomers extends JPanel{
	private DefaultListModel<Customer> customerModel;
	private JList<Customer> customerList;
	private JScrollPane customerScroll;
	
	private JLabel customerLabel;
	private JTextField customerTextField;
	
	private JButton cancelButton;
	private JButton searchButton;
	private JButton showAllButton;
	
	private JPanel container;
	private JPanel listPanel;
	private JPanel searchByCustomerPanel;
	private JPanel buttonPanel;
	
	public GUI_ListOfCustomers(){
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
		customerModel=new DefaultListModel<Customer>();
		customerList=new JList<Customer>(customerModel);
		customerList.setVisibleRowCount(15);
		customerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerList.setLayoutOrientation(JList.VERTICAL);
		customerScroll=new JScrollPane(customerList);
		customerScroll.setPreferredSize(new Dimension(750, 200));
		listPanel.add(customerScroll);
		container.add(listPanel,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		
		searchByCustomerPanel=new JPanel();
		customerLabel=new JLabel("Customer Id:");
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
	
	public DefaultListModel<Customer> getcustomerModel() {
		return customerModel;
	}

	public JList<Customer> getcustomerList() {
		return customerList;
	}

	public JTextField getcustomerTextField() {
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
