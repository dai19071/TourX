import java.io.Serializable;


//i klasi user einai i vasi ton dio idon xristi pou iparxoyn , travveler kai admin



public class User implements Serializable {
	
	protected String username;   //onoma xristi
	protected String password;  //kodikos xristi
//	protected String name;
	
	public User(String username, String password){
		this.username = username; 
		this.password = password;
	//	this.name = name;
	}
	
	/*public boolean login(String username, String password) {
		return true;
	}

	public void reviewAccount() {
		System.out.println("Username: "+username);
		System.out.println("Name: "+name);
	}
	
	public void updateAccount() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Username: ");
		username = in.nextLine();
		System.out.println("Password: ");
		password = in.nextLine();
		System.out.println("Name: ");
		name = in.nextLine();
		
		in.close();
	}
	
	public boolean deleteAccount() {
		return true;
	} */

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
