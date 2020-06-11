import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//Ena antikeimeno GroupTrip deixnei ena olokliromeno taxidi me group kai ola ta stoixeia poy sxetizontai me ayto

public class GroupTrip extends TripItem{

	private Destination destination;
	private Hotel hotel ; 
	private Tickets ticket;
	private ArrayList <String> choices;
	private ArrayList <Traveller> travellers;
	private int capacity;
	private ArrayList<String> activities;
	
	public GroupTrip()
	{
		super("",0);
		travellers = new ArrayList<Traveller>();
	}
	
	
	public GroupTrip(String name, double price, int capacity, Destination destination, Hotel hotel, Tickets ticket,
									ArrayList<Traveller> travellers ) { 
		super(name,price);
		this.destination = destination;
		this.hotel = hotel;
		this.ticket = ticket;
		this.choices = choices;
		this.travellers = travellers;
		this.capacity = capacity;
		this.activities = destination.getActivities();
	}
	
	public boolean TravellerExists (Traveller traveller)   //elegxei an enas traveller symmetexei se ena grouptrip
	{
		boolean found= false;
		for (Traveller t: travellers)
			if (t.username.equals(traveller.username))
				found=true;
				
		return found;
	}
	
	public void addTraveller(Traveller traveller)   //prosthetei enan traveller se ena grouptrip
	{
		if(travellers.size()<capacity)
			travellers.add(traveller);
		
		else
		{
			JFrame Full = new JFrame();
			JOptionPane.showMessageDialog(Full, "This Group is full");
		}
	}
	
	public boolean isFull()
	{
		if(capacity == travellers.size())
			return true;
		return false;
	}
	
	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Tickets getTicket() {
		return ticket;
	}

	public void setTicket(Tickets ticket) {
		this.ticket = ticket;
	}

	public ArrayList<String> getChoices() {
		return choices;
	}

	public void setChoices(ArrayList<String> choices) {
		this.choices = choices;
	}

	public ArrayList<Traveller> getTravellers() {
		return travellers;
	}

	public void setTraveller(ArrayList<Traveller> travellers) {
		this.travellers = travellers;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public ArrayList<String> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<String> activities) {
		this.activities = activities;
	}

	public void setTravellers(ArrayList<Traveller> travellers) {
		this.travellers = travellers;
	}
	
}
