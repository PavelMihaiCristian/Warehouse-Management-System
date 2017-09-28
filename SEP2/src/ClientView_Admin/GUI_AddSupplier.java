package ClientView_Admin;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI_AddSupplier extends JPanel {

	private JPanel container;
	private JPanel supplierNamePanel;
	private JPanel streetPanel;
	private JPanel postCodePanel;
	private JPanel phoneNumberPanel;
	private JPanel emailPanel;
	private JPanel buttonPanel;
	
	private JLabel supplierNameLabel;
	private JLabel phoneNumberLabel;
	private JLabel emailLabel;
	private JLabel streetLabel;
	private JLabel postCodeLabel;

	private JTextField supplierNameText;
	private JTextField phoneNumberText;
	private JTextField emailText;
	private JTextField streetText;
	private JTextField postCodeText;

	private JButton addButton;
	private JButton cancelButton;

	public GUI_AddSupplier() {
		initialize();
	}

	private void initialize() {
		supplierNameLabel = new JLabel("Supplier Name: ");
		streetLabel = new JLabel("Street Name & #: ");
		postCodeLabel = new JLabel("Postal Code: ");
		phoneNumberLabel = new JLabel("Phone Number: ");
		emailLabel = new JLabel("E-mail: ");

		supplierNameText = new JTextField(15);
		streetText = new JTextField(15);
		postCodeText = new JTextField(15);
		phoneNumberText = new JTextField(15);
		emailText = new JTextField(15);

		cancelButton = new JButton("Cancel");
		addButton = new JButton("Add");

		supplierNamePanel = new JPanel(new GridLayout(1, 2));
		streetPanel = new JPanel(new GridLayout(1, 2));
		postCodePanel = new JPanel(new GridLayout(1, 2));
		phoneNumberPanel = new JPanel(new GridLayout(1, 2));
		emailPanel = new JPanel(new GridLayout(1, 2));
		container = new JPanel(new GridLayout(6, 1, 10, 10));

		supplierNamePanel.add(supplierNameLabel);
		supplierNamePanel.add(supplierNameText);
		streetPanel.add(streetLabel);
		streetPanel.add(streetText);
		postCodePanel.add(postCodeLabel);
		postCodePanel.add(postCodeText);
		phoneNumberPanel.add(phoneNumberLabel);
		phoneNumberPanel.add(phoneNumberText);
		emailPanel.add(emailLabel);
		emailPanel.add(emailText);

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);

		container.add(supplierNamePanel);
		container.add(streetPanel);
		container.add(postCodePanel);
		container.add(phoneNumberPanel);
		container.add(emailPanel);
		container.add(buttonPanel);

		add(container);
		setVisible(true);

	}

	public JTextField getSupplierNameText() {
		return supplierNameText;
	}

	public JTextField getPhoneNumberText() {
		return phoneNumberText;
	}

	public JTextField getEmailText() {
		return emailText;
	}

	public JTextField getStreetText() {
		return streetText;
	}

	public JTextField getPostCodeText() {
		return postCodeText;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}
