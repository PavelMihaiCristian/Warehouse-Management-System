package ClientView_Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import util.Date;

public class GUI_SearchByExpiryDate extends JPanel {
	private JLabel Ldate;
	private JTextArea TshowExpiry;
	private JButton searchButton,cancelButton;
	private JComboBox<Integer> CexpDay, CexpMonth, CexpYear;

	private JPanel Pcontainer, Pdate, Ptext;
	private JScrollPane bar;
	private Date startDate;
	private Date endDate;

	public GUI_SearchByExpiryDate() {
		createComponnts();
	}

	public void createComponnts() {
		startDate = new Date();
		int nextYear = startDate.getYear() + 10;
		endDate = new Date(startDate.getDay(), startDate.getMonth(), nextYear);

		Ldate = new JLabel("Date: ");

		TshowExpiry = new JTextArea(20, 50);
		bar = new JScrollPane(TshowExpiry);
		bar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		searchButton = new JButton("Search");
		cancelButton=new JButton("Cancel");
		CexpDay = new JComboBox<>();
		CexpMonth = new JComboBox<>();
		CexpYear = new JComboBox<>();
		CexpYear.addActionListener(new MyListener());
		CexpMonth.addActionListener(new MyListener());
		for (int i = startDate.getYear(); i < endDate.getYear(); i++) {
			if (!(checkForDoublication(CexpYear, i))) {
				CexpYear.addItem((i));
			}
		}

		Ptext = new JPanel(new BorderLayout());
		Pdate = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Pcontainer = new JPanel(new BorderLayout());

		Pdate.add(Ldate);
		Pdate.add(CexpDay);
		Pdate.add(CexpMonth);
		Pdate.add(CexpYear);
		Pdate.add(searchButton);
		Pdate.add(cancelButton);
		Ptext.add(bar);
		Pcontainer.add(Pdate, BorderLayout.NORTH);
		Pcontainer.add(Ptext, BorderLayout.CENTER);

		add(Pcontainer);
		setSize(400, 300);
		setVisible(true);
	}


	public void printToTextArea(int ID, String name, Object expiryDate,
			int amount) {
		TshowExpiry
				.append("Product ID:" + ID + ", Product name: " + name
						+ ", Expiry Date: " + expiryDate + ", Amount: "
						+ amount + "\n");
	}

	public static void main(String[] args) {

		GUI_SearchByExpiryDate frame = new GUI_SearchByExpiryDate();
	}

	// ***************New********
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
			// System.out.println(tempdate.daysInMonth());
			while (tempdate.getDay() < tempdate.daysInMonth()) {
				// System.out.println(tempdate.getDay());
				// System.out.println("Here");
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

	private boolean checkForDoublication(JComboBox<Integer> box, int item) {
		boolean exists = false;
		for (int index = 0; index < box.getItemCount() && !exists; index++) {
			if (item == (box.getItemAt(index))) {
				return true;
			}
		}
		return false;
	}

	private class MyListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == CexpYear) {
				yearHasChanged((int) CexpYear.getSelectedItem(), CexpMonth);
			}
			if (e.getSource() == CexpMonth) {
				if (CexpMonth.getItemCount() != 0) {
					monthHasChanged((int) CexpYear.getSelectedItem(),
							(int) CexpMonth.getSelectedItem(), CexpDay);
				}

			}

		}

	}
	
	public void resetDateToTodaysDay(){
		CexpDay.setSelectedIndex(0);
		CexpMonth.setSelectedIndex(0);
		CexpYear.setSelectedIndex(0);
	}
	
	public JTextArea getTshowExpiry() {
		return TshowExpiry;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JComboBox<Integer> getCexpDay() {
		return CexpDay;
	}

	public JComboBox<Integer> getCexpMonth() {
		return CexpMonth;
	}

	public JComboBox<Integer> getCexpYear() {
		return CexpYear;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}
