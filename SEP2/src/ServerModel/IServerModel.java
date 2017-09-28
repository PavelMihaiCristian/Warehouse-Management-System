package ServerModel;

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


public interface IServerModel
{
   public ArrayList<Pallet> getAllPalletsForOneProduct(int id);
   public  ArrayList<Product> getAllProducts(String category);
   public  boolean addNewProduct(int ID, String PName, String CName);
   public  String[] getCategories();
   public  boolean deleteProduct(int id);
   public  boolean addCategory(String CName);
   public  boolean removeCategory(String CName);
   public  ArrayList<Object> getProductInfo(int id);
   public  ArrayList<Object> getProductsCloseToExpire(Date date);
   public  String pickerName(int pickerID);
   public  boolean pickerIsAlreadyIn(int PickerID);
   public  boolean pickerLogIn(int pickerID);
   public  boolean PickerLogOut(int pickerID);
   public  String HoursWorked(int pickerID);
   public  boolean addPicker(Pickers picker) ;
   public  Pickers findPicker(int id);
   public  boolean deletePicker(int id);
   public  ArrayList<Pickers> getAllPickers();
   public  ArrayList<Pickers> getAllPickersByName(String nameP) ;
   public  ArrayList<PickersSchedule> pickersAtWork() ;
   public  ArrayList<Order> getOrders(boolean choose);
   public  ArrayList<Item> getItemsForOrder(long orderNr);
   public  ArrayList<Invoice> getCustomerInvoices(int customerId);
   public  ArrayList<Invoice> getAllInvoices();
   public  Date convertSqlDateToDate(java.sql.Timestamp sqlDate);
   public  boolean addSupplier(Supplier supplier);
   public  int getPickerIdForOrder(long invoiceNo);
   public  String[] getSuppliersNames();
   public  boolean delegateSupplierForProduct(int pid,String supname);
   public  ArrayList<Supplier> getAllSuppliers();
   public  Supplier getSupplierByName(String supplierName);
   public  ArrayList<Product> getAllProductsFromSupplier(String supplierName);
   public  Pallet[] getRegisteredPallets(Pallet pallet,int nrOfPallets);
   public  int[][][] getPositionsInWarehouse(ArrayList<Object[]> currentpositions);
   public  int generateIdNumber();
   public  ArrayList<Integer> getPalletsIds();
   public  ArrayList<Product> getProductsWithStocksUnder(int limit);
   public  Product getProductWithStocksUnder(int id, int limit);
   public  boolean productIdExists(int productID);
   public  boolean addCustomer(Customer customer);
   public  long getStockForProduct(int id);
   public  ArrayList<Customer> getAllCustomers();
   public  Customer getCustomerById(int id);
   public  ArrayList<Pallet> getOnePalletForProduct();
   public  boolean idExist(int customerID);
   public  boolean makeOrder(Order tempOrder, ArrayList<Item>ItemsinOrder);
   public  ArrayList<Order> getAllOrders() ;
   public  ArrayList<Item> getAllItemsForAnOrder(long orderNo);
   public  boolean startAnOrder(long OrderNo,int pickerID);
   public  ArrayList<Order> getAllOrdersInProgress();
   public  ArrayList<Integer> getAllPickersPickedAnOrder(long orderNo);
   public  boolean OrderFinished(long OrderNo);
   public  ArrayList<Item> getNOTpickedItemsForAnOrder(long orderNo);
   public  boolean updatePickedItemOffPallets(ArrayList<Item> pickedItems);
   public  ArrayList<Pallet> getAllPalletsOfAProductOrderedByDate(int productID);
   public  ArrayList<Item> getALLpickedItemsForAnOrder(long orderNo);
   public  boolean incompleteAnOrder(long OrderNo, int pickerID);
   public  ArrayList<Item> getItemsForInvoice(long orderNr);
}