package ClientView_Picker;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLayeredPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class PickerGUIMain extends JFrame {

   private JFrame frame;
   private JPanel firstPage;
   private GUI_CheckInOut checkInOutPanel;
   private GUI_AvailableOrders availableOrdersPanel;
   private GUI_InProgressOrders inprogressPanel;

   private JMenuBar menuBar;
   
   private JMenu Orders;
   private JMenu PickersInOutMenu; 
   
   private JMenuItem Available;
   private JMenuItem Processing ;

   private JPanel panel;
   
   private CardLayout cl;
   
   private ActionListener myAction;
   private ImageIcon logoIcon;
   private JLabel logoLabel;
   private Container overAllPanel;

   /**
    * Create the application.
    */
   public PickerGUIMain() {
      initialize();
      
   }
   
   public GUI_AvailableOrders getAvailableOrdersPanel()
   {
      return availableOrdersPanel;
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      frame = new JFrame();
      checkInOutPanel=new GUI_CheckInOut();
      availableOrdersPanel=new GUI_AvailableOrders();
      inprogressPanel=new GUI_InProgressOrders();
      
      cl = new CardLayout();
      myAction = new MyListener();

      menuBar = new JMenuBar();
      frame.setJMenuBar(menuBar);

      Orders = new JMenu("Orders");
      menuBar.add(Orders);
      
      PickersInOutMenu = new JMenu("Sign in/out");
      menuBar.add(PickersInOutMenu);
      PickersInOutMenu.addMenuListener(new MyMenuListener());

      Available = new JMenuItem("Available Orders");
      Orders.add(Available);
      Available.addActionListener(myAction);

      Processing  = new JMenuItem("Processing");
      Orders.add(Processing );
      Processing .addActionListener(myAction);
   
      panel = new JPanel(cl);
      
      
      overAllPanel=new JPanel(new BorderLayout());
      logoIcon = new ImageIcon("img/warehouse.jpg");
       logoLabel = new JLabel("",logoLabel.CENTER);
       logoLabel.setIcon(logoIcon);
       

       overAllPanel.add(logoLabel,BorderLayout.CENTER);
       
       panel.add(overAllPanel,"Start Page");
       panel.add(checkInOutPanel, "Check In/Out");
       panel.add(availableOrdersPanel, "available Orders");
       panel.add(inprogressPanel,"In Progress");
       
      cl.show(panel, "Start Page");
      
      frame.add(panel);
      frame.setSize(1000, 800);
      frame.setVisible(true);
      frame.setResizable(true);

      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
   }
   public JMenuItem getAvailable()
   {
      return Available;
   }
   public JMenuItem getProcessing ()
   {
      return Processing ;
   }
   
   public GUI_CheckInOut getCheckInOutPanel()
   {
      return checkInOutPanel;
   }

   public GUI_InProgressOrders getInprogressPanel()
   {
      return inprogressPanel;
   }

   public void showErrorMessage(String msg){
      JOptionPane.showMessageDialog(null,msg);
   }
   public void switchToMain(){
      cl.show(panel, "Start Page");
     
   }

   private class MyListener implements ActionListener {

      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == Available) {
            cl.show(panel, "available Orders");
         }
         if (e.getSource() == Processing ) {
            cl.show(panel, "In Progress");
         }
         
      }

   }
   
   private class MyMenuListener implements MenuListener {

      @Override
      public void menuSelected(MenuEvent e) {
         if (e.getSource() == PickersInOutMenu ) {
            cl.show(panel, "Check In/Out");
           // PickersInOutMenu.setSelected(false);
           
         }
          
      }

      @Override
      public void menuDeselected(MenuEvent e) {
          
      }

      @Override
      public void menuCanceled(MenuEvent e) {
          
      }
   }
}
