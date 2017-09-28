package ServerModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.sql.rowset.spi.SyncResolver;

import org.postgresql.util.PSQLException;

import util.Customer;
import util.Date;
import util.Invoice;
import util.Item;
import util.Order;
import util.Pallet;
import util.Pickers;
import util.PickersSchedule;
import util.Product;
import util.Search;
import util.Supplier;
import util.partition;
import JDBC.MyDatabase;

public class ServerModel implements IServerModel{

	private MyDatabase database;

	public ServerModel() {
		try {
			database = new MyDatabase();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public synchronized ArrayList<Pallet> getAllPalletsForOneProduct(int id) {
		ArrayList<Pallet> result = new ArrayList<Pallet>();
		String sql = "SELECT id,nrofboxesperpallet,expirydate FROM Inventory.Pallet WHERE ProductId="
				+ id + "ORDER BY expirydate;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		} else {
			for (int k = 0; k < list.size(); k++) {
				int idPallet = (int) list.get(k)[0];
				int noOfBoxesPerPallet = (int) list.get(k)[1];
				java.sql.Date expiryDate = (java.sql.Date) list.get(k)[2];
				LocalDate anotherDate = expiryDate.toLocalDate();
				int day = anotherDate.getDayOfMonth();
				int month = anotherDate.getMonthValue();
				int year = anotherDate.getYear();
				// System.out.println(new Date(day, month, year));
				result.add(new Pallet(idPallet, noOfBoxesPerPallet, new Date(
						day, month, year)));
			}
			return result;
		}

	}

	public synchronized ArrayList<Product> getAllProducts(String category) {

		ArrayList<Product> result = new ArrayList<Product>();
		String sql = "SELECT * FROM Inventory.Product WHERE cname='" + category
				+ "';";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		} else {
			for (int i = 0; i < list.size(); i++) {
				int id = (int) list.get(i)[0];
				String productname = (String) list.get(i)[1];
				String cname = (String) list.get(i)[2];
				result.add(new Product(id, productname, cname));
			}
			return result;
		}
	}

	public synchronized boolean addNewProduct(int ID, String PName, String CName) {
		String sql = "INSERT INTO Inventory.PRODUCT VALUES(" + ID + ",'"
				+ PName + "' ,'" + CName + "');";
		System.out.println(sql);
		// sql = "INSERT INTO Inventory.PRODUCT VALUES(12,'test','test',5);";
		try {
			database.update(sql);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public synchronized String[] getCategories() {
		String sql = "Select cname from Inventory.Category;";
		ArrayList<Object[]> list = null;
		try {
			list = database.query(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String[] strreturn = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			strreturn[i] = list.get(i)[0] + "";
		}
		return strreturn;

	}

	public synchronized boolean deleteProduct(int id) {

		String sql = "select * from Inventory.PRODUCT WHERE ID= " + id + ";";
		ArrayList<Object[]> list = null;
		try {
			list = database.query(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (list.size() == 0) {
			return false;
		}
		sql = "DELETE FROM Inventory.PRODUCT WHERE ID= " + id + ";";
		try {
			database.update(sql);
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

	public synchronized boolean addCategory(String CName) {
		String sql = "INSERT INTO Inventory.CATEGORY(CName) VALUES('" + CName
				+ "');";
		try {
			database.update(sql);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public synchronized boolean removeCategory(String CName) {
		String sql = "DELETE FROM Inventory.CATEGORY WHERE CName= '" + CName
				+ "';";
		try {
			database.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public synchronized ArrayList<Object> getProductInfo(int id) {
		ArrayList<Object> result = new ArrayList<Object>();
		String sql = "SELECT * FROM Inventory.product WHERE Id=" + id + ";";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (list.size() == 0)
			return result;
		else {
			int idProduct = (int) list.get(0)[0];
			String name = (String) list.get(0)[1];
			String catName = (String) list.get(0)[2];
			result.add(new Product(idProduct, name, catName));
		}
		/**
		 * sql =
		 * "SELECT sum(nrofboxesperpallet),expirydate  FROM Inventory.Pallet WHERE ProductId="
		 * + id + "" + "GROUP BY ExpiryDate ORDER BY expirydate;";
		 **/

		// pallet ID, product ID, product Name, category, number of boxes, and
		// the
		// expiry date

		sql = "SELECT id,nrofboxesperpallet,expirydate FROM Inventory.Pallet WHERE ProductId="
				+ id + "ORDER BY expirydate;";

		list = new ArrayList<Object[]>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0)
			return result;
		else {
			for (int k = 0; k < list.size(); k++) {
				int idPallet = (int) list.get(k)[0];
				int noOfBoxesPerPallet = (int) list.get(k)[1];
				java.sql.Date expiryDate = (java.sql.Date) list.get(k)[2];
				LocalDate anotherDate = expiryDate.toLocalDate();
				int day = anotherDate.getDayOfMonth();
				int month = anotherDate.getMonthValue();
				int year = anotherDate.getYear();
				// System.out.println(new Date(day,month,year));
				result.add(new Pallet(idPallet, noOfBoxesPerPallet, new Date(
						day, month, year)));
			}
			return result;
		}
	}

	public synchronized ArrayList<Object> getProductsCloseToExpire(Date date) {
		ArrayList<Object> result = new ArrayList<Object>();
		String str = "";
		/**
		 * String sql =
		 * "SELECT p.id,p.pname, expirydate, sum(nrofboxesperpallet) FROM Inventory.Pallet,inventory.product p"
		 * + " WHERE expirydate<=" + "'" + date.getMonth() + "/" + date.getDay()
		 * + "/" + date.getYear() + "'" +
		 * "GROUP BY  expirydate,p.id ORDER BY expirydate;";
		 **/

		String sql = "SELECT id,productid,nrofboxesperpallet,expirydate FROM Inventory.Pallet"
				+ " WHERE expirydate<="
				+ "'"
				+ date.getYear()
				+ "/"
				+ date.getMonth() + "/" + date.getDay()

				+ "'" + "ORDER BY expirydate;";

		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		} else {
			for (int k = 0; k < list.size(); k++) {
				int idPallet = (int) list.get(k)[0];
				int productID = (int) list.get(k)[1];
				int noOfBoxesPerPallet = (int) list.get(k)[2];
				java.sql.Date expiryDate = (java.sql.Date) list.get(k)[3];
				LocalDate anotherDate = expiryDate.toLocalDate();
				int day = anotherDate.getDayOfMonth();
				int month = anotherDate.getMonthValue();
				int year = anotherDate.getYear();
				// System.out.println(new Date(day,month,year));
				result.add(new Pallet(idPallet, productID, noOfBoxesPerPallet,
						new Date(day, month, year)));
			}
			return result;
		}
	}

	public static void main(String[] args) {
		ServerModel model = new ServerModel();
		/**
		 * ArrayList<Object> result=model.getProductInfo(566764); for(int
		 * i=0;i<result.size();i++){ System.out.println(result.get(i)); }
		 **/
		/*
		 * Date date = new Date(10, 5, 2016, 0, 0); ArrayList<Object> list =
		 * model.getProductsCloseToExpire(date); for (int i = 0; i <
		 * list.size(); i++) { Pallet palet = (Pallet) list.get(i);
		 * System.out.println(palet.toStringForListOfExpiry()); }
		 */
		System.out.println(model.pickerName(1));
		System.out.println(model.pickerIsAlreadyIn(1));
		System.out.println(model.pickerIsAlreadyIn(15));
		System.out.println(model.HoursWorked(1));
	}

	// ---------------December 04, 2016
	public synchronized String pickerName(int pickerID) {
		String sql = "SELECT Name FROM Inventory.Pickers WHERE ID=" + pickerID
				+ ";";

		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		} else {
			String name = "";
			for (int k = 0; k < list.size(); k++) {
				name = (String) list.get(k)[0];
			}
			return name;
		}

	}

	public synchronized boolean pickerIsAlreadyIn(int PickerID) {
		String sql = "SELECT ID FROM Inventory.PickersSchedule WHERE ID="
				+ PickerID + "AND CheckOut isnull;";

		ArrayList<Object[]> list = new ArrayList<Object[]>();
		System.out.println(list.size());
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return false;
		} else {
			return true;
		}

	}

	public synchronized boolean pickerLogIn(int pickerID) {
		String sql = "INSERT INTO Inventory.PickersSchedule VALUES(" + pickerID
				+ ",NOW(),null);";

		try {
			database.update(sql);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public synchronized boolean PickerLogOut(int pickerID) {
		String sql = "update inventory.pickersschedule"
				+ " set checkout =NOW() " + "WHERE id=" + pickerID
				+ " AND checkout ISNULL ;";

		try {
			database.update(sql);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	// **select checkout-checkin FROM inventory.pickersschedule p;
	/*
	 * select checkout-checkin FROM inventory.pickersschedule where p.checkin
	 * BETWEEN CURRENT_DATE and CURRENT_DATE+1 And id=1;
	 */
	public synchronized String HoursWorked(int pickerID) {
		String sql = "select checkout-checkin FROM inventory.pickersschedule"
				+ " where checkin  BETWEEN CURRENT_DATE and CURRENT_DATE+1 And id="
				+ pickerID + ";";

		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		} else {
			String timeWorked = "";
			for (int k = 0; k < list.size(); k++) {
				timeWorked += (String) list.get(k)[0];
				timeWorked += ", ";
			}
			return timeWorked;
		}

	}

	// ---
	public synchronized boolean addPicker(Pickers picker) {
		String sql = "INSERT INTO Inventory.pickers(cpr,name, street, postcode, phone, email) VALUES("
				+ picker.getCPR()
				+ ",'"
				+ picker.getName()
				+ "','"
				+ picker.getStreet()
				+ "',"
				+ picker.getPostCode()
				+ ","
				+ picker.getPhoneNo() + ",'" + picker.getEmail() + "');";
		try {
			database.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public synchronized Pickers findPicker(int id) {
		Pickers picker = null;
		String sql = "SELECT id,cpr,name, street, postcode, phone, email FROM Inventory.pickers WHERE id= ? ;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0)
			return picker;
		int idbase = (int) list.get(0)[0];
		long CPR = (long) list.get(0)[1];
		String name = (String) list.get(0)[2];
		String street = (String) list.get(0)[3];
		int postCode = (int) list.get(0)[4];
		long phoneNo = (long) list.get(0)[5];
		String email = (String) list.get(0)[6];
		picker = new Pickers(idbase, name, CPR, street, postCode, phoneNo,
				email);
		// System.out.println(picker.toString2());
		return picker;
	}

	public synchronized boolean deletePicker(int id) {
		PickerLogOut(id);
		String sql = "DELETE FROM Inventory.pickers WHERE id= ? ;";
		try {
			database.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public synchronized ArrayList<Pickers> getAllPickers() {
		ArrayList<Pickers> result = new ArrayList<Pickers>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT id,cpr,name, street, postcode, phone, email FROM Inventory.pickers";
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		} else {
			for (int i = 0; i < list.size(); i++) {
				int id = (int) list.get(i)[0];
				long CPR = (long) list.get(i)[1];
				String name = (String) list.get(i)[2];
				String street = (String) list.get(i)[3];
				int postCode = (int) list.get(i)[4];
				long phoneNo = (long) list.get(i)[5];
				String email = (String) list.get(i)[6];
				result.add(new Pickers(id, name, CPR, street, postCode,
						phoneNo, email));
			}
		}
		return result;
	}

	public synchronized ArrayList<Pickers> getAllPickersByName(String nameP) {
		ArrayList<Pickers> result = new ArrayList<Pickers>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT id,cpr,name, street, postcode, phone, email FROM Inventory.pickers WHERE name= ?;";
		try {
			list = database.query(sql, nameP);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		} else {
			for (int i = 0; i < list.size(); i++) {
				int id = (int) list.get(i)[0];
				long CPR = (long) list.get(i)[1];
				String name = (String) list.get(i)[2];
				String street = (String) list.get(i)[3];
				int postCode = (int) list.get(i)[4];
				long phoneNo = (long) list.get(i)[5];
				String email = (String) list.get(i)[6];
				result.add(new Pickers(id, name, CPR, street, postCode,
						phoneNo, email));
			}
		}
		return result;
	}

	public synchronized ArrayList<PickersSchedule> pickersAtWork() {
		String sql = "SELECT id,checkin FROM Inventory.PickersSchedule "
				+ "where CheckOut isnull;";
		ArrayList<PickersSchedule> result = new ArrayList<PickersSchedule>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		System.out.println(list.size());
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		}
		for (int i = 0; i < list.size(); i++) {
			int id = (int) list.get(i)[0];
			Date checkIn = convertSqlDateToDate((java.sql.Timestamp) list
					.get(i)[1]);
			result.add(new PickersSchedule(id, checkIn));
		}
		return result;
	}

	// update 06/12/2016
	public synchronized ArrayList<Order> getOrders(boolean choose) {
		ArrayList<Order> available = new ArrayList<Order>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT ordernumber,dateofshipment,dateorderreceived,customerid FROM Inventory.order WHERE availability=?;";
		int day, month, year, hour, minute, customerId, day1, month1, year1, hour1, minute1;
		Date dateRecived, dateOfShipment;
		LocalDateTime anotherDate, anotherDate2;
		try {
			list = database.query(sql, choose);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return available;
		}
		for (int i = 0; i < list.size(); i++) {
			long OrderNo = (long) list.get(i)[0];
			dateOfShipment = convertSqlDateToDate((java.sql.Timestamp) list
					.get(i)[1]);
			dateRecived = convertSqlDateToDate((java.sql.Timestamp) list.get(i)[2]);
			customerId = (int) list.get(i)[3];
			available.add(new Order(OrderNo, dateOfShipment, dateRecived,
					customerId));
		}
		return available;
	}

	public synchronized ArrayList<Item> getItemsForOrder(long orderNr) {
		String sql = "SELECT productid,amount FROM Inventory.item WHERE ordernumber=?;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			list = database.query(sql, orderNr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return items;
		}
		for (int i = 0; i < list.size(); i++) {
			int ID = (int) list.get(i)[0];
			int amount = (int) list.get(i)[1];
			items.add(new Item(ID, amount));
		}
		return items;
	}
	
	public synchronized ArrayList<Item> getItemsForInvoice(long orderNr) {
		String sql = "SELECT productid,amount,short FROM Inventory.item WHERE ordernumber=? and picked=true;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			list = database.query(sql, orderNr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return items;
		}
		for (int i = 0; i < list.size(); i++) {
			int ID = (int) list.get(i)[0];
			int amount = (int) list.get(i)[1];
			int shortage=(int)list.get(i)[2];
			items.add(new Item(ID, amount-shortage));
		}
		return items;
	}

	public synchronized ArrayList<Invoice> getCustomerInvoices(int customerId) {
		ArrayList<Invoice> result = new ArrayList<Invoice>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT * FROM Inventory.invoice WHERE customerid=? ORDER BY dateoforderreceived;";
		try {
			list = database.query(sql, customerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		}
		for (int i = 0; i < list.size(); i++) {
			long orderNumber = (long) list.get(i)[0];
			int customerID = (int) list.get(i)[1];
			Date dateOfShipment = convertSqlDateToDate((java.sql.Timestamp) list
					.get(i)[2]);
			Date dateOfOrderRceived = convertSqlDateToDate((java.sql.Timestamp) list
					.get(i)[3]);
			double totalExVAT = (double) list.get(i)[4];
			double vAT = (double) list.get(i)[5];
			double totalInclVAT = (double) list.get(i)[6];
			result.add(new Invoice(orderNumber, customerID, dateOfShipment,
					dateOfOrderRceived, totalExVAT, vAT, totalInclVAT));
		}
		return result;
	}

	public synchronized ArrayList<Invoice> getAllInvoices() {
		ArrayList<Invoice> result = new ArrayList<Invoice>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT * FROM Inventory.invoice ORDER BY dateoforderreceived;";
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		}
		for (int i = 0; i < list.size(); i++) {
			long orderNumber = (long) list.get(i)[0];
			int customerID = (int) list.get(i)[1];
			Date dateOfShipment = convertSqlDateToDate((java.sql.Timestamp) list
					.get(i)[2]);
			Date dateOfOrderRceived = convertSqlDateToDate((java.sql.Timestamp) list
					.get(i)[3]);
			double totalExVAT = (double) list.get(i)[4];
			double vAT = (double) list.get(i)[5];
			double totalInclVAT = (double) list.get(i)[6];
			result.add(new Invoice(orderNumber, customerID, dateOfShipment,
					dateOfOrderRceived, totalExVAT, vAT, totalInclVAT));
		}
		return result;
	}

	public synchronized Date convertSqlDateToDate(java.sql.Timestamp sqlDate) {
		LocalDateTime anotherDate = sqlDate.toLocalDateTime();
		int day = anotherDate.getDayOfMonth();
		int month = anotherDate.getMonthValue();
		int year = anotherDate.getYear();
		int hour = anotherDate.getHour();
		int minute = anotherDate.getMinute();
		return new Date(day, month, year, hour, minute);
	}

	public synchronized boolean addSupplier(Supplier supplier) {
		String sql = "INSERT INTO Inventory.supplier VALUES(?,?,?,?,?);";
		try {
			database.update(sql, supplier.getSupplierName(),
					supplier.getPhoneNumber(), supplier.getEmail(),
					supplier.getStreet(), supplier.getPostalCode());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public synchronized int getPickerIdForOrder(long invoiceNo) {
		String sql = "SELECT pickerid FROM Inventory.pickerorder WHERE ordernumber=?;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql, invoiceNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return 0;
		}
		return (int) list.get(0)[0];
	}

	public synchronized String[] getSuppliersNames() {
		String sql = "SELECT supname FROM Inventory.supplier;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] result = new String[list.size()];
		if (list.size() == 0) {
			return result;
		} else {
			for (int i = 0; i < list.size(); i++) {
				result[i] = (String) list.get(i)[0];
			}
		}
		return result;
	}

	public synchronized boolean delegateSupplierForProduct(int pid,
			String supname) {
		String sql = "INSERT INTO Inventory.productsupplier VALUES(?,?);";
		try {
			database.update(sql, pid, supname);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public synchronized ArrayList<Supplier> getAllSuppliers() {
		String sql = "SELECT * FROM Inventory.supplier;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		ArrayList<Supplier> result = new ArrayList<Supplier>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		}
		for (int i = 0; i < list.size(); i++) {
			String supplierName = (String) list.get(i)[0];
			int phoneNumber = (int) list.get(i)[1];
			String email = (String) list.get(i)[2];
			String street = (String) list.get(i)[3];
			int postalCode = (int) list.get(i)[4];
			result.add(new Supplier(supplierName, phoneNumber, email, street,
					postalCode));
		}
		return result;
	}

	public synchronized Supplier getSupplierByName(String supplierName) {
		String sql = "SELECT * FROM Inventory.supplier WHERE supname=?;";
		Supplier supplier = null;
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql, supplierName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return supplier;
		}
		String suppliername = (String) list.get(0)[0];
		int phoneNumber = (int) list.get(0)[1];
		String email = (String) list.get(0)[2];
		String street = (String) list.get(0)[3];
		int postalCode = (int) list.get(0)[4];
		supplier = new Supplier(suppliername, phoneNumber, email, street,
				postalCode);
		return supplier;
	}

	public synchronized ArrayList<Product> getAllProductsFromSupplier(
			String supplierName) {
		String sql = "select * from inventory.product p WHERE p.id=(Select pid from inventory.productsupplier s where s.supname=? and pid=id );";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		ArrayList<Product> result = new ArrayList<Product>();
		try {
			list = database.query(sql, supplierName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		}
		for (int i = 0; i < list.size(); i++) {
			int ID = (int) list.get(i)[0];
			String name = (String) list.get(i)[1];
			String catName = (String) list.get(i)[2];
			result.add(new Product(ID, name, catName));
		}
		return result;
	}

	public synchronized Pallet[] getRegisteredPallets(Pallet pallet,
			int nrOfPallets) {
		int productID = pallet.getproductID();
		int noOfBoxesPerPallet = pallet.getNoOfBoxesPerPallet();
		int itemsPerBox = pallet.getItemsPerBox();
		double pricePerBox = pallet.getPrice();
		double costPerBox = pallet.getCost();
		Date expiaryDate = pallet.getExpiryDate();
		int day = expiaryDate.getDay();
		int month = expiaryDate.getMonth();
		int year = expiaryDate.getYear();

		Pallet[] registeredPallets = new Pallet[nrOfPallets];
		int[][][] warehouse = new int[35][45][5];
		ArrayList<Integer> currentPalletIds = getPalletsIds();
		int[] ids = new int[currentPalletIds.size()];
		int[] newIds = new int[nrOfPallets];

		int pos, idnew;
		for (int i = 0; i < nrOfPallets; i++) {
			partition.QuickSort(ids, 0, ids.length - 1);
			do {
				idnew = generateIdNumber();
				pos = Search.binSearchRec(ids, idnew, 0, ids.length - 1);
			} while (pos != -1);
			newIds[i] = idnew;
			currentPalletIds.add(idnew);
			ids = new int[currentPalletIds.size()];
			for (int j = 0; j < currentPalletIds.size(); j++) {
				ids[j] = currentPalletIds.get(j);
			}
		}
		String sql = "SELECT aisle,depth,floor FROM Inventory.pallet;";
		ArrayList<Object[]> positions = new ArrayList<Object[]>();
		try {
			positions = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (positions.size() != 0) {
			warehouse = getPositionsInWarehouse(positions);
		}
		String sql1 = "INSERT INTO Inventory.pallet VALUES(?,?,?,"+"'"+ year+ "/"+ month + "/" + day+ "'"+",?,?,?,?,?,?);";
		for (int index = 0; index < nrOfPallets; index++) {
			boolean flag = false;
			for (int i = 1; i < warehouse.length && !flag; i++) {
				for (int j = 1; j < warehouse[i].length && !flag; j++) {
					for (int k = 1; k < warehouse[i][j].length && !flag; k++) {
						if (warehouse[i][j][k] == 0) {
							try {
								database.update(sql1, newIds[index], productID,
										noOfBoxesPerPallet,itemsPerBox,
										costPerBox, pricePerBox, i, j, k);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							warehouse[i][j][k] = 1;
							registeredPallets[index] = new Pallet(
									newIds[index], productID,
									noOfBoxesPerPallet, itemsPerBox,
									expiaryDate, i, j, k, pricePerBox,
									costPerBox);
							flag = true;
						}
					}
				}
			}
		}
		return registeredPallets;
	}

	public synchronized int[][][] getPositionsInWarehouse(
			ArrayList<Object[]> currentpositions) {
		int[][][] positions = new int[35][45][5];
		for (int i = 0; i < currentpositions.size(); i++) {
			int aisle = (int) currentpositions.get(i)[0];
			int depth = (int) currentpositions.get(i)[1];
			int floor = (int) currentpositions.get(i)[2];
			positions[aisle][depth][floor] = 1;
		}
		return positions;
	}

	public synchronized int generateIdNumber() {
		Random random = new Random();
		return (random.nextInt(900000) + 100000);
	}

	public synchronized ArrayList<Integer> getPalletsIds() {
		String sql = "SELECT id FROM Inventory.pallet;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return ids;
		}
		for (int i = 0; i < list.size(); i++) {
			ids.add((Integer) list.get(i)[0]);
		}
		return ids;
	}

	public synchronized ArrayList<Product> getProductsWithStocksUnder(int limit) {
		String sql = "select * from inventory.product s WHERE s.id=(select p.productid from inventory.pallet p WHERE p.productid=p.productid  and s.id=p.productid GROUP BY p.productid HAVING sum(p.nrofboxesperpallet)<?);";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			list = database.query(sql, limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return products;
		}
		for (int i = 0; i < list.size(); i++) {
			int ID = (int) list.get(i)[0];
			String name = (String) list.get(i)[1];
			String catName = (String) list.get(i)[2];
			products.add(new Product(ID, name, catName));
		}
		return products;
	}

	public synchronized Product getProductWithStocksUnder(int id, int limit) {
		String sql = "select * from inventory.product s WHERE s.id=(select p.productid from inventory.pallet p WHERE p.productid=p.productid  and s.id=p.productid GROUP BY p.productid HAVING sum(p.nrofboxesperpallet)<?) and s.id=?;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Product product = null;
		try {
			list = database.query(sql, limit, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return product;
		}
		product = new Product((int) list.get(0)[0], (String) list.get(0)[1],
				(String) list.get(0)[2]);
		return product;
	}

	public synchronized boolean productIdExists(int productID) {
		String sql = "SELECT id FROM Inventory.product;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return false;
		} else {
			for (int i = 0; i < list.size(); i++) {
				if ((int) list.get(i)[0] == productID) {
					return true;
				}
			}
		}
		return false;
	}

	public synchronized boolean addCustomer(Customer customer) {
		String sql = "INSERT INTO Inventory.customer VALUES(?,?,?,?,?,?);";
		int id = customer.getID();
		String name = customer.getName();
		String street = customer.getstreetAndNumber();
		int postcode = customer.getPostCode();
		int telNo = customer.getPhoneNumber();
		String email = customer.getEmail();
		try {
			database.update(sql, id, name, street, postcode, telNo, email);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public synchronized long getStockForProduct(int id) {
		String sql = "SELECT sum(p.nrofboxesperpallet) FROM inventory.pallet p WHERE p.productid=?;";
		ArrayList<Object[]> result = new ArrayList<Object[]>();
		long stock = -1;
		try {
			result = database.query(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result.size() == 0) {
			return stock;
		}
		stock = (long) result.get(0)[0];
		return stock;
	}

	public synchronized ArrayList<Customer> getAllCustomers() {
		String sql = "SELECT * FROM Inventory.customer;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return customers;
		}
		for (int i = 0; i < list.size(); i++) {
			int ID = (int) list.get(i)[0];
			String name = (String) list.get(i)[1];
			String streetAndNumber = (String) list.get(i)[2];
			int postCode = (int) list.get(i)[3];
			String email = (String) list.get(i)[5];//switched 
			int phoneNo = (int) list.get(i)[4];
			customers.add(new Customer(ID, name, streetAndNumber, postCode,
					email, phoneNo));
		}
		return customers;
	}

	public synchronized Customer getCustomerById(int id) {
		String sql = "SELECT * FROM Inventory.customer WHERE id=?;";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Customer customer = null;
		try {
			list = database.query(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return customer;
		}
		int ID = (int) list.get(0)[0];
		String name = (String) list.get(0)[1];
		String streetAndNumber = (String) list.get(0)[2];
		int postCode = (int) list.get(0)[3];
		String email = (String) list.get(0)[5];
		int phoneNo = (int) list.get(0)[4];
		customer = new Customer(ID, name, streetAndNumber, postCode, email,
				phoneNo);
		return customer;
	}

	// end

	// ******************05-12-2016 BT------
	public synchronized ArrayList<Pallet> getOnePalletForProduct() {
		ArrayList<Pallet> pallets = new ArrayList<Pallet>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "Select productID,itemsPerBox, sellingPrice from"
				+ " inventory.pallet p group by productID, itemsPerBox, sellingPrice";
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int k = 0; k < list.size(); k++) {
			int productID = (int) list.get(k)[0];
			int itemPerBox = (int) list.get(k)[1];
			double sellingPrice = (double) list.get(k)[2];
			// System.out.println(new Date(day,month,year));
			pallets.add(new Pallet(productID, itemPerBox, sellingPrice));
		}
		return pallets;

	}

	// ****************************06-12-2016--************

	public synchronized boolean idExist(int customerID) {
		String sql = "SELECT  id from inventory.customer where id="
				+ customerID + ";";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		// System.out.println(list.size());
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return false;
		} else {
			return true;
		}

	}

	public synchronized boolean makeOrder(Order tempOrder,
			ArrayList<Item> ItemsinOrder) {
		Date now = new Date(0);
		String OrderNumber = tempOrder.getCustomer() + "" + now.getYear() + ""
				+ now.getMonth() + "" + now.getDay() + "" + now.getHour() + ""
				+ now.getMinute() + "" + now.getSeconds() + "";
		long order = Long.parseLong(OrderNumber);
		String sql = "insert INTO inventory.order"
				+ " (OrderNumber, dateofshipment, dateorderreceived, customerid) VALUES ("
				+ order
				+ ",'"
				+ tempOrder.getDateOfShipment().getDay()
				+ "/"
				+ tempOrder.getDateOfShipment().getMonth()
				+ "/"
				+ tempOrder.getDateOfShipment().getYear()// position of year is
															// switched with day
				+ " " + tempOrder.getDateOfShipment().getHour() + ":"
				+ tempOrder.getDateOfShipment().getMinute() + "' , NOW(),"
				+ tempOrder.getCustomer() + ");";
		// System.out.println("Date of Ship"+tempOrder.getDateOfShipment());
		System.out.println(sql);
		try {
			database.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		for (int i = 0; i < ItemsinOrder.size(); i++) {
			sql = "INSERT INTO inventory.item VALUES("
					+ ItemsinOrder.get(i).getProductID() + ","
					+ ItemsinOrder.get(i).getAmount() + "," + order + ");";
			try {
				database.update(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	// *********07-12-2016***
	public synchronized ArrayList<Order> getAllOrders() {
		ArrayList<Order> result = new ArrayList<Order>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT OrderNumber,dateofshipment,dateorderreceived, customerID"
				+ " FROM Inventory.order where availability= true Order by dateofshipment";
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		} else {
			for (int i = 0; i < list.size(); i++) {
				long OrderNumber = (long) list.get(i)[0];
				java.sql.Timestamp shipmentDate = (java.sql.Timestamp) list
						.get(i)[1];
				LocalDateTime anotherDate = shipmentDate.toLocalDateTime();
				int day = anotherDate.getDayOfMonth();
				int month = anotherDate.getMonthValue();
				int year = anotherDate.getYear();
				int hour = anotherDate.getHour();
				int minute = anotherDate.getMinute();
				Date shipDate = new Date(year, month, day, hour, minute);
				java.sql.Timestamp orderReceivedDate = (java.sql.Timestamp) list
						.get(i)[2];
				anotherDate = orderReceivedDate.toLocalDateTime();
				day = anotherDate.getDayOfMonth();
				month = anotherDate.getMonthValue();
				year = anotherDate.getYear();
				hour = anotherDate.getHour();
				minute = anotherDate.getMinute();
				Date OrderReceivedDate = new Date(year, month, day, hour,
						minute);
				int customerID = (int) list.get(i)[3];
				result.add(new Order(OrderNumber, shipDate, OrderReceivedDate,
						customerID));

			}
		}
		return result;
	}

	public synchronized ArrayList<Item> getAllItemsForAnOrder(long orderNo) {
		ArrayList<Item> result = new ArrayList<Item>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT productid,amount,ordernumber"
				+ " FROM Inventory.Item where ordernumber=" + orderNo;
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		} else {
			for (int i = 0; i < list.size(); i++) {
				int productid = (int) list.get(i)[0];
				int amount = (int) list.get(i)[1];
				long orderNumber = (long) list.get(i)[2];

				result.add(new Item(productid, amount, orderNumber));
			}
		}
		return result;
	}

	public synchronized boolean startAnOrder(long OrderNo, int pickerID) {
		String sql = "update inventory.order" + " set availability =false "
				+ "WHERE ordernumber=" + OrderNo + ";";

		try {
			database.update(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		sql = "INSERT INTO Inventory.pickerorder values (" + OrderNo + ","
				+ pickerID + ");";
		try {
			database.update(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public synchronized ArrayList<Order> getAllOrdersInProgress() {
		ArrayList<Order> result = new ArrayList<Order>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT OrderNumber,dateofshipment,dateorderreceived, customerID"
				+ " FROM Inventory.order where availability= false Order by dateofshipment";
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		} else {
			for (int i = 0; i < list.size(); i++) {
				long OrderNumber = (long) list.get(i)[0];
				java.sql.Timestamp shipmentDate = (java.sql.Timestamp) list
						.get(i)[1];
				LocalDateTime anotherDate = shipmentDate.toLocalDateTime();
				int day = anotherDate.getDayOfMonth();
				int month = anotherDate.getMonthValue();
				int year = anotherDate.getYear();
				int hour = anotherDate.getHour();
				int minute = anotherDate.getMinute();
				Date shipDate = new Date(year, month, day, hour, minute);
				java.sql.Timestamp orderReceivedDate = (java.sql.Timestamp) list
						.get(i)[2];
				anotherDate = orderReceivedDate.toLocalDateTime();
				day = anotherDate.getDayOfMonth();
				month = anotherDate.getMonthValue();
				year = anotherDate.getYear();
				hour = anotherDate.getHour();
				minute = anotherDate.getMinute();
				Date OrderReceivedDate = new Date(year, month, day, hour,
						minute);
				int customerID = (int) list.get(i)[3];
				result.add(new Order(OrderNumber, shipDate, OrderReceivedDate,
						customerID));

			}
		}
		return result;

	}

	public synchronized ArrayList<Integer> getAllPickersPickedAnOrder(
			long orderNo) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT  pickerid"
				+ " FROM Inventory.pickerorder where ordernumber=" + orderNo+";";
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		} else {
			for (int i = 0; i < list.size(); i++) {

				result.add((int) list.get(i)[0]);

			}
		}
		return result;
	}

	public synchronized boolean OrderFinished(long OrderNo) {
		// ArrayList<Item> items=getNOTpickedItemsForAnOrder(OrderNo);
		ArrayList<Item> items = getALLpickedItemsForAnOrder(OrderNo);
		// System.out.println("Size of Picked Items"+items.size());
		ArrayList<Pallet> pallets = new ArrayList<Pallet>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		double totalPrice = 0;
		String sql = "SELECT OrderNumber,dateofshipment,dateorderreceived, customerID"
				+ " FROM Inventory.order where OrderNumber=" + OrderNo + ";";
		try {
			list = database.query(sql);
			pallets = getOnePalletForProduct();// if there is no pallets?there
												// is
												// no price
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if (list.size() == 0) {
			return false;
		} else {
			for (int j = 0; j < items.size(); j++) {
				for (int k = 0; k < pallets.size(); k++) {
					if (items.get(j).getProductID() == pallets.get(k)
							.getproductID()) {
						// System.out.println("found price and id");
						totalPrice += pallets.get(k).getPrice()
								* (items.get(j).getAmount() - items.get(j)
										.getShortage());
						break;
					}
				}

			}
			for (int i = 0; i < list.size(); i++) {
				long OrderNumber = (long) list.get(i)[0];
				/*
				 * INSERT INTO inventory.invoice VALUES (invoiceno, customerid,
				 * dateofshipment, dateoforderreceived, totalexvat, vat,
				 * totalinclvat);
				 */
				sql = "INSERT INTO inventory.invoice  VALUES ("
						+ list.get(i)[0] + ", " + list.get(i)[3] + ", '"
						+ list.get(i)[1] + "','" + list.get(i)[2] + "',"
						+ totalPrice + "," + (totalPrice * 0.25) + ", "
						+ (totalPrice + (totalPrice * 0.25)) + ");";
				try {
					// System.out.println(sql);
					database.update(sql);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}

			}
			sql = "DELETE FROM Inventory.order WHERE ordernumber=" + OrderNo
					+ " ;";
			try {
				database.update(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}

	public synchronized ArrayList<Item> getNOTpickedItemsForAnOrder(long orderNo) {
		ArrayList<Item> result = new ArrayList<Item>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT productid,amount,ordernumber"
				+ " FROM Inventory.Item where ordernumber=" + orderNo
				+ " AND picked=false ;";
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		} else {
			for (int i = 0; i < list.size(); i++) {
				int productid = (int) list.get(i)[0];
				int amount = (int) list.get(i)[1];
				long orderNumber = (long) list.get(i)[2];

				result.add(new Item(productid, amount, orderNumber));
			}
		}
		return result;
	}

	// make a method count pallets when the admin starts and there are products
	// less than 5pallets
	// it notify

	public synchronized boolean updatePickedItemOffPallets(
			ArrayList<Item> pickedItems) {
		// make a field in items for short product*********
		ArrayList<Pallet> pallets = new ArrayList<Pallet>();
		int productID;
		Pallet firstPallet;
		int amountNeeded;
		int amountResult;
		ArrayList<Integer> toDeletePallets = new ArrayList<Integer>();
		for (int i = 0; i < pickedItems.size(); i++) {
			productID = pickedItems.get(i).getProductID();
			amountNeeded = pickedItems.get(i).getAmount();
			pallets = getAllPalletsForOneProduct(productID);

			// change the picked to true in the item table regardless of the
			// amount
			String sql = "update inventory.item" + " set picked =true "
					+ "WHERE ordernumber=" + pickedItems.get(i).getOrderNo()
					+ "AND productid=" + pickedItems.get(i).getProductID()
					+ ";";
			try {
				database.update(sql);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			while (pallets.size() > 0 && amountNeeded > 0) {
				firstPallet = pallets.get(0);
				amountResult = firstPallet.getNoOfBoxesPerPallet()
						- amountNeeded;
				if (amountResult > 0) {
					// update the NoOfBoxesOnPallet and insert 0 as the short
					// items
					// break;
					sql = "update inventory.item" + " set Short =0 "
							+ "WHERE ordernumber="
							+ pickedItems.get(i).getOrderNo()
							+ "AND productid="
							+ pickedItems.get(i).getProductID() + ";";
					try {
						database.update(sql);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
					sql = "update inventory.pallet"
							+ " set NrOfBoxesPerPallet =" + amountResult
							+ " WHERE id=" + firstPallet.getID()
							+ " AND productid="
							+ pickedItems.get(i).getProductID() + " ;";
					try {
						database.update(sql);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
					amountNeeded = 0;
					break;
				}
				if (amountResult <= 0) {
					toDeletePallets.add(firstPallet.getID());
					pallets.remove(firstPallet);
					amountNeeded = Math.abs(amountResult);
				}
			}
			if (amountNeeded >= 0) {
				// update the item table with the number of short items
				sql = "update inventory.item" + " set Short =" + amountNeeded
						+ " WHERE ordernumber="
						+ pickedItems.get(i).getOrderNo() + " AND productid="
						+ pickedItems.get(i).getProductID() + ";";
				try {
					database.update(sql);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}

			for (int j = 0; j < toDeletePallets.size(); j++) {

				sql = "DELETE FROM Inventory.pallet WHERE id="
						+ toDeletePallets.get(j) + ";";
				try {
					database.update(sql);
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}

		return true;
	}

	public synchronized ArrayList<Pallet> getAllPalletsOfAProductOrderedByDate(
			int productID) {
		String sql = "SELECT id,nrofboxesperpallet,expirydate FROM Inventory.Pallet WHERE ProductId="
				+ productID + "ORDER BY expirydate;";
		ArrayList<Pallet> result = new ArrayList<Pallet>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			list = database.query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0)
			return result;
		else {
			for (int k = 0; k < list.size(); k++) {
				int idPallet = (int) list.get(k)[0];
				int noOfBoxesPerPallet = (int) list.get(k)[1];
				java.sql.Date expiryDate = (java.sql.Date) list.get(k)[2];
				LocalDate anotherDate = expiryDate.toLocalDate();
				int day = anotherDate.getDayOfMonth();
				int month = anotherDate.getMonthValue();
				int year = anotherDate.getYear();
				// System.out.println(new Date(day,month,year));
				result.add(new Pallet(idPallet, noOfBoxesPerPallet, new Date(
						day, month, year)));
			}
			return result;
		}

	}

	public synchronized ArrayList<Item> getALLpickedItemsForAnOrder(long orderNo) {
		// System.out.println("Order nymber inside get all picked"+orderNo);
		ArrayList<Item> result = new ArrayList<Item>();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT productid,amount,ordernumber, short"
				+ " FROM Inventory.Item where ordernumber= " + orderNo
				+ " AND picked =true;";
		// System.out.println("This is the SQL: "+sql);
		try {
			list = database.query(sql);
			// System.out.println("size from inside get all picked "+list.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return result;
		} else {
			for (int i = 0; i < list.size(); i++) {
				int productid = (int) list.get(i)[0];
				int amount = (int) list.get(i)[1];
				long orderNumber = (long) list.get(i)[2];
				int shortage = (int) list.get(i)[3];
				result.add(new Item(productid, amount, orderNumber, shortage));
			}
		}
		return result;
	}

	public synchronized boolean incompleteAnOrder(long OrderNo, int pickerID) {
		String sql = "update inventory.order" + " set availability =true "
				+ "WHERE ordernumber=" + OrderNo + ";";

		try {
			database.update(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
