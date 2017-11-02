package CampingReg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
	
	private Key keyPressedListener;
	
	private ArrayList<String> pastEdits;
	
	/*******************************************************************
	 * Initialize all class level variables
	 ******************************************************************/
	public GUICampingReg() {
		keyPressedListener = new Key();
		pastEdits = new ArrayList<String>();
		
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
		table.addKeyListener(keyPressedListener);
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
					tent, sModel);
			if (dialog.getCloseStatus() == true && dialog.getTent() != null) {
				sModel.add(dialog.getTent());
				pastEdits.add("D," + (sModel.getRowCount() - 1));
			}
		}
		else if (e.getSource() == checkInRv) {
			RV rv = new RV();
			DialogCheckInRv dialog = new DialogCheckInRv(this,
					rv, sModel);

			if (dialog.getCloseStatus() == true && dialog.getRV() != null) {
				sModel.add(dialog.getRV());
				pastEdits.add("D," + (sModel.getRowCount() - 1));
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
	
	private void undo() {
		if (pastEdits.size() > 0) {
			String execute = pastEdits.get(pastEdits.size() - 1);
			String[] items = execute.split(",");
			
			switch (items[0]) {
				case "D":
					sModel.remove(Integer.parseInt(items[1]));
					pastEdits.remove(pastEdits.size() - 1);
					
					break;
				case "I":
					String input = "";
					
					for (int i = 1; i < items.length; i++) {
						input += (items[i] + ",");
					}
					
					try {
						Site retVal = sModel.stringToSite(input);
						sModel.add(retVal);
						pastEdits.remove(pastEdits.size() - 1);
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, 
								ex.getMessage());
					}
					
					break;
			}
		}
	}

	/*******************************************************************
	 * Key listener class used for catching keypresses on the table
	 * 
	 * @author Ben Buurstra
	 ******************************************************************/
	private class Key implements KeyListener {
		public void keyPressed(KeyEvent e) {
			// 8 is the backspace key
			if (e.getKeyCode() == 8) {
				int row = table.getSelectedRow();
				if (row != -1) {
					int result = JOptionPane.showConfirmDialog(null, 
							"Are you sure you want to "
							+ "delete this entry?");
					
					// 0 means yes
					if (result == 0) {
						pastEdits.add("I," + sModel.getAtIndex(row));
						sModel.remove(row);
					}
				}
			}
			
			if (e.isMetaDown()) {
				// 90 means Z
				if (e.getKeyCode() == 90) {
					undo();
				}
			}
		}
		
		public void keyTyped(KeyEvent e) {
			// Unused, but is abstract
		}


		public void keyReleased(KeyEvent e) {
			// Unused, but is abstract
		}
		
	}
}
