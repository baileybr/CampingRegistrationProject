package CampingReg;

import java.util.GregorianCalendar;

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
		/** Sets the name of the occupant */
		nameReserving = name;
		
		/** Sets the check-in date */
		checkIn = date;
		
		/** Sets the number of days the occupant plans to stay */
		daysStaying = stay;
		
		/** Sets the site number */
		siteNumber = site;
		
		/** Sets the number of tenters */
		numOfTenters = tents;
		
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
