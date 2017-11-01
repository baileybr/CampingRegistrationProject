package CampingReg;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;

/**********************************************************************
 * Class that creates the JDialog Box when checking in to an RV site
 * 
 * @author Brendan Bailey
 *********************************************************************/
public class DialogCheckInRv extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	/* JTextFields */
	private JTextField nameTxt, occupyedOnTxt, stayingTxt, 
	siteNumberTxt;
	
	/* JComboBox */
	private JComboBox<String> powerBox;
	
	/* JButtons */
	private JButton okButton, cancelButton;
	
	/* Close Status */
	private boolean closeStatus;
	
	/* RV object */
	private RV unit;  
	
	/* JLabels */
	private JLabel nameLabel, occupyingLabel, stayingLabel, siteLabel,
	powerLabel;
	
	/* Gregorian Calendar */
	private GregorianCalendar gCalendarCheckIn;
	
	/* JDialog */
	private JDialog dialog;
	
	/* JFrame */
	private JFrame parentFrame;
	
	/* JPanel */
	private JPanel panel;
	
	/* Month, Day, and Year */
	private int month, day, year;
	

	/**********************************************************************
	 * Constructor that sets up the Dialog with given parameters
	 * 
	 * @param paOccupy is the frame for the JDialog, d is the RV being
	 * checked in
	 *********************************************************************/
	public DialogCheckInRv(JFrame paOccupy, RV d) {	
		unit = d; 
		
		//Creates Gregorian Calendar
		gCalendarCheckIn = new GregorianCalendar();
		gCalendarCheckIn.setLenient(false);
		month = gCalendarCheckIn.get(GregorianCalendar.MONTH) + 1;
		day = gCalendarCheckIn.get(GregorianCalendar.DAY_OF_MONTH);
		year = gCalendarCheckIn.get(GregorianCalendar.YEAR);
		
		//Creates JDialog
		dialog = new JDialog();
		
		//Creates JTextFields
		nameTxt = new JTextField(d.getNameReserving());
		occupyedOnTxt = new JTextField(month + "/" + day + "/" + year);
		stayingTxt = new JTextField(d.getDaysStaying());
		siteNumberTxt = new JTextField(d.getSiteNumber());
		
		//Creates JComboBox
		powerBox = new JComboBox<String>();
		
		//Creates JButtons
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		
		//Creates JLabels
		nameLabel = new JLabel("Name of Reserver:");
		occupyingLabel = new JLabel("Occupied On Date:");
		stayingLabel = new JLabel("Days Staying:");
		siteLabel = new JLabel("Requested Site Number:");
		powerLabel = new JLabel("Power in AMPS:");
		
		//Creates Frame
		parentFrame = paOccupy;
		
		//Sets closeStatus to false
		closeStatus = false;
		
		//Instantiate Buttons
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		//Adds Items to powerBox
		powerBox.addItem("30");
		powerBox.addItem("40");
		powerBox.addItem("50");
		
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
		panel.add(powerLabel);
		panel.add(powerBox);
		panel.add(Box.createVerticalStrut(5));
		panel.add(Box.createVerticalStrut(5));
		panel.add(okButton);
		panel.add(cancelButton);
		
		//Sets the layout of panel
		panel.setLayout(new GridLayout(7, 2));
		
		//Adds the panel to the JDialog
		dialog.add(panel);
		
		//Sets specifics for JDialog
		dialog.setLocationRelativeTo(parentFrame);
		dialog.setModal(true);
		dialog.setTitle("Reserve An RV Site");
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setSize(350, 250);
		dialog.setVisible(true);
	}
	
	/******************************************************************
	 * Handles the actions of the buttons
	 * 
	 * @param e is an ActionEvent that determines the action
	 *****************************************************************/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			if (check() == true) {
				unit.setNameReserving(nameTxt.getText());
				setPower();
				closeStatus = true;
				if (setCheckInDate() == true && checkFields() == true){
					JOptionPane.showMessageDialog(null, "You Owe: $" + 
					calcPriceRV(Integer.parseInt
							(stayingTxt.getText())));
					dialog.dispose();
				}	
			}
			else {
				JOptionPane.showMessageDialog(null, "Please make sure "
						+ "all fields are filled in.");
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
				stayingTxt.getText().length() > 0) {

			check = true;
		}
		return check;
	}
	
	/******************************************************************
	 * Private helper method that ensures appropriate input is 
	 * entered into the textfields
	 * 
	 *****************************************************************/
	private boolean checkFields() {
		boolean a = true;
		boolean b = true;
		
		try {
			if (Integer.parseInt(siteNumberTxt.getText()) > 5 || 
					Integer.parseInt(siteNumberTxt.getText()) < 0) {
				a = false; 
				JOptionPane.showMessageDialog(null, "Site number must"
						+ " be from 1 to 5.");
			}
			
		} catch (NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Site number must be"
					+ " an integer. Please enter" + " an integer.");
			a = false;
		}
		
		try {
			Integer.parseInt(stayingTxt.getText());
		} catch (NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Days staying must be"
					+ " an integer. Please enter" + " an integer.");
			b = false;
		}
		
		if (a == true && b == true) {
			if(Integer.parseInt(siteNumberTxt.getText()) > 0 &&
					Integer.parseInt(stayingTxt.getText()) > 0) {
			unit.setSiteNumber(Integer.parseInt
					(siteNumberTxt.getText()));
			unit.setDaysStaying(Integer.parseInt
					(stayingTxt.getText()));
			return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Please make sure all "
						+ "inputs are greater than 0.");
			return false;
			}
		}
		else { 
			return false;
		}
		}
	
	/******************************************************************
	 * Private helper method that calculates the price of renting
	 * an RV site
	 * 
	 * @return cost is the cost of renting the site
	 *****************************************************************/
	private double calcPriceRV(int daysStaying) {
		double cost = daysStaying * 30;
		return cost;
		}
	
	/******************************************************************
	 * Private helper method that converts the text in occupyedOnTxt 
	 * into a GregorianCalender and then calls the set Method from 
	 * the Site class
	 * 
	 *****************************************************************/
	private boolean setCheckInDate() {
		boolean a = true;
		String input[] = occupyedOnTxt.getText().split("/");
		int inputInt[] = new int[input.length];

		if (!input[0].isEmpty()) {
			if (input.length == 3) {
			for (int i = 0; i < input.length; i++) {
				try {
				inputInt[i] = Integer.parseInt(input[i].trim());
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter"
							+ " the date"
							+ " in the correct format, mm/dd/yyyy");
					a = false;
					return false;
					}
			}
			
			}else {
			JOptionPane.showMessageDialog(null, "Please enter the date"
					+ " in the correct format, mm/dd/yyyy");
			a = false;
			return false;
			}
			
		}
		if(a = true && checkDates(inputInt[0], inputInt[1],
				inputInt[2]) == true) {
		unit.setCheckIn(new GregorianCalendar(inputInt[2],
				inputInt[0], inputInt[1]));
		return true;
		}
		else { 
			return false;
		}	
	}
	
	/******************************************************************
	 * Private helper method that checks that dates are valid
	 * 
	 *****************************************************************/
	private boolean checkDates(int month, int day, int year) {
		boolean a = false;
		boolean b = false;
		boolean c = false;
		if (year >= 2017 && year < 2099) {
			a = true;
			if(month > 0 && month < 13) {
				b = true;
				if (month == 1 && day > 31 || month == 1 && day < 0) {
					JOptionPane.showMessageDialog(null, "For January,"
							+ " please choose a day from 1 to 31.");
				}else if (month == 2 && day > 28 || month == 2 &&
						day < 0) {
					JOptionPane.showMessageDialog(null, "For February,"
							+ " please choose a day from 1 to 28.");
				}else if (month == 3 && day > 31 || month == 3 &&
						day < 0) {
					JOptionPane.showMessageDialog(null, "For March,"
							+ " please choose a day from 1 to 31.");
				}else if (month == 4 && day > 30 || month == 4 &&
						day < 0) {
					JOptionPane.showMessageDialog(null, "For April,"
							+ " please choose a day from 1 to 30.");
				}else if (month == 5 && day > 31 || month == 5 &&
						day < 0) {
					JOptionPane.showMessageDialog(null, "For May,"
							+ " please choose a day from 1 to 31.");
				}else if (month == 6 && day > 30 || month == 6 &&
						day < 0) {
					JOptionPane.showMessageDialog(null, "For June,"
							+ " please choose a day from 1 to 30.");
				}else if (month == 7 && day > 31 || month == 7 &&
						day < 0) {
					JOptionPane.showMessageDialog(null, "For July,"
							+ " please choose a day from 1 to 31.");
				}else if (month == 8 && day > 31 || month == 8 &&
						day < 0) {
					JOptionPane.showMessageDialog(null, "For August,"
							+ " please choose a day from 1 to 31.");
				}else if (month == 9 && day > 30 || month == 9 &&
						day < 0) {
					JOptionPane.showMessageDialog(null,"For September,"
							+ " please choose a day from 1 to 30.");
				}else if (month == 10 && day > 31 || month == 10 &&
						day < 0) {
					JOptionPane.showMessageDialog(null, "For October,"
							+ " please choose a day from 1 to 31.");
				}else if (month == 11 && day > 30 || month == 11 &&
						day < 0) {
					JOptionPane.showMessageDialog(null, "For November,"
							+ " please choose a day from 1 to 30.");
				}else if (month == 12 && day > 31 || month == 12 &&
						day < 0) {
					JOptionPane.showMessageDialog(null, "For December,"
							+ " please choose a day from 1 to 31.");
				}else
					c = true;
			}else {
			JOptionPane.showMessageDialog(null, "Please choose a month"
					+ " from 1 to 12.");
			return false;
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please choose a year"
					+ " from 2017 to 2099.");
			return false;
			}
		if (a == true && b == true && c == true) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/******************************************************************
	 * Private helper method that sets the power in the RV class
	 * 
	 *****************************************************************/
	private void setPower() {
		if (powerBox.getSelectedItem().equals("30")) {
			unit.setPower(30);
		} else if (powerBox.getSelectedItem().equals("40")) {
			unit.setPower(40);
		} else {
			unit.setPower(50);
		}
	}
	
	
	/******************************************************************
	 * Getter method for unit
	 * 
	 * @return unit is the current RV
	 *****************************************************************/
	public RV getRV() {
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
