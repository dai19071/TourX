import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
/*Στο συγκεκριμενο παραθυρο 
 * ο χρηστης επιλεγει τις διαραστηριοτητες που το αρεσουν 
 * και αναλογα με αυτες του εμφανιζει τους καταλληλους προορισμους
 */


public class PlanATrip extends JFrame{
	
	private ArrayList<String> Activities = new ArrayList<>();
	private JList acti;
	private JPanel panel = new JPanel();
	private Traveller currentTraveller;
	private JButton select = new JButton("Select");
	private JButton back = new JButton("Back");
	private final JLabel moreInfoLabel = new JLabel("Choose your interests to organize the ideal trip");
	
	public PlanATrip(Traveller currentTraveller)
	{
		try {
			this.currentTraveller = currentTraveller;
			FileInputStream fileIn = new FileInputStream("activities.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Activities = (ArrayList<String>) in.readObject();
			in.close();
			fileIn.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		acti = new JList(Activities.toArray());
		acti.setFont(new Font("Tahoma", Font.PLAIN, 14));
		acti.setBounds(34, 75, 277, 157);
		acti.setSelectionModel(new DefaultListSelectionModel() {
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
		panel.setBackground(Color.WHITE);
		
		this.setContentPane(panel);
		panel.setLayout(null);
		getContentPane().add(acti);
		select.setBounds(219, 243, 92, 23);
		getContentPane().add(select);
		back.setBounds(144, 243, 65, 23);
		getContentPane().add(back);
		
		JLabel interestLabel = new JLabel("What are you interested in?");
		interestLabel.setForeground(new Color(204, 0, 0));
		interestLabel.setBackground(new Color(204, 0, 0));
		interestLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		interestLabel.setBounds(34, 23, 277, 14);
		panel.add(interestLabel);
		moreInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		moreInfoLabel.setBounds(34, 50, 277, 14);
		
		panel.add(moreInfoLabel);
		
		this.setVisible(true);
		this.setSize(361, 326);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("PlanATrip");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		
		ButtonListener listener = new ButtonListener();
		select.addActionListener(listener);
		back.addActionListener(listener);
	}

	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			if(e.getSource().equals(select))
			{
				ArrayList<Destination> desti = new ArrayList<>();
				int [] selected = acti.getSelectedIndices();
				String[] destinationname = new String[50];
				for(int i=0; i<selected.length; i++)
				{
					destinationname[i] = String.valueOf(acti.getModel().getElementAt(selected[i]));
				}
				try {
					FileInputStream fileIn = new FileInputStream("destination.ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					desti = (ArrayList<Destination>) in.readObject();
					in.close();
					fileIn.close();		
				}
				catch(IOException i) {
					i.printStackTrace();
				}
				catch(ClassNotFoundException c) {
					c.printStackTrace();
				}
				ArrayList<String> destinations = new ArrayList<>();
				for(Destination d:desti)
				{
					for(int i=0; i<selected.length; i++)
					{
						if(d.existActivity(destinationname[i]) && !destinations.contains(d.getName()))
						{
							destinations.add(d.getName());
						//	System.out.println(d.getName());
						}
					}
				}
				SelectedDestination selDest = new SelectedDestination(destinations,currentTraveller);
			}
			else
			{
				dispose();
				HomepageGUI home = new HomepageGUI(currentTraveller);
				
			}
		}
	}
}
