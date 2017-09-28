package ClientView_Picker;




import java.awt.*;

import javax.swing.*;

import util.Order;

public class GUI_AvailableOrders extends JPanel
{
   private JLabel Ltitle;
   private JList<Order> JLorders;
   private JButton BStart, Bcancel;
   private JPanel Ptitle, Pbutton, Pcontainer;
   private DefaultListModel listModel;

   public GUI_AvailableOrders()
   {
     
      createComponents();
   }
   
   public DefaultListModel getListModel()
   {
      return listModel;
   }
   public void createComponents()
   {
      Ltitle = new JLabel("Available orders");
      listModel = new DefaultListModel();
      JLorders = new JList<>(listModel);
      BStart = new JButton("Start");
      Bcancel = new JButton("Cancel");
      Ptitle = new JPanel(new BorderLayout());
      Pbutton = new JPanel();
      Pcontainer = new JPanel(new GridLayout(2,1));
      Ltitle.setFont(new Font("title1", Font.BOLD,20));
      Ptitle.add(Ltitle, BorderLayout.NORTH);
      
      JLorders.setVisibleRowCount(15);
      JLorders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JLorders.setLayoutOrientation(JList.VERTICAL);
      JLorders.setPreferredSize(new Dimension(700, 1000));
      JScrollPane js = new JScrollPane(JLorders);
      Ptitle.add(js, BorderLayout.CENTER);
      
      Pbutton.add(BStart);
      Pbutton.add(Bcancel);
      
      
      
      
      Pcontainer.add(Ptitle);
      Pcontainer.add(Pbutton);


      add(Pcontainer);
      setSize(700, 500);
      setVisible(true);
   }
   public JList<Order> getJLorders()
   {
      return JLorders;
   }

   public JButton getBStart()
   {
      return BStart;
   }

   public JButton getBcancel()
   {
      return Bcancel;
   }

}
