package CampingReg;

import java.util.GregorianCalendar;

/**********************************************************************
 * Tent class, keeps track of the number of Tenters, then relies on
 * the extension of the Site class to keep track of other variables
 * 
 * @author Brendan Bailey
 *********************************************************************/
public class Tent extends Site {
	
	/** Represents the number of tenters on this site */
	private int numOfTenters;
	
	/*****************************************************************
	 * Constructor that sets up the tent site with given parameters
	 * 
	 * @param name is the name of the person occupying the
	 * site, date is the check In date, stay is the number of days the
	 * person is reserving for their stay, site is the number of their 
	 * site, tents is the number of tenters on the site
	 *****************************************************************/
	public Tent(String name, GregorianCalendar date, int stay,
			int site, int tents) {
		/** Calls super class and sets variables accordingly */
		super(name, date, stay, site);
		
		/** Sets the number of tenters */
		numOfTenters = tents;
		
	}
	
	/*****************************************************************
	 * Constructor that sets up the tent site with default settings
	 * 
	 *****************************************************************/
	public Tent () {
		
	}
	
	/*****************************************************************
	 * Getter method for the number of tenters on this site
	 * 
	 * @return numOfTenter is the number of tenters on this site
	 *****************************************************************/
	public int getNumOfTenters() {
		return numOfTenters;
	}
	
	/*****************************************************************
	 * Setter method for the number of tenters on this site
	 * 
	 * @param tents is the number of tenters on this site
	 *****************************************************************/
	public void setNumOfTenters(int tents) {
		numOfTenters = tents;
	}
	
}
