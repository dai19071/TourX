import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

//to epomeno parathiro apo to GroupGUI sto opoio o xristis mporei na dei ta onomata ton group ta opoia toy tairiazoyn

public class JoinGroupGUI extends JFrame {

	private Traveller currentTraveller;
	private ArrayList<GroupTrip> groups;
	private ArrayList<GroupTrip> availableGroups;
	private JPanel panel = new JPanel();
	private JButton viewButton = new JButton("View Group Trip");
	private JButton backButton = new JButton("Back");
	private JList groupsList = new JList();
	private final JLabel groupLabel = new JLabel("Choose a group");
	private final JLabel lblWhichOneDo = new JLabel("Which one do you prefer?");
	
	public JoinGroupGUI(Traveller traveller, ArrayList<GroupTrip> groups,ArrayList<GroupTrip> availableGroups)
	{
		currentTraveller = traveller;
		this.groups = groups;
		this.availableGroups = availableGroups;
		
		DefaultListModel model = new DefaultListModel();
		
		for(GroupTrip item:groups) {
			model.addElement(item.getName());
		}
		groupsList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		groupsList.setBounds(34, 75, 277, 157);
		
		groupsList.setModel(model);
		panel.setBackground(Color.WHITE);
			

		this.setContentPane(panel);
		panel.setLayout(null);
		getContentPane().add(groupsList);
		viewButton.setBounds(176, 243, 135, 23);
		getContentPane().add(viewButton);
		backButton.setBounds(101, 243, 65, 23);
		getContentPane().add(backButton);
		groupLabel.setForeground(new Color(204, 0, 0));
		groupLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		groupLabel.setBackground(new Color(204, 0, 0));
		groupLabel.setBounds(34, 23, 277, 23);
		
		panel.add(groupLabel);
		lblWhichOneDo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWhichOneDo.setBounds(34, 50, 277, 14);
		
		panel.add(lblWhichOneDo);
		
		ButtonListener listener = new ButtonListener();
		viewButton.addActionListener(listener);
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
			if(e.getSource().equals(viewButton))
			{
				
				String choice = (String) groupsList.getSelectedValue();
				GroupTrip selGroup = null;
				for(GroupTrip g:groups)
					if(g.getName().equals(choice))
						selGroup= g;
				
					dispose();
					new JoinGroupGUISecond(currentTraveller, selGroup , groups , availableGroups);
						
			}
			else if(e.getSource().equals(backButton))
			{
				dispose();
				new GroupGUI(currentTraveller);
			}
		}
	}
}
			
