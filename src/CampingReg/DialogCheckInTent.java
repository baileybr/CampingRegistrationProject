package CampingReg;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**********************************************************************
 * Class that creates the JDialog Box when checking in to an Tent site
 * 
 * @author Brendan Bailey
 *********************************************************************/
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
	 * @param paOccupy is the frame for the JDialog, d is the Tent being
	 * checked in
	 *********************************************************************/
	public DialogCheckInTent(JFrame paOccupy, Tent d) {	
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
	 * @param e is an ActionEvent that determines the action
	 *****************************************************************/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			if (check() == true) {
				unit.setNameReserving(nameTxt.getText());
				closeStatus = true;
				if (setCheckInDate() == true && checkFields() == true){
					JOptionPane.showMessageDialog(null, "You Owe: $" + 
					calcPriceTent(Integer.parseInt(stayingTxt.getText())));
					dialog.dispose();
				}
			}else {
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
				stayingTxt.getText().length() > 0 && 
				tentersTxt.getText().length() > 0)  {

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
		boolean c = true;
		
		try {
			Integer.parseInt(siteNumberTxt.getText());
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
		
		try {
			Integer.parseInt(tentersTxt.getText());
		} catch (NumberFormatException ex){
			JOptionPane.showMessageDialog(null,"Number of tenters must"
					+ " be an integer. Please enter" + " an integer.");
			c = false;
		}
		
		if (a == true && b == true && c == true) {
			if(Integer.parseInt(siteNumberTxt.getText()) > 0 &&
					Integer.parseInt(stayingTxt.getText()) > 0 &&
					Integer.parseInt(tentersTxt.getText()) > 0) {
			unit.setSiteNumber(Integer.parseInt
					(siteNumberTxt.getText()));
			unit.setDaysStaying(Integer.parseInt
					(stayingTxt.getText()));
			unit.setNumOfTenters(Integer.parseInt
					(tentersTxt.getText()));
			return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Please make sure "
						+ "all inputs are greater than 0.");
			return false;
			}
		}
			return false;
			
	}
	
	/******************************************************************
	 * Private helper method that calculates the price of renting
	 * an Tent site
	 * 
	 * @return cost is the cost of renting the site
	 *****************************************************************/
	private double calcPriceTent(int daysStaying) {
		double cost = (daysStaying * 3 * unit.getNumOfTenters());
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
		else 
			return false;
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
