package ClientView_Admin;


import java.awt.*;
import javax.swing.*;

public class GUI_AddPicker extends JPanel
{

   private JLabel LCpr, LName, LStreet, LPostCode, LPhone, LEmail;
   private JTextField TCpr, TName, TStreet, TPostCode, TPhone, TEmail;
   private JPanel Pcontainer,Pcontainer2, Pbutton, PCpr, PName, PStreet, PPostCode, PPhone, PEmail;
   private JButton addB;
   private JButton cancelButton;
   
   public GUI_AddPicker()
   {
      createComponents();
   }

   public void createComponents()
   {
      LCpr = new JLabel("CPR: ");
      LName = new JLabel("Name: ");
      LStreet = new JLabel("Street Name & #: ");
      LPostCode = new JLabel("Postal Code: ");
      LPhone = new JLabel("Phone Number: ");
      LEmail = new JLabel("E-mail: ");
      
      TCpr = new JTextField(7);
      TName = new JTextField(7);
      TStreet = new JTextField(7);
      TPostCode = new JTextField(7);
      TPhone = new JTextField(7);
      TEmail = new JTextField(7);
      
      cancelButton=new JButton("Cancel");
      addB = new JButton("Add");
      
      PCpr = new JPanel (new GridLayout(1,2));
      PName = new JPanel (new GridLayout(1,2));
      PStreet =new JPanel (new GridLayout(1,2));
      PPostCode =new JPanel (new GridLayout(1,4));
      PPhone =new JPanel (new GridLayout(1,4));
      PEmail =new JPanel (new GridLayout(1,4));
      Pcontainer = new JPanel (new GridLayout(5,1,10,10));
      Pcontainer2 = new JPanel (new FlowLayout(FlowLayout.LEFT,20,30));
      
      PCpr.add(LCpr);
      PCpr.add(TCpr);
      PName.add(LName);
      PName.add(TName);
      PStreet.add(LStreet);
      PStreet.add(TStreet);
      PPostCode.add(LPostCode);
      PPostCode.add(TPostCode);
      PPhone.add(LPhone);
      PPhone.add(TPhone);
      PEmail.add(LEmail);
      PEmail.add(TEmail);
     
      Pbutton = new JPanel (new FlowLayout(FlowLayout.RIGHT));
      Pbutton.add(addB);
      Pbutton.add(cancelButton);
      
    

      
      Pcontainer.add(PCpr);
      Pcontainer.add(PName);
      Pcontainer.add(PStreet);
      Pcontainer.add(PPostCode);
      Pcontainer.add(PPhone);
      Pcontainer.add(PEmail);
      Pcontainer.add(Pbutton);
      Pcontainer2.add(Pcontainer);
      
      add(Pcontainer2);
      setSize(300, 300);
      setVisible(true);

   }

   public JButton getCancelButton()
   {
      return cancelButton;
   }

   public JTextField getTCpr()
   {
      return TCpr;
   }
   
   public JTextField getTName()
   {
      return TName;
   }

   public JTextField getTStreet()
   {
      return TStreet;
   }
   
   public JTextField getTPostCode()
   {
      return TPostCode;
   }
   
   public JTextField getTPhone()
   {
      return TPhone;
   }
   
   public JTextField getTEmail()
   {
      return TEmail;
   }
   
   public JButton getAddB()
   {
      return addB;
   }
}
