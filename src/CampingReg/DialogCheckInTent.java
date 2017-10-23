package CampingReg;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogCheckInTent extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	/* JTextFields */
	private JTextField nameTxt, occupyedOnTxt, stayingTxt, 
	siteNumberTxt, tentersTxt;
	
	/* JButtons */
	private JButton okButton, cancelButton;
	
	/* Close Status */
	private boolean closeStatus;
	
	/* Tent Object */
	private Tent unit;  
	
	/* JLabels */
	private JLabel nameLabel, occupyingLabel, stayingLabel, siteLabel,
	tentersLabel;
	
	/* Gregorian Calendar */
	private GregorianCalendar gCalenderCheckIn;
	
	/* JDialog */
	private JDialog dialog;
	
	/* JFrame */
	private JFrame parentFrame;
	
	/* JPanel */
	private JPanel panel;
	
	/* Month, Day, and Year */
	private int month, day, year;

	public DialogCheckInTent(JFrame paOccupy, Tent d) {	
		unit = d; 
		
		//Creates Gregorian Calendar
		gCalenderCheckIn = new GregorianCalendar();
		gCalenderCheckIn = d.getCheckIn();
		month = gCalenderCheckIn.get(GregorianCalendar.MONTH);
		day = gCalenderCheckIn.get(GregorianCalendar.DAY_OF_MONTH);
		year = gCalenderCheckIn.get(GregorianCalendar.YEAR);
		
		//Creates JDialog
		dialog = new JDialog();
		
		//Creates JTextFields
		nameTxt = new JTextField();
		occupyedOnTxt = new JTextField(month + "/" + day + "/" + year);
		stayingTxt = new JTextField(d.getDaysStaying());
		siteNumberTxt = new JTextField(d.getSiteNumber());
		tentersTxt = new JTextField(d.getNumOfTenters());
		
		//Creates JButtons
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		
		//Creates JLabels
		nameLabel = new JLabel("Name of Reserver:");
		occupyingLabel = new JLabel("Occupied On Date:");
		stayingLabel = new JLabel("Days Staying:");
		siteLabel = new JLabel("Requested Site Number:");
		tentersLabel = new JLabel("Number of Tenters:");
		
		//Creates Frame
		parentFrame = paOccupy;
		
		//Sets closeStatus to false
		closeStatus = false;
		
		//Instantiates Buttons
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		//Creates JPanel and adds on items
		panel = new JPanel();
		panel.add(nameLabel);
		panel.add(nameTxt);
		panel.add(siteLabel);
		panel.add(siteNumberTxt);
		panel.add(occupyingLabel);
		panel.add(occupyedOnTxt);
		panel.add(stayingLabel);
		panel.add(stayingTxt);
		panel.add(tentersLabel);
		panel.add(tentersTxt);
		panel.add(Box.createVerticalStrut(5));
		panel.add(Box.createVerticalStrut(5));
		panel.add(okButton);
		panel.add(cancelButton);
		
		//Sets the layout of the panel
		panel.setLayout(new GridLayout(7, 2));
		
		//Adds the panel to the JDialog
		dialog.add(panel);
		
		//Sets specifics for JDialog
		dialog.setLocationRelativeTo(parentFrame);
		dialog.setModal(true);
		dialog.setTitle("Reserve A Tent Site");
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setSize(350, 250);
		dialog.setVisible(true);
	}
	
	/******************************************************************
	 * Handles the actions of the buttons
	 * 
	 * @return e is an ActionEvent that determines the action
	 *****************************************************************/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			if (check() == true) {
				unit.setSiteNumber(Integer.parseInt(siteNumberTxt.getText()));
				unit.setDaysStaying(Integer.parseInt(stayingTxt.getText()));
				unit.setNumOfTenters(Integer.parseInt(tentersTxt.getText()));
				setCheckInDate();
				closeStatus = true;
				dialog.dispose();
			}
			
		}
		else if (e.getSource() == cancelButton) {
			dialog.dispose();
		}
		
	}
	
	/******************************************************************
	 * Private helper method that checks if every text field has
	 * input
	 * 
	 * @return check is true if all textfields have input, false if
	 * not
	 *****************************************************************/
	private boolean check() {
		boolean check = false;
		if (nameTxt.getText().length() > 0 &&
				siteNumberTxt.getText().length() > 0 && 
				occupyedOnTxt.getText().length() > 0 && 
				stayingTxt.getText().length() > 0 && 
				tentersTxt.getText().length() > 0)  {

			check = true;
		}
		return check;
	}
	
	/******************************************************************
	 * Private helper method that converts the text in occupyedOnTxt 
	 * into a GregorianCalender and then calls the set Method from 
	 * the Site class
	 * 
	 *****************************************************************/
	private void setCheckInDate() {
		String input[] = occupyedOnTxt.getText().split("/");
		int inputInt[] = new int[input.length];

		if (!input[0].isEmpty()) {
			for (int i = 0; i < input.length; i++) {
				inputInt[i] = Integer.parseInt(input[i].trim());
			}
		}

		unit.setCheckIn(new GregorianCalendar(inputInt[2], inputInt[0],
				inputInt[1]));
	}
	
	
	/******************************************************************
	 * Getter method for unit
	 * 
	 * @return unit is the current RV
	 *****************************************************************/
	public Tent getTent() {
		return unit;
	}

	/******************************************************************
	 * Getter method for closeStatus
	 * 
	 * @return closeStatus is a boolean that changes when the 
	 * JDialog is finished
	 *****************************************************************/
	public boolean getCloseStatus() {
		return closeStatus;
	}
	
}
