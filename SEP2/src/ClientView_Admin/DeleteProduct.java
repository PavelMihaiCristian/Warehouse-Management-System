package ClientView_Admin;
import java.awt.EventQueue;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import JDBC.MyDatabase;

import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;


public class DeleteProduct extends JPanel{

	public JPanel mainPanel;
	private JButton delete;
	private JButton cancelButton;
	

   private JTextField productIdField;
	private JLabel label;
	private Component rigidArea;
	private Component rigidArea_1;
	private Component rigidArea_2;

	/**
	 * Create the application.
	 */
	public DeleteProduct() {
		initialize();
	}

	/**
	 * Initialize the contents of the mainPanel.
	 */
	private void initialize() {
		
		mainPanel = new JPanel();
		mainPanel.setBounds(100, 100, 450, 300);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{51, 0, 86, 0, 63, 0, 65, 0};
		gbl_mainPanel.rowHeights = new int[]{23, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		label = new JLabel("Product ID");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		mainPanel.add(label, gbc_label);
		
		rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 0, 5);
		gbc_rigidArea_1.gridx = 1;
		gbc_rigidArea_1.gridy = 0;
		mainPanel.add(rigidArea_1, gbc_rigidArea_1);
		
		productIdField = new JTextField();
		productIdField.setColumns(10);
		GridBagConstraints gbc_productIdField = new GridBagConstraints();
		gbc_productIdField.insets = new Insets(0, 0, 0, 5);
		gbc_productIdField.gridx = 2;
		gbc_productIdField.gridy = 0;
		mainPanel.add(productIdField, gbc_productIdField);
		
		rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 0, 5);
		gbc_rigidArea.gridx = 3;
		gbc_rigidArea.gridy = 0;
		mainPanel.add(rigidArea, gbc_rigidArea);
		
		delete = new JButton("Delete");
		GridBagConstraints gbc_delete = new GridBagConstraints();
		gbc_delete.insets = new Insets(0, 0, 0, 5);
		gbc_delete.gridx = 4;
		gbc_delete.gridy = 0;
		mainPanel.add(delete, gbc_delete);
		
		rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_2 = new GridBagConstraints();
		gbc_rigidArea_2.insets = new Insets(0, 0, 0, 5);
		gbc_rigidArea_2.gridx = 5;
		gbc_rigidArea_2.gridy = 0;
		mainPanel.add(rigidArea_2, gbc_rigidArea_2);
		cancelButton=new JButton("Cancel");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.WEST;
		gbc_cancelButton.gridx = 6;
		gbc_cancelButton.gridy = 0;
		mainPanel.add(cancelButton, gbc_cancelButton);
		add(mainPanel);
		setVisible(true);
		setBounds(100, 100, 450, 300);
	}
	
	public JButton getDeleteButton(){
		return delete;
	}
	
	public JTextField getTextField(){
		return productIdField;
	}
	
	public void showErrorMessage(String msg){
		JOptionPane.showMessageDialog(null,msg);
	}
	public JButton getCancelButton()
   {
      return cancelButton;
   }
}
