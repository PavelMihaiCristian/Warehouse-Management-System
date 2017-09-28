package ClientView_Picker;



import java.awt.*;

import javax.swing.*;

public class GUI_CheckInOut extends JPanel
{
   private JLabel Lid;
   private JTextField Tid;
   private JButton Bin, Bout;
   
   private JButton cancelButton;
   private JPanel cancelButtonPanel;
   private JPanel Pid, Pbutton, Pcontainer;

   public GUI_CheckInOut()
   {
      //super("Check in/ out");
      createContent();
   }

   public void createContent()
   {
      Lid = new JLabel("Picker ID: ");
      Tid = new JTextField(7);
      Bin = new JButton("Check in");
      Bout = new JButton("Check out");
      Pid = new JPanel(new FlowLayout());
      Pbutton = new JPanel(new FlowLayout());
      Pcontainer = new JPanel(new BorderLayout(10, 20));
      cancelButton=new JButton("Cancel");
      cancelButtonPanel=new JPanel();
      
      cancelButtonPanel.add(cancelButton);

      Pid.add(Lid);
      Pid.add(Tid);

      Pcontainer.add(Pid, BorderLayout.NORTH);

      Pbutton.add(Bin);
      Pbutton.add(Bout);

      Pcontainer.add(Pbutton, BorderLayout.CENTER);
      Pcontainer.add(cancelButtonPanel,BorderLayout.SOUTH);
      add(Pcontainer);
      

     // setSize(400, 300);
      setVisible(true);
   }
   public JTextField getTid()
   {
      return Tid;
   }

   public JButton getBin()
   {
      return Bin;
   }

   public JButton getBout()
   {
      return Bout;
   }

   public JButton getCancelButton()
   {
      return cancelButton;
   }

}
