package ServerController;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
import utility.observer.RemoteSubject;

public interface ServerInterface extends RemoteSubject<String> 
{
   public boolean addNewProduct(int ID, String PName,
         String CName) throws RemoteException;
   public String[] getCategories()throws RemoteException;
   public boolean addCategory(String category)throws RemoteException;
   public boolean deleteProduct(int id)throws RemoteException;
   public boolean deleteCategory(String str)throws RemoteException;
 
   public ArrayList<Object> getProductInfo(int id)throws RemoteException;
   public ArrayList<Object> getProductsCloseToExpire(Date date)throws RemoteException;
   
   public ArrayList<Product> getAllProducts(String category)throws RemoteException;
   public ArrayList<Pallet> getAllPalletsForOneProduct(int id)throws RemoteException;
   
   //--------December 04, 2016----------
   public String pickerName(int pickerID)throws RemoteException;
   public boolean pickerIsAlreadyIn(int PickerID) throws RemoteException;
   public boolean pickerLogIn(int pickerID)throws RemoteException;
   public boolean PickerLogOut(int pickerID)throws RemoteException;
   //----------------------------
   public boolean addPicker(Pickers picker)throws RemoteException;
   public  Pickers findPicker(int id)throws RemoteException;
   public boolean deletePicker(int id)throws RemoteException;
   public ArrayList<Pickers> getAllPickers()throws RemoteException;
   public ArrayList<Pickers> getAllPickersByName(String nameP)throws RemoteException;
   public ArrayList<PickersSchedule> pickersAtWork()throws RemoteException;
   
   //update 06/12/2016
   public ArrayList<Order> getOrders(boolean choose)throws RemoteException;
   public ArrayList<Item> getItemsForOrder(long orderNr)throws RemoteException;
   public ArrayList<Invoice> getCustomerInvoices(int customerId)throws RemoteException;
   public ArrayList<Invoice> getAllInvoices()throws RemoteException;
   public boolean addSupplier(Supplier supplier)throws RemoteException;
   public int getPickerIdForOrder(long invoiceNo)throws RemoteException;
   public String[] getSuppliersNames()throws RemoteException;
   public boolean delegateSupplierForProduct(int parseInt, String selectedItem)throws RemoteException;
   public ArrayList<Supplier> getAllSuppliers()throws RemoteException;
   public Supplier getSupplierByName(String supplierName)throws RemoteException;
   public ArrayList<Product> getAllProductsFromSupplier(String supplierName)throws RemoteException;
   public Pallet[] getRegisteredPallets(Pallet pallet, int nrOfPallets)throws RemoteException;
   public ArrayList<Product> getProductsWithStocksUnder(int limit)throws RemoteException;
   public Product getProductWithStocksUnder(int id, int limit)throws RemoteException;
   public boolean productIdExists(int productID)throws RemoteException;
   public boolean addCustomer(Customer customer)throws RemoteException;
   public long getStockForProduct(int id)throws RemoteException;
   public ArrayList<Customer> getAllCustomers()throws RemoteException;
   public Customer getCustomerById(int id)throws RemoteException;
   public  ArrayList<Item> getItemsForInvoice(long orderNr) throws RemoteException;
   //end
   
   public ArrayList<Pallet> getOnePalletForProduct()throws RemoteException;
   //***************NEW 06-12-2016**********
   public boolean idExist(int customerID)throws RemoteException;
   public boolean makeOrder(Order tempOrder, ArrayList<Item>ItemsinOrder)throws RemoteException;
   public  ArrayList<Order> getAllOrders()throws RemoteException;
   public ArrayList<Item> getAllItemsForAnOrder(long orderNo) throws RemoteException;
   public  boolean startAnOrder(long OrderNo,int pickerID)throws RemoteException;
   public ArrayList<Order> getAllOrdersInProgress()throws RemoteException;
   public ArrayList<Integer> getAllPickersPickedAnOrder(long orderNo)throws RemoteException;
   public  boolean OrderFinished(long OrderNo/*,ArrayList<Item> items*/)throws RemoteException;
   public  ArrayList<Item> getNOTpickedItemsForAnOrder(long orderNo)throws RemoteException;
   public boolean updatePickedItemOffPallets(ArrayList<Item> pickedItems)throws RemoteException;
   public boolean incompleteAnOrder(long OrderNo,int pickerID)throws RemoteException;
}
