import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
//Κλάση που χρησιμοποιείται για την επεξεργασία των προορισμών από τον admin
public class DestAdminGUI extends JFrame{
	
	private JPanel panel = new JPanel();
	private JList actiList ;
	private JList list = new JList();
	private ArrayList<Destination> dests;
	private ArrayList<String> acts;
	private JButton create = new JButton("Create");
	private JButton update = new JButton("Update");
	private JButton delete = new JButton("Delete");
	private JButton view = new JButton("View");
	private JButton back = new JButton("Back");
	private Destination selectedDest; 
	
	public DestAdminGUI() {
		panel.setBackground(new Color(0, 153, 153));
		this.setContentPane(panel);
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
		//Ανάκτηση δραστηριοτήτων
		try {
			FileInputStream fileIn = new FileInputStream("activities.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			acts = (ArrayList<String>) in.readObject();
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
		this.setTitle("Destinations");
		this.setLocationRelativeTo(null);
		
		DefaultListModel model = new DefaultListModel();
		
		for(Destination item:dests) {
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
		
		JLabel destinationsLabel = new JLabel("Manage Destinations");
		destinationsLabel.setForeground(Color.WHITE);
		destinationsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		destinationsLabel.setBounds(18, 11, 201, 18);
		panel.add(destinationsLabel);
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
				JPanel createPanel = new JPanel();
				
				JLabel name = new JLabel("Name");
				
				JLabel activities = new JLabel("Choose activities");
				
				actiList = new JList(acts.toArray());
				actiList.setSelectionModel(new DefaultListSelectionModel() {
				    @Override
				    public void setSelectionInterval(int index0, int index1) {
				        if(super.isSelectedIndex(index0)) {
				            super.removeSelectionInterval(index0, index1);
				        }
				        else {
				            super.addSelectionInterval(index0, index1);
				        }
				    }
				});
				
				
				JTextField nameField = new JTextField(10);
				
				JButton save = new JButton("Save");
				JButton cancel = new JButton("Cancel");
				
				createPanel.add(name);
				createPanel.add(nameField);
				createPanel.add(activities);
				createPanel.add(actiList);
				
				createPanel.add(save);
				createPanel.add(cancel);
				
				hide();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(200,250);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X Create Destination");
				createFrame.setLocationRelativeTo(null);
				
				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
						
						int[] selIndices= actiList.getSelectedIndices();
						ArrayList<String> actArray = new ArrayList<String>();
						for (int i = 0; i < selIndices.length; i++)
						{
						      actArray.add( String.valueOf(actiList.getModel().getElementAt(selIndices[i])));
					    }
						
					
							Destination d = new Destination(nameField.getText(),actArray);
							dests.add(d);
							
							try {
								FileOutputStream fileOutTickets = new FileOutputStream("destination.ser");
								ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
								out.writeObject(dests);
								out.close();
								fileOutTickets.close();		
							}
							catch(IOException i) {
								i.printStackTrace();
							}
							finally {
								System.out.println("Serialization Attempted Destinations...");
							}
							
							createFrame.hide();
							DestAdminGUI GUIA = new DestAdminGUI();
					}
				});
		
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							DestAdminGUI GUIA = new DestAdminGUI();
					}
				});
				}
			else 
			{
				
				String selectedDestName =(String) list.getSelectedValue();
				
				selectedDest = null;
				for(Destination d: dests) 									
					if(d.getName().equals(selectedDestName))
						selectedDest = d;
				
				JFrame createFrame;
				JPanel createPanel = new JPanel();
				JTextArea actArea ;
				
				if (e.getSource().equals(view)) 			//VIEW
				{
					
				JLabel name = new JLabel("Name: " + selectedDest.getName());
				JLabel actLabel = new JLabel ( "            Activities");
				
				String ex2 = "";
				for (String a: selectedDest.getActivities())
					ex2 += a + "  " ;
				   
				
				actArea = new JTextArea(ex2);
				actArea.setLineWrap(true);
				actArea.setWrapStyleWord(true);
				actArea.setEditable(false);
				
				JButton back = new JButton("Back");
				createPanel.add(name);
				createPanel.add(actLabel);
				createPanel.add(actArea);
				createPanel.add(back);
				
				hide();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(200,250);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X View Destination");
				createFrame.setLocationRelativeTo(null);
				
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							DestAdminGUI GUIA = new DestAdminGUI();
					}
				});
				
			}
			
			else if (e.getSource().equals(update))					//UPDATE
			{
				actiList = new JList(acts.toArray());
				actiList.setSelectionModel(new DefaultListSelectionModel() {
				    @Override
				    public void setSelectionInterval(int index0, int index1) {
				        if(super.isSelectedIndex(index0)) {
				            super.removeSelectionInterval(index0, index1);
				        }
				        else {
				            super.addSelectionInterval(index0, index1);
				        }
				    }
				});
				
		        
				JLabel name = new JLabel("Name");
				JLabel activities = new JLabel("Choose new activities");
				
				
				JTextField nameField = new JTextField(10);

				JButton update = new JButton("Update");
				JButton cancel = new JButton("Cancel");
				
				createPanel.add(name);
				createPanel.add(nameField);
				createPanel.add(activities);
				createPanel.add(actiList);
				
				createPanel.add(update);
				createPanel.add(cancel);
				
				hide();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(200,250);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X Update Destination");
				createFrame.setLocationRelativeTo(null);
				
				update.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createFrame.hide();
						if (!nameField.getText().isEmpty())
							selectedDest.setName(nameField.getText());
						
						int[] selIndices= actiList.getSelectedIndices();
						ArrayList<String> actArray = new ArrayList<String>();
						for (int i = 0; i < selIndices.length; i++)
						{
						      actArray.add( String.valueOf(actiList.getModel().getElementAt(selIndices[i])));
					    }
						
						if (selIndices.length!=0)
							selectedDest.setActivities(actArray);
							
							try {
								FileOutputStream fileOutTickets = new FileOutputStream("destination.ser");
								ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
								out.writeObject(dests);
								out.close();
								fileOutTickets.close();		
							}
							catch(IOException i) {
								i.printStackTrace();
							}
							finally {
								System.out.println("Serialization Attempted Destinations...");
							}
						
							createFrame.hide();
							DestAdminGUI GUIA = new DestAdminGUI();
				
					}
				});
		
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							DestAdminGUI GUIA = new DestAdminGUI();
					}
				});
				
			}
			
			else if (e.getSource().equals(delete))													//DELETE
			{   hide();
				for (Destination d:dests)
				{
					if ( d.getName().equals(selectedDest.getName()))
						{dests.remove(d);
						
						try {
							FileOutputStream fileOutTickets = new FileOutputStream("destination.ser");
							ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
							out.writeObject(dests);
							out.close();
							fileOutTickets.close();		
						}
						catch(IOException i) {
							i.printStackTrace();
						}
						finally {
							System.out.println("Serialization Attempted Destinations...");
						
						}
					
					      hide();
					      break;}
					
				}
				DestAdminGUI GUIA = new DestAdminGUI();
			}
			else
			{
				hide();
				new AdminHomepageGUI();
			}
		}}
	}
}
