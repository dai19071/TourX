import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

//H leitourgia tou sugkekrimenou parathuro einai h epilogh tou xrhsth,dld an einai traveller h admin etsi wste na dieukolinetai h eisodos
//epipleon dieukolunei sthn anaptujei tou programmatos gt etsi mporoume na parexoume eukolotera ta parathura gia tous 2 parapanw xrhstes

public class UserLogged extends JFrame{
	
	private JPanel panel = new JPanel();
	private JButton Admin = new JButton("Admin");
	private JButton Traveller = new JButton("Traveller");
	private final JLabel helloLabel = new JLabel("Hello there!");
	private final JLabel lblNewLabel = new JLabel("Log In as:");
	private final JLabel lblNewLabel_1 = new JLabel("Tour-X Developer Team 2020");
	//private ArrayList<Traveller> Travellers;
	//private ArrayList<Admin> Admins;
	
	public UserLogged()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		panel.setBackground(Color.WHITE);
		
		
	
		this.setContentPane(panel);
		panel.setLayout(null);
		Admin.setBounds(66, 112, 146, 23);
		getContentPane().add(Admin);
		Traveller.setBounds(66, 78, 146, 23);
		getContentPane().add(Traveller);
		helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloLabel.setForeground(new Color(204, 0, 51));
		helloLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		helloLabel.setBounds(66, 11, 146, 31);
		
		panel.add(helloLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 153, 153));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(66, 44, 146, 23);
		
		panel.add(lblNewLabel);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(53, 230, 175, 14);
		
		panel.add(lblNewLabel_1);
		//this.Admins = Admins;
	//	this.Travellers = Travellers;
		
		this.setSize(286,300);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		ButtonListener listener = new ButtonListener();
		Admin.addActionListener(listener);
		Traveller.addActionListener(listener);
	}

	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(Traveller))
			{
				dispose();
				LoginGUI loginFrame = new LoginGUI();
			}
			else
			{
				dispose();
				AdminGUI loginAdminFrame = new AdminGUI();
				
			}
			
		}
		
	}
}
