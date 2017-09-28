package loginView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.*;

import ClientController_Admin.ClientMain;

import java.awt.*;
import java.awt.event.*;

public class login {
   // private static final ActionListener Event = null;
    private JComboBox combo1;
    private JPasswordField txt;
    private Button boton;

   /* public static void main(String[] args) {
        login b = new login();
    }*/

    public login() {
        String course1[] = { "Admin", "Picker", "Customer"};
        JFrame frame = new JFrame("Login Page");
        JPanel panel = new JPanel();
        combo1 = new JComboBox(course1);
        txt = new JPasswordField(10);
        boton = new Button( "Login");
        panel.add(combo1);
        panel.add(txt);
        panel.add(boton);
        frame.add(panel);

        
        txt.addKeyListener(new KeyListener() {
			
		
			public void keyTyped(KeyEvent e) {
			
				
			}
			public void keyReleased(KeyEvent e) {	
				
			}
			
			
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					String str1 = (String) combo1.getSelectedItem();
                    char[] PassChar=txt.getPassword();
                    String str2=new String(PassChar);
                    if(str1.equals("Admin")&&str2.equals("AdminPass")){
                       ClientMain.setAllowAdmin(true);
                       frame.dispose();
                    }
                    else if(str1.equals("Picker")&&str2.equals("PickerPass")){
                       ClientMain.setAllowPicker(true);
                       frame.dispose();
                    }
                    else if(str1.equals("Customer")&&str2.equals("CustomerPass")){
                       ClientMain.setAllowCustomer(true);
                       frame.dispose();
                    }else{
                       JOptionPane.showMessageDialog(null,"Incorrect Password");
                       txt.setText("");
                    }
				}
			}
		});
        boton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                    String str1 = (String) combo1.getSelectedItem();
                    char[] PassChar=txt.getPassword();
                    String str2=new String(PassChar);
                    if(str1.equals("Admin")&&str2.equals("AdminPass")){
                       ClientMain.setAllowAdmin(true);
                       frame.dispose();
                    }
                    else if(str1.equals("Picker")&&str2.equals("PickerPass")){
                       ClientMain.setAllowPicker(true);
                       frame.dispose();
                    }
                    else if(str1.equals("Customer")&&str2.equals("CustomerPass")){
                       ClientMain.setAllowCustomer(true);
                       frame.dispose();
                    }else{
                       JOptionPane.showMessageDialog(null,"Incorrect Password");
                       txt.setText("");
                    }
                    
                    

            }
        });
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}