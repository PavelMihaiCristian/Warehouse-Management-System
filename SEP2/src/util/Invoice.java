package util;

import java.io.Serializable;

public class Invoice implements Serializable{

	private long orderNumber;
	private int customerId;
	private Date dateOfShipment;
	private Date dateOfOrderReceived;
	private double totalExVAT;
	private double VAT;
	private double totalInclVAT;
	
	
	public Invoice(long orderNumber, int customerId, Date dateOfShipment,
			Date dateOfOrderRceived,double totalExVAT, double vAT, double totalInclVAT) {
		this.orderNumber = orderNumber;
		this.customerId = customerId;
		this.dateOfShipment = dateOfShipment;
		this.dateOfOrderReceived=dateOfOrderRceived;
		this.totalExVAT = totalExVAT;
		VAT = vAT;
		this.totalInclVAT = totalInclVAT;
	}
	public Date getDateOfOrderRceived() {
		return dateOfOrderReceived;
	}
	public void setDateOfOrderRceived(Date dateOfOrderRceived) {
		this.dateOfOrderReceived = dateOfOrderRceived;
	}

	public long getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Date getDateOfShipment() {
		return dateOfShipment;
	}
	public void setDateOfShipment(Date dateOfShipment) {
		this.dateOfShipment = dateOfShipment;
	}
	public double getTotalExVAT() {
		return totalExVAT;
	}
	public void setTotalExVAT(double totalExVAT) {
		this.totalExVAT = totalExVAT;
	}
	public double getVAT() {
		return VAT;
	}
	public void setVAT(double vAT) {
		VAT = vAT;
	}
	public double getTotalInclVAT() {
		return totalInclVAT;
	}
	public void setTotalInclVAT(double totalInclVAT) {
		this.totalInclVAT = totalInclVAT;
	}
	
	public String toString() {
		return "Invoice=" + orderNumber + ", customerId="
				+ customerId + ", dateOfShipment=" + dateOfShipment
				+ ", totalExVAT=" + totalExVAT + ", VAT=" + VAT
				+ ", totalInclVAT=" + totalInclVAT;
	}
	
}
