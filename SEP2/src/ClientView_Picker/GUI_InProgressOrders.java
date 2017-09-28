package ClientView_Picker;





import java.awt.*;

import javax.swing.*;

import util.Order;

public class GUI_InProgressOrders extends JPanel
{
 

   
      private JLabel Ltitle;
      private JList<Order> JLorders;
      private JButton BFinish, Bcancel,Bincomplete;
      private DefaultListModel listModel;
      private JPanel Ptitle, Pbutton, Pcontainer;

      public GUI_InProgressOrders()
      {
         
         createComponents();
      }
      
      public DefaultListModel getListModel()
      {
         return listModel;
      }
      public void createComponents()
      {
         Ltitle = new JLabel("In progres orders");
         listModel = new DefaultListModel();
         JLorders = new JList<>(listModel);
         BFinish = new JButton("Finish");
         Bcancel = new JButton("Cancel");
         Bincomplete=new JButton("Incomplete");
         Ptitle = new JPanel(new BorderLayout());
         Pbutton = new JPanel();
         Pcontainer = new JPanel(new GridLayout(2,1));
         
         Ltitle.setFont(new Font("title1", Font.BOLD,20));
         Ptitle.add(Ltitle, BorderLayout.NORTH);
         
         JLorders.setVisibleRowCount(15);
         JLorders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         JLorders.setLayoutOrientation(JList.VERTICAL);
         
         JScrollPane js = new JScrollPane(JLorders);
         js.setPreferredSize(new Dimension(700, 500));
         Ptitle.add(js, BorderLayout.CENTER);
         
         Pbutton.add(Bincomplete);
         Pbutton.add(BFinish);
         Pbutton.add(Bcancel);
         
         
         
         
         
         Pcontainer.add(Ptitle);
         Pcontainer.add(Pbutton);

         
         add(Pcontainer);
         
         setSize(700,500);
         setVisible(true);
      }
      public JList<Order> getJLorders()
      {
         return JLorders;
      }

      public JButton getBcancel()
      {
         return Bcancel;
      }

      public JButton getBFinish()
      {
         return BFinish;
      }
      public JButton getBinComplete()
      {
         return Bincomplete;
      }
   }


