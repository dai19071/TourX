import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//Tour-X is a platform for tourist agencies
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Traveller> Travellers = new ArrayList<Traveller>();
		ArrayList<�dmin> Admins = new ArrayList<�dmin>();
		
		
		//���������� ��� Admins
		�dmin Ad1 = new �dmin("stefanos","123");
		�dmin Ad2 = new �dmin("spuros","456");
		�dmin Ad3 = new �dmin("niki","789");
		�dmin Ad4 = new �dmin("mhtsos","058");
				
				Admins.add(Ad1);
				Admins.add(Ad2);
				Admins.add(Ad3);
				Admins.add(Ad4);
				
				try {
					FileOutputStream fileOutAdmins = new FileOutputStream("Admins.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOutAdmins);
					out.writeObject(Admins);
					out.close();
					fileOutAdmins.close();		
				}
				catch(IOException i) {
					i.printStackTrace();
				}

				//���������� ��� Travellers
				Traveller Tr1 = new Traveller("a1","123","nikos");
				Traveller Tr2 = new Traveller("a2","456","xarhs");
				Traveller Tr3 = new Traveller("a3","789","xrhstos");
				
				
				Travellers.add(Tr1);
				Travellers.add(Tr2);
				Travellers.add(Tr3);
			
		
		try {
			FileOutputStream fileOutTravellers = new FileOutputStream("Travellers.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOutTravellers);
			out.writeObject(Travellers);
			out.close();
			fileOutTravellers.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		new WelcomeGUI();
		

	}

}
