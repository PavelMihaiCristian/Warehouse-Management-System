package ClientView_Customer;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;



public class RowPanel extends JPanel
{
   private JLabel productAvailabilityLabel;
   private JLabel productNameLabel;
   private JLabel productIdLabel;
   private JLabel productItemsLabel;
   private JLabel productPricePerBoxLabel;
   private JLabel amountPBoxLabel;
   private JLabel productTotalPriceLabel;
   private JCheckBox checkBox;
   private JLabel nameText;
   private JLabel idText;
   private JLabel pricePerBoxText;
   private JLabel itemsInBoxText;
   private JTextField amountTxtF;
   private JLabel totalPriceText; 

   private JPanel container;

 
   public RowPanel()
   {
      createComponents();
      initializeComponents();
      addComponentsToFrame();
   }

   private void initializeComponents()
   {
      amountTxtF.setEnabled(false);
      amountTxtF.setEditable(false);

      //checkBox.setActionCommand("checkBox");
   }

   private void createComponents()
   {
      productAvailabilityLabel = new JLabel("Select");
      productNameLabel = new JLabel("Name");
      productIdLabel = new JLabel("ID");
      productItemsLabel = new JLabel("Items pers box");
      productPricePerBoxLabel = new JLabel("Price per box");
      amountPBoxLabel = new JLabel("Number of boxes");
      productTotalPriceLabel = new JLabel("Total price");
      amountTxtF = new JTextField(10);
      amountTxtF.addFocusListener(new FocusListener()
      {
         
         @Override
         public void focusLost(FocusEvent e)
         {
            // TODO Auto-generated method stub
            if(amountTxtF.getText().equals("")){
               
            }else{
            calculatePrice();
            }
            
         }
         
         @Override
         public void focusGained(FocusEvent e)
         {
            // TODO Auto-generated method stub
            
         }
      });
      checkBox = new JCheckBox();
      checkBox.addActionListener(new ActionListener()
      {
         
         @Override
         public void actionPerformed(ActionEvent e)
         {
            
               if(checkBox.isSelected()){
                  amountTxtF.setEnabled(true);
                  amountTxtF.setEditable(true);
               }else{
                  amountTxtF.setText("");
                  totalPriceText.setText("");
                  amountTxtF.setEnabled(false);
                  amountTxtF.setEditable(false);
               }
            
            
         }
      });
      nameText = new JLabel("");
      idText = new JLabel("");
      itemsInBoxText = new JLabel("");
      pricePerBoxText = new JLabel("");
      totalPriceText = new JLabel("");
   }

   public JCheckBox getCheckBox()
   {
      return checkBox;
   }

   public JLabel getIdText()
   {
      return idText;
   }

   public JTextField getAmountTxtF()
   {
      return amountTxtF;
   }

   public JLabel getTotalPriceText()
   {
      return totalPriceText;
   }
   
   private void addComponentsToFrame()
   {
      JPanel labelsPanel = new JPanel(new GridLayout(1, 7, 5, 0));
      labelsPanel.add(productAvailabilityLabel);
      labelsPanel.add(productNameLabel);
      labelsPanel.add(productIdLabel);
      labelsPanel.add(productItemsLabel);
      labelsPanel.add(productPricePerBoxLabel);
      labelsPanel.add(amountPBoxLabel);
      labelsPanel.add(productTotalPriceLabel);

      JPanel contentPane = new JPanel(new GridLayout(1, 7, 5, 0));
      contentPane.add(checkBox);
      contentPane.add(nameText);
      contentPane.add(idText);
      contentPane.add(itemsInBoxText);
      contentPane.add(pricePerBoxText);
      contentPane.add(amountTxtF);
      contentPane.add(totalPriceText);

      container = new JPanel(new BorderLayout(5, 5));
      container.add(labelsPanel, BorderLayout.NORTH);
      container.add(contentPane, BorderLayout.CENTER);
      container.setBorder(BorderFactory.createLineBorder(Color.BLACK));

      add(container);
   }

   public void setLabelText(String name, int id, int itemsPBox, double pricePerBox)
   {
      nameText.setText(name);
      idText.setText("" + id);
      itemsInBoxText.setText("" + itemsPBox);
      pricePerBoxText.setText("" + pricePerBox);
   }
   
   public void calculatePrice()
   {
      double price = Double.parseDouble(pricePerBoxText.getText())* 
            Double.parseDouble(amountTxtF.getText());
      totalPriceText.setText("" + price + " dkk");
   }
}
