import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
/*Στο συγκεκριμενο παραθυρο το οποιο αποτελει το τελευταιο σταδιο 
 * ο χρηστης αφου εχει επιλεξει την τοποθεσια η εφαρμογη τον βαζει να επιλεξει 
 * το ξενοδοχειο στο οποιο θελει να μεινει και το εισιτηριο
 */
public class SelectHotelTicketGUI extends JFrame{
	private String destination;
	private ArrayList<Hotel> hotels;
	private ArrayList<Tickets> tickets;
	private JPanel panel = new JPanel();
	private DefaultListModel hotelsmodel = new DefaultListModel();
	private JList hotellist = new JList();
	private DefaultListModel ticketsmodel = new DefaultListModel();
	private JList ticketslist = new JList();
	private JLabel hotelslabel = new JLabel("Hotels");
	private JLabel ticketslabel = new JLabel("Tickets");
	private ArrayList<String> dest;
	private Traveller currentTraveller;
	
	private JButton select = new JButton("Select");
	private JButton back = new JButton("Back");
	private final JLabel infoLabel = new JLabel("Which hotel and which ticket do you prefer the most?");
	private final JLabel lblPickFromThe = new JLabel("Pick from the following lists");
	
	private ArrayList<TravellerTrip> trips;

	public SelectHotelTicketGUI(String destination,ArrayList<String> dest,Traveller currentTraveller)
	{
		this.destination = destination;
		this.dest = dest;
		this.currentTraveller = currentTraveller;
		panel.setBackground(Color.WHITE);
		this.setContentPane(panel);
		this.setLocationRelativeTo(null);
		try {
			FileInputStream fileIn = new FileInputStream("trips.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			trips = (ArrayList<TravellerTrip>) in.readObject();
			in.close();
			fileIn.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		try {
			FileInputStream fileIn = new FileInputStream("hotels.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			hotels = (ArrayList<Hotel>) in.readObject();
			in.close();
			fileIn.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		try {
			FileInputStream fileIn = new FileInputStream("tickets.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tickets = (ArrayList<Tickets>) in.readObject();
			in.close();
			fileIn.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		for(Hotel h:hotels)
		{
			if(h.getDest().getName().equals(destination))
			{
				String s = h.getName() + " " + h.getPrice() + " per day";
				hotelsmodel.addElement(s);
			}
		}
		hotellist.setBounds(38, 101, 277, 157);
		hotellist.setModel(hotelsmodel);
		for(Tickets t:tickets)
		{
			if(t.getDestination().getName().equals(destination))
			{
				String s = t.getName() + " " + t.getPrice();
				ticketsmodel.addElement(s);
			}
		}
		ticketslist.setBounds(357, 101, 277, 157);
		ticketslist.setModel(ticketsmodel);
		panel.setLayout(null);
		hotelslabel.setForeground(new Color(51, 102, 204));
		hotelslabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		hotelslabel.setBounds(38, 76, 72, 14);
		getContentPane().add(hotelslabel);
		ticketslabel.setForeground(new Color(51, 102, 204));
		ticketslabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		ticketslabel.setBounds(357, 78, 87, 14);
		getContentPane().add(ticketslabel);
		getContentPane().add(hotellist);
		getContentPane().add(ticketslist);
		select.setBounds(542, 304, 92, 23);
		getContentPane().add(select);
		back.setBounds(469, 304, 65, 23);
		getContentPane().add(back);
		infoLabel.setForeground(new Color(204, 0, 0));
		infoLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		infoLabel.setBackground(new Color(204, 0, 0));
		infoLabel.setBounds(38, 26, 596, 14);
		
		panel.add(infoLabel);
		lblPickFromThe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPickFromThe.setBounds(38, 51, 277, 14);
		
		panel.add(lblPickFromThe);
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(670,378);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		ButtonListener listener = new ButtonListener();
		select.addActionListener(listener);
		back.addActionListener(listener);
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame Trippackage = new JFrame();
			JPanel packagepanel = new JPanel();
			JLabel destlabel = new JLabel("Destination:");
			JLabel hotellabel = new JLabel("Hotel:");
			JLabel ticketlabel = new JLabel("Ticket:");
			JLabel pricelabel = new JLabel("Total Price:");
			JLabel selecteddest = new JLabel(destination);
			JButton cancel = new JButton("cancel");
			JButton finish = new JButton("finish");

			if(e.getSource().equals(select))
			{//Επιλεγει ενα ξενοδοχειο
				hide();
				double totalvalue;
				String hotelvalue = (String) hotellist.getSelectedValue();
				//String ticketvalue = (String) ticketslist.getSelectedValue();
				Scanner in1 = new Scanner(hotelvalue);
			//	Scanner in2 = new Scanner(ticketvalue);
				String hotelName = in1.next();
				for(Hotel h:hotels)
				{
					//ψαχνει το ξενοδοχειο που εχει επιλεξει
					if(h.getName().equals(hotelName))
					{
						if(h.getCapacity()!=0)
						{//ελεγχει αν υπαρχει διαθεσιμο δωματιο και στην περιπτωση που υπαρχει συνεχιζει κανονικα την διαδικασια.
							String ticketvalue = (String) ticketslist.getSelectedValue();
							Scanner in2 = new Scanner(ticketvalue);
							h.setCapacity(h.getCapacity() - 1);
							totalvalue = Double.valueOf(in1.next());
							String ticketName = in2.next();
							totalvalue = totalvalue + Double.valueOf(in2.next());
							JLabel selectedhotel = new JLabel(hotelName);
							JLabel selectedticket = new JLabel(ticketName);
							JLabel totalprice = new JLabel(String.valueOf(totalvalue));
							//Προσθετει το ταξιδι στα συνολικα ταξιδια που προγραμματιστηκαν απο το γραφειο
							TravellerTrip trip = new TravellerTrip(destination,hotelName,ticketName,currentTraveller);
							
							trips.add(trip);
							packagepanel.add(destlabel);
							packagepanel.add(selecteddest);
							packagepanel.add(hotellabel);
							packagepanel.add(selectedhotel);
							packagepanel.add(ticketlabel);
							packagepanel.add(selectedticket);
							packagepanel.add(pricelabel);
							packagepanel.add(totalprice);
							
							packagepanel.add(finish);
							packagepanel.add(cancel);
							Trippackage.setContentPane(packagepanel);
							Trippackage.setResizable(false);
							Trippackage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							Trippackage.setVisible(true);
							Trippackage.setSize(150, 200);
							Trippackage.setLocationRelativeTo(null);
							try {
								FileOutputStream fileOutHotel = new FileOutputStream("trips.ser");
								ObjectOutputStream out = new ObjectOutputStream(fileOutHotel);
								out.writeObject(trips);
								out.close();
								fileOutHotel.close();		
							}
							catch(IOException i) {
								i.printStackTrace();
							}
							  try {
									FileOutputStream fileOutHotel = new FileOutputStream("hotels.ser");
									ObjectOutputStream out = new ObjectOutputStream(fileOutHotel);
									out.writeObject(hotels);
									out.close();
									fileOutHotel.close();		
								}
								catch(IOException i) {
									i.printStackTrace();
								}
						}
						else
						{
							//Στην περιπτωση που δεν υπαρχει διαθεσιμο ξενοδοχειο του εμφανιζει καταλληλο μνμ και τον επιστρεφει στην επιλογη εισιτηριου και ξενοδοχειο.
							JFrame notavaliableroom = new JFrame();
							JOptionPane.showMessageDialog(notavaliableroom,"There is no avaliable room in this hotel please pick another one");
							SelectHotelTicketGUI htg = new SelectHotelTicketGUI(destination,dest,currentTraveller);
						}
						
					}
				}
				
				
				/*Εφοσον εχει ολοκληρωθει η επιλογη των λεπτομεριων του ταξιδιου
				 * του εμφανιζει ενα ακομη παραθυρο στο οποιο ζηταει την επιβεβαιωση του χρηστη
				 * μαζι με το συνολικο κοστος του ταξιδιου.
				 */
				
				
				cancel.addActionListener(new ActionListener() {
					//Σε περιπτωση που επιθυμει να το ακυρωσει τον επιστρεφει στην επιλογη ξενοδοχειου και εισιτηριου.
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource().equals(cancel))
						{
							Trippackage.hide();
							SelectHotelTicketGUI SelHotelandTicketGUI = new  SelectHotelTicketGUI(destination,dest,currentTraveller);
						}
						
					}
				});
				
				finish.addActionListener(new ActionListener() {
					//Ολοκληρωση της διαδικασιας και μεταδιδαση στο αρχικο παραθυρο του χρηστη.
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource().equals(finish))
						{
							Trippackage.hide();
							JFrame finishframe = new JFrame();
							JOptionPane.showMessageDialog(finishframe, "You completed your package thank you for trusting us");
							new HomepageGUI(currentTraveller);
						}
						
					}
					
				});
				
			}
			else
			{
				dispose();
				SelectedDestination destination = new SelectedDestination(dest,currentTraveller);
			}
			
		}
		
	}
}
