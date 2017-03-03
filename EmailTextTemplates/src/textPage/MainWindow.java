package textPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import textPage.FileImporter;

public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JTextField userInput = new JTextField();
	JTextArea emailOut = new JTextArea();
	JButton process = new JButton();
	JLabel directions = new JLabel(
			"Enter names into text box, select type of request and click button to generate email.");
	Font font = new Font("SERIF", Font.PLAIN, 13);
	JRadioButton[] radioB = new JRadioButton[12];
	String[] buttonString = { "cabinet keys are ready", "not signed by authoized signer", "request is not for a door",
			"key(s) ready to be picked up", "request self signed", "key was broken or cut wrong",
			"request is missing building and door", "two keys requested on one form", "campus key was requested",
			"out of date form used", "missing lost key report number", "lost badge returned" };
	String[] buttonCommand = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" };
	ButtonGroup radioGroup = new ButtonGroup();
	String header = "Hello, \n\nKeys requested for: ";
	// Setting buttons for the email signature
	JRadioButton[] eSig = new JRadioButton[3];
	String[] eSigName = {"Jeff", "Scott", "Hamzah"};
	ButtonGroup eSigGroup = new ButtonGroup();
		
	public MainWindow() {	// Create all panels and buttons
		super();
		this.setSize(800, 1000);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//add(userInput, BorderLayout.NORTH);
		UIManager.put("TitledBorder.border", new LineBorder(new Color(00,00,00), 2));
		TitledBorder title;		
		
		
		JPanel midPanel = new JPanel();
		JPanel ButtonPannel = new JPanel();
		JPanel SigPannel = new JPanel();
		JPanel insidePannel = new JPanel();
		SigPannel.setLayout(new GridLayout(1, 3));
		insidePannel.setLayout(new BorderLayout());
		midPanel.setLayout(new BorderLayout());
		ButtonPannel.setLayout(new GridLayout(5, 3));
		
		title = BorderFactory.createTitledBorder("Select Sig");
		title.setTitleColor(Color.BLACK);
		SigPannel.setBorder(title);
		
		title = BorderFactory.createTitledBorder("Select an Email Type");
		insidePannel.setBorder(title);
		
		title = BorderFactory.createTitledBorder("Enter Names in Text Field");
		midPanel.setBorder(title);
		
		process.setText("Hit 'Enter' or Click Here to Generate Email");
		process.setFont(font);
		process.addActionListener(this);
		midPanel.add(process, BorderLayout.SOUTH);
		midPanel.add(userInput, BorderLayout.NORTH);
		midPanel.add(insidePannel, BorderLayout.CENTER);
		emailOut.setLineWrap(true);
		emailOut.setPreferredSize(new Dimension(200, 500));

		for (int i = 0; i < 3; i++){
			eSig[i] = new JRadioButton(eSigName[i]);
			eSig[i].setActionCommand(buttonCommand[i]);
			eSigGroup.add(eSig[i]);
			SigPannel.add(eSig[i]);
		}
		insidePannel.add(SigPannel, BorderLayout.SOUTH);
		
		for (int i = 0; i < 12; i++) {
			radioB[i] = new JRadioButton(buttonString[i]);
			radioB[i].setActionCommand(buttonCommand[i]);
			radioGroup.add(radioB[i]);
			ButtonPannel.add(radioB[i]);
		}
		radioB[0].setSelected(true);
		insidePannel.add(ButtonPannel, BorderLayout.NORTH);

		add(midPanel, BorderLayout.CENTER);
		// Taken off the text area at bottom of window, not needed after auto email added
		//add(emailOut, BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(process); // Make the 'Enter' button on keyboard process the 'process' button.
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		StringBuilder s = new StringBuilder(userInput.getText() + "\n");
		String emailBody = radioGroup.getSelection().getActionCommand();
		String emailSig = null;
		String selection = "";
		String finalSig = "";
		// Setting signature based on selection, if nothing give a warning
		try {
			emailSig = eSigGroup.getSelection().getActionCommand();
			switch (emailSig){
			case "0":
				finalSig = "Jeff.txt";
				break;
			case "1":
				finalSig = "Scott.txt";
				break;
			case "2":
				finalSig = "Hamzah.txt";
				break;
			default :
				JOptionPane.showMessageDialog(null, "A email signature needs to be selected");
			}
		} catch (NullPointerException e){
			JOptionPane.showMessageDialog(null, "A email signature needs to be selected", "Error Message", JOptionPane.INFORMATION_MESSAGE);
		}
		// Setting signature based on selection
//		if (emailSig != null){
//			switch (emailSig){
//			case "0":
//				finalSig = "Jeff.txt";
//				break;
//			case "1":
//				finalSig = "Scott.txt";
//				break;
//			case "2":
//				finalSig = "Hamzah.txt";
//				break;
//			default :
//				JOptionPane.showMessageDialog(null, "A email signature needs to be selected");
//			}
//		} else if (emailSig.equals(null)){
//			JOptionPane.showMessageDialog(null, "A email signature needs to be selected");
//		}
		// Selecting the email context based on radiobutton selection
		switch (emailBody) {
		case "0":
			selection = "cabKeyRed.txt";
			s.setLength(0);
			break;
		case "1":
			selection = "notAuthSigner.txt";
			s.setLength(0);
			break;
		case "2":
			selection = "notDoorKey.txt";
			s.setLength(0);
			break;
		case "3":
			selection = "keyReady.txt";
			s.insert(0, header);
			break;
		case "4":
			selection = "selfSignedForm.txt";
			s.setLength(0);
			break;
		case "5":
			selection = "fixedKey.txt";
			s.setLength(0);
			break;
		case "6":
			selection = "missingBuilding.txt";
			s.insert(0, header);
			break;
		case "7":
			selection = "twoKeysOneForm.txt";
			s.insert(0, header);
			break;
		case "8":
			selection = "campusKey.txt";
			s.setLength(0);
			break;
		case "9":
			selection = "oldForm.txt";
			s.setLength(0);
			break;
		case "10":
			selection = "reportNumber.txt";
			s.insert(0, header);
			break;
		case "11":
			selection = "lostBadgeReturned.txt";
			s.setLength(0);
			break;
		}
		// Importing the email body
		FileImporter fileIn = new FileImporter(selection);
		ArrayList<String> fileArr = new ArrayList<>();
		try {
			fileArr = fileIn.getFile();
			emailOut.setText(s + "");
			for (int i = 0; i < fileArr.size(); i++) {
				emailOut.append(fileArr.get(i).toString() + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Importing the signature footer
		FileImporter sigIn = new FileImporter(finalSig);
		ArrayList<String> sigArr = new ArrayList<>();
		try {
			sigArr = sigIn.getFile();
			for (int i = 0; i < sigArr.size(); i++) {
				emailOut.append(sigArr.get(i).toString() + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		
		// Starting up outlook email string
		StringBuilder outlook = new StringBuilder();
		
		// Placing names of employees at top of email type of request is required
		switch (emailBody) {
		case "3":
		case "5":
		case "6":
		case "7":
		case "10":
			outlook.append(s);
			break;
		}
		// Appending email body to outlook
		for (int i = 0; i < fileArr.size(); i++) {
			outlook.append(fileArr.get(i));
			outlook.append("%0D%0A");
		}
		// Appending signature footer to outlook
		for (int i = 0; i < sigArr.size(); i++) {
			outlook.append(sigArr.get(i));
			outlook.append("%0D%0A");
		}		
		// Setting email to string
		outlook.toString().replaceAll(" ", "%20").replaceAll("\n", "%0D%0A");
		// Checking that user computer will allow mailto and will send message to computers default email
		Desktop desktop;
		if (Desktop.isDesktopSupported() && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL) && emailSig != null) {

			URI mailto = null;
			try {
				mailto = new URI("mailto:?subject=Key%20Request&body="
						+ outlook.toString().replaceAll(" ", "%20").replaceAll("\n", "%0D%0A"));
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}

			try {
				desktop.mail(mailto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new RuntimeException("desktop doesn't support mailto or no emailSig selected; mail is dead");
		}

	}
}
