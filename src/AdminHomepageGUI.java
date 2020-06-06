import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AdminHomepageGUI extends JFrame {

	private JPanel panel = new JPanel();	
	
	private JButton hotels = new JButton("Hotels");
	private JButton tickets = new JButton("Tickets");
	private JButton destinations = new JButton("Destinations");
	private JButton groups = new JButton("Groups");
	private JButton rewards = new JButton("Rewards");
	private JButton logOut = new JButton("Log Out");
	private final JLabel administartionLabel = new JLabel("Tour-X Administration Panel");
	private final JLabel welcomeLabel = new JLabel("Welcome");
	private final JLabel lblNewLabel = new JLabel("A place to manage your tourist agency ");
	
	
	public AdminHomepageGUI() {
		panel.setBackground(new Color(0, 139, 139));
		
				
		this.setContentPane(panel);
		panel.setLayout(null);
		hotels.setBounds(33, 186, 83, 23);
		
		getContentPane().add(hotels);
		tickets.setBounds(126, 186, 97, 23);
		getContentPane().add(tickets);
		destinations.setBounds(233, 186, 115, 23);
		getContentPane().add(destinations);
		groups.setBounds(233, 220, 115, 23);
		getContentPane().add(groups);
		rewards.setBounds(126, 221, 97, 23);
		getContentPane().add(rewards);
		logOut.setBounds(33, 220, 83, 23);
		getContentPane().add(logOut);
		administartionLabel.setForeground(Color.WHITE);
		administartionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		administartionLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		administartionLabel.setBounds(47, 46, 283, 14);
		
		panel.add(administartionLabel);
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(47, 87, 283, 14);
		
		panel.add(welcomeLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(47, 124, 283, 23);
		
		panel.add(lblNewLabel);
		
		this.setResizable(false);
		this.setSize(400,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Tour-X Administrator Homepage");
		this.setLocationRelativeTo(null);
		
		
		ButtonListener listener = new ButtonListener();
		
		logOut.addActionListener(listener);
		hotels.addActionListener(listener);
		tickets.addActionListener(listener);
		destinations.addActionListener(listener);
		groups.addActionListener(listener);
		rewards.addActionListener(listener);
	}
			
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(logOut)) {
				System.exit(0);;
			}
			else if(e.getSource().equals(hotels)) {
				hide();
				HotelAdminGUI HotelsGUI = new HotelAdminGUI();
			}
			
			else if (e.getSource().equals(tickets))
			{
				hide();
				TicketsAdminGUI TicketsGUI = new TicketsAdminGUI();
			}
			
			else if ( e.getSource().equals(destinations))
			{
				hide();
				DestAdminGUI DestGUI = new DestAdminGUI();
			}
			
			else if (e.getSource().equals(groups))
			{
				hide();
				GroupAdminGUI GroupGUI = new GroupAdminGUI();
			}
			
			else if(e.getSource().equals(rewards))
			{
				hide();
				RewardAdminGUI RewardGUI = new RewardAdminGUI();
				
			}
			
			
			
		}
		
	}
}
