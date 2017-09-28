package ClientView_Picker;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

public class Start_PopUp
{

   private JPanel pickerIdPanel;
   

   private JPanel container;
   private JOptionPane jOption ;
   

   private JLabel PickerIDLabel;
   private JTextField id ;
   
   private JPanel textAreaPanel;
   private JTextArea textArea;
   private JScrollPane scroll; 

   public Start_PopUp()
   {
     
      pickerIdPanel = new JPanel();
      container = new JPanel(new BorderLayout());
      jOption = new JOptionPane();
      PickerIDLabel = new JLabel("Picker ID: ");
      id = new JTextField(10);
      textAreaPanel = new JPanel();
      textArea = new JTextArea();
      scroll = new JScrollPane(textArea);
      
   }

   public void build()
   {
      
      pickerIdPanel.add(PickerIDLabel);
      pickerIdPanel.add(id);
      scroll.setPreferredSize(new Dimension(500, 500));
      textArea.setEditable(false);
      scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      textAreaPanel.add(scroll);
      container.add(pickerIdPanel, BorderLayout.NORTH);
      container.add(textAreaPanel, BorderLayout.SOUTH);
      
      jOption.add(container);
      
      jOption.setVisible(true);

      jOption.showMessageDialog(null, container, "Start an Order",
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
      Start_PopUp or=new Start_PopUp();
      or.getTextArea().setText("Tessssssssssst");
   }
   
   public JOptionPane getjOption()
   {
      return jOption;
   }
   
   public JTextArea getTextArea()
   {
      return textArea;
   }
}
