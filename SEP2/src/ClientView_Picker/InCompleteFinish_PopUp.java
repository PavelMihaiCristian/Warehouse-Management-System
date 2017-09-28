package ClientView_Picker;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import util.Date;
import util.Item;

public class InCompleteFinish_PopUp
{
   private JPanel container;
   private JPanel pickerIdPanel;
   private JPanel checkBoxesPanel;
   
   private JCheckBox[] checkBoxes;
   private ArrayList<Item> items;
   private JOptionPane jOption ;
   
   private JLabel PickerIDLabel;
   private JTextField id ;
   
   private JScrollPane scroll; 

   int clickCount;
   JButton selection;
   public InCompleteFinish_PopUp(ArrayList<Item> items)
   {
      selection=new JButton("Select/Deselect ALL");
      selection.addActionListener(new MyListener());
      clickCount=0;
      this.items=items;
      pickerIdPanel = new JPanel();
      checkBoxesPanel = new JPanel();
      container = new JPanel(new BorderLayout());
      
      jOption = new JOptionPane();
      PickerIDLabel = new JLabel("Picker ID: ");
      id = new JTextField(10);
      
      scroll = new JScrollPane(checkBoxesPanel);
      
   }

   public void build()
   {
      
      pickerIdPanel.add(PickerIDLabel);
      pickerIdPanel.add(id);
      container.setPreferredSize(new Dimension(500, 500));
      checkBoxesPanel.setPreferredSize(new Dimension(100, 500));
     scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
     //checkBoxesPanel.add(scroll);
     checkBoxes=new JCheckBox[items.size()];
     for (int i=0;i<items.size();i++){
        checkBoxes[i] = new JCheckBox(items.get(i).toString());
        checkBoxesPanel.add(checkBoxes[i]);

     }
      container.add(pickerIdPanel, BorderLayout.NORTH);
      container.add(scroll, BorderLayout.CENTER);
      container.add(selection, BorderLayout.SOUTH);
     
      
      
      
      
      jOption.add(container);
      
      jOption.setVisible(true);

      jOption.showMessageDialog(null, container, "Incomplete Page",
            JOptionPane.INFORMATION_MESSAGE);
     /* if(jOption.OK_OPTION==0){
        
      }*/
      
   }

   // year.addActionListener(new MyListener());
   // ***************New********

 
   public JTextField getPickerId()
   {
      return id;
   }

   public static void main(String[] args)
   {
      ArrayList<Item> item=new ArrayList<Item>();
      item.add(new Item(12,16));
      item.add(new Item(15,16));
      item.add(new Item(18,16));
      InCompleteFinish_PopUp or=new InCompleteFinish_PopUp(item);
      or.build();
      
   }
   
   public JOptionPane getjOption()
   {
      return jOption;
   }
   public JCheckBox[] getcheckBoxes(){
      return checkBoxes;
   }
  /* public JTextArea getTextArea()
   {
      return textArea;
   }*/
   private class MyListener implements ActionListener
   {

      public void actionPerformed(ActionEvent e)
      {
         
         if (e.getSource() == selection)
         {
            if(clickCount%2==0){
               for(int i=0;i<checkBoxes.length;i++){
                  checkBoxes[i].setSelected(true);
               }
            }else{
               for(int i=0;i<checkBoxes.length;i++){
                  checkBoxes[i].setSelected(false);
               }
            }
           clickCount++; 
         }
      }
   }

}
