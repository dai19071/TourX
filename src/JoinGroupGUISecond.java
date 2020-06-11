import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

//sto parathyro auto o traveller vlepei tis leptomereies ton grouptrip poy toy tairiazoyn kai exei tin epilogi na dialexei kapoio apo ayta 
//oste na taxidepsei

public class JoinGroupGUISecond extends JFrame {

	private ArrayList<GroupTrip> groups;
	private ArrayList<GroupTrip> availableGroups;
	private Traveller curTraveller;
	private GroupTrip selgroup = new GroupTrip();
	private JPanel panel = new JPanel();
	private JButton joinButton = new JButton("Join this trip");
	private JButton backButton = new JButton("Back");
	private JLabel nameL ;
	private JLabel priceL ;
	private JLabel destL ;
	private JLabel hotelL;
	private JLabel ticketL ;
	private JLabel extra1L = new JLabel("Available trip activities" );
	private JTextArea extra2L ;
	private JLabel lblGroupTripDetails;
	
	public JoinGroupGUISecond(Traveller traveller , GroupTrip groupsel ,ArrayList<GroupTrip> groupsn, ArrayList<GroupTrip> availableGroupsn) {
		
		curTraveller  = traveller;
		selgroup = groupsel;
		availableGroups = availableGroupsn;
		groups = groupsn ;
				
		nameL = new JLabel("Name: " + selgroup.getName() );
		nameL.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameL.setBounds(34, 54, 185, 14);
		priceL = new JLabel("Price: " + selgroup.getPrice() + "$");
		priceL.setFont(new Font("Tahoma", Font.BOLD, 14));
		priceL.setBounds(259, 54, 148, 14);
		destL = new JLabel("Destination: " + selgroup.getDestination().getName());
		destL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		destL.setBounds(34, 79, 185, 14);
		hotelL = new JLabel("Hotel: " + selgroup.getHotel().name);
		hotelL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		hotelL.setBounds(34, 104, 185, 14);
		ticketL = new JLabel("Ticket: " + selgroup.getTicket().name);
		ticketL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ticketL.setBounds(259, 104, 148, 14);
		
		
		
		String ex2 = "";
		for (String a: selgroup.getDestination().getActivities())
			ex2 += a + "  " ;
		   
		
		extra2L = new JTextArea(ex2);
		extra2L.setWrapStyleWord(true);
		extra2L.setFont(new Font("Tahoma", Font.PLAIN, 16));
		extra2L.setBounds(34, 177, 373, 157);
		extra2L.setLineWrap(true);
		extra2L.setEditable(false);
		panel.setBackground(Color.WHITE);
		
		this.setContentPane(panel);
		panel.setLayout(null);
		getContentPane().add(nameL);
		getContentPane().add(priceL);
		getContentPane().add(destL);
		getContentPane().add(hotelL);
		getContentPane().add(ticketL);
		extra1L.setForeground(new Color(0, 102, 204));
		extra1L.setFont(new Font("Tahoma", Font.PLAIN, 16));
		extra1L.setBounds(34, 142, 185, 14);
		getContentPane().add(extra1L);
		getContentPane().add(extra2L);
		joinButton.setBounds(293, 356, 114, 23);
		getContentPane().add(joinButton);
		backButton.setBounds(218, 356, 65, 23);
		getContentPane().add(backButton);
		
		lblGroupTripDetails = new JLabel("Group Trip Details");
		lblGroupTripDetails.setForeground(new Color(204, 0, 0));
		lblGroupTripDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGroupTripDetails.setBackground(new Color(204, 0, 0));
		lblGroupTripDetails.setBounds(34, 20, 277, 23);
		panel.add(lblGroupTripDetails);
		
		ButtonListener listener = new ButtonListener();
		joinButton.addActionListener(listener);
		backButton.addActionListener(listener);
		
		this.setVisible(true);
		this.setSize(457, 435);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("GroupTrip Details");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(joinButton))
			{												//den vriskei to group
				dispose();
				
				if (selgroup.TravellerExists(curTraveller))
				{											//o xristis taxideuei idi me auto to group
					JFrame fail = new JFrame();
					JOptionPane.showMessageDialog(fail, "You are already member of this trip");
					dispose();
					new JoinGroupGUI(curTraveller, groups, availableGroups); 
				}
				else
				{
					selgroup.addTraveller(curTraveller);	
					JFrame Success = new JFrame();
					JOptionPane.showMessageDialog(Success, "You have joined the trip succesfully");
	            
				
				try {
					FileOutputStream fileOutGroups = new FileOutputStream("groups.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOutGroups);
			out.writeObject(availableGroups);
			out.close();
			fileOutGroups.close();		
				}
				catch(IOException i) {
					i.printStackTrace();
				}
				finally {
					System.out.println("Serialization Attempted Activity...");
				}
				dispose();
				new HomepageGUI(curTraveller);	
				}	
			}
			else 
			{
				dispose();
				new JoinGroupGUI(curTraveller, groups, availableGroups);   
			}
		}
	}
	
	

}
