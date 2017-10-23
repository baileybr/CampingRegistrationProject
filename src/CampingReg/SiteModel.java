package CampingReg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.table.AbstractTableModel;

public class SiteModel extends AbstractTableModel {
	
	private ArrayList<Site> sites;
	
	private String[] columnNames;
	
	public SiteModel() {
		sites = new ArrayList<Site>();
		columnNames = new String[] { "Name Reserving", "Checked in", "Days Staying", "Site #", "Tent/RV info" };
	}
	
	public int getRowCount() {
		return sites.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				return sites.get(rowIndex).getNameReserving();
			case 1:
				Date temp = sites.get(rowIndex).getCheckIn().getTime();
				
				return new SimpleDateFormat("MM/dd/YYY").format(temp);
			case 2:
				return sites.get(rowIndex).getDaysStaying();
			case 3:
				return sites.get(rowIndex).getSiteNumber();
			case 4:
				if (sites.get(rowIndex) instanceof Tent) {
					String text = ((Tent)sites.get(rowIndex)).getNumOfTenters() != 1 ? " tenters" : " tenter";
					
					return ((Tent)sites.get(rowIndex)).getNumOfTenters() + text;
				}
				else if (sites.get(rowIndex) instanceof RV) {
					return ((RV)sites.get(rowIndex)).getPower() + " amps";
				}
				break;
		}
		
		return null;
	}
	
	public void add(Site site) {
		sites.add(site);
		
		fireTableRowsInserted(0, getRowCount() - 1);
	}
}
