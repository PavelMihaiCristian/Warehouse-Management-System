package ClientView_Admin;

import java.awt.*;

import javax.swing.*;

import util.Order;

public class GUI_InProgressOrders extends JPanel {

	private JLabel Ltitle;
	private JList<Order> JLorders;
	private JButton Bcancel;
	private JPanel Ptitle, Pbutton, Pcontainer;

	// update 06/12
	private DefaultListModel<Order> inProgressOrderModel;
	private JScrollPane inProgressOrderScroll;
	// end
	public GUI_InProgressOrders() {
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
		
		Ptitle = new JPanel();
		inProgressOrderModel=new DefaultListModel<Order>();
		JLorders = new JList<Order>(inProgressOrderModel);
		//JLorders.setVisibleRowCount(15);
		JLorders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JLorders.setLayoutOrientation(JList.VERTICAL);
		//JLorders.setPreferredSize(new Dimension(100, 400));
		inProgressOrderScroll= new JScrollPane(JLorders);
		inProgressOrderScroll.setPreferredSize(new Dimension(750, 200));
		Ptitle.add(inProgressOrderScroll);
		
		Pcontainer.add(Ptitle,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		
		Pbutton = new JPanel();
		Bcancel = new JButton("Cancel");
		Pbutton.add(Bcancel);

		Pcontainer.add(Pbutton,gbc);

		
		add(Pcontainer);
		setVisible(true);
	}

	public JButton getBcancel() {
		return Bcancel;
	}

	public DefaultListModel<Order> getInProgressOrderModel() {
		return inProgressOrderModel;
	}
	
	public JList<Order> getJLorders() {
		return JLorders;
	}
}
