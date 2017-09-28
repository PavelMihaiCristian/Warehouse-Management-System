package util;

import java.io.Serializable;

public class Pickers implements Serializable {
	private int id;
	private String name;
	private long CPR;
	private String street;
	private int postCode;
	private long phoneNo;
	private String email;

	public Pickers(String name, long CPR, String street, int postCode,
			long phoneNo, String email) {
		this.name = name;
		this.CPR = CPR;
		this.street = street;
		this.postCode = postCode;
		this.phoneNo = phoneNo;
		this.email = email;
		this.id = 0;
	}

	public Pickers(int id, String name, long CPR, String street, int postCode,
			long phoneNo, String email) {
		this.id = id;
		this.name = name;
		this.CPR = CPR;
		this.street = street;
		this.postCode = postCode;
		this.phoneNo = phoneNo;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCPR(int CPR) {
		this.CPR = CPR;
	}

	public long getCPR() {
		return CPR;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet() {
		return street;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String toString() {
		return "Name:" + name + " Cpr:" + CPR;
	}

	public String toString2() {
		return id+" "+name+" "+CPR+" "+postCode+" "+street+" "+phoneNo+" "+email;
	}

}
