package util;

import java.io.Serializable;

public class Order implements Serializable
{

   private long orderNo;
   private Date dateOfShipment;
   private Date orderRecived;
   private int customerId;
   private boolean availability;

   public Order(long OrderNo, Date dateOfShipment, Date dateRecived,int customerId)
   {
      this.orderNo = OrderNo;
      this.dateOfShipment = dateOfShipment;
      this.orderRecived = dateRecived;
      this.customerId = customerId;
      this.availability = true;
   }
   
   public Order(Date dateOfShipment, int customerID)
   {
 
      this.dateOfShipment = dateOfShipment;
 
      this.customerId = customerID;

   }

   public void setOrderNo(long orderNo)
   {
      this.orderNo = orderNo;
   }

   public long getOrderNo()
   {
      return orderNo;
   }

   public void setDateOfShipment(Date dateOfShipment)
   {
      this.dateOfShipment = dateOfShipment;
   }

   public Date getDateOfShipment()
   {
      return dateOfShipment;
   }

   public void setOrderRecived(Date orderRecived)
   {
      this.orderRecived = orderRecived;
   }

   public Date getOrderRecived()
   {
      return orderRecived;
   }

   public void setCustomer(int customerId)
   {
      this.customerId = customerId;
   }

   public int getCustomer()
   {
      return customerId;
   }

   public void setAvailability(boolean availability)
   {
      this.availability = availability;
   }

   public boolean getAvailability()
   {
      return availability;
   }
   
   public String toString2(){
	   return orderNo+" "+customerId+" "+orderRecived+" "+dateOfShipment;
   }
   
   public String toString(){
	   return "Order No.: " +orderNo+"   Cusomter ID: "+customerId+
	         "   Order Received: "+orderRecived+"   Date Of Shipment: "+dateOfShipment;
   }
}
