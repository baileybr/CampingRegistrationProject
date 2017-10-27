package CampingReg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/***********************************************************************
 * The SiteModel class is the structure that all rows in the table will
 * follow
 * 
 * @author Ben Buurstra
 **********************************************************************/
public class SiteModel extends AbstractTableModel {
	
	/** List of all the sites **/
	private ArrayList<Site> sites;
	
	/** Array of the column names **/
	private String[] columnNames;
	
	/*******************************************************************
	 * The default constructor that initializes the list of sites and
	 * sets the column names
	 ******************************************************************/
	public SiteModel() {
		sites = new ArrayList<Site>();
		columnNames = new String[] { "Name Reserving", 
									"Checked in", 
									"Days Staying", 
									"Site #", 
									"Tent/RV info" };
	}
	
	/*******************************************************************
	 * Gets the row count
	 * 
	 * @return returns the number of sites
	 ******************************************************************/
	public int getRowCount() {
		return sites.size();
	}

	/*******************************************************************
	 * Gets the column count
	 * 
	 * @return returns the number of columns
	 ******************************************************************/
	public int getColumnCount() {
		return columnNames.length;
	}
	
	/*******************************************************************
	 * Gets the column name at a specific index
	 * 
	 * @param col the column index you want the name of
	 * 
	 * @return returns the name of the specified column
	 ******************************************************************/
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/*******************************************************************
	 * Gets the value at a specific row and column
	 * 
	 * @param rowIndex the specified row
	 * @param columnIndex the specified column
	 * 
	 * @return returns the object at the specified row and column
	 ******************************************************************/
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				return sites.get(rowIndex).getNameReserving();
			case 1:
				GregorianCalendar temp = sites.get(rowIndex).getCheckIn();
				
				String retVal = (temp.get(GregorianCalendar.MONTH))
					+ "/" + (temp.get(GregorianCalendar.DAY_OF_MONTH))
					+ "/" + (temp.get(GregorianCalendar.YEAR));
				
				return retVal;
			case 2:
				return sites.get(rowIndex).getDaysStaying();
			case 3:
				return sites.get(rowIndex).getSiteNumber();
			case 4:
				if (sites.get(rowIndex) instanceof Tent) {
					String text = 
							((Tent)sites.get(rowIndex))
							.getNumOfTenters() != 1 ? " tenters" : 
							" tenter";
					
					return ((Tent)sites.get(rowIndex))
							.getNumOfTenters() + text;
				}
				else if (sites.get(rowIndex) instanceof RV) {
					return ((RV)sites.get(rowIndex)).getPower() + 
							" amps";
				}
				break;
		}
		
		return null;
	}
	
	/*******************************************************************
	 * Adds a site to the list (and updates the view)
	 * 
	 * @param site the site to be added to the list (and view)
	 ******************************************************************/
	public void add(Site site) {
		sites.add(site);
		
		refresh();
	}
	
	public void saveSerial(String filename) {
		try {
			FileOutputStream f = new FileOutputStream(new File(filename));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			o.writeObject(sites);
			
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void loadSerial(String filename) {
		try {
			FileInputStream f = new FileInputStream(new File(filename));
			ObjectInputStream o = new ObjectInputStream(f);
			
			sites = (ArrayList<Site>) o.readObject();
			
			refresh();
			
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void saveText() {
		
	}
	
	public void loadText() {
		
	}
	
	private void refresh() {
		fireTableRowsInserted(0, getRowCount() - 1);
	}
}
