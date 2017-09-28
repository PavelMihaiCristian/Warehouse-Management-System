package ClientView_Admin;

import java.awt.*;

import javax.swing.*;

import util.Pickers;
import util.PickersSchedule;

public class GUI_ListOfPickers extends JPanel {

	private JList<PickersSchedule> JLatWork;
	private JList<Pickers> JLregisterd;
	private JButton Bcancel, Bsearch;

	private JPanel Plist1, Plist2, Plist3, Psearch, Pcontainer;
	private JLabel LatWork, Lregisterd, Lsearch;
	private JTextField Tsearch;

	// new code 04/12/2016
	private JPanel fourthPanel;
	private JLabel pickerInfoLabel;
	private JTextArea pickerInfoText;

	private DefaultListModel<Pickers> registeredModel;
	private DefaultListModel<PickersSchedule> atWorkModel;
	// end

	public GUI_ListOfPickers() {
		createComponents();
	}

	public void createComponents() {
		
		registeredModel=new DefaultListModel<Pickers>();
		atWorkModel=new DefaultListModel<PickersSchedule>();
		
		//Pickers[] pk=new Pickers[1];
		//pk[0]=new Pickers("Jack", 123456789, "street 55", 8700, 1233546, "somethin");
		
		//JLatWork = new JList<Pickers>(pk);
		JLatWork = new JList<PickersSchedule>(atWorkModel);
		JLregisterd = new JList<Pickers>(registeredModel);
		
		Bcancel = new JButton("Cancel");
		Bsearch = new JButton("Search");
		Plist1 = new JPanel(new BorderLayout());
		Plist2 = new JPanel(new BorderLayout());
		Plist3 = new JPanel(new BorderLayout());
		Psearch = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		Pcontainer = new JPanel();
		LatWork = new JLabel("Currently at work");
		Lregisterd = new JLabel("All pickers");
		Lsearch = new JLabel("Name: ");
		Tsearch = new JTextField(40);

		LatWork.setFont(new Font("title1", Font.BOLD, 15));
		JLatWork.setVisibleRowCount(15);
		JLatWork.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JLatWork.setLayoutOrientation(JList.VERTICAL);
		JLatWork.setPreferredSize(new Dimension(100, 900));
		JScrollPane js = new JScrollPane(JLatWork);

		Plist1.add(LatWork, BorderLayout.CENTER);
		Plist1.add(js, BorderLayout.SOUTH);

		Lregisterd.setFont(new Font("title2", Font.BOLD, 15));
		JLregisterd.setVisibleRowCount(15);
		JLregisterd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JLregisterd.setLayoutOrientation(JList.VERTICAL);
		JLregisterd.setPreferredSize(new Dimension(100, 900));
		JScrollPane js2 = new JScrollPane(JLregisterd);

		Plist2.add(Lregisterd, BorderLayout.CENTER);
		Plist2.add(js2, BorderLayout.SOUTH);

		// new code 04/12/2016
		
		
		//fourthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//pickerInfoLabel = new JLabel("Picker information");
		//pickerInfoText = new JTextArea(40,30);
		//fourthPanel.add(pickerInfoLabel);
		//fourthPanel.add(pickerInfoText);
		// end

		Psearch.add(Lsearch);
		Psearch.add(Tsearch);
		Psearch.add(Bsearch);
		Psearch.add(Bcancel);

		Plist3.add(Plist1, BorderLayout.WEST);
		Plist3.add(Plist2, BorderLayout.CENTER);
		Plist3.add(Psearch, BorderLayout.SOUTH);

		//some new code here
		Pcontainer.setLayout(new BorderLayout());
		Pcontainer.add(Plist3,BorderLayout.CENTER);
		//Pcontainer.add(fourthPanel,BorderLayout.SOUTH);

		add(Pcontainer);
		setSize(700, 400);
		setVisible(true);

	}
	
	public JTextArea getPickerInfoText() {
		return pickerInfoText;
	}

	public JTextField getTsearch() {
		return Tsearch;
	}
	public DefaultListModel<Pickers> getRegisteredModel() {
		return registeredModel;
	}

	public DefaultListModel<PickersSchedule> getAtWorkModel() {
		return atWorkModel;
	}
	
	public JList<PickersSchedule> getJLatWork() {
		return JLatWork;
	}

	public JList<Pickers> getJLregisterd() {
		return JLregisterd;
	}

	public JButton getBcancel() {
		return Bcancel;
	}

	public JButton getBsearch() {
		return Bsearch;
	}

	public static void main(String[] args) {

		GUI_ListOfPickers frame = new GUI_ListOfPickers();
	}
}
