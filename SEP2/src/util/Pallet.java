package util;

import java.io.Serializable;

public class Pallet implements Serializable {
	private int ID;
	private int productID;
	private int noOfBoxesPerPallet;
	private int itemsPerBox;
	private Date expiryDate;
	private int aisle;
	private int depth;
	private int floor;
	private double pricePerBox;
	private double costPerBox;

	public Pallet(int ID, int productID, int noOfBoxesPerPallet,
			int itemsPerBox, Date expiaryDate, int aisle, int depth, int floor,
			double pricePerBox, double costPerBox) {
		this.ID = ID;
		this.productID = productID;
		this.noOfBoxesPerPallet = noOfBoxesPerPallet;
		this.itemsPerBox = itemsPerBox;
		this.expiryDate = expiaryDate;
		this.aisle = aisle;
		this.depth = depth;
		this.floor = floor;
		this.pricePerBox = pricePerBox;
		this.costPerBox = costPerBox;
	}

	public Pallet(int productID, int noOfBoxesPerPallet, int itemsPerBox,
			Date expiaryDate, double pricePerBox, double costPerBox) {
		this.productID = productID;
		this.noOfBoxesPerPallet = noOfBoxesPerPallet;
		this.itemsPerBox = itemsPerBox;
		this.expiryDate = expiaryDate;
		this.pricePerBox = pricePerBox;
		this.costPerBox = costPerBox;
	}

	public Pallet(int ID, int noOfBoxesPerPallet, Date expiryDate) {
		this.ID = ID;
		this.noOfBoxesPerPallet = noOfBoxesPerPallet;
		this.expiryDate = expiryDate;
	}

	public Pallet(int productID, int itemsPerBox, double pricePerBox) {

		this.pricePerBox = pricePerBox;
		this.productID = productID;
		this.itemsPerBox = itemsPerBox;
	}

	public Pallet(int ID, int productID, int noOfBoxesPerPallet, Date expiryDate) {
		this.productID = productID;
		this.ID = ID;
		this.noOfBoxesPerPallet = noOfBoxesPerPallet;
		this.expiryDate = expiryDate;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public int getproductID() {
		return productID;
	}

	public void setNoOfBoxes(int noOfBoxesPerPallet) {
		this.noOfBoxesPerPallet = noOfBoxesPerPallet;
	}

	public int getNoOfBoxes() {
		return noOfBoxesPerPallet;
	}

	public void setItemsPerBox(int itemsPerBox) {
		this.itemsPerBox = itemsPerBox;
	}

	public int getItemsPerBox() {
		return itemsPerBox;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setAisle(int aisle) {
		this.aisle = aisle;
	}

	public int getAisle() {
		return aisle;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getDepth() {
		return depth;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getFloor() {
		return floor;
	}

	public void setNoOfBoxesPerPallet(int noOfBoxesPerPallet) {
		this.noOfBoxesPerPallet = noOfBoxesPerPallet;
	}

	public int getNoOfBoxesPerPallet() {
		return noOfBoxesPerPallet;
	}

	public void setPrice(double pricePerBox) {
		this.pricePerBox = pricePerBox;
	}

	public double getPrice() {
		return pricePerBox;
	}

	public void setCost(double costPerBox) {
		this.costPerBox = costPerBox;
	}

	public double getCost() {
		return costPerBox;
	}

	public String toString() {
		return "Pallet Id:" + ID + "  Nr of Boxes:" + noOfBoxesPerPallet
				+ "  Expires:" + expiryDate;// productID+
											// noOfBoxesPerPallet+" "+itemsPerBox
											// +" "+pricePerBox+" "+costPerBox
											// +" "+aisle +" "+depth +" "+floor
											// ;
	}

	public String toStringForListOfExpiry() {
		return "Product Id:" + productID + "  Pallet Id:" + ID
				+ "  Nr of Boxes:" + noOfBoxesPerPallet + "  Expires:"
				+ expiryDate;// productID+ noOfBoxesPerPallet+" "+itemsPerBox
								// +" "+pricePerBox+" "+costPerBox +" "+aisle
								// +" "+depth +" "+floor ;
	}

	public String toStringForRegistraring() {
		return "Pallet Id:" + ID + " Product id:" + productID + " Expires:"
				+ expiryDate + " Boxes per pallet:" + noOfBoxesPerPallet
				+ " Items per box:" + itemsPerBox + "Selling price:"
				+ pricePerBox + " Buying price:" + costPerBox + " Aisle:"
				+ aisle + " Depth:" + depth + " Floor:" + floor;
	}

}
