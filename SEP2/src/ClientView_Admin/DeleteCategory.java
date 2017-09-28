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

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class DeleteCategory extends JPanel {

	private JPanel firstPanel, secondPanel, thirdPanel;
	private JComboBox<String> comboBox;
	private JButton removeButton;
	private JButton cancelButton;

	/**
	 * Create the application.
	 */
	public DeleteCategory() {
		initialize();
	}

	public JComboBox<String> GetCategorycomboBox()
   {
      return comboBox;
   }

   public JButton getRemoveButton()
   {
      return removeButton;
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

		removeButton = new JButton("Remove");
		cancelButton=new JButton("Cancel");
		JLabel deleteLabel = new JLabel("Category name");
		comboBox = new JComboBox<String>();
		
		firstPanel.add(deleteLabel);
		firstPanel.add(comboBox);
		secondPanel.add(removeButton);
		secondPanel.add(cancelButton);
		
		thirdPanel.add(firstPanel, BorderLayout.NORTH);
		thirdPanel.add(secondPanel, BorderLayout.CENTER);
		add(thirdPanel,BorderLayout.CENTER);
		setVisible(true);
		setBounds(100, 100, 450, 300);
	}

}
