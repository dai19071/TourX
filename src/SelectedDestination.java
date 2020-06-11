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
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
/*Στο συγκεκριμενο παραθυρο ο χρηστης επιλεγει τον προορισμο
 * που επιθυμει να ταξιδεψει, οι οποιες προερχονται απο την επιλογη 
 * των δραστηριοτητων.
 */
public class SelectedDestination extends JFrame{
	private JPanel panel = new JPanel();
	private JList list ;
	private JButton select = new JButton("Select");
	private JButton back = new JButton("Back");
	private ArrayList<String> dest;
	private Traveller currentTraveller;
	private final JLabel destinationLabel = new JLabel("Choose a matching destination ");
	private final JLabel lblWhichOneDo = new JLabel("Which one do you prefer?");
	public SelectedDestination (ArrayList<String> dest,Traveller currentTraveller)
	{
		panel.setBackground(Color.WHITE);
		this.setContentPane(panel);
		this.dest = dest;
		this.currentTraveller = currentTraveller;
		panel.setLayout(null);
		list = new JList(dest.toArray());
		list.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list.setBounds(34, 75, 277, 157);
		getContentPane().add(list);
		select.setBounds(219, 243, 92, 23);
		getContentPane().add(select);
		back.setBounds(144, 243, 65, 23);
		getContentPane().add(back);
		destinationLabel.setForeground(new Color(204, 0, 0));
		destinationLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		destinationLabel.setBackground(new Color(204, 0, 0));
		destinationLabel.setBounds(34, 23, 277, 14);
		
		panel.add(destinationLabel);
		lblWhichOneDo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWhichOneDo.setBounds(34, 50, 277, 14);
		
		panel.add(lblWhichOneDo);
		this.setVisible(true);
		this.setSize(361, 326);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Select Destination");
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
				String destinationname = (String) list.getSelectedValue();
				SelectHotelTicketGUI htGUI = new SelectHotelTicketGUI(destinationname,dest,currentTraveller);
			}
			else
			{
				PlanATrip trip = new PlanATrip(currentTraveller);
			}
		}
		
	}

}
