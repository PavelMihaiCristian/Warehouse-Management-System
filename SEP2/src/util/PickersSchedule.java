package util;

import java.io.Serializable;


public class PickersSchedule implements Serializable
{
   private int ID;
   private Date checkIn;
   private Date checkOut;
   
   public PickersSchedule(int ID, Date checkIn )
   {
      this.ID = ID;
      this.checkIn = checkIn;
      this.checkOut = null;
   }
   
   public int getID()
   {
      return ID;
   }
   
   public void setCheckIn(Date checkIn)
   {
      this.checkIn = checkIn;
   }
   public Date getCheckIn()
   {
      return checkIn;
   }
   public void setCheckOut(Date checkOut)
   {
      this.checkOut= checkOut;
   }
   
   public Date checkOut()
   {
      return checkOut;
   }
   
   public String toString(){
	   return ID+" checked in:"+checkIn.toString2();
   }

}
