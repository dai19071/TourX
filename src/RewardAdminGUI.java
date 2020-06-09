
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
//Κλάση που δημιουργεί ένα παράθυρο για την επεξεργασία των δώρων από τον admin
public class RewardAdminGUI extends JFrame{
	
	private JPanel panel = new JPanel();
	
	private JList list = new JList();
	private ArrayList<Reward> rewards;				
	private JButton create = new JButton("Create");
	private JButton update = new JButton("Update");
	private JButton delete = new JButton("Delete");
	private JButton view = new JButton("View");
	private JButton back = new JButton("Back");
	private Reward selectedReward; 
	
	public RewardAdminGUI() {
		panel.setBackground(new Color(0, 153, 153));
		this.setContentPane(panel);
		//Ανάκτηση δώρων
		try {
			FileInputStream fileIn = new FileInputStream("Rewards.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			rewards = (ArrayList<Reward>) in.readObject();
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
		this.setTitle("Rewards");
		this.setLocationRelativeTo(null);
		
		DefaultListModel model = new DefaultListModel();
		
		for(Reward item:rewards) {
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
		
		JLabel rewardsLabel = new JLabel("Manage Rewards");
		rewardsLabel.setForeground(Color.WHITE);
		rewardsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		rewardsLabel.setBounds(18, 11, 147, 18);
		panel.add(rewardsLabel);
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
				JLabel price = new JLabel("Price");
				JLabel points = new JLabel("Points needed");
				
				JTextField nameField = new JTextField(10);
				JTextField priceField = new JTextField(10);
				JTextField pointsField = new JTextField(10);
				
				JButton save = new JButton("Save");
				JButton cancel = new JButton("Cancel");
				
				createPanel.add(name);
				createPanel.add(nameField);
				createPanel.add(price);
				createPanel.add(priceField);
				createPanel.add(points);
				createPanel.add(pointsField);
				
				createPanel.add(save);
				createPanel.add(cancel);
				
				hide();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(200,250);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X Create Reward");
				createFrame.setLocationRelativeTo(null);
				
				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
						
							Reward t = new Reward(nameField.getText(),Integer.parseInt(priceField.getText()),Integer.parseInt(pointsField.getText()));
							rewards.add(t);
							
							try {
								FileOutputStream fileOutTickets = new FileOutputStream("Rewards.ser");
								ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
								out.writeObject(rewards);
								out.close();
								fileOutTickets.close();		
							}
							catch(IOException i) {
								i.printStackTrace();
							}
							finally {
								System.out.println("Serialization Attempted Rewards...");
							}
							
							createFrame.hide();
							RewardAdminGUI GUIA = new RewardAdminGUI();
					}
				});
		
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							RewardAdminGUI GUIA = new RewardAdminGUI();
					}
				});
				}
			else 
			{
				
				String selectedRewardName =(String) list.getSelectedValue();
				
				selectedReward = null;
				for(Reward t: rewards) 									
					if(t.getName().equals(selectedRewardName))
						selectedReward = t;
				
				JFrame createFrame;
				JPanel createPanel = new JPanel();
				
				
				if (e.getSource().equals(view)) 			//VIEW
				{
					
				JLabel name = new JLabel("Name: " + selectedReward.getName());
				JLabel price = new JLabel("Price: " +selectedReward.getPrice());
				JLabel pointsL = new JLabel("Points Needed: " + selectedReward.getPointsNeeded());
				JButton back = new JButton("Back");
				createPanel.add(name);
				createPanel.add(price);
				createPanel.add(pointsL);
				createPanel.add(back);
				
				hide();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(200,250);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X View Reward");
				createFrame.setLocationRelativeTo(null);
				
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							RewardAdminGUI GUIA = new RewardAdminGUI();
					}
				});
				
			}
			
			else if (e.getSource().equals(update))					//UPDATE
			{
				
				
		        
				JLabel name = new JLabel("Name");
				JLabel price = new JLabel("Price");
				JLabel points = new JLabel("Points needed");
				
				
				JTextField nameField = new JTextField(10);
				JTextField priceField = new JTextField(10);
				JTextField pointsField = new JTextField(10);
				
				
				JButton update = new JButton("Update");
				JButton cancel = new JButton("Cancel");
				
				createPanel.add(name);
				createPanel.add(nameField);
				createPanel.add(price);
				createPanel.add(priceField);
				createPanel.add(points);
				createPanel.add(pointsField);
				
				createPanel.add(update);
				createPanel.add(cancel);
				
				hide();
				createFrame = new JFrame();
				createFrame.setContentPane(createPanel);
			
				createFrame.setResizable(false);
				createFrame.setSize(200,250);
				createFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				createFrame.setVisible(true);
				createFrame.setTitle("Tour-X Update Reward");
				createFrame.setLocationRelativeTo(null);
				
				update.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createFrame.hide();
						if (!nameField.getText().isEmpty())
							selectedReward.setName(nameField.getText());
						if (!priceField.getText().isEmpty())
							selectedReward.setPrice(Integer.parseInt(priceField.getText()));
						if (!pointsField.getText().isEmpty())
							selectedReward.setPointsNeeded(Integer.parseInt(pointsField.getText()));
							
						try {
							FileOutputStream fileOutTickets = new FileOutputStream("Rewards.ser");
							ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
							out.writeObject(rewards);
							out.close();
							fileOutTickets.close();		
						}
						catch(IOException i) {
							i.printStackTrace();
						}
						finally {
							System.out.println("Serialization Attempted Rewards...");
						}
						
						createFrame.hide();
						RewardAdminGUI GUIA = new RewardAdminGUI();
				}
			});
		
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
							createFrame.hide();
							RewardAdminGUI GUIA = new RewardAdminGUI();
					}
				});
				
			}
			
			else if (e.getSource().equals(delete))													//DELETE
			{   hide();
				for (	Reward t:rewards)
				{
					if ( t.getName().equals(selectedReward.getName()))
						{rewards.remove(t);
						
						try {
							FileOutputStream fileOutTickets = new FileOutputStream("Rewards.ser");
							ObjectOutputStream out = new ObjectOutputStream(fileOutTickets);
							out.writeObject(rewards);
							out.close();
							fileOutTickets.close();		
						}
						catch(IOException i) {
							i.printStackTrace();
						}
						finally {
							System.out.println("Serialization Attempted Rewards...");
						}
					
					      hide();
					      break;}
					
				}
				RewardAdminGUI GUIA = new RewardAdminGUI();
			}
			else
			{
				hide();
				new AdminHomepageGUI();
			}
		}}
	}
}
