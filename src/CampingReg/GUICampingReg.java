package CampingReg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/***********************************************************************
 * GUICampingReg is the main view of this application
 * 
 * @author Ben Buurstra
 ***********************************************************************/
public class GUICampingReg extends JFrame implements ActionListener {
	private JPanel mainPanel;
	
	private JMenuBar menu;
	private JMenu file;
	private JMenu checkIn;
	private JMenuItem saveSerial;
	private JMenuItem saveText;
	private JMenuItem loadSerial;
	private JMenuItem loadText;
	private JMenuItem checkInRv;
	private JMenuItem checkInTent;
	private JMenuItem exit;
	
	private JTable table;
	
	private SiteModel sModel;
	
	/*******************************************************************
	 * Initialize all class level variables
	 ******************************************************************/
	public GUICampingReg() {
		mainPanel = new JPanel(new BorderLayout());
		
		menu = new JMenuBar();
		file = new JMenu("File");
		checkIn = new JMenu("Checking In");
		saveSerial = new JMenuItem("Save Serial...");
		saveText = new JMenuItem("Save Text...");
		loadSerial = new JMenuItem("Open Serial...");
		loadText = new JMenuItem("Open Text...");
		checkInRv = new JMenuItem("Check-in RV Site");
		checkInTent = new JMenuItem("Check-in Tent Site");
		exit = new JMenuItem("Exit");
		
		sModel = new SiteModel();
		
		table = new JTable(sModel);
	}
	
	/*******************************************************************
	 * Setup the JFrame and all it's components
	 ******************************************************************/
	public void run() {
		setupMenu();
		setupActionListeners();
		
		mainPanel.add(menu, BorderLayout.NORTH);
		mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		this.add(mainPanel);
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Camping Registration");
		this.setVisible(true);
	}
	
	/*******************************************************************
	 * Handles any actions that are performed
	 * 
	 * @param e has information about what action was performed
	 ******************************************************************/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveSerial) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showSaveDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				sModel.saveSerial(filename);
			}
		}
		else if (e.getSource() == loadSerial) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				sModel.loadSerial(filename);
			}
		}
		else if (e.getSource() == saveText) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showSaveDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				sModel.saveText(filename);
			}
		}
		else if (e.getSource() == loadText) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				sModel.loadText(filename);
			}
		}
		else if (e.getSource() == exit) {
			System.exit(1);
		}
		else if (e.getSource() == checkInTent) {
			Tent tent = new Tent();
			DialogCheckInTent dialog = new DialogCheckInTent(this,
					tent);
			if (dialog.getCloseStatus() == true) {
				sModel.add(dialog.getTent());
			}
		}
		else if (e.getSource() == checkInRv) {
			RV rv = new RV();
			DialogCheckInRv dialog = new DialogCheckInRv(this,
					rv);

			if (dialog.getCloseStatus() == true) {
				sModel.add(dialog.getRV());
			}
		}
	}
	
	/*******************************************************************
	 * Helper method for setting up the JMenu
	 ******************************************************************/
	private void setupMenu() {
		file.add(saveSerial);
		file.add(loadSerial);
		file.addSeparator();
		file.add(saveText);
		file.add(loadText);
		file.addSeparator();
		file.add(exit);
		
		checkIn.add(checkInTent);
		checkIn.add(checkInRv);
		
		menu.add(file);
		menu.add(checkIn);
	}
	
	/*******************************************************************
	 * Helper method for adding all action listeners
	 ******************************************************************/
	private void setupActionListeners() {
		saveSerial.addActionListener(this);
		loadSerial.addActionListener(this);
		saveText.addActionListener(this);
		loadText.addActionListener(this);
		exit.addActionListener(this);
		checkInTent.addActionListener(this);
		checkInRv.addActionListener(this);
	}
}
