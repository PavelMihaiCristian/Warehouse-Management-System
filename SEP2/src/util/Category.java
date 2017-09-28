package util;

import java.io.Serializable;

public class Category implements Serializable
{
	private String name;
	private int noOfProducts;

	public Category(String name)
	{
		this.name = name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setNoOfProducts(int noOfProducts)
	{
		this.noOfProducts = noOfProducts;
	}

	public int getNoOfProducts()
	{
		return noOfProducts;
	}
}
