package ClientView_Admin;

import java.awt.*;
import javax.swing.*;

public class GUI_NewProduct extends JPanel {

	private JLabel LproductID, Lname, Lcategory, Lamount, supplierLabel;
	private JTextField TproductID, Tname, TCategory;
	private JComboBox<String> Ccategory;
	private JComboBox<String> suppliers;

	private JPanel Pcontainer, Pcontainer2, Pid, Pname, Pcategory, Pamount,
			Pbutton, supplierPanel;
	private JButton addB;
	private JButton cancelButton;

	public GUI_NewProduct() {
		createComponents();

	}

	public void createComponents() {
		LproductID = new JLabel("ID: ");
		Lname = new JLabel("Name: ");
		Lcategory = new JLabel("Category: ");
		Lamount = new JLabel("New category: ");
		cancelButton = new JButton("Cancel");

		TproductID = new JTextField(7);
		Tname = new JTextField(7);
		TCategory = new JTextField(7);

		String[] category = { "Add Category" };

		Ccategory = new JComboBox<>(category);

		addB = new JButton("Add");

		Pid = new JPanel(new GridLayout(1, 2));
		Pname = new JPanel(new GridLayout(1, 2));
		Pcategory = new JPanel(new GridLayout(1, 2));
		Pamount = new JPanel(new GridLayout(1, 4));
		supplierPanel = new JPanel(new GridLayout(1, 2));
		Pcontainer = new JPanel(new GridLayout(6, 1, 10, 10));
		Pcontainer2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 30));

		supplierLabel = new JLabel("Supplier name:");
		suppliers = new JComboBox<String>();
		supplierPanel.add(supplierLabel);
		supplierPanel.add(suppliers);

		Pid.add(LproductID);
		Pid.add(TproductID);
		Pbutton = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		Pname.add(Lname);
		Pname.add(Tname);
		Pcategory.add(Lcategory);
		Pcategory.add(Ccategory);
		Pamount.add(Lamount);
		Pamount.add(TCategory);

		Pbutton.add(addB);
		Pbutton.add(cancelButton);

		Pcontainer.add(Pid);
		Pcontainer.add(Pname);
		Pcontainer.add(Pcategory);
		Pcontainer.add(Pamount);
		Pcontainer.add(supplierPanel);
		Pcontainer.add(Pbutton);
		Pcontainer2.add(Pcontainer);

		add(Pcontainer2);
		setSize(400, 300);
		setVisible(true);

	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JTextField getTproductID() {
		return TproductID;
	}

	public JTextField getTname() {
		return Tname;
	}

	public JTextField getTCategory() {
		return TCategory;
	}

	public JComboBox<String> getCcategory() {
		return Ccategory;
	}

	public JButton getAddB() {
		return addB;
	}

	public JComboBox<String> getSuppliers() {
		return suppliers;
	}
}
