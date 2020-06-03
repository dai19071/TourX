import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/* Η κλάση αυτή αντιπροσωπεύει το παράθυρο από το οποίο ο χρήστης θα συ7νδέεται στην πλατφόρμα
 * καθώς και θα μπορεί να κάνει Register σε περίπτωση που δεν έχει λογαριασμό
 */

public class LoginGUI extends JFrame{

private JPanel panel = new JPanel();
	
	private JButton loginButton = new JButton("Login");
	private JButton registerButton = new JButton("Register");
	private JTextField username = new JTextField(10);
	private JPasswordField password = new JPasswordField(10);
	private JLabel usernameLabel = new JLabel("Username");
	private JLabel passwordLabel = new JLabel("Password");
	private ArrayList<Traveller> Travellers = new ArrayList<>();
	
	public LoginGUI() {	
		try {
			FileInputStream fileIn = new FileInputStream("Travellers.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Travellers = (ArrayList<Traveller>) in.readObject();
			in.close();
			fileIn.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		panel.setBackground(Color.WHITE);
		
		this.setContentPane(panel);
		this.setSize(292, 250);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Tour-X Login");
		this.setLocation(300, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		panel.setLayout(null);
		
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		usernameLabel.setForeground(new Color(204, 0, 51));
		usernameLabel.setBounds(73, 52, 69, 14);
		
		
		panel.add(usernameLabel);
		username.setBounds(141, 49, 86, 20);
		panel.add(username);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel.setForeground(new Color(0, 153, 204));
		passwordLabel.setBounds(73, 83, 69, 14);
		panel.add(passwordLabel);
		password.setBounds(141, 80, 86, 20);
		panel.add(password);
		registerButton.setBounds(35, 127, 100, 23);
		
		panel.add(registerButton);
		loginButton.setBounds(148, 127, 100, 23);
		panel.add(loginButton);
		
		JLabel lblNewLabel = new JLabel("Tour-X");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(108, 11, 69, 14);
		panel.add(lblNewLabel);
		
		
		ButtonListener listener = new ButtonListener();
		
		loginButton.addActionListener(listener);
		registerButton.addActionListener(listener);
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(loginButton)) {
				Traveller found = null;
				//Έλεγχος στοιχείων του χρήστη για το Login
				for(Traveller s: Travellers)
				{
					
					if(s.getUsername().equals(username.getText()))
					{
						found = s;
					}
				}
				if(found!=null)
				{
						if(found.getPassword().equals(password.getText()))
						{
							HomepageGUI homepage = new HomepageGUI(found);
							dispose();
						}
						//Αν πατήσει λάθος τον κωδικό του για τρεις φορές τότε εμφανίζει ένα παράθυρο για να ξαναπληκτρολογήσει τον κωδικό
						//Αν το κάνει πάλι λάθος θα βγάλει μήνυμα σφάλματος και ο λογαριασμός θα κλειδωθεί
						else
						{
							JFrame wrongPassword = new JFrame();
							JOptionPane.showMessageDialog(wrongPassword, "Incorrect Password");
							int i;
							boolean userlogged = false;
							for(i=1; i<=2; i++)
							{
								String retry =  JOptionPane.showInputDialog("Please try again");
								if(retry.equals(found.getPassword()))
								{
									dispose();
									HomepageGUI homepage = new HomepageGUI(found);
									userlogged = true;
									break;
								}
							}
							if(!userlogged)
							{
								JFrame accountClosed = new JFrame();
								JOptionPane.showMessageDialog(accountClosed, "Your account was closed,because you inserted a wrong password many times");
								System.exit(0);						
							}
						}
					}
					else
					{
						JFrame notExist = new JFrame();
						JOptionPane.showMessageDialog(notExist, "This user doesnt exists");
					}	
				
				}
			else
			{
				dispose();
				new RegisterGUI(Travellers);
			}
				
		}
	}
	
	public ArrayList<Traveller> getTravellers()
	{
		return Travellers;
	}
}
