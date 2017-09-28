package util;

import java.io.Serializable;

public class Product implements Serializable
{
	 private int ID;
	 private String name;
	 private String catName;

	 public Product(int ID, String name, String catName)
	 {
		 this.ID = ID;
		 this.name = name;
		 this.catName = catName;
	 }

	 public void setID(int ID)
	 {
		 this.ID = ID;
	 }

	 public int getID()
	 {
		 return ID;
	 }

	 public void setName(String name)
	 {
		 this.name = name;
	 }

	 public String getName()
	 {
		 return name;
	 }

	 public void setCatName(String catName)
	 {
		 this.catName = catName;
	 }

	 public String getCatName()
	 {
		 return catName;
	 }
	 
	 public String toString2(){
		 return "ID: "+ID+"-  Name: "+name+"  Category Name:"+catName;
	 }
	 public String toString(){
       return ID+"  \t  /   "+name;
    }
}
