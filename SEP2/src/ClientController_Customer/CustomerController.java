package ClientController_Customer;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.postgresql.util.PSQLException;

import ClientModel.ClientModel;
import ClientView_Customer.GUI_Main_Customer;
import ClientView_Customer.OrderPopUp;
import ClientView_Customer.RowPanel;
import ServerController.ServerInterface;
import util.Date;
import util.Item;
import util.Order;
import util.Pallet;
import util.Pickers;
import util.PickersSchedule;
import util.Product;
import utility.observer.RemoteObserver;
import utility.observer.RemoteSubject;

public class CustomerController implements RemoteObserver<String> {

	private ServerInterface server;

	private GUI_Main_Customer view;
	private static boolean changed;
	private ClientModel model;
	// ***Panels

	private MyListener listener;

	public CustomerController(GUI_Main_Customer view, ClientModel model)
			throws RemoteException {
		connect();
		System.out.println("Client is connected");
		this.view = view;
		this.model = model;
		// **
		// get the panels from the view

		listener = new MyListener();
		addListeners();
		listener.populateComboCategory();

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
			changed = true;
		} catch (Exception e) {
			System.out.println("SERVER CONNECTION FAILED");
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void addListeners() {
		view.getCategoryComboBox().addActionListener(listener);
		view.getAddtoBasketB().addActionListener(listener);
		view.getDeleteB().addActionListener(listener);
		view.getCancelB().addActionListener(listener);
		view.getSendB().addActionListener(listener);
		// view.getShowPickerList().addActionListener(listener);
		// showPickersPage.getJLatWork().addListSelectionListener(
		// new MyListSelection());

	}

	public void update(RemoteSubject<String> arg0, String updateMsg)
			throws RemoteException {
		System.out.println("got an update: " + updateMsg + " @ "
				+ (new Date().toString()));

		if (updateMsg.equals("Pallets Registered")) {
			listener.categoryChanged();

		} else if (updateMsg.equals("Category Added")) {
			listener.populateComboCategory();
		} else if (updateMsg.equals("Category Deleted")) {
			listener.populateComboCategory();
		}
	}

	public ArrayList<Component> getAllComponents() {
		Component[] comps = view.getProductsPanel().getComponents();
		ArrayList<Component> compList = new ArrayList<Component>();
		for (int i = 0; i < comps.length; i++) {
			if (comps[i] instanceof RowPanel) {
				compList.add(comps[i]);
			}
		}
		return compList;
	}

	private class MyListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == view.getCategoryComboBox()) {

				categoryChanged();
			}
			if (e.getSource() == view.getAddtoBasketB()) {
				// System.out.println(getAllComponents().size());
				ArrayList<Component> compList = getAllComponents();
				ArrayList<Item> items = new ArrayList<Item>();
				for (int i = 0; i < compList.size(); i++) {
					RowPanel rp = (RowPanel) compList.get(i);
					if (rp.getCheckBox().isSelected()
							&& !(rp.getAmountTxtF().getText().equals(""))) {
						items.add(new Item(Integer.parseInt(rp.getIdText()
								.getText()), Integer.parseInt(rp
								.getAmountTxtF().getText())));
						rp.getCheckBox().setSelected(false);
						rp.getAmountTxtF().setEnabled(false);
					}
				}
				for (int j = 0; j < items.size(); j++) {
					view.getListModel().addElement(items.get(j));
				}
			}
			if (e.getSource() == view.getDeleteB()) {
				if (view.getCartList().getSelectedValue() != null) {
					// Product s = (Product) prodOfCat.getProductsList()
					// .getSelectedValue();
					int ID = view.getCartList().getSelectedValue()
							.getProductID();
					view.getListModel().removeElementAt(
							view.getCartList().getSelectedIndex());
					// **
					ArrayList<Component> compList = getAllComponents();
					for (int i = 0; i < compList.size(); i++) {
						RowPanel rp = (RowPanel) compList.get(i);
						if (Integer.parseInt(rp.getIdText().getText()) == (ID)) {
							rp.getAmountTxtF().setText("");
							rp.getTotalPriceText().setText("");
						}
					}
				}
			}

			// ************
			if (e.getSource() == view.getCancelB()) {
				view.getListModel().removeAllElements();
			}
			if (e.getSource() == view.getSendB()) {
				int customerID = -1, year = -1, month = -1, day = -1, hour = -1, minute = -1;
				Date shipmentDate = null;
				ArrayList<Item> ItemsinOrder = new ArrayList<Item>();
				for (int i = 0; i < view.getListModel().getSize(); i++) {
					ItemsinOrder.add((Item) view.getListModel().get(i));
				}
				OrderPopUp or = new OrderPopUp();
				if (or.getjOption().CLOSED_OPTION == 0) {
					System.out.println("CanelPressed");
				}
				if (or.getjOption().OK_OPTION == 0) {
					try {
						customerID = Integer.parseInt(or.getId().getText());
					} catch (NumberFormatException x) {
						view.showMessage("Customer id must be a number");
					}
					year = (int) or.getYear().getSelectedItem();
					month = (int) or.getMonth().getSelectedItem();
					day = (int) or.getDay().getSelectedItem();
					hour = (int) or.getHour().getSelectedItem();
					minute = (int) or.getMinute().getSelectedItem();
					shipmentDate = new Date(year, month, day, hour, minute);

				}
				try {
					if (server.idExist(customerID)) {
						Order tempOrder = new Order(shipmentDate, customerID);
						if (server.makeOrder(tempOrder, ItemsinOrder)) {
							view.showMessage("You're order was successfully sent.");
						} else {
							view.showMessage("Something went wrong, please try again.");
						}
					} else {
						view.showMessage("The ID you entered was incorrect!");
					}
				} catch (RemoteException e1) {

					e1.printStackTrace();
				}
			}

		}

		private void categoryChanged() {
			if (view.getCategoryComboBox().getSelectedItem() != null) {
				String category = (String) view.getCategoryComboBox()
						.getSelectedItem();
				ArrayList<Product> productList = new ArrayList<Product>();
				ArrayList<Pallet> pallets = new ArrayList<Pallet>();
				try {
					productList = server.getAllProducts(category);
					pallets = server.getOnePalletForProduct();
					addAndRevalidateMainFrame(category, productList, pallets);
				} catch (RemoteException e1) {

					e1.printStackTrace();
				}
			}

		}

		// end

		public void populateComboCategory() {
			view.getCategoryComboBox().removeAllItems();

			String[] categories = null;
			try {
				categories = server.getCategories();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < categories.length; i++) {

				view.getCategoryComboBox().addItem(categories[i]);

			}

		}

		private void addAndRevalidateMainFrame(String category,
				ArrayList<Product> product, ArrayList<Pallet> pallet) {
			JPanel productsPanel = view.getProductsPanel();
			JPanel mainFrameContainer = view.getContainer();

			productsPanel.removeAll();
			int items = 0;
			double price = 0;
			for (int i = 0; i < product.size(); i++) {
				for (int j = 0; j < pallet.size(); j++) {
					if (product.get(i).getID() == pallet.get(j).getproductID()) {
						items = pallet.get(j).getItemsPerBox();
						price = pallet.get(j).getPrice();
						break;
					}
				}
				if (price == 0 || items == 0) {
					continue;
				}
				RowPanel rowPanel = new RowPanel();
				rowPanel.setLabelText(product.get(i).getName(), product.get(i)
						.getID(), items, price);
				productsPanel.add(rowPanel);
				productsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
				price = 0;
				items = 0;
			}

			mainFrameContainer.revalidate();
			mainFrameContainer.repaint();
		}

	}
}
