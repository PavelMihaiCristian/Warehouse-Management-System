package util;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * A class that will help to keep track of the pick-up date and delivery date
 * @author mihai cristian pavel
 * @version 1.0
 */
public class Date implements Serializable
{
   private int day;
   private int month;
   private int year;
   private int hour;
   private int minute;
   private int seconds;
  
   /**
    * A constructor initializing all the parameters setting up the day,month,year,hour and minute
    * @param day the day
    * @param month the month
    * @param year the year
    * @param hour the hour
    * @param minute the minutes
    */
   public Date(int day,int month,int year,int hour,int minute)
   {
      this.day=day;
      this.month=month;
      this.year=year;
      this.hour=hour;
      this.minute=minute;
   }
   
   public Date(int day,int month,int year)
   {
      this.day=day;
      this.month=month;
      this.year=year;
   }
   /**
    * A no-argument constructor that sets the parameters to the current time,but the minute will be set to the upcoming quarter 
    */
   public Date()
   {
      GregorianCalendar today=new GregorianCalendar();
      this.day=today.get(GregorianCalendar.DAY_OF_MONTH);
      this.month=today.get(GregorianCalendar.MONTH)+1;
      this.year=today.get(GregorianCalendar.YEAR);
      this.hour=today.get(GregorianCalendar.HOUR_OF_DAY);
      if(today.get(GregorianCalendar.MINUTE)<=15){
                           this.minute=15;
                           }
      else{
         if(today.get(today.MINUTE)<=30){
                             this.minute=30;
                              }
         else {
               if(today.get(today.MINUTE)<=45) {
                                    this.minute=45;
                                    }
               else {
                  this.minute=0;
               }
               }
      }
   }
   /**
    * a set day method that will set the day parameter to the day received as a argument
    * @param day the day 
    */
   public void setDay(int day)
   {
      this.day=day;
   }
   /**
    * @param month
    * a set method for setting the month parameter to the value that is given to the month argument
    */
   public void setMonth(int month)
   {
      this.month=month;
   }
   /**
    * @param year
    * a set method for setting the year parameter to the value that is given by the year argument
    */
   public void setYear(int year)
   {
      this.year=year;
   }
   /**
    * @param hour
    * a set method for setting the hour parameter to the value given by the hour argument
    */
   public void setHour(int hour)
   {
      this.hour=hour;
   }
   /**
    * @param minute
    * a set method for setting the minute parameter to the value given by the minute argument
    */
   public void setMinute(int minute)
   {
      this.minute=minute;
   }
   /**
    * a get method for the day field
    * @return the value of the day parameter
    */
   public int getDay()
   {
      return day;
   }
   /**
    * a get method for the month field
    * @return the value of the month parameter
    */
   public int getMonth()
   {
      return month;
   }
   /**
    * a get methid for the year field
    * @return the value of the year parameter
    */
   public int getYear()
   {
      return year;
   }
   /**
    * a get method for the hour field
    * @return the value of the hour parameter
    */
   public int getHour()
   {
      return hour;
   }
   /**
    * a get method for the minute field
    * @return the value of the minute parameter
    */
   public int getMinute()
   {
      return minute;
   }
   /**
    * a copy method
    * @return a copy of the date object
    */
   public Date copy()
   {
      return new Date(day,month,year,hour,minute);
   }
   /**
    * a method that is changing the date by adding one day 
    */
   public void nextDay()
   {
      if (getDay()< daysInMonth()) { day+=1;
                                     setDay(day);
                                     
                                   }
                        else if(getDay()==daysInMonth())if (getMonth()==12){ year+=1;
                                                                              month=1;
                                                                              day=1;
                                                                              setDay(day);
                                                                              setMonth(month);
                                                                              setYear(year);
                                                                              }  
                                                              else {month+=1;
                                                                    day=1;
                                                                    setDay(day);
                                                                    setMonth(month);
                                                              }
      }
   /**
    * a method that is changing the date by adding a number of days given by the argument that the method receives
    * @param days is giving us the exact number of days that the system should go forward to
    */
   public void nextDays(int days)
   {
      for(int i=1;i<=days;i++)
      {
         nextDay();
      }
   }
   /**
    * a method that finds the number of days that are in a certain month
    * @return the number of days i a month corresponding to the parameter month
    */
   public int daysInMonth()
   {
      if (month==1 || month==3 || month==5 || month==7 || month==8 ||month==10 || month==12)
      {
        return 31; 
      }
         else if (month==2 && isLeapYear()) {return 29;}
                  else if(month==4 || month==6 || month==9 || month==11) return 30;
                               else {return 28;}
   }
   /**
    * a method testing the year parameter
    * @return a boolean value of true if the year is a leap year and false if is not a leap year
    */
   public boolean isLeapYear()
   {
      if(year%4==0)if(year%400==0) {return true;}
                  else if(year%100==0) {return false;}
                           else {return true;}
        else {return false;}
   }
   /**
    * a method to compare if the date is before the date given as a parameter
    * @param date the date is compared to
    * @return the boolean value if the date is before the one given as a parameter , or false if it is not
    */
   public boolean isBefore(Date date)
   {
      if(year<date.year) 
         {
         return true;
         }
      else {
         if(year==date.year)
         {
            if(month<date.month)
            {
               return true;
            }
            else
            {
               if(month==date.month)
               {
                  if(day<date.day)
                  {
                     return true;
                  }
                  else
                  {
                     if(day==date.day)
                     {
                        if(hour<date.hour) 
                           {
                              return true;
                           }
                        else
                        {
                           if(hour==date.hour)
                           {
                              if(minute<date.minute)
                              {
                                 return true;
                              }
                              else
                              {
                                 if(minute==date.minute)
                                 {
                                    return false;
                                 }
                                 else
                                 {
                                    return false;
                                 }
                              }
                            }
                           else
                           {
                              return false;
                           }
                        }
                     }
                     else
                     {
                        return false;
                     }
                  }
               }
               else
               {
                  return false;
               }
            }
         }
         else
         {
            return false;
         }
      }
   }
   
   public Date(int seconds)
   {
      GregorianCalendar today=new GregorianCalendar();
      this.day=today.get(GregorianCalendar.DAY_OF_MONTH);
      this.month=today.get(GregorianCalendar.MONTH)+1;
      this.year=today.get(GregorianCalendar.YEAR);
      this.hour=today.get(GregorianCalendar.HOUR_OF_DAY);
      this.minute = today.get(GregorianCalendar.MINUTE);
      this.seconds = today.get(GregorianCalendar.SECOND);
   }
   
   public int getSeconds(){
	   return seconds;
   }
   /**
    * a toString method for the Date class
    * @return a String object containing all the parameters of the Date object
    */
   public String toString()
   {
      return day+"/"+month+"/"+year+" "+hour+":"+minute;
   }
   
   public String toString2()
   {
      return day+"/"+month+"/"+year;//+" "+hour+":"+minute;
   }
   /**
    * a equals method that compares 2 Date objects
    * @return a boolean value that shows if the 2 Object are the same by returning true, or false if they are different
    */
   public boolean equals(Object obj)
   {
      if(!(obj instanceof Date)) return false;
      Date other=(Date)obj;
      return day==other.day && month==other.month && year==other.year && hour==other.hour && minute==other.minute;
   }
   
}
