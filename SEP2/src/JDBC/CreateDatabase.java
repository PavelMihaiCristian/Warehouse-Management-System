package JDBC;

import java.sql.SQLException;

import util.Populate;

public class CreateDatabase {
	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {
		/*
		 * If you have different attributes for driver url name password:
		 * 
		 * MyDatabase db = new MyDatabase("org.postgresql.Driver",
		 * "jdbc:postgresql://localhost:5432/postgres", "postgres", "jens");
		 */
		MyDatabase db = new MyDatabase();

		String sql = "CREATE SCHEMA IF NOT EXISTS Inventory;";

		db.update(sql);

		// -----------------------------
		sql = "CREATE TABLE IF NOT EXISTS Inventory.Category ("
				+ "  CName CHARACTER VARYING (50) NOT NULL,"
				+ "  NrOfProducts Int ," + "  PRIMARY KEY (CName)" + ");";
		db.update(sql);

		// ------------------------------
		// -----------------------------
		sql = "CREATE TABLE IF NOT EXISTS Inventory.CityPostCode ("
				+ "  City CHARACTER VARYING (50) NOT NULL," + "  PostCode Int,"
				+ "  PRIMARY KEY (PostCode)" + ");";
		db.update(sql);

		// ------------------------------
		// -----------------------------
		sql = "CREATE TABLE IF NOT EXISTS Inventory.Supplier ("
				+ "  SupName CHARACTER VARYING (50) NOT NULL,"
				+ "  phone Int NOT NULL,"
				+ "  Email CHARACTER VARYING,"
				+ "  Street CHARACTER VARYING,"
				+ "  PostCode int,"
				+ "  PRIMARY KEY (SupName),"
				+ "  FOREIGN KEY (PostCode)REFERENCES Inventory.CityPostCode(PostCode)"
				+ ");";
		db.update(sql);

		// ------------------------------

		// -----------------------------
		sql = "CREATE TABLE IF NOT EXISTS Inventory.product (" + "  ID Int,"
				+ "  PName CHARACTER VARYING (50) NOT NULL,"
				+ "  CName CHARACTER VARYING (50) NOT NULL,"
				+ "  PRIMARY KEY (ID),"
				+ "  FOREIGN KEY (CName)REFERENCES Inventory.category(CName)"
				+ ");";
		db.update(sql);

		// ------------------------------
		// -----------------------------
		sql = "CREATE TABLE IF NOT EXISTS Inventory.productSupplier ("
				+ "  PID Int,"
				+ "  SupName CHARACTER VARYING (50) ,"
				+ "  PRIMARY KEY (PID,SupName),"
				+ "  FOREIGN KEY (SupName)REFERENCES Inventory.Supplier(SupName)"
				+ ");";
		db.update(sql);

		// ------------------------------
		// -----------------------------
		sql = "CREATE TABLE IF NOT EXISTS Inventory.Pallet (" + "  ID Int,"
				+ "  ProductID Int," + "  NrOfBoxesPerPallet Int NOT NULL,"
				+ "  ExpiryDate date," + "  ItemsPerBox Int NOT NULL,"
				+ "  Cost DOUBLE PRECISION NOT NULL," + "  SellingPrice DOUBLE PRECISION NOT NULL,"
				+ "  Aisle int," + "  Depth int," + "  floor int,"
				+ "  PRIMARY KEY (ID),"
				+ "  FOREIGN KEY (ProductID)REFERENCES Inventory.product(ID)"
				+ ");";
		db.update(sql);

		// ------------------------------
		// ------------------------------
		// -----------------------------
		sql = "CREATE TABLE IF NOT EXISTS Inventory.Pickers ("
				+ "  ID serial,"
				+ "  Cpr BIGINT,"
				+ "  Name CHARACTER VARYING (50),"
				+ "  Street CHARACTER VARYING,"
				+ "  PostCode int,"
				+ "  phone  BIGINT NOT NULL,"
				+ "  Email CHARACTER VARYING,"
				+ "  PRIMARY KEY (ID),"
				+ "  FOREIGN KEY (PostCode)REFERENCES Inventory.CityPostCode(PostCode),"
				+ "  CONSTRAINT cprconstraint UNIQUE (cpr));";
		db.update(sql);

		// ------------------------------
		// -----------------------------
		sql = "CREATE TABLE IF NOT EXISTS Inventory.PickersSchedule ("
				+ "  ID Int," + "  CheckIn timestamp,"
				+ "  CheckOut timestamp," + "  PRIMARY KEY (ID, CheckIn),"
				+ "  FOREIGN KEY (ID)REFERENCES Inventory.Pickers(ID) " + ""
				+ "  ON UPDATE CASCADE ON DELETE CASCADE);";
		db.update(sql);

		// ----------------------------
		// ---------------------------- CUSTOMER TABLE
		sql = "CREATE TABLE IF NOT EXISTS Inventory.Customer ("
				+ "  ID Int,"
				+ "  Name CHARACTER VARYING (50),"
				+ "  Street CHARACTER VARYING,"
				+ "  PostCode Int,"
				+ "  phone Int NOT NULL,"
				+ "  Email CHARACTER VARYING,"
				+ "  PRIMARY KEY (ID),"
				+ "  FOREIGN KEY (PostCode)REFERENCES Inventory.CityPostCode(Postcode)"
				+ ");";
		db.update(sql);

		// ----------------------------
		// ---------------------------- PickerOrder TABLE
		sql = "CREATE TABLE IF NOT EXISTS Inventory.PickerOrder ("
				+ "  OrderNumber BIGINT,"
				+ "  PickerID Int,"
				+ "  PRIMARY KEY (OrderNumber,PickerID),"
				+ "  FOREIGN KEY (PickerID) REFERENCES Inventory.Pickers(ID)"
				//+ "  FOREIGN KEY (OrderNumber) REFERENCES Inventory.Order(OrderNumber)"
				+ ");";
		db.update(sql);

		// ---------------------------- Order TABLE
		sql = "CREATE TABLE IF NOT EXISTS Inventory.Order ("
				+ "  OrderNumber BIGINT,"
				+ "  DateOfShipment timestamp,"
				+ "  DateOrderReceived timestamp,"
				+ "  CustomerID Int,"
				+ "  Availability Boolean default(true),"
				+ "  PRIMARY KEY (OrderNumber),"
				+ "  FOREIGN KEY (CustomerID) REFERENCES Inventory.Customer(ID)"
				+ ");";
		db.update(sql);

		// ----------------------------
		// ---------------------------- Item TABLE
		sql = "CREATE TABLE IF NOT EXISTS Inventory.Item ("
				+ "  ProductID INT,"
				+ "  Amount Int NOT NULL,"
				+ "  OrderNumber BIGINT,"
				+ "  Picked Boolean Default(false),"
				+ "  Short int, "
				+ "  PRIMARY KEY (ProductID, OrderNumber),"
				+ "  FOREIGN KEY (ProductID)REFERENCES Inventory.product(ID)"
				+ ");";
		db.update(sql);

		// ----------------------------

		// ----------------------------
		// ---------------------------- Invoice TABLE
		sql = "CREATE TABLE IF NOT EXISTS Inventory.Invoice ("
				+ "  InvoiceNo BIGINT,"
				+ "  customerId Int NOT NULL,"
				+ "  dateOfShipment timestamp,"
				+ "  dateOfOrderReceived timestamp,"
				+ "  totalExVAT double precision,"
				+ "  VAT double precision,"
				+ "  totalInclVAT double precision,"
				+ "  PRIMARY KEY (InvoiceNo),"
				+ "  FOREIGN KEY (customerId) REFERENCES Inventory.Customer(ID)"
				+ ");";
		db.update(sql);

		// ----------------------------
		Populate cities= new Populate(db);
	}
}
