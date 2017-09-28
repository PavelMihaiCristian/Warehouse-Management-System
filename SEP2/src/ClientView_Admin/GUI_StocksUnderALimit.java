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

import util.Invoice;
import util.Product;

public class GUI_StocksUnderALimit extends JPanel{
	private DefaultListModel<Product> productsModel;
	private JList<Product> productsList;
	private JScrollPane productsScroll;
	
	private JLabel productLabel;
	private JTextField productTextField;
	
	private JLabel limitLabel;
	private JTextField limitTextField;
	
	private JButton cancelButton;
	private JButton searchButton;
	private JButton showAllButton;
	
	private JPanel container;
	private JPanel listPanel;
	private JPanel searchByProductPanel;
	private JPanel limitPanel;
	private JPanel buttonPanel;
	
	public GUI_StocksUnderALimit(){
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
		productsModel=new DefaultListModel<Product>();
		productsList=new JList<Product>(productsModel);
		productsList.setVisibleRowCount(15);
		productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productsList.setLayoutOrientation(JList.VERTICAL);
		productsScroll=new JScrollPane(productsList);
		productsScroll.setPreferredSize(new Dimension(750, 200));
		listPanel.add(productsScroll);
		container.add(listPanel,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		
		searchByProductPanel=new JPanel();
		productLabel=new JLabel("Product id");
		productTextField=new JTextField(10);
		searchButton=new JButton("Search");
		limitLabel=new JLabel("Stock under:");
		limitTextField=new JTextField(5);
		searchByProductPanel.add(productLabel);
		searchByProductPanel.add(productTextField);
		searchByProductPanel.add(limitLabel);
		searchByProductPanel.add(limitTextField);
		searchByProductPanel.add(searchButton);
		
		
		
		
		container.add(searchByProductPanel,gbc);
		
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

	public DefaultListModel<Product> getProductsModel() {
		return productsModel;
	}

	public JList<Product> getProductsList() {
		return productsList;
	}

	public JTextField getProductTextField() {
		return productTextField;
	}

	public JTextField getLimitTextField() {
		return limitTextField;
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
