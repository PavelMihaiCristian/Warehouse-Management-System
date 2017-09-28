package ServerController;

import java.io.Serializable;
import java.lang.ProcessBuilder.Redirect;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import ServerModel.IServerModel;
import ServerModel.ServerModel;
import ServerView.ServerGUI;
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
import utility.observer.NotifyObserversThread;
import utility.observer.RemoteObserver;
import utility.observer.RemoteSubject;
import utility.observer.RemoteSubjectDelegate;

public class ServerController implements ServerInterface {

	private IServerModel model;
	private ServerGUI view;
	private Date date;

	// ********************************
	private RemoteSubjectDelegate rsd;

	// *******************************

	public ServerController(IServerModel model, ServerGUI view)
			throws RemoteException {
		this.view = view;
		this.model = model;
		// ********************
		rsd = new RemoteSubjectDelegate<String>((RemoteSubject<String>) this);
		// **********************
		date = new Date();
	}

	public void addObserver(RemoteObserver<String> arg0) throws RemoteException {
		rsd.addObserver(arg0);
	}

	public void deleteObserver(RemoteObserver<String> arg0)
			throws RemoteException {
		rsd.deleteObserver(arg0);
	}

	public void deleteProductById(int id) {
		model.deleteProduct(id);
	}

	@Override
	public boolean addNewProduct(int ID, String PName, String CName)
			throws RemoteException {
		// rsd.notifyObservers("Product added at:"+date.toString());
		rsd.notifyObservers("New Product Added");
		view.showMessage("product "+ID+" was added");
		return model.addNewProduct(ID, PName, CName);
	}

	public String[] getCategories() throws RemoteException {
		return model.getCategories();
	}

	public boolean addCategory(String category) {

		Boolean test = model.addCategory(category);
		if (test)
			// rsd.notifyObservers("Category added at:"+date.toString());
			rsd.notifyObservers("Category Added");
		return test;

	}

	public boolean deleteProduct(int id) {
		// rsd.notifyObservers("Product deleted at:"+date.toString());
		rsd.notifyObservers("Product Deleted");
		return model.deleteProduct(id);
	}

	@Override
	public boolean deleteCategory(String str) throws RemoteException {
		Boolean test = model.removeCategory(str);
		if (test)
			rsd.notifyObservers("Category Deleted");
		// rsd.notifyObservers("Category deleted at:"+date.toString());
		return test;
	}

	public ArrayList<Product> getAllProducts(String category)
			throws RemoteException {
		return model.getAllProducts(category);
	}

	public ArrayList<Pallet> getAllPalletsForOneProduct(int id)
			throws RemoteException {
		return model.getAllPalletsForOneProduct(id);
	}

	// update 02/12/2016
	public ArrayList<Object> getProductInfo(int id) {
		return model.getProductInfo(id);
	}

	public ArrayList<Object> getProductsCloseToExpire(Date date) {
		return model.getProductsCloseToExpire(date);
	}

	// ---------December 04, 2016-------
	@Override
	public String pickerName(int pickerID) throws RemoteException {
		// TODO Auto-generated method stub
		return model.pickerName(pickerID);
	}

	@Override
	public boolean pickerIsAlreadyIn(int PickerID) throws RemoteException {
		// TODO Auto-generated method stub
		return model.pickerIsAlreadyIn(PickerID);
	}

	public boolean pickerLogIn(int pickerID) throws RemoteException {
		// rsd.notifyObservers("Picker logged in:"+date.toString());
		rsd.notifyObservers("Picker LoggedIn");

		return model.pickerLogIn(pickerID);
	}

	public boolean PickerLogOut(int pickerID) throws RemoteException {
		// rsd.notifyObservers("Picker logged out:"+date.toString());
		rsd.notifyObservers("Picker LoggedOut");

		return model.PickerLogOut(pickerID);
	}

	// ----------
	public boolean addPicker(Pickers picker) {
		// rsd.notifyObservers("Picker added:"+date.toString());
		rsd.notifyObservers("Picker Added");

		return model.addPicker(picker);
	}

	public Pickers findPicker(int id) {
		return model.findPicker(id);
	}

	public boolean deletePicker(int id) {
		// rsd.notifyObservers("Picker fired in:"+date.toString());
		rsd.notifyObservers("Picker Deleted");

		return model.deletePicker(id);
	}

	public ArrayList<Pickers> getAllPickers() {
		return model.getAllPickers();
	}

	public ArrayList<Pickers> getAllPickersByName(String nameP) {
		return model.getAllPickersByName(nameP);
	}

	public ArrayList<PickersSchedule> pickersAtWork() {
		return model.pickersAtWork();
	}

	// update 06/12/2016
	public ArrayList<Order> getOrders(boolean choose) throws RemoteException {
		return model.getOrders(choose);
	}

	public ArrayList<Item> getItemsForOrder(long orderNr)
			throws RemoteException {
		return model.getItemsForOrder(orderNr);
	}

	public ArrayList<Invoice> getCustomerInvoices(int customerId)
			throws RemoteException {
		return model.getCustomerInvoices(customerId);
	}

	public ArrayList<Invoice> getAllInvoices() throws RemoteException {
		return model.getAllInvoices();
	}

	public boolean addSupplier(Supplier supplier) throws RemoteException {
		// rsd.notifyObservers("Supplier added :"+date.toString());
		rsd.notifyObservers("Supplier Added");
		return model.addSupplier(supplier);
	}

	public int getPickerIdForOrder(long invoiceNo) throws RemoteException {
		return model.getPickerIdForOrder(invoiceNo);
	}

	public String[] getSuppliersNames() throws RemoteException {
		return model.getSuppliersNames();
	}

	public boolean delegateSupplierForProduct(int pid, String supname)
			throws RemoteException {
		return model.delegateSupplierForProduct(pid, supname);
	}

	public ArrayList<Supplier> getAllSuppliers() throws RemoteException {
		return model.getAllSuppliers();
	}

	public Supplier getSupplierByName(String supplierName)
			throws RemoteException {
		return model.getSupplierByName(supplierName);
	}

	public ArrayList<Product> getAllProductsFromSupplier(String supplierName)
			throws RemoteException {
		return model.getAllProductsFromSupplier(supplierName);
	}

	public Pallet[] getRegisteredPallets(Pallet pallet, int nrOfPallets)
			throws RemoteException {
		rsd.notifyObservers("Pallets Registered");
		return model.getRegisteredPallets(pallet, nrOfPallets);
	}

	public ArrayList<Product> getProductsWithStocksUnder(int limit)
			throws RemoteException {
		return model.getProductsWithStocksUnder(limit);
	}

	public Product getProductWithStocksUnder(int id, int limit)
			throws RemoteException {
		return model.getProductWithStocksUnder(id, limit);
	}

	public boolean productIdExists(int productID) throws RemoteException {
		return model.productIdExists(productID);
	}

	public boolean addCustomer(Customer customer) throws RemoteException {
		// rsd.notifyObservers("Customer added :"+date.toString());
		rsd.notifyObservers("Customer Added");
		return model.addCustomer(customer);
	}

	public long getStockForProduct(int id) throws RemoteException {
		return model.getStockForProduct(id);
	}

	public ArrayList<Customer> getAllCustomers() throws RemoteException {
		return model.getAllCustomers();
	}

	public Customer getCustomerById(int id) throws RemoteException {
		return model.getCustomerById(id);
	}

	// end

	public ArrayList<Pallet> getOnePalletForProduct() throws RemoteException {
		return model.getOnePalletForProduct();
	}

	// *********06-12-2016****
	public boolean idExist(int customerID) throws RemoteException {
		return model.idExist(customerID);
	}

	public boolean makeOrder(Order tempOrder, ArrayList<Item> ItemsinOrder)
			throws RemoteException {
		// rsd.notifyObservers("An order was placed:"+date.toString());
		rsd.notifyObservers("Order Made");

		return model.makeOrder(tempOrder, ItemsinOrder);
	}

	public ArrayList<Order> getAllOrders() throws RemoteException {
		return model.getAllOrders();
	}

	public ArrayList<Item> getAllItemsForAnOrder(long orderNo)
			throws RemoteException {
		return model.getAllItemsForAnOrder(orderNo);
	}

	public boolean startAnOrder(long OrderNo, int pickerID)
			throws RemoteException {
		// rsd.notifyObservers("Order started :"+date.toString());
		rsd.notifyObservers("Order Started");
		return model.startAnOrder(OrderNo, pickerID);
	}

	public ArrayList<Order> getAllOrdersInProgress() throws RemoteException {
		return model.getAllOrdersInProgress();
	}

	public ArrayList<Integer> getAllPickersPickedAnOrder(long orderNo) {
		return model.getAllPickersPickedAnOrder(orderNo);
	}

	public boolean OrderFinished(long OrderNo/* ,ArrayList<Item> items */)
			throws RemoteException {
		// rsd.notifyObservers("order finished :"+date.toString());
		rsd.notifyObservers("order Finished");
		return model.OrderFinished(OrderNo/* , items */);
	}

	public ArrayList<Item> getNOTpickedItemsForAnOrder(long orderNo)
			throws RemoteException {
		return model.getNOTpickedItemsForAnOrder(orderNo);
	}

	public boolean updatePickedItemOffPallets(ArrayList<Item> pickedItems)
			throws RemoteException {
		// rsd.notifyObservers("Picked items have been changed :"+date.toString());
		rsd.notifyObservers("Picked Items Updated");

		return model.updatePickedItemOffPallets(pickedItems);
	}

	public boolean incompleteAnOrder(long OrderNo, int pickerID)
			throws RemoteException {
		// rsd.notifyObservers("Order was interrupted:"+date.toString());
		rsd.notifyObservers("Order Paused");
		return model.incompleteAnOrder(OrderNo, pickerID);
	}

	public ArrayList<Item> getItemsForInvoice(long orderNr)
			throws RemoteException {
		return model.getItemsForInvoice(orderNr);
	}
}
