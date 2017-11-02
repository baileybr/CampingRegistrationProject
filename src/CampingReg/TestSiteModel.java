package CampingReg;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;


/**********************************************************************
 * JUnit Testing for SiteModel.java
 * 
 * @author Brendan Bailey
 *
 *********************************************************************/
public class TestSiteModel {

	@Test
	public void testRowCount() {
		SiteModel s = new SiteModel();
		RV r = new RV();
		s.add(r);
		assertTrue(s.getRowCount() == 1);
	}
	
	@Test
	public void testRowCount2() {
		SiteModel s = new SiteModel();
		Tent t = new Tent();
		s.add(t);
		assertTrue(s.getRowCount() == 1);
	}
	
	@Test
	public void testRowCount3() {
		SiteModel s = new SiteModel();
		RV r = new RV();
		Tent t = new Tent();
		s.add(r);
		s.add(t);
		assertTrue(s.getRowCount() == 2);
	}
	
	@Test
	public void testRowCount4() {
		SiteModel s = new SiteModel();
		RV r = new RV();
		RV r2 = new RV();
		s.add(r);
		s.add(r2);
		assertTrue(s.getRowCount() == 2);
	}
	
	@Test
	public void testRowCount5() {
		SiteModel s = new SiteModel();
		Tent t = new Tent();
		Tent t2 = new Tent();
		s.add(t);
		s.add(t2);
		assertTrue(s.getRowCount() == 2);
	}
	
	@Test
	public void testRowCount6() {
		SiteModel s = new SiteModel();
		assertTrue(s.getRowCount() == 0);
	}
	
	@Test
	public void testColumnCount() {
		SiteModel s = new SiteModel();
		RV r = new RV();
		s.add(r);
		assertTrue(s.getColumnCount() == 5);
	}
	
	@Test
	public void testColumnCount2() {
		SiteModel s = new SiteModel();
		Tent t = new Tent();
		s.add(t);
		assertTrue(s.getColumnCount() == 5);
	}
	
	@Test
	public void testColumnCount3() {
		SiteModel s = new SiteModel();
		Tent t = new Tent();
		RV r = new RV();
		s.add(t);
		s.add(r);
		assertTrue(s.getColumnCount() == 5);
	}
	
	@Test
	public void testColumnName() {
		SiteModel s = new SiteModel();
		assertTrue(s.getColumnName(0) == "Name Reserving");
	}
	
	@Test
	public void testColumnName2() {
		SiteModel s = new SiteModel();
		assertTrue(s.getColumnName(1) == "Checked in");
	}
	
	@Test
	public void testColumnName3() {
		SiteModel s = new SiteModel();
		assertTrue(s.getColumnName(2) == "Days Staying");
	}
	
	@Test
	public void testColumnName4() {
		SiteModel s = new SiteModel();
		assertTrue(s.getColumnName(3) == "Site #");
	}
	
	@Test
	public void testColumnName5() {
		SiteModel s = new SiteModel();
		assertTrue(s.getColumnName(4) == "Tent/RV info");
	}
	
	@Test
	public void testRemove() {
		SiteModel s = new SiteModel();
		RV r = new RV();
		s.add(r);
		assertTrue(s.getRowCount() == 1);
		s.remove(0);
		assertTrue(s.getRowCount() == 0);
	}
	
	@Test
	public void testValueAt() throws Exception {
		SiteModel s = new SiteModel();
		Tent t = new Tent();
		GregorianCalendar g = new GregorianCalendar(2017, 10, 31);
		t.setNameReserving("John Doe");
		t.setCheckIn(g);
		t.setDaysStaying(1);
		t.setSiteNumber(1);
		t.setNumOfTenters(1);
		s.add(t);
		assertTrue(s.getValueAt(0, 0) == "John Doe");
//		assertTrue(s.getValueAt(0, 1) == "10/31/2017");
//		assertTrue(s.getValueAt(0, 2) == "1");
//		assertTrue(s.getValueAt(0, 3) == "1");
//		assertTrue(s.getValueAt(0, 4) == "1 tenter");
	}
	
	@Test
	public void testCurrentSites() {
		SiteModel s = new SiteModel();
		ArrayList<Site> sites = new ArrayList<Site>();
		Tent t = new Tent();
		sites.add(t);
		s.add(t);
		assertTrue(s.getCurrentSites().equals(sites));
	}
	
	@Test
	public void testCurrentSites2() {
		SiteModel s = new SiteModel();
		ArrayList<Site> sites = new ArrayList<Site>();
		Tent t = new Tent();
		RV r = new RV();
		sites.add(t);
		s.add(r);
		assertFalse(s.getCurrentSites().equals(sites));
	}
	
	@Test
	public void testCurrentSites3() {
		SiteModel s = new SiteModel();
		ArrayList<Site> sites = new ArrayList<Site>();
		Tent t = new Tent();
		Tent t2 = new Tent();
		RV r = new RV();
		RV r2 = new RV();
		sites.add(t);
		sites.add(r2);
		s.add(t);
		s.add(r2);
		assertTrue(s.getCurrentSites().equals(sites));
	}
	
	@Test
	public void testCurrentSites4() {
		SiteModel s = new SiteModel();
		ArrayList<Site> sites = new ArrayList<Site>();
		Tent t = new Tent();
		Tent t2 = new Tent();
		RV r = new RV();
		RV r2 = new RV();
		sites.add(r);
		sites.add(t2);
		s.add(t);
		s.add(r2);
		assertFalse(s.getCurrentSites().equals(sites));
	}
	
	@Test
	public void testCurrentSites5() {
		SiteModel s = new SiteModel();
		ArrayList<Site> sites = new ArrayList<Site>();
		Tent t = new Tent();
		Tent t2 = new Tent();
		RV r = new RV();
		RV r2 = new RV();
		sites.add(r);
		sites.add(t2);
		s.add(t2);
		s.add(r);
		assertFalse(s.getCurrentSites().equals(sites));
	}
	
	@Test
	public void testLoadandSaveSerial() {
		SiteModel s = new SiteModel();
		ArrayList<Site> sites = new ArrayList<Site>();
		RV r = new RV();
		sites.add(r);
		s.add(r);
		assertTrue(s.getCurrentSites().equals(sites));
		s.saveSerial("serialFile");
		s.loadSerial("serialFile");
		assertTrue(s.getCurrentSites().equals(sites));
	}
	
	@Test
	public void testLoadandSaveText() {
		SiteModel s = new SiteModel();
		ArrayList<Site> sites = new ArrayList<Site>();
		RV r = new RV();
		sites.add(r);
		s.add(r);
		assertTrue(s.getCurrentSites().equals(sites));
		s.saveText("textFile");
		s.loadText("textFile");
		assertTrue(s.getCurrentSites().equals(sites));
	}
	
}
