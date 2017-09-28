package ClientView_Admin;

import java.awt.*;

import javax.swing.*;

public class GUI_AddCustomer extends JPanel
{

   private JLabel LCustomerID, LName, LStreet, LPostalCode, LPhoneNumber,
         LEmail;
   private JTextField TCustomerID, TName, TStreet, TPostalCode, TPhoneNumber,
         TEmail;
   private JPanel Pcontainer, Pcontainer2, PId, PName, PStreet, PPostalCode,
         PPhoneNumber, PEmail, Pbutton;
   private JButton addButton;
   private JButton cancelButton;

   public GUI_AddCustomer()
   {
      createComponents();
   }

   public void createComponents()
   {
      LCustomerID = new JLabel("ID: ");
      LName = new JLabel("Name: ");
      LStreet = new JLabel("Street Name & #: ");
      LPostalCode = new JLabel("Post Code: ");
      LPhoneNumber = new JLabel("Phone number: ");
      LEmail = new JLabel("Email: ");
      addButton = new JButton("Add");
      cancelButton = new JButton("Cancel");

      TCustomerID = new JTextField(10);
      TName = new JTextField(10);
      TStreet = new JTextField(10);
      TPostalCode = new JTextField(10);
      TPhoneNumber = new JTextField(10);
      TEmail = new JTextField(10);

      PId = new JPanel(new GridLayout(1, 2));
      PName = new JPanel(new GridLayout(1, 2));
      PStreet = new JPanel(new GridLayout(1, 2));
      PPostalCode = new JPanel(new GridLayout(1, 2));
      PPhoneNumber = new JPanel(new GridLayout(1, 2));
      PEmail = new JPanel(new GridLayout(1, 2));

      Pcontainer = new JPanel(new GridLayout(7, 1, 10, 10));
      Pcontainer2 = new JPanel();
      Pbutton = new JPanel(new FlowLayout(FlowLayout.RIGHT));

      PId.add(LCustomerID);
      PId.add(TCustomerID);

      PName.add(LName);
      PName.add(TName);

      PStreet.add(LStreet);
      PStreet.add(TStreet);

      PPostalCode.add(LPostalCode);
      PPostalCode.add(TPostalCode);

      PPhoneNumber.add(LPhoneNumber);
      PPhoneNumber.add(TPhoneNumber);

      PEmail.add(LEmail);
      PEmail.add(TEmail);

      Pbutton.add(addButton);
      Pbutton.add(cancelButton);

      Pcontainer.add(PId);
      Pcontainer.add(PName);
      Pcontainer.add(PStreet);
      Pcontainer.add(PPostalCode);
      Pcontainer.add(PPhoneNumber);
      Pcontainer.add(PEmail);
      Pcontainer.add(Pbutton);
      Pcontainer2.add(Pcontainer);

      add(Pcontainer2);
      setSize(400, 300);
      setVisible(true);

   }

   public JLabel getLCustomerID()
   {
      return LCustomerID;
   }

   public JLabel getLName()
   {
      return LName;
   }

   public JLabel getLStreet()
   {
      return LStreet;
   }

   public JLabel getLPostalCode()
   {
      return LPostalCode;
   }

   public JLabel getLPhoneNumber()
   {
      return LPhoneNumber;
   }

   public JLabel getLEmail()
   {
      return LEmail;
   }

   public JTextField getTCustomerID()
   {
      return TCustomerID;
   }

   public JTextField getTName()
   {
      return TName;
   }

   public JTextField getTStreet()
   {
      return TStreet;
   }

   public JTextField getTPostalCode()
   {
      return TPostalCode;
   }

   public JTextField getTPhoneNumber()
   {
      return TPhoneNumber;
   }

   public JTextField getTEmail()
   {
      return TEmail;
   }

   public JButton getAddButton()
   {
      return addButton;
   }

   public JButton getCancelButton()
   {
      return cancelButton;
   }

}