import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


//� ����� ���� �������������� �� �������� ��� �� ����� � adminer �� ��������� ���� ���������
public class AdminGUI extends JFrame{
	private ArrayList<�dmin> Admins;
	
	private JPanel panel = new JPanel();
	private JTextField username = new JTextField(10);
	private JPasswordField password = new JPasswordField(10);
	private JButton login = new JButton("Login");
	private JLabel usernameLabel = new JLabel("Username");
	private JLabel passwordLabel = new JLabel("Password");
	private final JLabel adminLabel = new JLabel("Tour-X Administration");
	public AdminGUI()
	{
		try {
			FileInputStream fileIn = new FileInputStream("Admins.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Admins = (ArrayList<�dmin>) in.readObject();
			in.close();
			fileIn.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		panel.setBackground(new Color(230, 230, 250));
		this.setContentPane(panel);
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(292,250);
		this.setLocation(300,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameLabel.setBounds(54, 62, 76, 14);
		
		getContentPane().add(usernameLabel);
		username.setBounds(140, 59, 86, 20);
		getContentPane().add(username);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordLabel.setBounds(55, 93, 75, 14);
		getContentPane().add(passwordLabel);
		password.setBounds(140, 90, 86, 20);
		getContentPane().add(password);
		login.setBounds(150, 121, 76, 23);
		getContentPane().add(login);
		adminLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		adminLabel.setBounds(54, 25, 189, 14);
		
		panel.add(adminLabel);
		
		
		ButtonListener listener = new ButtonListener();
		login.addActionListener(listener);
		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(login))
			{
				�dmin found = null;
				for(�dmin s: Admins)
				{
					if(s.getUsername().equals(username.getText()))
					{
						found = s;
					}
				}
				if(found != null)
				{
					//������ ������� � adminer
					if(found.getPassword().equals(password.getText()))
					{
						dispose();
						//������ ������������ �� ����� ������ ���� ��������� �� �������� ������ ��� adminer
						AdminHomepageGUI hp = new AdminHomepageGUI();
						
					}
					else
					{
						//� ������� ���� ����� ���� ������ �� ��� ���� ���������������
						JFrame incorrect = new JFrame();
						JOptionPane.showMessageDialog(incorrect, "The password was incorrect");
						int i;
						boolean userlogged = false;
						for(i=1; i<=2; i++)
						{
							String retry =  JOptionPane.showInputDialog("Please try again");
							if(retry.equals(found.getPassword()))
							{
								dispose();
								//�� ��������� ��� ������ ��� ����� ������
								AdminHomepageGUI hp = new AdminHomepageGUI();
								userlogged = true;
								break;
							}
						}
						if(!userlogged)
						{
							//�� ��������� ��� �������������� 3 ����� ��� ������ ����� ��� ������� ��� ����������.
							JFrame accountClosed = new JFrame();
							JOptionPane.showMessageDialog(accountClosed, "Your account was closed,because you inserted a wrong password many times");
							System.exit(0);						}
					}
				}
				else
				{
					// �� ��������� ��� ��� ������� � adminer ��������� ��� ������ ��� ����� ���� ��� � ������� ��� �������.
					JFrame notExisted = new JFrame();
					JOptionPane.showMessageDialog(notExisted, "The Adminer doesn't exists");
				}
							
			}
		
		}
	}

}
