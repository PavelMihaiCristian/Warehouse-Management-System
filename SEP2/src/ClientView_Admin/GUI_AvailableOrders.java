package ClientView_Admin;

import java.awt.*;

import javax.swing.*;

import util.Order;

public class GUI_AvailableOrders extends Panel {
	private JList<Order> JLorders;
	private JButton Bcancel;
	private JPanel Pcontainer,BPanel,LPanel;

	private DefaultListModel<Order> ordersModel;
	private JScrollPane ordersScroll;


	public GUI_AvailableOrders() {
		createComponents();
	}

	public void createComponents() {
		Pcontainer=new JPanel();
		Pcontainer.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.insets=new  Insets(10, 10, 10, 10);
		
		gbc.gridx=0;
		gbc.gridy=0;
		
		LPanel=new JPanel();
		ordersModel=new DefaultListModel<Order>();
		JLorders=new JList<Order>(ordersModel);
		//JLorders.setVisibleRowCount(15);
		JLorders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JLorders.setLayoutOrientation(JList.VERTICAL);
		//JLorders.setPreferredSize(new Dimension(600,200));
		ordersScroll=new JScrollPane(JLorders);
		ordersScroll.setPreferredSize(new Dimension(750,200));
		LPanel.add(ordersScroll);
		
		Pcontainer.add(LPanel,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		
		BPanel=new JPanel();
		Bcancel=new JButton("Cancel");
		BPanel.add(Bcancel);
		
		Pcontainer.add(BPanel,gbc);
		
		add(Pcontainer);
		setVisible(true);
	}

	public JButton getBcancel() {
		return Bcancel;
	}

	public DefaultListModel<Order> getOrdersModel() {
		return ordersModel;
	}
	
	public JList<Order> getJLorders() {
		return JLorders;
	}
}
