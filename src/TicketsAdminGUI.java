import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class TicketsAdminGUI extends JFrame{
	
	private JPanel panel = new JPanel();
	
	private JList list = new JList();
	private ArrayList<Tickets> tickets;
	private ArrayList<Destination> dests;				
	private JButton create = new JButton("Create");
	private JButton update = new JButton("Update");
	private JButton delete = new JButton("Delete");
	private JButton view = new JButton("View");
	private JButton back = new JButton("Back");
	private Tickets selectedTicket; 
	private final JLabel lblManageTickets = new JLabel("Manage Tickets");
	
	public TicketsAdminGUI() {
		panel.setBackground(new Color(0, 139, 139));
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
		
		
		this.setResizable(false);
		this.setSize(440,304);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Tickets");
		this.setLocationRelativeTo(null);
		
		DefaultListModel model = new DefaultListModel();
		
		for(Tickets item:tickets) {
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
		lblManageTickets.setForeground(Color.WHITE);
		lblManageTickets.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblManageTickets.setBounds(18, 11, 147, 18);
		
		panel.add(lblManageTickets);
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
			if(e.getSource().equals(create)) {
				
				JFrame createFrame;
				JList destList = new JList();
				JPanel createPanel = new JPanel();
				
				JLabel name = new JLabel("Name");
				JLabel price = new JLabel("Price");
				DefaultListModel model = new DefaultListModel();
				JLabel destination = new JLabel("Choose destination");
				
				for(Destination item:dests) {
					model.addElement(item.getName());
				}
				
				destList.setModel(model);
				
				JTextField nameField = new JTextField(10);
				JTextField priceField = new JTextField(10);

				JButton save = new JButton("Save");
				JButton cancel = new JButton("Cancel");
				
				createPanel.add(name);
				createPanel.add(nameField);
				createPanel.add(price);
				createPanel.add(priceField);
				createPanel.add(destination);
				createPanel.add(destList);
				
				createPanel.add(save);
				createPanel.add(cancel);
				
				hide();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(200,250);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X Create Ticket");
				createFrame.setLocationRelativeTo(null);
				
				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
						
						String selectedDest =(String) destList.getSelectedValue();
						
						Destination selectedDestination = null;
						for(Destination d: dests) 									
							if(d.getName().equals(selectedDest))
								selectedDestination = d;
						
							Tickets t = new Tickets(nameField.getText(),Integer.parseInt(priceField.getText()),selectedDestination);
							tickets.add(t);
							
							try {
								FileOutputStream fileOutTickets = new FileOutputStream("tickets.ser");
								ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
								out.writeObject(tickets);
								out.close();
								fileOutTickets.close();		
							}
							catch(IOException i) {
								i.printStackTrace();
							}
							finally {
								System.out.println("Serialization Attempted Tickets...");
							}
							
							createFrame.hide();
							TicketsAdminGUI GUIA = new TicketsAdminGUI();
					}
				});
		
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							TicketsAdminGUI GUIA = new TicketsAdminGUI();
					}
				});
				}
			else 
			{
				
				String selectedTicketName =(String) list.getSelectedValue();
				
				selectedTicket = null;
				for(Tickets t: tickets) 									
					if(t.getName().equals(selectedTicketName))
						selectedTicket = t;
				
				JFrame createFrame;
				JPanel createPanel = new JPanel();
				
				
				if (e.getSource().equals(view)) 			//VIEW
				{
					
				JLabel name = new JLabel("Name: " + selectedTicket.getName());
				JLabel price = new JLabel("Price: " +selectedTicket.getPrice());
				JLabel destination = new JLabel("Destination: " + selectedTicket.getDestination().getName());
				JButton back = new JButton("Back");
				createPanel.add(name);
				createPanel.add(price);
				createPanel.add(destination);
				createPanel.add(back);
				
				hide();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(200,250);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X View Ticket");
				createFrame.setLocationRelativeTo(null);
				
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							TicketsAdminGUI GUIA = new TicketsAdminGUI();
					}
				});
				
			}
			
			else if (e.getSource().equals(update))					//UPDATE
			{
				
				
		        JList destList = new JList();
				JLabel name = new JLabel("Name");
				JLabel price = new JLabel("Price");
				DefaultListModel model = new DefaultListModel();
				JLabel destination = new JLabel("Choose new destination");
				
				for(Destination item:dests) {
					model.addElement(item.getName());
				}
				
				destList.setModel(model);
				
				JTextField nameField = new JTextField(10);
				JTextField priceField = new JTextField(10);
				priceField.setText(null);

				JButton update = new JButton("Update");
				JButton cancel = new JButton("Cancel");
				
				createPanel.add(name);
				createPanel.add(nameField);
				createPanel.add(price);
				createPanel.add(priceField);
				createPanel.add(destination);
				createPanel.add(destList);
				
				createPanel.add(update);
				createPanel.add(cancel);
				
				hide();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(200,250);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X Update Ticket");
				createFrame.setLocationRelativeTo(null);
				
				update.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createFrame.hide();
						if (!nameField.getText().isEmpty())
							selectedTicket.setName(nameField.getText());
						if (!priceField.getText().isEmpty())
							selectedTicket.setPrice(Integer.parseInt(priceField.getText()));
						if (destList.getSelectedValue()!=null)
						{
						String selectedDest =(String) destList.getSelectedValue();
						
						Destination selectedDestination = null;
						for(Destination d: dests) 									
							if(d.getName().equals(selectedDest))
								selectedDestination = d;
						selectedTicket.setDestination(selectedDestination);
						}
							
						try {
							FileOutputStream fileOutTickets = new FileOutputStream("tickets.ser");
							ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
							out.writeObject(tickets);
							out.close();
							fileOutTickets.close();		
						}
						catch(IOException i) {
							i.printStackTrace();
						}
						finally {
							System.out.println("Serialization Attempted Tickets...");
						}
						
							createFrame.hide();
							TicketsAdminGUI GUIA = new TicketsAdminGUI();
				
					}
				});
		
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							TicketsAdminGUI GUIA = new TicketsAdminGUI();
					}
				});
				
			}
			
			else if (e.getSource().equals(delete))													//DELETE
			{   hide();
				for (Tickets t:tickets)
				{
					if ( t.getName().equals(selectedTicket.getName()))
						{tickets.remove(t);
						
						try {
							FileOutputStream fileOutTickets = new FileOutputStream("tickets.ser");
							ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
							out.writeObject(tickets);
							out.close();
							fileOutTickets.close();		
						}
						catch(IOException i) {
							i.printStackTrace();
						}
						finally {
							System.out.println("Serialization Attempted Tickets...");
						}
					
					      hide();
					      break;}
					
				}
				TicketsAdminGUI GUIA = new TicketsAdminGUI();
			}
			else
			{
				hide();
				new AdminHomepageGUI();
			}
		}}
	}
}
