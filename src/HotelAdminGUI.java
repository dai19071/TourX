import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
//Κλάση που δημιουργεί ένα παράθυρο για την επεξεργασία των ξενοδοχείων από τον admin
public class HotelAdminGUI extends JFrame{
	
	private JPanel panel = new JPanel();
	
	private JList list = new JList();
	private ArrayList<Hotel> hotels;
	private ArrayList<Destination> dests;				
	private JButton create = new JButton("Create");
	private JButton update = new JButton("Update");
	private JButton delete = new JButton("Delete");
	private JButton view = new JButton("View");
	private JButton back = new JButton("Back");
	private Hotel selectedHotel; 
	private final JLabel lblNewLabel = new JLabel("Manage Hotels");
	
	public HotelAdminGUI() {
		panel.setBackground(new Color(0, 139, 139));
		this.setContentPane(panel);
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
		this.setSize(438,304);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Hotels");
		this.setLocationRelativeTo(null);
		
		DefaultListModel model = new DefaultListModel();
		
		for(Hotel item:hotels) {
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
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(18, 11, 147, 18);
		
		panel.add(lblNewLabel);
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
				JLabel capacity = new JLabel("Capacity");
				JLabel price = new JLabel("Price");
				DefaultListModel model = new DefaultListModel();
				JLabel destination = new JLabel("Choose destination");
				
				for(Destination item:dests) {
					model.addElement(item.getName());
				}
				
				destList.setModel(model);
				
				JTextField nameField = new JTextField(10);
				JTextField capacityField = new JTextField(10);
				JTextField priceField = new JTextField(10);

				JButton save = new JButton("Save");
				JButton cancel = new JButton("Cancel");
				
				createPanel.add(name);
				createPanel.add(nameField);
				createPanel.add(capacity);
				createPanel.add(capacityField);
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
				createFrame.setTitle("Tour-X Create Hotel");
				createFrame.setLocationRelativeTo(null);
				
				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
						
						String selectedDest =(String) destList.getSelectedValue();
						
						Destination selectedDestination = null;
						for(Destination d: dests) 									
							if(d.getName().equals(selectedDest))
								selectedDestination = d;
						
							Hotel h = new Hotel(nameField.getText(),Integer.parseInt(capacityField.getText()),Integer.parseInt(priceField.getText()),selectedDestination);
							hotels.add(h);
							
							try {
								FileOutputStream fileOutHotels = new FileOutputStream("hotels.ser");
								ObjectOutputStream out = new ObjectOutputStream(fileOutHotels);
								out.writeObject(hotels);
								out.close();
								fileOutHotels.close();		
							}
							catch(IOException i) {
								i.printStackTrace();
							}
							finally {
								System.out.println("Serialization Attempted Hotels...");
							}
							
							createFrame.hide();
							HotelAdminGUI GUIA = new HotelAdminGUI();
					}
				});
		
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							HotelAdminGUI GUIA = new HotelAdminGUI();
					}
				});
				}
			else 
			{
				
				String selectedHotelName =(String) list.getSelectedValue();
				
				selectedHotel = null;
				for(Hotel h: hotels) 									
					if(h.getName().equals(selectedHotelName))
						selectedHotel = h;
				
				JFrame createFrame;
				JPanel createPanel = new JPanel();
				
				
				if (e.getSource().equals(view)) 			//VIEW
				{
					
				JLabel name = new JLabel("Name: " + selectedHotel.getName());
				JLabel capacity = new JLabel("Capacity: " + selectedHotel.getCapacity());
				JLabel price = new JLabel("Price: " +selectedHotel.getPrice());
				JLabel destination = new JLabel("Destination: " + selectedHotel.getDest().getName());
				JButton back = new JButton("Back");
				createPanel.add(name);
				createPanel.add(capacity);
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
				createFrame.setTitle("Tour-X View Hotel");
				createFrame.setLocationRelativeTo(null);
				
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							HotelAdminGUI GUIA = new HotelAdminGUI();
					}
				});
				
			}
			
			else if (e.getSource().equals(update))					//UPDATE
			{
				
				
		        JList destList = new JList();
				JLabel name = new JLabel("Name");
				JLabel capacity = new JLabel("Capacity");
				JLabel price = new JLabel("Price");
				DefaultListModel model = new DefaultListModel();
				JLabel destination = new JLabel("Choose new destination");
				
				for(Destination item:dests) {
					model.addElement(item.getName());
				}
				
				destList.setModel(model);
				
				JTextField nameField = new JTextField(10);
				JTextField capacityField = new JTextField(10);
				capacityField.setText(null);
				JTextField priceField = new JTextField(10);
				priceField.setText(null);

				JButton update = new JButton("Update");
				JButton cancel = new JButton("Cancel");
				
				createPanel.add(name);
				createPanel.add(nameField);
				createPanel.add(capacity);
				createPanel.add(capacityField);
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
				createFrame.setTitle("Tour-X Update Hotel");
				createFrame.setLocationRelativeTo(null);
				
				update.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createFrame.hide();
						if (!nameField.getText().isEmpty())
							selectedHotel.setName(nameField.getText());
						if (!capacityField.getText().isEmpty())
							selectedHotel.setCapacity(Integer.parseInt(capacityField.getText()));
						if (!priceField.getText().isEmpty())
							selectedHotel.setPrice(Integer.parseInt(priceField.getText()));
						if (destList.getSelectedValue()!=null)
						{
						String selectedDest =(String) destList.getSelectedValue();
						
						Destination selectedDestination = null;
						for(Destination d: dests) 									
							if(d.getName().equals(selectedDest))
								selectedDestination = d;
						selectedHotel.setDest(selectedDestination);
						}
							
						try {
							FileOutputStream fileOutHotels = new FileOutputStream("hotels.ser");
							ObjectOutputStream out = new ObjectOutputStream(fileOutHotels);
							out.writeObject(hotels);
							out.close();
							fileOutHotels.close();		
						}
						catch(IOException i) {
							i.printStackTrace();
						}
						finally {
							System.out.println("Serialization Attempted Hotels...");
						}
						
							createFrame.hide();
							HotelAdminGUI GUIA = new HotelAdminGUI();
				
					}
				});
		
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							HotelAdminGUI GUIA = new HotelAdminGUI();
					}
				});
				
			}
			
			else if (e.getSource().equals(delete))													//DELETE
			{   hide();
				for (Hotel h:hotels)
				{
					if ( h.getName().equals(selectedHotel.getName()))
						{hotels.remove(h);
						
						try {
							FileOutputStream fileOutHotels = new FileOutputStream("hotels.ser");
							ObjectOutputStream out = new ObjectOutputStream(fileOutHotels);
							out.writeObject(hotels);
							out.close();
							fileOutHotels.close();		
						}
						catch(IOException i) {
							i.printStackTrace();
						}
						finally {
							System.out.println("Serialization Attempted Hotels...");
						}
					
					      hide();
					      break;}
					
				}
				HotelAdminGUI GUIA = new HotelAdminGUI();
			}
			else
			{
				hide();
				new AdminHomepageGUI();
			}
		}}
	}
}
