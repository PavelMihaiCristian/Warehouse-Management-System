package util;

import java.io.Serializable;

public class Customer implements Serializable {

	private int ID;
	private String name;
	private String streetAndNumber;
	private int postCode;
	private String email;
	private int phoneNo;

	public Customer(int ID, String name, String streetAndNumber, int postCode,
			String email, int phoneNo) {
		this.ID = ID;
		this.name = name;
		this.streetAndNumber = streetAndNumber;
		this.postCode = postCode;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setstreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}

	public String getstreetAndNumber() {
		return streetAndNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getPhoneNumber() {
		return phoneNo;
	}
	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	@Override
	public String toString() {
		return "ID=" + ID + ", name=" + name + ", streetAndNumber="
				+ streetAndNumber + ", postCode=" + postCode + ", email="
				+ email + ", phoneNo=" + phoneNo;
	}
}
