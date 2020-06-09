import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
//Κλάση για την εξαργύρωση των πόντων και την απόκτηση δώρων από τον χρήστη
public class ExploreMyRewardsGUI extends JFrame{
	
	private JPanel panel = new JPanel();
	
	private JLabel availableRewards = new JLabel("Available Rewards");
	private JLabel yourRewards = new JLabel("Rewards you own");
	private JLabel youHavePoints;
	
	private JButton claimReward = new JButton("Claim Reward");
	private JButton back = new JButton("Back");
	
	private JList availableRewardsList = new JList();
	private JList yourRewardsList = new JList();
	
	private DefaultListModel model1 = new DefaultListModel();
	private DefaultListModel model2 = new DefaultListModel();
	
	private ArrayList<Reward> rewards = new ArrayList<Reward>();
	
	private Traveller currentUser;
	private JLabel redeemPointsLabel = new JLabel("Redeem your points and get rewards");
	
	public ExploreMyRewardsGUI() {
		
		currentUser = HomepageGUI.getTraveller();
		
		int userPoints=HomepageGUI.getTraveller().getTravelPoints();
		
		
		//Retrieve Available Rewards from file
		try {
			FileInputStream fileIn = new FileInputStream("rewards.ser");
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
		panel.setBackground(Color.WHITE);
		
		
		
		
		
		//Add available rewards in availableRewardsList
		for(Reward r: rewards)
			model1.addElement(r.getName());
		availableRewardsList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		availableRewardsList.setBounds(370, 99, 277, 157);
		
		availableRewardsList.setModel(model1);
		
		//Add current user's rewards in yourRewardsList
		for(Reward r: HomepageGUI.getTraveller().getRewards())
			model2.addElement(r.getName());
		yourRewardsList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yourRewardsList.setBounds(30, 99, 277, 157);
		
		yourRewardsList.setModel(model2);
		
		
		//Create Panel
		this.setContentPane(panel);
		this.setSize(690,377);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("My Rewards");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel.setLayout(null);
		availableRewards.setForeground(new Color(0, 153, 153));
		availableRewards.setFont(new Font("Tahoma", Font.PLAIN, 16));
		availableRewards.setBounds(370, 68, 129, 20);
		
		panel.add(availableRewards);
		panel.add(availableRewardsList);
		yourRewards.setForeground(new Color(0, 153, 153));
		yourRewards.setFont(new Font("Tahoma", Font.PLAIN, 16));
		yourRewards.setBounds(30, 68, 126, 20);
		
		panel.add(yourRewards);
		panel.add(yourRewardsList);
		back.setBounds(475, 281, 65, 23);
		
		panel.add(back);
		claimReward.setBounds(550, 281, 97, 23);
		panel.add(claimReward);
		
		
		redeemPointsLabel.setForeground(new Color(204, 0, 0));
		redeemPointsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		redeemPointsLabel.setBackground(new Color(204, 0, 0));
		redeemPointsLabel.setBounds(30, 16, 301, 20);
		
		panel.add(redeemPointsLabel);
		
		
		
		ButtonListener listener = new ButtonListener();
		back.addActionListener(listener);
		claimReward.addActionListener(listener);
		
		
		this.setVisible(true);
	}

	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			//Αν πατήσει back πάει στο homepage
			if(e.getSource().equals(back)) {
				dispose();
				HomepageGUI homepage = new HomepageGUI(HomepageGUI.getTraveller());
			}
			//Αλλιώς γίνονται έλεγχοι για το συγκεκριμενο δώρο και αν οι πόντοι επαρκούν τότε ο χρήστης το αποκτά
			else {
				String claimedReward = (String)availableRewardsList.getSelectedValue();
				boolean found = false;
				
				for(Reward r: rewards)
					if(r.getName().equals(claimedReward) && r.getPointsNeeded()<= currentUser.getTravelPoints()) {
						currentUser.addReward(r);
						JOptionPane.showMessageDialog(getComponent(0), "Reward succesfully claimed");
						model1.removeElement(r.getName());
						model2.addElement(r.getName());
						availableRewardsList.setModel(model1);
						yourRewardsList.setModel(model2);
						int userPoints = currentUser.getTravelPoints();
						if(userPoints>0) {
							youHavePoints = new JLabel("You have "+ Integer.toString(userPoints)+ " Travel Points" );
						}
						else {
							youHavePoints = new JLabel("It seems that you don't have any Travel Points"); 
						}
						found = true;
						break;
					}
				if(!found) {
					JOptionPane.showMessageDialog(getComponent(0), "Not enough Travel Points");
				}
			}
			
		}
		
	}
}
