import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

//i klasi antistoixei sto parathyro poy emfanizetai ston xristi otan epilegei na mpei se kapoio GroupTrip
//toy zitaei arxika na epilexei tis drastiriotites poy ton endiaferoyn gia to taxidi kai me vasi autes
//vriskei ta group sta opoia tairiazei

public class GroupGUI extends JFrame{

	private JPanel panel = new JPanel();
	private JButton nextButton = new JButton("Next");
	private JButton backButton = new JButton("Back");
	private Traveller currentTraveller;
	private ArrayList<String> availableActivities ;
	private JList actiList; 
	private ArrayList<GroupTrip> availableGroups;
	private ArrayList<GroupTrip> groups;
	private int numAct;
	private final JLabel interestLabel = new JLabel("What are you interested in?");
	private final JLabel lblChooseYourInterests = new JLabel("Choose your interests to join the ideal group");
	
	public GroupGUI(Traveller traveller) {
		
		currentTraveller = traveller;
		
		try {
			FileInputStream fileIn = new FileInputStream("groups.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			availableGroups = (ArrayList<GroupTrip>) in.readObject();
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
		
		try {
			FileInputStream fileIn = new FileInputStream("activities.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			availableActivities = (ArrayList<String>) in.readObject();
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
		panel.setBackground(Color.WHITE);
		
		this.setContentPane(panel);
		panel.setLayout(null);
	
		actiList = new JList(availableActivities.toArray());
		actiList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		actiList.setBounds(34, 75, 277, 157);
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
		getContentPane().add(actiList);
		nextButton.setBounds(230, 243, 81, 23);
		getContentPane().add(nextButton);
		backButton.setBounds(149, 243, 71, 23);
		getContentPane().add(backButton);
		interestLabel.setForeground(new Color(204, 0, 0));
		interestLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		interestLabel.setBackground(new Color(204, 0, 0));
		interestLabel.setBounds(34, 23, 277, 14);
		
		panel.add(interestLabel);
		lblChooseYourInterests.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChooseYourInterests.setBounds(34, 50, 277, 14);
		
		panel.add(lblChooseYourInterests);
		ButtonListener listener = new ButtonListener();
		nextButton.addActionListener(listener);
		backButton.addActionListener(listener);
		
		this.setVisible(true);
		this.setSize(361, 326);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("GroupTrip");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(nextButton))
			{
			dispose();	
			int[] selIndices= actiList.getSelectedIndices();
			String[] actArray = new String[50];
			for (int i = 0; i < selIndices.length; i++)
			{
			      actArray[i] =  String.valueOf(actiList.getModel().getElementAt(selIndices[i]));
		    }
				
			    numAct = selIndices.length;
				
				groups =findGroups(actArray);
				if(groups.size()!=0)
					new JoinGroupGUI(currentTraveller,groups,availableGroups);
					
				else
				{
					dispose();
					JFrame Fail = new JFrame();
					JOptionPane.showMessageDialog(Fail, "There are no groups according to your choices but we "
							+ "suggest you the following options");
					new JoinGroupGUI(currentTraveller,availableGroups,availableGroups);
				}
			}
			else
				{
					dispose();
					new HomepageGUI(currentTraveller);
				}
		}
	}
	
	public ArrayList<GroupTrip> findGroups(String[] activities)  //vriskei ta idanika group pou exoyn 100% antistoixia me tis epithimies
	{
		ArrayList<GroupTrip> groups = new ArrayList<>();
		int act_counter;
	
		
		for (GroupTrip group: availableGroups)
		{
			act_counter=0;
			for (int i=0; i<numAct;i++)
				if (group.getDestination().getActivities().contains(activities[i]))
						act_counter++;
				if(act_counter == numAct && !group.isFull())
				groups.add(group);	
		}
		
		return groups;
	}
}

