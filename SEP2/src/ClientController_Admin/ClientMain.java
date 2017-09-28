package ClientController_Admin;

import java.rmi.RemoteException;

import loginView.login;
import ClientController_Customer.CustomerController;
import ClientController_Picker.PickerController;
import ClientModel.ClientModel;
import ClientView_Admin.DeleteProduct;
import ClientView_Admin.GUIMain;
import ClientView_Customer.GUI_Main_Customer;
import ClientView_Picker.PickerGUIMain;

public class ClientMain
{
   private static boolean allowPicker = false;
   private static boolean allowAdmin = false;
   private static boolean allowCustomer = false;

   public static void setAllowPicker(boolean allowPicker)
   {
      ClientMain.allowPicker = allowPicker;
   }

   public static void setAllowAdmin(boolean allowAdmin)
   {
      ClientMain.allowAdmin = allowAdmin;
   }

   public static void setAllowCustomer(boolean allowCustomer)
   {
      ClientMain.allowCustomer = allowCustomer;
   }

   public static void main(String[] args)
   {
      login l = new login();
      while (true)
      {
         if (allowPicker)
         {
            ClientModel model = new ClientModel();
            PickerGUIMain view = new PickerGUIMain();
            try
            {
               PickerController controller=new PickerController(view, model);
            }
            catch (RemoteException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            System.out.println("Picker Logged In");
            break;
            
         }
         else if (allowAdmin)
         {
            ClientModel model = new ClientModel();
            GUIMain view = new GUIMain();
            System.out.println("Admin Logged In");
            try
            {
               ClientController controller = new ClientController(view, model);
            }
            catch (RemoteException e)
            {
               e.printStackTrace();
            }
            break;
         }
         else if (allowCustomer)
         {
            ClientModel model = new ClientModel();
            GUI_Main_Customer view = new GUI_Main_Customer();
            System.out.println("Customer Logged In");
            try
            {
               CustomerController controller = new CustomerController(view, model);
            }
            catch (RemoteException e)
            {
               e.printStackTrace();
            }
            System.out.println("Customer Logged In");
            break;
         }
         
      }
      System.out.println("User Chosen");

   }
}
