import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Dhmiourgia ths klashs WelcomeGUI oi opoia apotelei to arxiko parathuro pou kaloswrizei ton xrhsth ths efarmoghs

public class WelcomeGUI extends JFrame {

	private JPanel panel = new JPanel();
	private JButton continueButton;
	
	public WelcomeGUI()
	{
		String path = "icon.png";
        Icon icon = new ImageIcon(path);
        
        continueButton = new JButton(icon);
 
        panel.add(continueButton);
        
        ButtonListener listener = new ButtonListener();
		continueButton.addActionListener(listener);
		
		this.setResizable(false);
		this.setContentPane(panel);
		this.setSize(600,430);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Welcome to Tour-X");
		this.setLocationRelativeTo(null);
	}
	
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(continueButton))
			{
				dispose();
				new UserLogged();
			}
		}
	}
}
