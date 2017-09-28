package ClientView_Admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.ws.handler.MessageContext.Scope;

import util.Date;

public class GUI_RegisterGoods extends JPanel{
	
	private JLabel productIdLabel;
	private JLabel noOfPalletsLabel;
	private JLabel noOfBoxesPerPaletLabel;
	private JLabel noOfItemsPerBoxLabel;
	private JLabel expiryDateLabel;
	private JLabel dayLabel;
	private JLabel monthLabel;
	private JLabel yearLabel;
	private JLabel sellingPriceLabel;
	private JLabel buyingPriceLabel;
	private JLabel positionLabel;
	
	private JTextField productIdText;
	private JTextField noOfPalletsText;
	private JTextField noOfBoxesPerPaletText;
	private JTextField noOfItemsPerBoxText;
	private JComboBox<Integer> dayCombo;
	private JComboBox<Integer> monthCombo;
	private JComboBox<Integer> yearCombo;
	private JTextField sellingPriceText;
	private JTextField buyingPriceText;
	private JTextArea positionArea;
	
	
	private JScrollPane scroll;
	
	private JButton addButton;
	private JButton cancelButton;
	
	private JPanel container;
	private JPanel productIdPanel;
	private JPanel noOfPalletsPanel;
	private JPanel noOfBoxesPerPalletPanel;
	private JPanel noOfItemsPerBoxPanel;
	private JPanel expiryDatePanel;
	private JPanel buyingPricePanel;
	private JPanel sellingPricePanel;
	private JPanel buttonsPanel;
	private JPanel positionsPanel;
	
	private Date startDate;
	private Date endDate;

	
	public GUI_RegisterGoods(){
		initialize();
	}

	private void initialize() {
		container=new JPanel();
		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.insets=new  Insets(10, 10, 10, 10);
		
		gbc.gridx=0;
		gbc.gridy=0;
		
		productIdPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		productIdLabel=new JLabel("Product Id:");
		productIdText=new JTextField(6);
		productIdPanel.add(productIdLabel);
		productIdPanel.add(productIdText);
		
		container.add(productIdPanel,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		
		
		
		
		noOfPalletsPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		noOfPalletsLabel=new JLabel("Number of pallets:");
		noOfPalletsText=new JTextField(5);
		noOfPalletsPanel.add(noOfPalletsLabel);
		noOfPalletsPanel.add(noOfPalletsText);
		
		container.add(noOfPalletsPanel,gbc);
		
		gbc.gridx=2;
		gbc.gridy=0;
		
		
		noOfBoxesPerPalletPanel=new JPanel();
		noOfBoxesPerPaletLabel=new JLabel("Boxes per pallet:");
		noOfBoxesPerPaletText=new JTextField(5);
		noOfBoxesPerPalletPanel.add(noOfBoxesPerPaletLabel);
		noOfBoxesPerPalletPanel.add(noOfBoxesPerPaletText);
		
		container.add(noOfBoxesPerPalletPanel,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		
		
		noOfItemsPerBoxPanel=new JPanel();
		noOfItemsPerBoxLabel=new JLabel("Number of items per box:");
		noOfItemsPerBoxText=new JTextField(5);
		noOfItemsPerBoxPanel.add(noOfItemsPerBoxLabel);
		noOfItemsPerBoxPanel.add(noOfItemsPerBoxText);
		
		container.add(noOfItemsPerBoxPanel,gbc);
		
		startDate = new Date();
		int nextYear = startDate.getYear() + 10;
		endDate = new Date(startDate.getDay(), startDate.getMonth(), nextYear);
		
		gbc.gridwidth=2;
		gbc.gridx=1;
		gbc.gridy=1;
		
		
		expiryDatePanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		expiryDateLabel=new JLabel("Expiry date");
		dayLabel=new JLabel("Day");
		dayCombo=new JComboBox<Integer>();
		monthLabel=new JLabel("Month");
		monthCombo=new JComboBox<Integer>();
		yearLabel=new JLabel("Year");
		yearCombo=new JComboBox<Integer>();
		monthCombo.addActionListener(new MyListener());
		yearCombo.addActionListener(new MyListener());
		for (int i = startDate.getYear(); i < endDate.getYear(); i++) {
			if (!(checkForDoublication(yearCombo, i))) {
				yearCombo.addItem((i));
			}
		}
		expiryDatePanel.add(expiryDateLabel);
		expiryDatePanel.add(dayLabel);
		expiryDatePanel.add(dayCombo);
		expiryDatePanel.add(monthLabel);
		expiryDatePanel.add(monthCombo);
		expiryDatePanel.add(yearLabel);
		expiryDatePanel.add(yearCombo);
		
		container.add(expiryDatePanel,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		
		
		buyingPricePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		buyingPriceLabel=new JLabel("Buying price:");
		buyingPriceText=new JTextField(5);
		buyingPricePanel.add(buyingPriceLabel);
		buyingPricePanel.add(buyingPriceText);
		
		container.add(buyingPricePanel,gbc);
		
		gbc.gridx=2;
		gbc.gridy=3;
		
		
		sellingPricePanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		sellingPriceLabel=new JLabel("Selling price:");
		sellingPriceText=new JTextField(5);
		sellingPricePanel.add(sellingPriceLabel);
		sellingPricePanel.add(sellingPriceText);
		
		container.add(sellingPricePanel,gbc);
		
		gbc.gridwidth=3;
		gbc.gridx=0;
		gbc.gridy=4;
		
		
		buttonsPanel=new JPanel();
		cancelButton=new JButton("Cancel");
		addButton=new JButton("Register");
		buttonsPanel.add(addButton);
		buttonsPanel.add(cancelButton);
		
		container.add(buttonsPanel,gbc);
		
		gbc.gridwidth=3;
		gbc.gridx=0;
		gbc.gridy=5;
	
		
		positionsPanel=new JPanel(new BorderLayout());
		positionLabel=new JLabel("Pallets Locations");
		positionArea=new JTextArea();
		positionArea.setEditable(false);
		scroll=new JScrollPane(positionArea);
		scroll.setPreferredSize(new Dimension(600,300));
		positionsPanel.add(positionLabel,BorderLayout.NORTH);
		positionsPanel.add(scroll,BorderLayout.CENTER);
		
		container.add(positionsPanel,gbc);
		
		add(container);
		setVisible(true);
	}

	public JTextField getProductIdText() {
		return productIdText;
	}

	public JTextField getNoOfPalletsText() {
		return noOfPalletsText;
	}

	public JTextField getNoOfBoxesPerPaletText() {
		return noOfBoxesPerPaletText;
	}

	public JTextField getNoOfItemsPerBoxText() {
		return noOfItemsPerBoxText;
	}

	public JComboBox<Integer> getDayCombo() {
		return dayCombo;
	}

	public JComboBox<Integer> getMonthCombo() {
		return monthCombo;
	}

	public JComboBox<Integer> getYearCombo() {
		return yearCombo;
	}

	public JTextField getSellingPriceText() {
		return sellingPriceText;
	}

	public JTextField getBuyingPriceText() {
		return buyingPriceText;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
	public JTextArea getPositionArea() {
		return positionArea;
	}
	
	private class MyListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == yearCombo) {
				yearHasChanged((int) yearCombo.getSelectedItem(), monthCombo);
			}
			if (e.getSource() == monthCombo) {
				if (monthCombo.getItemCount() != 0) {
					monthHasChanged((int) yearCombo.getSelectedItem(),
							(int) monthCombo.getSelectedItem(), dayCombo);
				}

			}

		}

	}
	private boolean checkForDoublication(JComboBox<Integer> box, int item) {
		boolean exists = false;
		for (int index = 0; index < box.getItemCount() && !exists; index++) {
			if (item == (box.getItemAt(index))) {
				return true;
			}
		}
		return false;
	}
	public void resetDateToTodaysDay(){
		dayCombo.setSelectedIndex(0);
		monthCombo.setSelectedIndex(0);
		yearCombo.setSelectedIndex(0);
	}
	public void yearHasChanged(int year, JComboBox<Integer> month) {
		month.removeAllItems();

		if (year == startDate.getYear()) {
			for (int i = startDate.getMonth(); i <= 12; i++) {
				if (!(checkForDoublication(month, (i)))) {
					month.addItem(i);
				}

			}
		} else {

			for (int i = 1; i <= 12; i++) {
				if (!(checkForDoublication(month, (i)))) {
					month.addItem(i);
				}
			}
		}
	}

	public void monthHasChanged(int year, int month, JComboBox<Integer> day) {
		day.removeAllItems();

		if (year == startDate.getYear() && month == startDate.getMonth()) {
			Date tempdate = startDate.copy();
			while (tempdate.getDay() < tempdate.daysInMonth()) {
				if (!(checkForDoublication(day, tempdate.getDay()))) {
					day.addItem(tempdate.getDay());
				}
				tempdate.nextDay();
			}
			day.addItem(tempdate.getDay());
		}

		else {
			Date tempdate = new Date(1, month, year);
			while (tempdate.getDay() < tempdate.daysInMonth()) {
				if (!(checkForDoublication(day, (tempdate.getDay())))) {
					day.addItem(tempdate.getDay());
				}
				tempdate.nextDay();
			}
			day.addItem(tempdate.getDay());
		}

	}
	

}
