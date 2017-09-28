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

import util.Supplier;

public class GUI_ListOfSuppliers extends JPanel{
	
	private DefaultListModel<Supplier> supplierModel;
	private JList<Supplier> supplierList;
	private JScrollPane supplierScroll;
	
	private JLabel supplierLabel;
	private JTextField supplierTextField;
	
	private JButton cancelButton;
	private JButton searchButton;
	private JButton showAllButton;
	
	private JPanel container;
	private JPanel listPanel;
	private JPanel searchBySupplierPanel;
	private JPanel buttonPanel;
	
	public GUI_ListOfSuppliers(){
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
		supplierModel=new DefaultListModel<Supplier>();
		supplierList=new JList<Supplier>(supplierModel);
		supplierList.setVisibleRowCount(15);
		supplierList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		supplierList.setLayoutOrientation(JList.VERTICAL);
		supplierScroll=new JScrollPane(supplierList);
		supplierScroll.setPreferredSize(new Dimension(750, 200));
		listPanel.add(supplierScroll);
		container.add(listPanel,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		
		searchBySupplierPanel=new JPanel();
		supplierLabel=new JLabel("Supplier name:");
		supplierTextField=new JTextField(10);
		searchButton=new JButton("Search");
		searchBySupplierPanel.add(supplierLabel);
		searchBySupplierPanel.add(supplierTextField);
		searchBySupplierPanel.add(searchButton);
		
		container.add(searchBySupplierPanel,gbc);
		
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
	
	public DefaultListModel<Supplier> getsupplierModel() {
		return supplierModel;
	}

	public JList<Supplier> getsupplierList() {
		return supplierList;
	}

	public JTextField getsupplierTextField() {
		return supplierTextField;
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
