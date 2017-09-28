package ClientView_Customer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Date;

public class OrderPopUp
{

   private JPanel idPanel;
   private JPanel datePanel;

   private JPanel container;
   private JOptionPane jOption ;
   

   private JLabel customerIDLabel;
   private JTextField id ;
   private JLabel dateLabel ;
   private JComboBox<Integer> year;
   private JComboBox<Integer> month ;
   private JComboBox<Integer> day;
   private JComboBox<Integer> hour ;
   private JComboBox<Integer> minute ;
   private Date startDate;
   private Date endDate;

   public OrderPopUp()
   {
      startDate = new Date();
      int nextYear = startDate.getYear() + 1;
      endDate = new Date(startDate.getDay(), startDate.getMonth(), nextYear);

      idPanel = new JPanel();
      datePanel = new JPanel();
      datePanel.setPreferredSize(new Dimension(375, 75));
      container = new JPanel(new BorderLayout());
      jOption = new JOptionPane();
      customerIDLabel = new JLabel("Customer ID: ");
      id = new JTextField(10);
      dateLabel = new JLabel("Date Of Shipment:");
      year = new JComboBox<>();
      month = new JComboBox<>();
      day = new JComboBox<>();
      Integer[] hours = { 00, 01, 02, 03, 04, 05, 06, 07, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23 };
      Integer[] minutes = { 00, 15, 30, 45 };
      hour = new JComboBox<Integer>(hours);
      minute = new JComboBox<>(minutes);

      build();
   }

   private void build()
   {
      idPanel.add(customerIDLabel);
      idPanel.add(id);
      datePanel.add(dateLabel);
      datePanel.add(year);
      datePanel.add(month);
      datePanel.add(day);
      datePanel.add(hour);
      datePanel.add(minute);
      container.add(idPanel, BorderLayout.NORTH);
      container.add(datePanel, BorderLayout.SOUTH);

      jOption.add(container);
      year.addActionListener(new MyListener());
      month.addActionListener(new MyListener());
      year.addItem((startDate.getYear()));
      year.addItem(endDate.getYear());

      jOption.setVisible(true);

      jOption.showMessageDialog(null, container, "Customer Information",
            JOptionPane.QUESTION_MESSAGE);
     /* if(jOption.OK_OPTION==0){
        
      }*/
      
   }

   // year.addActionListener(new MyListener());
   // ***************New********
   public void yearHasChanged(int year, JComboBox<Integer> month)
   {
      month.removeAllItems();

      if (year == startDate.getYear())
      {
         for (int i = startDate.getMonth(); i <= 12; i++)
         {
            if (!(checkForDoublication(month, (i))))
            {
               month.addItem(i);
            }

         }
      }
      else
      {

         for (int i = 1; i <= 12; i++)
         {
            if (!(checkForDoublication(month, (i))))
            {
               month.addItem(i);
            }
         }
      }
   }

   public void monthHasChanged(int year, int month, JComboBox<Integer> day)
   {
      day.removeAllItems();

      if (year == startDate.getYear() && month == startDate.getMonth())
      {
         Date tempdate = startDate.copy();
         // System.out.println(tempdate.daysInMonth());
         while (tempdate.getDay() < tempdate.daysInMonth())
         {
            // System.out.println(tempdate.getDay());
            // System.out.println("Here");
            if (!(checkForDoublication(day, tempdate.getDay()+1)))
            {
               day.addItem(tempdate.getDay()+1);
            }
            tempdate.nextDay();
         }//added +1 to start from next day
         day.addItem(tempdate.getDay());
      }

      else
      {
         Date tempdate = new Date(1, month, year);
         while (tempdate.getDay() < tempdate.daysInMonth())
         {
            if (!(checkForDoublication(day, (tempdate.getDay()))))
            {
               day.addItem(tempdate.getDay());
            }
            tempdate.nextDay();
         }
         day.addItem(tempdate.getDay());
      }

   }

   private boolean checkForDoublication(JComboBox<Integer> box, int item)
   {
      boolean exists = false;
      for (int index = 0; index < box.getItemCount() && !exists; index++)
      {
         if (item == (box.getItemAt(index)))
         {
            return true;
         }
      }
      return false;
   }

   private class MyListener implements ActionListener
   {

      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == year)
         {
            yearHasChanged((int) year.getSelectedItem(), month);
         }
         if (e.getSource() == month)
         {
            if (month.getItemCount() != 0)
            {
               monthHasChanged((int) year.getSelectedItem(),
                     (int) month.getSelectedItem(), day);
            }

         }
         

      }

   }

   public JTextField getId()
   {
      return id;
   }

   public JComboBox<Integer> getYear()
   {
     
      return year;
   }

   public JComboBox<Integer> getMonth()
   {
      return month;
   }

   public JComboBox<Integer> getDay()
   {
      return day;
   }

   public JComboBox<Integer> getHour()
   {
      return hour;
   }

   public JComboBox<Integer> getMinute()
   {
      return minute;
   }

   public static void main(String[] args)
   {
      OrderPopUp or=new OrderPopUp();
   }
   public JOptionPane getjOption()
   {
      return jOption;
   }
}
