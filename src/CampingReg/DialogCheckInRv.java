package CampingReg;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;

public class DialogCheckInRv extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField nameTxt, occupyedOnTxt, stayingTxt, 
	siteNumberTxt;
	
	private JComboBox powerBox;
	private JButton okButton, cancelButton;
	private boolean closeStatus;
	private Site unit;  
	
	private JLabel nameLabel, occupyingLabel, stayingLabel, siteLabel,
	powerLabel;
	
	private GregorianCalendar gCalenderCheckIn, gCalenderCheckOut; 
	
	private JDialog dialog;
	private JFrame parentFrame;
	private JPanel panel;
	
	private int month, day, year, monthOut, dayOut, yearOut;
	private String[] ampStrings;


	public DialogCheckInRv(JFrame paOccupy, Site d) {	
		unit = d; 
		
		gCalenderCheckIn = new GregorianCalendar();
		gCalenderCheckIn = d.getCheckIn();
		month = gCalenderCheckIn.get(GregorianCalendar.MONTH);
		day = gCalenderCheckIn.get(GregorianCalendar.DAY_OF_MONTH);
		year = gCalenderCheckIn.get(GregorianCalendar.YEAR);
		
//		gCalenderCheckOut = new GregorianCalendar();
//		gCalenderCheckOut.add(GregorianCalendar.DAY_OF_MONTH, d.getDaysStaying());
//		monthOut = gCalenderCheckOut.get(GregorianCalendar.MONTH);
//		dayOut = gCalenderCheckOut.get(GregorianCalendar.DAY_OF_MONTH);
//		yearOut = gCalenderCheckOut.get(GregorianCalendar.YEAR);
		
		dialog = new JDialog();
		nameTxt = new JTextField();
		occupyedOnTxt = new JTextField(month + "/" + day + "/" + year);
		stayingTxt = new JTextField(d.getDaysStaying());
		siteNumberTxt = new JTextField(d.getSiteNumber());
		powerBox = new JComboBox<String>();
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		nameLabel = new JLabel("Name of Reserver:");
		occupyingLabel = new JLabel("Occupied On Date:");
		stayingLabel = new JLabel("Days Staying:");
		siteLabel = new JLabel("Requested Site Number:");
		powerLabel = new JLabel("Power in AMPS:");
		parentFrame = paOccupy;
		closeStatus = false;
		
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		powerBox.addItem("30");
		powerBox.addItem("40");
		powerBox.addItem("50");
		
		panel = new JPanel();
		panel.add(nameLabel);
		panel.add(nameTxt);
		panel.add(siteLabel);
		panel.add(siteNumberTxt);
		panel.add(occupyingLabel);
		panel.add(occupyedOnTxt);
		panel.add(stayingLabel);
		panel.add(stayingTxt);
		panel.add(powerLabel);
		panel.add(powerBox);
		panel.add(Box.createVerticalStrut(5));
		panel.add(okButton);
		panel.add(cancelButton);
		
		panel.setLayout(new GridLayout(7, 2));
		dialog.add(panel);
		
		dialog.setLocationRelativeTo(parentFrame);
		dialog.setModal(true);
		dialog.setTitle("Reserve An RV Site");
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setSize(350, 250);
		dialog.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			
		}
		else if (e.getSource() == cancelButton) {
			dialog.dispose();
		}
		
	}

}
