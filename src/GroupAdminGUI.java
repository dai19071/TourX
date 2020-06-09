
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
//Κλάση που δημιουργεί ένα παράθυρο για την επεξεργασία των γκρουπ από τον admin
public class GroupAdminGUI extends JFrame{
	
	private JPanel panel = new JPanel();
	
	private JList list = new JList();
	private JList hlist= new JList();
	private JList tlist= new JList();
	private JList dlist= new JList();
	private DefaultListModel modelh = new DefaultListModel();
	private DefaultListModel modelt = new DefaultListModel();
	private DefaultListModel modeld = new DefaultListModel();
	
    private ArrayList<Traveller> travellers = new ArrayList<Traveller>();
	private ArrayList<Tickets> tickets;
	private ArrayList<Destination> dests;
	private ArrayList<Hotel> hotels;
	private ArrayList<GroupTrip> groups;
	private JButton create = new JButton("Create");
	private JButton update = new JButton("Update");
	private JButton delete = new JButton("Delete");
	private JButton view = new JButton("View");
	private JButton back = new JButton("Back");
	private GroupTrip selectedGroup; 
	private final JLabel groupsLabel = new JLabel("Manage Groups");
	
	public GroupAdminGUI() {
		panel.setBackground(new Color(0, 153, 153));
		this.setContentPane(panel);
		//Ανάκτηση εισιτηρίων
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
		finally {
			System.out.println("De-Serialization Attempted...");
		
		}
		//Ανάκτηση προορισμών
		try {
			FileInputStream fileIn = new FileInputStream("destination.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			dests = (ArrayList<Destination>) in.readObject();
			in.close();
			fileIn.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		finally {
			System.out.println("De-Serialization Attempted...");
		
		}
		//Ανάκτηση ξενοδοχείων
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
		finally {
			System.out.println("De-Serialization Attempted...");
		
		}
		//Ανάκτηση γκρουπ
		try {
			FileInputStream fileIn = new FileInputStream("groups.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			groups = (ArrayList<GroupTrip>) in.readObject();
			in.close();
			fileIn.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		finally {
			System.out.println("De-Serialization Attempted...");
		
		}
		
		
		this.setResizable(false);
		this.setSize(440,304);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Groups");
		this.setLocationRelativeTo(null);
		
		DefaultListModel model = new DefaultListModel();
		
		for(GroupTrip item:groups) {
			model.addElement(item.getName());
		}
		panel.setLayout(null);
		list.setBounds(18, 40, 392, 157);
		
		list.setModel(model);
		
		getContentPane().add(list);
		view.setBounds(18, 217, 65, 23);
		
		getContentPane().add(view);
		create.setBounds(93, 217, 72, 23);
		getContentPane().add(create);
		update.setBounds(175, 217, 78, 23);
		getContentPane().add(update);
		delete.setBounds(263, 217, 72, 23);
		getContentPane().add(delete);
		back.setBounds(345, 217, 65, 23);
		getContentPane().add(back);
		groupsLabel.setForeground(Color.WHITE);
		groupsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		groupsLabel.setBounds(18, 11, 147, 18);
		
		panel.add(groupsLabel);
		ButtonListener listener = new ButtonListener();
		
		view.addActionListener(listener);
		create.addActionListener(listener);
		update.addActionListener(listener);
		delete.addActionListener(listener);
		back.addActionListener(listener);
		
	}

	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Όταν πατηθεί το Create βγαίνει ένα νέο παράθυρο που μπορούμε να δημιουργήσουμε το Group
			if(e.getSource().equals(create)) {
				
				JFrame createFrame;
				JPanel createPanel = new JPanel();
				
				JLabel name = new JLabel("Name");
				JLabel price = new JLabel("Price");
				JLabel cap = new JLabel("Capacity");
				JLabel destination = new JLabel("Choose destination");
				JLabel hotel = new JLabel("Choose hotel");
				JLabel ticket = new JLabel("Choose ticket");
				
				for(Destination item:dests) {
					modeld.addElement(item.getName());
				}
				
				dlist.setModel(modeld);
				
				
				for(Hotel item:hotels) {
					modelh.addElement(item.getName());
				}
				
				hlist.setModel(modelh);
				
				for(Tickets item:tickets) {
					modelt.addElement(item.getName());
				}
				
				tlist.setModel(modelt);
				
				JTextField nameField = new JTextField(10);
				JTextField priceField = new JTextField(10);
				JTextField capField = new JTextField(10);
				
				JButton save = new JButton("Save");
				JButton cancel = new JButton("Cancel");
				
				createPanel.add(name);
				createPanel.add(nameField);
				createPanel.add(price);
				createPanel.add(priceField);
				createPanel.add(cap);
				createPanel.add(capField);
				createPanel.add(destination);
				createPanel.add(dlist);
				createPanel.add(hotel);
				createPanel.add(hlist);
				createPanel.add(ticket);
				createPanel.add(tlist);
				
				
				createPanel.add(save);
				createPanel.add(cancel);
				
				dispose();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(200,500);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X Create Group");
				createFrame.setLocationRelativeTo(null);
				
				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
						
						String selectedDest =(String) dlist.getSelectedValue();
						
						Destination selectedDestination = null;
						for(Destination d: dests) 									
							if(d.getName().equals(selectedDest))
								selectedDestination = d;
						
                        String selectedHot =(String) hlist.getSelectedValue();
						
						Hotel selectedHotel = null;
						for(Hotel d: hotels) 									
							if(d.getName().equals(selectedHot))
								selectedHotel = d;
						
                        String selectedTic =(String) tlist.getSelectedValue();
						
						Tickets selectedTicket = null;
						for(Tickets d: tickets) 									
							if(d.getName().equals(selectedTic))
								selectedTicket = d;
						
							GroupTrip g = new GroupTrip(nameField.getText(),Integer.parseInt(priceField.getText()) ,Integer.parseInt(capField.getText()),selectedDestination,
														selectedHotel, selectedTicket , travellers);
							groups.add(g);
							//Ενημέρωση του αρχείου που κρατώνται τα γκρουπ
							try {
								FileOutputStream fileOutTickets = new FileOutputStream("groups.ser");
								ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
								out.writeObject(groups);
								out.close();
								fileOutTickets.close();		
							}
							catch(IOException i) {
								i.printStackTrace();
							}
							finally {
								System.out.println("Serialization Attempted Groups...");
							}
							
							createFrame.hide();
							GroupAdminGUI GUIA = new GroupAdminGUI();
					}
				});
		
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.dispose();
							GroupAdminGUI GUIA = new GroupAdminGUI();
					}
				});
				}
			else 
			{
				
				String selectedGroupName =(String) list.getSelectedValue();
				
				selectedGroup = null;
				for(GroupTrip t: groups) 									
					if(t.getName().equals(selectedGroupName))
						selectedGroup = t;
				
				JFrame createFrame;
				JPanel createPanel = new JPanel();
				
				
				if (e.getSource().equals(view)) 			//VIEW
				{
					
				JLabel name = new JLabel("Name: " + selectedGroup.getName());
				JLabel price = new JLabel("Price: " +selectedGroup.getPrice());
				JLabel cap = new JLabel("Capacity: " +selectedGroup.getCapacity());
				JLabel destination = new JLabel("Destination: " + selectedGroup.getDestination().getName());
				JLabel hotel = new JLabel("Hotel: " + selectedGroup.getHotel().getName());
				JLabel ticket = new JLabel("Ticket: " + selectedGroup.getTicket().getName());
				JButton back = new JButton("Back");
				createPanel.add(name);
				createPanel.add(price);
				createPanel.add(cap);
				createPanel.add(destination);
				createPanel.add(hotel);
				createPanel.add(ticket);
				
				createPanel.add(back);
				
				dispose();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(200,250);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X View Group");
				createFrame.setLocationRelativeTo(null);
				
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.dispose();
							GroupAdminGUI GUIA = new GroupAdminGUI();
					}
				});
				
			}
			
			else if (e.getSource().equals(update))					//Αν πατηθεί το update τότε δημιουργείται ένα νέο παράθυρο που μπορεί να γίνει ενημέρωση των στοιχείων
			{
				
			
				JLabel name = new JLabel("Name");
				JLabel price = new JLabel("Price");
				JLabel cap = new JLabel("Capacity");
				
				JLabel destination = new JLabel("Choose new destination");
				JLabel hotel = new JLabel("Choose new hotel");
				JLabel ticket = new JLabel("Choose new ticket");
				
				
				JTextField nameField = new JTextField(10);
				JTextField priceField = new JTextField(10);
				JTextField capField = new JTextField(10);

				JButton update = new JButton("Update");
				JButton cancel = new JButton("Cancel");
				
				
				for(Destination item:dests) {
					modeld.addElement(item.getName());
				}
				
				dlist.setModel(modeld);
				
				
				for(Hotel item:hotels) {
					modelh.addElement(item.getName());
				}
				
				hlist.setModel(modelh);
				
				for(Tickets item:tickets) {
					modelt.addElement(item.getName());
				}
				
				tlist.setModel(modelt);
				
				
				createPanel.add(name);
				createPanel.add(nameField);
				createPanel.add(price);
				createPanel.add(priceField);
				createPanel.add(cap);
				createPanel.add(capField);
				createPanel.add(destination);
				createPanel.add(dlist);
				createPanel.add(hotel);
				createPanel.add(hlist);
				createPanel.add(ticket);
				createPanel.add(tlist);
				
				createPanel.add(update);
				createPanel.add(cancel);
				
				dispose();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(150,550);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X Update Group");
				createFrame.setLocationRelativeTo(null);
				
				update.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createFrame.dispose();
						if (!nameField.getText().isEmpty())
							selectedGroup.setName(nameField.getText());
						if (!priceField.getText().isEmpty())
							selectedGroup.setPrice(Integer.parseInt(priceField.getText()));
						if (!capField.getText().isEmpty())
							selectedGroup.setPrice(Integer.parseInt(priceField.getText()));
						if (dlist.getSelectedValue()!=null)
						{
						String selectedDest =(String) dlist.getSelectedValue();
						
						Destination selectedDestination = null;
						for(Destination d: dests) 									
							if(d.getName().equals(selectedDest))
								selectedDestination = d;
						selectedGroup.setDestination(selectedDestination);
						}
						if (hlist.getSelectedValue()!=null)
						{
						String selectedHot =(String) hlist.getSelectedValue();
						
						Hotel selectedHotel = null;
						for(Hotel d: hotels) 									
							if(d.getName().equals(selectedHot))
								selectedHotel = d;
						selectedGroup.setHotel(selectedHotel);
						}
						if (tlist.getSelectedValue()!=null)
						{
						String selectedTic =(String) tlist.getSelectedValue();
						
						Tickets selectedTicket = null;
						for(Tickets d: tickets) 									
							if(d.getName().equals(selectedTic))
								selectedTicket = d;
						selectedGroup.setTicket(selectedTicket);
						}
						
								
						try {
							FileOutputStream fileOutTickets = new FileOutputStream("groups.ser");
							ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
							out.writeObject(groups);
							out.close();
							fileOutTickets.close();		
						}
						catch(IOException i) {
							i.printStackTrace();
						}
						finally {
							System.out.println("Serialization Attempted Groups...");
						}
						
						createFrame.dispose();
						GroupAdminGUI GUIA = new GroupAdminGUI();
				}
			});
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.dispose();
							GroupAdminGUI GUIA = new GroupAdminGUI();
					}
				});
				
			}
			
			else if (e.getSource().equals(delete))													//Αν πατήσει Delete τότε το ξενοδοχείο διαγράφεται 
			{   dispose();
				for (GroupTrip t:groups)
				{
					if ( t.getName().equals(selectedGroup.getName()))
						{groups.remove(t);
						
						try {
							FileOutputStream fileOutTickets = new FileOutputStream("groups.ser");
							ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
							out.writeObject(groups);
							out.close();
							fileOutTickets.close();		
						}
						catch(IOException i) {
							i.printStackTrace();
						}
						finally {
							System.out.println("Serialization Attempted Groups...");
						}
					
					      dispose();
					      break;}
					
				}
				GroupAdminGUI GUIA = new GroupAdminGUI();
			}
			else
			{
				dispose();
				new AdminHomepageGUI();
			}
		}}
	}
}

