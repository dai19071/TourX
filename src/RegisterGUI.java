import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Η κλάση αυτή αντιπροσωπεύει το παράθυρο από το οποίο ο χρήστης θα μπορεί να δημιουργήσει νέο λογαριασμό
public class RegisterGUI extends JFrame{

	private JTextField name = new JTextField(10);
	private JTextField username = new JTextField(10);
	private JTextField password = new JTextField(10);
	private JPanel panel = new JPanel();
	private ArrayList <Traveller> users= new ArrayList<>();
	private JButton registerButton = new JButton("Register");
	
	private JLabel usernameLabel = new JLabel("Username");
	private JLabel passwordLabel = new JLabel("Password");
	private JLabel nameLabel = new JLabel("Name");
	
	
	public RegisterGUI(ArrayList <Traveller> users) {
		this.setContentPane(panel);
		this.setSize(200, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Tour-X Register");
		this.setLocation(300, 300);
		this.setResizable(false);
		this.users = users;
		this.setLocationRelativeTo(null);
		
		panel.add(nameLabel);
		panel.add(name);
		panel.add(usernameLabel);
		panel.add(username);
		panel.add(passwordLabel);
		panel.add(password);
		panel.add(registerButton);
		
		ButtonListener listener = new ButtonListener();
		
		registerButton.addActionListener(listener);
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Αν ο χρήστης ακολουθήσει επιτυχώς τη διαδικασία τότε θα βγάλει ένα μήνυμα καλωσορίσματος και θα πάει στο LoginGUI
			if (!UserExists(username.getText()))
			{
				Traveller newUser= new Traveller(username.getText(),password.getText(),name.getText());
				users.add(newUser);
				dispose();
				JFrame Success = new JFrame();
				JOptionPane.showMessageDialog(Success ,"Welcome");
				try {
					FileOutputStream fileOutTravellers = new FileOutputStream("Travellers.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOutTravellers);
					out.writeObject(users);
					out.close();
					fileOutTravellers.close();
				}
				catch(IOException i) {
					i.printStackTrace();
				}
				finally {
					System.out.println("Serialization Attempted Travellers...");
				}

				new LoginGUI();
			}
			//Αν όχι θα πρέπει να διαλέξει κάποιο άλλο username
			else 
			{
				dispose();
				JFrame Fail = new JFrame();
				JOptionPane.showMessageDialog(Fail ,"Try with a different username");
				new RegisterGUI(users);
			}
			}

}
	
	public boolean UserExists(String username)
	{
		for (User u : users)
			if (username.equals(u.getUsername()))
				return true;
		return false;
	}
	
}
