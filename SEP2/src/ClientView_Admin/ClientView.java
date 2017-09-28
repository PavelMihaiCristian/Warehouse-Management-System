package ClientView_Admin;

import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ClientView 
{
   private Scanner key;
   private GregorianCalendar calendar=new GregorianCalendar();
   
   public ClientView()
   {
      key = new Scanner(System.in);
   }
   
	public void menu()
	{
		System.out.println("1. Get all available vehicles within a given date interval");
		System.out.println("2. Get current bookings within a given date interval");
		System.out.println("3. Reserve a Vehicle");
		System.out.println("4. Return a vehicle");
		System.out.println("5. Complete booking process");
		System.out.println("6. Cancel reservation");
		System.out.println("0. Exit");
	}
	
   public void login()
   {
      System.out.println("Are you an administrator or desk clerk?");
      System.out.println("1. Administrator");
      System.out.println("2. Clerk");
   }

   public void insertDate(int x)
   {
      System.out.println("Insert " + x + "° date: ");
   }

   public void insertDate(String msg)
   {
      System.out.println("Insert " + msg + ": ");
   }

   public void showMessage(String msg)
   {
	   if(msg.equals("")){
		   System.out.println("No records found");
	   }
	   else{
		   System.out.println(msg);
	   }
   }
   
   public String readStringFromUser()
   {
      return key.nextLine();
   }
   
   public void getRegistrationNumber(){
	   System.out.println("Enter vehicles registration number");
   }
   public int readIntFromUser()
   {
	   boolean flag = true;
	   int nr = -1;
	   while(flag)
	   {
		   try
		   {
			  nr = key.nextInt();
			  flag = false;
		   }
		   catch(InputMismatchException e)
		   {
			   flag=true;
			   System.out.println("Enter an integer.");
			   key.nextLine();
		   }
	   }
	   return nr;
   }

   
   public void showVehicleType(){
	   System.out.println("Select the desired type of vehicle ");
	   System.out.println("1. Family Cars");
	   System.out.println("2. Vans");
	   System.out.println("3. Moving Trucks");
	   System.out.println("4. AutoCamper");
   }
   
  
   
   public String getCustomerData(String msg){
	   String temp;
	   System.out.println("Introduce customers "+msg);
	   temp=readStringFromUser();
	   return temp;
   }
}
