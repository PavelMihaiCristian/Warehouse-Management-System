package util;

import java.io.Serializable;

public class Supplier implements Serializable
{
	
	private String supplierName;
	private int phoneNumber;
	private String email;
	private String street;
	private int postalCode;
	
	public Supplier(String supplierName, int phoneNumber, String email, String street, int postalCode)
	{
		this.supplierName = supplierName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.street = street;
		this.postalCode = postalCode;
	}
	
	public void setSupplierName(String supplierName)
	{
		this.supplierName = supplierName;
	}
	
	public String getSupplierName()
	{
		return supplierName;
	}
	
	public void setPhoneNumber(int phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	
	public int getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setStreet(String street)
	{
		this.street = street;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public void setPostalCode(int postalCode)
	{
		this.postalCode = postalCode;
	}
	
	public int getPostalCode()
	{
		return postalCode;
	}
	public String toString() {
		return "Supplier =" + supplierName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", street=" + street
				+ ", postalCode=" + postalCode;
	}

}
