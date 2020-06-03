import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
//���������� ��� ��������� ��� ������,��� ������ � ���������� �)��� �� ������� ��� ������ �)�� ���� �� ��� ������ �� ������������ ���������
//�)�� ����� �� ���� ��� ���� ��� ������ ��� ������� �)�� ������� �� �������� ��� ���������� ��� ��� �) �� ���� ��� ��� ��������.
public class HomepageGUI extends JFrame{
	
	private JPanel panel = new JPanel();
	
	private JButton planATrip = new JButton("Plan A Trip");
	private JButton joinAGroupTrip = new JButton("Join a Group Trip");
	private JButton exploreMyRewards = new JButton("Explore My Rewards");
	private JButton myAccount = new JButton("My Account");
	private JButton logOut = new JButton("Log Out");
	private static Traveller currentTraveller;
	private final JLabel lblNewLabel_2 = new JLabel("An easy and fast way to plan your trips, earn points and get gifts.");
	private final JLabel lblNewLabel_2_1 = new JLabel("Start now by planning your own trip or joining a group one.");
	
	
	
	public HomepageGUI(Traveller currentTraveller){
		
		this.currentTraveller = currentTraveller;
		
		this.setResizable(false);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		planATrip.setBounds(28, 250, 127, 23);
		
		panel.add(planATrip);
		joinAGroupTrip.setBounds(165, 250, 131, 23);
		panel.add(joinAGroupTrip);
		exploreMyRewards.setBounds(306, 250, 151, 23);
		panel.add(exploreMyRewards);
		myAccount.setBounds(467, 250, 119, 23);
		panel.add(myAccount);
		logOut.setBounds(477, 24, 109, 23);
		panel.add(logOut);
		
		this.setContentPane(panel);
		
		JLabel lblNewLabel = new JLabel("Welcome "+ currentTraveller.getName()+" to Tour-X");
		lblNewLabel.setForeground(new Color(204, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(28, 15, 439, 40);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Travel, Claim, Redeem");
		lblNewLabel_1.setForeground(new Color(0, 102, 153));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(28, 97, 183, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(28, 122, 446, 32);
		
		panel.add(lblNewLabel_2);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(28, 147, 446, 32);
		
		panel.add(lblNewLabel_2_1);
		this.setSize(618,326);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Tour-X Homepage");
		this.setLocationRelativeTo(null);
		
		
		ButtonListener listener = new ButtonListener();
		planATrip.addActionListener(listener);
		joinAGroupTrip.addActionListener(listener);
		exploreMyRewards.addActionListener(listener);
		myAccount.addActionListener(listener);
		logOut.addActionListener(listener);
	}
	
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(logOut))
			{
				//����� logOut ���������� ���������� �� ���������.
				System.exit(0);
			}
			else if(e.getSource().equals(myAccount))
					{
				//��� ������� ��� ��������� ��� ������� ��� ��������� ��� ����������� ��� �� ��� ���� interface
				//		myAccountGUI mine = new myAccountGUI(currentTraveller);
						dispose();
						
					}
			else if(e.getSource().equals(exploreMyRewards))
			{
				//��� ������� ��� ��������� �� ����� �� ���� ��� ������� �� ���� ������� ��� ���� ��������
				dispose();
			//	ExploreMyRewardsGUI rewards = new ExploreMyRewardsGUI();
			}
			else if(e.getSource().equals(joinAGroupTrip))
			{
				//��� ������� ������ ���� ������� ������� �� ��� �������� ���.
			//	GroupGUI group = new GroupGUI(currentTraveller);
				dispose();
			}
			else
			{
				//��� ������� ��� ��������� �� ������� ��� ������ ����� ���.
				dispose();
			//	PlanATrip trip = new PlanATrip(currentTraveller);
			}
			
		}
	}
	
	public static Traveller getTraveller() {
		return currentTraveller;
	}
}
