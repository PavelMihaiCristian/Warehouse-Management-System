package ClientView_Customer;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import util.Item;



public class GUI_Main_Customer extends JFrame
{


   

   private JComboBox<String> categoryComboBox;

   private JButton addtoBasketB;
   private JButton sendB;
   private JList<Item> cartList;
   private JButton deleteB;
   private JButton cancelB;
   private JScrollPane listScroll;
   private DefaultListModel listModel;
   private JPanel productsPanel;
   private JPanel container;

   public GUI_Main_Customer()
   {
      super("Make order");
      createComponents();
      initializeComponents();
      addComponentsToFrame();
   }

   public JPanel getGUI_Main_CustomerContainer()
   {
      return container;
   }

   public JPanel getGUI_Main_CustomerProductsPanel()
   {
      return productsPanel;
   }

   private void initializeComponents()
   {
      setSize(1500, 800);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      categoryComboBox.setActionCommand("comboBox");

      cartList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      cartList.setLayoutOrientation(JList.VERTICAL);
      cartList.setFixedCellWidth(500);

      listScroll.setMinimumSize(new Dimension(100, 50));
      
      

   }

   private void createComponents()
   {
      categoryComboBox = new JComboBox<>();

      addtoBasketB = new JButton("Add to basket");
      sendB = new JButton("Send");
      cancelB = new JButton("Remove All");
      listModel = new DefaultListModel();
      cartList = new JList<Item>(listModel);
      listScroll = new JScrollPane(cartList);
      deleteB = new JButton("Remove");
   }

   private void addComponentsToFrame()
   {
      JPanel categoryDropdownP = new JPanel(new FlowLayout(FlowLayout.LEADING));
      categoryDropdownP.add(categoryComboBox);

      productsPanel = new JPanel();
      productsPanel
            .setLayout(new BoxLayout(productsPanel, BoxLayout.PAGE_AXIS));
      JPanel button = new JPanel(new FlowLayout(FlowLayout.CENTER));
      JPanel listPanel = new JPanel(new BorderLayout());
      listPanel.add(listScroll, BorderLayout.CENTER);
      button.add(deleteB);
      button.add(cancelB);
      listPanel.add(button, BorderLayout.SOUTH);
      listPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2), "Basket", 2, 0,
            new Font("MyFont", Font.BOLD, 20)));

      JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      buttonsPanel.add(addtoBasketB);
      buttonsPanel.add(sendB);

      container = new JPanel(new BorderLayout());
      container.add(categoryDropdownP, BorderLayout.NORTH);
      container.add(new JScrollPane(productsPanel), BorderLayout.CENTER);
      container.add(listPanel, BorderLayout.EAST);
      container.add(buttonsPanel, BorderLayout.SOUTH);

      setContentPane(container);
      setVisible(true);
   }
   public JComboBox<String> getCategoryComboBox()
   {
      return categoryComboBox;
   }

   public JButton getAddtoBasketB()
   {
      return addtoBasketB;
   }

   public JButton getSendB()
   {
      return sendB;
   }

   public JList<Item> getCartList()
   {
      return cartList;
   }

   public JButton getDeleteB()
   {
      return deleteB;
   }

   public DefaultListModel getListModel()
   {
      return listModel;
   }

   public JPanel getProductsPanel()
   {
      return productsPanel;
   }
   
   public JPanel getContainer()
   {
      return container;
   }

   public JButton getCancelB()
   {
      return cancelB;
   }
   public void showMessage(String msg){
      JOptionPane.showMessageDialog(null,msg);
   }
   
}
