package ClientView_Admin;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import util.Product;

public class ProductsOfCategory extends JPanel {

   private JPanel firstPanel, secondPanel, thirdPanel;
   private JComboBox<String> comboBox;
   private JButton showProdButton;
   private JButton cancelButton;
   private JTextArea TAshowProducts;
   private JScrollPane bar;
   //private JPanel PanelTextArea;
   private JList productsList;
   private DefaultListModel listModel;
  

   public DefaultListModel getListModel()
   {
      return listModel;
   }
   public JList getProductsList()
   {
      return productsList;
   }
   /**
    * Create the application.
    */
   public ProductsOfCategory() {
      initialize();
   }
   public JTextArea getTAshowProducts()
   {
      return TAshowProducts;
   }
   public JComboBox<String> GetCategorycomboBox()
   {
      return comboBox;
   }

   public JButton getshowProdButton()
   {
      return showProdButton;
   }

   public JButton getCancelButton()
   {
      return cancelButton;
   }

   /**
    * Initialize the contents of the mainpanel.
    */
   private void initialize() {
      firstPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      secondPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      thirdPanel = new JPanel(new BorderLayout());
     /* PanelTextArea = new JPanel(new BorderLayout());
      TAshowProducts = new JTextArea(20,50);
      bar= new JScrollPane(TAshowProducts);
      bar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      */
      listModel = new DefaultListModel();
      productsList=new JList<Product>(listModel);
      productsList.setVisibleRowCount(10);
      //productsList.setSize(100, 500);
      productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      add(new JScrollPane(productsList));
      
      
      showProdButton = new JButton("Show Products");
      cancelButton=new JButton("Cancel");
      JLabel CatName = new JLabel("Category name");
      comboBox = new JComboBox<String>();
     // PanelTextArea.add(bar);
      firstPanel.add(CatName);
      firstPanel.add(comboBox);
      secondPanel.add(showProdButton);
      secondPanel.add(cancelButton);
      
      
      thirdPanel.add(firstPanel, BorderLayout.NORTH);
      thirdPanel.add(secondPanel, BorderLayout.CENTER);
      
      add(thirdPanel,BorderLayout.CENTER);
      setVisible(true);
      setBounds(100, 100, 450, 300);
     // add(PanelTextArea);
   }

}
