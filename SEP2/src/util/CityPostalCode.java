package util;

import java.io.Serializable;

public class CityPostalCode implements Serializable
{
	private String city;
	private int postalCode;
	
	public CityPostalCode(String city, int postalCode)
	{
		this.city = city;
		this.postalCode = postalCode;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setPostalCode(int postalCode)
	{
		this.postalCode = postalCode;
	}
	
	public int getPostalCode()
	{
		return postalCode;
	}
}
