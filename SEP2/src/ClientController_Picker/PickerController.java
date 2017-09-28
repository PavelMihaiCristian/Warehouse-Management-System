package ClientController_Picker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.postgresql.util.PSQLException;

import ClientModel.ClientModel;
import ClientView_Picker.GUI_AvailableOrders;
import ClientView_Picker.GUI_CheckInOut;
import ClientView_Picker.GUI_InProgressOrders;
import ClientView_Picker.InCompleteFinish_PopUp;
import ClientView_Picker.PickerGUIMain;
import ClientView_Picker.Start_PopUp;
import ServerController.ServerInterface;
import util.Date;
import util.Item;
import util.Order;
import util.Pallet;
import util.Pickers;
import util.Product;
import utility.observer.RemoteObserver;
import utility.observer.RemoteSubject;

public class PickerController implements RemoteObserver<String> {

	private ServerInterface server;
	private PickerGUIMain view;
	private static boolean changed;
	private ClientModel model;
	// ***Panels

	private GUI_CheckInOut checkInOut;
	private GUI_AvailableOrders availableOrders;
	private GUI_InProgressOrders inProgress;

	private MyListener listener;

	public PickerController(PickerGUIMain view, ClientModel model)
			throws RemoteException {
		connect();
		System.out.println("Client is connected");
		this.view = view;
		this.model = model;
		// **

		checkInOut = view.getCheckInOutPanel();
		availableOrders = view.getAvailableOrdersPanel();
		inProgress = view.getInprogressPanel();

		listener = new MyListener();
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
			changed = true;
		} catch (Exception e) {
			System.out.println("SERVER CONNECTION FAILED");
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void addListeners() {
		checkInOut.getBin().addActionListener(listener);
		checkInOut.getBout().addActionListener(listener);
		checkInOut.getCancelButton().addActionListener(listener);

		availableOrders.getBcancel().addActionListener(listener);
		availableOrders.getBStart().addActionListener(listener);

		inProgress.getBcancel().addActionListener(listener);

		// ***********************************07-12-2016****
		view.getAvailable().addActionListener(listener);
		availableOrders.getJLorders().addListSelectionListener(
				new MyListSelection());

		inProgress.getBinComplete().addActionListener(listener);
		inProgress.getBFinish().addActionListener(listener);
		view.getProcessing().addActionListener(listener);

		inProgress.getJLorders()
				.addListSelectionListener(new MyListSelection());

		/*
		 * prodOfCat.getProductsList().addListSelectionListener(new
		 * MyListSelection());
		 */
	}

	public void update(RemoteSubject<String> arg0, String updateMsg)
			throws RemoteException {
		System.out.println("got an update: " + updateMsg + " @ "
				+ (new Date().toString()));

		if (updateMsg.equals("Order Made")) {
			listener.showAvailableOrders();
		} else if (updateMsg.equals("Order Started")) {
			listener.showAvailableOrders();
			listener.showOrdersInProgress();
		} else if (updateMsg.equals("order Finished")) {
			listener.showOrdersInProgress();

		} else if (updateMsg.equals("Order Paused")) {
			listener.showAvailableOrders();
			listener.showOrdersInProgress();
		}
	}

	class MyListSelection implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (availableOrders.getJLorders().getSelectedValue() != null) {
				if (e.getSource() == availableOrders.getJLorders()) {

					ArrayList<Item> items = new ArrayList<Item>();
					Order order = (Order) availableOrders.getJLorders()
							.getSelectedValue();
					try {
						items = server.getNOTpickedItemsForAnOrder(order
								.getOrderNo());
						// getAllItemsForAnOrder(order.getOrderNo());
					} catch (RemoteException e1) {

						e1.printStackTrace();
					}
					String str = "";
					for (int i = 0; i < items.size(); i++) {
						str += items.get(i) + "\n";
					}
					view.showErrorMessage(str);
				}
			}

			if (inProgress.getJLorders().getSelectedValue() != null) {
				if (e.getSource() == inProgress.getJLorders()) {

					ArrayList<Item> items = new ArrayList<Item>();
					Order order = (Order) inProgress.getJLorders()
							.getSelectedValue();
					ArrayList<Integer> PickersofAnOrde = new ArrayList<Integer>();
					try {
						PickersofAnOrde = server
								.getAllPickersPickedAnOrder(order.getOrderNo());
						items = server.getNOTpickedItemsForAnOrder(order
								.getOrderNo());
						// getAllItemsForAnOrder(order.getOrderNo());
					} catch (RemoteException e1) {

						e1.printStackTrace();
					}
					String str = "Picker ID of That Order:\n";
					for (int i = 0; i < PickersofAnOrde.size(); i++) {
						str += PickersofAnOrde.get(i) + " \t ";

					}
					str += "\n------------------\n";
					for (int i = 0; i < items.size(); i++) {
						str += items.get(i) + "\n";
					}
					view.showErrorMessage(str);
				}
			}
		}
	}

	private class MyListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == inProgress.getBinComplete()) {
				int pickerID = -1;// check with the orderpicker table
				boolean PickerAccepted = false;
				Order Order = inProgress.getJLorders().getSelectedValue();
				ArrayList<Item> Items = new ArrayList<Item>();
				ArrayList<Integer> pickers = new ArrayList<Integer>();

				try {
					Items = server.getNOTpickedItemsForAnOrder(Order
							.getOrderNo());
					// getAllItemsForAnOrder(Order.getOrderNo());
					pickers = server.getAllPickersPickedAnOrder(Order
							.getOrderNo());
				} catch (RemoteException e1) {

					e1.printStackTrace();
				}
				InCompleteFinish_PopUp popUp = new InCompleteFinish_PopUp(Items);
				popUp.build();
				if (popUp.getjOption().OK_OPTION == 0) {
					try {
						pickerID = Integer.parseInt(popUp.getPickerId()
								.getText());
					} catch (NumberFormatException c) {
						view.showErrorMessage("Picker id must be a number");
					}
					for (int i = 0; i < pickers.size(); i++) {
						if (pickerID == pickers.get(i)) {
							PickerAccepted = true;
						}
					}
					try {
						if (PickerAccepted
								&& server.pickerIsAlreadyIn(pickerID)) {
							// here take them out pallets
							ArrayList<Item> pickedItems = new ArrayList<>();
							for (int k = 0; k < popUp.getcheckBoxes().length; k++) {
								if (popUp.getcheckBoxes()[k].isSelected()) {
									pickedItems.add(Items.get(k));
								}
							}

							if (server.updatePickedItemOffPallets(pickedItems)
									&& server.incompleteAnOrder(
											Order.getOrderNo(), pickerID)) {

								view.showErrorMessage("Request done Successfully!");
							} else {
								view.showErrorMessage("Couldn't pause the Order!");
							}
						} else {
							view.showErrorMessage("Picker ID is not accepted!");
						}
					} catch (RemoteException e1) {

						e1.printStackTrace();
					}
					view.getProcessing().doClick();
				}

			}

			if (e.getSource() == inProgress.getBFinish()) {
				int pickerID = -1;// check with the orderpicker table
				boolean PickerAccepted = false;
				Order Order = inProgress.getJLorders().getSelectedValue();
				ArrayList<Item> Items = new ArrayList<Item>();
				ArrayList<Integer> pickers = new ArrayList<Integer>();

				try {
					Items = server.getNOTpickedItemsForAnOrder(Order
							.getOrderNo());
					// getAllItemsForAnOrder(Order.getOrderNo());
					pickers = server.getAllPickersPickedAnOrder(Order
							.getOrderNo());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				InCompleteFinish_PopUp popUp = new InCompleteFinish_PopUp(Items);
				popUp.build();
				if (popUp.getjOption().OK_OPTION == 0) {
					try {
						pickerID = Integer.parseInt(popUp.getPickerId()
								.getText());
					} catch (NumberFormatException x) {
						view.showErrorMessage("Picker id must be a number");
					}
					for (int i = 0; i < pickers.size(); i++) {
						if (pickerID == pickers.get(i)) {
							PickerAccepted = true;
						}
					}
					try {
						if (PickerAccepted
								&& server.pickerIsAlreadyIn(pickerID)) {
							// here take them out pallets
							ArrayList<Item> pickedItems = new ArrayList<>();
							for (int k = 0; k < popUp.getcheckBoxes().length; k++) {
								if (popUp.getcheckBoxes()[k].isSelected()) {
									pickedItems.add(Items.get(k));
								}
							}

							if (server.updatePickedItemOffPallets(pickedItems)
									&& server
											.OrderFinished(Order.getOrderNo()/*
																			 * ,
																			 * pickedItems
																			 */)) {

								view.showErrorMessage("Finished Successfully!");
							} else {
								view.showErrorMessage("Couldn't Finish the Order!");
							}
						} else {
							view.showErrorMessage("Picker ID is not accepted!");
						}
					} catch (RemoteException e1) {

						e1.printStackTrace();
					}
					view.getProcessing().doClick();
				}
			}

			if (e.getSource() == view.getProcessing()) {
				showOrdersInProgress();
			}
			if (e.getSource() == view.getAvailable()) {
				showAvailableOrders();

			}

			if (e.getSource() == availableOrders.getBStart()) {
				int pickerID = -1;
				Order FirstOrder = (Order) availableOrders.getListModel()
						.getElementAt(0);
				ArrayList<Item> Items = new ArrayList<Item>();

				try {
					Items = server.getNOTpickedItemsForAnOrder(FirstOrder
							.getOrderNo());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Start_PopUp popUP = new Start_PopUp();
				String Str = "Order Number: " + FirstOrder.getOrderNo();
				Str += " \t Customer ID: " + FirstOrder.getCustomer()
						+ "\n\nItems:\n";
				for (int i = 0; i < Items.size(); i++) {
					Str += Items.get(i) + "\n";
				}
				popUP.getTextArea().setText(Str);
				popUP.build();
				if (popUP.getjOption().OK_OPTION == 0) {
					try {
						pickerID = Integer.parseInt(popUP.getPickerId()
								.getText());
					} catch (NumberFormatException l) {
						view.showErrorMessage("Only numbers please!!!");
					}
					String orderDetails = popUP.getTextArea().getText();
					try {
						if (server.pickerIsAlreadyIn(pickerID)) {
							if (server.startAnOrder(FirstOrder.getOrderNo(),
									pickerID)) {
								view.showErrorMessage("Order Started Successfully!");
								view.getAvailable().doClick();
							} else {
								view.showErrorMessage("Couldn't Start Order!!!");
							}
						} else {
							view.showErrorMessage("Picker is not Checked in!");
						}
					} catch (RemoteException e1) {

						e1.printStackTrace();
					}
				}

			}
			if (e.getSource() == checkInOut.getCancelButton()) {
				view.switchToMain();
			}
			if (e.getSource() == availableOrders.getBcancel()) {
				view.switchToMain();
			}
			if (e.getSource() == inProgress.getBcancel()) {
				view.switchToMain();
			}
			if (e.getSource() == checkInOut.getBin()) {
				int PickerID = -1;
				String pickerName = null;
				try {
					PickerID = Integer.parseInt(checkInOut.getTid().getText());
				} catch (ClassCastException | NumberFormatException l) {
					view.showErrorMessage("Picker id must be a number");
					l.printStackTrace();
				}
				try {
					pickerName = server.pickerName(PickerID);
					if (pickerName != null) {
						if (server.pickerIsAlreadyIn(PickerID)) {
							view.showErrorMessage(pickerName
									+ " You are already logged in!");

						} else {
							if (server.pickerLogIn(PickerID)) {
								view.showErrorMessage(pickerName
										+ " You successfully logged in!");
							} else {
								view.showErrorMessage(pickerName
										+ " Couldn't Login!");
							}
						}
					} else {
						view.showErrorMessage("Invalid ID");
					}
				} catch (RemoteException e1) {

					e1.printStackTrace();
				}

			}
			if (e.getSource() == checkInOut.getBout()) {
				int PickerID = -1;
				String pickerName = null;

				try {
					PickerID = Integer.parseInt(checkInOut.getTid().getText());
				} catch (ClassCastException | NumberFormatException l) {
					view.showErrorMessage("Picker id must be a number");
					l.printStackTrace();
				}

				try {
					pickerName = server.pickerName(PickerID);
					if (pickerName != null) {
						if (server.pickerIsAlreadyIn(PickerID)) {
							// view.showErrorMessage(pickerName+" You are already logged in!");
							if (server.PickerLogOut(PickerID)) {
								view.showErrorMessage(pickerName
										+ " You are Successfully logged OUT!");
							} else {
								view.showErrorMessage(pickerName
										+ " Couldn't log out!");
							}

						} else {
							view.showErrorMessage(pickerName
									+ " You are NOT logged in!");
						}
					} else {
						view.showErrorMessage("Invalid ID");
					}
				} catch (RemoteException e1) {

					e1.printStackTrace();
				}

			}
		}

		private void showAvailableOrders() {
			availableOrders.getListModel().removeAllElements();
			ArrayList<Order> orders = new ArrayList<Order>();
			try {
				orders = server.getAllOrders();
				for (int i = 0; i < orders.size(); i++) {
					availableOrders.getListModel().addElement(orders.get(i));
				}
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}

		}

		private void showOrdersInProgress() {
			inProgress.getListModel().removeAllElements();
			ArrayList<Order> orders = new ArrayList<Order>();
			try {
				orders = server.getAllOrdersInProgress();
				for (int i = 0; i < orders.size(); i++) {
					inProgress.getListModel().addElement(orders.get(i));
				}
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}

		}

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
}
