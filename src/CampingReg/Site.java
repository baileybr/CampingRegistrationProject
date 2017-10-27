package CampingReg;

import java.io.Serializable;
import java.util.*;

/**********************************************************************
 * Site class, keeps track of the person who is occupying the site,
 * what date this person checked in, how many days they are staying,
 * and their site number
 * 
 * @author Brendan Bailey
 *********************************************************************/
public class Site implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** The name of the person who is occupying the Site **/
	protected String nameReserving;

	/** The date the Site was checked-in (occupied) **/
	protected GregorianCalendar checkIn;

	/** The estimated number of days the person is reserving **/
	/** This is just an estimate when the camper is  **/
	/** is checking in  **/
	protected int daysStaying; 

	/** The Site number **/
	protected int siteNumber;  
	
	/*****************************************************************
	 * Constructor that sets up the site with given parameters
	 * 
	 * @param name is the name of the person occupying the
	 * site
	 * @param date is the check In date
	 * @param stay is the number of days the person is reserving for 
	 * their stay
	 * @param site is the number of their site
	 *****************************************************************/
	public Site(String name, GregorianCalendar date, int stay,
			int site) {
		/** Sets the name of the occupant **/
		nameReserving = name;
		
		/** Sets the check-in date **/
		checkIn = date;
		
		/** Sets the number of days the occupant plans to stay **/
		daysStaying = stay;
		
		/** Sets the site number **/
		siteNumber = site;
		
	}
	
	/*****************************************************************
	 * Constructor that sets up the site with predefined settings.
	 * 
	 *****************************************************************/
	public Site() {
//		/** Sets the name of the occupant */
//		nameReserving = "Dan Smith";
//		
//		/** Sets the check-in date */
//		checkIn = new GregorianCalendar(2017, 10, 30);
//		
//		/** Sets the number of days the occupant plans to stay */
//		daysStaying = 1;
//		
//		/** Sets the site number */
//		siteNumber = 1;
	}
	
	/*****************************************************************
	 * Getter method for the name of the occupant
	 * 
	 * @return nameReserving is the name of the occupant who made the
	 * reservation
	 *****************************************************************/
	public String getNameReserving() {
		return nameReserving;
	}
	
	/*****************************************************************
	 * Setter method for the name of the occupant
	 * 
	 * @param name is the name of the occupant who made the
	 * reservation
	 *****************************************************************/
	public void setNameReserving(String name) {
		nameReserving = name;
	}
	
	/*****************************************************************
	 * Getter method for the checkIn date of an occupant
	 * 
	 * @return checkIn is the date the occupant chose to check in
	 *****************************************************************/
	public GregorianCalendar getCheckIn() {
		return checkIn;
	}
	
	/*****************************************************************
	 * Setter method for the checkIn date of an occupant
	 * 
	 * @param date is the date the occupant chose to reserve 
	 *****************************************************************/
	public void setCheckIn(GregorianCalendar date) {
		checkIn = date;
	}
	
	/*****************************************************************
	 * Getter method for the number of days an occupant is staying
	 * 
	 * @return daysStaying is the number of days an occupant has
	 * reserved to stay
	 *****************************************************************/
	public int getDaysStaying() {
		return daysStaying;
	}
	
	/*****************************************************************
	 * Setter method for the number of days an occupant is staying
	 * 
	 * @param stay is the number of days an occupant has
	 * reserved to stay
	 *****************************************************************/
	public void setDaysStaying(int stay) {
		daysStaying = stay;
	}
	
	/*****************************************************************
	 * Getter method for the site number in which an occupant is 
	 * staying
	 * 
	 * @return siteNumber is the number in which an occupant is
	 * staying
	 *****************************************************************/
	public int getSiteNumber() {
		return siteNumber;
	}
	
	/*****************************************************************
	 * Setter method for the site number in which an occupant is 
	 * staying
	 * 
	 * @param site is the site number in which an occupant is
	 * staying
	 *****************************************************************/
	public void setSiteNumber(int site) {
		siteNumber = site;
	}
}
