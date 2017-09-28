package ClientView_Admin;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DeletePickerPanel extends JPanel{

	private JPanel containerPanel;
	private JPanel idPanel;
	private JPanel pickerInfoPanel;
	private JPanel buttonsPanel;
	
	private JLabel pickerIdLabel;
	
	private JTextField pickerIdTextField;
	
	private JTextField pickerInfoTextField;
	
	private JButton deletePickerButton;
	private JButton cancelButton;
	
	public DeletePickerPanel(){
		createPanel();
	}

	public void createPanel() {
		
		setLayout(new BorderLayout());
		containerPanel=new JPanel();
		idPanel=new JPanel();
		pickerInfoPanel=new JPanel();
		buttonsPanel=new JPanel();
		
		containerPanel.setLayout(new BorderLayout());
		
		pickerIdLabel=new JLabel("Picker Id");
		pickerIdTextField=new JTextField(10);
		pickerInfoTextField=new JTextField(30);
		
		deletePickerButton=new JButton("Delete");
		cancelButton=new JButton("Cancel");
		
		idPanel.add(pickerIdLabel);
		idPanel.add(pickerIdTextField);
		
		pickerInfoPanel.add(pickerInfoTextField);
		
		buttonsPanel.add(deletePickerButton);
		buttonsPanel.add(cancelButton);
		
		containerPanel.add(idPanel,BorderLayout.NORTH);
		containerPanel.add(pickerInfoPanel,BorderLayout.CENTER);
		containerPanel.add(buttonsPanel,BorderLayout.SOUTH);
		
		add(containerPanel,BorderLayout.CENTER);
		setSize(400, 300);
		setVisible(true);
	}

	public JTextField getPickerIdTextField() {
		return pickerIdTextField;
	}

	public JTextField getPickerInfoTextField() {
		return pickerInfoTextField;
	}

	public JButton getDeletePickerButton() {
		return deletePickerButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}
