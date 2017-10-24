package CampingReg;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

public class RV extends Site {
	
	/** Represents the power supplied to the site **/
	private int power;   // 30, 40, 50 amps of service
	
	/*****************************************************************
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
	 *****************************************************************/
	public RV(String name, GregorianCalendar date, int stay,
			int site, int amps) {
		/** Calls super class and sets variables accordingly */
		super(name, date, stay, site);
		
		/** Sets power supplied */
		power = amps;
	}

	/*****************************************************************
	 * Getter method for the power supplied to the site, power 
	 * can be either 30, 40, or 50 amps
	 * 
	 * @return power is the power supplied to the site
	 *****************************************************************/
	public int getPower() {
		return power;
	}
	
	/*****************************************************************
	 * Getter method for the power supplied to the site, power 
	 * can be either 30, 40, or 50 amps
	 * 
	 * @param amps is the power supplied to the site, amps can 
	 * be either 30, 40, or 50
	 *****************************************************************/
	public void setPower(int amps) {
		if (amps == 30 || amps == 40 || amps == 50) {
			power = amps;
		}
		else 
			JOptionPane.showMessageDialog(null, "Power can only be"
					+ " supplied in 30, 40, or 50 amps.");
		}
}
