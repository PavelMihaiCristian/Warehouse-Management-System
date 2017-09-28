package util;

import java.io.Serializable;

public class Item implements Serializable
{

   private int ID;
   private int amount;
   private long orderNo;
   private boolean picked;
   private int shortage;

   public Item(int ProductID, int amount, long orderNo, boolean picked)
   { 
      this.ID = ProductID;
      this.amount = amount;
      this.orderNo = orderNo;
      this.picked=picked;
   }
   
   public Item(int ProductID, int amount, long orderNo, int shortage)
   { 
      this.ID = ProductID;
      this.amount = amount;
      this.orderNo = orderNo;
      this.shortage=shortage;
   }
   
   public Item(int ProductID, int amount, long orderNo)
   {
      this.ID = ProductID;
      this.amount = amount;
      this.orderNo = orderNo;
   }
   
   public Item(int ID, int amount)
   {
      this.ID = ID;
      this.amount = amount;
   }

   public int getID()
   {
      return ID;
   }
   
   public int getProductID(){
	   return ID;
   }

   public void setAmount(int amount)
   {
      this.amount = amount;
   }

   public int getAmount()
   {
      return amount;
   }
   public void setOrderNo(long orderNo)
   {
      this.orderNo = orderNo;
   }

   public long getOrderNo()
   {
      return orderNo;
   }
   
   public int getShortage()
   {
      return shortage;
   }
   public void setShortage(int shortage)
   {
      this.shortage = shortage;
   }
   public boolean isPicked()
   {
      return picked;
   }
   public void setPicked(boolean picked)
   {
      this.picked = picked;
   }

   public String toString(){
	   return "Product id:"+ID+" Amount:"+amount;
   }
}
