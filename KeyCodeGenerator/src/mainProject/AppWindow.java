package mainProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class AppWindow extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	JTextPane codeOut = new JTextPane();
	JButton generate = new JButton();
	JLabel direction = new JLabel();
	JPanel mainPanel = new JPanel();
	Font font = new Font("SERIF", Font.PLAIN, 25);
	
	public AppWindow() {
		super();
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		UIManager.put("TitledBorder.border", new LineBorder(new Color(00,00,00), 2));
		TitledBorder title;
		this.setTitle("Timmy's CodeLock Generator");

		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(300, 100));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		StyledDocument doc = codeOut.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		this.pack();
		
		title = BorderFactory.createTitledBorder("Welcome to JPapp");
		title.setTitleColor(Color.BLACK);
		mainPanel.setBorder(title);
		codeOut.setEditable(false);		
		
		generate.setText("Generate New Code");
		generate.setFont(font);
		generate.addActionListener(this);
		mainPanel.add(generate, BorderLayout.CENTER);
		codeOut.setFont(font);
		mainPanel.add(codeOut, BorderLayout.SOUTH);
		
		this.getRootPane().setDefaultButton(generate);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Testing storage file checker
		StorageFile sf = new StorageFile();
		String out;
		try {
			out = sf.StoreNumbers();
			codeOut.setText(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// End storage file checker
	}
	
}
