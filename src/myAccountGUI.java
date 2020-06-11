import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


//o xristis mporei na kanei apo edo allages sta stoixeia tou prosopikou toy logariasmoy stin platforma 

public class myAccountGUI extends JFrame {
	private Traveller currentTraveller;
	private ArrayList<Traveller> travellers; 
	
	private JPanel panel = new JPanel();
	private JTextField newUsername = new JTextField(10);
	private JTextField newPassword = new JTextField(10);
	private JTextField newName = new JTextField(10);
	private JButton makeChanges = new JButton("Save");
	private JButton goBack = new JButton("Back");
	private JLabel username = new JLabel("Username");
	private JLabel password = new JLabel("Password");
	private JLabel name = new JLabel("Name");
	private JLabel makeChangesLabel = new JLabel("Make any change");
	private final JLabel privacyLabel = new JLabel("We value your");
	private final JLabel lblPrivacyAnd = new JLabel("privacy and we");
	private final JLabel lblAreTryingTo = new JLabel("are trying to do");
	private final JLabel lblOurBestTo = new JLabel("our best to keep");
	private final JLabel lblYouSafe = new JLabel("you safe");
	
	public myAccountGUI( Traveller currentTraveller)
	{
		
		try {
			FileInputStream fileIn = new FileInputStream("Travellers.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			travellers = (ArrayList<Traveller>) in.readObject();
			in.close();
			fileIn.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		travellers.remove(currentTraveller);
		
		this.setTitle("Tour-X My Account");
		this.currentTraveller = currentTraveller;
		panel.setBackground(Color.WHITE);
		this.setContentPane(panel);
		this.setSize(357, 250);
		this.setVisible(true);
		panel.setLayout(null);
		makeChangesLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		makeChangesLabel.setForeground(new Color(204, 0, 51));
		makeChangesLabel.setBounds(31, 21, 157, 20);
		getContentPane().add(makeChangesLabel);
		username.setFont(new Font("Tahoma", Font.PLAIN, 12));
		username.setBounds(31, 59, 64, 14);
		getContentPane().add(username);
		newUsername.setBackground(Color.WHITE);
		newUsername.setBounds(102, 57, 86, 20);
		getContentPane().add(newUsername);
		password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		password.setBounds(31, 90, 64, 14);
		getContentPane().add(password);
		newPassword.setBounds(102, 88, 86, 20);
		getContentPane().add(newPassword);
		name.setFont(new Font("Tahoma", Font.PLAIN, 12));
		name.setBounds(31, 121, 64, 14);
		getContentPane().add(name);
		newName.setBounds(102, 119, 86, 20);
		getContentPane().add(newName);
		makeChanges.setBounds(112, 161, 76, 23);
		getContentPane().add(makeChanges);
		goBack.setBounds(31, 161, 71, 23);
		getContentPane().add(goBack);
		privacyLabel.setForeground(new Color(0, 153, 153));
		privacyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		privacyLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		privacyLabel.setBounds(202, 59, 126, 14);
		
		panel.add(privacyLabel);
		lblPrivacyAnd.setForeground(new Color(0, 153, 153));
		lblPrivacyAnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrivacyAnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrivacyAnd.setBounds(202, 76, 126, 14);
		
		panel.add(lblPrivacyAnd);
		lblAreTryingTo.setForeground(new Color(0, 153, 153));
		lblAreTryingTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreTryingTo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAreTryingTo.setBounds(202, 93, 126, 14);
		
		panel.add(lblAreTryingTo);
		lblOurBestTo.setForeground(new Color(0, 153, 153));
		lblOurBestTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblOurBestTo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOurBestTo.setBounds(202, 111, 124, 14);
		
		panel.add(lblOurBestTo);
		lblYouSafe.setForeground(new Color(0, 153, 153));
		lblYouSafe.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouSafe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYouSafe.setBounds(202, 127, 126, 14);
		
		panel.add(lblYouSafe);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		ButtonListener listener = new ButtonListener();
		makeChanges.addActionListener(listener);
		goBack.addActionListener(listener);
	}
	
	class ButtonListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(makeChanges))
				{
					if(!newUsername.getText().equals("New Username"))
					{
						currentTraveller.setUsername(newUsername.getText());
					}
					if(!newPassword.getText().equals("New Password"))
					{
						currentTraveller.setPassword(newPassword.getText());
					}
					if(!newName.getText().equals("New Name"))
					{
						currentTraveller.setName(newName.getText());
					}
					
					travellers.add(currentTraveller);
					
					try {
						FileOutputStream fileOutTravellers = new FileOutputStream("Travellers.ser");
						ObjectOutputStream out = new ObjectOutputStream(fileOutTravellers);
						out.writeObject(travellers);
						out.close();
						fileOutTravellers.close();
					}
					catch(IOException i) {
						i.printStackTrace();
					}
					
					JFrame prompt = new JFrame();
					JOptionPane.showMessageDialog( prompt,"Changes accepted");
					
					prompt.dispose();
				}
				else
				{
					dispose();
					HomepageGUI homePage = new HomepageGUI(currentTraveller);
				}
				
			}
			
		}
}
	
