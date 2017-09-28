package ClientView_Admin;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.omg.CORBA.INITIALIZE;

import util.Product;

public class GUI_SuppliersProducts extends JFrame{

	private JPanel container;
	
	private DefaultListModel<Product> model;
	private JList<Product> list;
	private JScrollPane scroll;
	
	public GUI_SuppliersProducts(String name){
		super(name);
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
		
		model=new DefaultListModel<Product>();
		list=new JList<Product>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		scroll=new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(700, 600));
		
		container.add(scroll,gbc);
		
		add(container);
		setSize(1000, 800);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public DefaultListModel<Product> getModel() {
		return model;
	}

	public static void main(String[] args){
		GUI_SuppliersProducts s=new GUI_SuppliersProducts("suppliers 34 products");
	}
}

