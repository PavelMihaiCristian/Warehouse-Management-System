package ClientView_Admin;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import util.Item;

public class GUI_OrderDetails extends JFrame{
	
	private JPanel container;
	private DefaultListModel<Item> itemsModel;
	private JList<Item> itemList;
	private JScrollPane itemsScroll;
	
	private JLabel message;
	
	public GUI_OrderDetails(String string){
		
		super("Order List for order "+string);
		createComponents();
	}

	private void createComponents() {
		
		container=new JPanel(new BorderLayout());
		itemsModel=new DefaultListModel<Item>();
		itemList=new JList<Item>(itemsModel);
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemList.setLayoutOrientation(JList.VERTICAL);
		itemsScroll=new JScrollPane(itemList);
		itemsScroll.setPreferredSize(new Dimension(500,300));
		message=new JLabel();
		
		container.add(message,BorderLayout.NORTH);
		container.add(itemsScroll,BorderLayout.CENTER);

		
		setLayout(new BorderLayout());
		add(container,BorderLayout.CENTER);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700,700);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public JLabel getMessage() {
		return message;
	}

	public DefaultListModel<Item> getItemsModel() {
		return itemsModel;
	}

	public JList<Item> getItemList() {
		return itemList;
	}
}
