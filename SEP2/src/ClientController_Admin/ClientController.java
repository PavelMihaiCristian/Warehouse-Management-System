package ClientController_Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.postgresql.util.PSQLException;

import ClientModel.ClientModel;
import ClientView_Admin.Admin_GUI_Product_Info;
import ClientView_Admin.ClientView;
import ClientView_Admin.DeleteCategory;
import ClientView_Admin.DeletePickerPanel;
import ClientView_Admin.DeleteProduct;
import ClientView_Admin.GUIMain;
import ClientView_Admin.GUI_AddCustomer;
import ClientView_Admin.GUI_AddPicker;
import ClientView_Admin.GUI_AddSupplier;
import ClientView_Admin.GUI_AvailableOrders;
import ClientView_Admin.GUI_InProgressOrders;
import ClientView_Admin.GUI_ListOfCustomers;
import ClientView_Admin.GUI_ListOfInvoices;
import ClientView_Admin.GUI_ListOfPickers;
import ClientView_Admin.GUI_ListOfSuppliers;
import ClientView_Admin.GUI_NewProduct;
import ClientView_Admin.GUI_OrderDetails;
import ClientView_Admin.GUI_RegisterGoods;
import ClientView_Admin.GUI_SearchByExpiryDate;
import ClientView_Admin.GUI_StocksUnderALimit;
import ClientView_Admin.GUI_SuppliersProducts;
import ClientView_Admin.ProductsOfCategory;
import ServerController.ServerInterface;
import util.Customer;
import util.Date;
import util.Invoice;
import util.Item;
import util.Order;
import util.Pallet;
import util.Pickers;
import util.PickersSchedule;
import util.Product;
import util.Supplier;
import utility.observer.RemoteObserver;
import utility.observer.RemoteSubject;

public class ClientController implements RemoteObserver<String> {

	private ServerInterface server;

	private GUIMain view;
	private ClientModel model;
	// ***Panels
	private GUI_NewProduct NewProd;
	private ProductsOfCategory prodOfCat;
	private DeleteCategory deleteCat;
	private DeleteProduct delPro;
	private Admin_GUI_Product_Info prodInfor;
	private GUI_SearchByExpiryDate expiredBefore;
	private MyListener listener;

	private GUI_AddPicker addPickerPage;
	private GUI_ListOfPickers showPickersPage;
	private DeletePickerPanel deletePickerPage;

	// update 06/12/2016
	private GUI_AvailableOrders availableOrdersPage;
	private GUI_InProgressOrders inProgressOrdersPage;
	private MyListSelection listListener;
	private GUI_OrderDetails details;

	private GUI_ListOfInvoices invoicesPage;
	private GUI_AddSupplier addSupplierPage;
	private GUI_ListOfSuppliers listSuppliersPage;
	private GUI_SuppliersProducts supplierProductsPage;
	private GUI_RegisterGoods registerGoodsPage;
	private GUI_StocksUnderALimit stockPage;
	private GUI_AddCustomer addCustomerPage;
	private GUI_ListOfCustomers customerListPage;

	// end

	public ClientController(GUIMain view, ClientModel model)
			throws RemoteException {
		connect();
		System.out.println("Client is connected");
		this.view = view;
		this.model = model;
		// **
		NewProd = view.getNewProduct();
		deleteCat = view.getDeleteCategory();
		delPro = view.getDeleteProduct();
		prodInfor = view.getProductInfo();
		expiredBefore = view.getExpire();
		prodOfCat = view.getProdOfCat();

		addPickerPage = view.getAddPickerPanel();
		deletePickerPage = view.getDeletePickerPanel();
		showPickersPage = view.getListOfPickersPanel();

		// update 06/12/2016
		availableOrdersPage = view.getAvailableOrdersPanel();
		inProgressOrdersPage = view.getInProgressOrdersPanel();
		invoicesPage = view.getInvoicesPanel();
		addSupplierPage = view.getAddSupplierPanel();
		listSuppliersPage = view.getListOfSupliers();
		registerGoodsPage = view.getRegisterGoodsPanel();
		stockPage = view.getStockUnderPanel();
		addCustomerPage = view.getCustomerPanel();
		customerListPage = view.getCustomerList();
		// end

		listener = new MyListener();
		listListener = new MyListSelection();
		addListeners();
	}

	private void connect() {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		try {
			server = (ServerInterface) Naming
					.lookup("rmi://localhost:1099/Warehouse");
			UnicastRemoteObject.exportObject(this, 0);
			server.addObserver(this);
		} catch (Exception e) {
			System.out.println("SERVER CONNECTION FAILED");
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void addListeners() {
		NewProd.getAddB().addActionListener(listener);
		NewProd.getCcategory().addActionListener(listener);
		view.getMntmAddNewProduct().addActionListener(listener);
		NewProd.getCancelButton().addActionListener(listener);
		delPro.getCancelButton().addActionListener(listener);
		delPro.getDeleteButton().addActionListener(listener);

		view.getMntmRemoveCategory().addActionListener(listener);
		deleteCat.getCancelButton().addActionListener(listener);
		deleteCat.getRemoveButton().addActionListener(listener);

		prodOfCat.getCancelButton().addActionListener(listener);
		prodOfCat.getshowProdButton().addActionListener(listener);
		view.getMntmProductsOfCategory().addActionListener(listener);

		prodInfor.getCancelButton().addActionListener(listener);
		prodInfor.getsearch().addActionListener(listener);
		expiredBefore.getCancelButton().addActionListener(listener);
		expiredBefore.getSearchButton().addActionListener(listener);
		prodOfCat.getProductsList().addListSelectionListener(listListener);

		addPickerPage.getAddB().addActionListener(listener);
		addPickerPage.getCancelButton().addActionListener(listener);
		deletePickerPage.getDeletePickerButton().addActionListener(listener);
		deletePickerPage.getCancelButton().addActionListener(listener);
		showPickersPage.getBcancel().addActionListener(listener);
		showPickersPage.getBsearch().addActionListener(listener);
		view.getShowPickerList().addActionListener(listener);
		showPickersPage.getJLatWork().addListSelectionListener(listListener);
		showPickersPage.getJLregisterd().addListSelectionListener(listListener);

		// update 06/12/2016
		availableOrdersPage.getBcancel().addActionListener(listener);
		inProgressOrdersPage.getBcancel().addActionListener(listener);
		view.getAvailableOrdersItemMenu().addActionListener(listener);
		view.getInProgressOrdetsItemMenu().addActionListener(listener);
		availableOrdersPage.getJLorders()
				.addListSelectionListener(listListener);
		inProgressOrdersPage.getJLorders().addListSelectionListener(
				listListener);

		invoicesPage.getInvoiceList().addListSelectionListener(listListener);
		invoicesPage.getCancelButton().addActionListener(listener);
		invoicesPage.getSearchButton().addActionListener(listener);
		invoicesPage.getShowAllButton().addActionListener(listener);
		addSupplierPage.getCancelButton().addActionListener(listener);
		addSupplierPage.getAddButton().addActionListener(listener);
		listSuppliersPage.getShowAllButton().addActionListener(listener);
		listSuppliersPage.getCancelButton().addActionListener(listener);
		listSuppliersPage.getSearchButton().addActionListener(listener);
		listSuppliersPage.getsupplierList().addListSelectionListener(
				listListener);
		registerGoodsPage.getAddButton().addActionListener(listener);
		registerGoodsPage.getCancelButton().addActionListener(listener);
		view.getRegisterGoodsMenuItem().addActionListener(listener);
		stockPage.getCancelButton().addActionListener(listener);
		stockPage.getSearchButton().addActionListener(listener);
		stockPage.getShowAllButton().addActionListener(listener);
		stockPage.getProductsList().addListSelectionListener(listListener);
		view.getStockUnderMenuItem().addActionListener(listener);
		addCustomerPage.getAddButton().addActionListener(listener);
		addCustomerPage.getCancelButton().addActionListener(listener);
		customerListPage.getCancelButton().addActionListener(listener);
		customerListPage.getShowAllButton().addActionListener(listener);
		customerListPage.getSearchButton().addActionListener(listener);
		// end
	}

	public void update(RemoteSubject<String> arg0, String updateMsg)
			throws RemoteException {
		System.out.println("got an update: " + updateMsg + " @ "
				+ (new Date().toString()));

		if (updateMsg.equals("New Product Added")) {
			listener.showProductsOfCategory();
		} else if (updateMsg.equals("Category Added")) {
			listener.populateComboCategory();
		} else if (updateMsg.equals("Product Deleted")) {
			listener.showProductsOfCategory();
		} else if (updateMsg.equals("Category Deleted")) {
			listener.populateComboCategory();
		} else if (updateMsg.equals("Picker LoggedIn")) {
			listener.showPickersList();
		} else if (updateMsg.equals("Picker LoggedOut")) {
			listener.showPickersList();
		} else if (updateMsg.equals("Picker Added")) {
			listener.showPickersList();
		} else if (updateMsg.equals("Picker Deleted")) {
			listener.showPickersList();
		} else if (updateMsg.equals("Supplier Added")) {
			listener.populateComboSupplier();
			listSuppliersPage.getShowAllButton().doClick();
		} else if (updateMsg.equals("Customer Added")) {
			customerListPage.getShowAllButton().doClick();
		} else if (updateMsg.equals("Order Made")) {
			listener.showAvailableOrders();
		} else if (updateMsg.equals("Order Started")) {
			listener.showAvailableOrders();
			listener.showInProgressOrders();
		} else if (updateMsg.equals("order Finished")) {
			listener.showInProgressOrders();
			listener.showInvoices();
		} else if (updateMsg.equals("Picked Items Updated")) {

		} else if (updateMsg.equals("Order Paused")) {
			listener.showAvailableOrders();
			listener.showInProgressOrders();
		} else if (updateMsg.equals("Pallets Registered")) {

			listener.productInformation();
			listener.expiredBefore();
			if ((stockPage.getShowAllButton().isFocusOwner() || stockPage
					.getProductsList().isFocusOwner())
					&& stockPage.getProductTextField().getText().equals("")
					&& !(stockPage.getLimitTextField().getText().equals(""))) {
				stockPage.getShowAllButton().doClick();
			}
			if ((stockPage.getSearchButton().isFocusOwner() || stockPage
					.getProductsList().isFocusOwner())
					&& !(stockPage.getProductTextField().getText().equals(""))
					&& !(stockPage.getLimitTextField().getText().equals(""))) {
				stockPage.getSearchButton().doClick();
			}

		}

	}

	class MyListSelection implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (prodOfCat.getProductsList().getSelectedValue() != null) {
				Product s = (Product) prodOfCat.getProductsList()
						.getSelectedValue();
				ArrayList<Pallet> ps = new ArrayList<Pallet>();

				try {
					ps = server.getAllPalletsForOneProduct(s.getID());
				} catch (NumberFormatException | RemoteException e1) {
					e1.printStackTrace();
				}
				String collecter = "";
				for (int i = 0; i < ps.size(); i++) {
					collecter += ps.get(i);
					collecter += "\n";
				}
				view.showErrorMessage(collecter);
				prodOfCat.getProductsList().clearSelection();
			}
			if (showPickersPage.getJLregisterd().getSelectedValue() != null) {
				Pickers p = (Pickers) showPickersPage.getJLregisterd()
						.getSelectedValue();
				view.showErrorMessage(p.getId() + "\n" + p.getName() + "\n"
						+ p.getCPR() + "\n" + p.getPostCode() + "\n"
						+ p.getStreet() + "\n" + p.getPhoneNo() + "\n"
						+ p.getEmail());
				showPickersPage.getJLregisterd().clearSelection();
			}
			if (showPickersPage.getJLatWork().getSelectedValue() != null) {
				PickersSchedule pick = (PickersSchedule) showPickersPage
						.getJLatWork().getSelectedValue();
				try {
					view.showErrorMessage(server.findPicker(pick.getID())
							.toString2());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				showPickersPage.getJLatWork().clearSelection();
			}

			// update 06/12
			if (availableOrdersPage.getJLorders().getSelectedValue() != null) {
				Order order = (Order) availableOrdersPage.getJLorders()
						.getSelectedValue();
				long orderNr = order.getOrderNo();
				availableOrdersPage.getJLorders().clearSelection();
				System.out.println(order);
				try {
					ArrayList<Item> itemList = server.getItemsForOrder(orderNr);
					if (itemList.size() == 0) {
						view.showErrorMessage("Empty order. No items found");
					} else {
						details = new GUI_OrderDetails(orderNr + "");
						for (int i = 0; i < itemList.size(); i++) {
							details.getItemsModel().addElement(itemList.get(i));
						}
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (inProgressOrdersPage.getJLorders().getSelectedValue() != null) {
				Order order = (Order) inProgressOrdersPage.getJLorders()
						.getSelectedValue();
				long orderNr = order.getOrderNo();
				inProgressOrdersPage.getJLorders().clearSelection();
				System.out.println(order);
				try {
					ArrayList<Item> itemList = server.getItemsForOrder(orderNr);
					if (itemList.size() == 0) {
						view.showErrorMessage("Empty order. No items found");
					} else {
						ArrayList<Integer> pickerIds = server
								.getAllPickersPickedAnOrder(orderNr);
						String ids = " Is/has been picked by picker(s) with the id(s): ";
						for (int i = 0; i < pickerIds.size(); i++) {
							ids += pickerIds.get(i) + " / ";
						}
						details = new GUI_OrderDetails(orderNr + ids);
						for (int i = 0; i < itemList.size(); i++) {
							details.getItemsModel().addElement(itemList.get(i));
						}
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (invoicesPage.getInvoiceList().getSelectedValue() != null) {
				Invoice invoice = (Invoice) invoicesPage.getInvoiceList()
						.getSelectedValue();
				long invoiceNo = invoice.getOrderNumber();
				invoicesPage.getInvoiceList().clearSelection();
				System.out.println(invoiceNo);
				try {
					ArrayList<Item> itemList = server
							.getItemsForInvoice(invoiceNo);
					if (itemList.size() == 0) {
						view.showErrorMessage("Empty order. No items found");
					} else {
						details = new GUI_OrderDetails(invoiceNo + "");
						ArrayList<Integer> pickerIds = server
								.getAllPickersPickedAnOrder(invoiceNo);
						// int pickerId = server.getPickerIdForOrder(invoiceNo);
						if (pickerIds.size() == 0) {
							view.showErrorMessage("No picker id found for this order");
						} else {
							String ids = "";
							for (int i = 0; i < pickerIds.size(); i++) {
								ids += pickerIds.get(i) + " / ";
							}
							details.getMessage().setText("Picker id is:" + ids);
							for (int i = 0; i < itemList.size(); i++) {
								details.getItemsModel().addElement(
										itemList.get(i));
							}
						}
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (listSuppliersPage.getsupplierList().getSelectedValue() != null) {
				Supplier supplierTemp = (Supplier) listSuppliersPage
						.getsupplierList().getSelectedValue();
				listSuppliersPage.getsupplierList().clearSelection();
				String supplierName = supplierTemp.getSupplierName();
				try {
					ArrayList<Product> supplierProducts = server
							.getAllProductsFromSupplier(supplierName);
					if (supplierProducts.size() == 0) {
						view.showErrorMessage("The supplier does not supply any products");
					} else {
						supplierProductsPage = new GUI_SuppliersProducts(
								"Products supplied by:" + supplierName);
						for (int i = 0; i < supplierProducts.size(); i++) {
							supplierProductsPage.getModel().addElement(
									supplierProducts.get(i));
						}
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (stockPage.getProductsList().getSelectedValue() != null) {
				Product temp = stockPage.getProductsList().getSelectedValue();
				int id = temp.getID();
				long stock;
				try {
					stock = server.getStockForProduct(id);
					if (stock == -1) {
						view.showErrorMessage("Stock for the selected product is 0");
					} else {
						view.showErrorMessage("Stock for selected product is "
								+ stock + " boxes");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				stockPage.getProductsList().clearSelection();
			}
			// end
		}
	}

	private class MyListener implements ActionListener {
		// ************************************************************************
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == prodOfCat.getCancelButton()) {
				view.switchToMain();
				prodOfCat.getListModel().clear();// new code
			}
			if (e.getSource() == view.getMntmProductsOfCategory()) {
				populateComboCategory();
			}

			if (e.getSource() == prodOfCat.getshowProdButton()) {
				showProductsOfCategory();
			}

			if (e.getSource() == prodInfor.getCancelButton()) {
				view.switchToMain();
				prodInfor.getTextArea().setText("");
				prodInfor.getTextField().setText("");
			}

			if (e.getSource() == prodInfor.getsearch()) {
				productInformation();
			}

			if (e.getSource() == expiredBefore.getCancelButton()) {
				view.switchToMain();
				expiredBefore.getTshowExpiry().setText("");
				expiredBefore.resetDateToTodaysDay();
			}
			if (e.getSource() == expiredBefore.getSearchButton()) {
				expiredBefore();
			}

			if (e.getSource() == NewProd.getCancelButton()) {
				view.switchToMain();
			}
			if (e.getSource() == deleteCat.getCancelButton()) {
				view.switchToMain();
			}
			if (e.getSource() == delPro.getCancelButton()) {
				view.switchToMain();
			}
			if (e.getSource() == delPro.getDeleteButton()) {
				try {
					if (server.deleteProduct(Integer.parseInt(delPro
							.getTextField().getText()))) {
						view.showErrorMessage("Product Successfully Deleted");
						delPro.getTextField().setText("");
					} else {
						view.showErrorMessage("Couldn't Delete Product. \n-Pallets exist/Product ID inccorect");
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == view.getMntmAddNewProduct()) {
				populateComboCategory();
				populateComboSupplier();
			}
			if (e.getSource() == view.getMntmRemoveCategory()) {
				populateComboCategory();
			}
			if (e.getSource() == deleteCat.getRemoveButton()) {
				try {
					if (server.deleteCategory((String) deleteCat
							.GetCategorycomboBox().getSelectedItem())) {
						view.showErrorMessage("Category Successfully Deleted");
						populateComboCategory();
					} else {
						view.showErrorMessage("Couldn't Delete Category");

					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == NewProd.getCcategory()) {
				if (NewProd.getCcategory().getSelectedItem() != null)
					if (((String) NewProd.getCcategory().getSelectedItem())
							.equals("Add Category")) {
						NewProd.getTCategory().setEditable(true);
					} else {
						NewProd.getTCategory().setEditable(false);
					}

			}
			if (e.getSource() == view.getNewProduct().getAddB()) {
				if (((String) NewProd.getCcategory().getSelectedItem())
						.equals("Add Category")
						&& NewProd.getTCategory().getText().equals("")
						|| NewProd.getTname().getText().equals("")
						|| NewProd.getTproductID().getText().equals("")) {

					view.showErrorMessage("Fill all/valid information");
				} else {

					try {
						if (((String) NewProd.getCcategory().getSelectedItem())
								.equals("Add Category")) {
							if (server.addCategory((String) NewProd
									.getTCategory().getText())) {
								if (server.addNewProduct(Integer
										.parseInt(NewProd.getTproductID()
												.getText()), (String) NewProd
										.getTname().getText(), (String) NewProd
										.getTCategory().getText())) {
									if (server
											.delegateSupplierForProduct(Integer
													.parseInt(NewProd
															.getTproductID()
															.getText()),
													(String) NewProd
															.getSuppliers()
															.getSelectedItem())) {
										view.showErrorMessage("Supplier added to the product");
									} else {
										view.showErrorMessage("Supplier was not added to the product");
									}
									view.showErrorMessage("Product succesfully added");
									NewProd.getTCategory().setText("");
									NewProd.getCcategory().setSelectedIndex(0);
									NewProd.getTproductID().setText("");
									NewProd.getTname().setText("");
									populateComboCategory();
								} else {
									view.showErrorMessage("Adding new product failed");
								}
							} else {
								view.showErrorMessage("Adding new category failed");
							}

						} else {
							if (server.addNewProduct(Integer.parseInt(NewProd
									.getTproductID().getText()),
									(String) NewProd.getTname().getText(),
									(String) (String) NewProd.getCcategory()
											.getSelectedItem())) {
								if (server.delegateSupplierForProduct(Integer
										.parseInt(NewProd.getTproductID()
												.getText()), (String) NewProd
										.getSuppliers().getSelectedItem())) {
									view.showErrorMessage("Supplier added to the product");
								} else {
									view.showErrorMessage("Supplier was not added to the product");
								}
								view.showErrorMessage("Product succesfully added");
								NewProd.getTCategory().setText("");
								NewProd.getCcategory().setSelectedIndex(0);
								NewProd.getTproductID().setText("");
								NewProd.getTname().setText("");
							} else {
								view.showErrorMessage("Adding new product failed");
							}

						}
					} catch (NumberFormatException | RemoteException e1) {
						e1.printStackTrace();
					}

				}
			}
			if (e.getSource() == addPickerPage.getCancelButton()) {
				view.switchToMain();
				resetAddPickerPage();
			}
			if (e.getSource() == addPickerPage.getAddB()) {
				if (addPickerPage.getTCpr().getText().equals("")
						|| addPickerPage.getTEmail().getText().equals("")
						|| addPickerPage.getTName().equals("")
						|| addPickerPage.getTPhone().equals("")
						|| addPickerPage.getTStreet().equals("")
						|| addPickerPage.getTPostCode().equals("")) {
					view.showErrorMessage("Fill out all data fields");
				} else {
					try {
						long CPR = Long.parseLong(addPickerPage.getTCpr()
								.getText());
						int postCode = Integer.parseInt(addPickerPage
								.getTPostCode().getText());
						long phoneNo = Long.parseLong(addPickerPage.getTPhone()
								.getText());
						String name = addPickerPage.getTName().getText();
						String email = addPickerPage.getTEmail().getText();
						String street = addPickerPage.getTStreet().getText();
						Pickers picker = new Pickers(name, CPR, street,
								postCode, phoneNo, email);
						if (server.addPicker(picker)) {
							view.showErrorMessage("Picker added succesfully");
							resetAddPickerPage();
						} else {
							view.showErrorMessage("Picker added failed");
						}

					} catch (RemoteException e1) {
						e1.printStackTrace();
					} catch (NumberFormatException e1) {
						view.showErrorMessage("CPR,PostCode or PhoneNo is not a number");
					}

				}

			}
			if (e.getSource() == deletePickerPage.getCancelButton()) {
				view.switchToMain();
				resetDeletePickerPage();
			}
			if (e.getSource() == deletePickerPage.getDeletePickerButton()) {
				if (deletePickerPage.getPickerIdTextField().getText()
						.equals("")) {
					view.showErrorMessage("Picker Id not filled");
				} else {
					try {
						Pickers picker = server.findPicker(Integer
								.parseInt(deletePickerPage
										.getPickerIdTextField().getText()));
						if (picker != null) {
							deletePickerPage.getPickerInfoTextField().setText(
									picker.toString());
							int option = view.confirmDelete();
							if (option == JOptionPane.YES_OPTION) {
								if (server.deletePicker(Integer
										.parseInt(deletePickerPage
												.getPickerIdTextField()
												.getText()))) {
									view.showErrorMessage("Picker succesfully deleted");
									resetDeletePickerPage();
								}else{
									view.showErrorMessage("Picker could not be deleted");
								}
							} else if (option == JOptionPane.NO_OPTION) {
								resetDeletePickerPage();
							} else {
								resetDeletePickerPage();
							}
						} else {
							view.showErrorMessage("No picker found with the given id");
						}
					} catch (NumberFormatException e1) {
						view.showErrorMessage("Picker id is invalid");
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
			if (e.getSource() == showPickersPage.getBcancel()) {
				view.switchToMain();
			}
			if (e.getSource() == view.getShowPickerList()) {
				showPickersList();
			}
			if (e.getSource() == showPickersPage.getBsearch()) {
				String name = showPickersPage.getTsearch().getText();
				ArrayList<Pickers> listPickers;
				try {
					listPickers = server.getAllPickersByName(name);
					if (listPickers.size() > 0) {
						view.showErrorMessage(model
								.getAllPickersByName(listPickers));
					} else {
						view.showErrorMessage("No picker with that name");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			// update 06/12/2016
			if (e.getSource() == availableOrdersPage.getBcancel()) {
				view.switchToMain();
				availableOrdersPage.getOrdersModel().clear();
			}
			if (e.getSource() == view.getAvailableOrdersItemMenu()) {
				showAvailableOrders();
			}
			if (e.getSource() == view.getInProgressOrdetsItemMenu()) {
				showInProgressOrders();
			}
			if (e.getSource() == inProgressOrdersPage.getBcancel()) {
				view.switchToMain();
				inProgressOrdersPage.getInProgressOrderModel().clear();
			}

			if (e.getSource() == invoicesPage.getCancelButton()) {
				view.switchToMain();
				invoicesPage.getInvoiceModel().clear();
			}
			if (e.getSource() == invoicesPage.getSearchButton()) {
				invoicesPage.getInvoiceModel().clear();
				if (invoicesPage.getCustomerTextField().getText().equals("")) {
					view.showErrorMessage("Fill in customers id");
				} else {
					try {
						int customerId = Integer.parseInt(invoicesPage
								.getCustomerTextField().getText());
						try {
							ArrayList<Invoice> list = server
									.getCustomerInvoices(customerId);
							if (list.size() == 0) {
								view.showErrorMessage("The client does not have any invoices");
							} else {
								for (int i = 0; i < list.size(); i++) {
									invoicesPage.getInvoiceModel().addElement(
											list.get(i));
								}
							}
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					} catch (NumberFormatException x) {
						view.showErrorMessage("Customer id is not a number");
					}
				}
			}
			if (e.getSource() == invoicesPage.getShowAllButton()) {
				showInvoices();
			}
			if (e.getSource() == addSupplierPage.getCancelButton()) {
				view.switchToMain();
				resetAddSupplierPage();
			}
			if (e.getSource() == addSupplierPage.getAddButton()) {
				if (addSupplierPage.getSupplierNameText().getText().equals("")
						|| addSupplierPage.getStreetText().getText().equals("")
						|| addSupplierPage.getPostCodeText().getText()
								.equals("")
						|| addSupplierPage.getPhoneNumberText().getText()
								.equals("")
						|| addSupplierPage.getEmailText().getText().equals("")) {
					view.showErrorMessage("Fill out all data fields");
				} else {
					try {
						String supplierName = addSupplierPage
								.getSupplierNameText().getText();
						String street = addSupplierPage.getStreetText()
								.getText();
						int postalCode = Integer.parseInt(addSupplierPage
								.getPostCodeText().getText());
						int phoneNumber = Integer.parseInt(addSupplierPage
								.getPhoneNumberText().getText());
						String email = addSupplierPage.getEmailText().getText();
						Supplier supplier = new Supplier(supplierName,
								phoneNumber, email, street, postalCode);
						if (server.addSupplier(supplier)) {
							view.showErrorMessage("Supplier added succesfully");
							resetAddSupplierPage();
						} else {
							view.showErrorMessage("Supplier added failed");
						}

					} catch (RemoteException e1) {
						e1.printStackTrace();
					} catch (NumberFormatException e1) {
						view.showErrorMessage("PostCode or PhoneNo is not a number");
					}

				}

			}
			if (e.getSource() == listSuppliersPage.getCancelButton()) {
				listSuppliersPage.getsupplierModel().clear();
				view.switchToMain();
			}
			if (e.getSource() == listSuppliersPage.getShowAllButton()) {
				listSuppliersPage.getsupplierModel().clear();
				try {
					ArrayList<Supplier> list = server.getAllSuppliers();
					if (list.size() == 0) {
						view.showErrorMessage("No suppliers found");
					} else {
						for (int i = 0; i < list.size(); i++) {
							listSuppliersPage.getsupplierModel().addElement(
									list.get(i));
						}
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == listSuppliersPage.getSearchButton()) {
				listSuppliersPage.getsupplierModel().clear();
				if (listSuppliersPage.getsupplierTextField().getText()
						.equals("")) {
					view.showErrorMessage("No supplier name filled");
				} else {
					String supplierName = listSuppliersPage
							.getsupplierTextField().getText();
					try {
						Supplier supplier = server
								.getSupplierByName(supplierName);
						if (supplier == null) {
							view.showErrorMessage("No supplier found with that name");
						} else {
							listSuppliersPage.getsupplierModel().addElement(
									supplier);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
			if (e.getSource() == registerGoodsPage.getCancelButton()) {
				resetRegisterGoodPage();
				view.switchToMain();
			}
			if (e.getSource() == view.getRegisterGoodsMenuItem()) {
				resetRegisterGoodPage();
				populateComboCategory();
			}
			if (e.getSource() == registerGoodsPage.getAddButton()) {
				if (registerGoodsPage.getProductIdText().getText().equals("")
						|| registerGoodsPage.getNoOfPalletsText().getText()
								.equals("")
						|| registerGoodsPage.getNoOfBoxesPerPaletText()
								.getText().equals("")
						|| registerGoodsPage.getNoOfItemsPerBoxText().getText()
								.equals("")
						|| registerGoodsPage.getBuyingPriceText().getText()
								.equals("")
						|| registerGoodsPage.getSellingPriceText().getText()
								.equals("")) {
					view.showErrorMessage("Fill out all data");
				} else {
					try {
						int productID = Integer.parseInt(registerGoodsPage
								.getProductIdText().getText());
						if (server.productIdExists(productID)) {
							int nrOfPallets = Integer
									.parseInt(registerGoodsPage
											.getNoOfPalletsText().getText());
							int noOfBoxesPerPallet = Integer
									.parseInt(registerGoodsPage
											.getNoOfBoxesPerPaletText()
											.getText());
							int itemsPerBox = Integer
									.parseInt(registerGoodsPage
											.getNoOfItemsPerBoxText().getText());
							double pricePerBox = Double
									.parseDouble(registerGoodsPage
											.getSellingPriceText().getText());
							double costPerBox = Double
									.parseDouble(registerGoodsPage
											.getBuyingPriceText().getText());
							int day = (int) registerGoodsPage.getDayCombo()
									.getSelectedItem();
							int month = (int) registerGoodsPage.getMonthCombo()
									.getSelectedItem();
							int year = (int) registerGoodsPage.getYearCombo()
									.getSelectedItem();
							Date expiaryDate = new Date(day, month, year);
							Pallet pallet = new Pallet(productID,
									noOfBoxesPerPallet, itemsPerBox,
									expiaryDate, pricePerBox, costPerBox);
							Pallet[] palletsAndLocations = new Pallet[nrOfPallets];
							palletsAndLocations = server.getRegisteredPallets(
									pallet, nrOfPallets);
							int counter = 0;
							for (int i = 0; i < palletsAndLocations.length; i++) {
								if (palletsAndLocations[i] == null) {
									counter++;
								}
							}
							if (counter > 0) {
								view.showErrorMessage("Storage space full. "
										+ counter
										+ " pallets that have not been registered");
								for (int i = 0; i < (palletsAndLocations.length - counter); i++) {
									registerGoodsPage.getPositionArea().append(
											palletsAndLocations[i] + "\n");
								}
							} else {
								registerGoodsPage
										.getPositionArea()
										.setText(
												model.getPalletsAndPositions(palletsAndLocations));
							}
						} else {
							view.showErrorMessage("Product id does not exist");
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					} catch (NumberFormatException e2) {
						view.showErrorMessage("One of the required fields is not a number");
					}
				}
			}
			if (e.getSource() == stockPage.getCancelButton()) {
				view.switchToMain();
				stockPage.getProductsModel().clear();
				stockPage.getLimitTextField().setText("");
				stockPage.getProductTextField().setText("");
			}
			if (e.getSource() == stockPage.getShowAllButton()) {
				stockPage.getProductsModel().clear();
				int limit;
				ArrayList<Product> list = new ArrayList<Product>();
				try {
					limit = Integer.parseInt(stockPage.getLimitTextField()
							.getText());
					list = server.getProductsWithStocksUnder(limit);
					if (list.size() == 0) {
						view.showErrorMessage("There are no products that have the stock under the selected limit");
					} else {
						for (int i = 0; i < list.size(); i++) {
							stockPage.getProductsModel()
									.addElement(list.get(i));
						}
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e2) {
					view.showErrorMessage("Fill the limit as a number");
				}
			}
			if (e.getSource() == stockPage.getSearchButton()) {
				stockPage.getProductsModel().clear();
				int limit;
				int id;
				Product product;
				try {
					limit = Integer.parseInt(stockPage.getLimitTextField()
							.getText());
					id = Integer.parseInt(stockPage.getProductTextField()
							.getText());
					product = server.getProductWithStocksUnder(id, limit);
					if (product == null) {
						view.showErrorMessage("The product doesnot have the stock under the selected limit or product id does not exist");
					} else {
						stockPage.getProductsModel().addElement(product);
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e2) {
					view.showErrorMessage("Fill in limit/product id as a number");
				}
			}
			if (e.getSource() == view.getStockUnderMenuItem()) {
				stockPage.getProductsModel().clear();
				stockPage.getLimitTextField().setText("");
				stockPage.getProductTextField().setText("");
			}
			if (e.getSource() == addCustomerPage.getCancelButton()) {
				view.switchToMain();
				addCustomerPage.getTCustomerID().setText("");
				addCustomerPage.getTEmail().setText("");
				addCustomerPage.getTName().setText("");
				addCustomerPage.getTPhoneNumber().setText("");
				addCustomerPage.getTPostalCode().setText("");
				addCustomerPage.getTStreet().setText("");
			}
			if (e.getSource() == addCustomerPage.getAddButton()) {
				try {
					if (addCustomerPage.getTCustomerID().getText().equals("")
							|| addCustomerPage.getTName().getText().equals("")
							|| addCustomerPage.getTStreet().getText()
									.equals("")
							|| addCustomerPage.getTPostalCode().getText()
									.equals("")
							|| addCustomerPage.getTEmail().getText().equals("")
							|| addCustomerPage.getTPhoneNumber().getText()
									.equals("")) {
						view.showErrorMessage("Fill in all fields");
					} else {
						int ID = Integer.parseInt(addCustomerPage
								.getTCustomerID().getText());
						String name = addCustomerPage.getTName().getText();
						String streetAndNumber = addCustomerPage.getTStreet()
								.getText();
						int postCode = Integer.parseInt(addCustomerPage
								.getTPostalCode().getText());
						String email = addCustomerPage.getTEmail().getText();
						int phoneNo = Integer.parseInt(addCustomerPage
								.getTPhoneNumber().getText());
						if (server.addCustomer(new Customer(ID, name,
								streetAndNumber, postCode, email, phoneNo))) {
							view.showErrorMessage("Customer added succesfully");
						} else {
							view.showErrorMessage("Error adding customer to database");
						}
					}

				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e2) {
					view.showErrorMessage("Fill in id,phoneNo and post code as numbers");
				}

			}
			if (e.getSource() == customerListPage.getCancelButton()) {
				view.switchToMain();
				customerListPage.getcustomerModel().clear();
				customerListPage.getcustomerTextField().setText("");
			}
			if (e.getSource() == customerListPage.getShowAllButton()) {
				customerListPage.getcustomerModel().clear();
				ArrayList<Customer> customers = new ArrayList<Customer>();
				try {
					customers = server.getAllCustomers();
					if (customers.size() == 0) {
						if (customerListPage.getShowAllButton().isFocusOwner())
							view.showErrorMessage("No customers found/Error reading from database");
					} else {
						for (int i = 0; i < customers.size(); i++) {
							customerListPage.getcustomerModel().addElement(
									customers.get(i));
						}
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == customerListPage.getSearchButton()) {
				customerListPage.getcustomerModel().clear();
				int id;
				Customer customer = null;
				try {
					id = Integer.parseInt(customerListPage
							.getcustomerTextField().getText());
					customer = server.getCustomerById(id);
					if (customer == null) {
						view.showErrorMessage("No customer found with the given id");
					} else {
						customerListPage.getcustomerModel()
								.addElement(customer);
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e2) {
					view.showErrorMessage("Customer id must be a number");
				}
			}
		}

		// *****************************************************************************

		private void showInvoices() {
			invoicesPage.getInvoiceModel().clear();
			try {
				ArrayList<Invoice> list = server.getAllInvoices();
				if (list.size() == 0) {
					if (invoicesPage.getShowAllButton().isFocusOwner())
						view.showErrorMessage("No invoices found");
				} else {
					for (int i = 0; i < list.size(); i++) {
						invoicesPage.getInvoiceModel().addElement(list.get(i));
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		}

		private void showInProgressOrders() {
			inProgressOrdersPage.getInProgressOrderModel().clear();
			ArrayList<Order> inProgressOrders;
			try {

				inProgressOrders = server.getOrders(false);
				System.out.println("Array Size" + inProgressOrders.size());
				if (inProgressOrders.size() == 0) {
					if (view.getInProgressOrdetsItemMenu().isFocusOwner()
							|| inProgressOrdersPage.getJLorders()
									.isFocusOwner())
						view.showErrorMessage("No orders in progress");
				} else {
					Order[] inProgress = model.getOrders(inProgressOrders);
					for (int i = 0; i < inProgress.length; i++) {
						inProgressOrdersPage.getInProgressOrderModel()
								.addElement(inProgress[i]);
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		}

		private void showAvailableOrders() {
			availableOrdersPage.getOrdersModel().clear();
			ArrayList<Order> orders;
			try {
				orders = server.getOrders(true);
				if (orders.size() == 0) {
					if (view.getAvailableOrdersItemMenu().isFocusOwner()
							|| availableOrdersPage.getJLorders().isFocusOwner())
						view.showErrorMessage("No available orders");
				} else {
					Order[] available = model.getOrders(orders);
					for (int i = 0; i < available.length; i++) {
						availableOrdersPage.getOrdersModel().addElement(
								available[i]);
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		}

		private void expiredBefore() {
			expiredBefore.getTshowExpiry().setText("");
			int day = (int) expiredBefore.getCexpDay().getSelectedItem();
			int month = (int) expiredBefore.getCexpMonth().getSelectedItem();
			int year = (int) expiredBefore.getCexpYear().getSelectedItem();
			Date selectedDate = new Date(day, month, year);
			try {
				if (model.getProductsCloseToExpiryDate(
						server.getProductsCloseToExpire(selectedDate)).equals(
						"")) {
					if (expiredBefore.getSearchButton().isFocusOwner())
						view.showErrorMessage("No products expire until the selected date");
				} else {
					expiredBefore.getTshowExpiry().setText(
							model.getProductsCloseToExpiryDate(server
									.getProductsCloseToExpire(selectedDate)));
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		}

		private void productInformation() {
			if (prodInfor.getTextField().getText().equals("")
					&& prodInfor.getTextField().isFocusOwner()) {
				view.showErrorMessage("Insert product id");
			} else {
				try {
					if (model.getProductInformation(
							server.getProductInfo(Integer.parseInt(prodInfor
									.getTextField().getText()))).equals("")) {
						view.showErrorMessage("No product with that product id");
					} else {
						prodInfor.getTextArea().setText(
								model.getProductInformation(server
										.getProductInfo(Integer
												.parseInt(prodInfor
														.getTextField()
														.getText()))));
					}
				} catch (NumberFormatException e1) {
					if (prodInfor.getTextField().isFocusOwner()
							|| prodInfor.getsearch().isFocusOwner())
						view.showErrorMessage("Inserted product id is not a number");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}
		}

		private void showProductsOfCategory() {
			prodOfCat.getListModel().removeAllElements();
			String category = (String) prodOfCat.GetCategorycomboBox()
					.getSelectedItem();
			ArrayList<Product> prodList = new ArrayList<Product>();
			try {
				prodList = server.getAllProducts(category);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			for (int i = 0; i < prodList.size(); i++) {
				prodOfCat.getListModel().addElement(prodList.get(i));
			}

		}

		private void resetRegisterGoodPage() {
			registerGoodsPage.getProductIdText().setText("");
			registerGoodsPage.getNoOfPalletsText().setText("");
			registerGoodsPage.getNoOfBoxesPerPaletText().setText("");
			registerGoodsPage.getNoOfItemsPerBoxText().setText("");
			registerGoodsPage.getBuyingPriceText().setText("");
			registerGoodsPage.getSellingPriceText().setText("");
			registerGoodsPage.getPositionArea().setText("");
		}

		private void populateComboSupplier() {
			NewProd.getSuppliers().removeAllItems();
			String[] suppliers = null;
			try {
				suppliers = server.getSuppliersNames();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < suppliers.length; i++) {
				NewProd.getSuppliers().addItem(suppliers[i]);
			}
		}

		public void showPickersList() {

			showPickersPage.getRegisteredModel().removeAllElements();
			showPickersPage.getTsearch().setText("");
			try {

				ArrayList<Pickers> pickers = server.getAllPickers();
				for (int i = 0; i < model.getAllPickers(pickers).length; i++) {
					showPickersPage.getRegisteredModel().addElement(
							model.getAllPickers(pickers)[i]);
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			showPickersPage.getAtWorkModel().removeAllElements();
			try {
				ArrayList<PickersSchedule> pickAtWork = server.pickersAtWork();
				if (pickAtWork.size() == 0) {
					view.showErrorMessage("No pickers at work");
				} else {
					for (int i = 0; i < pickAtWork.size(); i++) {
						showPickersPage.getAtWorkModel().addElement(
								pickAtWork.get(i));
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

		// end

		private void populateComboCategory() {
			prodOfCat.GetCategorycomboBox().removeAllItems();
			deleteCat.GetCategorycomboBox().removeAllItems();
			NewProd.getCcategory().removeAllItems();
			NewProd.getCcategory().addItem("Add Category");
			String[] categories = null;
			try {
				categories = server.getCategories();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			for (int i = 0; i < categories.length; i++) {
				if (!checkForDoublication(NewProd.getCcategory(), categories[i])) {
					NewProd.getCcategory().addItem(categories[i]);

				}
				deleteCat.GetCategorycomboBox().addItem(categories[i]);
				prodOfCat.GetCategorycomboBox().addItem(categories[i]);
			}
		}

		private void resetDeletePickerPage() {
			deletePickerPage.getPickerIdTextField().setText("");
			deletePickerPage.getPickerInfoTextField().setText("");
		}

		private void resetAddPickerPage() {
			addPickerPage.getTCpr().setText("");
			addPickerPage.getTEmail().setText("");
			addPickerPage.getTName().setText("");
			addPickerPage.getTPhone().setText("");
			addPickerPage.getTStreet().setText("");
			addPickerPage.getTPostCode().setText("");
		}

		// update 06/12
		public void resetAddSupplierPage() {
			addSupplierPage.getSupplierNameText().setText("");
			addSupplierPage.getStreetText().setText("");
			addSupplierPage.getPostCodeText().setText("");
			addSupplierPage.getPhoneNumberText().setText("");
			addSupplierPage.getEmailText().setText("");
		}

		// end

		private boolean checkForDoublication(JComboBox<String> box, String item) {
			boolean exists = false;
			for (int index = 0; index < box.getItemCount() && !exists; index++) {
				if (item.equals(box.getItemAt(index))) {
					return true;
				}
			}
			return false;
		}
	}
	/*
	 * ("New Product Added"); ("Category Added"); ("Product Deleted");
	 * ("Category Deleted"); ("Picker LoggedIn"); ("Picker LoggedOut");
	 * ("Picker Added"); ("Picker Deleted"); ("Supplier Added");
	 * ("Customer Added"); ("Order Made"); ("Order Started");
	 * ("order Finished"); ("Picked Items Updated"); ("Order Paused");
	 * ("Pallets Registered");
	 */
}
