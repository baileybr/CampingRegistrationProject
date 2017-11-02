package CampingReg;

import java.io.Serializable;
import java.util.*;

/***********************************************************************
 * Site class, keeps track of the person who is occupying the site,
 * what date this person checked in, how many days they are staying,
 * and their site number
 * 
 * @author Brendan Bailey
 ***********************************************************************/
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
	
	/** The number of sites at the camp ground **/
	private final int NUM_OF_SITES = 5;
	
	/** The minimum accepted date **/
	private final GregorianCalendar MIN_DATE = 
			new GregorianCalendar(2017, 0, 1);
	
	/** The maximum accepted date **/
	private final GregorianCalendar MAX_DATE = 
			new GregorianCalendar(2099, 11, 31);
	
	/*******************************************************************
	 * Constructor that sets up the site with given parameters
	 * 
	 * @param name is the name of the person occupying the
	 * site
	 * @param date is the check In date
	 * @param stay is the number of days the person is reserving for 
	 * their stay
	 * @param site is the number of their site
	 * 
	 * @throws Exception when any set methods receive bad data
	 ******************************************************************/
	public Site(String name, GregorianCalendar date, int stay,
			int site) throws Exception {
		/** Sets the name of the occupant **/
		setNameReserving(name);
		
		/** Sets the check-in date **/
		setCheckIn(date);
		
		/** Sets the number of days the occupant plans to stay **/
		setDaysStaying(stay);
		
		/** Sets the site number **/
		setSiteNumber(site);
	}
	
	/*******************************************************************
	 * Constructor that sets up the site with predefined settings.
	 ******************************************************************/
	public Site() {
		
	}
	
	/*******************************************************************
	 * Getter method for the name of the occupant
	 * 
	 * @return nameReserving is the name of the occupant who made the
	 * reservation
	 ******************************************************************/
	public String getNameReserving() {
		return nameReserving;
	}
	
	/*******************************************************************
	 * Setter method for the name of the occupant
	 * 
	 * @param name is the name of the occupant who made the
	 * reservation
	 ******************************************************************/
	public void setNameReserving(String name) throws Exception {
		if (name.equals("")) {
			throw new Exception("Name cannot be empty");
		}
		
		nameReserving = name;
	}
	
	/*******************************************************************
	 * Getter method for the checkIn date of an occupant
	 * 
	 * @return checkIn is the date the occupant chose to check in
	 ******************************************************************/
	public GregorianCalendar getCheckIn() {
		return checkIn;
	}
	
	/*******************************************************************
	 * Getter method for getting the check in date as a string
	 * 
	 * @return string representation of the check in date
	 ******************************************************************/
	public String getCheckInAsString() {
		String retVal = (checkIn.get(GregorianCalendar.MONTH) + 1) + "/"
						+ checkIn.get(GregorianCalendar.DAY_OF_MONTH) +
						"/" + checkIn.get(GregorianCalendar.YEAR);
		
		return retVal;
	}
	
	/*******************************************************************
	 * Setter method for the checkIn date of an occupant
	 * 
	 * @param date is the date the occupant chose to reserve 
	 * 
	 * @throws Exception when the sent in date is null, less than 
	 * MIN_DATE, or greater than MAX_DATE
	 ******************************************************************/
	public void setCheckIn(GregorianCalendar date) throws Exception {
		if (date == null) {
			throw new Exception("Don't show");
		}
		else if (date.getTimeInMillis() < MIN_DATE.getTimeInMillis()) {
			throw new Exception("Check in date cannot be before "
					+ "1/1/2017");
		}
		else if (date.getTimeInMillis() > MAX_DATE.getTimeInMillis()) {
			throw new Exception("Check in date cannot be after "
					+ "12/31/2099");
		}
		
		checkIn = date;
	}
	
	/*******************************************************************
	 * Getter method for the number of days an occupant is staying
	 * 
	 * @return daysStaying is the number of days an occupant has
	 * reserved to stay
	 ******************************************************************/
	public int getDaysStaying() {
		return daysStaying;
	}
	
	/*******************************************************************
	 * Setter method for the number of days an occupant is staying
	 * 
	 * @param stay is the number of days an occupant has
	 * reserved to stay
	 * 
	 * @throws Exception when stay is 0 or negative
	 ******************************************************************/
	public void setDaysStaying(int stay) throws Exception {
		if (stay < 1) {
			throw new Exception("Cannot stay for less than 0 days");
		}
		
		daysStaying = stay;
	}
	
	/*******************************************************************
	 * Getter method for the site number in which an occupant is 
	 * staying
	 * 
	 * @return siteNumber is the number in which an occupant is
	 * staying
	 ******************************************************************/
	public int getSiteNumber() {
		return siteNumber;
	}
	
	/*******************************************************************
	 * Setter method for the site number in which an occupant is 
	 * staying
	 * 
	 * @param site is the site number in which an occupant is
	 * staying
	 * 
	 * @throws Exception when the selected site number is 0, negative,
	 * or greater than the number of sites available
	 ******************************************************************/
	public void setSiteNumber(int site) throws Exception {
		if (site < 1 || site > NUM_OF_SITES) {
			throw new Exception("There are only " + NUM_OF_SITES + " sites available");
		}
		
		siteNumber = site;
	}
}
