package ClientView_Admin;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLayeredPane;

public class GUIMain extends JFrame {

	private JFrame frame;
	private JPanel firstPage;
	private DeleteProduct deleteProduct;

	private DeleteCategory deleteCategory;
	private GUI_NewProduct newProduct;
	private Admin_GUI_Product_Info productInfo;
	private GUI_SearchByExpiryDate expire;
	private ProductsOfCategory prodOfCat;
	
	
	private GUI_AddPicker addPickerPanel;
	private DeletePickerPanel deletePickerPanel;
	private GUI_ListOfPickers listOfPickersPanel;
	
	private JMenuBar menuBar;

	private JMenu manageGoods;

	
	private JMenu managePickers;
	

	private JMenuItem mntmAddNewProduct;
	private JMenuItem mntmRemoveProduct;
	private JMenuItem mntmRemoveCategory;
	private JMenuItem mntmCheckProductInfo;
	private JMenuItem mntmCheckProductExpiry;
	private JMenuItem mntmProductsOfCategory;

	
	private JMenuItem addPickerMenuItem;
	private JMenuItem deletePickerMenuItem;
	private JMenuItem showPickerList;
	
	//update 06/12
	private JMenu manageOrdersMenu;
	private JMenuItem availableOrdersItemMenu;
	private JMenuItem inProgressOrdetsItemMenu;
	
	private JMenu invoiceMenu;
	private JMenuItem invoiceMenuItem;
	
	private JMenu manageSupplierMenu;
	private JMenuItem addSupplierMenuItem;
	private JMenuItem listOfSuppliersMenuItem;
	
	private JMenu receiveGoodsMenu;
	private JMenuItem receiveGoodsMenuItem;
	private JMenuItem stockUnderMenuItem;
	
	private JMenu manageCustomersMenu;
	private JMenuItem addCustomersMenuItem;
	private JMenuItem listOfCustomersMenuItem;
	
	private GUI_AvailableOrders availableOrdersPanel;
	private GUI_InProgressOrders inProgressOrdersPanel;
	private GUI_OrderDetails ordersDetailsPanel;
	private GUI_ListOfInvoices invoicePanel;
	private GUI_AddSupplier addSupplierPanel;
	private GUI_ListOfSuppliers suppliersPanel;
	private GUI_RegisterGoods registerPanel;
	private GUI_StocksUnderALimit stockLimitPanel;
	private GUI_AddCustomer addCustomer;
	private GUI_ListOfCustomers customerList;
	//end

	private JPanel panel;

	private CardLayout cl;

	private ActionListener myAction;
	private ImageIcon logoIcon;
	private JLabel logoLabel;
	private Container overAllPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain window = new GUIMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIMain() {
		initialize();

	}

	// ******************************
	public DeleteProduct getDeleteProduct() {
		return deleteProduct;
	}

	public void setDeleteProduct(DeleteProduct deleteProduct) {
		this.deleteProduct = deleteProduct;
	}

	public ProductsOfCategory getProdOfCat() {
		return prodOfCat;
	}

	public DeleteCategory getDeleteCategory() {
		return deleteCategory;
	}

	public JMenuItem getMntmProductsOfCategory() {
		return mntmProductsOfCategory;
	}

	public void setDeleteCategory(DeleteCategory deleteCategory) {
		this.deleteCategory = deleteCategory;
	}

	public GUI_NewProduct getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(GUI_NewProduct newProduct) {
		this.newProduct = newProduct;
	}

	public Admin_GUI_Product_Info getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(Admin_GUI_Product_Info productInfo) {
		this.productInfo = productInfo;
	}

	public GUI_SearchByExpiryDate getExpire() {
		return expire;
	}

	public void setExpire(GUI_SearchByExpiryDate expire) {
		this.expire = expire;
	}
	public GUI_AddPicker getAddPickerPanel() {
		return addPickerPanel;
	}

	public DeletePickerPanel getDeletePickerPanel() {
		return deletePickerPanel;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		deleteCategory = new DeleteCategory();
		deleteProduct = new DeleteProduct();
		newProduct = new GUI_NewProduct();
		prodOfCat = new ProductsOfCategory();
		
		
		addPickerPanel=new GUI_AddPicker();
		deletePickerPanel=new DeletePickerPanel();
		listOfPickersPanel=new GUI_ListOfPickers();
		
		
		//update06/12
		availableOrdersPanel=new GUI_AvailableOrders();
		inProgressOrdersPanel=new GUI_InProgressOrders();
		
		invoicePanel=new GUI_ListOfInvoices();
		addSupplierPanel=new GUI_AddSupplier();
		suppliersPanel=new GUI_ListOfSuppliers();
		registerPanel=new GUI_RegisterGoods();
		stockLimitPanel=new GUI_StocksUnderALimit();
		addCustomer=new GUI_AddCustomer();
		customerList=new GUI_ListOfCustomers();
		//end
		
		// ***
		newProduct.getAddB().addActionListener(myAction);
		// **
		productInfo = new Admin_GUI_Product_Info();
		expire = new GUI_SearchByExpiryDate();

		cl = new CardLayout();
		myAction = new MyListener();

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		manageGoods = new JMenu("Manage products");
		menuBar.add(manageGoods);

		mntmAddNewProduct = new JMenuItem("Add new product/category");
		manageGoods.add(mntmAddNewProduct);
		mntmAddNewProduct.addActionListener(myAction);

		mntmRemoveProduct = new JMenuItem("Remove product");
		manageGoods.add(mntmRemoveProduct);
		mntmRemoveProduct.addActionListener(myAction);

		mntmRemoveCategory = new JMenuItem("Remove Category");
		manageGoods.add(mntmRemoveCategory);
		mntmRemoveCategory.addActionListener(myAction);

		mntmCheckProductInfo = new JMenuItem("Check product info");
		manageGoods.add(mntmCheckProductInfo);
		mntmCheckProductInfo.addActionListener(myAction);

		mntmCheckProductExpiry = new JMenuItem("Check product expiry date");
		manageGoods.add(mntmCheckProductExpiry);
		mntmCheckProductExpiry.addActionListener(myAction);

		mntmProductsOfCategory = new JMenuItem("View Products of a Category");// **
		manageGoods.add(mntmProductsOfCategory);// **
		mntmProductsOfCategory.addActionListener(myAction);
		
		managePickers = new JMenu("Manage pickers");
		menuBar.add(managePickers);
		
		addPickerMenuItem=new JMenuItem("Add picker");
		managePickers.add(addPickerMenuItem);
		addPickerMenuItem.addActionListener(myAction);
		
		deletePickerMenuItem=new JMenuItem("Delete picker");
		managePickers.add(deletePickerMenuItem);
		deletePickerMenuItem.addActionListener(myAction);
		
		showPickerList=new JMenuItem("Show picker lists");
		managePickers.add(showPickerList);
		showPickerList.addActionListener(myAction);
		
		//update 06/12
		manageSupplierMenu=new JMenu("Manage Suppliers");
		menuBar.add(manageSupplierMenu);
		
		addSupplierMenuItem=new JMenuItem("Add supplier");
		manageSupplierMenu.add(addSupplierMenuItem);
		addSupplierMenuItem.addActionListener(myAction);
		
		listOfSuppliersMenuItem=new JMenuItem("List Of Suppliers");
		manageSupplierMenu.add(listOfSuppliersMenuItem);
		listOfSuppliersMenuItem.addActionListener(myAction);
		
		manageCustomersMenu=new JMenu("Manage Customers");
		menuBar.add(manageCustomersMenu);
		
		addCustomersMenuItem=new JMenuItem("Add Customer");
		manageCustomersMenu.add(addCustomersMenuItem);
		addCustomersMenuItem.addActionListener(myAction);
		
		listOfCustomersMenuItem=new JMenuItem("List Of Customers");
		manageCustomersMenu.add(listOfCustomersMenuItem);
		listOfCustomersMenuItem.addActionListener(myAction);
		
		manageOrdersMenu=new JMenu("Manage Orders");
		menuBar.add(manageOrdersMenu);
		
		availableOrdersItemMenu=new JMenuItem("Available orders");
		manageOrdersMenu.add(availableOrdersItemMenu);
		availableOrdersItemMenu.addActionListener(myAction);
		
		inProgressOrdetsItemMenu=new JMenuItem("In Progress orders");
		manageOrdersMenu.add(inProgressOrdetsItemMenu);
		inProgressOrdetsItemMenu.addActionListener(myAction);
		
		invoiceMenu=new JMenu("View Invoices");
		menuBar.add(invoiceMenu);
		
		invoiceMenuItem=new JMenuItem("List Of Invoices");
		invoiceMenu.add(invoiceMenuItem);
		invoiceMenuItem.addActionListener(myAction);
		
		receiveGoodsMenu=new JMenu("Update stocks");
		menuBar.add(receiveGoodsMenu);
		
		receiveGoodsMenuItem=new JMenuItem("Receive Goods");
		receiveGoodsMenu.add(receiveGoodsMenuItem);
		receiveGoodsMenuItem.addActionListener(myAction);
		
		stockUnderMenuItem=new JMenuItem("Review Stocks");
		receiveGoodsMenu.add(stockUnderMenuItem);
		stockUnderMenuItem.addActionListener(myAction);
		//end
		
		panel = new JPanel(cl);

		overAllPanel = new JPanel(new BorderLayout());
		logoIcon = new ImageIcon("img/warehouse.jpg");
		logoLabel = new JLabel("", logoLabel.CENTER);
		logoLabel.setIcon(logoIcon);

		overAllPanel.add(logoLabel, BorderLayout.CENTER);

		panel.add(overAllPanel, "Start Page");
		panel.add(productInfo, "Product info");
		panel.add(newProduct, "Add new product");
		panel.add(deleteCategory, "Delete category");
		panel.add(deleteProduct, "Delete product");
		panel.add(expire, "Products close to expire date");
		panel.add(prodOfCat, "View Products Of A Category");
		panel.add(addPickerPanel,"Add picker");
		panel.add(deletePickerPanel,"Delete Picker");
		panel.add(listOfPickersPanel,"Show picker lists");
		
		//update 06/12/2016
		panel.add(availableOrdersPanel,"Available orders");
		panel.add(inProgressOrdersPanel,"In progress orders");
		panel.add(invoicePanel,"Invoices");
		panel.add(addSupplierPanel,"Add supplier");
		panel.add(suppliersPanel,"Supplier list");
		panel.add(registerPanel,"Receive goods");
		panel.add(stockLimitPanel,"StockLimit");
		panel.add(addCustomer,"Add Customer");
		panel.add(customerList,"Customer list");
		//end
		
		cl.show(panel, "Start Page");

		frame.add(panel);
		frame.setSize(1000, 800);
		frame.setVisible(true);
		frame.setResizable(true);

		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	//update 06/12
	public JMenuItem getAvailableOrdersItemMenu() {
		return availableOrdersItemMenu;
	}

	public JMenuItem getInProgressOrdetsItemMenu() {
		return inProgressOrdetsItemMenu;
	}

	public GUI_AvailableOrders getAvailableOrdersPanel() {
		return availableOrdersPanel;
	}

	public GUI_InProgressOrders getInProgressOrdersPanel() {
		return inProgressOrdersPanel;
	}
	
	public GUI_ListOfInvoices getInvoicesPanel(){
		return invoicePanel;
	}
	public GUI_AddSupplier getAddSupplierPanel(){
		return addSupplierPanel;
	}
	public GUI_ListOfPickers getListOfPickersPanel() {
		return listOfPickersPanel;
	}
	public GUI_ListOfSuppliers getListOfSupliers(){
		return suppliersPanel;
	}
	public GUI_RegisterGoods getRegisterGoodsPanel(){
		return registerPanel;
	}
	public JMenuItem getRegisterGoodsMenuItem(){
		return receiveGoodsMenuItem;
	}
	public JMenuItem getStockUnderMenuItem() {
		return stockUnderMenuItem;
	}
	public GUI_StocksUnderALimit getStockUnderPanel(){
		return stockLimitPanel;
	}
	public GUI_AddCustomer getCustomerPanel(){
		return addCustomer;
	}
	public GUI_ListOfCustomers getCustomerList(){
		return customerList;
	}
	//end
	
	public JMenuItem getShowPickerList() {
		return showPickerList;
	}
	public JMenuItem getMntmAddNewProduct() {
		return mntmAddNewProduct;
	}

	public JMenuItem getMntmRemoveProduct() {
		return mntmRemoveProduct;
	}

	public JMenuItem getMntmRemoveCategory() {
		return mntmRemoveCategory;
	}

	public JMenuItem getMntmCheckProductInfo() {
		return mntmCheckProductInfo;
	}

	public JMenuItem getMntmCheckProductExpiry() {
		return mntmCheckProductExpiry;
	}

	public void showErrorMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void switchToMain() {
		cl.show(panel, "Start Page");
	}

	private class MyListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mntmAddNewProduct) {
				cl.show(panel, "Add new product");
			}
			if (e.getSource() == mntmRemoveProduct) {
				cl.show(panel, "Delete product");
			}
			if (e.getSource() == mntmRemoveCategory) {
				cl.show(panel, "Delete category");
			}
			if (e.getSource() == mntmCheckProductInfo) {
				cl.show(panel, "Product info");
			}
			if (e.getSource() == mntmCheckProductExpiry) {
				cl.show(panel, "Products close to expire date");
			}
			if (e.getSource() == mntmProductsOfCategory) {
				cl.show(panel, "View Products Of A Category");
			}
			if(e.getSource()==addPickerMenuItem){
				cl.show(panel,"Add picker");
			}
			if(e.getSource()==deletePickerMenuItem){
				cl.show(panel,"Delete Picker");
			}
			if(e.getSource()==showPickerList){
				cl.show(panel,"Show picker lists");
			}
			//update 06/12/2017
			
			if(e.getSource()==availableOrdersItemMenu){
				cl.show(panel,"Available orders");
			}
			if(e.getSource()==inProgressOrdetsItemMenu){
				cl.show(panel,"In progress orders");
			}
			if(e.getSource()==invoiceMenuItem){
				cl.show(panel, "Invoices");
			}
			if(e.getSource()==addSupplierMenuItem){
				cl.show(panel, "Add supplier");
			}
			if(e.getSource()==listOfSuppliersMenuItem){
				cl.show(panel, "Supplier list");
			}
			if(e.getSource()==receiveGoodsMenuItem){
				cl.show(panel, "Receive goods");
			}
			if(e.getSource()==stockUnderMenuItem){
				cl.show(panel, "StockLimit");
			}
			if(e.getSource()==addCustomersMenuItem){
				cl.show(panel, "Add Customer");
			}
			if(e.getSource()==listOfCustomersMenuItem){
				cl.show(panel, "Customer list");
			}
			//end
		}

	}

	public int confirmDelete() {
		return JOptionPane.showConfirmDialog(null,"Are you sure you want to remove the picker?","Confirm Removel of Picker",JOptionPane.YES_NO_CANCEL_OPTION);
	}

}
