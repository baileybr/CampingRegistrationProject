package CampingReg;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

/***********************************************************************
 * RV class, keeps track of the power supplied to the site,
 * then relies on the extension of the Site class to keep track of 
 * other variables
 * 
 * @author Brendan Bailey
 **********************************************************************/
public class RV extends Site {
	
	/** Represents the power supplied to the site **/
	private int power;   // 30, 40, 50 amps of service
	
	/*******************************************************************
	 * Constructor that sets up the RV site with given parameters
	 * 
	 * @param name is the name of the person occupying the
	 * site
	 * @param date is the check In date
	 * @param stay is the number of days the person is reserving for 
	 * their stay
	 * @param site is the number of their site
	 * @param amps is the power supplied to the site (can be either 30,
	 * 40, or 50)
	 * 
	 * @throws Exception when any of the parameters are invalid values
	 ******************************************************************/
	public RV(String name, GregorianCalendar date, int stay,
			int site, int amps) throws Exception {
		/** Calls super class and sets variables accordingly */
		super(name, date, stay, site);
		
		/** Sets power supplied */
		power = amps;
	}
	
	/*******************************************************************
	 * Constructor that sets up the tent site with default settings
	 ******************************************************************/
	public RV () {
		
	}

	/*******************************************************************
	 * Getter method for the power supplied to the site, power 
	 * can be either 30, 40, or 50 amps
	 * 
	 * @return power is the power supplied to the site
	 ******************************************************************/
	public int getPower() {
		return power;
	}
	
	/*******************************************************************
	 * Method that calculates the cost of the RV site
	 * 
	 * @return cost is the cost of the RV site
	 ******************************************************************/
	public int calcCost(int days) {
		return 30 * days;
	}
	
	/*******************************************************************
	 * Getter method for the power supplied to the site, power 
	 * can be either 30, 40, or 50 amps
	 * 
	 * @param amps is the power supplied to the site, amps can 
	 * be either 30, 40, or 50
	 * 
	 * @throws Exception when amps aren't a correct value
	 ******************************************************************/
	public void setPower(int amps) throws Exception {
		if (amps == 30 || amps == 40 || amps == 50) {
			power = amps;
		}
		else {
			throw new Exception("Power can only be supplied in "
					+ "30, 40, or 50 amps.");
		}
	}
	
	public String toString() {
		return "RV," +
				this.getNameReserving() + "," +
				this.getCheckInAsString() + "," +
				this.getDaysStaying() + "," +
				this.getSiteNumber() + "," +
				this.getPower();
	}

}
