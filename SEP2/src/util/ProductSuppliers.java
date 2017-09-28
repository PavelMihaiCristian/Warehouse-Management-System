package util;

import java.io.Serializable;

public class ProductSuppliers implements Serializable
{
	private int productID;
	private String supplierName;
	
	public ProductSuppliers(int productID, String supplierName)
	{
		this.productID = productID;
		this.supplierName = supplierName;
	}
	
	public void setProductID(int productID)
	{
		this.productID = productID;
	}
	
	public int getProductID()
	{
		return productID;
	}
	
	public void setSupplierName(String supplierName)
	{
		this.supplierName = supplierName;
	}
	
	public String getSuplierName()
	{
		return supplierName;
	}
}
